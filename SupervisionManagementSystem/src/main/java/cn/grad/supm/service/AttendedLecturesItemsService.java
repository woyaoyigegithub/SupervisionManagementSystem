package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.utils.tag.PageModel;

public interface AttendedLecturesItemsService {

	public List<AttendedLecturesItems> findAttendedLecturesItemsList(AttendedLecturesItems attendedLecturesItems,PageModel pageModel);

	
	public void addAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems);
	
	
	public void modifyAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems);
	
	
	public void removeAttendedLecturesItems(int id);
	
	
}
