package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_ITEMS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.DailyInspectionItems;


public class DailyInspectionItemsDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(DAILY_INSPECTION_ITEMS);
				if(params.get("dailyInspectionItems") != null){
					DailyInspectionItems dailyInspectionItems = (DailyInspectionItems)params.get("dailyInspectionItems");
					
					if(dailyInspectionItems.getId()!=0){
						WHERE("id = #{dailyInspectionItems.id}");
					}
					if(dailyInspectionItems.getContent()!=null && !"".equals(dailyInspectionItems.getContent())){
						WHERE("content LIKE CONCAT('%',#{dailyInspectionItems.content},'%')");
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
				FROM(DAILY_INSPECTION_ITEMS);
				if(params.get("dailyInspectionItems") != null){
					DailyInspectionItems dailyInspectionItems = (DailyInspectionItems)params.get("dailyInspectionItems");
					
					if(dailyInspectionItems.getId()!=0){
						WHERE("id = #{dailyInspectionItems.id}");
					}
					if(dailyInspectionItems.getContent()!=null && !"".equals(dailyInspectionItems.getContent())){
						WHERE("content LIKE CONCAT('%',#{dailyInspectionItems.content},'%')");
					}
					
				}
			}
		}.toString();
	}
	
}
