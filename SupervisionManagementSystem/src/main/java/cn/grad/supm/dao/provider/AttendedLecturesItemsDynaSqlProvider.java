package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_ITEMS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.AttendedLecturesItems;


public class AttendedLecturesItemsDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(ATTENDED_LECTURES_ITEMS);
				if(params.get("attendedLecturesItems") != null){
					AttendedLecturesItems attendedLecturesItems = (AttendedLecturesItems)params.get("attendedLecturesItems");
					if(attendedLecturesItems.getId()!=0){
						WHERE("id=#{attendedLecturesItems.id}");
					}
					if(attendedLecturesItems.getContent()!=null && !"".equals(attendedLecturesItems.getContent())){
						WHERE("content LIKE CONCAT ('%',#{attendedLecturesItems.content},'%') ");
					}
					if(attendedLecturesItems.getScore()!=0){
						WHERE("score LIKE CONCAT ('%',#{attendedLecturesItems.score},'%') ");
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
				FROM(ATTENDED_LECTURES_ITEMS);
				if(params.get("attendedLecturesItems") != null){
					AttendedLecturesItems attendedLecturesItems = (AttendedLecturesItems)params.get("attendedLecturesItems");
					if(attendedLecturesItems.getId()!=0){
						WHERE("id=#{attendedLecturesItems.id}");
					}
					if(attendedLecturesItems.getContent()!=null && !"".equals(attendedLecturesItems.getContent())){
						WHERE("content LIKE CONCAT ('%',#{attendedLecturesItems.content},'%') ");
					}
					if(attendedLecturesItems.getScore()!=0){
						WHERE("score LIKE CONCAT ('%',#{attendedLecturesItems.score},'%') ");
					}
					
				}
			}
		}.toString();
	}
	
	
}
