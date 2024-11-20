package cn.lee.nft.turbo.pay.infrastructure;

import cn.lee.nft.turbo.api.chain.service.ChainFacadeService;
import cn.lee.nft.turbo.api.collection.service.CollectionFacadeService;
import cn.lee.nft.turbo.api.collection.service.CollectionManageFacadeService;
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
public class PayDubboConfiguration {

    @DubboReference(version = "1.0.0")
    private CollectionFacadeService collectionFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "collectionFacadeService")
    public CollectionFacadeService collectionFacadeService() {
        return collectionFacadeService;
    }

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "userFacadeService")
    public UserFacadeService userFacadeService() {
        return userFacadeService;
    }

    @DubboReference(version = "1.0.0")
    private OrderFacadeService orderFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "orderFacadeService")
    public OrderFacadeService orderFacadeService() {
        return this.orderFacadeService;
    }

    @DubboReference(version = "1.0.0")
    private ChainFacadeService chainFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "chainFacadeService")
    public ChainFacadeService chainFacadeService() {
        return this.chainFacadeService;
    }

    @DubboReference(version = "1.0.0")
    private CollectionManageFacadeService collectionManageFacadeService;

    @Bean
    @ConditionalOnMissingBean(name = "collectionManageFacadeService")
    public CollectionManageFacadeService collectionManageFacadeService() {
        return collectionManageFacadeService;
    }
}
