package cn.grad.supm.domain;

import java.io.Serializable;

public class Clazz implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String major;
	private String grade;
	private String department;
	private String undergOrJun;
	
	public Clazz() {}
	public Clazz(String id, String name, String major, String grade, String department, String undergOrJun) {
		super();
		this.id = id;
		this.name = name;
		this.major = major;
		this.grade = grade;
		this.department = department;
		this.undergOrJun = undergOrJun;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUndergOrJun() {
		return undergOrJun;
	}
	public void setUndergOrJun(String undergOrJun) {
		this.undergOrJun = undergOrJun;
	}

	@Override
	public String toString() {
		return "Clazz [id=" + id + ", name=" + name + ", major=" + major + ", grade=" + grade + ", department="
				+ department + ", undergOrJun=" + undergOrJun+ "]";
	}
}
