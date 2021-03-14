package cn.grad.supm.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.utils.BaseControllerTest;

public class TeachingPlanInspectionControllerTest extends BaseControllerTest {

	@Autowired
	private TeachingPlanInspectionController teachingPlanInspectionController;
	
	
	@Test
	public void TestQueryTeachingPlanInspectionItemsList() {
		System.out.println(teachingPlanInspectionController.queryTeachingPlanInspectionItemsList("理论"));
	}
}
