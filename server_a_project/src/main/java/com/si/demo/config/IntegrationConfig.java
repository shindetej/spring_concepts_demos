package com.si.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
	
	@Bean
	MessageChannel inputChannel(){
		return new DirectChannel();
	}
	
	@Bean
	IntegrationFlow integrationFlow() {
		return IntegrationFlow.from("inputChannel")
						.handle(Http.outboundGateway("http://localhost:8082/api/receive")
								.httpMethod(HttpMethod.POST).expectedResponseType(String.class))
						.log()
						.get();
	}

}
