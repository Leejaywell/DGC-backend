package cn.lee.nft.turbo.user.infrastructure;

import cn.lee.nft.turbo.api.chain.service.ChainFacadeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 */
@Configuration
public class UserDubboConfiguration {

    @DubboReference(version = "1.0.0")
    private ChainFacadeService chainFacadeService;


    @Bean
    @ConditionalOnMissingBean(name = "chainFacadeService")
    public ChainFacadeService chainFacadeService() {
        return chainFacadeService;
    }
}
