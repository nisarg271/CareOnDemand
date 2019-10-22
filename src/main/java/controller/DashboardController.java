package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import util.StaticDataUtil;
import bean.LoginUser;

@Controller
public class DashboardController {

	@RequestMapping("/dashboardScreen")
	public String createUserScreen(HttpServletRequest request, Model model, @ModelAttribute LoginUser loginUser) {
		if(request.getSession().getAttribute("userName")==null){
			return "loginScreen";
		}
		if(request.getParameter("resetFlag")!=null && request.getParameter("resetFlag").equals("Y")) {
			StaticDataUtil.dataMap.clear();
			StaticDataUtil.voters.clear();
		}
		model.addAttribute("data", StaticDataUtil.dataMap);
		model.addAttribute("voters", StaticDataUtil.voters);
		return "dashboardScreen";
	}
}
