package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.utils.tag.PageModel;

public interface PapersInspectionService {

	public List<PapersInspectionRecord> findPapersInspectionList(PapersInspectionRecord papersInspectionRecord,PageModel pageModel);
	
	
	public void addPapersInspection(PapersInspectionRecord papersInspectionRecord);
	
	
	public void modifyPapersInspection(PapersInspectionRecord papersInspectionRecord);
	
	
	public void removePapersInspection(int id);
	
}
