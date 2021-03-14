package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.InspectionAreaArrangementBuildingDao;
import cn.grad.supm.dao.InspectionAreaArrangementDao;
import cn.grad.supm.dao.InspectionAreaArrangementSituationDao;
import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.domain.InspectionAreaArrangementBuilding;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.service.InspectionAreaArrangementService;
import cn.grad.supm.utils.tag.PageModel;

@Service("inspectionAreaArrangementService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class InspectionAreaArrangementServiceImpl implements InspectionAreaArrangementService {

	@Autowired
	private InspectionAreaArrangementDao inspectionAreaArrangementDao;
	@Autowired
	private InspectionAreaArrangementBuildingDao inspectionAreaArrangementBuildingDao;
	@Autowired
	private InspectionAreaArrangementSituationDao inspectionAreaArrangementSituationDao;

	
	@Override
	public List<InspectionAreaArrangement> findInspectionAreaArrangementList(InspectionAreaArrangement inspectionAreaArrangement,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("inspectionAreaArrangement", inspectionAreaArrangement);
		
		int recordCount=inspectionAreaArrangementDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<InspectionAreaArrangement> inspectionAreaArrangementList=inspectionAreaArrangementDao.selectInspectionAreaArrangementListByPage(params);
		return inspectionAreaArrangementList;
	}

	
	@Override
	public void addInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement) {
		inspectionAreaArrangementDao.insertInspectionAreaArrangement(inspectionAreaArrangement);
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList=inspectionAreaArrangement.getInspectionAreaArrangementBuildingList();
		for(InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding:inspectionAreaArrangementBuildingList) {
			inspectionAreaArrangementBuildingDao.insertInspectionAreaArrangementBuilding(inspectionAreaArrangement.getId(), inspectionAreaArrangementBuilding);
			List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList=inspectionAreaArrangementBuilding.getInspectionAreaArrangementSituationList();
			for(InspectionAreaArrangementSituation inspectionAreaArrangementSituation:inspectionAreaArrangementSituationList) {
				inspectionAreaArrangementSituationDao.InsertInspectionAreaArrangementSituation(inspectionAreaArrangementBuilding.getId(), inspectionAreaArrangementSituation);
			}
		}
		
	}
	
	
	@Override
	public void modifyInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement) {
		inspectionAreaArrangementDao.updateInspectionAreaArrangement(inspectionAreaArrangement);
		List<InspectionAreaArrangementBuilding> areaArrangementBuildingList=inspectionAreaArrangement.getInspectionAreaArrangementBuildingList();
		for(InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding:areaArrangementBuildingList) {
			inspectionAreaArrangementBuildingDao.updateInspectionAreaArrangementBuilding(inspectionAreaArrangementBuilding);
			List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList=inspectionAreaArrangementBuilding.getInspectionAreaArrangementSituationList();
			for(InspectionAreaArrangementSituation inspectionAreaArrangementSituation:inspectionAreaArrangementSituationList) {
				inspectionAreaArrangementSituationDao.updateInspectionAreaArrangementSituation(inspectionAreaArrangementSituation);
			}
		}
		
	}

	
	@Override
	public void removeInspectionAreaArrangement(int id) {
		InspectionAreaArrangement inspectionAreaArrangement=inspectionAreaArrangementDao.selectInspectionAreaArrangementById(id);
		List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList=inspectionAreaArrangement.getInspectionAreaArrangementBuildingList();
		for(InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding:inspectionAreaArrangementBuildingList){
			inspectionAreaArrangementSituationDao.deleteInsepctionAreaArrangementSituation(inspectionAreaArrangementBuilding.getId());
		}
		inspectionAreaArrangementBuildingDao.deleteInspectionAreaArrangementBuilding(id);
		inspectionAreaArrangementDao.deleteInspectionAreaArrangement(id);
	}
	
	
	
	
	
}
