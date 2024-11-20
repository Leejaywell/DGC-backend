package cn.lee.nft.turbo.order.domain.validator;

import cn.lee.nft.turbo.api.order.request.OrderCreateRequest;
import cn.lee.nft.turbo.api.user.constant.UserRole;
import cn.lee.nft.turbo.api.user.constant.UserStateEnum;
import cn.lee.nft.turbo.api.user.request.UserQueryRequest;
import cn.lee.nft.turbo.api.user.response.UserQueryResponse;
import cn.lee.nft.turbo.api.user.response.data.UserInfo;
import cn.lee.nft.turbo.api.user.service.UserFacadeService;
import cn.lee.nft.turbo.order.domain.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.lee.nft.turbo.api.order.constant.OrderErrorCode.*;

/**
 * 用户校验器
 *
 * @author lee
 */
@Component
public class UserValidator extends BaseOrderCreateValidator {

    @Autowired
    private UserFacadeService userFacadeService;

    @Override
    public void doValidate(OrderCreateRequest request) throws OrderException {
        String buyerId = request.getBuyerId();
        UserQueryRequest userQueryRequest = new UserQueryRequest(Long.valueOf(buyerId));
        UserQueryResponse<UserInfo> userQueryResponse = userFacadeService.query(userQueryRequest);
        if (userQueryResponse.getSuccess() && userQueryResponse.getData() != null) {
            UserInfo userInfo = userQueryResponse.getData();
            if (userInfo.getUserRole() != null && !userInfo.getUserRole().equals(UserRole.CUSTOMER)) {
                throw new OrderException(BUYER_IS_PLATFORM_USER);
            }
            //判断买家状态
            if (userInfo.getState() != null && !userInfo.getState().equals(UserStateEnum.ACTIVE.name())) {
                throw new OrderException(BUYER_STATUS_ABNORMAL);
            }
            //判断买家状态
            if (userInfo.getState() != null && !userInfo.getCertification()) {
                throw new OrderException(BUYER_NOT_AUTH);
            }
        }
    }
}
