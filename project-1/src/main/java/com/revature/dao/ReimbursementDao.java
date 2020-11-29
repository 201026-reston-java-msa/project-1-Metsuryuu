package com.revature.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.utilities.HibernateUtil;

import jdk.internal.org.jline.utils.Log;

public class ReimbursementDao {
	
	public void insert(Reimbursement reim) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(reim);
		tx.commit();
	}
	
	public void update(Reimbursement reim) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.update(reim);
		tx.commit();
	}
	
	public Reimbursement selectById(int id) {
		
		Session session = HibernateUtil.getSession();
		
		Reimbursement reim = session.get(Reimbursement.class, id);
		
		return reim;
		
	}
	
	public List<Reimbursement> selectPending(int id){
		
		Session session = HibernateUtil.getSession();
		
//		List<Reimbursement> pending = session.createQuery("from reimbursement where status_fk=1 and user_fk_author="+id, Reimbursement.class).list();
		List<Reimbursement> pending = null;
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reimbursement> query = builder.createQuery(Reimbursement.class);
		
		Root<Reimbursement> root = query.from(Reimbursement.class);
		Path<Integer> statusFK = root.get("status");	//make sure it's the variable name, not the column name.
		Path<Integer> userFK = root.get("author");
		
		Predicate statusFKPredicate = builder.equal(statusFK, 1);
		Predicate userFKPredicate = builder.equal(userFK, id);
		
		Predicate predicate = builder.and(statusFKPredicate, userFKPredicate);
		query.select(root).where(predicate);
		
		pending = session.createQuery(query).getResultList();
		
		if(pending.size() == 0) {
			Log.info("There are no pending requests.");
			return null;
		}
		
		return pending;
	}

}
