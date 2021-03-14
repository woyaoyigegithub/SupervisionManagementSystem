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

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.service.ClassroomService;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;
	
	
	@RequestMapping("addClassroom")
	public String addClassroom() {
		return "addClassroom";
	}
	
	
	
	@RequestMapping("queryClassroom")
	public String queryClassroom(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Classroom classroom,HttpSession session) {
		Classroom classroomSession;
		PageModel pageModel=new PageModel();
		List<Classroom> classroomList = null;
		
		System.out.println("pageIndex="+pageIndex+", classroom="+classroom);
		
		classroomSession=(Classroom) session.getAttribute("classroomSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryCourse页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("classroomSession", classroom);
			classroomList=classroomService.findClassroomList(classroom, pageModel);
		}
		//非首次进入queryCourse页面且传入页码
		else if(classroomSession!=null && pageIndex>0) {
			classroomList=classroomService.findClassroomList(classroomSession, pageModel);
		}
		model.addAttribute("classroomList", classroomList);
		model.addAttribute("pageModel", pageModel);
		return "queryClassroom";
	}
	
	
	@RequestMapping("insertClassroom")
	@ResponseBody
	public Object insertClassroom(@RequestBody Classroom classroom) {
		System.out.println(classroom);
		try{ classroomService.addClassroom(classroom); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateClassroom")
	@ResponseBody
	public Object updateClassroom(@RequestBody Classroom classroom) {
		System.out.println(classroom);
		try {
			classroomService.modifyClassroom(classroom);
		}catch (Exception e) {
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteClassroom")
	public String deleteClassroom(String[] idList) {
		for(String id:idList) { classroomService.removeClassroom(id); }
		return "redirect:/queryClassroom";
	}
	
	
}
