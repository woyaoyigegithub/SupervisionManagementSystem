package cn.grad.supm.dao;

import static cn.grad.supm.utils.Constants.PAPERS_INSPECTION_ITEMS;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.grad.supm.dao.provider.PapersInspectionItemsDynaSqlProvider;
import cn.grad.supm.domain.PapersInspectionItems;

public interface PapersInspectionItemsDao {

	@Select("SELECT * FROM "+PAPERS_INSPECTION_ITEMS)
	public List<PapersInspectionItems> selectAllPapersInspectionItems();
	
	
	@Select("SELECT * FROM "+PAPERS_INSPECTION_ITEMS+" WHERE id=#{id}")
	public PapersInspectionItems selectPapersInspectionItemsById(@Param("id")int id);
	
	
	@SelectProvider(type=PapersInspectionItemsDynaSqlProvider.class,method="selectWithParam")
	public List<PapersInspectionItems> selectPapersInspectionItemsListByPage(Map<String,Object> params);
	
	
	@Insert("INSERT INTO "+PAPERS_INSPECTION_ITEMS+"(content,options) VALUE(#{content},#{options})")
	@Options(useGeneratedKeys = true)
	public void insertPapersInspectionItems(PapersInspectionItems papersInspectionItems);
	
	
	@Update("UPDATE "+PAPERS_INSPECTION_ITEMS+" SET content=#{content},options=#{options} WHERE id=#{id}")
	public void updatePapersInspectionItems(PapersInspectionItems papersInspectionItems);
	
	
	@Delete("DELETE FROM "+PAPERS_INSPECTION_ITEMS+" WHERE id=#{id}")
	public void deletePapersInspectionItems(@Param("id")int id);
	
	
	@SelectProvider(type=PapersInspectionItemsDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
}
