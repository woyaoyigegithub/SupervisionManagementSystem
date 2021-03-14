package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Course;
import cn.grad.supm.service.CourseService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class CourseServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private CourseService courseService;
	
	
	@Test
	public void testfindCourseList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Course course=new Course();
		course.setName("工程");
		
		System.out.println(courseService.findCourseList(course, pageModel));
	}
	
	

}
