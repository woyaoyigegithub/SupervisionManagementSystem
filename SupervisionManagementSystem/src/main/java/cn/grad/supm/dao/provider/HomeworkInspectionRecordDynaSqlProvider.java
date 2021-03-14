package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.HOMEWORK_INSPECTION_RECORD;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.HomeworkInspectionRecord;


public class HomeworkInspectionRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(HOMEWORK_INSPECTION_RECORD);
				if(params.get("homeworkInspectionRecord") != null){
					HomeworkInspectionRecord homeworkInspectionRecord = (HomeworkInspectionRecord)params.get("homeworkInspectionRecord");
					CourseSelection courseSelection=homeworkInspectionRecord.getCourseSelection();
					
					if(homeworkInspectionRecord.getId()!=0){
						WHERE("id = #{homeworkInspectionRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null 
							&& !"".equals(courseSelection.getId())){
						WHERE("course_selection_id LIKE CONCAT ('%',#{homeworkInspectionRecord.courseSelection.id},'%') ");
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
				FROM(HOMEWORK_INSPECTION_RECORD);
				if(params.get("homeworkInspectionRecord") != null){
					HomeworkInspectionRecord homeworkInspectionRecord = (HomeworkInspectionRecord)params.get("homeworkInspectionRecord");
					CourseSelection courseSelection=homeworkInspectionRecord.getCourseSelection();
					
					if(homeworkInspectionRecord.getId()!=0){
						WHERE("id = #{homeworkInspectionRecord.id}");
					}
					if(courseSelection!=null && courseSelection.getId()!=null 
							&& !"".equals(courseSelection.getId())){
						WHERE("course_selection_id =#{homeworkInspectionRecord.courseSelection.id}");
					}
					
				}
			}
		}.toString();
	}
	
	
}
