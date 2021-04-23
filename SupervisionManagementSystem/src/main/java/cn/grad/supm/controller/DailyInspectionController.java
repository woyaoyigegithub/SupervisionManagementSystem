package cn.grad.supm.controller;

import java.util.List;

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

import cn.grad.supm.domain.DailyInspectionItems;
import cn.grad.supm.domain.DailyInspectionRecord;
import cn.grad.supm.domain.InspectionAreaArrangementSituation;
import cn.grad.supm.domain.Staff;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.service.DailyInspectionItemsService;
import cn.grad.supm.service.DailyInspectionService;
import cn.grad.supm.service.Helper;
import cn.grad.supm.service.InspectionAreaArrangementService;
import cn.grad.supm.service.TableItemsListService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class DailyInspectionController {

	@Autowired
	private DailyInspectionItemsService dailyInspectionItemsService;
	@Autowired
	private DailyInspectionService dailyInspectionService;
	@Autowired
	private InspectionAreaArrangementService inspectionAreaArrangementService;
	@Autowired
	private TableItemsListService tableItemsListService;
	@Autowired
	private Helper helper;
	
	
	
	//=======================日常巡查项==============
	@RequestMapping("addDailyInspectionItems")
	public String addDailyInspectionItems() {
		return "addDailyInspectionItems";
	}
	
	
	@RequestMapping("queryDailyInspectionItems")
	public String queryDailyInspectionItems(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute DailyInspectionItems dailyInspectionItems,HttpSession session) {
		DailyInspectionItems dailyInspectionItemsSession;
		PageModel pageModel=new PageModel();
		List<DailyInspectionItems> dailyInspectionItemsList = null;
		
		System.out.println("pageIndex="+pageIndex+", dailyInspectionItems="+dailyInspectionItems);
		
		dailyInspectionItemsSession=(DailyInspectionItems) session.getAttribute("dailyInspectionItemsSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryDailyInspectionItems页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("dailyInspectionItemsSession", dailyInspectionItems);
			dailyInspectionItemsList=dailyInspectionItemsService.findDailyInspectionItemsList(dailyInspectionItems, pageModel);
		}
		//非首次进入queryDailyInspectionItems页面且传入页码
		else if(dailyInspectionItemsSession!=null && pageIndex>0) {
			dailyInspectionItemsList=dailyInspectionItemsService.findDailyInspectionItemsList(dailyInspectionItemsSession, pageModel);
		}
		
		model.addAttribute("dailyInspectionItemsList", dailyInspectionItemsList);
		model.addAttribute("pageModel", pageModel);
		return "queryDailyInspectionItems";
	}
	
	@RequestMapping("insertDailyInspectionItems")
	@ResponseBody
	public Object insertDailyInspectionItems(@RequestBody DailyInspectionItems dailyInspectionItems) {
		try{ dailyInspectionItemsService.addDailyInspectionItems(dailyInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateDailyInspectionItems")
	@ResponseBody
	public Object updateDailyInspectionItems(@RequestBody DailyInspectionItems dailyInspectionItems) {
		try{ dailyInspectionItemsService.modifyDailyInspectionItems(dailyInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteDailyInspectionItems")
	public String deleteDailyInspectionItems(int[] idList) {
		for(int id:idList) { dailyInspectionItemsService.removeDailyInspectionItems(id); }
		return "redirect:/queryDailyInspectionItems";
	}
	
	
	//=================日常巡查================
	@RequestMapping("addDailyInspection")
	public String addDailyInspection(Model model,@SessionAttribute("staff")Staff staff,
			@RequestParam(defaultValue = "0",value = "inspectionAreaArrangementSituationId")int inspectionAreaArrangementSituationId) {
		//如果是从督查区域安排表的超链接过来的，则放置将被选中的督查区域人员安排编号
		if(inspectionAreaArrangementSituationId!=0) {
			model.addAttribute("selectedInspectionAreaArrangementSituationId", inspectionAreaArrangementSituationId);
		}
		//查询最新的督导专属的读到区域人员安排编号列表
		List<Integer> dailyInspectionItemsIdList=Utils.getDailyInspectionItemsIdList();
		List<Integer> inspectionAreaArrangementSituationIdList=helper.findInspectionAreaArrangementSituationIdList(staff);
		model.addAttribute("dailyInspectionItemsList", tableItemsListService.findDailyInspectionItemsList(dailyInspectionItemsIdList));
		model.addAttribute("inspectionAreaArrangementSituationIdList", inspectionAreaArrangementSituationIdList);
		return "addDailyInspection";
	}
	
	
	@ModelAttribute
	public DailyInspectionRecord dailyInspectionModel(
			@ModelAttribute DailyInspectionRecord dailyInspection,
			@SessionAttribute("staff")Staff staff,
			@RequestParam(defaultValue = "0",value="inspectionAreaArrangementSituationId") int inspectionAreaArrangementSituationId) {
		InspectionAreaArrangementSituation inspectionAreaArrangementSituation=new InspectionAreaArrangementSituation();
		inspectionAreaArrangementSituation.setId(inspectionAreaArrangementSituationId);
		
		//如果为身份为督导，则查询该督导填写过的记录
		if(SUPERVISOR.equals(staff.getPower().getName())) {
			Supervisor supervisor=new Supervisor();
			supervisor.setId(staff.getId());
			inspectionAreaArrangementSituation.setSupervisor(new Supervisor(staff.getId(),"",""));
		}
		dailyInspection.setInspectionAreaArrangementSituation(inspectionAreaArrangementSituation);
		return dailyInspection;
	}
	
	
	@RequestMapping("queryDailyInspection")
	public String queryDailyInspection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute DailyInspectionRecord dailyInspection,HttpSession session) {
		DailyInspectionRecord dailyInspectionSession;
		PageModel pageModel=new PageModel();
		List<DailyInspectionRecord> dailyInspectionList = null;
		
		System.out.println("pageIndex="+pageIndex+", dailyInspection="+dailyInspection);
		
		dailyInspectionSession=(DailyInspectionRecord) session.getAttribute("dailyInspectionSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryDailyInspection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("dailyInspectionSession", dailyInspection);
			dailyInspectionList=dailyInspectionService.findDailyInspectionList(dailyInspection, pageModel);
		}
		//非首次进入queryDailyInspection页面且传入页码
		else if(dailyInspectionSession!=null && pageIndex>0) {
			dailyInspectionList=dailyInspectionService.findDailyInspectionList(dailyInspectionSession, pageModel);
		}
		model.addAttribute("dailyInspectionList", dailyInspectionList);
		model.addAttribute("pageModel", pageModel);
		return "queryDailyInspection";
	}
	
	
	@RequestMapping("insertDailyInspection")
	@ResponseBody
	public Object insertDailyInspection(@RequestBody DailyInspectionRecord dailyInspection) {
		System.out.println(dailyInspection);
		try{ dailyInspectionService.addDailyInspection(dailyInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateDailyInspection")
	@ResponseBody
	public Object updateDailyInspection(@RequestBody DailyInspectionRecord dailyInspection) {
		System.out.println(dailyInspection);
		try{ dailyInspectionService.modifyDailyInspection(dailyInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteDailyInspection")
	public String deleteDailyInspection(int[] idList) {
		for(int id:idList) { dailyInspectionService.removeDailyInspection(id); }
		return "redirect:/queryDailyInspection";
	}
	
	
	@RequestMapping("getDailyInspectionItemsList")
	@ResponseBody
	public Object getDailyInspectionItemsList() {
		List<Integer> itemsIdList=Utils.getItemsIdList("dailyInspection");
		return tableItemsListService.findDailyInspectionItemsList(itemsIdList);
	}
	
	
	
}
