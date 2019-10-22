package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.LoginUser;

@Controller
public class LoginController {
	
	public static Map<String, Integer> dataMap= new HashMap<String, Integer>();

	@RequestMapping("/admin")
	public String loginScreen(@ModelAttribute LoginUser loginUser) {
		return "loginScreen";
	}
	
	@RequestMapping("/loginFunction")
	public String loginFunction(HttpServletRequest request, @Valid @ModelAttribute LoginUser loginUser, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "loginScreen";
        }
		model.addAttribute("message", "Welcome " + loginUser.getUserName().toUpperCase() + "!");
		HttpSession session = request.getSession();
		session.setAttribute("userName", loginUser.getUserName());
		
		return "hello";
	}
	
	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request, @ModelAttribute LoginUser loginUser, Model model) {
		request.getSession().invalidate();
		model.addAttribute("message", "You have successfully signed out.");
		return "loginScreen";
	}
	
}
