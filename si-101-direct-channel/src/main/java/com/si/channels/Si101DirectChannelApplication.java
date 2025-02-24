package com.si.channels;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class Si101DirectChannelApplication implements ApplicationRunner {
	
	@Autowired
	public PrinterGateway printerGateway;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Si101DirectChannelApplication.class, args);
	}

	public void run(ApplicationArguments args) {
		List<Future<Message<String>>> futures = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Message<String> message = MessageBuilder.withPayload("Printing Message Payload for msg no :" + i)
					.setHeader("msgNo", i).build();
			
			this.printerGateway.print(message);
		}
	}

}
