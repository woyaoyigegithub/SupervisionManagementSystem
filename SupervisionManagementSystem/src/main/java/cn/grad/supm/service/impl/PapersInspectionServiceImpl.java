package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.PapersInspectionRecordDao;
import cn.grad.supm.dao.PapersInspectionSituationDao;
import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.domain.PapersInspectionSituation;
import cn.grad.supm.service.PapersInspectionService;
import cn.grad.supm.utils.tag.PageModel;


@Service("papersInspectionRecordService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PapersInspectionServiceImpl implements PapersInspectionService {

	@Autowired
	private PapersInspectionRecordDao papersInspectionRecordDao;
	@Autowired
	private PapersInspectionSituationDao papersInspectionSituationDao;

	
	@Override
	public List<PapersInspectionRecord> findPapersInspectionList(PapersInspectionRecord papersInspectionRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("papersInspectionRecord", papersInspectionRecord);
		
		int recordCount=papersInspectionRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<PapersInspectionRecord> papersInspectionRecordList=papersInspectionRecordDao.selectPapersInspectionRecordListByPage(params);
		return papersInspectionRecordList;
	}

	
	@Override
	public void addPapersInspection(PapersInspectionRecord papersInspectionRecord) {
		papersInspectionRecordDao.insertPapersInspectionRecord(papersInspectionRecord);
		List<PapersInspectionSituation> papersInspectionSituationList=papersInspectionRecord.getPapersInspectionSituationList();
		for(PapersInspectionSituation papersInspectionSituation:papersInspectionSituationList) {
			papersInspectionSituationDao.insertPapersInspectionSituation(papersInspectionRecord.getId(), papersInspectionSituation);
		}
	}
	
	
	@Override
	public void modifyPapersInspection(PapersInspectionRecord papersInspectionRecord) {
		papersInspectionRecordDao.updatePapersInspectionRecord(papersInspectionRecord);
		List<PapersInspectionSituation> papersInspectionSituationList=papersInspectionRecord.getPapersInspectionSituationList();
		for(PapersInspectionSituation papersInspectionSituation:papersInspectionSituationList) {
			papersInspectionSituationDao.updatePapersInspectionSituation(papersInspectionRecord.getId(), papersInspectionSituation);
		}
	}

	
	@Override
	public void removePapersInspection(int id) {
		papersInspectionSituationDao.deletePapersInspectionSituation(id);
		papersInspectionRecordDao.deletePapersInspectionRecord(id);
	}
	
	
}
