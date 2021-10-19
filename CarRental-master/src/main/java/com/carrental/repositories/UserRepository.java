package com.carrental.repositories;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.carrental.entities.User;

@Repository
@Transactional
public class UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * checks if user exists is in database 
	 * 
	 * @param login    user's login 
	 * @param password user's password
	 * @return User entity
	 */
	public User validateUser(String login, String password) {
		User user = null;

		try {
			String sql = "SELECT e FROM " + User.class.getName() + " e WHERE e.name =:name AND e.password =:password";

			Session session = this.sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(sql, User.class);
			query.setParameter(User.ATTRIBUTE_NAME, login);
			query.setParameter(User.ATTRIBUTE_PASSWORD, password);
			user = (User) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

		return user;
	}

	/**
	 * retrieves user entity from database if it matches the input login otherwise returns null;
	 * 
	 * @param user's login name
	 * @return User entity
	 */
	public User getUserByName(String login) {
		try {
			String sql = "SELECT e FROM " + User.class.getName() + " e WHERE e.name=:" + User.ATTRIBUTE_NAME;

			Session session = this.sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(sql, User.class);
			query.setParameter(User.ATTRIBUTE_NAME, login);
			return (User) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * retrieves user from database where id matches the input parameter.
	 * 
	 * @param user id 
	 * @return User entity
	 */
	public User getUser(Long id) {
		User user = null;
		Session session = this.sessionFactory.getCurrentSession();
		user = session.find(User.class, id);
		return user;
	}	


	/**
	 * adds new user entity into the database
	 * 
	 * @param user to add
	 */
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
	}
	

}
