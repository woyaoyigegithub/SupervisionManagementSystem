package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.COURSE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.CourseDynaSqlProvider;
import cn.grad.supm.domain.Course;

public interface CourseDao {

	@Select("SELECT * FROM course limit 0,10")
	public List<Course> selectAllCourse();
	
	
	@SelectProvider(type =CourseDynaSqlProvider.class, method = "selectWithParam" )
	public List<Course> selectCourseListByPage(Map<String, Object> params);
	
	
	@Select("SELECT * FROM "+COURSE+" WHERE id=#{id}")
	public Course selectCourseById(@Param("id")String id);
	
	
	@Insert("INSERT INTO "+COURSE+"(id,name,nature,type) value(#{id},#{name},#{nature},#{type})")
	public void insertCourse(Course course);
	
	
	@Update("UPDATE "+COURSE+" SET name=#{name},nature=#{nature},type=#{type} WHERE id=#{id}")
	public void updateCourse(Course course);
	
	
	@Delete("DELETE FROM "+COURSE+" WHERE id=#{id}")
	public void deleteCourse(@Param("id") String id);
	
	
	@SelectProvider(type=CourseDynaSqlProvider.class,method="count")
	public int count(Map<String, Object> params);
	
}
