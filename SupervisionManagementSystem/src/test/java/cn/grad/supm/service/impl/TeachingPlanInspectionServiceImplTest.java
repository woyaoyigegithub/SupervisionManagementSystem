package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TeachingPlanInspectionRecord;
import cn.grad.supm.service.TeachingPlanInspectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class TeachingPlanInspectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private TeachingPlanInspectionService teachingPlanInspectionService;
	
	
	@Test
	public void testfindTeachingPlanInspectionList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		TeachingPlanInspectionRecord teachingPlanInspectionRecord=new TeachingPlanInspectionRecord();
		teachingPlanInspectionRecord.setId(1);
		
		System.out.println(teachingPlanInspectionService.findTeachingPlanInspectionList(teachingPlanInspectionRecord, pageModel));
	}
	
	

}
