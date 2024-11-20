package cn.lee.nft.turbo.order.domain.listener.event;

import cn.lee.nft.turbo.order.domain.entity.TradeOrder;
import org.springframework.context.ApplicationEvent;

/**
 * @author lee
 */
public class OrderCreateEvent extends ApplicationEvent {

    public OrderCreateEvent(TradeOrder tradeOrder) {
        super(tradeOrder);
    }
}
