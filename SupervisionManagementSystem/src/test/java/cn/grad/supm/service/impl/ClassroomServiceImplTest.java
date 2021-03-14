package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.service.ClassroomService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class ClassroomServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private ClassroomService classroomService;
	
	
	@Test
	public void testfindClassroomList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Classroom classroom=new Classroom();
		classroom.setName("慎思5");
		classroom.setSeatingCapacity(0);
		classroom.setType("实验室");
		
		System.out.println(classroomService.findClassroomList(classroom, pageModel));
	}
	
	
	@Test
	public void testAddClassroom() {
		Classroom classroom=new Classroom("0250015","慎思7栋403/406",0,"实验室","");
		classroomService.addClassroom(classroom);
	}
	
	
	@Test
	public void testUpdateClassroom() {
		Classroom classroom=new Classroom("0250014","慎思7栋403/406",0,"实验室","");
		classroomService.modifyClassroom(classroom);
	}
	
	
	@Test
	public void testDeleteClassroom() {
		classroomService.removeClassroom("0250014");
	}

}
