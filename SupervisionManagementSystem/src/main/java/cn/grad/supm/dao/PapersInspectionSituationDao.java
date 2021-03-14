package cn.grad.supm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.domain.PapersInspectionSituation;

import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_SITUATION;
import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_ITEMS_DAO;

import java.util.List;

public interface PapersInspectionSituationDao {

	@Select("SELECT * FROM "+PAPERS_INSPECTION_SITUATION+" WHERE papers_inspection_record_id=#{papersInspectionRecordId}")
	@Results(id="papersInspectionSituationMap",value= {
			@Result(column = "papers_inspection_items_id",property = "papersInspectionItems",
					one=@One(select = PAPERS_INSPECTION_ITEMS_DAO+".selectPapersInspectionItemsById")),
	})
	public List<PapersInspectionSituation> selectPapersInspectionSituationListByPapersInspectionRecordId(@Param("papersInspectionRecordId")int papersInspectionRecordId);
	
	
	@Insert("INSERT INTO "+PAPERS_INSPECTION_SITUATION+"(papers_inspection_record_id,papers_inspection_items_id,situation,remarks) "
			+ "VALUE(#{papersInspectionRecordId},#{arg1.papersInspectionItems.id},#{arg1.situation},#{arg1.remarks})")
	public void insertPapersInspectionSituation(@Param("papersInspectionRecordId")int papersInspectionRecordId,PapersInspectionSituation papersInspectionSituation);
	
	
	@Update("UPDATE "+PAPERS_INSPECTION_SITUATION+" SET situation=#{arg1.situation},remarks=#{arg1.remarks} WHERE papers_inspection_record_id=#{papersInspectionRecordId} "
			+ "AND papers_inspection_items_id=#{arg1.papersInspectionItems.id}")
	public void updatePapersInspectionSituation(@Param("papersInspectionRecordId")int papersInspectionRecordId,PapersInspectionSituation papersInspectionSituation);
	
	
	@Delete("DELETE FROM "+PAPERS_INSPECTION_SITUATION+" WHERE papers_inspection_record_id=#{papersInspectionRecordId}")
	public void deletePapersInspectionSituation(@Param("papersInspectionRecordId")int papersInspectionRecordId);
}
