package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.InspectionAreaArrangement;


public class InspectionAreaArrangementDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(INSPECTION_AREA_ARRANGEMENT);
				if(params.get("inspectionAreaArrangement") != null){
					InspectionAreaArrangement inspectionAreaArrangement = (InspectionAreaArrangement)params.get("inspectionAreaArrangement");
					if(inspectionAreaArrangement.getId()!=0){
						WHERE("id =#{inspectionAreaArrangement.id}");
					}
					if(inspectionAreaArrangement.getSchoolYear()!=null && !"".equals(inspectionAreaArrangement.getSchoolYear())) {
						WHERE("school_year LIKE CONCAT('%', #{inspectionAreaArrangement.schoolYear}, '%')");
					}
					if(inspectionAreaArrangement.getSemester()!=-1) {
						WHERE("semester LIKE CONCAT('%', #{inspectionAreaArrangement.semester}, '%')");
					}
					if(inspectionAreaArrangement.getStartingAndEndingWeeks()!=null && !"".equals(inspectionAreaArrangement.getStartingAndEndingWeeks())) {
						WHERE("starting_and_ending_weeks LIKE CONCAT('%', #{inspectionAreaArrangement.startingAndEndingWeeks}, '%')");
					}
					if(inspectionAreaArrangement.getPublicationTime()!=null) {
						WHERE("publication_time LIKE CONCAT('%', #{inspectionAreaArrangement.publicationTime}, '%')");
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
				FROM(INSPECTION_AREA_ARRANGEMENT);
				if(params.get("inspectionAreaArrangement") != null){
					InspectionAreaArrangement inspectionAreaArrangement = (InspectionAreaArrangement)params.get("inspectionAreaArrangement");
					if(inspectionAreaArrangement.getId()!=0){
						WHERE("id =#{inspectionAreaArrangement.id}");
					}
					if(inspectionAreaArrangement.getSchoolYear()!=null && !"".equals(inspectionAreaArrangement.getSchoolYear())) {
						WHERE("school_year LIKE CONCAT('%', #{inspectionAreaArrangement.schoolYear}, '%')");
					}
					if(inspectionAreaArrangement.getSemester()!=-1) {
						WHERE("semester LIKE CONCAT('%', #{inspectionAreaArrangement.semester}, '%')");
					}
					if(inspectionAreaArrangement.getStartingAndEndingWeeks()!=null && !"".equals(inspectionAreaArrangement.getStartingAndEndingWeeks())) {
						WHERE("starting_and_ending_weeks LIKE CONCAT('%', #{inspectionAreaArrangement.startingAndEndingWeeks}, '%')");
					}
					if(inspectionAreaArrangement.getPublicationTime()!=null) {
						WHERE("publication_time LIKE CONCAT('%', #{inspectionAreaArrangement.publicationTime}, '%')");
					}
					
				}
			}
		}.toString();
	}
	
}
