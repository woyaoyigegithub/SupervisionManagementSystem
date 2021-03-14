package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_BUILDING;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_SITUATION_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.InspectionAreaArrangementBuilding;

public interface InspectionAreaArrangementBuildingDao {

	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT_BUILDING+" WHERE inspection_area_arrangement_id=#{inspectionAreaArrangementId}")
	@Results(id="inspectionAreaArrangementBuildingMap",value= {
			@Result(column = "id",property = "id",id = true),
			@Result(column = "academic_building",property = "academicBuilding"),
			@Result(column = "inspectors_num",property = "inspectorsNum"),
			@Result(column = "id",property = "inspectionAreaArrangementSituationList",
				many=@Many(select = INSPECTION_AREA_ARRANGEMENT_SITUATION_DAO+".selectInspectionAreaArrangementSituationListByInspectionAreaArrangementBuildingId"))
	})
	public List<InspectionAreaArrangementBuilding> selectInspectionAreaArrangementBuildingListByInspectionAreaArrangementId(@Param("inspectionAreaArrangementId")int inspectionAreaArrangementId);
	
	
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT_BUILDING+" WHERE id=#{id}")
	@ResultMap("inspectionAreaArrangementBuildingMap")
	public InspectionAreaArrangementBuilding selectInspectionAreaArrangementBuildingById(@Param("id")int id);
	
	
	@Insert("INSERT INTO "+INSPECTION_AREA_ARRANGEMENT_BUILDING+"(inspection_area_arrangement_id,academic_building,inspectors_num) VALUE"
			+ "(#{inspectionAreaArrangementId},#{arg1.academicBuilding},#{arg1.inspectorsNum})")
	@Options(useGeneratedKeys = true,keyProperty ="arg1.id" )
	public void insertInspectionAreaArrangementBuilding(@Param("inspectionAreaArrangementId")int inspectionAreaArrangementId,
			InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding);
	
	
	@Update("UPDATE "+INSPECTION_AREA_ARRANGEMENT_BUILDING+" SET academic_building=#{academicBuilding},inspectors_num=#{inspectorsNum} "
			+ "WHERE id=#{id}")
	public void updateInspectionAreaArrangementBuilding(InspectionAreaArrangementBuilding inspectionAreaArrangementBuilding);
	
	
	@Delete("DELETE FROM "+INSPECTION_AREA_ARRANGEMENT_BUILDING+" WHERE inspection_area_arrangement_id=#{inspectionAreaArrangementId}")
	public void deleteInspectionAreaArrangementBuilding(@Param("inspectionAreaArrangementId")int inspectionAreaArrangementId);
	
}
