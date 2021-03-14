package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_RECORD;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION;
import static cn.grad.supm.utils.Constants.STAFF;
import static cn.grad.supm.utils.Constants.CLASS;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;


public class AttendedLecturesRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(ATTENDED_LECTURES_RECORD+","+COURSE_SELECTION+","+STAFF+","+CLASS);
				WHERE(ATTENDED_LECTURES_RECORD+".course_selection_id = "+COURSE_SELECTION+".id");
				WHERE(ATTENDED_LECTURES_RECORD+".supervisor_id = "+STAFF+".id");
				WHERE(ATTENDED_LECTURES_RECORD+".class_id = "+CLASS+".id");
				if(params.get("attendedLecturesRecord") != null){
					AttendedLecturesRecord attendedLecturesRecord = (AttendedLecturesRecord)params.get("attendedLecturesRecord");
					CourseSelection courseSelection=attendedLecturesRecord.getCourseSelection();
					Teacher teacher=courseSelection.getTeacher();
					Supervisor supervisor=attendedLecturesRecord.getSupervisor();
					Clazz clazz=attendedLecturesRecord.getClazz();
					
					if(attendedLecturesRecord.getId()!=0){
						WHERE(ATTENDED_LECTURES_RECORD+".id = #{attendedLecturesRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null && !"".equals(courseSelection.getId())){
						WHERE(COURSE_SELECTION+".id LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.id},'%') ");
					}
					if(teacher!=null && teacher.getId()!=null && !"".equals(teacher.getId())){
						WHERE(COURSE_SELECTION+".teacher_id LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.teacher.id},'%') ");
					}
//					if(teacher!=null && teacher.getName()!=null && !"".equals(teacher.getName())){
//						WHERE(STAFF+".name LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.teacher.name},'%') ");
//					}
					if(supervisor!=null && supervisor.getId()!=null && !"".equals(supervisor.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{attendedLecturesRecord.supervisor.id},'%') ");
					}
					if(supervisor!=null && supervisor.getName()!=null && !"".equals(supervisor.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{attendedLecturesRecord.supervisor.name},'%') ");
					}
					if(attendedLecturesRecord.getTeachingSection()!=null && !"".equals(attendedLecturesRecord.getTeachingSection())){
						WHERE("teaching_section LIKE CONCAT ('%',#{attendedLecturesRecord.teachingSection},'%') ");
					}
					if(clazz!=null && clazz.getId()!=null && !"".equals(clazz.getId())){
						WHERE(ATTENDED_LECTURES_RECORD+".class_id LIKE CONCAT ('%',#{attendedLecturesRecord.clazz.id},'%') ");
					}
					if(attendedLecturesRecord.getActualNum()!=-1){
						WHERE("actual_num LIKE CONCAT ('%',#{attendedLecturesRecord.actualNum},'%') ");
					}
					if(attendedLecturesRecord.getLateNum()!=-1){
						WHERE("late_num LIKE CONCAT ('%',#{attendedLecturesRecord.lateNum},'%') ");
					}
					if(attendedLecturesRecord.getLeavingEarlyNum()!=-1){
						WHERE("leaving_early_num LIKE CONCAT ('%',#{attendedLecturesRecord.leavingEarlyNum},'%') ");
					}
					if(attendedLecturesRecord.getDate()!=null){
						WHERE("date LIKE CONCAT ('%',#{attendedLecturesRecord.date},'%') ");
					}
					if(attendedLecturesRecord.getWeekly()!=0){
						WHERE("weekly LIKE CONCAT ('%',#{attendedLecturesRecord.weekly},'%') ");
					}
					if(attendedLecturesRecord.getJieci()!=null && !"".equals(attendedLecturesRecord.getJieci())){
						WHERE("jieci LIKE CONCAT ('%',#{attendedLecturesRecord.jieci},'%') ");
					}
					if(attendedLecturesRecord.getTotalScore()!=-1){
						WHERE("total_score LIKE CONCAT ('%',#{attendedLecturesRecord.totalScore},'%') ");
					}
					if(attendedLecturesRecord.getEvaluationLevel()!='\0'){
						WHERE("evaluation_level LIKE CONCAT ('%',#{attendedLecturesRecord.evaluationLevel},'%') ");
					}
					if(attendedLecturesRecord.getExperimentalProcess()!=null && !"".equals(attendedLecturesRecord.getExperimentalProcess()) ){
						WHERE("experimental_process LIKE CONCAT ('%',#{attendedLecturesRecord.experimentalProcess},'%') ");
					}
					if(attendedLecturesRecord.getAlrp()!=null && !"".equals(attendedLecturesRecord.getAlrp())){
						WHERE("alrp LIKE CONCAT ('%',#{attendedLecturesRecord.alrp},'%') ");
					}
					if(attendedLecturesRecord.getDiscussingOrImproving()!=null && !"".equals(attendedLecturesRecord.getDiscussingOrImproving())){
						WHERE("discussing_or_improving LIKE CONCAT ('%',#{attendedLecturesRecord.discussingOrImproving},'%') ");
					}
					if(attendedLecturesRecord.getType()!=null && !"".equals(attendedLecturesRecord.getType())){
						WHERE("type LIKE CONCAT ('%',#{attendedLecturesRecord.type},'%') ");
					}
					
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count("+ATTENDED_LECTURES_RECORD+".id)");
				FROM(ATTENDED_LECTURES_RECORD+","+COURSE_SELECTION+","+STAFF+","+CLASS);
				WHERE(ATTENDED_LECTURES_RECORD+".course_selection_id = "+COURSE_SELECTION+".id");
				WHERE(ATTENDED_LECTURES_RECORD+".supervisor_id = "+STAFF+".id");
				WHERE(ATTENDED_LECTURES_RECORD+".class_id = "+CLASS+".id");
				if(params.get("attendedLecturesRecord") != null){
					AttendedLecturesRecord attendedLecturesRecord = (AttendedLecturesRecord)params.get("attendedLecturesRecord");
					CourseSelection courseSelection=attendedLecturesRecord.getCourseSelection();
					Teacher teacher=courseSelection.getTeacher();
					Supervisor supervisor=attendedLecturesRecord.getSupervisor();
					Clazz clazz=attendedLecturesRecord.getClazz();
					
					if(attendedLecturesRecord.getId()!=0){
						WHERE(ATTENDED_LECTURES_RECORD+".id = #{attendedLecturesRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null && !"".equals(courseSelection.getId())){
						WHERE(COURSE_SELECTION+".id LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.id},'%') ");
					}
					if(teacher!=null && teacher.getId()!=null && !"".equals(teacher.getId())){
						WHERE(COURSE_SELECTION+".teacher_id LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.teacher.id},'%') ");
					}
//					if(teacher!=null && teacher.getName()!=null && !"".equals(teacher.getName())){
//						WHERE(STAFF+".name LIKE CONCAT ('%',#{attendedLecturesRecord.courseSelection.teacher.name},'%') ");
//					}
					if(supervisor!=null && supervisor.getId()!=null && !"".equals(supervisor.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{attendedLecturesRecord.supervisor.id},'%') ");
					}
					if(supervisor!=null && supervisor.getName()!=null && !"".equals(supervisor.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{attendedLecturesRecord.supervisor.name},'%') ");
					}
					if(attendedLecturesRecord.getTeachingSection()!=null && !"".equals(attendedLecturesRecord.getTeachingSection())){
						WHERE("teaching_section LIKE CONCAT ('%',#{attendedLecturesRecord.teachingSection},'%') ");
					}
					if(clazz!=null && clazz.getId()!=null && !"".equals(clazz.getId())){
						WHERE(ATTENDED_LECTURES_RECORD+".class_id LIKE CONCAT ('%',#{attendedLecturesRecord.clazz.id},'%') ");
					}
					if(attendedLecturesRecord.getActualNum()!=-1){
						WHERE("actual_num LIKE CONCAT ('%',#{attendedLecturesRecord.actualNum},'%') ");
					}
					if(attendedLecturesRecord.getLateNum()!=-1){
						WHERE("late_num LIKE CONCAT ('%',#{attendedLecturesRecord.lateNum},'%') ");
					}
					if(attendedLecturesRecord.getLeavingEarlyNum()!=-1){
						WHERE("leaving_early_num LIKE CONCAT ('%',#{attendedLecturesRecord.leavingEarlyNum},'%') ");
					}
					if(attendedLecturesRecord.getDate()!=null){
						WHERE("date LIKE CONCAT ('%',#{attendedLecturesRecord.date},'%') ");
					}
					if(attendedLecturesRecord.getWeekly()!=0){
						WHERE("weekly LIKE CONCAT ('%',#{attendedLecturesRecord.weekly},'%') ");
					}
					if(attendedLecturesRecord.getJieci()!=null && !"".equals(attendedLecturesRecord.getJieci())){
						WHERE("jieci LIKE CONCAT ('%',#{attendedLecturesRecord.jieci},'%') ");
					}
					if(attendedLecturesRecord.getTotalScore()!=-1){
						WHERE("total_score LIKE CONCAT ('%',#{attendedLecturesRecord.totalScore},'%') ");
					}
					if(attendedLecturesRecord.getEvaluationLevel()!='\0'){
						WHERE("evaluation_level LIKE CONCAT ('%',#{attendedLecturesRecord.evaluationLevel},'%') ");
					}
					if(attendedLecturesRecord.getExperimentalProcess()!=null && !"".equals(attendedLecturesRecord.getExperimentalProcess()) ){
						WHERE("experimental_process LIKE CONCAT ('%',#{attendedLecturesRecord.experimentalProcess},'%') ");
					}
					if(attendedLecturesRecord.getAlrp()!=null && !"".equals(attendedLecturesRecord.getAlrp())){
						WHERE("alrp LIKE CONCAT ('%',#{attendedLecturesRecord.alrp},'%')");
					}
					if(attendedLecturesRecord.getDiscussingOrImproving()!=null && !"".equals(attendedLecturesRecord.getDiscussingOrImproving())){
						WHERE("discussing_or_improving LIKE CONCAT ('%',#{attendedLecturesRecord.discussingOrImproving},'%') ");
					}
					if(attendedLecturesRecord.getType()!=null && !"".equals(attendedLecturesRecord.getType())){
						WHERE("type LIKE CONCAT ('%',#{attendedLecturesRecord.type},'%') ");
					}
					
				}
			}
		}.toString();
		
	}
	
	
	
}
