package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_ITEMS;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.DailyInspectionItemsDynaSqlProvider;
import cn.grad.supm.domain.DailyInspectionItems;

public interface DailyInspectionItemsDao {

	
	@Select("SELECT * FROM "+DAILY_INSPECTION_ITEMS+" WHERE id=#{id}")
	public DailyInspectionItems selectDailyInspectionItemsById(@Param("id")int id);
	
	
	@SelectProvider(type=DailyInspectionItemsDynaSqlProvider.class,method="selectWithParam")
	public List<DailyInspectionItems> selectDailyInspectionItemsListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+DAILY_INSPECTION_ITEMS+"(content) VALUE(#{content})")
	@Options(useGeneratedKeys = true)
	public void insertDailyInspectionItems(DailyInspectionItems dailyInspectionItems);
	
	
	@Update("UPDATE "+DAILY_INSPECTION_ITEMS+" SET content=#{content} WHERE id=#{id}")
	public void updateDailyInspectionItems(DailyInspectionItems dailyInspectionItems);
	
	
	@Delete("DELETE FROM "+DAILY_INSPECTION_ITEMS+" WHERE id=#{id}")
	public void deleteDailyInspectionItems(@Param("id")int id);
	
	
	@SelectProvider(type=DailyInspectionItemsDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
