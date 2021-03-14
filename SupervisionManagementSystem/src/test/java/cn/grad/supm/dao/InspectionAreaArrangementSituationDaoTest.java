package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.utils.BaseDaoTest;

public class InspectionAreaArrangementSituationDaoTest extends BaseDaoTest {

	@Autowired
	private InspectionAreaArrangementSituationDao inspectionAreaArrangementSituationDao;
	private Supervisor supervisor=new Supervisor("1000006","","");
	private InspectionAreaArrangementSituation inspectionAreaArrangementSituation=new InspectionAreaArrangementSituation(0, supervisor, "周五", "晚上");
	
	@Test
	public void testSelectInsepctionAreaArrangementSituationById() {
		long start=System.currentTimeMillis();
		InspectionAreaArrangementSituation inspectionAreaArrangementSituation=inspectionAreaArrangementSituationDao.selectInspectionAreaArrangementSituationById(1);
		long end=System.currentTimeMillis();
		System.out.println("cost time : "+(end-start));
		System.out.println(inspectionAreaArrangementSituation);
	}
	
	
	@Test
	public void testUpdateInsepctionAreaArrangementSituation() {
		inspectionAreaArrangementSituationDao.InsertInspectionAreaArrangementSituation(4, inspectionAreaArrangementSituation);
		inspectionAreaArrangementSituation.setWeek("周六");
		inspectionAreaArrangementSituationDao.updateInspectionAreaArrangementSituation(inspectionAreaArrangementSituation);
		
		InspectionAreaArrangementSituation inspectionAreaArrangementSituation1=inspectionAreaArrangementSituationDao.selectInspectionAreaArrangementSituationById(inspectionAreaArrangementSituation.getId());
		System.out.println(inspectionAreaArrangementSituation1);
		assertNotNull(inspectionAreaArrangementSituation1);
		
		List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList=inspectionAreaArrangementSituationDao.selectInspectionAreaArrangementSituationListByInspectionAreaArrangementBuildingId(4);
		System.out.println(inspectionAreaArrangementSituationList);
		assertThat(inspectionAreaArrangementSituationList.size(),not(0));
		
	}
	
	@Test
	public void testDeleteInsepctionAreaArrangementSituation() {
		inspectionAreaArrangementSituationDao.deleteInsepctionAreaArrangementSituation(40);
		assertNull(inspectionAreaArrangementSituationDao.selectInspectionAreaArrangementSituationById(40));
	}

}
