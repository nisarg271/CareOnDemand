package controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.LoginUser;

import util.ScheduleGenerationUtil;

@Controller
public class ScheduleGenerationController {

	@RequestMapping("/scheduleInputScreen")
	public String createUserScreen(HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		return "scheduleInputScreen";
	}
	
	@RequestMapping("/generateSchedule")
	public String generateSchedule(HttpServletRequest request, Model model) {
		Map<String, Integer> memberMap = new LinkedHashMap<String, Integer>();
		if(request.getSession().getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		Enumeration<String> paraNames = request.getParameterNames();
	    while (paraNames.hasMoreElements()) {
	    	String name = paraNames.nextElement();
	    	if(name!=null && name.indexOf("name_") >= 0) {
	    		String index = name.replaceAll("name_", "");
	    		memberMap.put(request.getParameter(name), Integer.parseInt(request.getParameter("slots_"+index)));
	    	}
	    }
	    /*System.out.println("IP:" + memberMap);
	    System.out.println("OP:" + ScheduleGenerationUtil.generateSchedule(memberMap));*/
	    model.addAttribute("finalScheduleMap", ScheduleGenerationUtil.generateSchedule(memberMap));
		return "scheduleOutputScreen";
	}
}
