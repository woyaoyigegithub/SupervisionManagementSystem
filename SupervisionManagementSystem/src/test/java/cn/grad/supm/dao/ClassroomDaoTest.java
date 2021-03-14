package cn.grad.supm.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;


public class ClassroomDaoTest extends BaseDaoTest{

	@Autowired
	private ClassroomDao classroomDao;
	private Classroom classroom=new Classroom("0290000", "测试", 10, "多媒体", "未知楼");
	
	@Test
	public void testSelectAllClassroom() {
		List<Classroom> classroomList=classroomDao.selectAllClassroom();
		assertThat(classroomList.size(), not(0));
	}
	
	@Test
	public void testSelectClassroomListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		Classroom classroom=new Classroom();
		classroom.setName("慎思5");
		classroom.setSeatingCapacity(0);
		classroom.setType("实验室");
		params.put("classroom", classroom);
		pageModel.setRecordCount(classroomDao.count(params));
		params.put("pageModel", pageModel);
		List<Classroom> classroomList=classroomDao.selectClassroomListByPage(params);
		System.out.println(classroomList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(classroomList.size()));
	}
	
	
	@Test
	public void testSelectClassroomById() {
		Classroom classroom1=classroomDao.selectClassroomById(classroom.getId());
		System.out.println(classroom1);
		assertNotNull(classroom1);
	}
	
	
	@Test
	public void testAddClassroom() {
		classroomDao.insertClassroom(classroom);
		
		Classroom classroom1=classroomDao.selectClassroomById(classroom.getId());
		System.out.println(classroom1);
		assertNotNull(classroom1);
	}
	
	@Test
	public void testUpdateClassroom() {
		classroomDao.insertClassroom(classroom);
		classroom.setName("测试1");
		classroomDao.updateClassroom(classroom);
		
		Classroom classroom1=classroomDao.selectClassroomById(classroom.getId());
		System.out.println(classroom1);
		assertNotNull(classroom1);
	}
	
	@Test
	public void testDeleteClassroom() {
		classroomDao.insertClassroom(classroom);
		
		Classroom classroom1=classroomDao.selectClassroomById(classroom.getId());
		System.out.println(classroom1);
		assertNotNull(classroom1);
		
		classroomDao.deleteClassroom(classroom.getId());
		
		Classroom classroom2=classroomDao.selectClassroomById(classroom.getId());
		System.out.println(classroom2);
		assertNull(classroom2);
	}
	

}
