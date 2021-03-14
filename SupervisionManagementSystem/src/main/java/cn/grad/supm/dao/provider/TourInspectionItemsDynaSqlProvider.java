package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_ITEMS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

import cn.grad.supm.domain.TourInspectionItems;


public class TourInspectionItemsDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TOUR_INSPECTION_ITEMS);
				if(params.get("tourInspectionItems") != null){
					TourInspectionItems tourInspectionItems = (TourInspectionItems)params.get("tourInspectionItems");
					if(tourInspectionItems.getId()!=0){
						WHERE("id =#{tourInspectionItems.id}");
					}
					if(tourInspectionItems.getContent()!=null && !"".equals(tourInspectionItems.getContent())){
						WHERE("content LIKE CONCAT('%',#{tourInspectionItems.content},'%')");
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
				FROM(TOUR_INSPECTION_ITEMS);
				if(params.get("tourInspectionItems") != null){
					TourInspectionItems tourInspectionItems = (TourInspectionItems)params.get("tourInspectionItems");
					if(tourInspectionItems.getId()!=0){
						WHERE("id =#{tourInspectionItems.id}");
					}
					if(tourInspectionItems.getContent()!=null && !"".equals(tourInspectionItems.getContent())){
						WHERE("content LIKE CONCAT('%',#{tourInspectionItems.content},'%')");
					}
					
				}
			}
		}.toString();
	}
	
	
}
