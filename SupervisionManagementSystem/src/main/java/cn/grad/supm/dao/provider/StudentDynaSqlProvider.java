package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.STUDENT;
import static cn.grad.supm.utils.Constants.CLASS;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.Student;


public class StudentDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(STUDENT+","+CLASS);
				WHERE(STUDENT+".class_id = "+CLASS+".id");
				if(params.get("student") != null){
					Student student = (Student)params.get("student");
					Clazz clazz=student.getClazz();
					
					if(student.getId()!=null && !"".equals(student.getId())){
						WHERE(STUDENT+".id LIKE CONCAT ('%',#{student.id},'%')");
					}
					if(clazz!=null && clazz.getId()!=null && !"".equals(clazz.getId())){
						WHERE(CLASS+".id LIKE CONCAT ('%',#{student.clazz.id},'%') ");
					}
					if(clazz!=null && clazz.getName()!=null && !"".equals(clazz.getName())){
						WHERE(CLASS+".name LIKE CONCAT ('%',#{student.clazz.name},'%') ");
					}
					if(student.getName()!=null && !"".equals(student.getName())){
						WHERE(STUDENT+".name LIKE CONCAT ('%',#{student.name},'%')");
					}
					if(student.getSex()!=null && !"".equals(student.getSex())){
						WHERE("sex LIKE CONCAT ('%',#{student.sex},'%')");
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
				FROM(STUDENT+","+CLASS);
				WHERE(STUDENT+".class_id = "+CLASS+".id");
				if(params.get("student") != null){
					Student student = (Student)params.get("student");
					Clazz clazz=student.getClazz();
					
					if(student.getId()!=null && !"".equals(student.getId())){
						WHERE(STUDENT+".id LIKE CONCAT ('%',#{student.id},'%')");
					}
					if(clazz!=null && clazz.getName()!=null && !"".equals(clazz.getName())){
						WHERE(CLASS+".name LIKE CONCAT ('%',#{student.clazz.name},'%') ");
					}
					if(student.getName()!=null && !"".equals(student.getName())){
						WHERE(STUDENT+".name LIKE CONCAT ('%',#{student.name},'%')");
					}
					if(student.getSex()!=null && !"".equals(student.getSex())){
						WHERE("sex LIKE CONCAT ('%',#{student.sex},'%')");
					}
					
				}
			}
		}.toString();
	}
	
}
