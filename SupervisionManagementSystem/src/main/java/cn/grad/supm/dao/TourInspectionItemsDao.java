package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_ITEMS;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.TourInspectionItemsDynaSqlProvider;
import cn.grad.supm.domain.TourInspectionItems;

public interface TourInspectionItemsDao {

	@Select("SELECT * FROM "+TOUR_INSPECTION_ITEMS)
	public List<TourInspectionItems> selectAllTourInspectionItems();
	
	
	@Select("SELECT * FROM "+TOUR_INSPECTION_ITEMS+" WHERE id=#{id}")
	public TourInspectionItems selectTourInspectionItemsById(@Param("id")int id);
	
	
	@SelectProvider(type=TourInspectionItemsDynaSqlProvider.class,method="selectWithParam")
	public List<TourInspectionItems> selectTourInspectionItemsListByPage(Map<String,Object> params);
	
	@Insert("INSERT INTO "+TOUR_INSPECTION_ITEMS+"(content) VALUE(#{content})")
	@Options(useGeneratedKeys = true)
	public void insertTourInspectionItems(TourInspectionItems tourInspectionItems);
	
	
	@Update("UPDATE "+TOUR_INSPECTION_ITEMS+" SET content=#{content} WHERE id=#{id}")
	public void updateTourInspectionItems(TourInspectionItems tourInspectionItems);
	
	
	@Delete("DELETE FROM "+TOUR_INSPECTION_ITEMS+" WHERE id=#{id}")
	public void deleteTourInspectionItems(@Param("id")int id);
	
	
	@SelectProvider(type=TourInspectionItemsDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
