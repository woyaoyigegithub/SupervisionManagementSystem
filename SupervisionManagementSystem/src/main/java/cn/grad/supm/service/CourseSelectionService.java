package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.utils.tag.PageModel;

public interface CourseSelectionService {

	public List<CourseSelection> findCourseSelectionList(CourseSelection courseSelection,PageModel pageModel);
	
	
	public void addCourseSelection(CourseSelection courseSelection);
	
	
	public void modifyCourseSelection(CourseSelection courseSelection);
	
	
	public void removeCourseSelection(String id);
	
	
	
}
