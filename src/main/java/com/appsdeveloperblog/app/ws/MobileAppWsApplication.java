package com.appsdeveloperblog.app.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.appsdeveloperblog.app.ws.repository.ItemRepository;

@EnableMongoRepositories
@SpringBootApplication
public class MobileAppWsApplication {
	
	@Autowired
    ItemRepository groceryItemRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}

}
