package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.STUDENT;
import static cn.grad.supm.utils.Constants.CLAZZ_DAO;

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

import cn.grad.supm.dao.provider.StudentDynaSqlProvider;
import cn.grad.supm.domain.Student;;

public interface StudentDao {

	@Select("SELECT * FROM "+STUDENT)
	@Results(id="studentMap",value={
		@Result(column = "class_id",property = "clazz",
				one = @One(select = CLAZZ_DAO+".selectClazzById"))
	})
	public List<Student> selectAllStudent();
	
	
	@SelectProvider(type = StudentDynaSqlProvider.class, method="selectWithParam" )
	@ResultMap("studentMap")
	public List<Student> selectStudentListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+STUDENT+" WHERE id=#{id}")
	@ResultMap("studentMap")
	public Student selectStudentById(@Param("id")String id);
	
	
	@Insert("INSERT INTO "+STUDENT+"(id,class_id,name,sex) VALUE(#{id},#{clazz.id},#{name},#{sex})")
	public void insertStudent(Student student);
	
	@Update("UPDATE "+STUDENT+" SET class_id=#{clazz.id},name=#{name},sex=#{sex} WHERE id=#{id}")
	public void updateStudent(Student student);
	
	
	@Delete("DELETE FROM "+STUDENT+" WHERE id=#{id}")
	public void deleteStudent(@Param("id")String id);
	
	
	@SelectProvider(type=StudentDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
