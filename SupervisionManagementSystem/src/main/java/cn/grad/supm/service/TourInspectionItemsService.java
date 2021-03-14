package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.utils.tag.PageModel;

public interface TourInspectionItemsService {

	public List<TourInspectionItems> findTourInspectionItemsList(TourInspectionItems tourInspectionItems,PageModel pageModel);
	
	
	public void addTourInspectionItems(TourInspectionItems tourInspectionItems);
	
	
	public void modifyTourInspectionItems(TourInspectionItems tourInspectionItems);
	
	
	public void removeTourInspectionItems(int id);
}
