package cn.grad.supm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.AdminDao;
import cn.grad.supm.domain.Admin;
import cn.grad.supm.service.AdminService;


@Service("adminService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao; 

	
	@Override
	public Admin findAdmin(String id) {
		return adminDao.selectAdminById(id);
	}
	
	
	
}
