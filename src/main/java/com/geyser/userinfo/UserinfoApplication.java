package com.geyser.userinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.geyser.userinfo.entity")
@EnableJpaRepositories("com.geyser.userinfo.repository")
@EnableCaching
public class UserinfoApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserinfoApplication.class, args);
	}
}
