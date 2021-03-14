package cn.grad.supm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.grad.supm.domain.Classroom;

import static cn.grad.supm.utils.Constants.CLASSROOM;

public class ClassroomDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CLASSROOM);
				if(params.get("classroom") != null){
					Classroom classroom = (Classroom)params.get("classroom");
					if(classroom.getId() != null && !classroom.getId().equals("")){
						WHERE("id LIKE CONCAT ('%',#{classroom.id},'%')");
					}
					if(classroom.getName() != null && !classroom.getName().equals("")){
						WHERE("name LIKE CONCAT ('%',#{classroom.name},'%')");
					}
					if(classroom.getSeatingCapacity()!=-1) {
						WHERE("seating_capacity=#{classroom.seatingCapacity}");
					}
					if(classroom.getType() != null && !classroom.getType().equals("")){
						WHERE("type LIKE CONCAT ('%',#{classroom.type},'%')");
					}
					if(classroom.getBuildingNum() != null && !"".equals(classroom.getBuildingNum())){
						WHERE("building_num LIKE CONCAT ('%',#{classroom.buildingNum},'%')");
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
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(CLASSROOM);
				if(params.get("classroom") != null){
					Classroom classroom = (Classroom)params.get("classroom");
					if(classroom.getId() != null && !classroom.getId().equals("")){
						WHERE("id LIKE CONCAT ('%',#{classroom.id},'%')");
					}
					if(classroom.getName() != null && !classroom.getName().equals("")){
						WHERE("name LIKE CONCAT ('%',#{classroom.name},'%')");
					}
					if(classroom.getSeatingCapacity()!=-1) {
						WHERE("seating_capacity=#{classroom.seatingCapacity}");
					}
					if(classroom.getType() != null && !classroom.getType().equals("")){
						WHERE("type LIKE CONCAT ('%',#{classroom.type},'%')");
					}
					if(classroom.getBuildingNum() != null ){
						WHERE("building_num LIKE CONCAT ('%',#{classroom.buildingNum},'%')");
					}
					
				}
			}
		}.toString();
	}
		
}
