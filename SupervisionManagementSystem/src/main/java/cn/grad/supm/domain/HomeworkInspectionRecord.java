package cn.grad.supm.domain;

import java.io.Serializable;

public class HomeworkInspectionRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private CourseSelection courseSelection;
	private int assignmentsOrReportsFenNum;
	private int assignmentsOrReportsCiNum;
	private int correctionTimes;
	private int seriousCorrection;
	private int moreSeriousCorrection;
	private int generalCorrection;
	private int poorCorrection;
	private String remarks;
	
	public HomeworkInspectionRecord() {}
	public HomeworkInspectionRecord(int id, CourseSelection courseSelection, int assignmentsOrReportsFenNum,
			int assignmentsOrReportsCiNum, int correctionTimes, int seriousCorrection, int moreSeriousCorrection,
			int generalCorrection, int poorCorrection, String remarks) {
		this.id = id;
		this.courseSelection = courseSelection;
		this.assignmentsOrReportsFenNum = assignmentsOrReportsFenNum;
		this.assignmentsOrReportsCiNum = assignmentsOrReportsCiNum;
		this.correctionTimes = correctionTimes;
		this.seriousCorrection = seriousCorrection;
		this.moreSeriousCorrection = moreSeriousCorrection;
		this.generalCorrection = generalCorrection;
		this.poorCorrection = poorCorrection;
		this.remarks = remarks;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CourseSelection getCourseSelection() {
		return courseSelection;
	}
	public void setCourseSelection(CourseSelection courseSelection) {
		this.courseSelection = courseSelection;
	}
	public int getAssignmentsOrReportsFenNum() {
		return assignmentsOrReportsFenNum;
	}
	public void setAssignmentsOrReportsFenNum(int assignmentsOrReportsFenNum) {
		this.assignmentsOrReportsFenNum = assignmentsOrReportsFenNum;
	}
	public int getAssignmentsOrReportsCiNum() {
		return assignmentsOrReportsCiNum;
	}
	public void setAssignmentsOrReportsCiNum(int assignmentsOrReportsCiNum) {
		this.assignmentsOrReportsCiNum = assignmentsOrReportsCiNum;
	}
	public int getCorrectionTimes() {
		return correctionTimes;
	}
	public void setCorrectionTimes(int correctionTimes) {
		this.correctionTimes = correctionTimes;
	}
	public int getSeriousCorrection() {
		return seriousCorrection;
	}
	public void setSeriousCorrection(int seriousCorrection) {
		this.seriousCorrection = seriousCorrection;
	}
	public int getMoreSeriousCorrection() {
		return moreSeriousCorrection;
	}
	public void setMoreSeriousCorrection(int moreSeriousCorrection) {
		this.moreSeriousCorrection = moreSeriousCorrection;
	}
	public int getGeneralCorrection() {
		return generalCorrection;
	}
	public void setGeneralCorrection(int generalCorrection) {
		this.generalCorrection = generalCorrection;
	}
	public int getPoorCorrection() {
		return poorCorrection;
	}
	public void setPoorCorrection(int poorCorrection) {
		this.poorCorrection = poorCorrection;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "HomeworkInspectionRecord [id=" + id + ", courseSelection=" + courseSelection
				+ ", assignmentsOrReportsFenNum=" + assignmentsOrReportsFenNum + ", assignmentsOrReportsCiNum="
				+ assignmentsOrReportsCiNum + ", correctionTimes=" + correctionTimes + ", seriousCorrection="
				+ seriousCorrection + ", moreSeriousCorrection=" + moreSeriousCorrection + ", generalCorrection="
				+ generalCorrection + ", poorCorrection=" + poorCorrection + ", remarks=" + remarks + "]";
	}

}
