package cn.lee.nft.turbo.order.domain.listener.event;

import cn.lee.nft.turbo.api.order.request.BaseOrderRequest;
import org.springframework.context.ApplicationEvent;

/**
 * @author lee
 */
public class OrderTimeoutEvent extends ApplicationEvent {

    public OrderTimeoutEvent(BaseOrderRequest baseOrderRequest) {
        super(baseOrderRequest);
    }
}
