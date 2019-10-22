package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.UserService;
import bean.LoginUser;
import bean.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService; 

	@RequestMapping("/createUserScreen")
	public String createUserScreen(HttpServletRequest request, @ModelAttribute("createUser") User user, Model model) {
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		return "createUserScreen";
	}
	
	@RequestMapping("/createUserFunction")
	public String createUserFunction(HttpServletRequest request, @ModelAttribute("createUser") User user, Model model){
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		user.setStatus("active");
		String result = userService.addUser(user);
		if(result!="success"){
			model.addAttribute("message", "Username already in the db");
			return "createUserScreen";
		}
		else{
			model.addAttribute("message", "User creation successful");
		}
		return "createUserConfirmation";
	}
	
	@RequestMapping("/viewUsers")
	public String getAllUsers(HttpServletRequest request, Model model){
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "viewUsersScreen";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, @RequestParam(value = "deleteUserName", required = false) String userName, Model model){
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		List<User> userList = userService.deleteUser(userName);
		model.addAttribute("userList", userList);
		return "viewUsersScreen";
	}
	
	@RequestMapping("/userSignUpScreen")
	public String userSignUpScreen(HttpServletRequest request, @ModelAttribute("userSignUp") User user, Model model) {
		return "userSignUpScreen";
	}
	
	@RequestMapping("/userSignUpFunction")
	public String userSignUpFunction(HttpServletRequest request, @ModelAttribute("userSignUp") User user, Model model) {
		if(user == null || user.getUserName()==null || user.getPassword()==null){
			return "userSignUpScreen";	
		}
		user.setStatus("pending");
		userService.addUser(user);
		return "userSignUpConfirmation";
	}
	
	@RequestMapping("/activateUser")
	public String activateUser(HttpServletRequest request, @RequestParam(value = "activeUserName", required = false) String userName, Model model){
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		List<User> userList = userService.activateUser(userName);
		model.addAttribute("userList", userList);
		return "viewUsersScreen";
	}

}
