package cn.lee.nft.turbo.order.domain.validator;

import cn.lee.nft.turbo.api.goods.model.BaseGoodsVO;
import cn.lee.nft.turbo.api.goods.service.GoodsFacadeService;
import cn.lee.nft.turbo.api.order.request.OrderCreateRequest;
import cn.lee.nft.turbo.order.domain.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.lee.nft.turbo.api.order.constant.OrderErrorCode.GOODS_NOT_AVAILABLE;
import static cn.lee.nft.turbo.api.order.constant.OrderErrorCode.GOODS_PRICE_CHANGED;

/**
 * 商品校验器
 *
 * @author lee
 */
@Component
public class GoodsValidator extends BaseOrderCreateValidator {

    @Autowired
    private GoodsFacadeService goodsFacadeService;

    @Override
    protected void doValidate(OrderCreateRequest request) throws OrderException {
        BaseGoodsVO baseGoodsVO = goodsFacadeService.getGoods(request.getGoodsId(), request.getGoodsType());

        if (!baseGoodsVO.available()) {
            throw new OrderException(GOODS_NOT_AVAILABLE);
        }

        if (baseGoodsVO.getPrice().compareTo(request.getItemPrice()) != 0) {
            throw new OrderException(GOODS_PRICE_CHANGED);
        }
    }
}
