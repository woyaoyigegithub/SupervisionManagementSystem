package cn.grad.supm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.AttendedLecturesItemsDao;
import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.service.AttendedLecturesItemsService;
import cn.grad.supm.utils.tag.PageModel;

@Service("attendedLecturesItemsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AttendedLecturesItemsServiceImpl implements AttendedLecturesItemsService {

	@Autowired
	private AttendedLecturesItemsDao attendedLecturesItemsDao; 

	
	@Override
	public List<AttendedLecturesItems> findAttendedLecturesItemsList(AttendedLecturesItems attendedLecturesItems,PageModel pageModel) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("attendedLecturesItems", attendedLecturesItems);
		
		int recordCount=attendedLecturesItemsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0) {
			params.put("pageModel",pageModel);
		}
		List<AttendedLecturesItems> attendedLecturesItemsList=attendedLecturesItemsDao.selectAttendedLecturesItemsListByPage(params);
		return attendedLecturesItemsList;
	}

	@Override
	public void addAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems) {
		attendedLecturesItemsDao.insertAttendedLecturesItems(attendedLecturesItems);
	}
	
	
	@Override
	public void modifyAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems) {
		attendedLecturesItemsDao.updateAttendedLecturesItems(attendedLecturesItems);
	}

	@Override
	public void removeAttendedLecturesItems(int id) {
		attendedLecturesItemsDao.deleteAttendedLecturesItems(id);
	}
	
}
