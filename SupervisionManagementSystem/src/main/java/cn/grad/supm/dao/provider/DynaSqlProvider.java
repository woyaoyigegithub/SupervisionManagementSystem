package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.COURSE_SELECTION;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.CourseSelection;


public class DynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(COURSE_SELECTION);
				if(params.get("courseSelection") != null){
					CourseSelection courseSelection = (CourseSelection)params.get("courseSelection");
					if(courseSelection.getId()!=null && !"".equals(courseSelection.getId())){
						WHERE("id =#{courseSelection.id}");
					}
					if(courseSelection.getCourse()!=null && courseSelection.getCourse().getId()!=null 
							&& !"".equals(courseSelection.getCourse().getId())){
						WHERE("course_id =#{courseSelection.course.id}");
					}
					
					
					
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	
	
}
