package cn.grad.supm.domain;

import java.io.Serializable;
import java.util.List;

public class InspectionAreaArrangementBuilding implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String academicBuilding;
	private int inspectorsNum;
	private List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList;
	
	public InspectionAreaArrangementBuilding() {}
	public InspectionAreaArrangementBuilding(int id,String acdemicBuilding,int inspectorsNum,
			List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList) {
		this.id=id;
		this.academicBuilding=acdemicBuilding;
		this.inspectorsNum=inspectorsNum;
		this.inspectionAreaArrangementSituationList=inspectionAreaArrangementSituationList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcademicBuilding() {
		return academicBuilding;
	}
	public void setAcademicBuilding(String academicBuilding) {
		this.academicBuilding = academicBuilding;
	}
	public int getInspectorsNum() {
		return inspectorsNum;
	}
	public void setInspectorsNum(int inspectorsNum) {
		this.inspectorsNum = inspectorsNum;
	}
	public List<InspectionAreaArrangementSituation> getInspectionAreaArrangementSituationList() {
		return inspectionAreaArrangementSituationList;
	}
	public void setInspectionAreaArrangementSituationList(
			List<InspectionAreaArrangementSituation> inspectionAreaArrangementSituationList) {
		this.inspectionAreaArrangementSituationList = inspectionAreaArrangementSituationList;
	}
	
	@Override
	public String toString() {
		return "InspectionAreaArrangementBuilding [id=" + id + ", academicBuilding=" + academicBuilding
				+ ", inspectorsNum=" + inspectorsNum + ", inspectionAreaArrangementSituationList="
				+ inspectionAreaArrangementSituationList + "]";
	}
	
}
