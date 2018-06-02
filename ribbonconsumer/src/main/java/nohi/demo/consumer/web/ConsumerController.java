package nohi.demo.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nohi on 2018/5/26.
 */
@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "ribbon-consumer")
	public String helloConsumer(){
		return restTemplate.getForEntity( "http://HELLO-SERVICE/hello" , String.class).getBody();
	}
}
