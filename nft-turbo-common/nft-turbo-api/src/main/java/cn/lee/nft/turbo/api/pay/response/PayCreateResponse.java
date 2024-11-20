package cn.lee.nft.turbo.api.pay.response;

import cn.lee.nft.turbo.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class PayCreateResponse extends BaseResponse {

    private String payOrderId;

    private String payUrl;
}
