package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_RECORD;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION;
import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_SITUATION_DAO;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION_DAO;
import static cn.grad.supm.utils.Constants.SUPERVISOR_DAO;
import static cn.grad.supm.utils.Constants.CLAZZ_DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.grad.supm.dao.provider.AttendedLecturesRecordDynaSqlProvider;
import cn.grad.supm.domain.AttendedLecturesRecord;

public interface AttendedLecturesRecordDao {

	
	@Select("SELECT * FROM "+ATTENDED_LECTURES_RECORD)
	@Results(id="attendedLecturesRecordMap",value={
		@Result(column = "id",property ="id",id = true),
		@Result(column = "id",property ="attendedLecturesSituationList",
			many=@Many(select = ATTENDED_LECTURES_SITUATION_DAO+".selectAttendedLecturesSituationListByAttendedLecturesRecordId")),
		@Result(column = "course_selection_id",property ="courseSelection",
			one=@One(select = COURSE_SELECTION_DAO+".selectCourseSelectionById")),
		@Result(column = "supervisor_id",property ="supervisor",
			one=@One(select = SUPERVISOR_DAO+".selectSupervisorById")),
		@Result(column = "teaching_section",property ="teachingSection"),
		@Result(column = "class_id",property ="clazz",
			one = @One(select = CLAZZ_DAO+".selectClazzById")),
		@Result(column = "actual_num",property ="actualNum"),
		@Result(column = "late_num",property ="lateNum"),
		@Result(column = "leaving_early_num",property ="leavingEarlyNum"),
		@Result(column = "date",property ="date"),
		@Result(column = "weekly",property ="weekly"),
		@Result(column = "jieci",property ="jieci"),
		@Result(column = "total_score",property ="totalScore"),
		@Result(column = "evaluation_level",property ="evaluationLevel"),
		@Result(column = "experimental_process",property ="experimentalProcess"),
		@Result(column = "alrp",property ="alrp"),
		@Result(column = "discussing_or_improving",property ="discussingOrImproving")
	})
	public List<AttendedLecturesRecord> selectAllAttendedLecturesRecord();
	
	
	@SelectProvider(type = AttendedLecturesRecordDynaSqlProvider.class, method = "selectWithParam")
	@ResultMap("attendedLecturesRecordMap")
	public List<AttendedLecturesRecord> selectAttendedLecturesRecordListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+ATTENDED_LECTURES_RECORD+" WHERE id=#{id}")
	@ResultMap("attendedLecturesRecordMap")
	public AttendedLecturesRecord selectAttendedLecturesRecordById(@Param("id")int id);
	
	
	@Insert("INSERT INTO "+ATTENDED_LECTURES_RECORD+"(course_selection_id,supervisor_id,teaching_section,class_id,"
			+ "actual_num,late_num,leaving_early_num,date,weekly,week,jieci,total_score,evaluation_level,"
			+ "experimental_process,alrp,discussing_or_improving,type) value(#{courseSelection.id},#{supervisor.id},"
			+ "#{teachingSection},#{clazz.id},#{actualNum},#{lateNum},#{leavingEarlyNum},#{date},#{weekly},#{week},#{jieci},"
			+ "#{totalScore},#{evaluationLevel},#{experimentalProcess},#{alrp},#{discussingOrImproving},#{type})")
	@Options(useGeneratedKeys = true)
	public void insertAttendedLecturesRecord(AttendedLecturesRecord attendedLecturesRecord);
	
	
	
	@Insert("UPDATE "+ATTENDED_LECTURES_RECORD+" SET course_selection_id=#{courseSelection.id},supervisor_id=#{supervisor.id},"
			+ "teaching_section=#{teachingSection},class_id=#{clazz.id},actual_num=#{actualNum},late_num=#{lateNum},leaving_early_num=#{leavingEarlyNum},"
			+ "date=#{date},weekly=#{weekly},week=#{week},jieci=#{jieci},total_score=#{totalScore},evaluation_level=#{evaluationLevel},"
			+ "experimental_process=#{experimentalProcess},alrp=#{alrp},discussing_or_improving=#{discussingOrImproving},type=#{type} WHERE id=#{id}")
	public void updateAttendedLecturesRecord(AttendedLecturesRecord attendedLecturesRecord);
	
	
	@Delete("DELETE FROM "+ATTENDED_LECTURES_RECORD+" WHERE id=#{id}")
	public void deleteAttendedLecturesRecord(@Param("id")int id);
	
	
	@SelectProvider(type = AttendedLecturesRecordDynaSqlProvider.class, method = "count")
	public int count(Map<String,Object> params);
	
	
	//通过督导编号按学年学期统计听课次数
	@Select("SELECT school_year,semester,count(*) FROM "+ATTENDED_LECTURES_RECORD+","+COURSE_SELECTION+" WHERE "+
			ATTENDED_LECTURES_RECORD+".course_selection_id="+COURSE_SELECTION+".id AND "+ATTENDED_LECTURES_RECORD+".supervisor_id=#{supervisorId}"+
			"GROUP BY school_year,semester")
	@Results({
		@Result(column = "school_year",property = "schoolYear"),
		@Result(column = "count(*)",property = "count"),
	})
	public List<Map<String,String>> countBySchoolYearAndSemester(@Param("supervisorId")String supervisorId);
	
	
}
