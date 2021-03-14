package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.utils.tag.PageModel;

public interface InspectionAreaArrangementService {

	public List<InspectionAreaArrangement> findInspectionAreaArrangementList(InspectionAreaArrangement inspectionAreaArrangement,PageModel pageModel);
	
	
	public void addInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement);
	
	
	public void modifyInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement);
	
	
	public void removeInspectionAreaArrangement(int id);
	
	
	
}
