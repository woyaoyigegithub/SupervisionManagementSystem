package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.domain.DailyInspectionSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class DailyInspectionSituationDaoTest extends BaseDaoTest {

	@Autowired
	private DailyInspectionSituationDao dailyInspectionSituationDao;
	
	
	@Test
	public void testSelectDailyInspectionSituationListByDailyInspectionRecordId() {
		List<DailyInspectionSituation> dailyInspectionSituationList=dailyInspectionSituationDao.selectDailyInspectionSituationListByDailyInspectionRecordId(1);
		System.out.println(dailyInspectionSituationList);
		assertNotNull(dailyInspectionSituationList);
	}
	
	
	@Test
	public void testInsertDailyInspectionSituation() {
		DailyInspectionSituation dailyInspectionSituation=new DailyInspectionSituation(new DailyInspectionItems(1,""), "大情况", "小建议");
		dailyInspectionSituationDao.insertDailyInspectionSituation(1, dailyInspectionSituation);
		System.out.println(dailyInspectionSituationDao.selectDailyInspectionSituationListByDailyInspectionRecordId(1));
	}
	
	
	@Test
	public void testUpdateDailyInspectionSituation() {
		DailyInspectionSituation dailyInspectionSituation=new DailyInspectionSituation(new DailyInspectionItems(1,""), "大情况", "小建议");
		dailyInspectionSituationDao.updateDailyInspectionSituation(1, dailyInspectionSituation);
		System.out.println(dailyInspectionSituationDao.selectDailyInspectionSituationListByDailyInspectionRecordId(1));
	}
	
	
	@Test
	public void testDeleteDailyInspectionSituation() {
		dailyInspectionSituationDao.deleteDailyInspectionSituation(1);
		System.out.println(dailyInspectionSituationDao.selectDailyInspectionSituationListByDailyInspectionRecordId(1));
	}

}
