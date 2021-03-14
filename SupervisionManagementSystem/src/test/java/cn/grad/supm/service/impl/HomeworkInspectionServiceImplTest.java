package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.service.HomeworkInspectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class HomeworkInspectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private HomeworkInspectionService homeworkInspectionService;
	
	
	@Test
	public void testfindHomeworkInspectionList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		HomeworkInspectionRecord homeworkInspectionRecord=new HomeworkInspectionRecord();
		
		System.out.println(homeworkInspectionService.findHomeworkInspectionList(homeworkInspectionRecord, pageModel));
	}
	
	

}
