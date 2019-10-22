package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	protected Session getHibernateSession(){
		Session session = null;
		try{
			session = this.sessionFactory.openSession();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}
	
	protected void closeHibernateSession(Session session){
		if(session!=null){
			session.flush();
			session.close();
		}
	}

}
