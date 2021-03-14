package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.domain.TourInspectionSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class TourInspectionSituationDaoTest extends BaseDaoTest {

	@Autowired
	private TourInspectionSituationDao tourInspectionSituationDao;
	
	
	@Test
	public void testSelectTourInspectionSituationListById() {
		List<TourInspectionSituation> tourInspectionSituationList=tourInspectionSituationDao.selectTourInspectionSituationListByTourInspectionRecordId(1);
		System.out.println(tourInspectionSituationList);
		assertNotNull(tourInspectionSituationList);
	}
	
	
	@Test
	public void testInsertTourInspectionSituation() {
		TourInspectionSituation tourInspectionSituation=new TourInspectionSituation(new TourInspectionItems(1, ""), "11");
		tourInspectionSituationDao.insertTourInspectionSituation(1, tourInspectionSituation);
		System.out.println(tourInspectionSituationDao.selectTourInspectionSituationListByTourInspectionRecordId(1));
	}
	
	
	@Test
	public void testUpdateTourInspectionSituation() {
		TourInspectionSituation tourInspectionSituation=tourInspectionSituationDao.selectTourInspectionSituationListByTourInspectionRecordId(1).get(0);
		tourInspectionSituation.setSituation("好大情况1");
		tourInspectionSituationDao.updateTourInspectionSituation(1, tourInspectionSituation);
		System.out.println(tourInspectionSituationDao.selectTourInspectionSituationListByTourInspectionRecordId(1));
	}
	
	
	@Test
	public void testDeleteTourInspectionSituation() {
		tourInspectionSituationDao.deleteTourInspectionSituation(1);
		System.out.println(tourInspectionSituationDao.selectTourInspectionSituationListByTourInspectionRecordId(1));
	}
	
	
}
