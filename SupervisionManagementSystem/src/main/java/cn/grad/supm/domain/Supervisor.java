package cn.grad.supm.domain;

import java.io.Serializable;

public class Supervisor extends Staff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Supervisor() {}
	public Supervisor(String id,String name,String department) {
		super.id=id;
		super.name=name;
		super.department=department;
	}
	
	@Override
	public String toString() {
		return "Supervisor [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
	
	
	

}
