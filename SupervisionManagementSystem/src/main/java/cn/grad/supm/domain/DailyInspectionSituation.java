package cn.grad.supm.domain;

import java.io.Serializable;

public class DailyInspectionSituation implements Serializable {

	private static final long serialVersionUID = 1L;

	private DailyInspectionItems dailyInspectionItems;
	private String situation;
	private String suggest;
	
	public DailyInspectionSituation() {}
	public DailyInspectionSituation(DailyInspectionItems dailyInspectionItems, String situation, String suggest) {
		super();
		this.dailyInspectionItems = dailyInspectionItems;
		this.situation = situation;
		this.suggest = suggest;
	}
	
	public DailyInspectionItems getDailyInspectionItems() {
		return dailyInspectionItems;
	}
	public void setDailyInspectionItems(DailyInspectionItems dailyInspectionItems) {
		this.dailyInspectionItems = dailyInspectionItems;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
	@Override
	public String toString() {
		return "DailyInspectionSituation [dailyInspectionItems=" + dailyInspectionItems + ", situation=" + situation
				+ ", suggest=" + suggest + "]";
	}
	
}
