package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.User;
import dao.UserDao;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public String addUser(User user) {
		return userDao.addUser(user);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	public List<User> deleteUser(String userName) {
		return userDao.deleteUser(userName);
	}
	
	@Override
	public User getUser(String userName) {
		return userDao.getUser(userName);
	}
	
	@Override
	public List<User> activateUser(String userName) {
		return userDao.activateUser(userName);
	}
}
