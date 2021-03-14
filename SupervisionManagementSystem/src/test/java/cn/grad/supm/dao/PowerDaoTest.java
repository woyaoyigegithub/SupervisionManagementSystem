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
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class PowerDaoTest extends BaseDaoTest {

	@Autowired
	private PowerDao powerDao;
	private Power power=new Power("test", "测试人员");
	
	@Test
	public void testSelectAllpower() {
		List<Power> powerList=powerDao.selectAllPower();
		System.out.println(powerList);
		assertThat(0, not(powerList.size()));
	}
	
	@Test
	public void testSelectPowerListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(2);
		Power power=new Power();
		params.put("power", power);
		pageModel.setRecordCount(powerDao.count(params));
		params.put("pageModel", pageModel);
		List<Power> powerList=powerDao.selectPowerListByPage(params);
		System.out.println(powerList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(powerList.size()));
	}
	
	@Test
	public void testSelectPowerById() {
		Power power=powerDao.selectPowerById(1);
		System.out.println(power);
		assertNotNull(power);
	}
	
	@Test
	public void testAddPower() {
		powerDao.insertPower(power);
		
		Power power1=powerDao.selectPowerById(power.getId());
		System.out.println(power1);
		assertNotNull(power1);
	}
	
	@Test
	public void testUpdatePower() {
		powerDao.insertPower(power);
		power.setName("测试1");
		powerDao.updatePower(power);
		
		Power power1=powerDao.selectPowerById(power.getId());
		System.out.println(power1);
		assertNotNull(power1);
	}
	
	@Test
	public void testDeletePower() {
		powerDao.insertPower(power);
		
		Power power1=powerDao.selectPowerById(power.getId());
		System.out.println(power1);
		assertNotNull(power1);
		
		powerDao.deletePower(power.getId());
		
		Power power2=powerDao.selectPowerById(power.getId());
		System.out.println(power2);
		assertNull(power2);
	}
	
}
