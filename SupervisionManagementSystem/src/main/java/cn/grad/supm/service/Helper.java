package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Staff;

public interface Helper {

	public CourseSelection findCourseSelectionById(String id);
	
	public List<Integer> findInspectionAreaArrangementSituationIdList(Staff staff);
}
