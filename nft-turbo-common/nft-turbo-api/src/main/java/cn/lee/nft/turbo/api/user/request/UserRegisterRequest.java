package cn.lee.nft.turbo.api.user.request;

import cn.lee.nft.turbo.base.request.BaseRequest;
import lombok.*;

/**
 * @author lee
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest extends BaseRequest {

    private String telephone;

    private String inviteCode;

    private String password;

}
