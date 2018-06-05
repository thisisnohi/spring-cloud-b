package nohi.demo.consumer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nohi on 2018/5/26.
 */
@RestController
public class ConsumerController {
	private Logger log = LoggerFactory.getLogger( this.getClass() );
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "ribbon-consumer")
	public String helloConsumer(){
		log.info( "=============1==============" );
		String msg = restTemplate.getForEntity( "http://HELLO-SERVICE/hello" , String.class).getBody();
		log.info( "=============2==============" );
		return msg;
	}
}
