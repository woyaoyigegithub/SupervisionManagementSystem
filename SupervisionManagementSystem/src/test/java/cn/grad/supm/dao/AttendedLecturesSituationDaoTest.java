package cn.grad.supm.dao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.AttendedLecturesSituation;
import cn.grad.supm.utils.BaseDaoTest;

public class AttendedLecturesSituationDaoTest extends BaseDaoTest {

	@Autowired
	private AttendedLecturesSituationDao attendedLecturesSituationDao;
	
	@Test
	public void testSelectAllAttendedLecturesSituationByAttendedLecturesRecordId() {
		List<AttendedLecturesSituation> attendedLecturesSituationList=attendedLecturesSituationDao.selectAttendedLecturesSituationListByAttendedLecturesRecordId(1);
		System.out.println(attendedLecturesSituationList);
		assertThat(0, not(attendedLecturesSituationList.size()));
	}
	
	
	@Test
	public void testiInsertAttendedLecturesSituation() {
		AttendedLecturesSituation attendedLecturesSituation=new AttendedLecturesSituation(new AttendedLecturesItems(1, "", 0), 22, "");
		attendedLecturesSituationDao.insertAttendedLecturesSituation(1, attendedLecturesSituation);
		System.out.println(attendedLecturesSituationDao.selectAttendedLecturesSituationListByAttendedLecturesRecordId(1));
	}
	
	
	@Test
	public void testUpdateAttendedLectureSituation() {
		List<AttendedLecturesSituation> attendedLecturesSituationList=attendedLecturesSituationDao.selectAttendedLecturesSituationListByAttendedLecturesRecordId(1);
		attendedLecturesSituationList.get(0).setEvaluationScore(0);
		attendedLecturesSituationDao.updateAttendedLecturesSituation(1, attendedLecturesSituationList.get(0));
		System.out.println(attendedLecturesSituationDao.selectAttendedLecturesSituationListByAttendedLecturesRecordId(1));
	}
	
	@Test
	public void testDeleteAttendedLectureSituation() {
		attendedLecturesSituationDao.deleteAttendedLecturesSituation(1);
		System.out.println(attendedLecturesSituationDao.selectAttendedLecturesSituationListByAttendedLecturesRecordId(1));
	}
}
