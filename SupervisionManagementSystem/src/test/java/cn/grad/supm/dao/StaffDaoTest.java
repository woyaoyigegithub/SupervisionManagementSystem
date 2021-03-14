package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Power;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class StaffDaoTest extends BaseDaoTest {

	@Autowired
	private StaffDao staffDao;
	private Staff staff=new Staff("0002500", "五斗米", "工商管理学院","",new Power(3,"teacher","教师"));
	
	@Test
	public void testSelectAllStaff() {
		List<Staff> staffList=staffDao.selectAllStaff();
		System.out.println(staffList);
		assertThat(0, not(staffList.size()));
	}
	
	@Test
	public void testSelectStaffListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		Staff staff=new Staff();
		Power power=new Power();
		power.setName("teacher");
		staff.setPower(power);
		params.put("staff", staff);
		pageModel.setRecordCount(staffDao.count(params));
		params.put("pageModel", pageModel);
		List<Staff> staffList=staffDao.selectStaffListByPage(params);
		System.out.println(staffList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(staffList.size()));
	}
	
	@Test
	public void testSelectStaffById() {
		Staff staff=staffDao.selectStaffById("0000001");
		System.out.println(staff);
		assertNotNull(staff);
	}
	
	@Test
	public void testAddStaff() {
		staffDao.insertStaff(staff);
		
		Staff staff1=staffDao.selectStaffById(staff.getId());
		System.out.println(staff1);
		assertNotNull(staff1);
	}
	
	@Test
	public void testUpdateStaff() {
		staffDao.insertStaff(staff);
		staff.setName("测试1");
		staffDao.updateStaff(staff);
		
		Staff staff1=staffDao.selectStaffById(staff.getId());
		System.out.println(staff1);
		assertNotNull(staff1);
	}
	
	@Test
	public void testDeleteStaff() {
		staffDao.insertStaff(staff);
		
		Staff staff1=staffDao.selectStaffById(staff.getId());
		System.out.println(staff1);
		assertNotNull(staff1);
		
		staffDao.deleteStaff(staff.getId());
		
		Staff staff2=staffDao.selectStaffById(staff.getId());
		System.out.println(staff2);
		assertNull(staff2);
	}
}
