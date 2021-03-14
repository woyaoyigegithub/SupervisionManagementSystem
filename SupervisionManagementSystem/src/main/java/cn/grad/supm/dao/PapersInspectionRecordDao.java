package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.STUDENT_DAO;
import static cn.grad.supm.utils.Constants.TEACHER_DAO;
import static cn.grad.supm.utils.Constants.SUPERVISOR_DAO;
import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_SITUATION_DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.PapersInspectionRecordDynaSqlProvider;
import cn.grad.supm.domain.PapersInspectionRecord;

public interface PapersInspectionRecordDao {

	
	@Select("SELECT * FROM "+PAPERS_INSPECTION_RECORD)
	@Results(id="papersInspectionRecordMap",value= {
			@Result(column = "id",property = "id",id = true),
			@Result(column = "student_id",property = "student",
					one=@One(select=STUDENT_DAO+".selectStudentById")),
			@Result(column = "Instructor_id",property = "instructor",
					one=@One(select=TEACHER_DAO+".selectTeacherById")),
			@Result(column = "consult_teacher_id",property = "consultTeacher",
					one=@One(select=SUPERVISOR_DAO+".selectSupervisorById")),
			@Result(column = "id",property = "papersInspectionSituationList",
					many=@Many(select=PAPERS_INSPECTION_SITUATION_DAO+".selectPapersInspectionSituationListByPapersInspectionRecordId")),
			@Result(column = "general_comments",property = "generalComments"),
	})
	public List<PapersInspectionRecord> selectAllPapersInspectionRecord();
	
	
	@Select("SELECT * FROM "+PAPERS_INSPECTION_RECORD+" WHERE id=#{id}")
	@ResultMap("papersInspectionRecordMap")
	public PapersInspectionRecord selectPapersInspectionRecordById(@Param("id")int id);
	
	
	@SelectProvider(type = PapersInspectionRecordDynaSqlProvider.class,method="selectWithParam")
	@ResultMap("papersInspectionRecordMap")
	public List<PapersInspectionRecord> selectPapersInspectionRecordListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+PAPERS_INSPECTION_RECORD+"(student_id,instructor_id,consult_teacher_id,general_comments,remarks) "
			+ "VALUE(#{student.id},#{instructor.id},#{consultTeacher.id},#{generalComments},#{remarks})")
	@Options(useGeneratedKeys = true)
	public void insertPapersInspectionRecord(PapersInspectionRecord papersInspectionRecord);
	
	
	@Update("UPDATE "+PAPERS_INSPECTION_RECORD+" SET student_id=#{student.id},instructor_id=#{instructor.id},"
			+ "consult_teacher_id=#{consultTeacher.id},general_comments=#{generalComments},remarks=#{remarks} "
			+ "WHERE id=#{id}")
	public void updatePapersInspectionRecord(PapersInspectionRecord papersInspectionRecord);
	
	
	@Delete("DELETE FROM "+PAPERS_INSPECTION_RECORD+" WHERE id=#{id}")
	public void deletePapersInspectionRecord(@Param("id")int id);
	
	
	@SelectProvider(type = PapersInspectionRecordDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
