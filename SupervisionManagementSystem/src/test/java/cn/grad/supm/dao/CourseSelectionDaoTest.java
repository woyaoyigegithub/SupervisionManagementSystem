package cn.grad.supm.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.*;

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Course;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;


public class CourseSelectionDaoTest extends BaseDaoTest{

	@Autowired
	private CourseSelectionDao courseSelectionDao;
	private Course course=new Course("2101149931","","","");
	private Teacher teacher=new Teacher("0000984","","","");
	private Clazz clazz=new Clazz("2019010227","","","","","");
	private Classroom classroom=new Classroom("0210511","",0,"","");
	private CourseSelection courseSelection=new CourseSelection("(2020-2021-1)-2101149931-0000984-5",
			course,teacher,clazz,"",classroom,"2020-2021",1,"星期四","第5,6节","","1-16",2);
	
	@Test
	public void testSelectAllCourseSelection() {
		List<CourseSelection> courseSelectionList=courseSelectionDao.selectAllCourseSelection();
		System.out.println(courseSelectionList);
		assertThat(0, not(courseSelectionList.size()));
	}
	
	@Test
	public void testSelectCourseSelectionListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
//		Teacher teacher=new Teacher();
//		teacher.setId("0002383");
//		teacher.setName("吴爽");
//		Clazz clazz=new Clazz();
//		clazz.setId("2018010234");
//		clazz.setName("17级会计学1班");
//		Classroom classroom=new Classroom();
//		classroom.setId("0200011");
//		classroom.setName("明辨1-403");
//		clazz.setName("17级会计学1班");
		Course course=new Course();
		course.setId("0401140051");
//		course.setName("高级英语I");
		CourseSelection courseSelection=new CourseSelection();
//		courseSelection.setTeacher(teacher);
//		courseSelection.setClassroom(classroom);
//		courseSelection.setClazz(clazz);
		courseSelection.setCourse(course);
		params.put("courseSelection", courseSelection);
		pageModel.setRecordCount(courseSelectionDao.count(params));
		params.put("pageModel", pageModel);
		List<CourseSelection> courseSelectionList=courseSelectionDao.selectCourseSelectionListByPage(params);
		System.out.println(courseSelectionList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(courseSelectionList.size()));
	}
	
	@Test
	public void testSelectCourseSelectionById() {
		CourseSelection courseSelection=courseSelectionDao.selectCourseSelectionById("(2020-2021-1)-0010000002-0001317-3");
		System.out.println(courseSelection);
		assertNotNull(courseSelection);
	}
	
	@Test
	public void testAddCourseSelection() {
		courseSelectionDao.insertCourseSelection(courseSelection);
		
		CourseSelection courseSelection1=courseSelectionDao.selectCourseSelectionById(courseSelection.getId());
		System.out.println(courseSelection1);
		assertNotNull(courseSelection1);
	}
	
	@Test
	public void testUpdateCourseSelection() {
		courseSelectionDao.insertCourseSelection(courseSelection);
		courseSelection.getCourse().setId("2101119991");
		courseSelectionDao.updateCourseSelection(courseSelection);
		
		CourseSelection courseSelection1=courseSelectionDao.selectCourseSelectionById(courseSelection.getId());
		System.out.println(courseSelection1);
		assertNotNull(courseSelection1);
	}
	
	@Test
	public void testDeleteCourseSelection() {
		courseSelectionDao.insertCourseSelection(courseSelection);
		
		CourseSelection courseSelection1=courseSelectionDao.selectCourseSelectionById(courseSelection.getId());
		System.out.println(courseSelection1);
		assertNotNull(courseSelection1);
		
		courseSelectionDao.deleteCourseSelection(courseSelection.getId());
		
		CourseSelection courseSelection2=courseSelectionDao.selectCourseSelectionById(courseSelection.getId());
		System.out.println(courseSelection2);
		assertNull(courseSelection2);
	}

}
