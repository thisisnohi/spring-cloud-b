package nohi.demo;

import nohi.demo.rabbitmq.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nohi on 2018/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = HelloApplicationTest.class)
@SpringApplicationConfiguration(classes = RabbitMqApplication.class)
public class HelloApplicationTest {

	@Autowired
	private Sender sender;

	@Test
	public void test(){
		sender.send();
	}
}
