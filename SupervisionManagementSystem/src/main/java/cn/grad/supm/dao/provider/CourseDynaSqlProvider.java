package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.COURSE;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.Course;


public class CourseDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(COURSE);
				if(params.get("course") != null){
					Course course = (Course)params.get("course");
					if(course.getId()!=null && !"".equals(course.getId())){
						WHERE("id LIKE CONCAT ('%',#{course.id},'%')");
					}
					if(course.getName()!=null && !"".equals(course.getName())){
						WHERE("name LIKE CONCAT ('%',#{course.name},'%')");
					}
					if(course.getNature()!=null && !"".equals(course.getNature())){
						WHERE("nature LIKE CONCAT ('%',#{course.nature},'%')");
					}
					if(course.getType()!=null && !"".equals(course.getType())){
						WHERE("type LIKE CONCAT ('%',#{course.type},'%')");
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
				FROM(COURSE);
				if(params.get("course") != null){
					Course course = (Course)params.get("course");
					if(course.getId()!=null && !"".equals(course.getId())){
						WHERE("id LIKE CONCAT ('%',#{course.id},'%')");
					}
					if(course.getName()!=null && !"".equals(course.getName())){
						WHERE("name LIKE CONCAT ('%',#{course.name},'%')");
					}
					if(course.getNature()!=null && !"".equals(course.getNature())){
						WHERE("nature LIKE CONCAT ('%',#{course.nature},'%')");
					}
					if(course.getType()!=null && !"".equals(course.getType())){
						WHERE("type LIKE CONCAT ('%',#{course.type},'%')");
					}
					
				}
			}
		}.toString();
	}
	
	
	
}
