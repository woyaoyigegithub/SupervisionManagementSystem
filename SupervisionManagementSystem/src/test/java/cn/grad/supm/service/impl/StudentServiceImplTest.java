package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Student;
import cn.grad.supm.service.StudentService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class StudentServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private StudentService studentService;
	
	
	@Test
	public void testfindStudentList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Student student=new Student();
		student.setSex("男");
		
		System.out.println(studentService.findStudentList(student, pageModel));
	}
	
	

}
