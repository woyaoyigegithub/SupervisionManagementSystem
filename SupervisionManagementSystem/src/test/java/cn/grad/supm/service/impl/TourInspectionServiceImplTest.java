package cn.grad.supm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.domain.TourInspectionRecord;
import cn.grad.supm.domain.TourInspectionSituation;
import cn.grad.supm.service.TourInspectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class TourInspectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private TourInspectionService tourInspectionService;
	
	
	@Test
	public void testfindTourInspectionList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		TourInspectionRecord tourInspectionRecord=new TourInspectionRecord();
		System.out.println(tourInspectionService.findTourInspectionList(tourInspectionRecord, pageModel));
	}
	
	
	
	@Test
	public void testInsertTourInspection() {
		List<TourInspectionSituation> tourInspectionSituationList=new ArrayList<TourInspectionSituation>();
		TourInspectionSituation tourInspectionSituation=new TourInspectionSituation(new TourInspectionItems(1, ""), "情况");
		tourInspectionSituationList.add(tourInspectionSituation);
		
		TourInspectionRecord tourInspectionRecord=new TourInspectionRecord(0, new Date(), 14, 16, "", new Supervisor("0000109","",""), tourInspectionSituationList);
		
		tourInspectionService.addTourInspection(tourInspectionRecord);
		
		//查询记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		System.out.println(tourInspectionService.findTourInspectionList(new TourInspectionRecord(), pageModel));
	}
	
	
	@Test
	public void testModifyTourInspection() {
		//查询记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		TourInspectionRecord tourInspectionRecord=tourInspectionService.findTourInspectionList(new TourInspectionRecord(), pageModel).get(0);
		tourInspectionRecord.setStartingTime(11);
		tourInspectionRecord.setEndingTime(13);
		
		tourInspectionService.modifyTourInspection(tourInspectionRecord);
		
		System.out.println(tourInspectionService.findTourInspectionList(new TourInspectionRecord(), pageModel));
	}
	

	@Test
	public void testRemoveTourInspection() {
		tourInspectionService.removeTourInspection(1);
		
		//查询记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		System.out.println(tourInspectionService.findTourInspectionList(new TourInspectionRecord(), pageModel));
	}
	
}
