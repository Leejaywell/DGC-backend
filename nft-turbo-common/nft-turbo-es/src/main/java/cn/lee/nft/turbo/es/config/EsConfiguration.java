package cn.lee.nft.turbo.es.config;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * ES配置
 *
 * @author lee
 */
@Configuration
@EsMapperScan("cn.lee.nft.turbo.*.infrastructure.es.mapper")
@ConditionalOnProperty(value = "easy-es.enable", havingValue = "true")
public class EsConfiguration {

}
