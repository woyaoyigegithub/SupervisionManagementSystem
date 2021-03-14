package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.COURSE_SELECTION;
import static cn.grad.supm.utils.Constants.COURSE_DAO;
import static cn.grad.supm.utils.Constants.TEACHER_DAO;
import static cn.grad.supm.utils.Constants.CLAZZ_DAO;
import static cn.grad.supm.utils.Constants.CLASSROOM_DAO;

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

import cn.grad.supm.dao.provider.CourseSelectionDynaSqlProvider;
import cn.grad.supm.domain.CourseSelection;;

public interface CourseSelectionDao {

	@Select("SELECT * FROM "+COURSE_SELECTION+" limit 0,10")
	@Results(id="courseSelectionMap",value={
			@Result(column = "course_id",property = "course",
					one=@One(select = COURSE_DAO+".selectCourseById")),
			@Result(column = "teacher_id",property = "teacher",
					one=@One(select = TEACHER_DAO+".selectTeacherById")),
			@Result(column = "class_id",property = "clazz",
					one=@One(select = CLAZZ_DAO+".selectClazzById")),
			@Result(column = "classroom_id",property = "classroom",
				one=@One(select = CLASSROOM_DAO+".selectClassroomById")),
			@Result(column = "school_year",property = "schoolYear"),
			@Result(column = "starting_and_ending_weeks",property = "startingAndEndingWeeks"),
			@Result(column = "num_of_class",property = "numOfClass"),
	})
	public List<CourseSelection> selectAllCourseSelection();
	
	
	@SelectProvider(type=CourseSelectionDynaSqlProvider.class,method="selectWithParam")
	@ResultMap("courseSelectionMap")
	public List<CourseSelection> selectCourseSelectionListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+COURSE_SELECTION+" WHERE id=#{id}")
	@ResultMap("courseSelectionMap")
	public CourseSelection selectCourseSelectionById(@Param("id")String id);
	
	
	@Insert("INSERT INTO "+COURSE_SELECTION+"(id,course_id,teacher_id,class_id,department,classroom_id,"
			+ "school_year,semester,week,jieci,biweekly,starting_and_ending_weeks,num_of_class) "
			+ "value(#{id},#{course.id},#{teacher.id},#{clazz.id},#{department},#{classroom.id},#{schoolYear},"
			+ "#{semester},#{week},#{jieci},#{biweekly},#{startingAndEndingWeeks},#{numOfClass})")
	public void insertCourseSelection(CourseSelection courseSelection);
	
	
	@Update("UPDATE "+COURSE_SELECTION+" SET course_id=#{course.id},teacher_id=#{teacher.id},class_id=#{clazz.id},"
			+ "department=#{department},classroom_id=#{classroom.id},school_year=#{schoolYear},semester=#{semester},"
			+ "week=#{week},jieci=#{jieci},biweekly=#{biweekly},starting_and_ending_weeks=#{startingAndEndingWeeks},"
			+ "num_of_class=#{numOfClass} WHERE id=#{id}")
	public void updateCourseSelection(CourseSelection courseSelection);
	
	
	@Delete("DELETE FROM "+COURSE_SELECTION+" WHERE id=#{id}")
	public void deleteCourseSelection(@Param("id")String id);
	
	
	@SelectProvider(type=CourseSelectionDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
}
