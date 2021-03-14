package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_SITUATION;
import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_ITEMS_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.DailyInspectionSituation;

public interface DailyInspectionSituationDao {

	@Select("SELECT * FROM "+DAILY_INSPECTION_SITUATION+" WHERE daily_inspection_record_id=#{dailyInspectionRecordId}")
	@Results(id="dailyInspectionSituationMap",value={
			@Result(column = "daily_inspection_items_id",property = "dailyInspectionItems",
					one=@One(select = DAILY_INSPECTION_ITEMS_DAO+".selectDailyInspectionItemsById"))
	})
	public List<DailyInspectionSituation> selectDailyInspectionSituationListByDailyInspectionRecordId(@Param("dailyInspectionRecordId")int dailyInspectionRecordId);
	
	
	@Insert("INSERT INTO "+DAILY_INSPECTION_SITUATION+"(daily_inspection_record_id,daily_inspection_items_id,situation,suggest) "
			+ "VALUE(#{dailyInspectionRecordId},#{arg1.dailyInspectionItems.id},#{arg1.situation},#{arg1.suggest})")
	public void insertDailyInspectionSituation(@Param("dailyInspectionRecordId")int dailyInspectionRecordId,DailyInspectionSituation dailyInspectionSituation);
	
	
	@Update("UPDATE "+DAILY_INSPECTION_SITUATION+" SET situation=#{arg1.situation},suggest=#{arg1.suggest} "
			+ "WHERE daily_inspection_record_id=#{dailyInspectionRecordId} AND daily_inspection_items_id=#{arg1.dailyInspectionItems.id}")
	public void updateDailyInspectionSituation(@Param("dailyInspectionRecordId")int dailyInspectionRecordId,DailyInspectionSituation dailyInspectionSituation);
	
	
	@Delete("DELETE FROM "+DAILY_INSPECTION_SITUATION+" WHERE daily_inspection_record_id=#{dailyInspectionRecordId}")
	public void deleteDailyInspectionSituation(@Param("dailyInspectionRecordId")int dailyInspectionRecordId);
	
}
