package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.service.DailyInspectionItemsService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class DailyInspectionItemsServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private DailyInspectionItemsService dailyInspectionItemsService;
	
	
	@Test
	public void testfindDailyInspectionItemsList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		DailyInspectionItems dailyInspectionItems=new DailyInspectionItems();
		dailyInspectionItems.setContent("迟到");;
		
		System.out.println(dailyInspectionItemsService.findDailyInspectionItemsList(dailyInspectionItems, pageModel));
	}
	
	

}
