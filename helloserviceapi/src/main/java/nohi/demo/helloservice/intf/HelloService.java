package nohi.demo.helloservice.intf;

import nohi.demo.helloservice.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nohi on 2018/5/30.
 */
@RequestMapping("refactor")
public interface HelloService {
	@RequestMapping(value = "/hello4",method = RequestMethod.GET)
	String hello4(@RequestParam(value = "name") String name);

	@RequestMapping(value = "/hello5",method = RequestMethod.GET)
	User hello5(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") Integer age);

	@RequestMapping(value = "/hello6",method = RequestMethod.POST)
	String hello6(@RequestBody User user);
}
