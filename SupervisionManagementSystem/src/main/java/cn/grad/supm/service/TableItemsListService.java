package cn.grad.supm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.domain.TourInspectionItems;


@Service("TableItemsListService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public interface TableItemsListService {

	public List<AttendedLecturesItems> findAttendedLecturesItemsList(List<Integer> itemsIdList);
	
	public List<DailyInspectionItems> findDailyInspectionItemsList(List<Integer> itemsIdList);
	
	public List<TourInspectionItems> findTourInspectionItemsList(List<Integer> itemsIdList);
	
	public List<TeachingPlanInspectionItems> findTeachingPlanInspectionItemsList(List<Integer> itemsIdList);
	
	public List<PapersInspectionItems> findPapersInspectionItemsList(List<Integer> itemsIdList);
}
