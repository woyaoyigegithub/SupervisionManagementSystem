package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Student;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class StudentDaoTest extends BaseDaoTest {

	@Autowired
	private StudentDao studentDao;
	private Clazz clazz=new Clazz("2017010101","","","","","");
	private Student student=new Student("201701010102", "周立波", "男", clazz);
	
	@Test
	public void testSelectAllStudent() {
		List<Student> studentList=studentDao.selectAllStudent();
		System.out.println(studentList);
		assertThat(0, not(studentList.size()));
	}
	
	@Test
	public void testSelectStudentListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		params.put("student", new Student());
		pageModel.setRecordCount(studentDao.count(params));
		params.put("pageModel", pageModel);
		List<Student> studentList=studentDao.selectStudentListByPage(params);
		System.out.println(studentList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(studentList.size()));
	}
	
	@Test
	public void testSelectStudentById() {
		Student student=studentDao.selectStudentById("201701010101");
		System.out.println(student);
		assertNotNull(student);
	}
	
	
	@Test
	public void testAddStudent() {
		studentDao.insertStudent(student);
		
		Student student1=studentDao.selectStudentById(student.getId());
		System.out.println(student1);
		assertNotNull(student1);
	}
	
	@Test
	public void testUpdateStudent() {
		studentDao.insertStudent(student);
		student.setName("测试1");
		studentDao.updateStudent(student);
		
		Student student1=studentDao.selectStudentById(student.getId());
		System.out.println(student1);
		assertNotNull(student1);
	}
	
	@Test
	public void testDeleteStudent() {
		studentDao.insertStudent(student);
		
		Student student1=studentDao.selectStudentById(student.getId());
		System.out.println(student1);
		assertNotNull(student1);
		
		studentDao.deleteStudent(student.getId());
		
		Student student2=studentDao.selectStudentById(student.getId());
		System.out.println(student2);
		assertNull(student2);
	}

}
