package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.utils.tag.PageModel;

public interface DailyInspectionService extends Statistics {

	public List<DailyInspectionRecord> findDailyInspectionList(DailyInspectionRecord dailyInspectionRecord,PageModel pageModel);
	
	
	public void addDailyInspection(DailyInspectionRecord dailyInspetionRecord);
	
	
	public void modifyDailyInspection(DailyInspectionRecord dailyInspetionRecord);
	
	
	public void removeDailyInspection(int id);
	
	
	
	
}
