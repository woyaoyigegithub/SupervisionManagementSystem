package cn.grad.supm.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class HomeworkInspectionRecordDaoTest extends BaseDaoTest {

	@Autowired
	private HomeworkInspectionRecordDao homeworkInspectionRecordDao;
	
	
	@Test
	public void testSelectAllHomeworkInspectionRecord() {
		long start=System.currentTimeMillis();
		List<HomeworkInspectionRecord> homeworkInspectionRecordList=homeworkInspectionRecordDao.selectAllHomeworkInspectionRecord();
		long end=System.currentTimeMillis();
		System.out.println("cost time : "+(end-start));
		System.out.println(homeworkInspectionRecordList);
		assertThat(0,not(homeworkInspectionRecordList.size()));
	}
	
	
	
	@Test
	public void testSelectHomeworkInspectionRecordById() {
		HomeworkInspectionRecord homeworkInspectionRecord=homeworkInspectionRecordDao.selectHomeworkInspectionRecordById(1);
		System.out.println(homeworkInspectionRecord);
		assertNotNull(homeworkInspectionRecord);
	}
	
	
	@Test
	public void testSelectHomeworkInspectionRecordListByPage() {
		
		HomeworkInspectionRecord homeworkInspectionRecord=new HomeworkInspectionRecord();
		homeworkInspectionRecord.setPoorCorrection(0);
		Map<String,Object> params=new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		params.put("dailyInspectionRecord", homeworkInspectionRecord);
		params.put("pageModel", pageModel);
		pageModel.setPageIndex(1);
		int count=homeworkInspectionRecordDao.count(params);
		if(count>0) {
			pageModel.setRecordCount(count);
			System.out.println(homeworkInspectionRecordDao.selectHomeworkInspectionRecordListByPage(params));
			System.out.println(pageModel.getRecordCount());
		}
	}
	
	
	@Test
	public void testInsertHomeworkInspectionRecordListByPage() {
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId("(2020-2021-1)-0010000001-0000558-1");
		HomeworkInspectionRecord homeworkInspectionRecord=new HomeworkInspectionRecord(0, courseSelection, 0, 0, 0, 0, 0, 0, 0, "备注");
		homeworkInspectionRecord.setAssignmentsOrReportsFenNum(1000);
		homeworkInspectionRecord.setPoorCorrection(0);
		homeworkInspectionRecordDao.insertHomeworkInspectionRecord(homeworkInspectionRecord);
		System.out.println(homeworkInspectionRecordDao.selectHomeworkInspectionRecordById(homeworkInspectionRecord.getId()));
	}
	
	
	@Test
	public void testUpdateHomeworkInspectionRecordListByPage() {
		
		HomeworkInspectionRecord homeworkInspectionRecord=homeworkInspectionRecordDao.selectHomeworkInspectionRecordById(1);
		homeworkInspectionRecord.setRemarks("还好");
		homeworkInspectionRecord.setAssignmentsOrReportsFenNum(1000);
		homeworkInspectionRecord.setPoorCorrection(0);
		homeworkInspectionRecordDao.updateHomeworkInspectionRecord(homeworkInspectionRecord);
		System.out.println(homeworkInspectionRecordDao.selectHomeworkInspectionRecordById(1));
	}
	
	
	@Test
	public void testDeleteHomeworkInspectionRecordListByPage() {
		homeworkInspectionRecordDao.deleteHomeworkInspectionRecord(1);
		System.out.println(homeworkInspectionRecordDao.selectHomeworkInspectionRecordById(1));
	}
	
}
