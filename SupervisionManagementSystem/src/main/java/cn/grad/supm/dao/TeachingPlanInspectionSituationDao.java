package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_SITUATION;
import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_ITEMS_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.TeachingPlanInspectionSituation;

public interface TeachingPlanInspectionSituationDao {

	
	@Select("SELECT * FROM "+TEACHING_PLAN_INSPECTION_SITUATION+" WHERE teaching_plan_inspecttion_record_id=#{teachingPlanInspecttionRecordId}")
	@Results(id="teachingPlanInspectionSituationMap",value= {
			@Result(column ="teaching_plan_inspection_items_id",property = "teachingPlanInspectionItems",
					one=@One(select = TEACHING_PLAN_INSPECTION_ITEMS_DAO+".selectTeachingPlanInspectionItemsById"))
	})
	public List<TeachingPlanInspectionSituation> selectTeachingPlanInspectionSituationListByTeachingPlanInspectionRecordId(@Param("teachingPlanInspecttionRecordId")int teachingPlanInspecttionRecordId);
	
	
	@Insert("INSERT INTO "+TEACHING_PLAN_INSPECTION_SITUATION+"(teaching_plan_inspecttion_record_id,teaching_plan_inspection_items_id,situation) "
			+ "VALUE(#{teachingPlanInspectionRecordId},#{arg1.teachingPlanInspectionItems.id},#{arg1.situation})")
	public void insertTeachingPlanInspectionSituation(@Param("teachingPlanInspectionRecordId")int teachingPlanInspectionRecordId,TeachingPlanInspectionSituation teachingPlanInspectionSituation);
	
	
	@Update("UPDATE "+TEACHING_PLAN_INSPECTION_SITUATION+" SET situation=#{arg1.situation} WHERE teaching_plan_inspecttion_record_id=#{teachingPlanInspectionRecordId} "
			+ "AND teaching_plan_inspection_items_id=#{arg1.teachingPlanInspectionItems.id}")
	public void updateTeachingPlanInspectionSituation(@Param("teachingPlanInspectionRecordId")int teachingPlanInspectionRecordId,TeachingPlanInspectionSituation teachingPlanInspectionSituation);
	
	
	@Delete("DELETE FROM "+TEACHING_PLAN_INSPECTION_SITUATION+" WHERE teaching_plan_inspecttion_record_id=#{teachingPlanInspectionRecordId}")
	public void deleteTeachingPlanInspectionSituation(@Param("teachingPlanInspectionRecordId")int teachingPlanInspectionRecordId);
	
	
}
