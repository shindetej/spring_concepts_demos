package com.si.channels;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;


@MessagingGateway
public interface PrinterGateway {
	@Gateway(requestChannel = "inputChannel")
	public void print(Message<?> message);
}
