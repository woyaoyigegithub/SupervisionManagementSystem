package cn.grad.supm.service.impl;

import static cn.grad.supm.utils.Constants.SUPERVISOR;
import static cn.grad.supm.utils.Constants.ADMIN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.grad.supm.dao.CourseSelectionDao;
import cn.grad.supm.dao.InspectionAreaArrangementDao;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.domain.InspectionAreaArrangementBuilding;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.service.Helper;


@Service("HelperService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class HelperImpl implements Helper {
	
	@Autowired
	private CourseSelectionDao courseSelectionDao;
	@Autowired
	private InspectionAreaArrangementDao inspectionAreaArrangementDao;
	

	@Override
	public CourseSelection findCourseSelectionById(String id) {
		return courseSelectionDao.selectCourseSelectionById(id);
	}

	
	//从最新的督查区域安排中查询今天督导的编号列表
	@Override
	public List<Integer> findInspectionAreaArrangementSituationIdList(Staff staff) {
		String staffId=staff.getId();
		String identity=staff.getPower().getName();
		
		List<Integer> inspectionAreaArrangementSituationIdList=new ArrayList<Integer>();
		
		String[] weeks= {"周日","周一","周二","周三","周四","周五","周六"};
		int day=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
		InspectionAreaArrangement inspectionAreaArrangement=inspectionAreaArrangementDao.selectLastInspectionAreaArrangement();
		if(inspectionAreaArrangement==null) { return null; }
		for(InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding:inspectionAreaArrangement.getInspectionAreaArrangementBuildingList()){
			for(InspectionAreaArrangementSituation inspectionAreaArrangementSituation:inspectionAreaArrangementBuilding.getInspectionAreaArrangementSituationList()) {
				if(inspectionAreaArrangementSituation.getSupervisor()!=null) {
					if(SUPERVISOR.equals(identity) && staffId.equals(inspectionAreaArrangementSituation.getSupervisor().getId()) && 
							weeks[day].equals(inspectionAreaArrangementSituation.getWeek())) {
						inspectionAreaArrangementSituationIdList.add(inspectionAreaArrangementSituation.getId());
					}else if(ADMIN.equals(identity)) {
						inspectionAreaArrangementSituationIdList.add(inspectionAreaArrangementSituation.getId());
					}
				}
				
			}
		}
		return inspectionAreaArrangementSituationIdList;
	}

	
}
