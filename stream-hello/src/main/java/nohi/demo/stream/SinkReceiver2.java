package nohi.demo.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by nohi on 2018/6/4.
 */
@EnableBinding(value = {Sink.class , SinkSender.class})
public class SinkReceiver2 {

	private static Logger log = LoggerFactory.getLogger( SinkReceiver2.class );

	@StreamListener(Sink.INPUT)
	public void receiver (Object payLoad) {
		log.info( "RECEIVER:" + payLoad );
	}
}
