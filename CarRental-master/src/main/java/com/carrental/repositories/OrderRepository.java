package com.carrental.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carrental.entities.Order;
import com.carrental.entities.OrderItem;

@Repository
@Transactional
public class OrderRepository {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * adds new order record as well as attached orderItem records into the
	 * database.
	 * 
	 * @param order  entity that has to be saved in database
	 * @param carsId list of cars which were picked in the order
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addOrder(Order order, List<Long> carsId) {

		Session session = this.sessionFactory.getCurrentSession();
		Long orderId = (Long) session.save(order);

		for (Long carId : carsId) {
			OrderItem item = new OrderItem(orderId, carId);
			session.save(item);
		}
	}

	/**
	 * this method updates status of certain order
	 * 
	 * @param id     identifier of entity to update
	 * @param status entity's parameter that has to be updated
	 */
	@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
	public void updateOrderStatus(Long id, String status) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = session.get(Order.class, id);
		order.setStatus(status);
	}

	/**
	 * this method retrieves order entity from the database
	 * 
	 * @param id identifier of entity to retrieve
	 * @return Order entity
	 */
	public Order getOrder(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Order.class, id);

	}

	/**
	 * retrieves all orders from database
	 * 
	 * @return List of Order entities
	 */
	public List<Order> getOrders() {
		List<Order> orderList = null;

		Session session = this.sessionFactory.getCurrentSession();
		String sql = "from " + Order.class.getName();
		Query<Order> query = session.createQuery(sql, Order.class);
		orderList = query.getResultList();

		return orderList;
	}

	/**
	 * pulls out records from database specific to a user
	 * 
	 * @param user Id 
	 * @return List of Order entities
	 */
	public List<Order> getOrdersByUser(Long userId) {
		List<Order> orderList = null;

		String sql = "SELECT e FROM " + Order.class.getName() + " e WHERE e.userId =:" + Order.ATTRIBUTE_USER_ID;
		Session session = this.sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery(sql, Order.class);
		query.setParameter(Order.ATTRIBUTE_USER_ID, userId);
		orderList = query.getResultList();

		return orderList;
	}
	
}
