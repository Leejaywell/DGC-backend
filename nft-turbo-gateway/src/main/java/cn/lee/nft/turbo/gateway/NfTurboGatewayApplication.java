package cn.lee.nft.turbo.gateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 */
@SpringBootApplication(scanBasePackages = "cn.lee.nft.turbo.gateway")
public class NfTurboGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfTurboGatewayApplication.class, args);
    }

}
