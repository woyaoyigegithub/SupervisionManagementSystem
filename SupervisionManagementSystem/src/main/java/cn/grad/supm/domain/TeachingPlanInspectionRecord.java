package cn.grad.supm.domain;

import java.io.Serializable;
import java.util.List;

public class TeachingPlanInspectionRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private CourseSelection courseSelection;
	private List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList;
	private String discussingAndAffirming;
	
	public TeachingPlanInspectionRecord() {}
	public TeachingPlanInspectionRecord(int id, CourseSelection courseSelection,
			List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList, 
			String discussingAndAffirming) {
		this.id = id;
		this.courseSelection = courseSelection;
		this.teachingPlanInspectionSituationList=teachingPlanInspectionSituationList;
		this.discussingAndAffirming = discussingAndAffirming;
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
	public List<TeachingPlanInspectionSituation> getTeachingPlanInspectionSituationList() {
		return teachingPlanInspectionSituationList;
	}
	public void setTeachingPlanInspectionSituationList(
			List<TeachingPlanInspectionSituation> teachingPlanInspectionSituationList) {
		this.teachingPlanInspectionSituationList = teachingPlanInspectionSituationList;
	}
	public String getDiscussingAndAffirming() {
		return discussingAndAffirming;
	}
	public void setDiscussingAndAffirming(String discussingAndAffirming) {
		this.discussingAndAffirming = discussingAndAffirming;
	}
	
	@Override
	public String toString() {
		return "TeachingPlanInspectionRecord [id=" + id + ", courseSelection=" + courseSelection
				+ ", teachingPlanInspectionSituationList=" + teachingPlanInspectionSituationList
				+ ", discussingAndAffirming=" + discussingAndAffirming + "]";
	}
	
}
