package nohi.demo.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by nohi on 2018/5/30.
 */
@FeignClient("hello-service")
public interface RefactorHelloService extends nohi.demo.helloservice.intf.HelloService{
}