package cn.lee.turbo.stream.config;

import cn.lee.turbo.stream.producer.StreamProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 */
@Configuration
public class StreamConfiguration {
    @Bean
    public StreamProducer streamProducer() {
        StreamProducer streamProducer = new StreamProducer();
        return streamProducer;
    }
}
