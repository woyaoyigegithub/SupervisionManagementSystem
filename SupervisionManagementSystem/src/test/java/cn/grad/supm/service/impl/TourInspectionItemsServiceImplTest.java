package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.service.TourInspectionItemsService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class TourInspectionItemsServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private TourInspectionItemsService tourInspectionItemsService;
	
	
	@Test
	public void testfindTourInspectionItemsList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		TourInspectionItems tourInspectionItems=new TourInspectionItems();
		tourInspectionItems.setContent("考试");
		
		System.out.println(tourInspectionItemsService.findTourInspectionItemsList(tourInspectionItems, pageModel));
	}
	
	

}
