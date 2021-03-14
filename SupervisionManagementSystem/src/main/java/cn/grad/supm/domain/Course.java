package cn.grad.supm.domain;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String nature;
	private String type;
	
	public Course() {}
	public Course(String id, String name, String nature, String type) {
		super();
		this.id = id;
		this.name = name;
		this.nature = nature;
		this.type = type;
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
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", nature=" + nature + ", type=" + type + "]";
	}
	
}
