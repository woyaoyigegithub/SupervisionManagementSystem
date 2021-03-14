package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_SITUATION;
import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_ITEMS_DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.TourInspectionSituation;

public interface TourInspectionSituationDao {

	@Select("SELECT * FROM "+TOUR_INSPECTION_SITUATION+" WHERE tour_inspection_record_id=#{tourInspectionRecordId}")
	@Results(id="TourInspectionSituationMap",value= {
			@Result(column = "tour_inspection_items_id",property = "tourInspectionItems",
					one=@One(select = TOUR_INSPECTION_ITEMS_DAO+".selectTourInspectionItemsById"))
	})
	public List<TourInspectionSituation> selectTourInspectionSituationListByTourInspectionRecordId(@Param("tourInspectionRecordId")int tourInspectionRecordId);
	
	
	@Insert("INSERT INTO "+TOUR_INSPECTION_SITUATION+"(tour_inspection_record_id,tour_inspection_items_id,situation) "
			+ "VALUE(#{tourInspectionRecordId},#{arg1.tourInspectionItems.id},#{arg1.situation})")
	public void insertTourInspectionSituation(@Param("tourInspectionRecordId")int tourInspectionRecordId,TourInspectionSituation tourInspectionSituation);
	
	
	@Update("UPDATE "+TOUR_INSPECTION_SITUATION+" SET situation=#{arg1.situation} WHERE tour_inspection_record_id=#{tourInspectionRecordId} "
			+ "AND tour_inspection_items_id=#{arg1.tourInspectionItems.id}")
	public void updateTourInspectionSituation(@Param("tourInspectionRecordId")int tourInspectionRecordId,TourInspectionSituation tourInspectionSituation);
	
	
	@Delete("DELETE FROM "+TOUR_INSPECTION_SITUATION+" WHERE tour_inspection_record_id=#{tourInspectionRecordId}")
	public void deleteTourInspectionSituation(@Param("tourInspectionRecordId")int tourInspectionRecordId);
}
