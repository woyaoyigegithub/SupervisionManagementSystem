package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.StudentDao;
import cn.grad.supm.domain.Student;
import cn.grad.supm.service.StudentService;
import cn.grad.supm.utils.tag.PageModel;

@Service("studentService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao; 

	
	@Override
	public List<Student> findStudentList(Student student,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("student", student);
		
		int recordCount=studentDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Student> studentList=studentDao.selectStudentListByPage(params);
		return studentList;
	}

	@Override
	public void addStudent(Student student) {
		studentDao.insertStudent(student);
	}
	
	
	@Override
	public void modifyStudent(Student student) {
		studentDao.updateStudent(student);
	}

	@Override
	public void removeStudent(String id) {
		studentDao.deleteStudent(id);
	}
	
	
}
