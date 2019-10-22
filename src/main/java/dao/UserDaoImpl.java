package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;

import bean.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	
	@Transactional
	@Override
	public String addUser(User user) {
		Session session = null;
		String message = "success";
		try{
			session = getHibernateSession();
			System.out.println("Saving User: "+ user.getUserName()+"@"+user.getPassword());
			session.save(user);
		}
		catch(ConstraintViolationException e){
			message = "duplicate";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeHibernateSession(session);
		}
		return message;
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = null;
		List<User> usersList = null;
		try{
			session = getHibernateSession();
			usersList = session.createQuery("from User where userName != 'nisarg' order by status, username").list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeHibernateSession(session);
		}
		return usersList;
	}
	
	@Override
	public List<User> deleteUser(String userName) {
		Session session = null;
		List<User> userList = null;
		try{
			session = getHibernateSession();
			session.createQuery("delete from User where userName = '"+ userName +"'").executeUpdate();
			userList = getAllUsers();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeHibernateSession(session);
		}
		return userList;
	}
	
	@Override
	public User getUser(String userName) {
		Session session = null;
		User user = null;
		try{
			session = getHibernateSession();
			Criteria cr =  session.createCriteria(User.class).add(Restrictions.eq("status", "active")).add(Restrictions.eq("userName", userName));
			user = (User) cr.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeHibernateSession(session);
		}
		return user;
	}
	
	@Override
	public List<User> activateUser(String userName) {
		Session session = null;
		List<User> userList = null;
		try{
			session = getHibernateSession();
			session.createQuery("update User set status = 'active' where userName = '"+ userName +"'").executeUpdate();
			userList = getAllUsers();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeHibernateSession(session);
		}
		return userList;
	}
	
}
