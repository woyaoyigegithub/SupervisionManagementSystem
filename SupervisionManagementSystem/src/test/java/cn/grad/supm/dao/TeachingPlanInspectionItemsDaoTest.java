package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.utils.BaseDaoTest;

public class TeachingPlanInspectionItemsDaoTest extends BaseDaoTest {

	@Autowired
	private TeachingPlanInspectionItemsDao teachingPlanInspectionItemsDao;
	
	@Test
	public void testSelectAllTeachingPlanInspectionItems() {
		List<TeachingPlanInspectionItems> teachingPlanInspectionItemsList=teachingPlanInspectionItemsDao.selectAllTeachingPlanInspectionItems();
		System.out.println(teachingPlanInspectionItemsList);
		assertThat(0, not(teachingPlanInspectionItemsList.size()));
	}
	
	@Test
	public void testSelectTeachingPlanInspectionItemsById() {
		TeachingPlanInspectionItems teachingPlanInspectionItems=teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsById(1);
		System.out.println(teachingPlanInspectionItems);
		assertNotNull(teachingPlanInspectionItems);
	}
	
	
	@Test
	public void testInsertTeachingPlanInspectionItems() {
		TeachingPlanInspectionItems teachingPlanInspectionItems=new TeachingPlanInspectionItems(0,"看看","明确（）具体");
		teachingPlanInspectionItemsDao.insertTeachingPlanInspectionItems(teachingPlanInspectionItems);
		System.out.println(teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsById(teachingPlanInspectionItems.getId()));
	}
	
	
	@Test
	public void testUpdateTeachingPlanInspectionItems() {
		TeachingPlanInspectionItems teachingPlanInspectionItems=teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsById(1);
		teachingPlanInspectionItems.setContent("112");
		teachingPlanInspectionItemsDao.updateTeachingPlanInspectionItems(teachingPlanInspectionItems);
		System.out.println(teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsById(teachingPlanInspectionItems.getId()));
	}
	
	
	@Test
	public void testDeleteTeachingPlanInspectionItems() {
		teachingPlanInspectionItemsDao.deleteTeachingPlanInspectionItems(1);
		System.out.println(teachingPlanInspectionItemsDao.selectAllTeachingPlanInspectionItems());
	}
	
}
