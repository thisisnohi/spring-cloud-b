package nohi.demo.helloservice.web;

import nohi.demo.helloservice.dto.User;
import nohi.demo.helloservice.intf.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nohi on 2018/5/26.
 */
@RestController
public class RefactoryHelloController implements HelloService {
	private Logger log = LoggerFactory.getLogger( this.getClass() );

	@Override
	public String hello4(@RequestParam(value = "name")String name) {
		return "hello world : " + name;
	}

	@Override
	public User hello5(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") Integer age) {
		return new User( name,age );
	}

	@Override
	public String hello6(@RequestBody User user) {
		return user.toString();
	}
}
