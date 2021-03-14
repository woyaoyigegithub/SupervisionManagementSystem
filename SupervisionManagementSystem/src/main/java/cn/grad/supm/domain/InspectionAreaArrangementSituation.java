package cn.grad.supm.domain;

import java.io.Serializable;

public class InspectionAreaArrangementSituation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Supervisor supervisor;
	private String week;
	private String earlyMiddleLate;
	
	public InspectionAreaArrangementSituation() {}
	public InspectionAreaArrangementSituation(int id, Supervisor supervisor, String week, String earlyMiddleLate) {
		super();
		this.id = id;
		this.supervisor = supervisor;
		this.week = week;
		this.earlyMiddleLate = earlyMiddleLate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Supervisor getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getEarlyMiddleLate() {
		return earlyMiddleLate;
	}
	public void setEarlyMiddleLate(String earlyMiddleLate) {
		this.earlyMiddleLate = earlyMiddleLate;
	}
	
	@Override
	public String toString() {
		return "InspectionAreaArrangementSituation [id=" + id + ", supervisor=" + supervisor + ", week=" + week
				+ ", earlyMiddleLate=" + earlyMiddleLate + "]";
	}
	
	
	
}
