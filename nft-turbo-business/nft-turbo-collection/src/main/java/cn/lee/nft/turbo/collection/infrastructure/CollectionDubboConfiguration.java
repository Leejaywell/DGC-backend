package cn.lee.nft.turbo.collection.infrastructure;

import cn.lee.nft.turbo.api.chain.service.ChainFacadeService;
import cn.lee.nft.turbo.api.order.OrderFacadeService;
import cn.lee.nft.turbo.api.user.service.UserFacadeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 */
@Configuration
public class CollectionDubboConfiguration {

    @DubboReference(version = "1.0.0")
    private OrderFacadeService orderFacadeService;

    @DubboReference(version = "1.0.0")
    private ChainFacadeService chainFacadeService;

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "orderFacadeService")
    public OrderFacadeService orderFacadeService() {
        return orderFacadeService;
    }


    @Bean
    @ConditionalOnMissingBean(name = "chainFacadeService")
    public ChainFacadeService chainFacadeService() {
        return chainFacadeService;
    }


    @Bean
    @ConditionalOnMissingBean(name = "userFacadeService")
    public UserFacadeService userFacadeService() {
        return userFacadeService;
    }

}
