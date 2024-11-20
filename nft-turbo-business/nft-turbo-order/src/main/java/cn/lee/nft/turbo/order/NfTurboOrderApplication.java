package cn.lee.nft.turbo.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 */
@SpringBootApplication(scanBasePackages = "cn.lee.nft.turbo.order")
@EnableDubbo
public class NfTurboOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfTurboOrderApplication.class, args);
    }

}
