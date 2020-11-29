package com.revature;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.Role;
import com.revature.model.Users;
import com.revature.utilities.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		initialValues();
		
		HibernateUtil.closeSession();
		System.out.println("Really done.");

	}

	private static void initialValues() {
//		Role role = new Role(1, "Employee");
//		Role role2 = new Role(2, "Manager");
//		Users u1 = new Users("test1", "1test", "Tester", "Oneson", "test1@gmail.com", role);
//		Users u2 = new Users("test2", "2test", "Testy", "Twoman", "test2@gmail.com", role2);
//		
//		UserDao ud = new UserDao();
//		
//		ud.insert(u1);
//		ud.insert(u2);
		
		Users u1 = new Users();
		u1.setUserId(4);	//TODO left off here, still having issues with duplicate user.
		
		ReimbursementStatus rs = new ReimbursementStatus(1, "Pending");
		ReimbursementType rt = new ReimbursementType(3, "Food");
		
		Reimbursement r1 = new Reimbursement(50.55, "2020-11-15 15:30:14.332", "I had to purchase food while en route.", u1, rs, rt);
		r1.setReimbursmentId(13);
		
		ReimbursementDao rd = new ReimbursementDao();
		
//		rd.insert(r1);
		
		rd.update(r1);
		
		u1 = new Users();
		
		System.out.println("Finished");
		
	}
}
