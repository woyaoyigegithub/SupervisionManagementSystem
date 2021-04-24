package cn.grad.supm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static cn.grad.supm.utils.Constants.SUPERVISOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.AttendedLecturesRecord;
import cn.grad.supm.domain.Clazz;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.service.AttendedLecturesItemsService;
import cn.grad.supm.service.AttendedLecturesService;
import cn.grad.supm.service.CourseSelectionService;
import cn.grad.supm.service.Helper;
import cn.grad.supm.service.TableItemsListService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;


@Controller
public class AttendedLecturesController {

	@Autowired
	private AttendedLecturesItemsService attendedLecturesItemsService;
	@Autowired
	private AttendedLecturesService attendedLecturesService;
	@Autowired
	private TableItemsListService tableItemsListService;
	@Autowired
	private Helper helper;
	
	
	//=================听课项=============
	@RequestMapping("addAttendedLecturesItems")
	public String addAttendedLecturesItems() {
		return "addAttendedLecturesItems";
	}
	
	
	@RequestMapping("queryAttendedLecturesItems")
	public String queryAttendedLecturesItems(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute AttendedLecturesItems attendedLecturesItems,HttpSession session) {
		AttendedLecturesItems attendedLecturesItemsSession;
		PageModel pageModel=new PageModel();
		List<AttendedLecturesItems> attendedLecturesItemsList = null;
		
		System.out.println("pageIndex="+pageIndex+", attendedLecturesItems="+attendedLecturesItems);
		
		attendedLecturesItemsSession=(AttendedLecturesItems) session.getAttribute("attendedLecturesItemsSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryAttendedLecturesItems页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("attendedLecturesItemsSession", attendedLecturesItems);
			attendedLecturesItemsList=attendedLecturesItemsService.findAttendedLecturesItemsList(attendedLecturesItems, pageModel);
		}
		//非首次进入queryAttendedLecturesItems页面且传入页码
		else if(attendedLecturesItemsSession!=null && pageIndex>0) {
			attendedLecturesItemsList=attendedLecturesItemsService.findAttendedLecturesItemsList(attendedLecturesItemsSession, pageModel);
		}
		model.addAttribute("attendedLecturesItemsList", attendedLecturesItemsList);
		model.addAttribute("pageModel", pageModel);
		
		return "queryAttendedLecturesItems";
	}
	
	
	@RequestMapping("insertAttendedLecturesItems")
	@ResponseBody
	public Object insertAttendedLecturesItems(@RequestBody AttendedLecturesItems attendedLecturesItems) {
		try{ attendedLecturesItemsService.addAttendedLecturesItems(attendedLecturesItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateAttendedLecturesItems")
	@ResponseBody
	public Object updateAttendedLecturesItems(@RequestBody AttendedLecturesItems attendedLecturesItems) {
		try{ attendedLecturesItemsService.modifyAttendedLecturesItems(attendedLecturesItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteAttendedLecturesItems")
	public String deleteAttendedLecturesItems(int[] idList) {
		for(int id:idList) { attendedLecturesItemsService.removeAttendedLecturesItems(id); }
		return "redirect:/queryAttendedLecturesItems";
	}
	
	
	//=================听课==============
	
	@ModelAttribute
	public AttendedLecturesRecord attendedLecturesRecordModel(
			@ModelAttribute AttendedLecturesRecord attendedLectures,
			@SessionAttribute("staff")Staff staff,
			@RequestParam(defaultValue = "0",value="id")int id,
			@RequestParam(defaultValue = "",value="type")String type,
			@RequestParam(defaultValue = "",value="courseSelectionId")String courseSelectionId,
			@RequestParam(defaultValue = "",value="supervisorId")String supervisorId,
			@RequestParam(defaultValue = "",value="teacherId")String teacherId,
			@RequestParam(defaultValue = "",value="clazzId")String clazzId) {
		//封装attendedLectures
		attendedLectures.setId(id);
		attendedLectures.setType(type);
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId(courseSelectionId);
		Teacher teacher=new Teacher();
		teacher.setId(teacherId);
		courseSelection.setTeacher(teacher);
		attendedLectures.setCourseSelection(courseSelection);
		Supervisor supervisor=new Supervisor();
		
		//身份为督导，则查询督导填写过的的记录
		if(SUPERVISOR.equals(staff.getPower().getName())) {
			supervisor.setId(staff.getId());
		}else {
			supervisor.setId(supervisorId);
		}
		attendedLectures.setSupervisor(supervisor);
		Clazz clazz=new Clazz();
		clazz.setId(clazzId);
		attendedLectures.setClazz(clazz);
		return attendedLectures;
	}
	
	
	@RequestMapping("addAttendedLectures")
	public String addAttendedLectures(Model model,
			@RequestParam(defaultValue = "",value = "courseSelectionId")String courseSelectionId) {
		if(!"".equals(courseSelectionId)) {
			CourseSelection courseSelection=helper.findCourseSelectionById(courseSelectionId);
			if(courseSelection!=null) {
				String type=courseSelection.getCourse().getType();
				List<Integer> itemsIdList=Utils.getAttendedLecturesItemsIdList(type);
				List<AttendedLecturesItems> attendedLecturesItemList=tableItemsListService.findAttendedLecturesItemsList(itemsIdList);
				model.addAttribute("courseSelectionId", courseSelectionId);
				if(courseSelection.getClazz()!=null) { model.addAttribute("clazzId",courseSelection.getClazz().getId()); }
				model.addAttribute("numOfClass", courseSelection.getNumOfClass());
				model.addAttribute("attendedLecturesItemsList", attendedLecturesItemList);
			}
		}
		return "addAttendedLectures";
	}
	
	
	
	
	@RequestMapping("queryAttendedLectures")
	public String queryAttendedLectures(Model model,
			@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute AttendedLecturesRecord attendedLectures,
			HttpSession session) {
		
		AttendedLecturesRecord attendedLecturesSession;
		PageModel pageModel=new PageModel();
		List<AttendedLecturesRecord> attendedLecturesList = null;
		
		System.out.println("pageIndex="+pageIndex+", attendedLectures="+attendedLectures);
		
		attendedLecturesSession=(AttendedLecturesRecord) session.getAttribute("attendedLecturesSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryAttendedLectures页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("attendedLecturesSession", attendedLectures);
			attendedLecturesList=attendedLecturesService.findAttendedLecturesList(attendedLectures, pageModel);
		}
		//非首次进入queryAttendedLectures页面且传入页码
		else if(attendedLecturesSession!=null && pageIndex>0) {
			attendedLecturesList=attendedLecturesService.findAttendedLecturesList(attendedLecturesSession, pageModel);
		}
		model.addAttribute("attendedLecturesList", attendedLecturesList);
		model.addAttribute("pageModel", pageModel);
		System.out.println(attendedLecturesList);
		return "queryAttendedLectures";
	}
	
	
	@RequestMapping("insertAttendedLectures")
	@ResponseBody
	public Object insertAttendedLectures(Model model,@RequestBody AttendedLecturesRecord attendedLectures) {
		System.out.println(attendedLectures);
		try{ attendedLecturesService.addAttendedLectures(attendedLectures); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateAttendedLectures")
	@ResponseBody
	public Object updateAttendedLectures(@RequestBody AttendedLecturesRecord attendedLectures) {
		try{ attendedLecturesService.modifyAttendedLectures(attendedLectures); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
		
	}
	
	
	@RequestMapping("deleteAttendedLectures")
	public String deleteAttendedLectures(int[] idList) {
		for(int id:idList) { attendedLecturesService.removeAttendedLectures(id); }
		return "redirect:/queryAttendedLectures";
	}
	
	
	@RequestMapping("queryAttendedLecturesItemsList")
	@ResponseBody
	public Object queryAttendedLecturesItemsList(@RequestParam("type")String type) {
		List<Integer> itemsIdList=Utils.getAttendedLecturesItemsIdList(type);
		return tableItemsListService.findAttendedLecturesItemsList(itemsIdList);
	}
	
	
}
