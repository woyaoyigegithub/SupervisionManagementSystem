package cn.grad.supm.domain;

import java.io.Serializable;

public class AttendedLecturesItems implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String content;
	private int score;
	
	public AttendedLecturesItems() {}
	public AttendedLecturesItems(int id, String content, int score) {
		super();
		this.id = id;
		this.content = content;
		this.score = score;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "AttendedLecturesItems [id=" + id + ", content=" + content + ", score=" + score + "]";
	}
}
