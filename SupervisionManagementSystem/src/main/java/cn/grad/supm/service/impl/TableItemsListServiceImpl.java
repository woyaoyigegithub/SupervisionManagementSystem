package cn.grad.supm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.AttendedLecturesItemsDao;
import cn.grad.supm.dao.DailyInspectionItemsDao;
import cn.grad.supm.dao.PapersInspectionItemsDao;
import cn.grad.supm.dao.TeachingPlanInspectionItemsDao;
import cn.grad.supm.dao.TourInspectionItemsDao;
import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.service.TableItemsListService;


@Service("tableItemsListService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TableItemsListServiceImpl implements TableItemsListService {
	
	@Autowired
	private AttendedLecturesItemsDao attendedLecturesItemsDao;
	@Autowired
	private DailyInspectionItemsDao dailyInspectionItemsDao;
	@Autowired
	private TourInspectionItemsDao tourInspectionItemsDao;
	@Autowired
	private TeachingPlanInspectionItemsDao teachingPlanInspectionItemsDao;
	@Autowired
	private PapersInspectionItemsDao papersInspectionItemsDao; 

	@Override
	public List<AttendedLecturesItems> findAttendedLecturesItemsList(List<Integer> itemsIdList) {
		List<AttendedLecturesItems> attendedLecturesItemList=new ArrayList<AttendedLecturesItems>(); 
		for(int itemsId:itemsIdList) {
			attendedLecturesItemList.add(attendedLecturesItemsDao.selectAttendedLecturesItemsById(itemsId));
		}
		return attendedLecturesItemList;
	}

	
	@Override
	public List<DailyInspectionItems> findDailyInspectionItemsList(List<Integer> itemsIdList) {
		List<DailyInspectionItems> dailyInspectionItemsList=new ArrayList<DailyInspectionItems>();
		for(int itemsId:itemsIdList) {
			dailyInspectionItemsList.add(dailyInspectionItemsDao.selectDailyInspectionItemsById(itemsId));
		}
		return dailyInspectionItemsList;
	}

	
	@Override
	public List<TourInspectionItems> findTourInspectionItemsList(List<Integer> itemsIdList) {
		List<TourInspectionItems> tourInspectionItemsList=new ArrayList<TourInspectionItems>();
		for(int itemsId:itemsIdList) {
			tourInspectionItemsList.add(tourInspectionItemsDao.selectTourInspectionItemsById(itemsId));
		}
		return tourInspectionItemsList;
	}
	 

	@Override
	public List<TeachingPlanInspectionItems> findTeachingPlanInspectionItemsList(List<Integer> itemsIdList) {
		List<TeachingPlanInspectionItems> teachingPlanInspectionItemsList=new ArrayList<TeachingPlanInspectionItems>();
		for(int itemsId:itemsIdList) {
			TeachingPlanInspectionItems teachingPlanInspectionItems=teachingPlanInspectionItemsDao.selectTeachingPlanInspectionItemsById(itemsId);
			//获取选项字符串，并
//			String options=teachingPlanInspectionItems.getOptions();
//			String newOptions="";
//			for(String option:options.split(";")) {
//				option
//			}
			teachingPlanInspectionItemsList.add(teachingPlanInspectionItems);
		}
		return teachingPlanInspectionItemsList;
	}

	@Override
	public List<PapersInspectionItems> findPapersInspectionItemsList(List<Integer> itemsIdList) {
		List<PapersInspectionItems> papersInspectionItemsList=new ArrayList<PapersInspectionItems>();
		for(int itemsId:itemsIdList) {
			papersInspectionItemsList.add(papersInspectionItemsDao.selectPapersInspectionItemsById(itemsId));
		}
		return papersInspectionItemsList;
	}

	
}
