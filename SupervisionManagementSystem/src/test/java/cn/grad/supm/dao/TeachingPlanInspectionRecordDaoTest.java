package cn.grad.supm.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.TeachingPlanInspectionRecord;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class TeachingPlanInspectionRecordDaoTest extends BaseDaoTest {

	@Autowired
	private TeachingPlanInspectionRecordDao teachingPlanInspectionRecordDao;
	
	
	@Test
	public void testSelectAllTeachingPlanInspectionRecord() {
		List<TeachingPlanInspectionRecord> teachingPlanInspectionRecordList=teachingPlanInspectionRecordDao.selectAllTeachingPlanInspectionRecord();
		System.out.println(teachingPlanInspectionRecordList);
		assertThat(0,not(teachingPlanInspectionRecordList.size()));
	}
	
	
	
	@Test
	public void testSelectTeachingPlanInspectionRecordById() {
		TeachingPlanInspectionRecord teachingPlanInspectionRecord=teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordById(1);
		System.out.println(teachingPlanInspectionRecord);
		assertNotNull(teachingPlanInspectionRecord);
	}
	
	
	@Test
	public void testSelectTeachingPlanInspectionRecordByPage() {
		TeachingPlanInspectionRecord teachingPlanInspectionRecord=new TeachingPlanInspectionRecord();
		teachingPlanInspectionRecord.setId(1);
		Map<String,Object> params=new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		params.put("teachingPlanInspectionRecord", teachingPlanInspectionRecord);
		params.put("pageModel", pageModel);
		pageModel.setPageIndex(1);
		int count=teachingPlanInspectionRecordDao.count(params);
		pageModel.setRecordCount(count);
		if(count>0) {
			System.out.println(teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordListByPage(params));
		}
	}
	
	
	@Test
	public void testInsertTeachingPlanInspectionRecord() {
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId("(2020-2021-1)-0010000016-0001199-1");
		TeachingPlanInspectionRecord teachingPlanInspectionRecord=new TeachingPlanInspectionRecord(2,courseSelection,null,"商榷");
		teachingPlanInspectionRecordDao.insertTeachingPlanInspectionRecord(teachingPlanInspectionRecord);
		System.out.println(teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordById(teachingPlanInspectionRecord.getId()));
	}
	
	
	@Test
	public void testUpdateTeachingPlanInspectionRecord() {
		TeachingPlanInspectionRecord teachingPlanInspectionRecord=teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordById(1);
		teachingPlanInspectionRecord.setDiscussingAndAffirming("好了，没意见");
		teachingPlanInspectionRecordDao.updateTeachingPlanInspectionRecord(teachingPlanInspectionRecord);
		System.out.println(teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordById(1));
	}
	
	
	@Test
	public void testdeleteTeachingPlanInspectionRecord() {
		teachingPlanInspectionRecordDao.deleteTeachingPlanInspectionRecord(1);
		System.out.println(teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordById(1));
	}
	
	
}
