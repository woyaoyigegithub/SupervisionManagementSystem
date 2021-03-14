package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Admin;
import cn.grad.supm.utils.BaseDaoTest;

public class AdminDaoTest extends BaseDaoTest {

	@Autowired
	private AdminDao adminDao;
	
	@Test
	public void testSelectAdminById() {
		Admin admin=adminDao.selectAdminById("0000001");
		System.out.println(admin.getPower());
		assertNotNull(admin);
	}

}
