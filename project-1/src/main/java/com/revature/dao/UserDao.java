package com.revature.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Users;
import com.revature.utilities.HibernateUtil;

public class UserDao {
	
	public static Logger log = Logger.getLogger(UserDao.class);
	
	//Insert a new user.
	public void insert(Users user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();
	}
	
	//Update an existing user. Just need the ID, everything else will be updated.
	public void update(Users user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.update(user);
		tx.commit();
	}
	
	//Get users by their id.
	public Users selectById(int id) {
		
		Session session = HibernateUtil.getSession();
		Users user = session.get(Users.class, id);
		
		return user;
	}
	
	//Get all users.
	public List<Users> selectAll(){
		
		List<Users> userList = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		
		userList = session.createQuery("from users", Users.class).list();
		
		return userList;
	}
	
	//Login method. Get the user by their username, then compare password hashes.
	public Users login(String username, String password) {
		
		Session session = HibernateUtil.getSession();
		Users user = session.get(Users.class, username);
		
		//compare the password hashes, they should be equal.
		if(user.getPassword().equals(password)) {
			return user;
		}else {
			//If they aren't equal, either the username or password was incorrect.
			log.warn("Username or password incorrect.");
			return null;
		}
	}

}
