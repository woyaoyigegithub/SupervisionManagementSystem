package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.ATTENDED_LECTURES_ITEMS;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.grad.supm.dao.provider.AttendedLecturesItemsDynaSqlProvider;
import cn.grad.supm.domain.AttendedLecturesItems;

public interface AttendedLecturesItemsDao {

	@Select("SELECT * FROM "+ATTENDED_LECTURES_ITEMS+" limit 0,10")
	public List<AttendedLecturesItems> selectAllAttendedLecturesItems();
	
	
	@SelectProvider(type =AttendedLecturesItemsDynaSqlProvider.class,method = "selectWithParam")
	public List<AttendedLecturesItems> selectAttendedLecturesItemsListByPage(Map<String,Object> params);
	
	
	@Select("SELECT * FROM "+ATTENDED_LECTURES_ITEMS+" WHERE id=#{id}")
	public AttendedLecturesItems selectAttendedLecturesItemsById(@Param("id")int id);
	
	
	@Insert("INSERT INTO "+ATTENDED_LECTURES_ITEMS+"(content,score) value(#{content},#{score})")
	@Options(useGeneratedKeys = true)
	public void insertAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems);
	
	
	@Insert("UPDATE "+ATTENDED_LECTURES_ITEMS+" SET content=#{content},score=#{score} WHERE id=#{id}")
	public void updateAttendedLecturesItems(AttendedLecturesItems attendedLecturesItems);
	
	
	@Delete("DELETE FROM "+ATTENDED_LECTURES_ITEMS+" WHERE id=#{id}")
	public void deleteAttendedLecturesItems(int id);
	
	
	@SelectProvider(type =AttendedLecturesItemsDynaSqlProvider.class,method = "count")
	public int count(Map<String,Object> params);
	
}
