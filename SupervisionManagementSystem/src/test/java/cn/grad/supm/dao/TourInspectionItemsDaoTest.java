package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.utils.BaseDaoTest;

public class TourInspectionItemsDaoTest extends BaseDaoTest {

	@Autowired
	private TourInspectionItemsDao tourInspectionItemsDao;
	
	@Test
	public void testSelectAllTourInspectionItems() {
		List<TourInspectionItems> tourInspectionItemsList=tourInspectionItemsDao.selectAllTourInspectionItems();
		System.out.println(tourInspectionItemsList);
		assertThat(0, not(tourInspectionItemsList.size()));
	}
	
	@Test
	public void testSelectTourInspectionItemsById() {
		TourInspectionItems tourInspectionItems=tourInspectionItemsDao.selectTourInspectionItemsById(1);
		System.out.println(tourInspectionItems);
		assertNotNull(tourInspectionItems);
	}
	
	
	@Test
	public void testInsertTourInspectionItems() {
		TourInspectionItems tourInspectionItems=new TourInspectionItems(0,"学生打小抄");
		tourInspectionItemsDao.insertTourInspectionItems(tourInspectionItems);
		System.out.println(tourInspectionItemsDao.selectTourInspectionItemsById(tourInspectionItems.getId()));
	}
	
	
	@Test
	public void testUpdateTourInspectionItems() {
		TourInspectionItems tourInspectionItems=tourInspectionItemsDao.selectTourInspectionItemsById(1);
		tourInspectionItems.setContent("112");
		tourInspectionItemsDao.updateTourInspectionItems(tourInspectionItems);
		System.out.println(tourInspectionItemsDao.selectTourInspectionItemsById(tourInspectionItems.getId()));
	}
	
	
	@Test
	public void testDeleteTourInspectionItems() {
		tourInspectionItemsDao.deleteTourInspectionItems(1);
		System.out.println(tourInspectionItemsDao.selectAllTourInspectionItems());
	}
	
}
