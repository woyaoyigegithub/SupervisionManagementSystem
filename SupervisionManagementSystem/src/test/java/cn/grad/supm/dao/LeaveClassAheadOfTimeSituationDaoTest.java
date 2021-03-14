package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.LeaveClassAheadOfTimeSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class LeaveClassAheadOfTimeSituationDaoTest extends BaseDaoTest {

	@Autowired
	private LeaveClassAheadOfTimeSituationDao leaveClassAheadOfTimeSituationDao;
	
	
	@Test
	public void testSelectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId() {
		List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList=leaveClassAheadOfTimeSituationDao.selectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId(1);
		System.out.println(leaveClassAheadOfTimeSituationList);
		assertNotNull(leaveClassAheadOfTimeSituationList);
	}
	
	
	@Test
	public void testInsertLeaveClassAheadOfTimeSituation() {
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId("(2020-2021-1)-0010000008-0002174-5");
		LeaveClassAheadOfTimeSituation leaveClassAheadOfTimeSituation=new LeaveClassAheadOfTimeSituation(courseSelection,null,"11:20","11:00");
		leaveClassAheadOfTimeSituationDao.insertLeaveClassAheadOfTimeSituation(1, leaveClassAheadOfTimeSituation);
		System.out.println(leaveClassAheadOfTimeSituationDao.selectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId(1));
	}
	
	
	@Test
	public void testDeleteLeaveClassAheadOfTimeSituation() {
		leaveClassAheadOfTimeSituationDao.deleteLeaveClassAheadOfTimeSituation(1);
		System.out.println(leaveClassAheadOfTimeSituationDao.selectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId(1));
	}

}
