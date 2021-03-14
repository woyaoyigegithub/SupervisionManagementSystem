package cn.grad.supm.listener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.grad.supm.utils.Utils;


public class TimerTaskListener implements ServletContextListener {
	
	private File file=Utils.getFile();
	private Document document=Utils.getDocument();
	private Element root=Utils.getRoot(); 
	
	public TimerTaskListener() {
		file=Utils.getFile();
		document=Utils.getDocument();
		root=Utils.getRoot(); 
	}

	
	//根据今天的日期判断是否是可创建新的学年，若可以则创建新的学年标签到tableContentItems.xml文件中，如没有则什么也不做
	private void createNewYear0() {
		String LastSchoolYear=((Element) root.selectSingleNode("//school-year/items[last()]")).attributeValue("value");
		String[] LastSchoolYearArray=LastSchoolYear.split("-");
		int schoolYear=Calendar.getInstance().get(Calendar.YEAR);
		int endSchoolYear=Integer.parseInt(LastSchoolYearArray[0]);
		if(schoolYear<=endSchoolYear) { return; }
		try {
			Element targetElement=(Element) root.selectSingleNode("//school-year");
			while(schoolYear>endSchoolYear) {
				targetElement.addElement("items").addAttribute("value", ++endSchoolYear+"-"+(endSchoolYear+1));
			}
			XMLWriter writer=new XMLWriter(new FileOutputStream(file), OutputFormat.createPrettyPrint());
			writer.write(document);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//开启一个定时器，每年元旦更新一次学年
	private void createNewSchoolYearTimer() {
		Calendar calendar=Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR)+1,1,1,0,0,0);
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				//执行任务
				createNewYear0();
				createNewSchoolYearTimer();
			}
		};
		Timer timer=new Timer();
		timer.schedule(task, calendar.getTime());
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//调用时执行一次，以后每次元旦执行一次
		createNewYear0();
		createNewSchoolYearTimer();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}
