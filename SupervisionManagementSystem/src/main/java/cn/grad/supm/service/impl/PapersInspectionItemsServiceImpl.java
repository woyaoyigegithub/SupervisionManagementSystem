package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.PapersInspectionItemsDao;
import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.service.PapersInspectionItemsService;
import cn.grad.supm.utils.tag.PageModel;


@Service("papersInspectionItemsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PapersInspectionItemsServiceImpl implements PapersInspectionItemsService {

	@Autowired
	private PapersInspectionItemsDao papersInspectionItemsDao; 

	
	@Override
	public List<PapersInspectionItems> findPapersInspectionItemsList(PapersInspectionItems papersInspectionItems,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("papersInspectionItems", papersInspectionItems);
		
		int recordCount=papersInspectionItemsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<PapersInspectionItems> papersInspectionItemsList=papersInspectionItemsDao.selectPapersInspectionItemsListByPage(params);
		return papersInspectionItemsList;
	}

	@Override
	public void addPapersInspectionItems(PapersInspectionItems papersInspectionItems) {
		papersInspectionItemsDao.insertPapersInspectionItems(papersInspectionItems);
	}
	
	
	@Override
	public void modifyPapersInspectionItems(PapersInspectionItems papersInspectionItems) {
		papersInspectionItemsDao.updatePapersInspectionItems(papersInspectionItems);
	}

	@Override
	public void removePapersInspectionItems(int id) {
		papersInspectionItemsDao.deletePapersInspectionItems(id);
	}
	
}
