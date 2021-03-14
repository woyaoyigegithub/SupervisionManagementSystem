package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.service.AttendedLecturesItemsService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class AttendedLecturesItemsServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private AttendedLecturesItemsService attendedLecturesItemsService;
	
	
	@Test
	public void testfindAttendedLecturesItemsList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		AttendedLecturesItems attendedLecturesItems=new AttendedLecturesItems();
		attendedLecturesItems.setContent("学生");
		
		System.out.println(attendedLecturesItemsService.findAttendedLecturesItemsList(attendedLecturesItems, pageModel));
	}
	
	

}
