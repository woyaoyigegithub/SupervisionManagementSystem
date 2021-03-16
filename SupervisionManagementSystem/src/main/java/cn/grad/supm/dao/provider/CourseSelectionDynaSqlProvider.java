package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.COURSE_SELECTION;
import static cn.grad.supm.utils.Constants.COURSE;
import static cn.grad.supm.utils.Constants.STAFF;
import static cn.grad.supm.utils.Constants.CLASS;
import static cn.grad.supm.utils.Constants.CLASSROOM;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Course;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Teacher;


public class CourseSelectionDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT(COURSE_SELECTION+".*");
				SELECT(COURSE+".name");
				SELECT(STAFF+".name");
				SELECT(CLASS+".name");
				SELECT(CLASSROOM+".name");
				FROM(COURSE_SELECTION);
				LEFT_OUTER_JOIN(COURSE+" ON "+COURSE_SELECTION+".course_id = "+COURSE+".id");
				LEFT_OUTER_JOIN(STAFF+" ON "+COURSE_SELECTION+".teacher_id = "+STAFF+".id");
				LEFT_OUTER_JOIN(CLASS+" ON "+COURSE_SELECTION+".class_id = "+CLASS+".id");
				LEFT_OUTER_JOIN(CLASSROOM+" ON "+COURSE_SELECTION+".classroom_id = "+CLASSROOM+".id");
				
				if(params.get("courseSelection") != null){
					CourseSelection courseSelection = (CourseSelection)params.get("courseSelection");
					Course course=courseSelection.getCourse();
					Teacher teacher=courseSelection.getTeacher();
					Clazz clazz=courseSelection.getClazz();
					Classroom classroom=courseSelection.getClassroom();
					
					if(courseSelection.getId()!=null && !"".equals(courseSelection.getId())){
						WHERE(COURSE_SELECTION+".id LIKE CONCAT ('%',#{courseSelection.id},'%')");
					}
					if(course!=null && course.getId()!=null &&
							!"".equals(course.getId())){
						WHERE(COURSE+".id LIKE CONCAT ('%',#{courseSelection.course.id},'%')");
					}
					if(course!=null && course.getName()!=null &&
							!"".equals(course.getName())){
						WHERE(COURSE+".name LIKE CONCAT ('%',#{courseSelection.course.name},'%')");
					}
					if(teacher!=null && teacher.getId()!=null &&
							!"".equals(teacher.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{courseSelection.teacher.id},'%')");
					}
					if(teacher!=null && teacher.getName()!=null &&
							!"".equals(teacher.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{courseSelection.teacher.name},'%')");
					}
					if(clazz!=null && clazz.getId()!=null &&
							!"".equals(clazz.getId())){
						WHERE(CLASS+".id LIKE CONCAT ('%',#{courseSelection.clazz.id},'%')");
					}
					
					if(clazz!=null && clazz.getName()!=null &&
							!"".equals(clazz.getName())){
						WHERE(CLASS+".name LIKE CONCAT ('%',#{courseSelection.clazz.name},'%')");
					}
					if(courseSelection.getDepartment()!=null && !"".equals(courseSelection.getDepartment())){
						WHERE(COURSE_SELECTION+".department LIKE CONCAT ('%',#{courseSelection.department},'%')");
					}
					if(classroom!=null && classroom.getId()!=null &&
							!"".equals(classroom.getId())){
						WHERE(CLASSROOM+".id LIKE CONCAT ('%',#{courseSelection.classroom.id},'%')");
					}
					if(classroom!=null && classroom.getName()!=null &&
							!"".equals(classroom.getName())){
						WHERE(CLASSROOM+".name LIKE CONCAT ('%',#{courseSelection.classroom.name},'%')");
					}
					if(courseSelection.getSchoolYear()!=null && !"".equals(courseSelection.getSchoolYear())){
						WHERE(COURSE_SELECTION+".school_year LIKE CONCAT ('%',#{courseSelection.schoolYear},'%')");
					}
					if(courseSelection.getSemester()!=0){
						WHERE(COURSE_SELECTION+".semester = #{courseSelection.semester}");
					}
					if(courseSelection.getWeek()!=null && !"".equals(courseSelection.getWeek())){
						WHERE(COURSE_SELECTION+".week LIKE CONCAT ('%',#{courseSelection.week},'%')");
					}
					if(courseSelection.getJieci()!=null && !"".equals(courseSelection.getJieci())){
						WHERE(COURSE_SELECTION+".jieci LIKE CONCAT ('%',#{courseSelection.jieci},'%')");
					}
					if(courseSelection.getBiweekly()!=null && !"".equals(courseSelection.getBiweekly())){
						WHERE(COURSE_SELECTION+".biweekly LIKE CONCAT ('%',#{courseSelection.biweekly},'%')");
					}
					if(courseSelection.getStartingAndEndingWeeks()!=null && !"".equals(courseSelection.getStartingAndEndingWeeks())){
						WHERE(COURSE_SELECTION+".starting_and_ending_weeks LIKE CONCAT ('%',#{courseSelection.startingAndEndingWeeks},'%')");
					}
					if(courseSelection.getNumOfClass()!=null){
						WHERE(COURSE_SELECTION+".num_of_class = #{courseSelection.numOfClass}");
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
				SELECT("count("+COURSE_SELECTION+".id)");
				FROM(COURSE_SELECTION);
				LEFT_OUTER_JOIN(COURSE+" ON "+COURSE_SELECTION+".course_id = "+COURSE+".id");
				LEFT_OUTER_JOIN(STAFF+" ON "+COURSE_SELECTION+".teacher_id = "+STAFF+".id");
				LEFT_OUTER_JOIN(CLASS+" ON "+COURSE_SELECTION+".class_id = "+CLASS+".id");
				LEFT_OUTER_JOIN(CLASSROOM+" ON "+COURSE_SELECTION+".classroom_id = "+CLASSROOM+".id");
				
				if(params.get("courseSelection") != null){
					CourseSelection courseSelection = (CourseSelection)params.get("courseSelection");
					Course course=courseSelection.getCourse();
					Teacher teacher=courseSelection.getTeacher();
					Clazz clazz=courseSelection.getClazz();
					Classroom classroom=courseSelection.getClassroom();
					
					if(courseSelection.getId()!=null && !"".equals(courseSelection.getId())){
						WHERE(COURSE_SELECTION+".id LIKE CONCAT ('%',#{courseSelection.id},'%')");
					}
					if(course!=null && course.getId()!=null &&
							!"".equals(course.getId())){
						WHERE(COURSE+".id LIKE CONCAT ('%',#{courseSelection.course.id},'%')");
					}
					if(course!=null && course.getName()!=null &&
							!"".equals(course.getName())){
						WHERE(COURSE+".name LIKE CONCAT ('%',#{courseSelection.course.name},'%')");
					}
					if(teacher!=null && teacher.getId()!=null &&
							!"".equals(teacher.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{courseSelection.teacher.id},'%')");
					}
					if(teacher!=null && teacher.getName()!=null &&
							!"".equals(teacher.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{courseSelection.teacher.name},'%')");
					}
					if(clazz!=null && clazz.getId()!=null &&
							!"".equals(clazz.getId())){
						WHERE(CLASS+".id LIKE CONCAT ('%',#{courseSelection.clazz.id},'%')");
					}
					if(clazz!=null && clazz.getName()!=null &&
							!"".equals(clazz.getName())){
						WHERE(CLASS+".name LIKE CONCAT ('%',#{courseSelection.clazz.name},'%')");
					}
					if(courseSelection.getDepartment()!=null && !"".equals(courseSelection.getDepartment())){
						WHERE(COURSE_SELECTION+".department LIKE CONCAT ('%',#{courseSelection.department},'%')");
					}
					if(classroom!=null && classroom.getId()!=null &&
							!"".equals(classroom.getId())){
						WHERE(CLASSROOM+".id LIKE CONCAT ('%',#{courseSelection.classroom.id},'%')");
					}
					if(classroom!=null && classroom.getName()!=null &&
							!"".equals(classroom.getName())){
						WHERE(CLASSROOM+".name LIKE CONCAT ('%',#{courseSelection.classroom.name},'%')");
					}
					if(courseSelection.getSchoolYear()!=null && !"".equals(courseSelection.getSchoolYear())){
						WHERE(COURSE_SELECTION+".school_year LIKE CONCAT ('%',#{courseSelection.schoolYear},'%')");
					}
					if(courseSelection.getSemester()!=0){
						WHERE(COURSE_SELECTION+".semester LIKE CONCAT ('%',#{courseSelection.semester},'%')");
					}
					if(courseSelection.getWeek()!=null && !"".equals(courseSelection.getWeek())){
						WHERE(COURSE_SELECTION+".week LIKE CONCAT ('%',#{courseSelection.week},'%')");
					}
					if(courseSelection.getJieci()!=null && !"".equals(courseSelection.getJieci())){
						WHERE(COURSE_SELECTION+".jieci LIKE CONCAT ('%',#{courseSelection.jieci},'%')");
					}
					if(courseSelection.getBiweekly()!=null && !"".equals(courseSelection.getBiweekly())){
						WHERE(COURSE_SELECTION+".biweekly LIKE CONCAT ('%',#{courseSelection.biweekly},'%')");
					}
					if(courseSelection.getStartingAndEndingWeeks()!=null && !"".equals(courseSelection.getStartingAndEndingWeeks())){
						WHERE(COURSE_SELECTION+".starting_and_ending_weeks LIKE CONCAT ('%',#{courseSelection.startingAndEndingWeeks},'%')");
					}
					if(courseSelection.getNumOfClass()!=null){
						WHERE(COURSE_SELECTION+".num_of_class LIKE CONCAT ('%',#{courseSelection.numOfClass},'%')");
					}
					
				}
			}
		}.toString();
	}
	
	
	
}
