package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import util.StaticDataUtil;
import bean.LoginUser;
import bean.Vote;

@Controller
public class VoteController {

	@RequestMapping("/voteScreen")
	public String voteScreen(HttpServletRequest request, @ModelAttribute Vote vote, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		if(StaticDataUtil.voters.contains(session.getAttribute("userName").toString())){
			model.addAttribute("message", "You have already voted in this session. Please wait while the admin initiates a new session");
			return "voteConfirmation";
		}
		else{
			return "voteScreen";
		}
		
	}
	
	@RequestMapping("/voteFunction")
	public String voteFunction(HttpServletRequest request, @ModelAttribute Vote vote, Model model){
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null){
			model.addAttribute("loginUser", new LoginUser());
			return "loginScreen";
		}
		if(StaticDataUtil.dataMap.containsKey(vote.getPoints()))
			StaticDataUtil.dataMap.put(vote.getPoints(), StaticDataUtil.dataMap.get(vote.getPoints()) + 1);
		else
			StaticDataUtil.dataMap.put(vote.getPoints(), 1);
		StaticDataUtil.voters.add(session.getAttribute("userName").toString());
		return "voteConfirmation";
	}
}
