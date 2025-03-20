package com.si.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.demo.model.PersonInfo;
import com.si.demo.service.IPersonInfoService;

@RestController
@RequestMapping("/api")
public class PersonInfoController {
	@Autowired
	private IPersonInfoService personService;

	@PostMapping("/receive")
	public ResponseEntity<String> receiveData(@RequestBody PersonInfo person) {

		String responseMessage = personService.addPersonInfo(person);
		return ResponseEntity.ok(responseMessage);
	}
}