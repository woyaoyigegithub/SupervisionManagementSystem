package cn.grad.supm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.grad.supm.domain.Staff;
import cn.grad.supm.service.AttendedLecturesService;
import cn.grad.supm.service.DailyInspectionService;
import cn.grad.supm.service.TourInspectionService;

@Controller
public class DataStatisticsController {

	@Autowired
	private AttendedLecturesService attendedLecturesService;
	@Autowired
	private DailyInspectionService dailyInspectionService;
	@Autowired
	private TourInspectionService tourInspectionService;
	
	
	@RequestMapping("countAttendedLecturesNum")
	public String countAttendedLecturesNum(Model model,@SessionAttribute("staff")Staff staff) {

		List<Map<String,String>> attendedLecturesCountsList=attendedLecturesService.countBySchoolYearAndSemester(staff.getId());
		model.addAttribute("attendedLecturesCountsList", attendedLecturesCountsList);
		return "countAttendedLecturesNum";
	}
	
	@RequestMapping("countDailyInspectionNum")
	public String countDailyInspectionNum(Model model,@SessionAttribute("staff")Staff staff) {
		
		List<Map<String,String>> dailyInspectionCountsList=dailyInspectionService.countBySchoolYearAndSemester(staff.getId());
		model.addAttribute("dailyInspectionCountsList", dailyInspectionCountsList);
		return "countDailyInspectionNum";
	}
	
	@RequestMapping("countTourInspectionNum")
	public String countTourInspectionNum(Model model,@SessionAttribute("staff")Staff staff) {
		
		List<Map<String,String>> tourInspectionCountsList=tourInspectionService.countBySchoolYearAndSemester(staff.getId());
		model.addAttribute("tourInspectionCountsList", tourInspectionCountsList);
		return "countTourInspectionNum";
	}
}
