package cn.lee.nft.turbo.order.facade;

import cn.lee.nft.turbo.api.collection.request.CollectionSaleRequest;
import cn.lee.nft.turbo.api.collection.response.CollectionSaleResponse;
import cn.lee.nft.turbo.api.collection.service.CollectionFacadeService;
import cn.lee.nft.turbo.api.order.OrderFacadeService;
import cn.lee.nft.turbo.api.order.constant.OrderErrorCode;
import cn.lee.nft.turbo.api.order.model.TradeOrderVO;
import cn.lee.nft.turbo.api.order.request.*;
import cn.lee.nft.turbo.api.order.response.OrderResponse;
import cn.lee.nft.turbo.api.user.constant.UserType;
import cn.lee.nft.turbo.api.user.request.UserQueryRequest;
import cn.lee.nft.turbo.api.user.response.UserQueryResponse;
import cn.lee.nft.turbo.api.user.response.data.UserInfo;
import cn.lee.nft.turbo.api.user.service.UserFacadeService;
import cn.lee.nft.turbo.base.response.PageResponse;
import cn.lee.nft.turbo.base.response.SingleResponse;
import cn.lee.nft.turbo.lock.DistributeLock;
import cn.lee.nft.turbo.order.domain.entity.TradeOrder;
import cn.lee.nft.turbo.order.domain.entity.convertor.TradeOrderConvertor;
import cn.lee.nft.turbo.order.domain.exception.OrderException;
import cn.lee.nft.turbo.order.domain.service.OrderManageService;
import cn.lee.nft.turbo.order.domain.service.OrderReadService;
import cn.lee.nft.turbo.order.domain.validator.OrderCreateValidator;
import cn.lee.nft.turbo.order.wrapper.InventoryWrapperService;
import cn.lee.nft.turbo.rpc.facade.Facade;
import cn.lee.turbo.stream.producer.StreamProducer;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.lee.nft.turbo.api.order.constant.OrderErrorCode.ORDER_CREATE_VALID_FAILED;

/**
 * @author lee
 */
@DubboService(version = "1.0.0")
public class OrderFacadeServiceImpl implements OrderFacadeService {

    @Autowired
    private OrderManageService orderService;

    @Autowired
    private OrderReadService orderReadService;

    @Autowired
    private InventoryWrapperService inventoryWrapperService;

    @Autowired
    private StreamProducer streamProducer;

    @Autowired
    private UserFacadeService userFacadeService;

    @Autowired
    private CollectionFacadeService collectionFacadeService;

    @Autowired
    private OrderCreateValidator orderValidatorChain;

    @Override
    @DistributeLock(keyExpression = "#request.identifier", scene = "ORDER_CREATE")
    @Facade
    public OrderResponse create(OrderCreateRequest request) {
        try {
            orderValidatorChain.validate(request);
        } catch (OrderException e) {
            return new OrderResponse.OrderResponseBuilder().buildFail(ORDER_CREATE_VALID_FAILED.getCode(), e.getErrorCode().getMessage());
        }

        Boolean preDeductResult = inventoryWrapperService.preDeduct(request);
        if (preDeductResult) {
            return orderService.create(request);
        }
        throw new OrderException(OrderErrorCode.INVENTORY_DEDUCT_FAILED);
    }

    @Override
    @Facade
    public OrderResponse cancel(OrderCancelRequest request) {
        return sendTransactionMsgForClose(request);
    }

    @Override
    @Facade
    public OrderResponse timeout(OrderTimeoutRequest request) {
        return sendTransactionMsgForClose(request);
    }

    @Override
    public OrderResponse confirm(OrderConfirmRequest request) {

        TradeOrder existOrder = orderReadService.getOrder(request.getOrderId());

        CollectionSaleRequest collectionSaleRequest = new CollectionSaleRequest();
        collectionSaleRequest.setUserId(existOrder.getBuyerId());
        collectionSaleRequest.setCollectionId(Long.valueOf(existOrder.getGoodsId()));
        collectionSaleRequest.setIdentifier(request.getIdentifier());
        collectionSaleRequest.setQuantity((long) existOrder.getItemCount());
        CollectionSaleResponse response = collectionFacadeService.trySale(collectionSaleRequest);

        if (response.getSuccess()) {
            return orderService.confirm(request);
        }

        return new OrderResponse.OrderResponseBuilder().orderId(existOrder.getOrderId()).buildFail(response.getResponseCode(), response.getResponseMessage());
    }

    @NotNull
    private OrderResponse sendTransactionMsgForClose(BaseOrderUpdateRequest request) {
        boolean result = streamProducer.send("orderClose-out-0", null, JSON.toJSONString(request), "CLOSE_TYPE", request.getOrderEvent().name());
        OrderResponse orderResponse = new OrderResponse();
        if (result) {
            orderResponse.setSuccess(true);
        } else {
            orderResponse.setSuccess(false);
        }
        return orderResponse;
    }

    @Override
    @Facade
    public OrderResponse pay(OrderPayRequest request) {
        OrderResponse response = orderService.pay(request);
        if (!response.getSuccess()) {
            TradeOrder existOrder = orderReadService.getOrder(request.getOrderId());
            if (existOrder != null && existOrder.isPaid()) {
                if (existOrder.getPayStreamId().equals(request.getPayStreamId()) && existOrder.getPayChannel() == request.getPayChannel()) {
                    return new OrderResponse.OrderResponseBuilder().orderId(existOrder.getOrderId()).buildSuccess();
                } else {
                    return new OrderResponse.OrderResponseBuilder().orderId(existOrder.getOrderId()).buildFail(OrderErrorCode.ORDER_ALREADY_PAID.getCode(), OrderErrorCode.ORDER_ALREADY_PAID.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public SingleResponse<TradeOrderVO> getTradeOrder(String orderId) {
        return SingleResponse.of(TradeOrderConvertor.INSTANCE.mapToVo(orderReadService.getOrder(orderId)));
    }

    @Override
    @Facade
    public SingleResponse<TradeOrderVO> getTradeOrder(String orderId, String userId) {
        return SingleResponse.of(TradeOrderConvertor.INSTANCE.mapToVo(orderReadService.getOrder(orderId, userId)));
    }

    @Override
    @Facade
    public PageResponse<TradeOrderVO> pageQuery(OrderPageQueryRequest request) {
        Page<TradeOrder> tradeOrderPage = orderReadService.pageQueryByState(request.getBuyerId(), request.getState(), request.getCurrentPage(), request.getPageSize());
        List<TradeOrderVO> tradeOrderVos = TradeOrderConvertor.INSTANCE.mapToVo(tradeOrderPage.getRecords());
        tradeOrderVos.forEach(tradeOrderVO -> tradeOrderVO.setSellerName(getSellerName(tradeOrderVO)));
        return PageResponse.of(tradeOrderVos, (int) tradeOrderPage.getTotal(), request.getPageSize(), request.getCurrentPage());
    }

    private String getSellerName(TradeOrderVO tradeOrderVO) {
        if (tradeOrderVO.getSellerType() == UserType.PLATFORM) {
            return "平台";
        }
        UserQueryRequest userQueryRequest = new UserQueryRequest(Long.valueOf(tradeOrderVO.getSellerId()));

        UserQueryResponse<UserInfo> userQueryResponse = userFacadeService.query(userQueryRequest);
        if (userQueryResponse.getSuccess()) {
            return userQueryResponse.getData().getNickName();
        }

        return "-";
    }
}
