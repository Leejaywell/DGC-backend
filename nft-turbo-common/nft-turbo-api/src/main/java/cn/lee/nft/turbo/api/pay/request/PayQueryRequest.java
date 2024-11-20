package cn.lee.nft.turbo.api.pay.request;

import cn.lee.nft.turbo.api.pay.constant.PayOrderState;
import cn.lee.nft.turbo.base.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class PayQueryRequest extends BaseRequest {

    @NotNull(message = "payQueryCondition is null")
    private PayQueryCondition payQueryCondition;

    private PayOrderState payOrderState;

    @NotNull(message = "payerId is null")
    private String payerId;

}
