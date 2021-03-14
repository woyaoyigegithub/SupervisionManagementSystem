package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.TeachingPlanInspectionRecordDao;
import cn.grad.supm.dao.TeachingPlanInspectionSituationDao;
import cn.grad.supm.domain.TeachingPlanInspectionRecord;
import cn.grad.supm.domain.TeachingPlanInspectionSituation;
import cn.grad.supm.service.TeachingPlanInspectionService;
import cn.grad.supm.utils.tag.PageModel;

@Service("teachingPlanInspectionService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TeachingPlanInspectionServiceImpl implements TeachingPlanInspectionService {

	@Autowired
	private TeachingPlanInspectionRecordDao teachingPlanInspectionRecordDao;
	@Autowired
	private TeachingPlanInspectionSituationDao teachingPlanInspectionSituationDao;

	
	@Override
	public List<TeachingPlanInspectionRecord> findTeachingPlanInspectionList(TeachingPlanInspectionRecord teachingPlanInspectionRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("teachingPlanInspectionRecord", teachingPlanInspectionRecord);
		
		int recordCount=teachingPlanInspectionRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<TeachingPlanInspectionRecord> teachingPlanInspectionRecordList=teachingPlanInspectionRecordDao.selectTeachingPlanInspectionRecordListByPage(params);
		return teachingPlanInspectionRecordList;
	}

	
	@Override
	public void addTeachingPlanInspection(TeachingPlanInspectionRecord teachingPlanInspectionRecord) {
		List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList=teachingPlanInspectionRecord.getTeachingPlanInspectionSituationList();
		teachingPlanInspectionRecordDao.insertTeachingPlanInspectionRecord(teachingPlanInspectionRecord);
		for(TeachingPlanInspectionSituation teachingPlanInspectionSituation:teachingPlanInspectionSituationList) {
			teachingPlanInspectionSituationDao.insertTeachingPlanInspectionSituation(teachingPlanInspectionRecord.getId(), teachingPlanInspectionSituation);
		}
	}
	
	
	@Override
	public void modifyTeachingPlanInspection(TeachingPlanInspectionRecord teachingPlanInspectionRecord) {
		List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList=teachingPlanInspectionRecord.getTeachingPlanInspectionSituationList();
		teachingPlanInspectionRecordDao.updateTeachingPlanInspectionRecord(teachingPlanInspectionRecord);
		for(TeachingPlanInspectionSituation teachingPlanInspectionSituation:teachingPlanInspectionSituationList) {
			teachingPlanInspectionSituationDao.updateTeachingPlanInspectionSituation(teachingPlanInspectionRecord.getId(), teachingPlanInspectionSituation);
		}
	}

	@Override
	public void removeTeachingPlanInspection(int id) {
		teachingPlanInspectionSituationDao.deleteTeachingPlanInspectionSituation(id);
		teachingPlanInspectionRecordDao.deleteTeachingPlanInspectionRecord(id);
	}
	
	
}
