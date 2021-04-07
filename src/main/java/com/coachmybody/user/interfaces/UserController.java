package com.coachmybody.user.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class UserController {

	@GetMapping("/users")
	public String getUser() {
		return "안녕";
	}
}
