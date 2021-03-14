package cn.grad.supm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.utils.BaseDaoTest;

public class SupervisorDaoTest extends BaseDaoTest {

	@Autowired
	private SupervisorDao supervisorDao;
	
	@Test
	public void testSelectSupervisorById() {
		Supervisor supervisor=supervisorDao.selectSupervisorById("0000109");
		System.out.println(supervisor.getPower());
		assertNotNull(supervisor);
	}
}
