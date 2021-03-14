package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.CourseSelectionDao;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.service.CourseSelectionService;
import cn.grad.supm.utils.tag.PageModel;

@Service("courseSelectionService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CourseSelectionServiceImpl implements CourseSelectionService {

	@Autowired
	private CourseSelectionDao courseSelectionDao; 

	@Override
	public List<CourseSelection> findCourseSelectionList(CourseSelection courseSelection,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("courseSelection", courseSelection);
		
		int recordCount=courseSelectionDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<CourseSelection> courseSelectionList=courseSelectionDao.selectCourseSelectionListByPage(params);
		return courseSelectionList;
	}
	

	@Override
	public void addCourseSelection(CourseSelection courseSelection) {
		courseSelectionDao.insertCourseSelection(courseSelection);
	}
	
	
	@Override
	public void modifyCourseSelection(CourseSelection courseSelection) {
		courseSelectionDao.updateCourseSelection(courseSelection);
	}

	@Override
	public void removeCourseSelection(String id) {
		courseSelectionDao.deleteCourseSelection(id);
	}
	
	
}
