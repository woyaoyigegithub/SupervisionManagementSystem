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

import cn.grad.supm.domain.Staff;
import cn.grad.supm.domain.Supervisor;
import cn.grad.supm.domain.TourInspectionItems;
import cn.grad.supm.domain.TourInspectionRecord;
import cn.grad.supm.service.TableItemsListService;
import cn.grad.supm.service.TourInspectionItemsService;
import cn.grad.supm.service.TourInspectionService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class TourInspectionController {

	@Autowired
	private TourInspectionItemsService tourInspectionItemsService;
	@Autowired
	private TourInspectionService tourInspectionService;
	@Autowired
	private TableItemsListService tableItemsListService;
	
	
	//================巡查项===============
	@RequestMapping("addTourInspectionItems")
	public String addTourInspectionItems() {
		return "addTourInspectionItems";
	}
	
	@RequestMapping("queryTourInspectionItems")
	public String queryTourInspectionItems(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute TourInspectionItems tourInspectionItems,HttpSession session) {
		TourInspectionItems tourInspectionItemsSession;
		PageModel pageModel=new PageModel();
		List<TourInspectionItems> tourInspectionItemsList = null;
		
		System.out.println("pageIndex="+pageIndex+", tourInspectionItems="+tourInspectionItems);
		
		tourInspectionItemsSession=(TourInspectionItems) session.getAttribute("tourInspectionItemsSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryTourInspectionItems页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("tourInspectionItemsSession", tourInspectionItems);
			tourInspectionItemsList=tourInspectionItemsService.findTourInspectionItemsList(tourInspectionItems, pageModel);
		}
		//非首次进入queryTourInspectionItems页面且传入页码
		else if(tourInspectionItemsSession!=null && pageIndex>0) {
			tourInspectionItemsList=tourInspectionItemsService.findTourInspectionItemsList(tourInspectionItemsSession, pageModel);
		}
		model.addAttribute("tourInspectionItemsList", tourInspectionItemsList);
		model.addAttribute("pageModel", pageModel);
		return "queryTourInspectionItems";
	}
	
	
	@RequestMapping("insertTourInspectionItems")
	@ResponseBody
	public Object insertTourInspectionItems(@RequestBody TourInspectionItems tourInspectionItems) {
		try{ tourInspectionItemsService.addTourInspectionItems(tourInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateTourInspectionItems")
	@ResponseBody
	public Object updateTourInspectionItems(@RequestBody TourInspectionItems tourInspectionItems) {
		try{ tourInspectionItemsService.modifyTourInspectionItems(tourInspectionItems); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteTourInspectionItems")
	public String deleteTourInspectionItems(int[] idList) {
		for(int id:idList) { tourInspectionItemsService.removeTourInspectionItems(id); }
		return "redirect:/queryTourInspectionItems";
	}
	
	
	
	//================巡查记录===============
	@RequestMapping("addTourInspection")
	public String addTourInspection(Model model) {
		model.addAttribute("tourInspectionItemsList", tableItemsListService.findTourInspectionItemsList(Utils.getTourInspectionItemsIdList()));
		return "addTourInspection";
	}
	
	@ModelAttribute
	public TourInspectionRecord tourInspectionRecordModel(
			@ModelAttribute TourInspectionRecord tourInspection,
			@SessionAttribute("staff")Staff staff,
			@RequestParam(defaultValue = "",value="supervisorId")String supervisorId) {
		
		Supervisor supervisor=new Supervisor();
		if(SUPERVISOR.equals(staff.getPower().getName())) {
			supervisor.setId(staff.getId());
		}else {
			supervisor.setId(supervisorId);
		}
		tourInspection.setSupervisor(supervisor);
		return tourInspection;
	}
	
	
	@RequestMapping("queryTourInspection")
	public String queryTourInspection(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute TourInspectionRecord tourInspection,HttpSession session) {
		TourInspectionRecord tourInspectionSession;
		PageModel pageModel=new PageModel();
		List<TourInspectionRecord> tourInspectionList = null;
		
		System.out.println("pageIndex="+pageIndex+", tourInspection="+tourInspection);
		
		tourInspectionSession=(TourInspectionRecord) session.getAttribute("tourInspectionSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryTourInspection页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("tourInspectionSession", tourInspection);
			tourInspectionList=tourInspectionService.findTourInspectionList(tourInspection, pageModel);
		}
		//非首次进入queryTourInspection页面且传入页码
		else if(tourInspectionSession!=null && pageIndex>0) {
			tourInspectionList=tourInspectionService.findTourInspectionList(tourInspectionSession, pageModel);
		}
		model.addAttribute("tourInspectionList", tourInspectionList);
		model.addAttribute("pageModel", pageModel);
		return "queryTourInspection";
	}
	
	
	@RequestMapping("insertTourInspection")
	@ResponseBody
	public Object insertTourInspection(@RequestBody TourInspectionRecord tourInspection) {
		try{ tourInspectionService.addTourInspection(tourInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateTourInspection")
	@ResponseBody
	public Object updateTourInspection(@RequestBody TourInspectionRecord tourInspection) {
		try{ tourInspectionService.modifyTourInspection(tourInspection); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteTourInspection")
	public String deleteTourInspection(int[] idList) {
		for(int id:idList) { tourInspectionService.removeTourInspection(id); }
		return "redirect:/queryTourInspection";
	}
	
	
	@RequestMapping("getTourInspectionItemsList")
	@ResponseBody
	public Object getTourInspectionItemsList() {
		List<Integer> itemsIdList=Utils.getItemsIdList("tourInspection");
		return tableItemsListService.findTourInspectionItemsList(itemsIdList);
	}
	
	
}
