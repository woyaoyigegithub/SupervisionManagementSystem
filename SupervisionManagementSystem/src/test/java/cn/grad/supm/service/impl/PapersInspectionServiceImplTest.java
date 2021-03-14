package cn.grad.supm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.domain.PapersInspectionSituation;
import cn.grad.supm.domain.Student;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.service.PapersInspectionService;
import cn.grad.supm.utils.BaseServiceImplTest;
import cn.grad.supm.utils.tag.PageModel;

public class PapersInspectionServiceImplTest extends BaseServiceImplTest{

	@Autowired
	private PapersInspectionService papersInspectionService;
	
	
	@Test
	public void testfindPapersInspectionList() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		
		PapersInspectionRecord papersInspectionRecord=new PapersInspectionRecord();
		System.out.println(papersInspectionService.findPapersInspectionList(papersInspectionRecord, pageModel));
	}
	
	
	@Test
	public void testInsertPapersInspection() {
		Student student=new Student();
		student.setId("201701010101");
		Teacher instructor=new Teacher();
		instructor.setId("0000116");
		Supervisor consultTeacher=new Supervisor();
		consultTeacher.setId("0000082");
		
		List<PapersInspectionSituation> papersInspectionSituationList=new ArrayList<PapersInspectionSituation>();
		PapersInspectionSituation papersInspectionSituation=new PapersInspectionSituation(new PapersInspectionItems(1,"",""), "好的", "无备注");
		papersInspectionSituationList.add(papersInspectionSituation);
		
		PapersInspectionRecord papersInspectionRecord=new PapersInspectionRecord(0, student, instructor, consultTeacher, papersInspectionSituationList,"","查阅教师很高兴");
		
		papersInspectionService.addPapersInspection(papersInspectionRecord);
		
		//查询记录
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		for(PapersInspectionRecord papersInspectionRecord2:papersInspectionService.findPapersInspectionList(new PapersInspectionRecord(), pageModel)){
			System.out.println(papersInspectionRecord2);
		}
	}

	
	@Test
	public void testModifyPapersInspection() {
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		PapersInspectionRecord papersInspectionRecord=papersInspectionService.findPapersInspectionList(new PapersInspectionRecord(), pageModel).get(0);
		papersInspectionRecord.setConsultTeacher(new Supervisor("0000109", "", ""));
		
		papersInspectionService.modifyPapersInspection(papersInspectionRecord);
		
		//查询记录
		for(PapersInspectionRecord papersInspectionRecord2:papersInspectionService.findPapersInspectionList(new PapersInspectionRecord(), pageModel)){
			System.out.println(papersInspectionRecord2);
		}
	}
	
	
	@Test
	public void testRemovePapersInspection() {
		papersInspectionService.removePapersInspection(1);
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		//查询记录
		for(PapersInspectionRecord papersInspectionRecord2:papersInspectionService.findPapersInspectionList(new PapersInspectionRecord(), pageModel)){
			System.out.println(papersInspectionRecord2);
		}
	}
	
}
