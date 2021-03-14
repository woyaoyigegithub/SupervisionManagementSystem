package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao. DailyInspectionItemsDao;
import cn.grad.supm.domain. DailyInspectionItems;
import cn.grad.supm.service. DailyInspectionItemsService;
import cn.grad.supm.utils.tag.PageModel;


@Service("dailyInspectionItemsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class DailyInspectionItemsServiceImpl implements  DailyInspectionItemsService {

	@Autowired
	private  DailyInspectionItemsDao dailyInspectionItemsDao; 

	
	@Override
	public List<DailyInspectionItems> findDailyInspectionItemsList(DailyInspectionItems dailyInspectionItems,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("dailyInspectionItems", dailyInspectionItems);
		
		int recordCount=dailyInspectionItemsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List< DailyInspectionItems> dailyInspectionItemsList=dailyInspectionItemsDao.selectDailyInspectionItemsListByPage(params);
		return dailyInspectionItemsList;
	}

	@Override
	public void addDailyInspectionItems(DailyInspectionItems dailyInspectionItems) {
		dailyInspectionItemsDao.insertDailyInspectionItems(dailyInspectionItems);
	}
	
	
	@Override
	public void modifyDailyInspectionItems(DailyInspectionItems dailyInspectionItems) {
		dailyInspectionItemsDao.updateDailyInspectionItems(dailyInspectionItems);
	}

	@Override
	public void removeDailyInspectionItems(int id) {
		dailyInspectionItemsDao.deleteDailyInspectionItems(id);
	}

	
}
