package com.gl.SpringBootBookStore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.SpringBootBookStore.entity.User;
import com.gl.SpringBootBookStore.service.BookService;
import com.gl.SpringBootBookStore.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/register")
	public String register(User user, HttpSession session) {
		userService.a
	}
}
