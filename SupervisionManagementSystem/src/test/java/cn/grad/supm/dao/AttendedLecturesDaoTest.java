package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.domain.AttendedLecturesSituation;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;


public class AttendedLecturesDaoTest extends BaseDaoTest {

	@Autowired
	private AttendedLecturesRecordDao attendedLecturesRecordDao;
	@Autowired
	private AttendedLecturesSituationDao attendedLecturesSituationDao;
	private Supervisor supervisor=new Supervisor("0000077","","");
	private CourseSelection courseSelection=new CourseSelection("(2020-2021-1)-0010000001-0000558-1",
			null,null,null,"",null,"2020-2021",1,"星期四","第5,6节","","1-16",2);
	private Clazz clazz=new Clazz("2017010133","","","","","");
	
	private static List<AttendedLecturesSituation> attendedLecturesSituationList=null;
	static {
		attendedLecturesSituationList=new ArrayList<AttendedLecturesSituation>();
		int[] scores= {4,6,5,20,10,10,20,15,10};
		for(int i=1;i<=9;i++) {
			AttendedLecturesItems attendedLecturesItems=new AttendedLecturesItems(i,"",10);
			AttendedLecturesSituation attendedLecturesSituation=new AttendedLecturesSituation(attendedLecturesItems,scores[i-1],null);
			attendedLecturesSituationList.add(attendedLecturesSituation);
		}
	}
	
	
	private AttendedLecturesRecord attendedLecturesRecord=new AttendedLecturesRecord(2,courseSelection,
			supervisor,"第三章第1节",clazz,0,0,0,new Date(),14,"一","1-2",attendedLecturesSituationList,100,'A',"无","无","无","跟踪听课");
	
	
	@Test
	public void testSelectAllAttendedLecturesRecord() {
		List<AttendedLecturesRecord> attendedLecturesRecordList=attendedLecturesRecordDao.selectAllAttendedLecturesRecord();
		System.out.println(attendedLecturesRecordList);
		assertThat(0, not(attendedLecturesRecordList.size()));
	}
	
	@Test
	public void testSelectAttendedLecturesRecordListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		AttendedLecturesRecord attendedLecturesRecord=new AttendedLecturesRecord();
		attendedLecturesRecord.setActualNum(0);
		params.put("attendedLecturesRecord", attendedLecturesRecord);
		pageModel.setRecordCount(attendedLecturesRecordDao.count(params));
		params.put("pageModel", pageModel);
		List<AttendedLecturesRecord> attendedLecturesRecordList=attendedLecturesRecordDao.selectAttendedLecturesRecordListByPage(params);
		System.out.println(attendedLecturesRecordList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(attendedLecturesRecordList.size()));
	}
	
	@Test
	public void testSelectAttendedLecturesRecordById() {
		AttendedLecturesRecord attendedLecturesRecord=attendedLecturesRecordDao.selectAttendedLecturesRecordById(1);
		System.out.println(attendedLecturesRecord);
		assertNotNull(attendedLecturesRecord);
	}
	
	@Test
	public void testAddAttendedLecturesRecord() {
		attendedLecturesRecordDao.insertAttendedLecturesRecord(attendedLecturesRecord);
		for(AttendedLecturesSituation attendedLecturesSituation:attendedLecturesRecord.getAttendedLecturesSituationList()) {
			attendedLecturesSituationDao.insertAttendedLecturesSituation(attendedLecturesRecord.getId(),attendedLecturesSituation);
		}
		
		AttendedLecturesRecord attendedLecturesRecord1=attendedLecturesRecordDao.selectAttendedLecturesRecordById(attendedLecturesRecord.getId());
		System.out.println(attendedLecturesRecord1);
		assertNotNull(attendedLecturesRecord1);
	}
	
	@Test
	public void testUpdateAttendedLecturesRecord() {
		
		AttendedLecturesRecord attendedLecturesRecord=attendedLecturesRecordDao.selectAttendedLecturesRecordById(1);
		attendedLecturesRecord.setTeachingSection("第三章第二节");
		attendedLecturesRecord.getAttendedLecturesSituationList().get(0).setEvaluationScore(0);
		attendedLecturesRecordDao.updateAttendedLecturesRecord(attendedLecturesRecord);
		for(AttendedLecturesSituation attendedLecturesSituation:attendedLecturesRecord.getAttendedLecturesSituationList()) {
			attendedLecturesSituationDao.updateAttendedLecturesSituation(attendedLecturesRecord.getId(),attendedLecturesSituation);
		}
		
		AttendedLecturesRecord attendedLecturesRecord2=attendedLecturesRecordDao.selectAttendedLecturesRecordById(1);
		System.out.println(attendedLecturesRecord2);
		assertNotNull(attendedLecturesRecord2);
	}
	
	@Test
	public void testDeleteAttendedLecturesRecord() {
		attendedLecturesSituationDao.deleteAttendedLecturesSituation(1);
		attendedLecturesRecordDao.deleteAttendedLecturesRecord(1);
		assertThat(attendedLecturesRecordDao.selectAllAttendedLecturesRecord().size(),is(0));
	}
	
	
	@Test
	public void testCountBySchoolYearAndSemester() {
		System.out.println(attendedLecturesRecordDao.countBySchoolYearAndSemester("0000954"));
	}

}
