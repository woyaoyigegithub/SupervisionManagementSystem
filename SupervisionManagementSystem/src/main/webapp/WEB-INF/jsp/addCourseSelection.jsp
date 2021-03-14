<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加选课信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/courseSelection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addCourseSelection.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加选课信息</h2>

	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">选课课号：</td><td><input type="text" name="id" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">课程编号：</td><td><input type="number" name="courseId" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">授课教师工号：</td><td><input type="number" name="teacherId" min="0"></td><td class="errors"></td></tr>
		<tr><td>选课班级编号：</td><td><input type="number" name="classId" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">开课学院：</td><td><select name="department"><option value=""></option>
			<c:forEach items="${departmentList}" var="department"><option value="${department}">${department}</option></c:forEach></select></td>
			<td class="errors"></td></tr>
		<tr><td class="essential">教室编号：</td><td><input type="number" name="classroomId" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">学年：</td><td><select name="schoolYear"><option value=""></option>
			<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach>
			</select></td><td class="errors"></td></tr>
		<tr><td class="essential">学期：</td><td><select name="semester"><option value=""></option>
			<option value="1">1</option><option value="2">2</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">星期：</td><td><select name="week"><option value=""></option>
			<option value="一">一</option><option value="二">二</option><option value="三">三</option><option value="四">四</option>
			<option value="五">五</option><option value="六">六</option><option value="日">日</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">节次：</td><td><input type="text" name="jieci"></td><td class="errors"></td></tr>
		<tr><td class="essential">单双周：</td><td><select name="biweekly"><option value="">==请选择单双周==</option><option value="单">单</option>
			<option value="双">双</option><option value="单双">单双</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">起止周：</td><td><input type="text" name="startingAndEndingWeeks"></td><td class="errors"></td></tr>
		<tr><td>上课人数：</td><td><input type="number" name="numOfClass" min="0"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
		
</div>

</body>
</html>