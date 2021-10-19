package com.carrental.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.carrental.entities.Car;
import com.carrental.entities.Order;
import com.carrental.repositories.CarRepository;
import com.carrental.repositories.OrderRepository;
import com.carrental.utils.UserDetail;

/**
 * this controller responsible for access to pages
 */
@Controller
@Transactional
@SessionAttributes({ "userId", "role" })
public class AccessController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CarRepository carRepository;

	@RequestMapping(value = "/403")
	public String acessDenied() {
		return "accessDenied";
	}

	@RequestMapping(value = { "/home", "/" })
	public String showHomePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/login")
	public String goSignIn() {
		// orderRepository.getUsersByOrderCount();
		return "loginPage";
	}

	@RequestMapping(value = "/logOut")
	public String logOut(ModelMap model) {

		model.remove("userId");
		model.remove("role");
		return "redirect:/home";
	}

	@RequestMapping(value = "/registration")
	public String goSignUp() {
		return "registration";
	}	
	

	@RequestMapping(value = "/adminHome")
	public String goAdminHome(ModelMap modelMap) {

		return "adminHome";
	}
	

	@RequestMapping(value = "/adminCars")
	public String goAdminCars(Model model, ModelMap modelMap) {

		List<Car> cars = carRepository.getAdminCars();
		model.addAttribute("CARS_LIST", cars);
		return "adminCars";
	}
	

	@RequestMapping(value = "/adminOrders")
	public String goAdminOrders(ModelMap modelMap, Model model) {

		List<Order> orders = orderRepository.getOrders();
		model.addAttribute("listOrders", orders);
		return "adminOrders";
	}
	

	@RequestMapping(value = "/userView")
	public String userViewOrders(Model model, Principal principal) {

		UserDetail loggedInUser = (UserDetail) ((Authentication) principal).getPrincipal();
		List<Order> orders = orderRepository.getOrdersByUser(loggedInUser.getUserId());
		model.addAttribute("listOrders", orders);
		if(orders.size()>0)
		{
			Order last = orders.get(orders.size() - 1);		
			if(!last.getStatus().equalsIgnoreCase("Car Returned/Available"))
				carRepository.updateCarStatus(last.getModel(), "0");
		}		
		
		return "userView";
	}

	@RequestMapping(value = "/userHome")
	public String userHome(ModelMap model, Principal principal) {
		return "userHome";
	}
	

	@RequestMapping(value = "/userNewOrder")
	public String userMakeNewOrder(ModelMap modelMap, Model model) {

		List<Car> cars = carRepository.getCars();
		model.addAttribute("CARS_LIST", cars);
		return "userNewOrder";
	}
}
