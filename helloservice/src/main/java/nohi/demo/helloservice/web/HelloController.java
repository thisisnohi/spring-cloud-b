package nohi.demo.helloservice.web;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nohi on 2018/5/26.
 */
@RestController
public class HelloController {
	private Logger log = LoggerFactory.getLogger( this.getClass() );

	@Autowired
	private DiscoveryClient client;

	@GetMapping(value = "/hello")
	public String index(){
		log.info( "=============index==============" );
		ServiceInstance instance =  client.getLocalServiceInstance();
	    log.info( "host:{},serviceId:{}",instance.getHost(),instance.getServiceId() );
		return "hello world : " + LocalDateTime.now();
	}

	@GetMapping(value = "/hello1")
	public String hello1(@RequestParam String name){
		return "hello : " + name;
	}

	@GetMapping(value = "/hello2")
	public User hello2(@RequestHeader String name,@RequestHeader Integer age){
		return new User( name, age );
	}

	@PostMapping(value = "/hello3")
	public String hello3(@RequestBody User user){
		return user.toString();
	}
}
