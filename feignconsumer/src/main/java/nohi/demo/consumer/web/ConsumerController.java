package nohi.demo.consumer.web;

import nohi.demo.consumer.model.User;
import nohi.demo.consumer.service.HelloService;
import nohi.demo.consumer.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nohi on 2018/5/30.
 */
@RestController
public class ConsumerController {

	@Autowired
	private HelloService service;

	@Autowired
	private RefactorHelloService refactorHelloService;

	@RequestMapping(value = "fegin-consumer",method = RequestMethod.GET)
	public String hello(){
		System.out.println("==fegin-consumer===");
		return service.hello();
	}

	@RequestMapping(value = "fegin-consumer2",method = RequestMethod.GET)
	public String hello2(){
		System.out.println("==fegin-consumer2222222===");
		StringBuffer sb = new StringBuffer(  );
		sb.append( service.hello() ).append( "\n" )
		  .append( service.hello1("hello1111") ).append( "\n" )
		  .append( service.hello2("hello2222",29) ).append( "\n" )
		  .append( service.hello3(new User( "hello3333" , 88 ) ) ).append( "\n" )

				;
		return sb.toString();
	}

	@RequestMapping(value = "fegin-consumer3",method = RequestMethod.GET)
	public String hello3(){
		System.out.println("==fegin-consumer33333===");
		StringBuffer sb = new StringBuffer(  );
		sb
		.append( refactorHelloService.hello4("hello1111") ).append( "<br>\n" )
		.append( refactorHelloService.hello5("hello2222",29) ).append( "<br>\n" )
		.append( refactorHelloService.hello6(new nohi.demo.helloservice.dto.User( "hello3333" , 88 ) ) ).append( "\n" )

		;
		return sb.toString();
	}
}
