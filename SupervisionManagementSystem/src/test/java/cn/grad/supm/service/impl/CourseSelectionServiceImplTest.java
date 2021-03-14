package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.service.CourseSelectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class CourseSelectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private CourseSelectionService courseSelectionService;
	
	
	@Test
	public void testfindCourseList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setBiweekly("");
		
		System.out.println(courseSelectionService.findCourseSelectionList(courseSelection, pageModel));
	}
	
	

}
