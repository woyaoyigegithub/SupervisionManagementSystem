package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.TeachingPlanInspectionItemsDao;
import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.service.TeachingPlanInspectionItemsService;
import cn.grad.supm.utils.tag.PageModel;


@Service("teachingPlanInspectionItemsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TeachingPlanInspectionItemsServiceImpl implements TeachingPlanInspectionItemsService {

	@Autowired
	private TeachingPlanInspectionItemsDao teachingPlanInspectionItemsDao; 

	
	@Override
	public List<TeachingPlanInspectionItems> findTeachingPlanInspectionItemsList(TeachingPlanInspectionItems teachingPlanInspectionItems,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("teachingPlanInspectionItems", teachingPlanInspectionItems);
		
		int recordCount=teachingPlanInspectionItemsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<TeachingPlanInspectionItems> teachingPlanInspectionItemsList=teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsListByPage(params);
		return teachingPlanInspectionItemsList;
	}

	@Override
	public void addTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems) {
		teachingPlanInspectionItemsDao.insertTeachingPlanInspectionItems(teachingPlanInspectionItems);
	}
	
	
	@Override
	public void modifyTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems) {
		teachingPlanInspectionItemsDao.updateTeachingPlanInspectionItems(teachingPlanInspectionItems);
	}

	@Override
	public void removeTeachingPlanInspectionItems(int id) {
		teachingPlanInspectionItemsDao.deleteTeachingPlanInspectionItems(id);
	}
	
	
	
	
}
