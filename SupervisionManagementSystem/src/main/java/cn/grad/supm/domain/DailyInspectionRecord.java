package cn.grad.supm.domain;

import static cn.grad.supm.utils.Utils.DATEFORMAT;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class DailyInspectionRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int weekly;
	private InspectionAreaArrangementSituation inspectionAreaArrangementSituation;
	private String week;
	private Date date;
	private List<DailyInspectionSituation> dailyInspectionSituationList;
	private List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList;
	
	
	public DailyInspectionRecord() {}
	public DailyInspectionRecord(int id, int weekly, InspectionAreaArrangementSituation inspectionAreaArrangementSituation, String week, Date date,
			List<DailyInspectionSituation> dailyInspectionSituationList,
			List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList) {
		this.id = id;
		this.inspectionAreaArrangementSituation = inspectionAreaArrangementSituation;
		this.weekly = weekly;
		this.week = week;
		this.date = date;
		this.dailyInspectionSituationList = dailyInspectionSituationList;
		this.leaveClassAheadOfTimeSituationList = leaveClassAheadOfTimeSituationList;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeekly() {
		return weekly;
	}
	public void setWeekly(int weekly) {
		this.weekly = weekly;
	}
	public InspectionAreaArrangementSituation getInspectionAreaArrangementSituation() {
		return inspectionAreaArrangementSituation;
	}
	public void setInspectionAreaArrangementSituation(
			InspectionAreaArrangementSituation inspectionAreaArrangementSituation) {
		this.inspectionAreaArrangementSituation = inspectionAreaArrangementSituation;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDate() {
		try { return DATEFORMAT.format(date);}
		catch (Exception e) { return null; }
	}
	public void setDate(String dataStr) {
		try {
			this.date = DATEFORMAT.parse(dataStr);
		}catch (Exception e) {
			this.date=null;
		}
	}
	public List<DailyInspectionSituation> getDailyInspectionSituationList() {
		return dailyInspectionSituationList;
	}
	public void setDailyInspectionSituationList(List<DailyInspectionSituation> dailyInspectionSituationList) {
		this.dailyInspectionSituationList = dailyInspectionSituationList;
	}
	public List<LeaveClassAheadOfTimeSituation> getLeaveClassAheadOfTimeSituationList() {
		return leaveClassAheadOfTimeSituationList;
	}
	public void setLeaveClassAheadOfTimeSituationList(List<LeaveClassAheadOfTimeSituation> leaveClassAheadOfTimeSituationList) {
		this.leaveClassAheadOfTimeSituationList = leaveClassAheadOfTimeSituationList;
	}
	
	
	@Override
	public String toString() {
		return "DailyInspectionRecord [id=" + id + ", weekly=" + weekly + ", inspectionAreaArrangementSituation="
				+ inspectionAreaArrangementSituation + ", week=" + week + ", date=" + getDate()
				+ ", dailyInspectionSituationList=" + dailyInspectionSituationList
				+ ", leaveClassAheadOfTimeSituationList=" + leaveClassAheadOfTimeSituationList + "]";
	}
	
}
