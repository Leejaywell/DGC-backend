package cn.lee.nft.turbo.trade.param;

import cn.lee.nft.turbo.api.pay.constant.PayChannel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class PayParam {

    @NotNull(message = "orderId is null")
    private String orderId;

    @NotNull(message = "payChannel is null")
    private PayChannel payChannel;

}
