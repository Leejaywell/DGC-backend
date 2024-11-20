package cn.lee.nft.turbo.cache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author lee
 */
@Configuration
@EnableMethodCache(basePackages = "cn.lee.nft.turbo")
public class CacheConfiguration {
}
