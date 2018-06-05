package nohi.demo.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * Created by nohi on 2018/6/4.
 */
public interface SinkSender {

	@Output(Sink.INPUT)
	MessageChannel output();
}
