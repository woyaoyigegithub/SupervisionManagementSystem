package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_ITEMS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

import cn.grad.supm.domain.PapersInspectionItems;


public class PapersInspectionItemsDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(PAPERS_INSPECTION_ITEMS);
				if(params.get("papersInspectionItems") != null){
					PapersInspectionItems papersInspectionItems = (PapersInspectionItems)params.get("papersInspectionItems");
					if(papersInspectionItems.getId()!=0){
						WHERE("id =#{papersInspectionItems.id}");
					}
					if(papersInspectionItems.getContent()!=null && !"".equals(papersInspectionItems.getContent())) {
						WHERE("content LIKE CONCAT('%',#{papersInspectionItems.content},'%')");
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
				FROM(PAPERS_INSPECTION_ITEMS);
				if(params.get("papersInspectionItems") != null){
					PapersInspectionItems papersInspectionItems = (PapersInspectionItems)params.get("papersInspectionItems");
					if(papersInspectionItems.getId()!=0){
						WHERE("id =#{papersInspectionItems.id}");
					}
					if(papersInspectionItems.getContent()!=null && !"".equals(papersInspectionItems.getContent())) {
						WHERE("content LIKE CONCAT('%',#{papersInspectionItems.content},'%')");
					}
				}
			}
		}.toString();
	}
	
}
