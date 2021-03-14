package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.TourInspectionRecord;
import cn.grad.supm.utils.tag.PageModel;

public interface TourInspectionService extends Statistics {

	public List<TourInspectionRecord> findTourInspectionList(TourInspectionRecord tourInspectionRecord,PageModel pageModel);
	
	
	public void addTourInspection(TourInspectionRecord tourInspectionRecord);
	
	
	public void modifyTourInspection(TourInspectionRecord tourInspectionRecord);
	
	
	public void removeTourInspection(int id);


	
}
