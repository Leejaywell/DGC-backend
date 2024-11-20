package cn.lee.nft.turbo.sms;

import cn.lee.nft.turbo.sms.response.SmsSendResponse;

/**
 * 短信服务
 *
 * @author lee
 */
public interface SmsService {
    /**
     * 发送短信
     *
     * @param phoneNumber
     * @param code
     * @return
     */
    public SmsSendResponse sendMsg(String phoneNumber, String code);
}
