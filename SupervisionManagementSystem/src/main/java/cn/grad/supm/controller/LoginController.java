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
import cn.grad.supm.domain.Staff;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.service.AdminService;
import cn.grad.supm.service.StaffService;
import cn.grad.supm.service.SupervisorService;

@Controller
public class LoginController {
	@Autowired
	private StaffService staffService;

	@RequestMapping("loginForm")
	public String loginForm(){
		return "loginForm";
	}
	
	@RequestMapping("login")
	public String login(
		Model model,
		HttpSession session,
		@RequestParam("id")String id,
		@RequestParam("password")String password){
		
		Staff staff=staffService.findStaff(id);
		if(staff==null || !password.equals(staff.getPassword())) {
			model.addAttribute("errorMessage", "教职工号或密码错误");
			return "loginForm";
		}
		session.setAttribute("staff", staff);
		return "main";
	}
	
	@RequestMapping("loginout")
	public String loginout(HttpSession session){
		session.removeAttribute("staff");
		return "loginForm";
	}
	
}
