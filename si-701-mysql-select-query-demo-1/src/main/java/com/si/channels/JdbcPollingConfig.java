package com.si.channels;

import java.time.Duration;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

@Configuration
public class JdbcPollingConfig {

	@Bean
	MessageChannel employeeChannel() {
		return new DirectChannel();
	}


	@Bean
	public MessageSource<?> jdbcPollingAdapter(JdbcTemplate jdbcTemplate) {
		return new JdbcPollingChannelAdapter(jdbcTemplate,
				"SELECT emp_no as empNo, CONCAT(first_name, ' ', last_name) AS name, dept_name AS deptName, salary\r\n"
						+ "FROM employees\r\n" + "WHERE salary > 70000 AND dept_name = 'Engineering';\r\n");
	}
	
	@Bean
	 IntegrationFlow pollingFlow(JdbcTemplate jdbcTemplate) {
	    return IntegrationFlow
	            .from(jdbcPollingAdapter(jdbcTemplate), 
	                  c -> c.poller(Pollers.fixedDelay(10000)))  // 10 seconds delay
	            .channel(employeeChannel())
	            .get();
	}


	@Bean
	@ServiceActivator(inputChannel = "employeeChannel")
	public MessageHandler printService() {
		return message -> {
			for (Map<String, Object> row : (Iterable<Map<String, Object>>) message.getPayload()) {
				System.out.println("Employee ID: " + row.get("empNo") + ", Name: " + row.get("name")
						+ ", Salary: " + row.get("salary")+ ", Department: " + row.get("deptName"));
			}
			System.out.println("\n ================== \n");
		};
	}
}
