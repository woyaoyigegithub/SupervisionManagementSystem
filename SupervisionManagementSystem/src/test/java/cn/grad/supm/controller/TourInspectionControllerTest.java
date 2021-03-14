package cn.grad.supm.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.utils.BaseControllerTest;

public class TourInspectionControllerTest extends BaseControllerTest {

	@Autowired
	private TourInspectionController tourInspectionController;
	
	
	@Test
	public void testGetAttendedLecutesItemsList() {
		System.out.println(tourInspectionController.getTourInspectionItemsList());
	}
}
