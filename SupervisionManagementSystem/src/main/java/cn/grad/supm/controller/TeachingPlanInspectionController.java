package cn.grad.supm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.grad.supm.domain.AttendedLecturesItems;
import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.TeachingPlanInspectionItems;
import cn.grad.supm.domain.TeachingPlanInspectionRecord;
import cn.grad.supm.service.Helper;
import cn.grad.supm.service.TableItemsListService;
import cn.grad.supm.service.TeachingPlanInspectionItemsService;
import cn.grad.supm.service.TeachingPlanInspectionService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class TeachingPlanInspectionController {

	@Autowired
	private TeachingPlanInspectionItemsService teachingPlanInspectionItemsService;
	@Autowired
	private TeachingPlanInspectionService teachingPlanInspectionService;
	@Autowired
	private TableItemsListService tableItemsListService;
	@Autowired
	private Helper helper;
	
	
	
	//==================教案检查项================
	@RequestMapping("addTeachingPlanInspectionItems")
	public String addTeachingPlanInspectionItems() {
		return "addTeachingPlanInspectionItems";
	}
	
	
	@RequestMapping("queryTeachingPlanInspectionItems")
	public String queryTeachingPlanInspectionItems(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute TeachingPlanInspectionItems teachingPlanInspectionItems,HttpSession session) {
		TeachingPlanInspectionItems teachingPlanInspectionItemsSession;
		PageModel pageModel=new PageModel();
		List<TeachingPlanInspectionItems> teachingPlanInspectionItemsList = null;
		
		System.out.println("pageIndex="+pageIndex+", teachingPlanInspectionItems="+teachingPlanInspectionItems);
		
		teachingPlanInspectionItemsSession=(TeachingPlanInspectionItems) session.getAttribute("teachingPlanInspectionItemsSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryTeachingPlanInspectionItems页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("teachingPlanInspectionItemsSession", teachingPlanInspectionItems);
			teachingPlanInspectionItemsList=teachingPlanInspectionItemsService.findTeachingPlanInspectionItemsList(teachingPlanInspectionItems, pageModel);
		}
		//非首次进入queryTeachingPlanInspectionItems页面且传入页码
		else if(teachingPlanInspectionItemsSession!=null && pageIndex>0) {
			teachingPlanInspectionItemsList=teachingPlanInspectionItemsService.findTeachingPlanInspectionItemsList(teachingPlanInspectionItemsSession, pageModel);
		}
		model.addAttribute("teachingPlanInspectionItemsList", teachingPlanInspectionItemsList);
		model.addAttribute("pageModel", pageModel);
		return "queryTeachingPlanInspectionItems";
	}
	
	
	@RequestMapping("insertTeachingPlanInspectionItems")
	@ResponseBody
	public Object insertTeachingPlanInspectionItems(@RequestBody TeachingPlanInspectionItems teachingPlanInspectionItems) {
		try{ teachingPlanInspectionItemsService.addTeachingPlanInspectionItems(teachingPlanInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateTeachingPlanInspectionItems")
	@ResponseBody
	public Object updateTeachingPlanInspectionItems(@RequestBody TeachingPlanInspectionItems teachingPlanInspectionItems) {
		try{ teachingPlanInspectionItemsService.modifyTeachingPlanInspectionItems(teachingPlanInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteTeachingPlanInspectionItems")
	public String deleteTeachingPlanInspectionItems(int[] idList) {
		for(int id:idList) { teachingPlanInspectionItemsService.removeTeachingPlanInspectionItems(id); }
		return "redirect:/queryTeachingPlanInspectionItems";
	}
	
	
	
	//==================教案检查================
	@RequestMapping("addTeachingPlanInspection")
	public String addTeachingPlanInspection(Model model,
			@RequestParam(defaultValue = "",value = "courseSelectionId")String courseSelectionId) {
		if(!"".equals(courseSelectionId)) {
			CourseSelection courseSelection=helper.findCourseSelectionById(courseSelectionId);
			if(courseSelection!=null) {
				String type=courseSelection.getCourse().getType();
				List<Integer> itemsIdList=Utils.getTeachingPlanInspectionItemsIdList(type);
				List<TeachingPlanInspectionItems> teachingPlanInspectionItemsList=tableItemsListService.findTeachingPlanInspectionItemsList(itemsIdList);
				model.addAttribute("courseSelectionId", courseSelectionId);
				model.addAttribute("teachingPlanInspectionItemsList", teachingPlanInspectionItemsList);
			}
		}
		return "addTeachingPlanInspection";
	}
	
	
	@ModelAttribute
	public TeachingPlanInspectionRecord teachingPlanInspectionRecordModel(
			@ModelAttribute TeachingPlanInspectionRecord teachingPlanInspection,
			@RequestParam(defaultValue = "",value="courseSelectionId")String courseSelectionId) {
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId(courseSelectionId);
		teachingPlanInspection.setCourseSelection(courseSelection);
		return teachingPlanInspection;
	}
	
	
	@RequestMapping("queryTeachingPlanInspection")
	public String queryTeachingPlanInspection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute TeachingPlanInspectionRecord teachingPlanInspection,HttpSession session) {
		TeachingPlanInspectionRecord teachingPlanInspectionSession;
		PageModel pageModel=new PageModel();
		List<TeachingPlanInspectionRecord> teachingPlanInspectionList = null;
		
		System.out.println("pageIndex="+pageIndex+", teachingPlanInspection="+teachingPlanInspection);
		
		teachingPlanInspectionSession=(TeachingPlanInspectionRecord) session.getAttribute("teachingPlanInspectionSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryTeachingPlanInspection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("teachingPlanInspectionSession", teachingPlanInspection);
			teachingPlanInspectionList=teachingPlanInspectionService.findTeachingPlanInspectionList(teachingPlanInspection, pageModel);
		}
		//非首次进入queryTeachingPlanInspection页面且传入页码
		else if(teachingPlanInspectionSession!=null && pageIndex>0) {
			teachingPlanInspectionList=teachingPlanInspectionService.findTeachingPlanInspectionList(teachingPlanInspectionSession, pageModel);
		}
		model.addAttribute("teachingPlanInspectionList", teachingPlanInspectionList);
		model.addAttribute("pageModel", pageModel);
		return "queryTeachingPlanInspection";
	}
	
	
	@RequestMapping("insertTeachingPlanInspection")
	@ResponseBody
	public Object insertTeachingPlanInspection(@RequestBody TeachingPlanInspectionRecord teachingPlanInspection) {
		try{ teachingPlanInspectionService.addTeachingPlanInspection(teachingPlanInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateTeachingPlanInspection")
	@ResponseBody
	public Object updateTeachingPlanInspection(@RequestBody TeachingPlanInspectionRecord teachingPlanInspection) {
		try{ teachingPlanInspectionService.modifyTeachingPlanInspection(teachingPlanInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteTeachingPlanInspection")
	public String deleteTeachingPlanInspection(int[] idList) {
		for(int id:idList) { teachingPlanInspectionService.removeTeachingPlanInspection(id); }
		return "redirect:/queryTeachingPlanInspection";
	}
	
	
	@RequestMapping("queryTeachingPlanInspectionItemsList")
	@ResponseBody
	public Object queryTeachingPlanInspectionItemsList(String type) {
		List<Integer> itemsIdList=Utils.getTeachingPlanInspectionItemsIdList(type);
		return tableItemsListService.findTeachingPlanInspectionItemsList(itemsIdList);
	}
	
}
