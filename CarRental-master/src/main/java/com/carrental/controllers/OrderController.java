package com.carrental.controllers;

import java.security.Principal;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.carrental.entities.Order;
import com.carrental.repositories.CarRepository;
import com.carrental.repositories.OrderRepository;
import com.carrental.utils.UserDetail;

@Controller
@Transactional
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CarRepository carRepository;

	/**
	 * this method adds new order made on FE into database by calling hibernate data
	 * access repository,
	 * 
	 * @param model     spring ModelMap is required to check user access modifier
	 * @param carId     array of selected cars
	 * @param startDate beginning of the order
	 * @param endDate   and of the order
	 * @return appropriate redirect link
	 */
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public String addOrder(Principal principal, @RequestParam(required = false) Long carId[],
			@RequestParam(required = true) Date startDate,
			@RequestParam(required = true) Date endDate, @RequestParam(required = false) String model) {

		UserDetail loginedUser = (UserDetail) ((Authentication) principal).getPrincipal();
		if (carId != null) {
			List<Long> carsId = new ArrayList<Long>();
			for (Long long1 : carId) {
				carsId.add(long1);
			}

			Order order = new Order();
			order.setStart_date(startDate);
			order.setEnd_date(endDate);
			order.setStatus("Car Rented");
			order.setUserId(loginedUser.getUserId());

			//Start date and End date should be at least 1 day apart
			long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
			if (days >= 1) {
				long bill = 0;

				// calculation the price by extracting car entities from database by Id
				for (Long car : carId) {
					bill = bill + (carRepository.getCar(car).getCost()) * days;
					model = carRepository.getCar(car).getModel();
				}				
				order.setBill((int) bill);
				order.setModel(model);

				orderRepository.addOrder(order, carsId);
			} else {
				return "redirect:/userNewOrder";
			}

			return "redirect:/userView";
		} else {
			return "redirect:/userNewOrder";
		}
	}

	/**
	 * this method retrieves orders based on user role from data base
	 * 
	 * @param modelMap required to check user access modifier
	 * @param model    spring UI model, used to retrieve data into FE
	 * @return spring Model
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public Model getOrders(Principal principal, Model model) {

		UserDetail loggedinUser = (UserDetail) ((Authentication) principal).getPrincipal();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) loggedinUser.getAuthorities();
		String role = authorities.get(0).getAuthority();
		if ("ROLE_USER".equals(role)) {
			model.addAttribute("listOrders", orderRepository.getOrdersByUser(loggedinUser.getUserId()));
			return model;
		} else if ("ROLE_ADMIN".equals(role)) {
			model.addAttribute("listOrders", orderRepository.getOrders());
			return model;
		} else {
			return model;
		}
	}

	/**
	 * Retrieves specified order
	 * 
	 * @param model    spring UI model, used to retrieve data into FE
	 * @param modelMap required to check user access modifier
	 * @param orderId  path
	 * @return redirect link
	 */
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public String getOrder(Model model, Principal principal, @PathVariable Long orderId) {

		UserDetail loggedinUser = (UserDetail) ((Authentication) principal).getPrincipal();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) loggedinUser.getAuthorities();
		String role = authorities.get(0).getAuthority();
		if ("ROLE_USER".equals(role)) {
			model.addAttribute("Order", orderRepository.getOrder(orderId));
			return "userViewOrder";
		} else if ("ROLE_ADMIN".equals(role)) {
			model.addAttribute("Order", orderRepository.getOrder(orderId));
			return "adminViewOrder";
		} else {
			return "redirect:/home";
		}
	}

	/**
	 * this method update changes of order status. Only admin is allowed to change the
	 * status
	 * 
	 * @param modelMap  required to check user access modifier
	 * @param orderId   path variable which specify order
	 * @param setStatus status that has to be applied
	 * @return redirect link
	 */
	@RequestMapping(value = "/orders/change/{orderId}")
	public String setOrderStatus(ModelMap modelMap, @PathVariable Long orderId,
			@RequestParam(required = false) String setStatus, @RequestParam(required = false) String model) {

		if (setStatus != null) {
			if(setStatus.equalsIgnoreCase("Car Rented"))
			{
				carRepository.updateCarStatus(model, "0");
			}	
			else if(setStatus.equalsIgnoreCase("Car Returned/Available"))
			{
				carRepository.updateCarStatus(model, "1");
			}			
			orderRepository.updateOrderStatus(orderId, setStatus);			
		}
		return "redirect:/adminOrders";
	}
}
