package nohi.demo.consumer.service;

import nohi.demo.consumer.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nohi on 2018/5/30.
 */
@FeignClient("hello-service")
public interface HelloService {
	@RequestMapping("hello")
	String hello();


	@RequestMapping(value = "hello1",method = RequestMethod.GET)
	String hello1(@RequestParam("name")String name);

	@RequestMapping(value = "hello2",method = RequestMethod.GET)
	User hello2(@RequestHeader("name")String name, @RequestHeader("age")Integer age);

	@RequestMapping(value = "hello3",method = RequestMethod.POST)
	String hello3(@RequestBody User user);
}
