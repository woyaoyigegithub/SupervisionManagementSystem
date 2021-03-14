package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class InspectionAreaArrangementDaoTest extends BaseDaoTest {

	@Autowired
	private InspectionAreaArrangementDao inspectionAreaArrangementDao;
	private InspectionAreaArrangement inspectionAreaArrangement = new InspectionAreaArrangement(0, "2020-2021", 1, "2-16", new Date(),null);
	
	
	@Test
	public void testSelectAllInspectionAreaArrangement() {
		long start=System.currentTimeMillis();
		List<InspectionAreaArrangement> inspectionAreaArrangementList=inspectionAreaArrangementDao.selectAllInspectionAreaArrangement();
		long end=System.currentTimeMillis();
		System.out.println("cost time : "+(end-start));
		System.out.println(inspectionAreaArrangementList);
		assertThat(inspectionAreaArrangementList.size(), not(0));
	}
	
	@Test
	public void testSelectInspectionAreaArrangementListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		InspectionAreaArrangement inspectionAreaArrangement=new InspectionAreaArrangement();
		inspectionAreaArrangement.setSchoolYear("2020-2021");
		params.put("inspectionAreaArrangement", inspectionAreaArrangement);
		params.put("pageModel", pageModel);
		pageModel.setRecordCount(inspectionAreaArrangementDao.count(params));
		List<InspectionAreaArrangement> inspectionAreaArrangementList=inspectionAreaArrangementDao.selectInspectionAreaArrangementListByPage(params);
		System.out.println(inspectionAreaArrangementList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(inspectionAreaArrangementList.size()));
	}
	
	
	
//	@Test
//	public void testAddInspectionAreaArrangement() {
//		inspectionAreaArrangementDao.insertInspectionAreaArrangement(inspectionAreaArrangement);
//		
//		InspectionAreaArrangement inspectionAreaArrangement=inspectionAreaArrangementDao.selectInspectionAreaArrangementById(inspectionAreaArrangement.getId());
//		System.out.println(inspectionAreaArrangement);
//		assertNotNull(inspectionAreaArrangement);
//	}
	
	@Test
	public void testUpdateInspectionAreaArrangement() {
		inspectionAreaArrangementDao.insertInspectionAreaArrangement(inspectionAreaArrangement);
		inspectionAreaArrangement.setSemester(2);
		inspectionAreaArrangementDao.updateInspectionAreaArrangement(inspectionAreaArrangement);
		
		InspectionAreaArrangement inspectionAreaArrangement1=inspectionAreaArrangementDao.selectInspectionAreaArrangementById(inspectionAreaArrangement.getId());
		System.out.println(inspectionAreaArrangement1);
		assertNotNull(inspectionAreaArrangement1);
	}
	
	@Test
	public void testDeleteInspectionAreaArrangement() {
		inspectionAreaArrangementDao.insertInspectionAreaArrangement(inspectionAreaArrangement);
		
		InspectionAreaArrangement inspectionAreaArrangement1=inspectionAreaArrangementDao.selectInspectionAreaArrangementById(inspectionAreaArrangement.getId());
		System.out.println(inspectionAreaArrangement1);
		assertNotNull(inspectionAreaArrangement1);
		
		inspectionAreaArrangementDao.deleteInspectionAreaArrangement(inspectionAreaArrangement.getId());
		
		InspectionAreaArrangement inspectionAreaArrangement2=inspectionAreaArrangementDao.selectInspectionAreaArrangementById(inspectionAreaArrangement.getId());
		System.out.println(inspectionAreaArrangement2);
		assertNull(inspectionAreaArrangement2);
	}
	
	
	@Test
	public void testSelectLastInspectionAreaArrangement() {
		System.out.println(inspectionAreaArrangementDao.selectLastInspectionAreaArrangement());
	}

}
