package nohi.demo.test;

import nohi.demo.StreamApplication;
import nohi.demo.stream.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by nohi on 2018/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StreamApplication.class)
@WebAppConfiguration
public class SinkReceiverTest {

	@Autowired
	private SinkSender sender;


	@Test
	public void test(){
		System.out.println("==================1=====================");
		sender.output().send( MessageBuilder.withPayload("from sinksend...").build() );
		System.out.println("==================2=====================");
	}
}
