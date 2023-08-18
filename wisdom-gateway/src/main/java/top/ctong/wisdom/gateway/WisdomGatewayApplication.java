package top.ctong.wisdom.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WisdomGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisdomGatewayApplication.class, args);
	}

}
