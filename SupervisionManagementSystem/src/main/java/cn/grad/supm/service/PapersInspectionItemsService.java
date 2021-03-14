package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.utils.tag.PageModel;

public interface PapersInspectionItemsService {

	public List<PapersInspectionItems> findPapersInspectionItemsList(PapersInspectionItems papersInspectionItems,PageModel pageModel);
	
	
	public void addPapersInspectionItems(PapersInspectionItems papersInspectionItems);
	
	
	public void modifyPapersInspectionItems(PapersInspectionItems papersInspectionItems);
	
	
	public void removePapersInspectionItems(int id);
	
}
