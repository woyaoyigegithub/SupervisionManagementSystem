package cn.grad.supm.domain;

import java.io.Serializable;

public class PapersInspectionItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private String options;
	
	public PapersInspectionItems() {}
	public PapersInspectionItems(int id, String content,String options) {
		super();
		this.id = id;
		this.content = content;
		this.options=options;
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
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "PapersInspectionItems [id=" + id + ", content=" + content + ", options=" + options + "]";
	}
	
}
