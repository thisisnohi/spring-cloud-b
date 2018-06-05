# spring-cloud-b
# SpringCloud微服务实践.md

# Spring Cloud Eureka 
```
server.port=1111
# 指定该Eureka实例的主机名
eureka.instance.hostname=discovery
# 不向注册中心注册自己
eureka.client.registerWithEureka=false
# 不检索服务
eureka.client.fetchRegistry=false
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
```


# 第６章 声明式服务调用Spring Cloud Feign
## Ribbon配置
*  全局配置
    ribbon.ConnectTimeout=500
    ribbon.ReadTimeOut=5000
* 指定服务配置
    HELLO-SERVICE.ribbon.ConnectTimeout=500
    HELLO-SERVICE.ribbon.ReadTimeOut=5000
    HELLO-SERVICE.ribbon.OkToRetryOnAllOperations=true
    HELLO-SERVICE.ribbon.MaxAutoRetriesNextServer=2
    HELLO-SERVICE.ribbon.MaxAutoRetires=1
## Hystrix配置
* 全局配置
   超时时间
    hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=5000
    feign.hystrix.enabled=false #关闭hystrix
    hystirx.command.default.execution.timeout.enabled=false　＃关闭熔断功能
* 禁用Hystrix Feign.Builder

* 指定命令配置
    hystrix.command.<commandKey>
    hystrix.command.hello.execution.isolation.thread.timeoutInMilliseconds=5000
## 其他配置
* 请求压缩
    feign.compression.request.enabled=true
    feign.compression.response.enabled=true

* 日志配置
    loggin.level.com.didispace.web.HelloService=DEBUG
     
       Logger.Level feignLoggerLevel(){
             return Logger.Level.FULL;
        }
