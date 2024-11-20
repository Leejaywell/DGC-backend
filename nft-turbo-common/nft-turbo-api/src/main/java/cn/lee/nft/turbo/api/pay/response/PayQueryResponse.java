package cn.lee.nft.turbo.api.pay.response;

import cn.lee.nft.turbo.api.pay.model.PayOrderVO;
import cn.lee.nft.turbo.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lee
 */
@Getter
@Setter
public class PayQueryResponse extends BaseResponse {

    private List<PayOrderVO> payOrders;
}
