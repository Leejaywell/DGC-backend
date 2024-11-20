package cn.lee.nft.turbo.trade.param;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class CancelParam {

    @NotNull(message = "orderId is null")
    private String orderId;
}
