package cn.grad.supm.domain;

import java.io.Serializable;

public class PapersInspectionSituation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PapersInspectionItems papersInspectionItems;
	private String situation;
	private String remarks;
	
	public PapersInspectionSituation() {}
	public PapersInspectionSituation(PapersInspectionItems papersInspectionItems, String situation, String remarks) {
		this.papersInspectionItems = papersInspectionItems;
		this.situation = situation;
		this.remarks = remarks;
	}
	
	public PapersInspectionItems getPapersInspectionItems() {
		return papersInspectionItems;
	}
	public void setPapersInspectionItems(PapersInspectionItems papersInspectionItems) {
		this.papersInspectionItems = papersInspectionItems;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "PapersInspectionSituation [papersInspectionItems=" + papersInspectionItems + ", situation=" + situation
				+ ", remarks=" + remarks + "]";
	}

}
