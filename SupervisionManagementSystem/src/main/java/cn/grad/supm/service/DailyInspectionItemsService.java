package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.utils.tag.PageModel;

public interface DailyInspectionItemsService {

	public List<DailyInspectionItems> findDailyInspectionItemsList(DailyInspectionItems dailyInspectionItems,PageModel pageModel);
	
	
	public void addDailyInspectionItems(DailyInspectionItems dailyInspetionItems);
	
	
	public void modifyDailyInspectionItems(DailyInspectionItems dailyInspetionItems);
	
	
	public void removeDailyInspectionItems(int id);
	
}
