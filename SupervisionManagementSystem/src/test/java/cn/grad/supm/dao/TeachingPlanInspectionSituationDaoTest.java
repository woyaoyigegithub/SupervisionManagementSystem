package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.domain.TeachingPlanInspectionSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class TeachingPlanInspectionSituationDaoTest extends BaseDaoTest {

	@Autowired
	private TeachingPlanInspectionSituationDao teachingPlanInspectionSituationDao;
	
	
	@Test
	public void testSelectTeachingPlanInspectionSituationListById() {
		List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList=teachingPlanInspectionSituationDao.selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(1);
		System.out.println(teachingPlanInspectionSituationList);
		assertNotNull(teachingPlanInspectionSituationList);
	}
	
	
	@Test
	public void testInsertTeachingPlanInspectionSituation() {
		TeachingPlanInspectionSituation teachingPlanInspectionSituation=new TeachingPlanInspectionSituation(new TeachingPlanInspectionItems(1, "",""), "11");
		teachingPlanInspectionSituationDao.insertTeachingPlanInspectionSituation(1,teachingPlanInspectionSituation);
		System.out.println(teachingPlanInspectionSituationDao.selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(1));
	}
	
	
	@Test
	public void testUpdateTeachingPlanInspectionSituation() {
		TeachingPlanInspectionSituation teachingPlanInspectionSituation=teachingPlanInspectionSituationDao.selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(1).get(0);
		teachingPlanInspectionSituation.setSituation("好大情况1");
		teachingPlanInspectionSituationDao.updateTeachingPlanInspectionSituation(1, teachingPlanInspectionSituation);
		System.out.println(teachingPlanInspectionSituationDao.selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(1));
	}
	
	
	@Test
	public void testDeleteTeachingPlanInspectionSituation() {
		teachingPlanInspectionSituationDao.deleteTeachingPlanInspectionSituation(1);
		System.out.println(teachingPlanInspectionSituationDao.selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(1));
	}
	
	
}
