package cn.lee.nft.turbo.api.user.request;

import cn.lee.nft.turbo.base.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lee
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyRequest extends BaseRequest {
    @NotNull(message = "userId不能为空")
    private Long userId;
    private String nickName;
    private String password;
    private String profilePhotoUrl;
    private String telephone;

}
