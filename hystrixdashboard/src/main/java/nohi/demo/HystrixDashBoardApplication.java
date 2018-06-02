package nohi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by nohi on 2018/5/30.
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run( HystrixDashBoardApplication.class, args);
	}
}
