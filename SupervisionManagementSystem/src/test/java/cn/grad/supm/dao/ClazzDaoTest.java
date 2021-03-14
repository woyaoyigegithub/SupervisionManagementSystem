package cn.grad.supm.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.*;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.utils.BaseDaoTest;
import cn.grad.supm.utils.tag.PageModel;


public class ClazzDaoTest extends BaseDaoTest{

	@Autowired
	private ClazzDao clazzDao;
	private Clazz clazz=new Clazz("2018010300", "18级test1班", "2018级test", "2018", "体育与健康学院", "本科");
	
	@Test
	public void testSelectAllClazz() {
		List<Clazz> clazzList=clazzDao.selectAllClazz();
		System.out.println(clazzList);
		assertThat(clazzList.size(), not(0));
	}
	
	@Test
	public void testSelectClazzListByPage() {
		Map<String,Object> params=new HashMap<String, Object>();
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(2);
		Clazz clazz=new Clazz();
		clazz.setUndergOrJun("本科");
		params.put("clazz", clazz);
		params.put("pageModel", pageModel);
		pageModel.setRecordCount(clazzDao.count(params));
		List<Clazz> clazzList=clazzDao.selectClazzListByPage(params);
		System.out.println(clazzList);
		System.out.println(pageModel.getRecordCount());
		assertThat(0, not(clazzList.size()));
	}
	
	
	@Test
	public void testSelectClazzById() {
		Clazz clazz=clazzDao.selectClazzById("2017010101");
		System.out.println(clazz);
		assertNotNull(clazz);
	}
	
	@Test
	public void testAddClazz() {
		clazzDao.insertClazz(clazz);
		
		Clazz clazz1=clazzDao.selectClazzById(clazz.getId());
		System.out.println(clazz1);
		assertNotNull(clazz1);
	}
	
	@Test
	public void testUpdateClazz() {
		clazzDao.insertClazz(clazz);
		clazz.setName("测试1");
		clazzDao.updateClazz(clazz);
		
		Clazz clazz1=clazzDao.selectClazzById(clazz.getId());
		System.out.println(clazz1);
		assertNotNull(clazz1);
	}
	
	@Test
	public void testDeleteClazz() {
		clazzDao.insertClazz(clazz);
		
		Clazz clazz1=clazzDao.selectClazzById(clazz.getId());
		System.out.println(clazz1);
		assertNotNull(clazz1);
		
		clazzDao.deleteClazz(clazz.getId());
		
		Clazz clazz2=clazzDao.selectClazzById(clazz.getId());
		System.out.println(clazz2);
		assertNull(clazz2);
	}

}
