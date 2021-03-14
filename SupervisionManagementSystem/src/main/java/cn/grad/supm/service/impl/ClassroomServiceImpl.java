package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.ClassroomDao;
import cn.grad.supm.domain.Classroom;
import cn.grad.supm.service.ClassroomService;
import cn.grad.supm.utils.tag.PageModel;

@Service("classroomService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassroomDao classroomDao; 

	
	@Override
	public List<Classroom> findClassroomList(Classroom classroom,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("classroom", classroom);
		
		int recordCount=classroomDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Classroom> classroomList=classroomDao.selectClassroomListByPage(params);
		return classroomList;
	}

	@Override
	public void addClassroom(Classroom classroom) {
		classroomDao.insertClassroom(classroom);
	}
	
	
	@Override
	public void modifyClassroom(Classroom classroom) {
		classroomDao.updateClassroom(classroom);
	}

	@Override
	public void removeClassroom(String id) {
		classroomDao.deleteClassroom(id);
	}

	
	
}
