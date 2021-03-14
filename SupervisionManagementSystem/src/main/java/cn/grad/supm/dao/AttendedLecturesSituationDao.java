package cn.grad.supm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.AttendedLecturesSituation;

import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_SITUATION;
import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_ITEMS_DAO;

import java.util.List;

public interface AttendedLecturesSituationDao {

	@Select("SELECT * FROM "+ATTENDED_LECTURES_SITUATION+" WHERE attended_lectures_record_id=#{attendedLecturesRecordId}")
	@Results(id="attendedLecturesSituationMap",value= {
			@Result(column = "attended_lectures_items_id",property = "attendedLecturesItems",
					one=@One(select = ATTENDED_LECTURES_ITEMS_DAO+".selectAttendedLecturesItemsById")),
			@Result(column = "evaluation_score",property = "evaluationScore"),
			@Result(column = "remarks",property = "remarks"),
	})
	public List<AttendedLecturesSituation> selectAttendedLecturesSituationListByAttendedLecturesRecordId(@Param("attendedLecturesRecordId") int attendedLecturesRecordId);
	
	
	@Insert("INSERT INTO "+ATTENDED_LECTURES_SITUATION+"(attended_lectures_record_id,attended_lectures_items_id,evaluation_score,remarks) "
			+ "VALUE(#{attendedLecturesRecordId},#{arg1.attendedLecturesItems.id},#{arg1.evaluationScore},#{arg1.remarks})")
	@Options(useGeneratedKeys = true,keyProperty = "arg1.id")
	public void insertAttendedLecturesSituation(@Param("attendedLecturesRecordId")int attendedLecturesRecordId,AttendedLecturesSituation attendedLecturesSituation);
	
	
	@Update("UPDATE "+ATTENDED_LECTURES_SITUATION+" SET evaluation_score=#{arg1.evaluationScore},"
			+ "remarks=#{arg1.remarks} WHERE attended_lectures_record_id=#{attendedLecturesRecordId} AND "
			+ "attended_lectures_items_id=#{arg1.attendedLecturesItems.id}")
	public void updateAttendedLecturesSituation(@Param("attendedLecturesRecordId")int attendedLecturesRecordId,AttendedLecturesSituation attendedLecturesSituation);
	
	
	@Delete("DELETE FROM "+ATTENDED_LECTURES_SITUATION+" WHERE attended_lectures_record_id=#{attendedLecturesRecordId}")
	public void deleteAttendedLecturesSituation(@Param("attendedLecturesRecordId") int attendedLecturesRecordId);
	
}
