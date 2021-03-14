package cn.grad.supm.service;

import java.util.List;

import cn.grad.supm.domain.Classroom;
import cn.grad.supm.utils.tag.PageModel;

public interface ClassroomService {

	public List<Classroom> findClassroomList(Classroom classroom,PageModel pageModel);
	
	
	public void addClassroom(Classroom classroom);
	
	
	public void modifyClassroom(Classroom classroom);
	
	
	public void removeClassroom(String id);
	
}
