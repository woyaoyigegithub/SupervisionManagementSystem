package cn.grad.supm.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.utils.BaseControllerTest;

public class PapersInspectionControllerTest extends BaseControllerTest {

	@Autowired
	private PapersInspectionController papersInspectionController;
	
	
	@Test
	public void testGetAttendedLecutesItemsList() {
		System.out.println(papersInspectionController.getPapersInspectionItemsList());
	}
}
