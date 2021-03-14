package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.utils.BaseDaoTest;

public class DailyInspectionItemsDaoTest extends BaseDaoTest {

	@Autowired
	private DailyInspectionItemsDao dailyInspectionItemsDao;
	
	
	@Test
	public void testSelectDailyInspectionItemsById() {
		DailyInspectionItems dailyInspectionItems=dailyInspectionItemsDao.selectDailyInspectionItemsById(1);
		System.out.println(dailyInspectionItems);
		assertNotNull(dailyInspectionItems);
	}
	
	@Test
	public void testInsertInspectionItems() {
		DailyInspectionItems dailyInspectionItems=new DailyInspectionItems(0,"明天再说");
		dailyInspectionItemsDao.insertDailyInspectionItems(dailyInspectionItems);
		
		DailyInspectionItems dailyInspectionItems2=dailyInspectionItemsDao.selectDailyInspectionItemsById(dailyInspectionItems.getId());
		System.out.println(dailyInspectionItems2);
		assertNotNull(dailyInspectionItems2);
	}
	
	
	@Test
	public void testUpdateInspectionItems() {
		DailyInspectionItems dailyInspectionItems=dailyInspectionItemsDao.selectDailyInspectionItemsById(10);
		dailyInspectionItems.setContent("没有其他");
		System.out.println(dailyInspectionItemsDao.selectDailyInspectionItemsById(10));
	}
	
	
	@Test
	public void testDeleteInspectionItems() {
		DailyInspectionItems dailyInspectionItems=new DailyInspectionItems(0,"明天再说");
		dailyInspectionItemsDao.insertDailyInspectionItems(dailyInspectionItems);
		dailyInspectionItemsDao.deleteDailyInspectionItems(dailyInspectionItems.getId());
	}

}
