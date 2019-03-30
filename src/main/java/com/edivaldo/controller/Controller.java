package com.edivaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edivaldo.service.TweetsServicesImp;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired
	private TweetsServicesImp tweetsRepository;
	
	@RequestMapping(value="/tweets", method=RequestMethod.GET)
	public String getTweets(){
		//tweetsRepository.getTweets();
		return "OK";
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String getUser(){
		//tweetsRepository.getUser("eu");
		return "OK";
	}
}