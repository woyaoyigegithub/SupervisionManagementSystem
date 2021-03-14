package cn.grad.supm.dao.provider;

import static cn.grad.supm.utils.Constants.DAILY_INSPECTION_RECORD;
import static cn.grad.supm.utils.Constants.INSPECTION_AREA_ARRANGEMENT_SITUATION;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.domain.Supervisor;


public class DailyInspectionRecordDynaSqlProvider {

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(DAILY_INSPECTION_RECORD);
				FROM(INSPECTION_AREA_ARRANGEMENT_SITUATION);
				
				WHERE(DAILY_INSPECTION_RECORD+".inspection_area_arrangement_situation_id="+INSPECTION_AREA_ARRANGEMENT_SITUATION+".id");
				if(params.get("dailyInspectionRecord") != null){
					DailyInspectionRecord dailyInspectionRecord = (DailyInspectionRecord)params.get("dailyInspectionRecord");
					InspectionAreaArrangementSituation inspectionAreaArrangementSituation = dailyInspectionRecord.getInspectionAreaArrangementSituation();
					Supervisor supervisor=dailyInspectionRecord.getInspectionAreaArrangementSituation().getSupervisor();
					System.out.println(supervisor);
					
					if(dailyInspectionRecord.getId()!=0){
						WHERE(DAILY_INSPECTION_RECORD+".id = #{dailyInspectionRecord.id}");
					}
					if(dailyInspectionRecord.getWeekly()!=0) {
						WHERE("weekly = #{dailyInspectionRecord.weekly}");
					}
					if(dailyInspectionRecord.getWeek()!=null && !"".equals(dailyInspectionRecord.getWeek())) {
						WHERE("week = #{dailyInspectionRecord.week}");
					}
					if(inspectionAreaArrangementSituation!=null && inspectionAreaArrangementSituation.getId()!=0) {
						WHERE("inspection_area_arrangement_situation_id = #{dailyInspectionRecord.inspectionAreaArrangementSituation.id}");
					}
					if(dailyInspectionRecord.getDate()!=null) {
						WHERE("date = #{dailyInspectionRecord.date}");
					}
					if(supervisor!=null && supervisor.getId()!=null && !"".equals(supervisor.getId())) {
						WHERE(INSPECTION_AREA_ARRANGEMENT_SITUATION+".supervisor_id = #{dailyInspectionRecord.inspectionAreaArrangementSituation.supervisor.id}");
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
				FROM(DAILY_INSPECTION_RECORD);
				if(params.get("dailyInspectionRecord") != null){
					DailyInspectionRecord dailyInspectionRecord = (DailyInspectionRecord)params.get("dailyInspectionRecord");
					InspectionAreaArrangementSituation inspectionAreaArrangementSituation = dailyInspectionRecord.getInspectionAreaArrangementSituation();
					
					if(dailyInspectionRecord.getId()!=0){
						WHERE(DAILY_INSPECTION_RECORD+".id = #{dailyInspectionRecord.id}");
					}
					if(dailyInspectionRecord.getWeekly()!=0) {
						WHERE("weekly = #{dailyInspectionRecord.weekly}");
					}
					if(dailyInspectionRecord.getWeek()!=null && !"".equals(dailyInspectionRecord.getWeek())) {
						WHERE("week = #{dailyInspectionRecord.week}");
					}
					if(inspectionAreaArrangementSituation!=null && inspectionAreaArrangementSituation.getId()!=0) {
						WHERE("inspection_area_arrangement_situation_id = #{dailyInspectionRecord.inspectionAreaArrangementSituation.id}");
					}
					if(dailyInspectionRecord.getDate()!=null) {
						WHERE("date = #{dailyInspectionRecord.date}");
					}
					
				}
			}
		}.toString();
	}
	
}
