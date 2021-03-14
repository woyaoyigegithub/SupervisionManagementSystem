package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.COURSE_SELECTION_DAO;
import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_SITUATION_DAO;

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
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.TeachingPlanInspectionRecordDynaSqlProvider;
import cn.grad.supm.domain.TeachingPlanInspectionRecord;

public interface TeachingPlanInspectionRecordDao {

	@Select("SELECT * FROM "+TEACHING_PLAN_INSPECTION_RECORD)
	@Results(id="teachingPlanInspectionRecordMap",value= {
			@Result(column ="id",property = "id",id=true),
			@Result(column ="course_selection_id",property = "courseSelection",
					one=@One(select = COURSE_SELECTION_DAO+".selectCourseSelectionById")),
			@Result(column ="id",property = "teachingPlanInspectionSituationList",
					many=@Many(select = TEACHING_PLAN_INSPECTION_SITUATION_DAO+".selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId")),
			@Result(column ="discussing_and_affirming",property = "discussingAndAffirming"),
	})
	public List<TeachingPlanInspectionRecord> selectAllTeachingPlanInspectionRecord();
	
	
	@Select("SELECT * FROM "+TEACHING_PLAN_INSPECTION_RECORD+" WHERE id=#{id}")
	@ResultMap("teachingPlanInspectionRecordMap")
	public TeachingPlanInspectionRecord selectTeachingPlanInspectionRecordById(@Param("id")int id);
	
	
	@SelectProvider(type=TeachingPlanInspectionRecordDynaSqlProvider.class,method="selectWithParam")
	@ResultMap("teachingPlanInspectionRecordMap")
	public List<TeachingPlanInspectionRecord> selectTeachingPlanInspectionRecordListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+TEACHING_PLAN_INSPECTION_RECORD+"(course_selection_id,discussing_and_affirming) "
			+ "VALUE(#{courseSelection.id},#{discussingAndAffirming})")
	@Options(useGeneratedKeys = true)
	public void insertTeachingPlanInspectionRecord(TeachingPlanInspectionRecord teachingPlanInspectionRecord);
	
	
	@Update("UPDATE "+TEACHING_PLAN_INSPECTION_RECORD+" SET course_selection_id=#{courseSelection.id},discussing_and_affirming=#{discussingAndAffirming} "
			+ "WHERE id=#{id}")
	public void updateTeachingPlanInspectionRecord(TeachingPlanInspectionRecord teachingPlanInspectionRecord);
	
	
	@Delete("DELETE FROM "+TEACHING_PLAN_INSPECTION_RECORD+" WHERE id=#{id}")
	public void deleteTeachingPlanInspectionRecord(@Param("id")int id);
	
	
	@SelectProvider(type = TeachingPlanInspectionRecordDynaSqlProvider.class,method = "count")
	public int count(Map<String,Object> params);
	
}
