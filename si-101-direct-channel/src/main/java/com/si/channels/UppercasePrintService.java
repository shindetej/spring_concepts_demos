package com.si.channels;

import java.util.Map;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class UppercasePrintService {

	@ServiceActivator(inputChannel = "inputChannel" )
		public void print(Message<String> message) {
			System.out.println("In UppercasePrintService.print()");
			System.out.println(message.getPayload().toUpperCase());
		}
}
