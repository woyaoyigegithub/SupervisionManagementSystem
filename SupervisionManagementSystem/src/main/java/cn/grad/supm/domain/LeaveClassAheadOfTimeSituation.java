package cn.grad.supm.domain;

import java.io.Serializable;

public class LeaveClassAheadOfTimeSituation implements Serializable {

	private static final long serialVersionUID = 1L;

	private CourseSelection courseSelection;
	private Clazz clazz;
	private String normalTime;
	private String actualTime;
	
	public LeaveClassAheadOfTimeSituation() {}
	public LeaveClassAheadOfTimeSituation(CourseSelection courseSelection, Clazz clazz, String normalTime,
			String actualTime) {
		this.courseSelection = courseSelection;
		this.clazz = clazz;
		this.normalTime = normalTime;
		this.actualTime = actualTime;
	}
	
	public CourseSelection getCourseSelection() {
		return courseSelection;
	}
	public void setCourseSelection(CourseSelection courseSelection) {
		this.courseSelection = courseSelection;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public String getNormalTime() {
		return normalTime;
	}
	public void setNormalTime(String normalTime) {
		this.normalTime = normalTime;
	}
	public String getActualTime() {
		return actualTime;
	}
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}
	
	@Override
	public String toString() {
		return "LeaveClassAheadOfTimeSituation [courseSelection=" + courseSelection + ", clazz=" + clazz
				+ ", normalTime=" + normalTime + ", actualTime=" + actualTime + "]";
	}
	
}
