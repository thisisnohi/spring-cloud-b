package nohi.demo.rabbitmq.sender;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nohi on 2018/6/1.
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(){
		String msg = "hello world : " + LocalDateTime.now();
		this.rabbitTemplate.convertAndSend( "hello" , msg );
	}
}
