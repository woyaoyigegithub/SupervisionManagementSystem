package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Staff;
import cn.grad.supm.utils.tag.PageModel;

public interface StaffService {

	public List<Staff> findStaffList(Staff staff,PageModel pageModel);
	
	
	public void addStaff(Staff staff);
	
	
	public void modifyStaff(Staff staff);
	
	
	public void removeStaff(String id);
	
}
