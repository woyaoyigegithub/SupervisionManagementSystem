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

import cn.grad.supm.domain.CourseSelection;
import cn.grad.supm.domain.HomeworkInspectionRecord;
import cn.grad.supm.service.HomeworkInspectionService;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class HomeworkInspectionController {

	@Autowired
	private HomeworkInspectionService homeworkInspectionService;
	
	
	@RequestMapping("addHomeworkInspection")
	public String addHomeworkInspection(Model model,
			@RequestParam(defaultValue = "", value = "courseSelectionId")String courseSelectionId) {
		if(!"".equals(courseSelectionId)) {
			model.addAttribute("courseSelectionId", courseSelectionId);
		}
		return "addHomeworkInspection";
	}
	
	@ModelAttribute
	public HomeworkInspectionRecord homeworkInspectionRecordModel(
			@ModelAttribute HomeworkInspectionRecord homeworkInspectionRecord,
			@RequestParam(defaultValue = "",value="courseSelectionId")String courseSelectionId) {
		CourseSelection courseSelection=new CourseSelection();
		courseSelection.setId(courseSelectionId);
		homeworkInspectionRecord.setCourseSelection(courseSelection);
		return homeworkInspectionRecord;
	}
	
	
	@RequestMapping("queryHomeworkInspection")
	public String queryHomeworkInspection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute HomeworkInspectionRecord homeworkInspectionRecord,HttpSession session) {
		HomeworkInspectionRecord homeworkInspectionRecordSession;
		PageModel pageModel=new PageModel();
		List<HomeworkInspectionRecord> homeworkInspectionRecordList = null;
		
		System.out.println("pageIndex="+pageIndex+", homeworkInspectionRecord="+homeworkInspectionRecord);
		
		homeworkInspectionRecordSession=(HomeworkInspectionRecord) session.getAttribute("homeworkInspectionRecordSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryHomeworkInspection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("homeworkInspectionRecordSession", homeworkInspectionRecord);
			homeworkInspectionRecordList=homeworkInspectionService.findHomeworkInspectionList(homeworkInspectionRecord, pageModel);
		}
		//非首次进入queryHomeworkInspection页面且传入页码
		else if(homeworkInspectionRecordSession!=null && pageIndex>0) {
			homeworkInspectionRecordList=homeworkInspectionService.findHomeworkInspectionList(homeworkInspectionRecordSession, pageModel);
		}
		model.addAttribute("homeworkInspectionList", homeworkInspectionRecordList);
		model.addAttribute("pageModel", pageModel);
		return "queryHomeworkInspection";
	}
	
	
	@RequestMapping("insertHomeworkInspection")
	@ResponseBody
	public Object insertHomeworkInspection(@RequestBody HomeworkInspectionRecord homeworkInspectionRecord) {
		System.out.println(homeworkInspectionRecord);
		try{ homeworkInspectionService.addHomeworkInspection(homeworkInspectionRecord); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateHomeworkInspection")
	@ResponseBody
	public Object updateHomeworkInspection(@RequestBody HomeworkInspectionRecord homeworkInspectionRecord) {
		try{ homeworkInspectionService.modifyHomeworkInspection(homeworkInspectionRecord); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteHomeworkInspection")
	public String deleteHomeworkInspection(int[] idList) {
		for(int id:idList) { homeworkInspectionService.removeHomeworkInspection(id); }
		return "redirect:/queryHomeworkInspection";
	}
	
}
