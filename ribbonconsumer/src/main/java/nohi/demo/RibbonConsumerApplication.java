package nohi.demo;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nohi on 2018/5/26.
 *
 *
 * @SpringCloudApplication 包含
 * 			@SpringBootApplication
 *          @EnableDiscoveryClient
 *          @EnableCircuitBreaker
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class RibbonConsumerApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.addInitParameter("name", "alue");//添加默认参数
		registration.setName("MyFilter");//设置优先级
		registration.setOrder(1);//设置优先级
		return registration;
	}

	public class MyFilter implements Filter {
		@Override
		public void destroy() {
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;

			HystrixRequestContext context = HystrixRequestContext.initializeContext();

			//打印请求Url
			System.out.println("this is MyFilter,url :" + request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
		}
	}

	public static void main(String[] args) {
		SpringApplication.run( RibbonConsumerApplication.class, args);
	}
}
