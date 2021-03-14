package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.STAFF;
import static cn.grad.supm.utils.Constants.POWER_DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.StaffDynaSqlProvider;
import cn.grad.supm.domain.Staff;

public interface StaffDao {

	@Select("SELECT * FROM "+STAFF)
	@Results(id="staffMap",value= {
			@Result(column ="power_id",property = "power",
					one=@One(select = POWER_DAO+".selectPowerById"))
	})
	public List<Staff> selectAllStaff();
	
	
	@SelectProvider(type = StaffDynaSqlProvider.class,method = "selectWithParam")
	@ResultMap("staffMap")
	public List<Staff> selectStaffListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+STAFF+" WHERE id=#{id}")
	@ResultMap("staffMap")
	public Staff selectStaffById(@Param("id")String id);
	
	
	@Insert("INSERT INTO "+STAFF+"(id,name,department,password,power_id) "
			+ "value(#{id},#{name},#{department},#{password},#{power.id})")
	public void insertStaff(Staff staff);
	
	
	@Update("UPDATE "+STAFF+" SET name=#{name},department=#{department},"
			+ "password=#{password},power_id=#{power.id} WHERE id=#{id}")
	public void updateStaff(Staff staff);
	
	
	@Delete("DELETE FROM "+STAFF+" WHERE id=#{id}")
	public void deleteStaff(@Param("id")String id);
	
	
	@SelectProvider(type=StaffDynaSqlProvider.class,method = "count")
	public int count(Map<String,Object> params);
}
