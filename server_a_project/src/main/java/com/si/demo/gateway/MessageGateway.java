package com.si.demo.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.si.demo.model.PersonInfo;

@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = "inputChannel")
	String sentToChannel(PersonInfo person);
}
