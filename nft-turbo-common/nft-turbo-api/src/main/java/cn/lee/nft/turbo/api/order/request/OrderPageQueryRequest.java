package cn.lee.nft.turbo.api.order.request;

import cn.lee.nft.turbo.base.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class OrderPageQueryRequest extends PageRequest {

    /**
     * 买家id
     */
    private String buyerId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单状态
     */
    private String state;
}
