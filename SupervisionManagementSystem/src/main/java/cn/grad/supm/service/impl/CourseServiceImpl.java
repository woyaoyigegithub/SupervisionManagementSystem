package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.CourseDao;
import cn.grad.supm.domain.Course;
import cn.grad.supm.service.CourseService;
import cn.grad.supm.utils.tag.PageModel;

@Service("courseService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao; 

	
	@Override
	public List<Course> findCourseList(Course course,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("course", course);
		
		int recordCount=courseDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Course> courseList=courseDao.selectCourseListByPage(params);
		return courseList;
	}

	@Override
	public void addCourse(Course course) {
		courseDao.insertCourse(course);
	}
	
	
	@Override
	public void modifyCourse(Course course) {
		courseDao.updateCourse(course);
	}

	@Override
	public void removeCourse(String id) {
		courseDao.deleteCourse(id);
	}
	
	
}
