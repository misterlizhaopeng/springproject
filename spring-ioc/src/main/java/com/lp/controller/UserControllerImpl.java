package com.lp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lp.service.UserService;

@Controller
public class UserControllerImpl {

	@Autowired
	private UserService userService;
	
	
}
