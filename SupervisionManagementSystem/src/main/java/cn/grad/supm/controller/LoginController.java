package cn.grad.supm.controller;

import javax.servlet.http.HttpSession;

import static cn.grad.supm.utils.Constants.ADMIN;
import static cn.grad.supm.utils.Constants.SUPERVISOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.grad.supm.domain.Admin;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.service.AdminService;
import cn.grad.supm.service.SupervisorService;

@Controller
public class LoginController {
	@Autowired
	private SupervisorService supervisorService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("loginForm")
	public String loginForm(){
		return "loginForm";
	}
	
	@RequestMapping("login")
	public String login(
			Model model,
			HttpSession session,
			@RequestParam("id")String id,
			@RequestParam("password")String password,
			@RequestParam("identity")String identity){
		
		if(SUPERVISOR.equals(identity)) {
			
			Supervisor supervisor=supervisorService.findSupervisor(id);
			if(supervisor==null || !password.equals(supervisor.getPassword())) {
				model.addAttribute("errorMessage", "教职工号或密码错误");
				return "loginForm";
			}
			session.setAttribute("staff", supervisor);
			return "main";
			
		}else if(ADMIN.equals(identity)) {
			
			Admin admin=adminService.findAdmin(id);
			if(admin==null || !password.equals(admin.getPassword())) {
				model.addAttribute("errorMessage", "教职工号或密码错误");
				return "loginForm";
			}
			session.setAttribute("staff", admin);
			return "main";
			
		}
		return "loginForm";
	}
	
	
	
	@RequestMapping("loginout")
	public String loginout(HttpSession session){
		session.removeAttribute("staff");
		return "loginForm";
	}
	
}
