package nohi.demo.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nohi on 2018/6/1.
 */
@RestController
@RefreshScope
public class TestController {

	@Value( "${from}" )
	private String from;

	@Autowired
	private Environment env;

	@GetMapping("/from")
	public String from(){

		return this.from;
	}

	@GetMapping("/from1")
	public String from1(){
		return env.getProperty( "from" ,"undefined");
	}

}
