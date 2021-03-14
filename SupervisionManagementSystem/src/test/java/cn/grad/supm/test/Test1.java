package cn.grad.supm.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.grad.supm.utils.Utils;

public class Test1 {

	/*{"courseSelection":{"id":"(2020-2021-1)-0010000001-0001317-2"},
	 * "supervisor":{"id":"0000954"},"teachingSection":"第一章","clazz":{"id":"2020010179"},
	 * "actualNum":"0","lateNum":"0","leavingEarlyNum":"0","date":"2021-01-09",
	 * "weekly":"5","week":"六","jieci":"3-4",
	 * "attendedLecturesSituationList":[{"id":"10","score":"9","remarks":""},{"id":"11","score":"9","remarks":""},{"id":"12","score":"9","remarks":""},{"id":"13","score":"9","remarks":""},{"id":"14","score":"9","remarks":""},{"id":"15","score":"9","remarks":""},{"id":"16","score":"9","remarks":""},{"id":"17","score":"9","remarks":""},{"id":"18","score":"9","remarks":""},{"id":"19","score":"9","remarks":""}],
	 * "totalScore":"90","evaluationLevelevaluationLevel":"A","experimentalProcess":"","ALRP":"",
	 * "discussingOrImproving":""}*/
	
	public static void main(String[] args) throws Exception {
		ClassLoader loader=Test1.class.getClassLoader();
		System.out.println(loader.getResource("").getPath());
	}
}
