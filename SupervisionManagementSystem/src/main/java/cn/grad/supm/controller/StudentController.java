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

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Student;
import cn.grad.supm.service.StudentService;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping("addStudent")
	public String addStudent() {
		return "addStudent";
	}
	
	@ModelAttribute
	public Student studentModel(
			@RequestParam(defaultValue = "",value="id")String id,
			@RequestParam(defaultValue = "",value="name")String name,
			@RequestParam(defaultValue = "",value="sex")String sex,
			@RequestParam(defaultValue = "",value="classId")String classId) {
		Clazz clazz=new Clazz();
		clazz.setId(classId);
		Student student=new Student(id,name,sex,clazz);
		return student;
	}
	
	
	@RequestMapping("queryStudent")
	public String queryStudent(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute Student student,HttpSession session) {
		Student studentSession;
		PageModel pageModel=new PageModel();
		List<Student> studentList = null;
		
		System.out.println("pageIndex="+pageIndex+", student="+student);
		
		studentSession=(Student) session.getAttribute("studentSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryStudent页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("studentSession", student);
			studentList=studentService.findStudentList(student, pageModel);
		}
		//非首次进入queryStudent页面且传入页码
		else if(studentSession!=null && pageIndex>0) {
			studentList=studentService.findStudentList(studentSession, pageModel);
		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("pageModel", pageModel);
		return "queryStudent";
	}
	
	
	@RequestMapping("insertStudent")
	@ResponseBody
	public Object insertStudent(Model model,@RequestBody Student student) {
		System.out.println(student);
		
		try{ studentService.addStudent(student); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateStudent")
	@ResponseBody
	public Object updateStudent(@RequestBody Student student) {
		
		try{ studentService.modifyStudent(student); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteStudent")
	public String deleteStudent(String[] idList) {
		for(String id:idList) { studentService.removeStudent(id); }
		return "redirect:/queryStudent";
	}
	
	
}
