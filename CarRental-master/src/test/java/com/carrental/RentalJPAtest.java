package com.carrental;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carrental.entities.User;
import com.carrental.repositories.CarRepository;
import com.carrental.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalJPAtest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;

	@Test
	public void addAndDeleteUserTest() throws Exception {
		// Creating an User object and saving into database
		User testUser = new User((long) 12345,"Adam", "Adam123", "user", "Adam@mail.com");
		userRepository.addUser(testUser);

		// Retrieving and checking User entity
		User retrievedUser = userRepository.getUserByName(testUser.getName());
		assertEquals(testUser.getName(), retrievedUser.getName());
		assertEquals(null, userRepository.getUserByName(testUser.getName()));
	}

}
