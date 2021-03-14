package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.PowerDao;
import cn.grad.supm.domain.Power;
import cn.grad.supm.service.PowerService;
import cn.grad.supm.utils.tag.PageModel;

@Service("powerService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PowerServiceImpl implements PowerService {

	@Autowired
	private PowerDao powerDao; 

	
	@Override
	public List<Power> findAllPower() {
		return powerDao.selectAllPower();
	}
	
	@Override
	public List<Power> findPowerList(Power power,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("power", power);
		
		int recordCount=powerDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Power> powerList=powerDao.selectPowerListByPage(params);
		return powerList;
	}

	@Override
	public void addPower(Power power) {
		powerDao.insertPower(power);
	}
	
	
	@Override
	public void modifyPower(Power power) {
		powerDao.updatePower(power);
	}

	@Override
	public void removePower(int id) {
		powerDao.deletePower(id);
	}

	
	
	
}
