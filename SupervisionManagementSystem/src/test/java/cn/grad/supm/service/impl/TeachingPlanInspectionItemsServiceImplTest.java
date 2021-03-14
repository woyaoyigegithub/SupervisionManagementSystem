package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.service.TeachingPlanInspectionItemsService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class TeachingPlanInspectionItemsServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private TeachingPlanInspectionItemsService teachingPlanInspectionItemsService;
	
	
	@Test
	public void testfindTeachingPlanInspectionItemsList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		TeachingPlanInspectionItems teachingPlanInspectionItems=new TeachingPlanInspectionItems();
		teachingPlanInspectionItems.setId(2);
		
		System.out.println(teachingPlanInspectionItemsService.findTeachingPlanInspectionItemsList(teachingPlanInspectionItems, pageModel));
	}
	
	

}
