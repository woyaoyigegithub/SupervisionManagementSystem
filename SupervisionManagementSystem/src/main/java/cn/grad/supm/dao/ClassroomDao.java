package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.CLASSROOM;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.ClassroomDynaSqlProvider;
import cn.grad.supm.domain.Classroom;

public interface ClassroomDao {

	@Select("SELECT * FROM "+CLASSROOM+" limit 0,20")
	@Results(id="classroomMap",value={
		@Result(column ="seating_capacity",property ="seatingCapacity"),
		@Result(column ="building_num",property ="buildingNum" )
	})
	public List<Classroom> selectAllClassroom();
	
	
	@SelectProvider(type =ClassroomDynaSqlProvider.class,method = "selectWithParam" )
	@ResultMap("classroomMap")
	public List<Classroom> selectClassroomListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+CLASSROOM+" WHERE id=#{id}")
	@ResultMap("classroomMap")
	public Classroom selectClassroomById(@Param("id")String id);
	
	
	@Insert("INSERT INTO "+CLASSROOM+"(id,name,seating_capacity,type,building_num) "
			+ "value(#{id},#{name},#{seatingCapacity},#{type},#{buildingNum})")
	public void insertClassroom(Classroom classroom);
	
	@Update("UPDATE "+CLASSROOM+" SET name=#{name},seating_capacity=#{seatingCapacity},"
			+ "type=#{type},building_num=#{buildingNum} WHERE id=#{id}")
	public void updateClassroom(Classroom classroom);
	
	
	@Delete("DELETE FROM "+CLASSROOM+" where id=#{id}")
	public void deleteClassroom(@Param("id")String id);
	
	
	@SelectProvider(type=ClassroomDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
}
