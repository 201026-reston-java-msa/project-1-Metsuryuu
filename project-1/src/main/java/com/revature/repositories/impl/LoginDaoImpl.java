package com.revature.repositories.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.model.Users;
import com.revature.repositories.LoginDao;
import com.revature.util.HibernateUtil;

public class LoginDaoImpl implements LoginDao {
	
	private static Logger log = Logger.getLogger(LoginDaoImpl.class);

	@Override
	public boolean validUser(Users user) {
		
		Session session = HibernateUtil.getSession();
		Users user_check = session.get(Users.class, user.getUsername());
		
		if(user_check.getPassword() == user.getPassword()) {
			user.setEmail(user_check.getEmail());
			user.setFirstName(user_check.getFirstName());
			user.setLastName(user_check.getLastName());
			user.setRole(user_check.getRole());
			user.setUserId(user_check.getUserId());
			
			return true;
		}
		
		return false;
	}

}
