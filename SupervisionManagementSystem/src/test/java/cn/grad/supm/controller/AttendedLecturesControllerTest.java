package cn.grad.supm.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.utils.BaseControllerTest;

public class AttendedLecturesControllerTest extends BaseControllerTest {

	@Autowired
	private AttendedLecturesController attendedLecturesController;
	
	
	@Test
	public void testGetAttendedLecutesItemsList() {
		long start=System.currentTimeMillis();
		System.out.println(attendedLecturesController.queryAttendedLecturesItemsList("美术"));
		long end=System.currentTimeMillis();
		System.out.println("cost time : "+(end-start));
	}
}
