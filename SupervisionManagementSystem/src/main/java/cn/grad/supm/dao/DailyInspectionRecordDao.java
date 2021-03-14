package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_SITUATION;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_BUILDING;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT;
import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_SITUATION_DAO;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_SITUATION_DAO;
import static cn.grad.supm.utils.Constants.LEAVE_CLASS_AHEAD_OF_TIME_SITUATION_DAO;

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

import cn.grad.supm.dao.provider.DailyInspectionRecordDynaSqlProvider;
import cn.grad.supm.domain.DailyInspectionRecord;

public interface DailyInspectionRecordDao {
	
	@Select("SELECT * FROM "+DAILY_INSPECTION_RECORD)
	@Results(id="dailyInspectionRecordMap",value= {
			@Result(column = "id",property = "id",id=true),
			@Result(column = "inspection_area_arrangement_situation_id",property = "inspectionAreaArrangementSituation",
					one=@One(select = INSPECTION_AREA_ARRANGEMENT_SITUATION_DAO+".selectInspectionAreaArrangementSituationById")),
			@Result(column = "date",property = "date"),
			@Result(column = "id",property = "dailyInspectionSituationList",
					many=@Many(select = DAILY_INSPECTION_SITUATION_DAO+".selectDailyInspectionSituationListByDailyInspectionRecordId")),
			@Result(column = "id",property = "leaveClassAheadOfTimeSituationList",
					many=@Many(select = LEAVE_CLASS_AHEAD_OF_TIME_SITUATION_DAO+".selectLeaveClassAheadOfTimeSituationListByDailyInspectionRecordId")),
	})
	public List<DailyInspectionRecord> selectAllDailyInspectionRecord();


	@Select("SELECT * FROM "+DAILY_INSPECTION_RECORD+" WHERE id=#{id}")
	@ResultMap("dailyInspectionRecordMap")
	public DailyInspectionRecord selectDailyInspectionRecordById(@Param("id")int id);
	
	
	@SelectProvider(type = DailyInspectionRecordDynaSqlProvider.class, method="selectWithParam" )
	@ResultMap("dailyInspectionRecordMap")
	public List<DailyInspectionRecord> selectDailyInspectionRecordByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+DAILY_INSPECTION_RECORD+"(inspection_area_arrangement_situation_id,weekly,week,date) "
			+ "VALUE(#{inspectionAreaArrangementSituation.id},#{weekly},#{week},#{date})")
	@Options(useGeneratedKeys = true)
	public void insertDailyInspectionRecord(DailyInspectionRecord dailyInspectionRecord);
	
	
	@Update("UPDATE "+DAILY_INSPECTION_RECORD+" SET inspection_area_arrangement_situation_id=#{inspectionAreaArrangementSituation.id},"
			+ "weekly=#{weekly},week=#{week},date=#{date} WHERE id=#{id}")
	public void updateDailyInspectionRecord(DailyInspectionRecord dailyInspectionRecord);
	
	
	@Delete("DELETE FROM "+DAILY_INSPECTION_RECORD+" WHERE id=#{id}")
	public void deleteDailyInspectionRecord(@Param("id")int id);
	
	
	@SelectProvider(type = DailyInspectionRecordDynaSqlProvider.class, method="count" )
	public int count(Map<String,Object> params);
	
	
	//通过督导编号按学年学期统计日常巡查次数
	@Select("SELECT school_year,semester,count(*) FROM "+INSPECTION_AREA_ARRANGEMENT+","+
			INSPECTION_AREA_ARRANGEMENT_BUILDING+","+INSPECTION_AREA_ARRANGEMENT_SITUATION+","+DAILY_INSPECTION_RECORD+" WHERE "+
			INSPECTION_AREA_ARRANGEMENT+".id="+INSPECTION_AREA_ARRANGEMENT_BUILDING+".inspection_area_arrangement_id AND "+
			INSPECTION_AREA_ARRANGEMENT_BUILDING+".id="+INSPECTION_AREA_ARRANGEMENT_SITUATION+".inspection_area_arrangement_building_id AND "+
			DAILY_INSPECTION_RECORD+".inspection_area_arrangement_situation_id="+INSPECTION_AREA_ARRANGEMENT_SITUATION+".id AND "+
			"supervisor_id=#{supervisorId} GROUP BY school_year,semester")
	@Results({
		@Result(column = "school_year",property = "schoolYear"),
		@Result(column = "count(*)",property = "count"),
	})
	public List<Map<String,String>> countBySchoolYearAndSemester(@Param("supervisorId")String supervisorId);
	
}
