package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_RECORD;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.domain.Student;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;


public class PapersInspectionRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(PAPERS_INSPECTION_RECORD);
				if(params.get("papersInspectionRecord") != null){
					PapersInspectionRecord papersInspectionRecord = (PapersInspectionRecord)params.get("papersInspectionRecord");
					Student student=papersInspectionRecord.getStudent();
					Teacher instructor=papersInspectionRecord.getInstructor();
					Supervisor consultTeacher=papersInspectionRecord.getConsultTeacher();
					
					if(papersInspectionRecord.getId()!=0){
						WHERE("id =#{papersInspectionRecord.id}");
					}
					if(student!=null && student.getId()!=null 
							&& !"".equals(student.getId())){
						WHERE("student_id LIKE CONCAT('%', #{papersInspectionRecord.student.id}, '%') ");
					}
					if(instructor!=null && instructor.getId()!=null 
							&& !"".equals(instructor.getId())){
						WHERE("instructor_id LIKE CONCAT('%', #{papersInspectionRecord.instructor.id}, '%') ");
					}
					if(consultTeacher!=null && consultTeacher.getId()!=null 
							&& !"".equals(consultTeacher.getId())){
						WHERE("consult_teacher_id LIKE CONCAT('%', #{papersInspectionRecord.consultTeacher.id}, '%') ");
					}
					
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(PAPERS_INSPECTION_RECORD);
				if(params.get("papersInspectionRecord") != null){
					PapersInspectionRecord papersInspectionRecord = (PapersInspectionRecord)params.get("papersInspectionRecord");
					Student student=papersInspectionRecord.getStudent();
					Teacher instructor=papersInspectionRecord.getInstructor();
					Supervisor consultTeacher=papersInspectionRecord.getConsultTeacher();
					
					if(papersInspectionRecord.getId()!=0){
						WHERE("id =#{papersInspectionRecord.id}");
					}
					if(student!=null && student.getId()!=null 
							&& !"".equals(student.getId())){
						WHERE("student_id =#{papersInspectionRecord.student.id}");
					}
					if(instructor!=null && instructor.getId()!=null 
							&& !"".equals(instructor.getId())){
						WHERE("instructor_id =#{papersInspectionRecord.instructor.id}");
					}
					if(consultTeacher!=null && consultTeacher.getId()!=null 
							&& !"".equals(consultTeacher.getId())){
						WHERE("consult_teacher_id =#{papersInspectionRecord.consultTeacher.id}");
					}
					
				}
			}
		}.toString();
	}
	
}
