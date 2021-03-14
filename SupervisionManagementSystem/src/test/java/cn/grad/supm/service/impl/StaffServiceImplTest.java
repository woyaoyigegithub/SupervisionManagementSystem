package cn.grad.supm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Power;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.service.StaffService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class StaffServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private StaffService staffService;
	
	
	@Test
	public void testfindStaffList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		Power power=new Power(0,"teacher","");
		Staff staff=new Staff();
		staff.setName("");
		staff.setPower(power);
		System.out.println(staffService.findStaffList(staff, pageModel));
	}
	
	

}
