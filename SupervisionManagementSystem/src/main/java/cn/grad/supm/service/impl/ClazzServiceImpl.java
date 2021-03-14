package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.ClazzDao;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.service.ClazzService;
import cn.grad.supm.utils.tag.PageModel;

@Service("clazzService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzDao clazzDao; 

	
	@Override
	public List<Clazz> findClazzList(Clazz clazz,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("clazz", clazz);
		
		int recordCount=clazzDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<Clazz> clazzList=clazzDao.selectClazzListByPage(params);
		return clazzList;
	}

	@Override
	public void addClazz(Clazz clazz) {
		clazzDao.insertClazz(clazz);
	}
	
	
	@Override
	public void modifyClazz(Clazz clazz) {
		clazzDao.updateClazz(clazz);
	}

	@Override
	public void removeClazz(String id) {
		clazzDao.deleteClazz(id);
	}
	
	
}
