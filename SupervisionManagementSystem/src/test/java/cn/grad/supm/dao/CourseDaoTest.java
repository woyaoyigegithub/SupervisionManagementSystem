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

import cn.grad.supm.domain.Course;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;

public class CourseDaoTest extends BaseDaoTest{

	@Autowired
	private CourseDao courseDao;
	private Course course=new Course("0010000090", "test", "", "实验");
	
	@Test
	public void testSelectAllCourse() {
		List<Course> courseList=courseDao.selectAllCourse();
		System.out.println(courseList);
		assertThat(0, not(courseList.size()));
	}
	
	@Test
	public void testSelectCourseListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		Course course=new Course();
		course.setType("理论");
		course.setNature("必修课");
		course.setName("工程");
		params.put("course", course);
		pageModel.setRecordCount(courseDao.count(params));
		params.put("pageModel", pageModel);
		List<Course> courseList=courseDao.selectCourseListByPage(params);
		System.out.println(courseList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(courseList.size()));
	}
	
	@Test
	public void testSelectCourseById() {
		Course course=courseDao.selectCourseById("0010000001");
		System.out.println(course);
		assertNotNull(course);
	}
	
	@Test
	public void testAddCourse() {
		courseDao.insertCourse(course);
		
		Course course1=courseDao.selectCourseById(course.getId());
		System.out.println(course1);
		assertNotNull(course1);
	}
	
	@Test
	public void testUpdateCourse() {
		courseDao.insertCourse(course);
		course.setName("测试1");
		courseDao.updateCourse(course);
		
		Course course1=courseDao.selectCourseById(course.getId());
		System.out.println(course1);
		assertNotNull(course1);
	}
	
	@Test
	public void testDeleteCourse() {
		courseDao.insertCourse(course);
		
		Course course1=courseDao.selectCourseById(course.getId());
		System.out.println(course1);
		assertNotNull(course1);
		
		courseDao.deleteCourse(course.getId());
		
		Course course2=courseDao.selectCourseById(course.getId());
		System.out.println(course2);
		assertNull(course2);
	}
	
}
