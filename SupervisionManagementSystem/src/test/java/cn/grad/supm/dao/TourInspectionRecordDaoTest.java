package cn.grad.supm.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.TourInspectionRecord;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class TourInspectionRecordDaoTest extends BaseDaoTest {

	@Autowired
	private TourInspectionRecordDao tourInspectionRecordDao;
	
	
	@Test
	public void testSelectAllTourInspectionRecord() {
		List<TourInspectionRecord> tourInspectionRecordList=tourInspectionRecordDao.selectAllTourInspectionRecord();
		System.out.println(tourInspectionRecordList);
		assertThat(0,not(tourInspectionRecordList.size()));
	}
	
	
	@Test
	public void testSelectTourInspectionRecordById() {
		TourInspectionRecord tourInspectionRecord=tourInspectionRecordDao.selectTourInspectionRecordById(1);
		System.out.println(tourInspectionRecord);
		assertNotNull(tourInspectionRecord);
	}
	
	
	@Test
	public void testSelectTourInspectionRecordByPage() {
		TourInspectionRecord tourInspectionRecord=new TourInspectionRecord();
		Map<String,Object> params=new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		params.put("tourInspectionRecord", tourInspectionRecord);
		params.put("pageModel", pageModel);
		pageModel.setPageIndex(1);
		int count=tourInspectionRecordDao.count(params);
		pageModel.setRecordCount(count);
		if(count>0) {
			System.out.println(tourInspectionRecordDao.selectTourInspectionRecordListByPage(params));
		}
	}
	
	
	@Test
	public void testInsertTourInspectionRecord() {
		TourInspectionRecord tourInspectionRecord=new TourInspectionRecord(2,new Date(),14,16,"明辨楼",new Supervisor("0000082",null,null),null);
		tourInspectionRecordDao.insertTourInspectionRecord(tourInspectionRecord);
		System.out.println(tourInspectionRecordDao.selectTourInspectionRecordById(tourInspectionRecord.getId()));
	}
	
	
	@Test
	public void testUpdateTourInspectionRecord() {
		TourInspectionRecord tourInspectionRecord=tourInspectionRecordDao.selectTourInspectionRecordById(1);
		tourInspectionRecord.setStartingTime(9);
		tourInspectionRecordDao.updateTourInspectionRecord(tourInspectionRecord);
		System.out.println(tourInspectionRecordDao.selectTourInspectionRecordById(1));
	}
	
	
	@Test
	public void testDeleteTourInspectionRecord() {
		tourInspectionRecordDao.deleteTourInspectionRecord(1);
		System.out.println(tourInspectionRecordDao.selectTourInspectionRecordById(1));
	}
	
}
