package cn.grad.supm.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.utils.BaseControllerTest;

public class DailyInspectionControllerTest extends BaseControllerTest {

	@Autowired
	private DailyInspectionController dailyInspectionController;
	
	
	@Test
	public void testGetAttendedLecutesItemsList() {
		System.out.println(dailyInspectionController.getDailyInspectionItemsList());
	}
}
