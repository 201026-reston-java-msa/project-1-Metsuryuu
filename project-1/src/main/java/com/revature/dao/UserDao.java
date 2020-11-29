package com.revature.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Users;
import com.revature.utilities.HibernateUtil;

public class UserDao {
	
	public void insert(Users user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();
	}
	
	public void update(Users user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.update(user);
		tx.commit();
	}

}
