package cn.grad.supm.domain;

import static cn.grad.supm.utils.Utils.DATEFORMAT;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InspectionAreaArrangement implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String schoolYear;
	private int semester;
	private String startingAndEndingWeeks;
	private Date publicationTime;
	private List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList;
	
	
	public InspectionAreaArrangement() {
		this.semester=-1;
	}
	public InspectionAreaArrangement(int id, String schoolYear, int semester, String startingAndEndingWeeks,
			Date publicationTime, List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList) {
		super();
		this.id = id;
		this.schoolYear = schoolYear;
		this.semester = semester;
		this.startingAndEndingWeeks = startingAndEndingWeeks;
		this.publicationTime = publicationTime;
		this.inspectionAreaArrangementBuildingList=inspectionAreaArrangementBuildingList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getStartingAndEndingWeeks() {
		return startingAndEndingWeeks;
	}
	public void setStartingAndEndingWeeks(String startingAndEndingWeeks) {
		this.startingAndEndingWeeks = startingAndEndingWeeks;
	}
	public String getPublicationTime() {
		try { return DATEFORMAT.format(publicationTime);}
		catch (Exception e) { return null; }
	}
	public void setPublicationTime(String publicationTime) {
		try {
			this.publicationTime = DATEFORMAT.parse(publicationTime);
		}catch (Exception e) {
			this.publicationTime=null;
		}
	}
	public List<InspectionAreaArrangementBuilding> getInspectionAreaArrangementBuildingList() {
		return inspectionAreaArrangementBuildingList;
	}
	public void setInspectionAreaArrangementBuildingList(
			List<InspectionAreaArrangementBuilding> inspectionAreaArrangementBuildingList) {
		this.inspectionAreaArrangementBuildingList = inspectionAreaArrangementBuildingList;
	}
	
	@Override
	public String toString() {
		return "InspectionAreaArrangement [id=" + id + ", schoolYear=" + schoolYear + ", semester=" + semester
				+ ", startingAndEndingWeeks=" + startingAndEndingWeeks + ", publicationTime=" + getPublicationTime()
				+ ", inspectionAreaArrangementBuildingList=" + inspectionAreaArrangementBuildingList + "]";
	}
	
}
