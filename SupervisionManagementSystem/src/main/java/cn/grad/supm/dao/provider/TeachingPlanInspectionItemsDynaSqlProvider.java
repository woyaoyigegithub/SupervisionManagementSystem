package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_ITEMS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.TeachingPlanInspectionItems;


public class TeachingPlanInspectionItemsDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TEACHING_PLAN_INSPECTION_ITEMS);
				if(params.get("teachingPlanInspectionItems") != null){
					TeachingPlanInspectionItems teachingPlanInspectionItems = (TeachingPlanInspectionItems)params.get("teachingPlanInspectionItems");
					if(teachingPlanInspectionItems.getId()!=0){
						WHERE("id = #{teachingPlanInspectionItems.id}");
					}
					if(teachingPlanInspectionItems.getContent()!=null && !"".equals(teachingPlanInspectionItems.getContent())){
						WHERE("content LIKE CONCAT ('%',#{teachingPlanInspectionItems.content},'%')");
					}
					if(teachingPlanInspectionItems.getOptions()!=null && !"".equals(teachingPlanInspectionItems.getOptions())){
						WHERE("options LIKE CONCAT ('%',#{teachingPlanInspectionItems.options},'%')");
					}
					
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(TEACHING_PLAN_INSPECTION_ITEMS);
				if(params.get("teachingPlanInspectionItems") != null){
					TeachingPlanInspectionItems teachingPlanInspectionItems = (TeachingPlanInspectionItems)params.get("teachingPlanInspectionItems");
					if(teachingPlanInspectionItems.getId()!=0){
						WHERE("id = #{teachingPlanInspectionItems.id}");
					}
					if(teachingPlanInspectionItems.getContent()!=null && !"".equals(teachingPlanInspectionItems.getContent())){
						WHERE("content LIKE CONCAT ('%',#{teachingPlanInspectionItems.content},'%')");
					}
					if(teachingPlanInspectionItems.getOptions()!=null && !"".equals(teachingPlanInspectionItems.getOptions())){
						WHERE("options LIKE CONCAT ('%',#{teachingPlanInspectionItems.options},'%')");
					}
					
				}
			}
		}.toString();
	}
	
	
}
