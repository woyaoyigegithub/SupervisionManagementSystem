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

import cn.grad.supm.domain.Course;
import cn.grad.supm.service.CourseService;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping("addCourse")
	public String addCourse() {
		return "addCourse";
	}
	
	@RequestMapping("queryCourse")
	public String queryCourse(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Course course,HttpSession session) {
		Course courseSession;
		PageModel pageModel=new PageModel();
		List<Course> courseList = null;
		
		System.out.println("pageIndex="+pageIndex+", course="+course);
		
		courseSession=(Course) session.getAttribute("courseSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryCourse页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("courseSession", course);
			courseList=courseService.findCourseList(course, pageModel);
		}
		//非首次进入queryCourse页面且传入页码
		else if(courseSession!=null && pageIndex>0) {
			courseList=courseService.findCourseList(courseSession, pageModel);
		}
		model.addAttribute("courseList", courseList);
		model.addAttribute("pageModel", pageModel);
		return "queryCourse";
	}
	
	
	@RequestMapping("insertCourse")
	@ResponseBody
	public Object insertCourse(@RequestBody Course course) {
		try{ courseService.addCourse(course); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateCourse")
	@ResponseBody
	public Object updateCourse(@RequestBody Course course) {
		System.out.println(course);
		try{ courseService.modifyCourse(course); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteCourse")
	public String deleteCourse(String[] idList) {
		for(String id:idList) { courseService.removeCourse(id); }
		return "redirect:/queryCourse";
	}
	
}
