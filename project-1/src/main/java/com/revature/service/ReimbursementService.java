package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;

public class ReimbursementService {
	
	private static Logger log = Logger.getLogger(ReimbursementService.class);
	private ReimbursementDao rd = new ReimbursementDao();
	
	public boolean insert(Reimbursement reim) {
		try {
			
			ReimbursementStatus status = new ReimbursementStatus(1, "Pending");
			reim.setStatus(status);
			
			rd.insert(reim);
			
			//Return true provided it doesn't fail.
			return true;
		}catch(HibernateException e) {
			log.warn("Insert request failed.");
			//return false with failure.
			return false;
		}
	}
	
	public List<Reimbursement> getAll(int status_id, int user_id){
		
		List<Reimbursement> reimList = new ArrayList<>();
		
		reimList = rd.selectRequests(status_id, user_id);
		
		return reimList; 
	}

}
