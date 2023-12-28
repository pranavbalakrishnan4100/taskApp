package com.onebox.onebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan("com.onebox.entityobjects") 
@ComponentScan("com.onebox.onebox")
public class OneboxApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(OneboxApplication.class, args);
		System.out.println("***Starting Application***");
	}

}
