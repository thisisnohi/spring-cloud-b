package nohi.demo.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nohi on 2018/5/29.
 */
@Service
public class HelloService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback")
	public String helloConsumer(){
		return restTemplate.getForEntity( "http://HELLO-SERVICE/hello" , String.class).getBody();
	}

	public String helloFallback(){
		return "something is wrong...";
	}

	public String abc(String id){
		return "something is wrong...abc...";
	}

	@HystrixCommand(fallbackMethod = "abc",commandKey = "findUserById", groupKey = "UserService", threadPoolKey = "userServiceThreadPool")
	@CacheResult(cacheKeyMethod = "getCacheKey" )
	public String helloConsumerCache(String id){
		System.out.println("======================helloConsumerCache:" + id);
		return restTemplate.getForEntity( "http://HELLO-SERVICE/hello", String.class ).getBody();
	}


	public String getCacheKey(String id){
		System.out.println("getKey:" + id);
		return id;
	}

	@HystrixCommand(fallbackMethod = "abc")
	@CacheRemove(commandKey = "helloConsumerCache", cacheKeyMethod = "getCacheKey")
	public String update(String id){
		System.out.println("======================update:" + id);
		return restTemplate.getForEntity( "http://HELLO-SERVICE/hello" , String.class).getBody();
	}
}
