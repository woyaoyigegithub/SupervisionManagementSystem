package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.CLASS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.Clazz;


public class ClazzDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CLASS);
				if(params.get("clazz") != null){
					Clazz clazz = (Clazz)params.get("clazz");
					if(clazz.getId()!=null && !"".equals(clazz.getId())){
						WHERE("id LIKE CONCAT ('%',#{clazz.id},'%') ");
					}
					if(clazz.getName()!=null && !"".contentEquals(clazz.getName())) {
						WHERE("name LIKE CONCAT ('%',#{clazz.name},'%')");
					}
					if(clazz.getMajor()!=null && !"".contentEquals(clazz.getMajor())) {
						WHERE("major LIKE CONCAT ('%',#{clazz.major},'%')");
					}
					if(clazz.getGrade()!=null && !"".contentEquals(clazz.getGrade())) {
						WHERE("grade LIKE CONCAT ('%',#{clazz.grade},'%')");
					}
					if(clazz.getDepartment()!=null && !"".contentEquals(clazz.getDepartment())) {
						WHERE("department LIKE CONCAT ('%',#{clazz.department},'%')");
					}
					if(clazz.getUndergOrJun()!=null && !"".contentEquals(clazz.getUndergOrJun())) {
						WHERE("underg_or_jun = #{clazz.undergOrJun}");
					}
					
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(CLASS);
				if (params.get("clazz") != null) {
					Clazz clazz = (Clazz) params.get("clazz");
					if (clazz.getId() != null && !"".equals(clazz.getId())) {
						WHERE("id LIKE CONCAT ('%',#{clazz.id},'%') ");
					}
					if (clazz.getName() != null && !"".contentEquals(clazz.getName())) {
						WHERE("name LIKE CONCAT ('%',#{clazz.name},'%') ");
					}
					if (clazz.getMajor() != null && !"".contentEquals(clazz.getMajor())) {
						WHERE("major LIKE CONCAT ('%',#{clazz.major},'%') ");
					}
					if (clazz.getGrade() != null && !"".contentEquals(clazz.getGrade())) {
						WHERE("grade LIKE CONCAT ('%',#{clazz.grade},'%') ");
					}
					if (clazz.getDepartment() != null && !"".contentEquals(clazz.getDepartment())) {
						WHERE("department LIKE CONCAT ('%',#{clazz.department},'%') ");
					}
					if (clazz.getUndergOrJun() != null && !"".contentEquals(clazz.getUndergOrJun())) {
						WHERE("underg_or_jun = #{clazz.undergOrJun}");
					}

				}
			}
		}.toString();
	}
	
	
}
