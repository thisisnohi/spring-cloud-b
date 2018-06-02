package nohi.demo.consumer.web;

import nohi.demo.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nohi on 2018/5/26.
 */
@RestController
public class ConsumerHystrixController {

	@Autowired
	private HelloService service;

	@GetMapping(value = "ribbon-consumer-hystrix")
	public String helloConsumer(){
		return service.helloConsumer();
	}

	@RequestMapping(value = "ribbon-consumer-hystrix-cache/{id}",method = RequestMethod.GET)
	public String helloConsumerCache(@PathVariable("id") String id){
		System.out.println("======1111-----");
		return service.helloConsumerCache(id);
	}

	@GetMapping(value = "ribbon-consumer-update/{id}")
	public String update(@PathVariable("id") String id){
		return service.update(id);
	}
}
