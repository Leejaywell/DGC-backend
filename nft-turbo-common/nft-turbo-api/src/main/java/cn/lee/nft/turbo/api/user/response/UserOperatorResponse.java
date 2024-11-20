package cn.lee.nft.turbo.api.user.response;

import cn.lee.nft.turbo.api.user.response.data.UserInfo;
import cn.lee.nft.turbo.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作响应
 *
 * @author lee
 */
@Getter
@Setter
public class UserOperatorResponse extends BaseResponse {

    private UserInfo user;
}
