package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Power;
import cn.grad.supm.service.PowerService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class PowerServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private PowerService powerService;
	
	
	@Test
	public void testfindPowerList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Power power=new Power();
		power.setName("s");
		
		System.out.println(powerService.findPowerList(power, pageModel));
	}
	
	

}
