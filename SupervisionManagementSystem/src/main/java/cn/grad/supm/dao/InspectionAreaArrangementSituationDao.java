package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_SITUATION;
import static cn.grad.supm.utils.Constants.SUPERVISOR_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.InspectionAreaArrangementSituation;

public interface InspectionAreaArrangementSituationDao {

	
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT_SITUATION+" WHERE id=#{id}")
	@Results(id="inspectionAreaArrangementSituationMap",value= {
			@Result(column = "supervisor_id",property = "supervisor",
				one=@One(select = SUPERVISOR_DAO+".selectSupervisorById")),
			@Result(column = "early_middle_late",property = "earlyMiddleLate"),
	})
	public InspectionAreaArrangementSituation selectInspectionAreaArrangementSituationById(@Param("id")int id);
	
	
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT_SITUATION+" WHERE inspection_area_arrangement_building_id=#{inspectionAreaArrangementBuildingId}")
	@ResultMap("inspectionAreaArrangementSituationMap")
	public List<InspectionAreaArrangementSituation> selectInspectionAreaArrangementSituationListByInspectionAreaArrangementBuildingId(@Param("inspectionAreaArrangementBuildingId")int inspectionAreaArrangementBuildingId);
	
	
	@Insert("INSERT INTO "+INSPECTION_AREA_ARRANGEMENT_SITUATION+"(inspection_area_arrangement_building_id,supervisor_id,week,early_middle_late) "
			+ "VALUE(#{inspectionAreaArrangementBuildingId},#{arg1.supervisor.id},#{arg1.week},#{arg1.earlyMiddleLate})")
	@Options(useGeneratedKeys = true,keyProperty = "arg1.id")
	public void InsertInspectionAreaArrangementSituation(@Param("inspectionAreaArrangementBuildingId")int inspectionAreaArrangementBuildingId,
			InspectionAreaArrangementSituation inspectionAreaArrangementSituation);
	
	
	@Update("UPDATE "+INSPECTION_AREA_ARRANGEMENT_SITUATION+" SET supervisor_id=#{supervisor.id},"
			+ "week=#{week},early_middle_late=#{earlyMiddleLate} WHERE id=#{id}")
	public void updateInspectionAreaArrangementSituation(InspectionAreaArrangementSituation inspectionAreaArrangementSituation);
	
	
	@Delete("DELETE FROM "+INSPECTION_AREA_ARRANGEMENT_SITUATION+" WHERE inspection_area_arrangement_building_id=#{inspectionAreaArrangementBuildingId}")
	public void deleteInsepctionAreaArrangementSituation(@Param("inspectionAreaArrangementBuildingId")int inspectionAreaArrangementBuildingId);
	
	
}
