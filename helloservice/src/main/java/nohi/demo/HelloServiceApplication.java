package nohi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by nohi on 2018/5/26.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HelloServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run( HelloServiceApplication.class, args);
	}
}
