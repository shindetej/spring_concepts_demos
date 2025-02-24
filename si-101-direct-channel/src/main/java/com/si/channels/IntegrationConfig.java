package com.si.channels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

	@Bean
	MessageChannel inputChannel() {
		return new DirectChannel();
	}
	
	
}
