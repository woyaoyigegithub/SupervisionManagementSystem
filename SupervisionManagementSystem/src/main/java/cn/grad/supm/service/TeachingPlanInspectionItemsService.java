package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.utils.tag.PageModel;

public interface TeachingPlanInspectionItemsService {

	public List<TeachingPlanInspectionItems> findTeachingPlanInspectionItemsList(TeachingPlanInspectionItems teachingPlanInspectionItems,PageModel pageModel);
	
	
	public void addTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems);
	
	
	public void modifyTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems);
	
	
	public void removeTeachingPlanInspectionItems(int id);
}
