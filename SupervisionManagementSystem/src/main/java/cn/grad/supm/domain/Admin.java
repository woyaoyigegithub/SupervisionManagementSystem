package cn.grad.supm.domain;

import java.io.Serializable;

public class Admin extends Staff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Admin() {}
	public Admin(String id,String name,String department,String password) {
		super.id=id;
		super.name=name;
		super.department=department;
		super.password=password;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", department=" + department + ", password=" + password
				+ ", power=" + power + "]";
	}
	
}
