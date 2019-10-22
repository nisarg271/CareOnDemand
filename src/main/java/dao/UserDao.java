package dao;

import java.util.List;

import bean.User;

public interface UserDao {
	
	public String addUser(User user);
	public List<User> deleteUser(String userName);
	public List<User> getAllUsers();
	public User getUser(String userName);
	public List<User> activateUser(String userName); 

}
