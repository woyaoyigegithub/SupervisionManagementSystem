package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.POWER;
import static cn.grad.supm.utils.Constants.POWER_DAO;
import static cn.grad.supm.utils.Constants.STAFF;
import static cn.grad.supm.utils.Constants.ADMIN;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.grad.supm.domain.Admin;

public interface AdminDao {

	
	@Select("SELECT "+STAFF+".id,"+STAFF+".name,"+STAFF+".department,"+STAFF+".password,"+STAFF+".power_id FROM "+STAFF+","+POWER+
			" WHERE "+STAFF+".power_id="+POWER+".id AND "+POWER+".name='"+ADMIN+"'"+" AND "+ STAFF+".id=#{id}")
	@Results(id="staffMap3",value= {
			@Result(column ="power_id",property = "power",
					one=@One(select = POWER_DAO+".selectPowerById"))
	})
	public Admin selectAdminById(@Param("id")String id);
	
	
}
