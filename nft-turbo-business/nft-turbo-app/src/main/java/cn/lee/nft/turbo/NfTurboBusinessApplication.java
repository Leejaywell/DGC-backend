package cn.lee.nft.turbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 */
@SpringBootApplication(scanBasePackages = "cn.lee.nft.turbo")
public class NfTurboBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfTurboBusinessApplication.class, args);
    }

}
