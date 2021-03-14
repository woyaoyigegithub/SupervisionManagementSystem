package cn.grad.supm.controller;

import static cn.grad.supm.utils.Constants.SUPERVISOR;

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
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.grad.supm.domain.PapersInspectionItems;
import cn.grad.supm.domain.PapersInspectionRecord;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.domain.Student;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.Teacher;
import cn.grad.supm.service.PapersInspectionItemsService;
import cn.grad.supm.service.PapersInspectionService;
import cn.grad.supm.service.TableItemsListService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class PapersInspectionController {

	@Autowired
	private PapersInspectionItemsService papersInspectionItemsService;
	@Autowired
	private PapersInspectionService papersInspectionService;
	@Autowired
	private TableItemsListService tableItemsListService;
	
	
	//=====================毕业论文检查项===============
	@RequestMapping("addPapersInspectionItems")
	public String addPapersInspectionItems() {
		return "addPapersInspectionItems";
	}
	
	@RequestMapping("queryPapersInspectionItems")
	public String queryPapersInspectionItems(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute PapersInspectionItems papersInspectionItems,HttpSession session) {
		PapersInspectionItems papersInspectionItemsSession;
		PageModel pageModel=new PageModel();
		List<PapersInspectionItems> papersInspectionItemsList = null;
		
		System.out.println("pageIndex="+pageIndex+", papersInspectionItems="+papersInspectionItems);
		
		papersInspectionItemsSession=(PapersInspectionItems) session.getAttribute("papersInspectionItemsSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryPapersInspectionItems页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("papersInspectionItemsSession", papersInspectionItems);
			papersInspectionItemsList=papersInspectionItemsService.findPapersInspectionItemsList(papersInspectionItems, pageModel);
		}
		//非首次进入queryPapersInspectionItems页面且传入页码
		else if(papersInspectionItemsSession!=null && pageIndex>0) {
			papersInspectionItemsList=papersInspectionItemsService.findPapersInspectionItemsList(papersInspectionItemsSession, pageModel);
		}
		model.addAttribute("papersInspectionItemsList", papersInspectionItemsList);
		model.addAttribute("pageModel", pageModel);
		return "queryPapersInspectionItems";
	}
	
	
	@RequestMapping("insertPapersInspectionItems")
	@ResponseBody
	public Object insertPapersInspectionItems(Model model,@ModelAttribute PapersInspectionItems papersInspectionItems) {
		System.out.println(papersInspectionItems);
		try{ papersInspectionItemsService.addPapersInspectionItems(papersInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updatePapersInspectionItems")
	@ResponseBody
	public Object updatePapersInspectionItems(@ModelAttribute PapersInspectionItems papersInspectionItems) {
		System.out.println(papersInspectionItems);
		try{ papersInspectionItemsService.modifyPapersInspectionItems(papersInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deletePapersInspectionItems")
	public String deletePapersInspectionItems(int[] idList) {
		for(int id:idList) { papersInspectionItemsService.removePapersInspectionItems(id); }
		return "redirect:/queryPapersInspectionItems";
	}
	
	
	
	//=====================毕业论文检查================
	@RequestMapping("addPapersInspection")
	public String addPapersInspection(Model model) {
		List<PapersInspectionItems> papersInspectionItemList=tableItemsListService.findPapersInspectionItemsList(Utils.getPapersInspectionItemsIdList());
		model.addAttribute("papersInspectionItemList", papersInspectionItemList);
		return "addPapersInspection";
	}
	
	
	@ModelAttribute
	public PapersInspectionRecord papersInspectionRecordModel(
			@ModelAttribute PapersInspectionRecord papersInspection,
			@SessionAttribute("staff")Staff staff,
			@RequestParam(defaultValue = "",value="studentId")String studentId,
			@RequestParam(defaultValue = "",value="instructorId")String teacherId,
			@RequestParam(defaultValue = "",value="consultTeacherId")String supervisorId) {
		Student student=new Student();
		Teacher teacher=new Teacher();
		Supervisor supervisor=new Supervisor();
		student.setId(studentId);
		teacher.setId(teacherId);
		//身份为督导，则查询督导填写过的的记录
		if(SUPERVISOR.equals(staff.getPower().getName())) {
			supervisor.setId(staff.getId());
		}else {
			supervisor.setId(supervisorId);
		}
		papersInspection.setStudent(student);
		papersInspection.setInstructor(teacher);
		papersInspection.setConsultTeacher(supervisor);
		return papersInspection;
	}
	
	
	@RequestMapping("queryPapersInspection")
	public String queryPapersInspection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute PapersInspectionRecord papersInspection,HttpSession session) {
		PapersInspectionRecord papersInspectionSession;
		PageModel pageModel=new PageModel();
		List<PapersInspectionRecord> papersInspectionList = null;
		
		System.out.println("pageIndex="+pageIndex+", papersInspection="+papersInspection);
		
		papersInspectionSession=(PapersInspectionRecord) session.getAttribute("papersInspectionSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryPapersInspection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("papersInspectionSession", papersInspection);
			papersInspectionList=papersInspectionService.findPapersInspectionList(papersInspection, pageModel);
		}
		//非首次进入queryPapersInspection页面且传入页码
		else if(papersInspectionSession!=null && pageIndex>0) {
			papersInspectionList=papersInspectionService.findPapersInspectionList(papersInspectionSession, pageModel);
		}
		model.addAttribute("papersInspectionList", papersInspectionList);
		model.addAttribute("pageModel", pageModel);
		return "queryPapersInspection";
	}
	
	
	@RequestMapping("insertPapersInspection")
	@ResponseBody
	public Object insertPapersInspection(@RequestBody PapersInspectionRecord papersInspection) {
		System.out.println(papersInspection);
		try{ papersInspectionService.addPapersInspection(papersInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updatePapersInspection")
	@ResponseBody
	public Object updatePapersInspection(@RequestBody PapersInspectionRecord papersInspection) {
		System.out.println(papersInspection);
		try{ papersInspectionService.modifyPapersInspection(papersInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deletePapersInspection")
	public String deletePapersInspection(int[] idList) {
		for(int id:idList) { papersInspectionService.removePapersInspection(id); }
		return "redirect:/queryPapersInspection";
	}
	
	
	@RequestMapping("getPapersInspectionItemsList")
	@ResponseBody
	public Object getPapersInspectionItemsList() {
		List<Integer> itemsIdList=Utils.getItemsIdList("papersInspection");
		return tableItemsListService.findPapersInspectionItemsList(itemsIdList);
	}
	
	
}
