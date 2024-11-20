package cn.lee.nft.turbo.base.config;

import cn.lee.nft.turbo.base.utils.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通用模块的配置类
 *
 * @author lee
 */
@Configuration
public class BaseConfiguration {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
