package com.project.warmyhomes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WarmyHomesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarmyHomesApplication.class, args);
	}

}
