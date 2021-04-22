package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.StaffDao;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.service.StaffService;
import cn.grad.supm.utils.tag.PageModel;

@Service("staffService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao; 

	
	@Override
	public List<Staff> findStaffList(Staff staff,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("staff", staff);
		
		int recordCount=staffDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Staff> staffList=staffDao.selectStaffListByPage(params);
		return staffList;
	}

	@Override
	public Staff findStaff(String id) {
		return staffDao.selectStaffById(id);
	}
	
	@Override
	public void addStaff(Staff staff) {
		staffDao.insertStaff(staff);
	}
	
	
	@Override
	public void modifyStaff(Staff staff) {
		staffDao.updateStaff(staff);
	}

	@Override
	public void removeStaff(String id) {
		staffDao.deleteStaff(id);
	}

	
	
	
}
