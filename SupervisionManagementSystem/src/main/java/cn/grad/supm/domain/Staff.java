package cn.grad.supm.domain;

import java.io.Serializable;

public class Staff implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String name;
	protected String department;
	protected String password;
	protected Power power;
	
	public Staff() {}
	public Staff(String id,String name,String department,String password,Power power) {
		this.id=id;
		this.name=name;
		this.department=department;
		this.password=password;
		this.power=power;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Power getPower() {
		return power;
	}
	public void setPower(Power power) {
		this.power = power;
	}
	
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", department=" + department + ", password=" + password
				+ ", power=" + power + "]";
	}
}
