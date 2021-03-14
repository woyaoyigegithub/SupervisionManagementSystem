package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.TourInspectionItemsDao;
import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.service.TourInspectionItemsService;
import cn.grad.supm.utils.tag.PageModel;


@Service("tourInspectionItemsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TourInspectionItemsServiceImpl implements TourInspectionItemsService {

	@Autowired
	private TourInspectionItemsDao tourInspectionItemsDao; 

	
	@Override
	public List<TourInspectionItems> findTourInspectionItemsList(TourInspectionItems tourInspectionItems,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("tourInspectionItems", tourInspectionItems);
		
		int recordCount=tourInspectionItemsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<TourInspectionItems> tourInspectionItemsList=tourInspectionItemsDao.selectTourInspectionItemsListByPage(params);
		return tourInspectionItemsList;
	}

	@Override
	public void addTourInspectionItems(TourInspectionItems tourInspectionItems) {
		tourInspectionItemsDao.insertTourInspectionItems(tourInspectionItems);
	}
	
	
	@Override
	public void modifyTourInspectionItems(TourInspectionItems tourInspectionItems) {
		tourInspectionItemsDao.updateTourInspectionItems(tourInspectionItems);
	}

	@Override
	public void removeTourInspectionItems(int id) {
		tourInspectionItemsDao.deleteTourInspectionItems(id);
	}
	
}
