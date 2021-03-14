package cn.grad.supm.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.domain.Student;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class PapersInspectionRecordDaoTest extends BaseDaoTest {

	@Autowired
	private PapersInspectionRecordDao papersInspectionRecordDao;
	
	
	@Test
	public void testSelectAllPapersInspectionRecord() {
		List<PapersInspectionRecord> papersInspectionRecordList=papersInspectionRecordDao.selectAllPapersInspectionRecord();
		System.out.println(papersInspectionRecordList);
		assertThat(0,not(papersInspectionRecordList.size()));
	}
	
	
	
	@Test
	public void testSelectPapersInspectionRecordById() {
		PapersInspectionRecord papersInspectionRecord=papersInspectionRecordDao.selectPapersInspectionRecordById(1);
		System.out.println(papersInspectionRecord);
		assertNotNull(papersInspectionRecord);
	}
	
	
	@Test
	public void testSelectPapersInspectionRecordByPage() {
		PapersInspectionRecord papersInspectionRecord=new PapersInspectionRecord();
		papersInspectionRecord.setStudent(new Student("201701010101","","",null));
		
		Map<String,Object> params=new HashMap<String,Object>();
		PageModel pageModel=new PageModel();
		params.put("papersInspectionRecord", papersInspectionRecord);
		params.put("pageModel", pageModel);
		pageModel.setPageIndex(1);
		int count=papersInspectionRecordDao.count(params);
		pageModel.setRecordCount(count);
		if(count>0) {
			System.out.println(papersInspectionRecordDao.selectPapersInspectionRecordListByPage(params));
		}
	}
	
	
	@Test
	public void testInsertPapersInspectionRecord() {
		PapersInspectionRecord papersInspectionRecord=new PapersInspectionRecord(2,new Student("201701010101","","",null),new Teacher("0000047","","",""),new Supervisor("0000077","",""),null,"","都很好");
		papersInspectionRecordDao.insertPapersInspectionRecord(papersInspectionRecord);
		System.out.println(papersInspectionRecordDao.selectPapersInspectionRecordById(papersInspectionRecord.getId()));
	}
	
	
	@Test
	public void testUpdatePapersInspectionRecord() {
		PapersInspectionRecord papersInspectionRecord=papersInspectionRecordDao.selectPapersInspectionRecordById(1);
		papersInspectionRecord.setConsultTeacher(new Supervisor("0000109", "", ""));
		papersInspectionRecordDao.updatePapersInspectionRecord(papersInspectionRecord);
		System.out.println(papersInspectionRecordDao.selectPapersInspectionRecordById(1));
	}
	
	
	@Test
	public void testdeletePapersInspectionRecord() {
		papersInspectionRecordDao.deletePapersInspectionRecord(1);
		System.out.println(papersInspectionRecordDao.selectPapersInspectionRecordById(1));
	}
	
}
