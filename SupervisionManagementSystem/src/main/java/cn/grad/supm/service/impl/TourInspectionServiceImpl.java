package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.TourInspectionRecordDao;
import cn.grad.supm.dao.TourInspectionSituationDao;
import cn.grad.supm.domain.TourInspectionRecord;
import cn.grad.supm.domain.TourInspectionSituation;
import cn.grad.supm.service.Statistics;
import cn.grad.supm.service.TourInspectionService;
import cn.grad.supm.utils.tag.PageModel;

@Service("tourInspectionRecordService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TourInspectionServiceImpl implements TourInspectionService,Statistics {

	@Autowired
	private TourInspectionRecordDao tourInspectionRecordDao;
	@Autowired
	private TourInspectionSituationDao tourInspectionSituationDao;

	
	@Override
	public List<TourInspectionRecord> findTourInspectionList(TourInspectionRecord tourInspectionRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("tourInspectionRecord", tourInspectionRecord);
		
		int recordCount=tourInspectionRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<TourInspectionRecord> tourInspectionRecordList=tourInspectionRecordDao.selectTourInspectionRecordListByPage(params);
		return tourInspectionRecordList;
	}

	@Override
	public void addTourInspection(TourInspectionRecord tourInspectionRecord) {
		tourInspectionRecordDao.insertTourInspectionRecord(tourInspectionRecord);
		List<TourInspectionSituation> tourInspectionSituationList=tourInspectionRecord.getTourInspectionSituationList();
		for(TourInspectionSituation tourInspectionSituation:tourInspectionSituationList) {
			tourInspectionSituationDao.insertTourInspectionSituation(tourInspectionRecord.getId(), tourInspectionSituation);
		}
	}
	
	
	@Override
	public void modifyTourInspection(TourInspectionRecord tourInspectionRecord) {
		tourInspectionRecordDao.updateTourInspectionRecord(tourInspectionRecord);
		List<TourInspectionSituation> tourInspectionSituationList=tourInspectionRecord.getTourInspectionSituationList();
		for(TourInspectionSituation tourInspectionSituation:tourInspectionSituationList) {
			System.out.println(tourInspectionSituation);
			tourInspectionSituationDao.updateTourInspectionSituation(tourInspectionRecord.getId(), tourInspectionSituation);
		}
	}

	@Override
	public void removeTourInspection(int id) {
		tourInspectionSituationDao.deleteTourInspectionSituation(id);
		tourInspectionRecordDao.deleteTourInspectionRecord(id);
	}

	@Override
	public List<Map<String, String>> countBySchoolYearAndSemester(String supervisorId) {
		return tourInspectionRecordDao.countBySchoolYearAndSemester(supervisorId);
	}
	
	
}
