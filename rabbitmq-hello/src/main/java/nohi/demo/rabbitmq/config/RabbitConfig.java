package nohi.demo.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nohi on 2018/6/1.
 */
@Configuration
public class RabbitConfig {

	public Queue helloQueue(){
		return new Queue("hello");
	}
}
