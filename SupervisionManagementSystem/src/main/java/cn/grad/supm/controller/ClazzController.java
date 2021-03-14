package cn.grad.supm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.service.ClazzService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class ClazzController {

	@Autowired
	private ClazzService clazzService; 
	
	@RequestMapping("addClass")
	public String addClass(Model model) {
		model.addAttribute("departmentList",Utils.getDepartmentList());
		return "addClass";
	}
	
	@RequestMapping("queryClass")
	public String queryClazz(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Clazz clazz,HttpSession session) {
		Clazz clazzSession;
		PageModel pageModel=new PageModel();
		List<Clazz> clazzList = null;
		
		System.out.println("pageIndex="+pageIndex+", clazz="+clazz);
		
		clazzSession=(Clazz) session.getAttribute("clazzSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryClass页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("clazzSession", clazz);
			clazzList=clazzService.findClazzList(clazz, pageModel);
		}
		//非首次进入queryClass页面且传入页码
		else if(clazzSession!=null && pageIndex>0) {
			clazzList=clazzService.findClazzList(clazzSession, pageModel);
		}
		
		model.addAttribute("clazzList", clazzList);
		model.addAttribute("departmentList",Utils.getDepartmentList());
		model.addAttribute("pageModel", pageModel);
		return "queryClass";
	}
	
	@RequestMapping("insertClass")
	public String insertClazz(Model model,@ModelAttribute Clazz clazz) {
		try{ clazzService.addClazz(clazz); }
		catch (Exception e) { 
			model.addAttribute("errorMessage",e.getMessage());
			return "forward:/addClass";
		}
		return "redirect:/queryClass";
	}
	
	
	@RequestMapping("updateClass")
	public String updateClazz(@ModelAttribute Clazz clazz) {
		clazzService.modifyClazz(clazz);
		return "redirect:/queryClass";
	}
	
	
	@RequestMapping("deleteClass")
	public String deleteClazz(String[] idList) {
		for(String id:idList) { clazzService.removeClazz(id); }
		return "redirect:/queryClass";
	}
	
}
