package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Teacher;
import cn.grad.supm.utils.BaseDaoTest;

public class TeacherDaoTest extends BaseDaoTest {

	@Autowired
	private TeacherDao teacherDao;
	
	@Test
	public void testSelectTeacherById() {
		Teacher teacher=teacherDao.selectTeacherById("0000005");
		System.out.println(teacher.getPower());
		assertNotNull(teacher);
	}
}
