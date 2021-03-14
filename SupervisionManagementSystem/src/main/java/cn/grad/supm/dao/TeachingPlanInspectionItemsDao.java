package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_ITEMS;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.TeachingPlanInspectionItemsDynaSqlProvider;
import cn.grad.supm.domain.TeachingPlanInspectionItems;


public interface TeachingPlanInspectionItemsDao {

	@Select("SELECT * FROM "+TEACHING_PLAN_INSPECTION_ITEMS)
	public List<TeachingPlanInspectionItems> selectAllTeachingPlanInspectionItems();
	
	
	@Select("SELECT * FROM "+TEACHING_PLAN_INSPECTION_ITEMS+" WHERE id=#{id}")
	public TeachingPlanInspectionItems selectTeachingPlanInspectionItemsById(@Param("id")int id);
	
	
	@SelectProvider(type=TeachingPlanInspectionItemsDynaSqlProvider.class,method="selectWithParam")
	public List<TeachingPlanInspectionItems> selectTeachingPlanInspectionItemsListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+TEACHING_PLAN_INSPECTION_ITEMS+"(content,options) VALUE(#{content},#{options})")
	@Options(useGeneratedKeys = true)
	public void insertTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems);
	
	
	@Update("UPDATE "+TEACHING_PLAN_INSPECTION_ITEMS+" SET content=#{content},options=#{options} WHERE id=#{id}")
	public void updateTeachingPlanInspectionItems(TeachingPlanInspectionItems teachingPlanInspectionItems);
	
	
	@Delete("DELETE FROM "+TEACHING_PLAN_INSPECTION_ITEMS+" WHERE id=#{id}")
	public void deleteTeachingPlanInspectionItems(@Param("id")int id);
	
	
	@SelectProvider(type=TeachingPlanInspectionItemsDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
}
