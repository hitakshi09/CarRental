package com.carrental.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.carrental.entities.User;
import com.carrental.repositories.UserRepository;

@Controller
@Transactional
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * adds new user to the database, but only if the user doesn't already exist.
	 * 
	 * @param model     Spring ModelMap class used to put and get parameters from
	 *                  session
	 * @param login     received from request
	 * @param password1 user's password received from request
	 * @param email     user's email received from request
	 * @return redirection to appropriate page
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(HttpServletRequest request, ModelMap model, @RequestParam(required = false) String login,
			@RequestParam(required = false) String password1, @RequestParam String email) {

		if (login != null && password1 != null) {
			User user = userRepository.getUserByName(login);
			if (user != null) {
				model.put("msg", "such user already exists");
				return "redirect:/registrations";
			}
			// adding new user into database and calling auto authentication method
			userRepository.addUser(new User((long) 4, login, password1, "User", email));
			authWithHttpServletRequest(request, login, password1);

			return "redirect:/userHome";
		} else {
			return "redirect:/login";
		}
	}	
	

	/**
	 * This method is used for auto authentication after successful registration
	 * process
	 * 
	 * @param request  incoming request
	 * @param username user's name
	 * @param password user's password
	 */
	public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
		try {
			request.login(username, password);
		} catch (ServletException e) {
			System.out.println("Wasn't able to autologin successfully " + e);
		}
	}
}
