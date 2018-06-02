package nohi.demo;

import nohi.demo.filter.AccessFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by nohi on 2018/5/31.
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}

	public static void main(String[] args){
		new SpringApplicationBuilder( ApiGatewayApplication.class ).web( true ).run( args );
	}
}
