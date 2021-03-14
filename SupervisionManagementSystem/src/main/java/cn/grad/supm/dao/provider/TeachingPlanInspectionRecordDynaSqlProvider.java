package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.TEACHING_PLAN_INSPECTION_RECORD;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.TeachingPlanInspectionRecord;


public class TeachingPlanInspectionRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TEACHING_PLAN_INSPECTION_RECORD);
				if(params.get("teachingPlanInspectionRecord") != null){
					TeachingPlanInspectionRecord teachingPlanInspectionRecord = (TeachingPlanInspectionRecord)params.get("teachingPlanInspectionRecord");
					CourseSelection courseSelection=teachingPlanInspectionRecord.getCourseSelection();
					
					if(teachingPlanInspectionRecord.getId()!=0){
						WHERE("id = #{teachingPlanInspectionRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null 
							&& !"".equals(courseSelection.getId())){
						WHERE("course_selection_id LIKE CONCAT('%', #{teachingPlanInspectionRecord.courseSelection.id}, '%')");
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
				FROM(TEACHING_PLAN_INSPECTION_RECORD);
				if(params.get("teachingPlanInspectionRecord") != null){
					TeachingPlanInspectionRecord teachingPlanInspectionRecord = (TeachingPlanInspectionRecord)params.get("teachingPlanInspectionRecord");
					CourseSelection courseSelection=teachingPlanInspectionRecord.getCourseSelection();
					
					if(teachingPlanInspectionRecord.getId()!=0){
						WHERE("id = #{teachingPlanInspectionRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null 
							&& !"".equals(courseSelection.getId())){
						WHERE("course_selection_id =#{teachingPlanInspectionRecord.courseSelection.id}");
					}
					
				}
			}
		}.toString();
	}
	
}
