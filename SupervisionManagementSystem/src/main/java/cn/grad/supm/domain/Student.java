package cn.grad.supm.domain;

import java.io.Serializable;


public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String sex;
	private Clazz clazz;
	
	public Student() {}
	public Student(String id,String name,String sex,Clazz clazz) {
		this.id=id;
		this.name=name;
		this.sex=sex;
		this.clazz=clazz;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", clazz=" + clazz + "]";
	}
	
}
