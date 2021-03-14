package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.SUPERVISOR_DAO;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_DAO;
import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_SITUATION_DAO;

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

import cn.grad.supm.dao.provider.TourInspectionRecordDynaSqlProvider;
import cn.grad.supm.domain.TourInspectionRecord;

public interface TourInspectionRecordDao {

	@Select("SELECT * FROM "+TOUR_INSPECTION_RECORD)
	@Results(id="tourInspectionRecordMap",value= {
			@Result(column ="id",property = "id",id=true),
			@Result(column ="date",property = "date"),
			@Result(column ="starting_time",property = "startingTime"),
			@Result(column ="ending_time",property = "endingTime"),
			@Result(column ="tour_inspection_area",property = "tourInspectionArea"),
			@Result(column ="supervisor_id",property = "supervisor",
			one=@One(select = SUPERVISOR_DAO+".selectSupervisorById")),
			@Result(column ="inspection_area_arrangement_id",property = "inspectionAreaArrangement",
				one=@One(select = INSPECTION_AREA_ARRANGEMENT_DAO+".selectInspectionAreaArrangementById")),
			@Result(column ="id",property = "tourInspectionSituationList",
				one=@One(select = TOUR_INSPECTION_SITUATION_DAO+".selectTourInspectionSituationListByTourInspectionRecordId")),
	})
	public List<TourInspectionRecord> selectAllTourInspectionRecord();
	
	
	@Select("SELECT * FROM "+TOUR_INSPECTION_RECORD+" WHERE id=#{id}")
	@ResultMap("tourInspectionRecordMap")
	public TourInspectionRecord selectTourInspectionRecordById(@Param("id")int id);
	
	
	@SelectProvider(type = TourInspectionRecordDynaSqlProvider.class,method = "selectWithParam")
	@ResultMap("tourInspectionRecordMap")
	public List<TourInspectionRecord> selectTourInspectionRecordListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+TOUR_INSPECTION_RECORD+"(date,starting_time,ending_time,tour_inspection_area,supervisor_id) "
			+ "VALUE(#{date},#{startingTime},#{endingTime},#{tourInspectionArea},#{supervisor.id})")
	@Options(useGeneratedKeys = true)
	public void insertTourInspectionRecord(TourInspectionRecord tourInspectionRecord);
	
	
	@Update("UPDATE "+TOUR_INSPECTION_RECORD+" SET date=#{date},starting_time=#{startingTime},ending_time=#{endingTime},"
			+ "tour_inspection_area=#{tourInspectionArea},supervisor_id=#{supervisor.id} WHERE id=#{id}")
	public void updateTourInspectionRecord(TourInspectionRecord tourInspectionRecord);
	
	
	@Delete("DELETE FROM "+TOUR_INSPECTION_RECORD+" WHERE id=#{id}")
	public void deleteTourInspectionRecord(@Param("id")int id);
	
	
	@SelectProvider(type = TourInspectionRecordDynaSqlProvider.class,method = "count")
	public int count(Map<String,Object> params);


	//通过督导编号按学年学期统计巡考次数
	@Select("SELECT if(month(date) between 1 and 7,concat(year(date)-1,'-',"
			+ "year(date)),concat(year(date),'-',year(date)+1)) schoolYear,if(month(date) between 2 and 7,2,1) semester,"
			+ "count(*) FROM "+TOUR_INSPECTION_RECORD+" WHERE supervisor_id=#{supervisorId} group by "
			+ "if(month(date) between 1 and 7,concat(year(date)-1,'-',year(date)),concat(year(date),'-',year(date)+1)),"
			+ "if(month(date) between 2 and 7,2,1) ")
	@Results({
		@Result(column = "count(*)",property = "count"),
	})
	public List<Map<String, String>> countBySchoolYearAndSemester(String supervisorId);
}
