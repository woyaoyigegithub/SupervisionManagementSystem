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

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class AttendedLecturesItemsDaoTest extends BaseDaoTest {

	@Autowired
	private AttendedLecturesItemsDao attendedLecturesItemsDao;
	private AttendedLecturesItems attendedLecturesItems=new AttendedLecturesItems(20,"测试",10);
	
	@Test
	public void testSelectAllAttendedLecturesItems() {
		List<AttendedLecturesItems> attendedLecturesItemsList=attendedLecturesItemsDao.selectAllAttendedLecturesItems();
		System.out.println(attendedLecturesItemsList);
		assertThat(0, not(attendedLecturesItemsList.size()));
	}
	
	
	@Test
	public void testSelectattendedLecturesItemsListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		AttendedLecturesItems attendedLecturesItems=new AttendedLecturesItems();
		attendedLecturesItems.setContent(":");
		params.put("attendedLecturesItems", attendedLecturesItems);
		pageModel.setRecordCount(attendedLecturesItemsDao.count(params));
		params.put("pageModel", pageModel);
		List<AttendedLecturesItems> attendedLecturesItemsList=attendedLecturesItemsDao.selectAttendedLecturesItemsListByPage(params);
		System.out.println(attendedLecturesItemsList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(attendedLecturesItemsList.size()));
	}
	
	
	
	@Test
	public void testSelectAttendedLecturesItemsById() {
		AttendedLecturesItems attendedLecturesItems1=attendedLecturesItemsDao.selectAttendedLecturesItemsById(attendedLecturesItems.getId());
		System.out.println(attendedLecturesItems1);
		assertNotNull(attendedLecturesItems1);
	}
	
	
	@Test
	public void testAddAttendedLecturesItems() {
		attendedLecturesItemsDao.insertAttendedLecturesItems(attendedLecturesItems);
		
		AttendedLecturesItems attendedLecturesItems1=attendedLecturesItemsDao.selectAttendedLecturesItemsById(attendedLecturesItems.getId());
		System.out.println(attendedLecturesItems1);
		assertNotNull(attendedLecturesItems1);
	}
	
	@Test
	public void testUpdateAttendedLecturesItems() {
		attendedLecturesItemsDao.insertAttendedLecturesItems(attendedLecturesItems);
		attendedLecturesItems.setContent("测试1");
		attendedLecturesItemsDao.updateAttendedLecturesItems(attendedLecturesItems);
		
		AttendedLecturesItems attendedLecturesItems1=attendedLecturesItemsDao.selectAttendedLecturesItemsById(attendedLecturesItems.getId());
		System.out.println(attendedLecturesItems1);
		assertNotNull(attendedLecturesItems1);
	}
	
	@Test
	public void testDeleteAttendedLecturesItems() {
		attendedLecturesItemsDao.insertAttendedLecturesItems(attendedLecturesItems);
		
		AttendedLecturesItems attendedLecturesItems1=attendedLecturesItemsDao.selectAttendedLecturesItemsById(attendedLecturesItems.getId());
		System.out.println(attendedLecturesItems1);
		assertNotNull(attendedLecturesItems1);
		
		attendedLecturesItemsDao.deleteAttendedLecturesItems(attendedLecturesItems.getId());
		
		AttendedLecturesItems attendedLecturesItems2=attendedLecturesItemsDao.selectAttendedLecturesItemsById(attendedLecturesItems.getId());
		System.out.println(attendedLecturesItems2);
		assertNull(attendedLecturesItems2);
	}
	
}
