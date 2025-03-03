package com.rohit.securityApp.SecurityApplication;

import com.rohit.securityApp.SecurityApplication.entities.User;
import com.rohit.securityApp.SecurityApplication.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L, "rohit@gmail.com", "1234");

		String token = jwtService.generateToken(user);

		log.info(token);

		Long id = jwtService.getUserIdFromToken(token);

		log.info(id.toString());
	}


}
