package cn.grad.supm.domain;

import java.io.Serializable;

public class AttendedLecturesSituation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AttendedLecturesItems attendedLecturesItems;
	private int evaluationScore;
	private String remarks;
	
	public AttendedLecturesSituation() {}
	public AttendedLecturesSituation(AttendedLecturesItems attendedLecturesItems,
			int evaluationScore,String remarks) {
		this.attendedLecturesItems=attendedLecturesItems;
		this.evaluationScore=evaluationScore;
		this.remarks=remarks;
	}
	
	public AttendedLecturesItems getAttendedLecturesItems() {
		return attendedLecturesItems;
	}
	public void setAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems) {
		this.attendedLecturesItems = attendedLecturesItems;
	}
	public int getEvaluationScore() {
		return evaluationScore;
	}
	public void setEvaluationScore(int evaluationScore) {
		this.evaluationScore = evaluationScore;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "AttendedLecturesSituation [attendedLecturesItems=" + attendedLecturesItems + ", evaluationScore=" + evaluationScore
				+ ", remarks=" + remarks + "]";
	}
	
}
