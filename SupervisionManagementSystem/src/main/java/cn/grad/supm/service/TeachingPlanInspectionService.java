package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.TeachingPlanInspectionRecord;
import cn.grad.supm.utils.tag.PageModel;


public interface TeachingPlanInspectionService {

	public List<TeachingPlanInspectionRecord> findTeachingPlanInspectionList(TeachingPlanInspectionRecord teachingPlanInspectionRecord,PageModel pageModel);
	
	
	public void addTeachingPlanInspection(TeachingPlanInspectionRecord teachingPlanInspectionRecord);
	
	
	public void modifyTeachingPlanInspection(TeachingPlanInspectionRecord teachingPlanInspectionRecord);
	
	
	public void removeTeachingPlanInspection(int id);
}
