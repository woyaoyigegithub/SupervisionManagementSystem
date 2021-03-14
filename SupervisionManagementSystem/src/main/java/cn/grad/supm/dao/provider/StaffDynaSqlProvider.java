package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.STAFF;
import static cn.grad.supm.utils.Constants.POWER;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.Power;
import cn.grad.supm.domain.Staff;


public class StaffDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(STAFF+","+POWER);
				WHERE(STAFF+".power_id = "+POWER+".id");
				if(params.get("staff") != null){
					Staff staff = (Staff)params.get("staff");
					Power power=staff.getPower();
					
					if(staff.getId()!=null && !"".equals(staff.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{staff.id},'%')");
					}
					if(staff.getName()!=null && !"".equals(staff.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{staff.name},'%') ");
					}
					if(staff.getDepartment()!=null && !"".equals(staff.getDepartment())){
						WHERE("department LIKE CONCAT ('%',#{staff.department},'%') ");
					}
					if(power!=null && power.getId()!=0){
						WHERE(POWER+".id = #{staff.power.id}");
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
				FROM(STAFF+","+POWER);
				WHERE(STAFF+".power_id = "+POWER+".id");
				if(params.get("staff") != null){
					Staff staff = (Staff)params.get("staff");
					Power power=staff.getPower();
					
					if(staff.getId()!=null && !"".equals(staff.getId())){
						WHERE(STAFF+".id LIKE CONCAT ('%',#{staff.id},'%')");
					}
					if(staff.getName()!=null && !"".equals(staff.getName())){
						WHERE(STAFF+".name LIKE CONCAT ('%',#{staff.name},'%') ");
					}
					if(staff.getDepartment()!=null && !"".equals(staff.getDepartment())){
						WHERE("department LIKE CONCAT ('%',#{staff.department},'%') ");
					}
					if(power!=null && power.getId()!=0){
						WHERE(POWER+".id = #{staff.power.id}");
					}
				}
			}
		}.toString();
		
	}
	
}
