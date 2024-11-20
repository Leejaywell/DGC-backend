package cn.lee.nft.turbo.api.notice.service;


import cn.lee.nft.turbo.api.notice.response.NoticeResponse;

/**
 * @author lee
 */
public interface NoticeFacadeService {
    /**
     * 生成并发送短信验证码
     *
     * @param telephone
     * @return
     */
    public NoticeResponse generateAndSendSmsCaptcha(String telephone);
}
