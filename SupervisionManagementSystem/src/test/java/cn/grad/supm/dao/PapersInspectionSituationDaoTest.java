package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.domain.PapersInspectionSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class PapersInspectionSituationDaoTest extends BaseDaoTest {

	@Autowired
	private PapersInspectionSituationDao papersInspectionSituationDao;
	
	
	@Test
	public void testSelectPapersInspectionSituationListByPapersInspectionRecordId() {
		List<PapersInspectionSituation> papersInspectionSituationList=papersInspectionSituationDao.selectPapersInspectionSituationListByPapersInspectionRecordId(1);
		System.out.println(papersInspectionSituationList);
		assertNotNull(papersInspectionSituationList);
	}
	
	
	@Test
	public void testInsertPapersInspectionSituation() {
		PapersInspectionSituation papersInspectionSituation=new PapersInspectionSituation(new  PapersInspectionItems(1, "",""), "11","");
		papersInspectionSituationDao.insertPapersInspectionSituation(1, papersInspectionSituation);
	}
	
	
	@Test
	public void testUpdatePapersInspectionSituation() {
		PapersInspectionSituation papersInspectionSituation=papersInspectionSituationDao.selectPapersInspectionSituationListByPapersInspectionRecordId(1).get(0);
		papersInspectionSituation.setSituation("好大情况1");
		papersInspectionSituation.setRemarks("我有一个大备注");
		papersInspectionSituationDao.updatePapersInspectionSituation(1, papersInspectionSituation);
		System.out.println(papersInspectionSituationDao.selectPapersInspectionSituationListByPapersInspectionRecordId(1));
	}
	
	
	@Test
	public void testDeletePapersInspectionSituation() {
		papersInspectionSituationDao.deletePapersInspectionSituation(1);
		System.out.println(papersInspectionSituationDao.selectPapersInspectionSituationListByPapersInspectionRecordId(1));
	}
	
	
	
}
