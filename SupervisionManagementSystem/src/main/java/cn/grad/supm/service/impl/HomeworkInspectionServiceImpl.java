package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.HomeworkInspectionRecordDao;
import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.service.HomeworkInspectionService;
import cn.grad.supm.utils.tag.PageModel;

@Service("homeworkInspectionService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class HomeworkInspectionServiceImpl implements HomeworkInspectionService {

	@Autowired
	private HomeworkInspectionRecordDao homeworkInspectionRecordDao; 

	
	@Override
	public List<HomeworkInspectionRecord> findHomeworkInspectionList(HomeworkInspectionRecord homeworkInspectionRecord,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("homeworkInspectionRecord", homeworkInspectionRecord);
		
		int recordCount=homeworkInspectionRecordDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<HomeworkInspectionRecord> homeworkInspectionRecordList=homeworkInspectionRecordDao.selectHomeworkInspectionRecordListByPage(params);
		return homeworkInspectionRecordList;
	}

	@Override
	public void addHomeworkInspection(HomeworkInspectionRecord homeworkInspectionRecord) {
		homeworkInspectionRecordDao.insertHomeworkInspectionRecord(homeworkInspectionRecord);
	}
	
	
	@Override
	public void modifyHomeworkInspection(HomeworkInspectionRecord homeworkInspectionRecord) {
		homeworkInspectionRecordDao.updateHomeworkInspectionRecord(homeworkInspectionRecord);
	}

	@Override
	public void removeHomeworkInspection(int id) {
		homeworkInspectionRecordDao.deleteHomeworkInspectionRecord(id);
	}
	
	
}
