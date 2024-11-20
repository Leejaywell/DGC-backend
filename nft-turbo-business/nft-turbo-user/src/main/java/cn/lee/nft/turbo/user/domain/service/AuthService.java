package cn.lee.nft.turbo.user.domain.service;

/**
 * 认证服务
 *
 * @author lee
 */
public interface AuthService {
    /**
     * 校验认证信息
     *
     * @param realName
     * @param idCard
     * @return
     */
    public boolean checkAuth(String realName, String idCard);
}
