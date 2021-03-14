package cn.grad.supm.domain;

import java.io.Serializable;

public class TeachingPlanInspectionSituation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TeachingPlanInspectionItems teachingPlanInspectionItems;
	private String situation;
	
	public TeachingPlanInspectionSituation() {}
	public TeachingPlanInspectionSituation(TeachingPlanInspectionItems teachingPlanInspectionItems, String situation) {
		this.teachingPlanInspectionItems = teachingPlanInspectionItems;
		this.situation = situation;
	}
	
	public TeachingPlanInspectionItems getTeachingPlanInspectionItems() {
		return teachingPlanInspectionItems;
	}
	public void setTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems) {
		this.teachingPlanInspectionItems = teachingPlanInspectionItems;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	@Override
	public String toString() {
		return "TeachingPlanInspectionSituation [teachingPlanInspectionItems=" + teachingPlanInspectionItems
				+ ", situation=" + situation + "]";
	}
	
}
