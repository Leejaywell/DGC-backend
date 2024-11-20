package cn.lee.nft.turbo.auth.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Setter
@Getter
public class LoginParam extends RegisterParam {

    /**
     * 记住我
     */
    private Boolean rememberMe;
}
