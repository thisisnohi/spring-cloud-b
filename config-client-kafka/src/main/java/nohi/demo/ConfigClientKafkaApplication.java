package nohi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by nohi on 2018/6/1.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientKafkaApplication {
	public static void main(String[] args) {
		SpringApplication.run( ConfigClientKafkaApplication.class, args);
	}
}
