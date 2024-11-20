package cn.lee.nft.turbo.api.order.request;

import cn.lee.nft.turbo.api.order.constant.TradeOrderEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class OrderTimeoutRequest extends BaseOrderUpdateRequest {

    @Override
    public TradeOrderEvent getOrderEvent() {
        return TradeOrderEvent.TIME_OUT;
    }
}

