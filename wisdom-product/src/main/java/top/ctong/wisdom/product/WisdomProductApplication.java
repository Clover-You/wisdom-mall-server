package top.ctong.wisdom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WisdomProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisdomProductApplication.class, args);
	}

}
