package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.HOMEWORK_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION_DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.HomeworkInspectionRecordDynaSqlProvider;
import cn.grad.supm.domain.HomeworkInspectionRecord;;

public interface HomeworkInspectionRecordDao {

	@Select("SELECT * FROM "+HOMEWORK_INSPECTION_RECORD)
	@Results(id="homeworkInspectionRecordMap",value= {
			@Result(column = "course_selection_id",property = "courseSelection",
						one=@One(select=COURSE_SELECTION_DAO+".selectCourseSelectionById")),
			@Result(column = "assignments_or_reports_fen_num",property = "assignmentsOrReportsFenNum"),
			@Result(column = "assignments_or_reports_ci_num",property = "assignmentsOrReportsCiNum"),
			@Result(column = "correction_times",property = "correctionTimes"),
			@Result(column = "serious_correction",property = "seriousCorrection"),
			@Result(column = "more_serious_correction",property = "moreSeriousCorrection"),
			@Result(column = "general_correction",property = "generalCorrection"),
			@Result(column = "poor_correction",property = "poorCorrection"),
	})
	public List<HomeworkInspectionRecord> selectAllHomeworkInspectionRecord();
	
	@Select("SELECT * FROM "+HOMEWORK_INSPECTION_RECORD+" WHERE id=#{id}")
	@ResultMap("homeworkInspectionRecordMap")
	public HomeworkInspectionRecord selectHomeworkInspectionRecordById(@Param("id")int id);
	
	
	@SelectProvider(type=HomeworkInspectionRecordDynaSqlProvider.class,method="selectWithParam")
	@ResultMap("homeworkInspectionRecordMap")
	public List<HomeworkInspectionRecord> selectHomeworkInspectionRecordListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+HOMEWORK_INSPECTION_RECORD+"(course_selection_id,assignments_or_reports_fen_num,"
			+ "assignments_or_reports_ci_num,correction_times,serious_correction,more_serious_correction,"
			+ "general_correction,poor_correction,remarks) VALUE(#{courseSelection.id},#{assignmentsOrReportsFenNum},"
			+ "#{assignmentsOrReportsCiNum},#{correctionTimes},#{seriousCorrection},#{moreSeriousCorrection},"
			+ "#{generalCorrection},#{poorCorrection},#{remarks})")
	@Options(useGeneratedKeys = true)
	public void insertHomeworkInspectionRecord(HomeworkInspectionRecord homeworkInspectionRecord);
	
	
	@Update("UPDATE "+HOMEWORK_INSPECTION_RECORD+" SET course_selection_id=#{courseSelection.id},"
			+ "assignments_or_reports_fen_num=#{assignmentsOrReportsFenNum},assignments_or_reports_ci_num=#{assignmentsOrReportsCiNum},"
			+ "correction_times=#{correctionTimes},serious_correction=#{seriousCorrection},more_serious_correction=#{moreSeriousCorrection},"
			+ "general_correction=#{generalCorrection},poor_correction=#{poorCorrection},remarks=#{remarks} WHERE id=#{id}")
	public void updateHomeworkInspectionRecord(HomeworkInspectionRecord homeworkInspectionRecord);
	
	
	@Delete("DELETE FROM "+HOMEWORK_INSPECTION_RECORD+" WHERE id=#{id}")
	public void deleteHomeworkInspectionRecord(@Param("id")int id);
	
	
	@SelectProvider(type=HomeworkInspectionRecordDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
}
