package cn.grad.supm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.TeacherDao;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.service.TeacherService;


@Service("teacherService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao; 

	
	@Override
	public Teacher findTeacher(String id) {
		return teacherDao.selectTeacherById(id);
	}
	
}
