package com.nagarro.dao_classes;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.nagarro.pojo.UserDetails;


@SuppressWarnings("all")
public class UserDAO {
	
	private static SessionFactory sessionFactory = getSessionFactory();
	
	private static SessionFactory getSessionFactory() 
	{

		Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		
		SessionFactory sf = con.buildSessionFactory(reg);
			
		return sf;

	}
	
	public boolean validateUser(UserDetails inpuDetails) {
		
		UserDetails user = null;
		Transaction transaction = null;
		
		try {
			
			Session session = sessionFactory.openSession();
			
			transaction = session.beginTransaction();
			
			Criteria cr = session.createCriteria(UserDetails.class);
			
			cr.add(Restrictions.eq("userName", inpuDetails.getUserName()));
			cr.add(Restrictions.eq("password", inpuDetails.getPassword()));
			
			Object result = cr.uniqueResult();
			
			transaction.commit();
			
			if(result != null) {
				return true;
			}
		} catch (HibernateException e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}

