package com.empire.shipmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableAspectJAutoProxy
public class ShipManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipManagementApplication.class, args);
	}

}
