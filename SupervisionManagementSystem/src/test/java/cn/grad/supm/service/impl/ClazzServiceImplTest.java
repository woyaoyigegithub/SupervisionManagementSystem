package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.service.ClazzService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class ClazzServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private ClazzService clazzService;
	
	
	@Test
	public void testfindClazzList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Clazz clazz=new Clazz();
		clazz.setName("工程");
		
		System.out.println(clazzService.findClazzList(clazz, pageModel));
	}
	
	

}
