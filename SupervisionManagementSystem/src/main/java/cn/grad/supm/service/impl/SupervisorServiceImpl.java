package cn.grad.supm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.SupervisorDao;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.service.SupervisorService;


@Service("supervisorService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class SupervisorServiceImpl implements SupervisorService {

	@Autowired
	private SupervisorDao supervisorDao; 

	
	@Override
	public Supervisor findSupervisor(String id) {
		return supervisorDao.selectSupervisorById(id);
	}
	
}
