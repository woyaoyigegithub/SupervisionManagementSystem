package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.AttendedLecturesRecordDao;
import cn.grad.supm.dao.AttendedLecturesSituationDao;
import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.domain.AttendedLecturesSituation;
import cn.grad.supm.service.AttendedLecturesService;
import cn.grad.supm.utils.tag.PageModel;

@Service("attendedLecturesService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AttendedLecturesServiceImpl implements AttendedLecturesService {

	@Autowired
	private AttendedLecturesRecordDao attendedLecturesRecordDao; 
	@Autowired
	private AttendedLecturesSituationDao attendedLecturesSituationDao; 

	
	@Override
	public List<AttendedLecturesRecord> findAttendedLecturesList(AttendedLecturesRecord attendedLecturesRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("attendedLecturesRecord", attendedLecturesRecord);
		
		int recordCount=attendedLecturesRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<AttendedLecturesRecord> attendedLecturesRecordList=attendedLecturesRecordDao.selectAttendedLecturesRecordListByPage(params);
		return attendedLecturesRecordList;
	}

	
	@Override
	public void addAttendedLectures(AttendedLecturesRecord attendedLecturesRecord) {
		attendedLecturesRecordDao.insertAttendedLecturesRecord(attendedLecturesRecord);
		List<AttendedLecturesSituation> attendedLecturesSituationList=attendedLecturesRecord.getAttendedLecturesSituationList();
		for(AttendedLecturesSituation attendedLecturesSituation:attendedLecturesSituationList) {
			attendedLecturesSituationDao.insertAttendedLecturesSituation(attendedLecturesRecord.getId(), attendedLecturesSituation);
		}
	}
	
	
	@Override
	public void modifyAttendedLectures(AttendedLecturesRecord attendedLecturesRecord) {
		attendedLecturesRecordDao.updateAttendedLecturesRecord(attendedLecturesRecord);
		List<AttendedLecturesSituation> attendedLecturesSituationList=attendedLecturesRecord.getAttendedLecturesSituationList();
		for(AttendedLecturesSituation attendedLecturesSituation:attendedLecturesSituationList) {
			attendedLecturesSituationDao.updateAttendedLecturesSituation(attendedLecturesRecord.getId(), attendedLecturesSituation);
		}
	}

	
	@Override
	public void removeAttendedLectures(int id) {
		attendedLecturesSituationDao.deleteAttendedLecturesSituation(id);
		attendedLecturesRecordDao.deleteAttendedLecturesRecord(id);
	}


	@Override
	public List<Map<String, String>> countBySchoolYearAndSemester(String supervisorId) {
		return attendedLecturesRecordDao.countBySchoolYearAndSemester(supervisorId);
	}
	
	
}
