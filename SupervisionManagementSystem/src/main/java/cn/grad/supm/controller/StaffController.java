package cn.grad.supm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.grad.supm.domain.Power;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.service.PowerService;
import cn.grad.supm.service.StaffService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class StaffController {

	@Autowired
	private PowerService powerService;
	@Autowired
	private StaffService staffService;
	
	
	//====================职权管理============================
	@RequestMapping("addPower")
	public String addPower() {
		return "addPower";
	}
	
	@RequestMapping("queryPower")
	public String queryPower(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Power power,HttpSession session) {
		Power powerSession;
		PageModel pageModel=new PageModel();
		List<Power> powerList = null;
		
		System.out.println("pageIndex="+pageIndex+", power="+power);
		
		powerSession=(Power) session.getAttribute("powerSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryPower页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("powerSession", power);
			powerList=powerService.findPowerList(power, pageModel);
		}
		//非首次进入queryPower页面且传入页码
		else if(powerSession!=null && pageIndex>0) {
			powerList=powerService.findPowerList(powerSession, pageModel);
		}
		model.addAttribute("powerList", powerList);
		model.addAttribute("pageModel", pageModel);
		return "queryPower";
	}
	
	
	@RequestMapping("insertPower")
	@ResponseBody
	public Object insertPower(@RequestBody Power power) {
		try{ powerService.addPower(power); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updatePower")
	@ResponseBody
	public Object updatePower(@RequestBody Power power) {
		try{ powerService.modifyPower(power); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deletePower")
	public String deletePower(int[] idList) {
		for(int id:idList) { powerService.removePower(id); }
		return "redirect:/queryPower";
	}
	
	
	//=====================教职工管理============================
	@RequestMapping("addStaff")
	public String addStaff(Model model) {
		model.addAttribute("powerList", powerService.findAllPower());
		model.addAttribute("departmentList", Utils.getDepartmentList());
		return "addStaff";
	}
	
	
	@ModelAttribute
	public Staff staffModel(@ModelAttribute Staff staff,@RequestParam(defaultValue = "0",value="powerId")int id) {
		if(id!=0) { staff.setPower(new Power(id,"","")); }
		return staff;
	}
	
	
	@RequestMapping("queryStaff")
	public String queryStaff(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Staff staff,HttpSession session) {
		Staff staffSession;
		PageModel pageModel=new PageModel();
		List<Staff> staffList = null;
		
		System.out.println("pageIndex="+pageIndex+", staff="+staff);
		
		staffSession=(Staff) session.getAttribute("staffSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryStaff页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("staffSession", staff);
			staffList=staffService.findStaffList(staff, pageModel);
		}
		//非首次进入queryStaff页面且传入页码
		else if(staffSession!=null && pageIndex>0) {
			staffList=staffService.findStaffList(staffSession, pageModel);
		}
		model.addAttribute("staffList", staffList);
		model.addAttribute("powerList", powerService.findAllPower());
		model.addAttribute("departmentList", Utils.getDepartmentList());
		model.addAttribute("pageModel", pageModel);
		return "queryStaff";
	}
	
	
	@RequestMapping("insertStaff")
	@ResponseBody
	public Object insertStaff(@RequestBody Staff staff) {
		try{ staffService.addStaff(staff); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateStaff")
	@ResponseBody
	public Object updateStaff(@RequestBody Staff staff) {
		try{ staffService.modifyStaff(staff); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteStaff")
	public String deleteStaff(String[] idList) {
		for(String id:idList) { staffService.removeStaff(id); }
		return "redirect:/queryStaff";
	}
	
}
