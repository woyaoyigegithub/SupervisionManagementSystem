package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.utils.BaseDaoTest;

public class PapersInspectionItemsDaoTest extends BaseDaoTest {

	@Autowired
	private PapersInspectionItemsDao papersInspectionItemsDao;
	
	@Test
	public void testSelectAllPapersInspectionItems() {
		List<PapersInspectionItems> papersInspectionItemsList=papersInspectionItemsDao.selectAllPapersInspectionItems();
		System.out.println(papersInspectionItemsList);
		assertThat(0, not(papersInspectionItemsList.size()));
	}
	
	@Test
	public void testSelectPapersInspectionItemsById() {
		PapersInspectionItems papersInspectionItems=papersInspectionItemsDao.selectPapersInspectionItemsById(1);
		System.out.println(papersInspectionItems);
		assertNotNull(papersInspectionItems);
	}
	
	
	@Test
	public void testInsertPapersInspectionItems() {
		PapersInspectionItems papersInspectionItems=new PapersInspectionItems(0,"学生打小抄","有;没有");
		papersInspectionItemsDao.insertPapersInspectionItems(papersInspectionItems);
		System.out.println(papersInspectionItemsDao.selectPapersInspectionItemsById(papersInspectionItems.getId()));
	}
	
	
	@Test
	public void testUpdatePapersInspectionItems() {
		PapersInspectionItems papersInspectionItems=papersInspectionItemsDao.selectPapersInspectionItemsById(1);
		papersInspectionItems.setContent("112");
		papersInspectionItemsDao.updatePapersInspectionItems(papersInspectionItems);
		System.out.println(papersInspectionItemsDao.selectPapersInspectionItemsById(papersInspectionItems.getId()));
	}
	
	
	@Test
	public void testDeletePapersInspectionItems() {
		papersInspectionItemsDao.deletePapersInspectionItems(1);
		System.out.println(papersInspectionItemsDao.selectAllPapersInspectionItems());
	}
}
