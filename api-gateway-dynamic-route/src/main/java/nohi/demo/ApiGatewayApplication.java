package nohi.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * Created by nohi on 2018/5/31.
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties(){
		return new ZuulProperties();
	}

	public static void main(String[] args){
		new SpringApplicationBuilder( ApiGatewayApplication.class ).web( true ).run( args );
	}
}