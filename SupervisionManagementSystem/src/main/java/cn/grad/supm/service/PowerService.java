package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Power;
import cn.grad.supm.utils.tag.PageModel;

public interface PowerService {

	public List<Power> findAllPower();
	public List<Power> findPowerList(Power power,PageModel pageModel);
	
	
	public void addPower(Power power);
	
	
	public void modifyPower(Power power);
	
	
	public void removePower(int id);
}
