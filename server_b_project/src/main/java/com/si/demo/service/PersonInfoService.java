package com.si.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.demo.entites.PersonInfoRepository;
import com.si.demo.entites.PersonInformation;
import com.si.demo.model.PersonInfo;

@Service
public class PersonInfoService implements IPersonInfoService {
	@Autowired
	private PersonInfoRepository personRepository;

	@Override
	public String addPersonInfo(PersonInfo person) {
		PersonInformation p = new PersonInformation(person.getFirstName(), person.getLastName(), person.getAge());
		PersonInformation responseEntity = personRepository.save(p);
		return "Data Saved Successfully With Id : " + responseEntity.getPersonId();
	}

}
