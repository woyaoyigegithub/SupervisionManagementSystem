package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.DailyInspectionRecordDao;
import cn.grad.supm.dao.DailyInspectionSituationDao;
import cn.grad.supm.dao.LeaveClassAheadOfTimeSituationDao;
import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.domain.DailyInspectionSituation;
import cn.grad.supm.domain.LeaveClassAheadOfTimeSituation;
import cn.grad.supm.service.DailyInspectionService;
import cn.grad.supm.service.Statistics;
import cn.grad.supm.utils.tag.PageModel;


@Service("dailyInspectionService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class DailyInspectionServiceImpl implements DailyInspectionService,Statistics {

	@Autowired
	private DailyInspectionRecordDao dailyInspectionRecordDao;
	@Autowired
	private DailyInspectionSituationDao dailyInspectionSituationDao;
	@Autowired
	private LeaveClassAheadOfTimeSituationDao leaveClassAheadOfTimeSituationDao;
	
	
	@Override
	public List<DailyInspectionRecord> findDailyInspectionList(DailyInspectionRecord dailyInspectionRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("dailyInspectionRecord", dailyInspectionRecord);
		
		int recordCount=dailyInspectionRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<DailyInspectionRecord> dailyInspectionRecordList=dailyInspectionRecordDao.selectDailyInspectionRecordByPage(params);
		return dailyInspectionRecordList;
	}

	
	@Override
	public void addDailyInspection(DailyInspectionRecord dailyInspectionRecord) {
		dailyInspectionRecordDao.insertDailyInspectionRecord(dailyInspectionRecord);
		List<DailyInspectionSituation> dailyInspectionSituationList=dailyInspectionRecord.getDailyInspectionSituationList();
		List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList=dailyInspectionRecord.getLeaveClassAheadOfTimeSituationList();
		for(DailyInspectionSituation dailyInspectionSituation:dailyInspectionSituationList) {
			dailyInspectionSituationDao.insertDailyInspectionSituation(dailyInspectionRecord.getId(), dailyInspectionSituation);
		}
		for(LeaveClassAheadOfTimeSituation leaveClassAheadOfTimeSituation:leaveClassAheadOfTimeSituationList) {
			leaveClassAheadOfTimeSituationDao.insertLeaveClassAheadOfTimeSituation(dailyInspectionRecord.getId(), leaveClassAheadOfTimeSituation);
		}
	}
	
	
	@Override
	public void modifyDailyInspection(DailyInspectionRecord dailyInspectionRecord) {
		dailyInspectionRecordDao.updateDailyInspectionRecord(dailyInspectionRecord);
		List<DailyInspectionSituation> dailyInspectionSituationList=dailyInspectionRecord.getDailyInspectionSituationList();
		List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList=dailyInspectionRecord.getLeaveClassAheadOfTimeSituationList();
		for(DailyInspectionSituation dailyInspectionSituation:dailyInspectionSituationList) {
			System.out.println(dailyInspectionSituation);
			dailyInspectionSituationDao.updateDailyInspectionSituation(dailyInspectionRecord.getId(), dailyInspectionSituation);
		}
		//先删除已有的提前下课记录，再重新添加
		leaveClassAheadOfTimeSituationDao.deleteLeaveClassAheadOfTimeSituation(dailyInspectionRecord.getId());
		for(LeaveClassAheadOfTimeSituation leaveClassAheadOfTimeSituation:leaveClassAheadOfTimeSituationList) {
			leaveClassAheadOfTimeSituationDao.insertLeaveClassAheadOfTimeSituation(dailyInspectionRecord.getId(), leaveClassAheadOfTimeSituation);
		}
	}

	
	@Override
	public void removeDailyInspection(int id) {
		dailyInspectionSituationDao.deleteDailyInspectionSituation(id);
		leaveClassAheadOfTimeSituationDao.deleteLeaveClassAheadOfTimeSituation(id);
		dailyInspectionRecordDao.deleteDailyInspectionRecord(id);
	}


	@Override
	public List<Map<String, String>> countBySchoolYearAndSemester(String supervisorId) {
		return dailyInspectionRecordDao.countBySchoolYearAndSemester(supervisorId);
	}
	
	
}
