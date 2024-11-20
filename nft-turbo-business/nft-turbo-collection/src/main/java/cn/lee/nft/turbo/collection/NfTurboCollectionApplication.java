package cn.lee.nft.turbo.collection;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 */
@SpringBootApplication(scanBasePackages = "cn.lee.nft.turbo.collection")
@EnableDubbo
public class NfTurboCollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfTurboCollectionApplication.class, args);
    }

}
