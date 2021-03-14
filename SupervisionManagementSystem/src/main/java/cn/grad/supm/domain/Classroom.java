package cn.grad.supm.domain;

import java.io.Serializable;

public class Classroom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int seatingCapacity;
	private String type;
	private String buildingNum;
	
	public Classroom() {
		seatingCapacity=-1;
	}
	public Classroom(String id,String name,int seatingCapacity,String type,String buildingNum) {
		this.id=id;
		this.name=name;
		this.seatingCapacity=seatingCapacity;
		this.type=type;
		this.buildingNum=buildingNum;
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
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	
	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", seatingCapacity=" + seatingCapacity + ", type=" + type
				+ ", buildingNum=" + buildingNum + "]";
	}

}
