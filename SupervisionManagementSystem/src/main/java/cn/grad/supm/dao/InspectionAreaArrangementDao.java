package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_BUILDING_DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.InspectionAreaArrangementDynaSqlProvider;
import cn.grad.supm.domain.InspectionAreaArrangement;

public interface InspectionAreaArrangementDao {

	
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT)
	@Results(id="inspectionAreaArrangementMap",value= {
			@Result(column = "id",property = "id", id = true),
			@Result(column = "school_year",property = "schoolYear"),
			@Result(column = "starting_and_ending_weeks",property = "startingAndEndingWeeks"),
			@Result(column = "publication_time",property = "publicationTime"),
			@Result(column = "id",property = "inspectionAreaArrangementBuildingList",
				many =@Many(select = INSPECTION_AREA_ARRANGEMENT_BUILDING_DAO+".selectInspectionAreaArrangementBuildingListByInspectionAreaArrangementId"))
	})
	public List<InspectionAreaArrangement> selectAllInspectionAreaArrangement();
	
	
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT+" WHERE id=#{id}")
	@ResultMap("inspectionAreaArrangementMap")
	public InspectionAreaArrangement selectInspectionAreaArrangementById(int id);
	
	
	@SelectProvider(type=InspectionAreaArrangementDynaSqlProvider.class, method="selectWithParam")
	@ResultMap("inspectionAreaArrangementMap")
	public List<InspectionAreaArrangement> selectInspectionAreaArrangementListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+INSPECTION_AREA_ARRANGEMENT+"(school_year,semester,starting_and_ending_weeks,"
			+ "publication_time) VALUE(#{schoolYear},#{semester},#{startingAndEndingWeeks},#{publicationTime})")
	@Options(useGeneratedKeys = true)
	public void insertInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement);
	
	
	@Update("UPDATE "+INSPECTION_AREA_ARRANGEMENT+" SET school_year=#{schoolYear},semester=#{semester},"
			+ "starting_and_ending_weeks=#{startingAndEndingWeeks},publication_time=#{publicationTime} "
			+ "WHERE id=#{id}")
	public void updateInspectionAreaArrangement(InspectionAreaArrangement inspectionAreaArrangement);
	
	
	@Delete("DELETE FROM "+INSPECTION_AREA_ARRANGEMENT+" WHERE id=#{id}")
	public void deleteInspectionAreaArrangement(@Param("id")int id);
	
	
	@SelectProvider(type=InspectionAreaArrangementDynaSqlProvider.class, method="count")
	public int count(Map<String,Object> params);
	
	
	//查询最新的督查区域安排记录
	@Select("SELECT * FROM "+INSPECTION_AREA_ARRANGEMENT+" WHERE id=(SELECT MAX(id) FROM "+INSPECTION_AREA_ARRANGEMENT+")")
	@ResultMap("inspectionAreaArrangementMap")
	public InspectionAreaArrangement selectLastInspectionAreaArrangement();
	
}
