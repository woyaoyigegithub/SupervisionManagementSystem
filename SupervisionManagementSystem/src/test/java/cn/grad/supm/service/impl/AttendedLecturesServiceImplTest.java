package cn.grad.supm.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.domain.AttendedLecturesSituation;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.service.AttendedLecturesService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

public class AttendedLecturesServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private AttendedLecturesService attendedLecturesService;
	
	
	@Test
	public void testfindAttendedLecturesList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		AttendedLecturesRecord attendedLecturesRecord=new AttendedLecturesRecord();
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId("2020-2021-1");
		attendedLecturesRecord.setCourseSelection(courseSelection);
		
		System.out.println(attendedLecturesService.findAttendedLecturesList(attendedLecturesRecord, pageModel));
	}
	
	
	/**
	 *public AttendedLecturesRecord(int id,CourseSelection courseSelection,Supervisor supervisor,
			String teachingSection,int actualNum,int lateNum,int leavingEarlyNum,
			Date date,String weekly,String week,String jieci,
			List<AttendedLecturesSituation> attendedLecturesSituationList,
			int totalScore,char evaluationLevel,String experimentalProcess,String ALRP,
			String discussingOrImproving,String type) 
	 */
	@Test
	public void testInsertAttendedLectures() {
		CourseSelection courseSelection=new CourseSelection();
		Supervisor supervisor=new Supervisor();
		Clazz clazz=new Clazz();
		List<AttendedLecturesSituation> attendedLecturesSituationList=new ArrayList<AttendedLecturesSituation>();
		
		courseSelection.setId("(2020-2021-1)-0010000015-1010005-4");
		supervisor.setId("0000258");
		clazz.setId("2017010138");
		AttendedLecturesSituation attendedLecturesSituation=new AttendedLecturesSituation(new AttendedLecturesItems(1, "", 0),4,"");
		attendedLecturesSituationList.add(attendedLecturesSituation);
		
		AttendedLecturesRecord attendedLecturesRecord=new AttendedLecturesRecord(0,courseSelection,supervisor,"",clazz,20,3,1,
				new Date(),17,"一","3-4",attendedLecturesSituationList,90,'A',"","","","新进教师");
		
		attendedLecturesService.addAttendedLectures(attendedLecturesRecord);
		
		//查询全部记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(AttendedLecturesRecord attendedLecturesRecord2:attendedLecturesService.findAttendedLecturesList(new AttendedLecturesRecord(), pageModel))
			System.out.println(attendedLecturesRecord2);
	}
	
	
	@Test
	public void testModifyAttendedLectures() throws ParseException {
		//获取第一条记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		AttendedLecturesRecord attendedLecturesRecord=attendedLecturesService.findAttendedLecturesList(new AttendedLecturesRecord(), pageModel).get(0);
		
		attendedLecturesRecord.setDate(Utils.DATEFORMAT.format(new Date()));
		attendedLecturesService.modifyAttendedLectures(attendedLecturesRecord);
		
		//查询全部记录
		for(AttendedLecturesRecord attendedLecturesRecord2:attendedLecturesService.findAttendedLecturesList(new AttendedLecturesRecord(), pageModel))
			System.out.println(attendedLecturesRecord2);
	}
	
	
	@Test
	public void testRemoveAttendedLectures() {
		attendedLecturesService.removeAttendedLectures(1);
		
		//查询全部记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(AttendedLecturesRecord attendedLecturesRecord2:attendedLecturesService.findAttendedLecturesList(new AttendedLecturesRecord(), pageModel))
			System.out.println(attendedLecturesRecord2);
	}

}
