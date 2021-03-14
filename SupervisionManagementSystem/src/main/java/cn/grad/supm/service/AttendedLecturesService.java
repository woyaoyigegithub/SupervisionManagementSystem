package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.utils.tag.PageModel;


public interface AttendedLecturesService extends Statistics {

	public List<AttendedLecturesRecord> findAttendedLecturesList(AttendedLecturesRecord attendedLecturesRecord,PageModel pageModel);
	
	
	public void addAttendedLectures(AttendedLecturesRecord attendedLecturesRecord);
	
	
	public void modifyAttendedLectures(AttendedLecturesRecord attendedLecturesRecord);
	
	
	public void removeAttendedLectures(int id);
	
}
