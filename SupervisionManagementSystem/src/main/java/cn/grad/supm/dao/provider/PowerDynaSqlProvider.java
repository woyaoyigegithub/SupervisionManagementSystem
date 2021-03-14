package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.POWER;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.Power;


public class PowerDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(POWER);
				if(params.get("power") != null){
					Power power = (Power)params.get("power");
					if(power.getId()!=0){
						WHERE("id = #{power.id}");
					}
					if(power.getName()!=null && !"".equals(power.getName())){
						WHERE("name LIKE CONCAT ('%',#{power.name},'%')");
					}
					if(power.getDescription()!=null && !"".equals(power.getDescription())){
						WHERE("description LIKE CONCAT ('%',#{power.description},'%')");
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
				FROM(POWER);
				if(params.get("power") != null){
					Power power = (Power)params.get("power");
					if(power.getId()!=0){
						WHERE("id = #{power.id}");
					}
					if(power.getName()!=null && !"".equals(power.getName())){
						WHERE("name LIKE CONCAT ('%',#{power.name},'%')");
					}
					if(power.getDescription()!=null && !"".equals(power.getDescription())){
						WHERE("description LIKE CONCAT ('%',#{power.description},'%')");
					}
				}
			}
		}.toString();
	}
	
	
}
