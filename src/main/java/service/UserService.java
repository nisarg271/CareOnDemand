package service;

import java.util.List;

import bean.User;

public interface UserService {

	public String addUser(User user);
	public List<User> deleteUser(String userName);
	public List<User> getAllUsers();
	public User getUser(String userName);
	public List<User> activateUser(String userName);
}
