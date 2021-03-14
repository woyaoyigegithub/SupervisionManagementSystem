package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Course;
import cn.grad.supm.utils.tag.PageModel;

public interface CourseService {

	public List<Course> findCourseList(Course course,PageModel pageModel);
	
	
	public void addCourse(Course course);
	
	
	public void modifyCourse(Course course);
	
	
	public void removeCourse(String id);
	
}
