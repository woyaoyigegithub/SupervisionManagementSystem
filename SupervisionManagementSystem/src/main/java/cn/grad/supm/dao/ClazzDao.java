package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.CLASS;

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

import cn.grad.supm.dao.provider.ClazzDynaSqlProvider;
import cn.grad.supm.domain.Clazz;

public interface ClazzDao {

	@Select("SELECT * FROM "+CLASS+" limit 0,10")
	@Results(id="clazzMap",value={
		@Result(column = "underg_or_jun",property = "undergOrJun")
	})
	public List<Clazz> selectAllClazz();
	
	
	@SelectProvider(type = ClazzDynaSqlProvider.class,method = "selectWithParam")
	@ResultMap("clazzMap")
	public List<Clazz> selectClazzListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+CLASS+" where id=#{id}")
	@ResultMap("clazzMap")
	public Clazz selectClazzById(@Param("id")String id);
	

	@Insert("INSERT INTO "+CLASS+"(id,name,major,grade,department,underg_or_jun) "
			+ "value(#{id},#{name},#{major},#{grade},#{department},#{undergOrJun})")
	public void insertClazz(Clazz clazz);
	
	
	@Update("UPDATE "+CLASS+" SET name=#{name},major=#{major},grade=#{grade},"
			+ "department=#{department},underg_or_jun=#{undergOrJun} WHERE id=#{id}")
	public void updateClazz(Clazz clazz);
	
	
	@Delete("DELETE FROM "+CLASS+" WHERE id=#{id}")
	public void deleteClazz(@Param("id")String id);
	
	
	@SelectProvider(type = ClazzDynaSqlProvider.class,method = "count")
	public int count(Map<String,Object> params);
	
}
