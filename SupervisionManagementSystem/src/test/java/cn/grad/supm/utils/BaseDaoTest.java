package cn.grad.supm.utils;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/springmvc-config.xml","/applicationContext.xml"})
@WebAppConfiguration
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
public class BaseDaoTest {

	
}
