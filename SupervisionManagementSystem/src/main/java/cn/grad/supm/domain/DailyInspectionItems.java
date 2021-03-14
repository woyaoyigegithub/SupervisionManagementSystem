package cn.grad.supm.domain;

import java.io.Serializable;

public class DailyInspectionItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	
	public DailyInspectionItems() {}
	public DailyInspectionItems(int id,String content) {
		this.id=id;
		this.content=content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "DailyInspectionItems [id=" + id + ", content=" + content + "]";
	}

}
