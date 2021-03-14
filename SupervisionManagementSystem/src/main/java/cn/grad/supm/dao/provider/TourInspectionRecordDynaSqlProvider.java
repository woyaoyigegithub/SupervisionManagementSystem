package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.TOUR_INSPECTION_RECORD;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.TourInspectionRecord;


public class TourInspectionRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TOUR_INSPECTION_RECORD);
				if(params.get("tourInspectionRecord") != null){
					TourInspectionRecord tourInspectionRecord = (TourInspectionRecord)params.get("tourInspectionRecord");
					if(tourInspectionRecord.getId()!=0){
						WHERE("id =#{tourInspectionRecord.id}");
					}
					if(tourInspectionRecord.getDate()!=null){
						WHERE("date =#{tourInspectionRecord.date}");
					}
					if(tourInspectionRecord.getStartingTime()!=0) {
						WHERE("starting_time =#{tourInspectionRecord.startingTime}");
					}
					if(tourInspectionRecord.getEndingTime()!=0) {
						WHERE("ending_time =#{tourInspectionRecord.endingTime}");
					}
					if(tourInspectionRecord.getTourInspectionArea()!=null && !"".equals(tourInspectionRecord.getTourInspectionArea())) {
						WHERE("tour_inspection_area LIKE CONCAT('%',#{tourInspectionRecord.tourInspectionArea},'%')");
					}
					if(tourInspectionRecord.getSupervisor()!=null && !"".equals(tourInspectionRecord.getSupervisor().getId())) {
						WHERE("supervisor_id LIKE CONCAT('%',#{tourInspectionRecord.supervisor.id},'%')");
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
				FROM(TOUR_INSPECTION_RECORD);
				if(params.get("tourInspectionRecord") != null){
					TourInspectionRecord tourInspectionRecord = (TourInspectionRecord)params.get("tourInspectionRecord");
					if(tourInspectionRecord.getId()!=0){
						WHERE("id =#{tourInspectionRecord.id}");
					}
					if(tourInspectionRecord.getDate()!=null){
						WHERE("date =#{tourInspectionRecord.date}");
					}
					if(tourInspectionRecord.getStartingTime()!=0) {
						WHERE("starting_time =#{tourInspectionRecord.startingTime}");
					}
					if(tourInspectionRecord.getEndingTime()!=0) {
						WHERE("ending_time =#{tourInspectionRecord.endingTime}");
					}
					if(tourInspectionRecord.getTourInspectionArea()!=null && !"".equals(tourInspectionRecord.getTourInspectionArea())) {
						WHERE("tour_inspection_area LIKE CONCAT('%',#{tourInspectionRecord.tourInspectionArea},'%')");
					}
					if(tourInspectionRecord.getSupervisor()!=null && !"".equals(tourInspectionRecord.getSupervisor().getId())) {
						WHERE("supervisor_id LIKE CONCAT('%',#{tourInspectionRecord.supervisor.id},'%')");
					}
					
				}
			}
		}.toString();
	}
	
	
}
