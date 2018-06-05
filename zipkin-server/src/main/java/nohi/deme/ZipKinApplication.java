package nohi.deme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by nohi on 2018/6/5.
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipKinApplication {

	public static void main(String[] args) {
		SpringApplication.run( ZipKinApplication.class, args);
	}
}
