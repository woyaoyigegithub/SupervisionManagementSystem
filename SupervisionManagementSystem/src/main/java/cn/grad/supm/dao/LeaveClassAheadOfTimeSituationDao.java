package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.LEAVE_CLASS_AHEAD_OF_TIME_SITUATION;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION_DAO;
import static cn.grad.supm.utils.Constants.CLAZZ_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.grad.supm.domain.LeaveClassAheadOfTimeSituation;

public interface LeaveClassAheadOfTimeSituationDao {

	@Select("SELECT * FROM "+LEAVE_CLASS_AHEAD_OF_TIME_SITUATION+" WHERE daily_inspection_record_id=#{dailyInspectionRecordId}")
	@Results(id="leaveClassAheadOfTimeSituationMap",value={
			@Result(column = "course_selection_id",property = "courseSelection",
					one=@One(select = COURSE_SELECTION_DAO+".selectCourseSelectionById")),
			@Result(column = "normal_time",property = "normalTime"),
			@Result(column = "actual_time",property = "actualTime"),
			@Result(column = "class_id",property = "clazz",
			one=@One(select = CLAZZ_DAO+".selectClazzById"))
	})
	public List<LeaveClassAheadOfTimeSituation> selectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId(@Param("dailyInspectionRecordId")int dailyInspectionRecordId);
	
	
	@Insert("INSERT INTO "+LEAVE_CLASS_AHEAD_OF_TIME_SITUATION+"(daily_inspection_record_id,course_selection_id,normal_time,actual_time,class_id) "
			+ "VALUE(#{dailyInspectionRecordId},#{arg1.courseSelection.id},#{arg1.normalTime},#{arg1.actualTime},#{arg1.clazz.id})")
	public void insertLeaveClassAheadOfTimeSituation(@Param("dailyInspectionRecordId")int dailyInspectionRecordId,LeaveClassAheadOfTimeSituation leaveClassAheadOfTimeSituation);
	
	
	@Delete("DELETE FROM "+LEAVE_CLASS_AHEAD_OF_TIME_SITUATION+" WHERE daily_inspection_record_id=#{dailyInspectionRecordId}")
	public void deleteLeaveClassAheadOfTimeSituation(@Param("dailyInspectionRecordId")int dailyInspectionRecordId);
}
