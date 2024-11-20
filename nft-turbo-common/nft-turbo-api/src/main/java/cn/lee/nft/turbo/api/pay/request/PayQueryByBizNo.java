package cn.lee.nft.turbo.api.pay.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class PayQueryByBizNo implements PayQueryCondition {

    private String bizNo;

    private String bizType;
}
