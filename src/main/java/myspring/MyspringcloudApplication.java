package myspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import myspring.zuul.AccessFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
public class MyspringcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyspringcloudApplication.class, args);
	}

	// 实例化过滤器
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

}
