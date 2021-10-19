package com.carrental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.carrental.entities.Car;
import com.carrental.repositories.CarRepository;

@Controller
@Transactional
@SessionAttributes("role")
public class CarController {

	@Autowired
	private CarRepository carRepository;

	/**
	 * on request connects to database and pulls out list of cars . Retrieved data
	 * is exposed via spring model
	 * 
	 * @param model    spring Model that used to input parameters for client
	 *                 response
	 * @param modelMap required to check user access modifier
	 * @return spring UI model with List of cars in it
	 */
	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public Model getCars(Model model, ModelMap modelMap) {

		model.addAttribute("CARS_LIST", carRepository.getCars());
		return model;
	}		

	/**
	 * adds new car into database by calling method from hibernate data access
	 * repository and using parameters received from request
	 * 
	 * @param carName  model of car entity received from request
	 * @param make     of car entity received from request
	 * @param carClass of car entity received from request
	 * @param carCost  of car entity received from request
	 * @param modelMap spring ModelMap class
	 * @return redirect link to adminCars page or to home page if role doesn't
	 *         match "admin"
	 */
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	public String addCar(@RequestParam(required = false) String carName, @RequestParam(required = false) String make,
			@RequestParam(required = false) String carClass,
			@RequestParam(defaultValue = "0", required = false) int carCost,
			@RequestParam(defaultValue = "1", required = false) String carStatus, ModelMap modelMap) {

		if ((carName != null) && (make != null) && (carClass != null) && (carCost != 0)) {
			Car car = new Car(carName, make, carClass, carCost, carStatus);
			carRepository.addCar(car);
		}
		return "redirect:/adminCars";
	}

	/**
	 * deletes defined car , only admin has access to delete a car 
	 * 
	 * @param carId    of car entity received from request
	 * @param modelMap spring ModelMap class
	 * @return redirect link to adminCars page or to home page if role doesn't
	 *         match "admin"
	 */
	@RequestMapping(value = "/cars/delete/{carId}")
	public String deleteCar(@PathVariable Long carId, ModelMap modelMap) {

		carRepository.deleteCar(carId);
		return "redirect:/adminCars";
	}	
	
}
