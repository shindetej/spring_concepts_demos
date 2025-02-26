package com.si.channels;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
public class FTPIntegrationConfig {

	@Bean
	public DefaultFtpSessionFactory ftpSessionFactory() {
		DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(21);
		factory.setUsername("tshinde");
		factory.setPassword("admin@123");
		System.out.println("default ftp session factory " + factory.toString());
		return factory;
	}

	@Bean
	public IntegrationFlow ftpInboundFlow() {
		System.out.println("IN ftpInboundFlow()");
		return IntegrationFlow
                .from(Ftp.inboundAdapter(ftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory("/test")  
                        .localDirectory(new File("C:\\Users\\shind\\Downloads\\ftp_temp"))  // Local Directory
                        .deleteRemoteFiles(true)  
                        .autoCreateLocalDirectory(true),
                        e -> e.poller(Pollers.fixedDelay(2000)))
                .handle((p,h)-> {
                	System.out.println("Payload : "+p);
                	System.out.println("Header : "+h);
                	return p;
                })
                .channel(ftpInputChannel())  // Connect to processing channel
                .get();
	}

	// Define Message Channel
	@Bean
	public MessageChannel ftpInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow fileProcessingFlow() {
		System.out.println("IN File processing flow ");
		return IntegrationFlow.from(ftpInputChannel())
				.handle((payload, headers) -> {
					System.out.println("***************IN File processing flow : " + payload);
					System.out.println("***************IN File processing flow : " + headers);
					return payload; // Important: return the message to continue flow
				})
				.handle(new FileWritingMessageHandler(new File("C:\\Users\\shind\\OneDrive\\Desktop\\FZshared\\files")))
				.get();
	}
}
