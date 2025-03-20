package com.si.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.demo.gateway.MessageGateway;
import com.si.demo.model.PersonInfo;
import com.si.demo.response.CustomResponse;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageGateway messageGateway;

	@PostMapping("/send")
	public ResponseEntity<?> sendDataToMessagingSystem(@RequestBody PersonInfo person) {
		String serviceResponse = messageGateway.sentToChannel(person);
		return ResponseEntity.ok(new CustomResponse(HttpStatus.OK, HttpStatus.OK.value(),
				"Successfully Got Response from Service B : " + serviceResponse));
	}

}
