package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Clazz;
import cn.grad.supm.utils.tag.PageModel;

public interface ClazzService {

	public List<Clazz> findClazzList(Clazz clazz,PageModel pageModel);
	
	
	public void addClazz(Clazz clazz);
	
	
	public void modifyClazz(Clazz clazz);
	
	
	public void removeClazz(String id);
	
}
