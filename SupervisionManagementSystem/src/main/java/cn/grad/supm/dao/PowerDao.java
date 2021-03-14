package cn.grad.supm.dao;


import static cn.grad.supm.utils.Constants.POWER;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.PowerDynaSqlProvider;
import cn.grad.supm.domain.Power;

public interface PowerDao {

	@Select("SELECT * FROM "+POWER)
	public List<Power> selectAllPower();
	
	@SelectProvider(type=PowerDynaSqlProvider.class,method="selectWithParam")
	public List<Power> selectPowerListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+POWER+" WHERE id=#{id}")
	public Power selectPowerById(@Param("id")int id);
	
	
	@Insert("INSERT INTO "+POWER+"(name,description) value(#{name},#{description})")
	@Options(useGeneratedKeys = true)
	public void insertPower(Power power);
	
	
	@Update("UPDATE "+POWER+" SET name=#{name},description=#{description} WHERE id=#{id}")
	public void updatePower(Power power);
	
	
	@Delete("DELETE FROM "+POWER+" WHERE id=#{id}")
	public void deletePower(@Param("id")int id);
	
	
	@SelectProvider(type=PowerDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
