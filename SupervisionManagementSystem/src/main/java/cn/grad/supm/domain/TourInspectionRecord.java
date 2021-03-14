package cn.grad.supm.domain;

import static cn.grad.supm.utils.Utils.DATEFORMAT;

import java.io.Serializable;
import java.util.Date;
import java.util.List;




public class TourInspectionRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date date;
	private int startingTime;
	private int endingTime;
	private String tourInspectionArea;
	private Supervisor supervisor;
	private List<TourInspectionSituation> tourInspectionSituationList;
	
	
	public TourInspectionRecord() {}
	public TourInspectionRecord(int id, Date date, int startingTime, int endingTime, String tourInspectionArea,
			Supervisor supervisor, List<TourInspectionSituation> tourInspectionSituationList) {
		super();
		this.id = id;
		this.date = date;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.tourInspectionArea = tourInspectionArea;
		this.supervisor = supervisor;
		this.tourInspectionSituationList = tourInspectionSituationList;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(int startingTime) {
		this.startingTime = startingTime;
	}
	public int getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(int endingTime) {
		this.endingTime = endingTime;
	}
	public String getTourInspectionArea() {
		return tourInspectionArea;
	}
	public void setTourInspectionArea(String tourInspectionArea) {
		this.tourInspectionArea = tourInspectionArea;
	}
	public Supervisor getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	public List<TourInspectionSituation> getTourInspectionSituationList() {
		return tourInspectionSituationList;
	}
	public void setTourInspectionSituationList(List<TourInspectionSituation> tourInspectionSituationList) {
		this.tourInspectionSituationList = tourInspectionSituationList;
	}
	
	@Override
	public String toString() {
		return "TourInspectionRecord [id=" + id + ", date=" + getDate() + ", startingTime=" + startingTime
				+ ", endingTime=" + endingTime + ", tourInspectionArea=" + tourInspectionArea + ", supervisor="
				+ supervisor + ", tourInspectionSituationList=" + tourInspectionSituationList + "]";
	}
	
}
