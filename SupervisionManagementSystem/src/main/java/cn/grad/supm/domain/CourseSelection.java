package cn.grad.supm.domain;

import java.io.Serializable;

public class CourseSelection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Course course;
	private Teacher teacher;
	private Clazz clazz;
	private String department;
	private Classroom classroom;
	private String schoolYear;
	private int semester;
	private String week;
	private String jieci;
	private String biweekly;
	private String startingAndEndingWeeks;
	private Integer numOfClass;
	
	public CourseSelection() {}
	public CourseSelection(String id,Course course,Teacher teacher,Clazz clazz,String department,
			Classroom classroom,String schoolYear,int semester,String week,String jieci,
			String biweekly,String startingAndEndingWeeks,int numOfClass) {
		this.id=id;
		this.course=course;
		this.teacher=teacher;
		this.clazz=clazz;
		this.department=department;
		this.classroom=classroom;
		this.schoolYear=schoolYear;
		this.semester=semester;
		this.week=week;
		this.jieci=jieci;
		this.biweekly=biweekly;
		this.startingAndEndingWeeks=startingAndEndingWeeks;
		this.numOfClass=numOfClass;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getJieci() {
		return jieci;
	}
	public void setJieci(String jieci) {
		this.jieci = jieci;
	}
	public String getBiweekly() {
		return biweekly;
	}
	public void setBiweekly(String biweekly) {
		this.biweekly = biweekly;
	}
	public String getStartingAndEndingWeeks() {
		return startingAndEndingWeeks;
	}
	public void setStartingAndEndingWeeks(String startingAndEndingWeeks) {
		this.startingAndEndingWeeks = startingAndEndingWeeks;
	}
	public Integer getNumOfClass() {
		return numOfClass;
	}
	public void setNumOfClass(Integer numOfClass) {
		this.numOfClass = numOfClass;
	}
	
	@Override
	public String toString() {
		return "CourseSelection [id=" + id + ", course=" + course + ", teacher=" + teacher + ", clazz=" + clazz
				+ ", department=" + department + ", classroom=" + classroom + ", schoolYear=" + schoolYear
				+ ", semester=" + semester + ", week=" + week + ", jieci=" + jieci + ", biweekly=" + biweekly
				+ ", startingAndEndingWeeks=" + startingAndEndingWeeks + ", numOfClass=" + numOfClass + "]";
	}
	
}
