package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.InspectionAreaArrangementBuilding;
import cn.grad.supm.utils.BaseDaoTest;

public class InspectionAreaArrangementBuildingDaoTest extends BaseDaoTest {

	@Autowired
	private InspectionAreaArrangementBuildingDao inspectionAreaArrangementBuildingDao;
	private InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding = new InspectionAreaArrangementBuilding(5, "明天楼", 1, null);
	
	
	@Test 
	public void testSelectInspectionAreaArrangementBuildingListById() {
		System.out.println(inspectionAreaArrangementBuildingDao.selectInspectionAreaArrangementBuildingById(1));
	}
	
	@Test
	public void testUpdateInspectionAreaArrangementBuilding() {
		inspectionAreaArrangementBuildingDao.insertInspectionAreaArrangementBuilding(1,inspectionAreaArrangementBuilding);
		inspectionAreaArrangementBuilding.setInspectorsNum(2);
		inspectionAreaArrangementBuildingDao.updateInspectionAreaArrangementBuilding(inspectionAreaArrangementBuilding);
		
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList=inspectionAreaArrangementBuildingDao.selectInspectionAreaArrangementBuildingListByInspectionAreaArrangementId(1);
		System.out.println(inspectionAreaArrangementBuildingList);
		assertThat(inspectionAreaArrangementBuildingList.size(),not(4));
	}
	
	@Test
	public void testDeleteInspectionAreaArrangementBuilding() {
		inspectionAreaArrangementBuildingDao.insertInspectionAreaArrangementBuilding(1,inspectionAreaArrangementBuilding);
		
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList=inspectionAreaArrangementBuildingDao.selectInspectionAreaArrangementBuildingListByInspectionAreaArrangementId(1);
		System.out.println(inspectionAreaArrangementBuildingList);
		assertNotNull(inspectionAreaArrangementBuildingList);
		
		inspectionAreaArrangementBuildingDao.deleteInspectionAreaArrangementBuilding(inspectionAreaArrangementBuilding.getId());
		
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList2=inspectionAreaArrangementBuildingDao.selectInspectionAreaArrangementBuildingListByInspectionAreaArrangementId(1);
		System.out.println(inspectionAreaArrangementBuildingList2);
		assertThat(inspectionAreaArrangementBuildingList2.size(),is(4));
	}

}
