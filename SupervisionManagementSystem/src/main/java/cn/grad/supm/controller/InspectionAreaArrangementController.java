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

import cn.grad.supm.domain.InspectionAreaArrangement;
import cn.grad.supm.service.InspectionAreaArrangementService;
import cn.grad.supm.utils.Utils;
import cn.grad.supm.utils.tag.PageModel;

@Controller
public class InspectionAreaArrangementController {

	@Autowired
	private InspectionAreaArrangementService inspectionAreaArrangementService;
	
	
	@RequestMapping("addInspectionAreaArrangement")
	public String addInspectionAreaArrangement(Model model) {
		model.addAttribute("schoolYearList", Utils.getSchoolYearList());
		return "addInspectionAreaArrangement";
	}
	
	@RequestMapping("queryInspectionAreaArrangement")
	public String queryInspectionAreaArrangement(Model model,@RequestParam(defaultValue="0",value="pageIndex")int pageIndex,
			@ModelAttribute InspectionAreaArrangement inspectionAreaArrangement,HttpSession session) {
		InspectionAreaArrangement inspectionAreaArrangementSession;
		PageModel pageModel=new PageModel();
		List<InspectionAreaArrangement> inspectionAreaArrangementList = null;
		
		System.out.println("pageIndex="+pageIndex+", inspectionAreaArrangement="+inspectionAreaArrangement);
		
		inspectionAreaArrangementSession=(InspectionAreaArrangement) session.getAttribute("inspectionAreaArrangementSession");
		pageModel.setPageIndex(pageIndex);
		
		//首次进入queryInspectionAreaArrangement页面时 和  进行高级搜索
		if(pageIndex==0) {
			session.setAttribute("inspectionAreaArrangementSession", inspectionAreaArrangement);
			inspectionAreaArrangementList=inspectionAreaArrangementService.findInspectionAreaArrangementList(inspectionAreaArrangement, pageModel);
		}
		//非首次进入queryInspectionAreaArrangement页面且传入页码
		else if(inspectionAreaArrangementSession!=null && pageIndex>0) {
			inspectionAreaArrangementList=inspectionAreaArrangementService.findInspectionAreaArrangementList(inspectionAreaArrangementSession, pageModel);
		}
		model.addAttribute("inspectionAreaArrangementList", inspectionAreaArrangementList);
		model.addAttribute("schoolYearList", Utils.getSchoolYearList());
		model.addAttribute("pageModel", pageModel);
		return "queryInspectionAreaArrangement";
	}
	
	
	@RequestMapping("insertInspectionAreaArrangement")
	@ResponseBody
	public Object insertInspectionAreaArrangement(@RequestBody InspectionAreaArrangement inspectionAreaArrangement) throws Exception {
		try{ inspectionAreaArrangementService.addInspectionAreaArrangement(inspectionAreaArrangement); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("updateInspectionAreaArrangement")
	@ResponseBody
	public Object updateInspectionAreaArrangement(@RequestBody InspectionAreaArrangement inspectionAreaArrangement) {
		try{ inspectionAreaArrangementService.modifyInspectionAreaArrangement(inspectionAreaArrangement); }
		catch (Exception e) { 
			return e.getLocalizedMessage();
		}
		return true;
	}
	
	
	@RequestMapping("deleteInspectionAreaArrangement")
	public String deleteInspectionAreaArrangement(int[] idList) {
		for(int id:idList) { inspectionAreaArrangementService.removeInspectionAreaArrangement(id); }
		return "redirect:/queryInspectionAreaArrangement";
	}
	
}
