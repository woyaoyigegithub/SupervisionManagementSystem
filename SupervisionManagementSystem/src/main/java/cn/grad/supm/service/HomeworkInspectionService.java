package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.utils.tag.PageModel;

public interface HomeworkInspectionService {

	public List<HomeworkInspectionRecord> findHomeworkInspectionList(HomeworkInspectionRecord homeworkInspectionRecord,PageModel pageModel);
	
	
	public void addHomeworkInspection(HomeworkInspectionRecord homeworkInspectionRecord);
	
	
	public void modifyHomeworkInspection(HomeworkInspectionRecord homeworkInspectionRecord);
	
	
	public void removeHomeworkInspection(int id);
	
}
