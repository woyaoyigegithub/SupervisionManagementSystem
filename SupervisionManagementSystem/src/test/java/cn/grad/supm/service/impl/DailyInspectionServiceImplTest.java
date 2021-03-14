package cn.grad.supm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.domain.DailyInspectionSituation;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.domain.LeaveClassAheadOfTimeSituation;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.service.DailyInspectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class DailyInspectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private DailyInspectionService dailyInspectionService;
	
	
	@Test
	public void testfindDailyInspectionList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		DailyInspectionRecord dailyInspectionRecord=new DailyInspectionRecord();
		
		System.out.println(dailyInspectionService.findDailyInspectionList(dailyInspectionRecord, pageModel));
	}
	
	
	/**
	 *public DailyInspectionRecord(int id, int weekly, InspectionAreaArrangementSituation inspectionAreaArrangementSituation, String week, Date date,
			List<DailyInspectionSituation> dailyInspectionSituationList,
			List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList)
	 */
	@Test
	public void testInsertDailyInspection() {
		//初始化日常巡查记录参数
		InspectionAreaArrangementSituation inspectionAreaArrangementSituation=new InspectionAreaArrangementSituation();
		inspectionAreaArrangementSituation.setId(4);
		
		
		List<DailyInspectionSituation> dailyInspectionSituationList=new ArrayList<DailyInspectionSituation>();
		DailyInspectionSituation dailyInspectionSituation=new DailyInspectionSituation(new DailyInspectionItems(1, ""),"情况","建议");
		dailyInspectionSituationList.add(dailyInspectionSituation);
		
		
		List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList=new ArrayList<LeaveClassAheadOfTimeSituation>();
		CourseSelection courseSelection=new CourseSelection();
		Clazz clazz=new Clazz();
		courseSelection.setId("(2020-2021-1)-1402145001-0001359-2");
		clazz.setId("2020010151");
		LeaveClassAheadOfTimeSituation leaveClassAheadOfTimeSituation=new LeaveClassAheadOfTimeSituation(courseSelection, clazz, "6:00", "5:30");
		leaveClassAheadOfTimeSituationList.add(leaveClassAheadOfTimeSituation);
		
		DailyInspectionRecord dailyInspectionRecord=new DailyInspectionRecord(0,14,inspectionAreaArrangementSituation,"二",new Date(),dailyInspectionSituationList,leaveClassAheadOfTimeSituationList);
		
		//插入记录
		dailyInspectionService.addDailyInspection(dailyInspectionRecord);
		
		//查询全部记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(DailyInspectionRecord dailyInspectionRecord2:dailyInspectionService.findDailyInspectionList(new DailyInspectionRecord(), pageModel))
			System.out.println(dailyInspectionRecord2);
	}
	
	
	@Test
	public void testModifyDailyInspection() {
		//获取第一条记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		DailyInspectionRecord dailyInspectionRecord=dailyInspectionService.findDailyInspectionList(new DailyInspectionRecord(), pageModel).get(0);
		
		dailyInspectionRecord.setDate("2021-10-5");
		dailyInspectionService.modifyDailyInspection(dailyInspectionRecord);
		
		//查询全部记录
		for(DailyInspectionRecord dailyInspectionRecord2:dailyInspectionService.findDailyInspectionList(new DailyInspectionRecord(), pageModel)) {
			System.out.println(dailyInspectionRecord2.getLeaveClassAheadOfTimeSituationList().size());
		}
	}
	
	
	@Test
	public void testRemoveDailyInspection() {
		dailyInspectionService.removeDailyInspection(1);
		
		//查询全部记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(DailyInspectionRecord dailyInspectionRecord2:dailyInspectionService.findDailyInspectionList(new DailyInspectionRecord(), pageModel))
			System.out.println(dailyInspectionRecord2);
	}

}
