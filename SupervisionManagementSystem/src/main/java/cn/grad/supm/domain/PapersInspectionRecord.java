package cn.grad.supm.domain;

import java.io.Serializable;
import java.util.List;

public class PapersInspectionRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Student student;
	private Teacher instructor;
	private Supervisor consultTeacher;
	private List<PapersInspectionSituation> papersInspectionSituationList;
	private String generalComments;
	private String remarks;
	
	public PapersInspectionRecord() {}
	public PapersInspectionRecord(int id, Student student, Teacher instructor, Supervisor consultTeacher,
			List<PapersInspectionSituation> papersInspectionSituationList,String generalComments,
			String remarks) {
		super();
		this.id = id;
		this.student = student;
		this.instructor = instructor;
		this.consultTeacher = consultTeacher;
		this.papersInspectionSituationList = papersInspectionSituationList;
		this.generalComments=generalComments;
		this.remarks=remarks;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getInstructor() {
		return instructor;
	}
	public void setInstructor(Teacher instructor) {
		this.instructor = instructor;
	}
	public Supervisor getConsultTeacher() {
		return consultTeacher;
	}
	public void setConsultTeacher(Supervisor consultTeacher) {
		this.consultTeacher = consultTeacher;
	}
	public List<PapersInspectionSituation> getPapersInspectionSituationList() {
		return papersInspectionSituationList;
	}
	public void setPapersInspectionSituationList(List<PapersInspectionSituation> papersInspectionSituationList) {
		this.papersInspectionSituationList = papersInspectionSituationList;
	}
	public String getGeneralComments() {
		return generalComments;
	}
	public void setGeneralComments(String generalComments) {
		this.generalComments = generalComments;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "PapersInspectionRecord [id=" + id + ", student=" + student + ", instructor=" + instructor
				+ ", consultTeacher=" + consultTeacher + ", papersInspectionSituationList="
				+ papersInspectionSituationList + ", generalComments=" + generalComments + ", remarks=" + remarks + "]";
	}
	
}
