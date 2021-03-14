package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Student;
import cn.grad.supm.utils.tag.PageModel;

public interface StudentService {

	public List<Student> findStudentList(Student student,PageModel pageModel);
	
	
	public void addStudent(Student student);
	
	
	public void modifyStudent(Student student);
	
	
	public void removeStudent(String id);
	
}
