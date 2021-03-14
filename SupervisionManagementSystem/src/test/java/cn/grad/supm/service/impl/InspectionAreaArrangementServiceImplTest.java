package cn.grad.supm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.domain.InspectionAreaArrangementBuilding;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.service.InspectionAreaArrangementService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class InspectionAreaArrangementServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private InspectionAreaArrangementService inspectionAreaArrangementService;
	
	
	@Test
	public void testFindInspectionAreaArrangementList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		InspectionAreaArrangement inspectionAreaArrangement=new InspectionAreaArrangement();
		inspectionAreaArrangement.setSemester(1);
		System.out.println(inspectionAreaArrangementService.findInspectionAreaArrangementList(inspectionAreaArrangement, pageModel));
	}
	
	
	@Test
	public void testAddInspectionAreaArrangement() {
		
		List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList=new ArrayList<InspectionAreaArrangementSituation>();
		InspectionAreaArrangementSituation inspectionAreaArrangementSituation=new InspectionAreaArrangementSituation();
		inspectionAreaArrangementSituationList.add(inspectionAreaArrangementSituation);
		
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList=new ArrayList<InspectionAreaArrangementBuilding>();
		InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding=new InspectionAreaArrangementBuilding(0,"梁山",1,inspectionAreaArrangementSituationList);
		inspectionAreaArrangementBuildingList.add(inspectionAreaArrangementBuilding);
		
		InspectionAreaArrangement inspectionAreaArrangement=new InspectionAreaArrangement(0,"2020-2021",2,"2-16",new Date(),inspectionAreaArrangementBuildingList);
		
		inspectionAreaArrangementService.addInspectionAreaArrangement(inspectionAreaArrangement);
		
		//查询记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(InspectionAreaArrangement inspectionAreaArrangement2:inspectionAreaArrangementService.findInspectionAreaArrangementList(new InspectionAreaArrangement(), pageModel)) {
			System.out.println(inspectionAreaArrangement2);
		}
	}
	
	
	@Test
	public void testModifyInspectionAreaArrangement() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		InspectionAreaArrangement inspectionAreaArrangement=inspectionAreaArrangementService.findInspectionAreaArrangementList(new InspectionAreaArrangement(), pageModel).get(0);
		inspectionAreaArrangement.setPublicationTime("2020-11-11");
		inspectionAreaArrangement.getInspectionAreaArrangementBuildingList().get(0).setInspectorsNum(2);
		inspectionAreaArrangement.getInspectionAreaArrangementBuildingList().get(0).getInspectionAreaArrangementSituationList().get(0).setWeek("周日");
		inspectionAreaArrangementService.modifyInspectionAreaArrangement(inspectionAreaArrangement);
		for(InspectionAreaArrangement inspectionAreaArrangement2:inspectionAreaArrangementService.findInspectionAreaArrangementList(new InspectionAreaArrangement(), pageModel)) {
			System.out.println(inspectionAreaArrangement2);
		}
	}
	
	
	@Test
	public void testRemoveInspectionAreaArrangement() {
		inspectionAreaArrangementService.removeInspectionAreaArrangement(1);
		
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(InspectionAreaArrangement inspectionAreaArrangement2:inspectionAreaArrangementService.findInspectionAreaArrangementList(new InspectionAreaArrangement(), pageModel)) {
			System.out.println(inspectionAreaArrangement2);
		}
	}
	
	

}
