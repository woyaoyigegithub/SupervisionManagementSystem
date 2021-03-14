package cn.grad.supm.service;

import java.util.List;
import java.util.Map;

public interface Statistics {

	public List<Map<String,String>> countBySchoolYearAndSemester(String supervisorId);
	
}
