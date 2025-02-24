package com.si.channels;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PrintService {

	@ServiceActivator(inputChannel = "inputChannel")
	public void print(Message<String> message) {
		System.out.println("In PrintService.print()");
		System.out.println(message.getPayload());
		
//		for(Map.Entry<String ,Object> entry : message.getHeaders().entrySet()) {
//			System.out.println(entry.getKey() + " : "+entry.getValue());
//		}
	}
}
