package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class DailyInspectionRecordDaoTest extends BaseDaoTest {

	@Autowired
	private DailyInspectionRecordDao dailyInspectionRecordDao;
	
	@Test
	public void testSelectAllDailyInspectionRecord() {
		List<DailyInspectionRecord> dailyInspectionRecordList=dailyInspectionRecordDao.selectAllDailyInspectionRecord();
		System.out.println(dailyInspectionRecordList);
		assertThat(0, not(dailyInspectionRecordList.size()));
	}
	
	
	@Test
	public void testSelectDailyInspectionRecordById() {
		DailyInspectionRecord dailyInspectionRecord=dailyInspectionRecordDao.selectDailyInspectionRecordById(1);
		System.out.println(dailyInspectionRecord);
		assertNotNull(dailyInspectionRecord);
	}

	
	@Test
	public void testSelectDailyInspectionRecordByPage() throws Exception {
		DailyInspectionRecord dailyInspectionRecord=new DailyInspectionRecord();
		dailyInspectionRecord.setDate("2020-10-19");
		Map<String,Object> params=new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		params.put("dailyInspectionRecord", dailyInspectionRecord);
		params.put("pageModel", pageModel);
		pageModel.setPageIndex(1);
		int count=dailyInspectionRecordDao.count(params);
		if(count>0) {
			pageModel.setRecordCount(count);
			System.out.println(dailyInspectionRecordDao.selectDailyInspectionRecordByPage(params));
			System.out.println(pageModel.getRecordCount());
			return;
		}
		System.out.println("无记录");
	}
	
	
	@Test
	public void testInsertDailyInspectionRecord() {
		DailyInspectionRecord dailyInspectionRecord=new DailyInspectionRecord(0,2,null,"一",new Date(),null,null);
		dailyInspectionRecordDao.insertDailyInspectionRecord(dailyInspectionRecord);
		System.out.println(dailyInspectionRecordDao.selectDailyInspectionRecordById(dailyInspectionRecord.getId()));
	}
	
	@Test
	public void testUpdateDailyInspectionRecord() {
		DailyInspectionRecord dailyInspectionRecord=dailyInspectionRecordDao.selectDailyInspectionRecordById(1);
		dailyInspectionRecord.setWeekly(1);
		dailyInspectionRecordDao.updateDailyInspectionRecord(dailyInspectionRecord);
		System.out.println(dailyInspectionRecordDao.selectDailyInspectionRecordById(1));
	}
	
	
	@Test
	public void testDeleteDailyInspectionRecord() {
		dailyInspectionRecordDao.deleteDailyInspectionRecord(1);
		System.out.println(dailyInspectionRecordDao.selectAllDailyInspectionRecord());
	}
	
	
	@Test
	public void testCountBySchoolYearAndSemester() {
		System.out.println(dailyInspectionRecordDao.countBySchoolYearAndSemester("0000077"));
	}
	
}
