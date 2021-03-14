package cn.grad.supm.domain;

import java.io.Serializable;

public class TourInspectionSituation implements Serializable {

	private static final long serialVersionUID = 1L;

	private TourInspectionItems tourInspectionItems;
	private String situation;
	
	public TourInspectionSituation() {}
	public TourInspectionSituation(TourInspectionItems tourInspectionItems, String situation) {
		super();
		this.tourInspectionItems = tourInspectionItems;
		this.situation = situation;
	}
	
	public TourInspectionItems getTourInspectionItems() {
		return tourInspectionItems;
	}
	public void setTourInspectionItems(TourInspectionItems tourInspectionItems) {
		this.tourInspectionItems = tourInspectionItems;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	@Override
	public String toString() {
		return "TourInspectionSituation [tourInspectionItems=" + tourInspectionItems + ", situation=" + situation + "]";
	}
	
}
