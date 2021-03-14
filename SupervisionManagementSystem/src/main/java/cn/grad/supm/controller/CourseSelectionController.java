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
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Course;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.service.CourseSelectionService;
import cn.grad.supm.service.Helper;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class CourseSelectionController {

	@Autowired
	private CourseSelectionService courseSelectionService;
	@Autowired
	private Helper helper;
	
	
	@RequestMapping("addCourseSelection")
	public String addCourseSelection(Model model) {
		model.addAttribute("departmentList", Utils.getDepartmentList());
		model.addAttribute("schoolYearList", Utils.getSchoolYearList());
		return "addCourseSelection";
	}
	
	@ModelAttribute
	public CourseSelection courseSelectionModel(
			@ModelAttribute CourseSelection courseSelection,
			@RequestParam(defaultValue = "",value="courseId")String courseId,
			@RequestParam(defaultValue = "",value="teacherId")String teacherId,
			@RequestParam(defaultValue = "",value="classId")String classId,
			@RequestParam(defaultValue = "",value="classroomId")String classroomId) {
		courseSelection.setCourse(new Course(courseId,"","",""));
		courseSelection.setTeacher(new Teacher(teacherId,"","",""));
		courseSelection.setClazz(new Clazz(classId,"","","","",""));
		courseSelection.setClassroom(new Classroom(classroomId,"",0,"",""));
		return courseSelection;
	}
	
	@RequestMapping("queryCourseSelection")
	public String queryCourseSelection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute CourseSelection courseSelection,HttpSession session) {
		CourseSelection courseSelectionSession;
		PageModel pageModel=new PageModel();
		List<CourseSelection> courseSelectionList = null;
		
		System.out.println("pageIndex="+pageIndex+", courseSelection="+courseSelection);
		
		courseSelectionSession=(CourseSelection) session.getAttribute("courseSelectionSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryCourseSelection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("courseSelectionSession", courseSelection);
			courseSelectionList=courseSelectionService.findCourseSelectionList(courseSelection, pageModel);
		}
		//非首次进入queryCourseSelection页面且传入页码
		else if(courseSelectionSession!=null && pageIndex>0) {
			courseSelectionList=courseSelectionService.findCourseSelectionList(courseSelectionSession, pageModel);
		}
		model.addAttribute("courseSelectionList", courseSelectionList);
		model.addAttribute("departmentList", Utils.getDepartmentList());
		model.addAttribute("schoolYearList", Utils.getSchoolYearList());
		model.addAttribute("pageModel", pageModel);
		return "queryCourseSelection";
	}
	
	
	@RequestMapping("insertCourseSelection")
	@ResponseBody
	public Object insertCourseSelection(@RequestBody CourseSelection courseSelection) {
		System.out.println(courseSelection);
		try{ courseSelectionService.addCourseSelection(courseSelection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateCourseSelection")
	@ResponseBody
	public Object updateCourseSelection(@RequestBody CourseSelection courseSelection) {
		System.out.println(courseSelection);
		try{ courseSelectionService.modifyCourseSelection(courseSelection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteCourseSelection")
	public String deleteCourseSelection(String[] idList) {
		for(String id:idList) { courseSelectionService.removeCourseSelection(id); }
		return "redirect:/queryCourseSelection";
	}
	
	
	@RequestMapping("queryCourseSelectionById")
	@ResponseBody
	public Object queryCourseSelectionById(@RequestParam("courseSelectionId")String courseSelectionId) {
		CourseSelection courseSelection=helper.findCourseSelectionById(courseSelectionId);
		return courseSelection;
	}
	
}
