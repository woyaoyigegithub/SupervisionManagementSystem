package cn.grad.supm.domain;

import static cn.grad.supm.utils.Utils.DATEFORMAT;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class AttendedLecturesRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private CourseSelection courseSelection;
	private Supervisor supervisor;
	private String teachingSection;
	private Clazz clazz;
	private int actualNum;
	private int lateNum;
	private int leavingEarlyNum;
	private Date date;
	private int weekly;
	private String week;
	private String jieci;
	private List<AttendedLecturesSituation> attendedLecturesSituationList;
	private int totalScore;
	private char evaluationLevel;
	private String experimentalProcess;
	private String alrp;
	private String discussingOrImproving;
	private String type;
	
	
	public AttendedLecturesRecord() {
		this.actualNum=-1;
		this.lateNum=-1;
		this.leavingEarlyNum=-1;
		this.totalScore=-1;
	}
	public AttendedLecturesRecord(int id,CourseSelection courseSelection,Supervisor supervisor,
			String teachingSection,Clazz clazz,int actualNum,int lateNum,int leavingEarlyNum,
			Date date,int weekly,String week,String jieci,
			List<AttendedLecturesSituation> attendedLecturesSituationList,
			int totalScore,char evaluationLevel,String experimentalProcess,String alrp,
			String discussingOrImproving,String type) {
		this.id=id;
		this.courseSelection=courseSelection;
		this.supervisor=supervisor;
		this.teachingSection=teachingSection;
		this.clazz=clazz;
		this.actualNum=actualNum;
		this.lateNum=lateNum;
		this.leavingEarlyNum=leavingEarlyNum;
		this.date=date;
		this.weekly=weekly;
		this.week=week;
		this.jieci=jieci;
		this.attendedLecturesSituationList=attendedLecturesSituationList;
		this.totalScore=totalScore;
		this.evaluationLevel=evaluationLevel;
		this.experimentalProcess=experimentalProcess;
		this.alrp=alrp;
		this.discussingOrImproving=discussingOrImproving;
		this.type=type;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CourseSelection getCourseSelection() {
		return courseSelection;
	}
	public void setCourseSelection(CourseSelection courseSelection) {
		this.courseSelection = courseSelection;
	}
	public Supervisor getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	public String getTeachingSection() {
		return teachingSection;
	}
	public void setTeachingSection(String teachingSection) {
		this.teachingSection = teachingSection;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public int getActualNum() {
		return actualNum;
	}
	public void setActualNum(int actualNum) {
		this.actualNum = actualNum;
	}
	public int getLateNum() {
		return lateNum;
	}
	public void setLateNum(int lateNum) {
		this.lateNum = lateNum;
	}
	public int getLeavingEarlyNum() {
		return leavingEarlyNum;
	}
	public void setLeavingEarlyNum(int leavingEarlyNum) {
		this.leavingEarlyNum = leavingEarlyNum;
	}
	public String getDate() {
		try { return DATEFORMAT.format(date);}
		catch (Exception e) { return null; }
	}
	public void setDate(String dataStr) {
		try {
			this.date = DATEFORMAT.parse(dataStr);
		}catch (Exception e) {
			this.date=null;
		}
	}
	public int getWeekly() {
		return weekly;
	}
	public void setWeekly(int weekly) {
		this.weekly = weekly;
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
	public List<AttendedLecturesSituation> getAttendedLecturesSituationList() {
		return attendedLecturesSituationList;
	}
	public void setAttendedLecturesSituationList(List<AttendedLecturesSituation> attendedLecturesSituationList) {
		this.attendedLecturesSituationList=attendedLecturesSituationList;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public char getEvaluationLevel() {
		return evaluationLevel;
	}
	public void setEvaluationLevel(char evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}
	public String getExperimentalProcess() {
		return experimentalProcess;
	}
	public void setExperimentalProcess(String experimentalProcess) {
		this.experimentalProcess = experimentalProcess;
	}
	public String getAlrp() {
		return alrp;
	}
	public void setAlrp(String alrp) {
		this.alrp = alrp;
	}
	public String getDiscussingOrImproving() {
		return discussingOrImproving;
	}
	public void setDiscussingOrImproving(String discussingOrImproving) {
		this.discussingOrImproving = discussingOrImproving;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		return "AttendedLecturesRecord [id=" + id + ", courseSelection=" + courseSelection + ", supervisor="
				+ supervisor + ", teachingSection=" + teachingSection + ", clazz=" + clazz + ", actualNum=" + actualNum
				+ ", lateNum=" + lateNum + ", leavingEarlyNum=" + leavingEarlyNum + ", date=" + getDate() + ", weekly="
				+ weekly + ", week=" + week + ", jieci=" + jieci + ", attendedLecturesSituationList="
				+ attendedLecturesSituationList + ", totalScore=" + totalScore + ", evaluationLevel=" + evaluationLevel
				+ ", experimentalProcess=" + experimentalProcess + ", alrp=" + alrp + ", discussingOrImproving="
				+ discussingOrImproving + ", type=" + type + "]";
	}
	
	
}