# 第７章 API网关服务 Spring Cloud Zuul  路由、请求过滤
> 面向服务的路由 Spring Cloud Zuul 与   Spring Cloud Eureka无缝整合
# 路由规则
# 根据path匹配url
zuul.routes.api-a-url.path=/api-a-url/**
zuul.routes.api-a-url.url=http://localhost:8080/
# 根据path匹配eureka的服务名
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=hello-service

zuul.routes.api-b.path=/api-b/**
zuul.routes.api-b.serviceId=feign-consumer
zuul.routes.<serviceId>=<path>
zuul.ignored-services=　　过滤服务
zuul.ignored-patterns
zuul.routes.api-b.url=florward:/local
## 请求过滤
public class AccessFilter extends ZuulFilter {
zuul.AccessFilter.pre.disable=true  #关闭过滤器
* 作为系统的统一入口，屏蔽了系统内部微服务细节
* 与服务治理框架结合，实现自动化的服务实例维护与负载均衡
* 实现接口权限校验，与微服务业务逻辑解耦
* 网关过滤器，对各生命周期中去校验请求

zuul.routes.<router>.sensitiveHeaders=    #指定路由为敏感头设置为空
zull.addHostHeader=true  #网关在路由转发前为请求设置HOST头信息

 路由转发请求HystrixCommand执行超时时间，单位毫秒
 hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=5000
 ribbon.ConnectTimeout  # 路由转发请求时，创建请求链接的超时时间

# 第８章 分布式配置中心Spring Cloud Config
* 服务端
> 分布式配置中心，连接配置仓库并为客户端提供获取配置信息、加密/解密信息等访问接口
spring.application.name=config-server
spring.cloud.config.server.git.uri=https://github.com/thisisthis/spring-cloud-a
#spring.cloud.config.server.git.uri=https://github.com/thisisthis/{application}
# windows file:///aa/bb/cc   linux  file://aa/bb/cc   //${user.home}/conf-repo
# spring.cloud.config.server.git.uri=https://github.com/thisisthis/spring-cloud-a
spring.cloud.config.server.git.search-paths=config-repo
spring.cloud.config.server.git.username=
spring.cloud.config.server.git.password=

* 客户端
>

配置多个仓库
子目录存储
SVN仓库
本地仓库  spring.cloud.config.server.git.basedir   spring.cloud.config.server.svn.basedir
本地文件系统  spring.profiles.active=native (默认目录src/main/resource)  spring.cloud.config.server.native.searchLocations=
失败快速响应与重试 spring.cloud.config.failFast=true
动态刷新  curl -d "" http://localhost:7002/refresh

# 第９章 消息总线 Spring Cloud Bus
## 消息代理
> 消息验证、传输、路由
目前仅支持RabbitMq 和 Kafka

## RabbitMQ 
* 消息方向
* 消息队列
* 消息路由(包括点到点和发布－订阅模式)
* 可靠性
* 安全性
### 基本概念
* Broker: 服务器实例，负责接收生产者的消息，然后将消息发送至消息接收者或其他Broker
* Exchange: 消息交换机，消息第一个到达的地方，通过它指定的路由规则，分发到不同的消息队列中去。
* Query: 消息队列
* Binding: 绑定  把Exchange和Queue按路由规则绑定起来，虚拟连接
* Routing Key: 路由关键字,Exchange根据这个关键字进行消息投递
* Virtual host:虚拟主机。对Broker虚拟划分。一个Broker设置多个虚拟主机，对不同用户进行权限的分离
* Connection: 连接。生产者、消费者、Broker之间通信的物理网络
* Channel: 消息通道。生产、消费者的逻辑结构。客户端连接里可以建立多个Channel,每个Channel代表一个会话
* Producer: 消息生产者
* Consumer:消息消费者
> 消息投递的过程
1. 客户端连接到消息队列服务器，打开一个Channel
2. 客户端声明一个Exchange，并设置相关属性
3. 客户端声明一个Queue,并设置相关属性
4. 客户端使用Routing Key，在Exchange和Queue之间建立好绑定关系
5. 客户端投递消息到Exchange
6. Exchange接收到消息后，根据消息Key和已经设置的Binding，进行消息路由，将消息投递到一个或多个Queue里

### 整合Spring Cloud Bus
```
config-client增加依赖，启动client后有/bug/refresh端点，更新一个后，会通知其他client
curl -d "" http://localhost:7002/bus/refresh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

```
* 刷新所有服务 curl -d "" http://localhost:7002/bus/refresh
* 刷新指定服务 /bus/refresh?destination=customers:9000    /bus/refresh?destination=customers:**

## Kafka
* 消息持久化
* 高吞吐
* 分布式
* 跨平台
* 实时性
* 伸缩性
### 基本概念
* Broker: Kafka集群包含一个或多个服务器(Broker)
* Topic: 逻辑上同RabbitMQ的Queue队列,每条消息都必须有一个topic
* Partition: 物理概念上的分区，为了提供系统吞吐率。每个topic分成一个或多个Partition，每个Partiion对应一个文件夹
* Producer: 消息生产者
* Consumer：消息消费者
* Cousumer Group : 每个Consumer属于一个特定的组

启动Zookeeper:  zookeeper-server-start.bat ../../config/zookeeper.properties
启动Kafka: kafka-server-start.bat  ../../config/server.properties
创建Topic:  kafka-topics.bat  --create  --zookeeper localhost:2181  --replication-factor 1 --partitions 1 --topic test
创建消息生产： kafka-console-producer.bat  --broker-list localhost:9092 --topic test
创建消息消费者: kafka-console-consumer.bat --zookeeper localhost:2181 --topic test --from-beginning



### 整合Spring Cloud Bus
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-kafka</artifactId>
</dependency>
查看主题 kafka-topics.bat --list --zookeeper localhost:2181
刷新 /bus/refresh
```
kafka-console-consumer.bat --zookeeper localhost:2181 --topic springCloudBus

# 第10章 消息驱动的微服务 Spring Cloud Stream
* 绑定器
* 发布-订阅模式
* 消费组
* 消费分区
# 第11章 分布式服务跟踪 Spring Cloud Sleuth
## 与Logstash整合 
ELK:     ElasticSearch、Logstash、Kibana
## 与Zipkini整合
