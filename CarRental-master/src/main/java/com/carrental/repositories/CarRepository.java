package com.carrental.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carrental.entities.Car;

@Repository
@Transactional
public class CarRepository {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * this method retrieves certain car from database. Car is chosen by input id
	 * 
	 * @param  Car id
	 * @return Car entity
	 */
	public Car getCar(Long id) {

		Session session = this.sessionFactory.getCurrentSession();
		return session.find(Car.class, id);
	}
	
	/**
	 * this method retrieves all cars from database
	 * 
	 * @return List of Car entities
	 */
	public List<Car> getCars() {
		List<Car> carList = null;

		Session session = this.sessionFactory.getCurrentSession();
		String sql = "from " + Car.class.getName() + " where status = '1'";
		Query<Car> query = session.createQuery(sql, Car.class);
		carList = query.getResultList();

		return carList;
	}

	/**
	 * this method retrieves all cars from database for the Admin irrespective of the status
	 * 
	 * @return List of Car entities
	 */
	public List<Car> getAdminCars() {
		List<Car> carList = null;

		Session session = this.sessionFactory.getCurrentSession();
		String sql = "from " + Car.class.getName();
		Query<Car> query = session.createQuery(sql, Car.class);
		carList = query.getResultList();

		return carList;
	}	
	
	public void updateCarStatus(String model, String status) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaUpdate<Car> update = cb.createCriteriaUpdate(Car.class);

		Root<Car> e = update.from(Car.class);

		update.set(Car.ATTRIBUTE_STATUS, status);
		update.where(cb.equal(e.get(Car.ATTRIBUTE_MODEL), model));

		session.createQuery(update).executeUpdate();

	}

	/**
	 * insert new car into the database
	 * 
	 * @param car entity that has to be added
	 */
	public void addCar(Car car) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(car);
	}

	/**
	 * deletes car from database 
	 * 
	 * @param id of car that has to be deleted
	 */
	public void deleteCar(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Car car = session.get(Car.class, id);
		session.delete(car);
	}
}
