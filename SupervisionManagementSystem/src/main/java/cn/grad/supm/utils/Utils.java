package cn.grad.supm.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Utils {
	
	public static final SimpleDateFormat DATEFORMAT=new SimpleDateFormat("yyyy-MM-dd");
	private static final String resourcePath="tableContentItems.xml";
	
	private static SAXReader reader=null;
	private static File file=null;
	private static Document document=null;
	private static Element root=null;
	
	static {
		//初始化静态属性
		reader=new SAXReader();
		try {
			file=new File(Utils.class.getClassLoader().getResource(resourcePath).getPath());
			document = reader.read(file);
			root=document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
 
	
	public static SAXReader getReader() {
		return reader;
	}
	public static File getFile() {
		return file;
	}
	public static Document getDocument() {
		return document;
	}
	public static Element getRoot() {
		return root;
	}
	

	//获取表格项目列表
	@SuppressWarnings("unchecked")
	private static List<Integer> getItemsIdList(String record,String type){
		List<Integer> itemsIdList=new ArrayList<Integer>();
		List<Element> elements=null;
		if(type!=null && !"".equals(type)) {
			elements=(List<Element>) root.selectNodes("//record[@name='"+record+"']/itemsList[@type='"+type+"']/*");
		}else {
			elements=(List<Element>) root.selectNodes("//record[@name='"+record+"']/itemsList/*");
		}
		for(Element element:elements) {
			itemsIdList.add(Integer.parseInt(element.attributeValue("value")));
		}
		return itemsIdList;
	}
	
	public static List<Integer> getItemsIdList(String record){
		return getItemsIdList(record, null);
	}
	
	
	//获取听课项id列表
	public static List<Integer> getAttendedLecturesItemsIdList(String type){
		return getItemsIdList("attendedLectures",type);
	}
	
	
	//获取学院列表
	@SuppressWarnings("unchecked")
	public static List<String> getDepartmentList(){
		List<String> departmentList=new ArrayList<String>();
		List<Element> elements=root.selectNodes("//resource[@name='department']/*");
		for(Element element:elements) {
			departmentList.add(element.attributeValue("value"));
		}
		return departmentList;
	}
	
	
	//获取学年列表
	public static List<String> getSchoolYearList(){
		List<String> schoolYearList=new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<Element> elements=root.selectNodes("//school-year/*");
		for(Element element:elements) {
			schoolYearList.add(element.attributeValue("value"));
		}
		return schoolYearList;
	}
	
	
	
	//获取日常巡查项id列表
	public static List<Integer> getDailyInspectionItemsIdList(){
		return getItemsIdList("dailyInspection");
	}
	
	
	//获取巡考项id列表
	public static List<Integer> getTourInspectionItemsIdList(){
		return getItemsIdList("tourInspection");
	}
	
	
	//获取教案检查项id列表
	public static List<Integer> getTeachingPlanInspectionItemsIdList(String type){
		return getItemsIdList("teachingPlanInspection",type);
	}
	
	//获取教案检查项id列表
	public static List<Integer> getPapersInspectionItemsIdList(){
		return getItemsIdList("papersInspection");
	}
	
}
