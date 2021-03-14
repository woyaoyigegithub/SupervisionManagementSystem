package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.service.PapersInspectionItemsService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class PapersInspectionItemsServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private PapersInspectionItemsService papersInspectionItemsService;
	
	
	@Test
	public void testfindPapersInspectionItemsList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		PapersInspectionItems papersInspectionItems=new PapersInspectionItems();
		papersInspectionItems.setContent("学生");
		
		System.out.println(papersInspectionItemsService.findPapersInspectionItemsList(papersInspectionItems, pageModel));
	}
	
	

}
