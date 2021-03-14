<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询选课信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/courseSelection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryCourseSelection.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询选课信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>选课课号：<input type="text" name="id" min="0"></li>
			<li>课程编号：<input type="number" name="courseId" min="0"></li>
			<li>授课教师工号：<input type="number" name="teacherId" min="0"></li>
			<li>选课班级编号：<input type="number" name="classId" min="0"></li>
			<li>开课学院：<select name="department"><option value=""></option>
				<c:forEach items="${departmentList}" var="department"><option value="${department}">${department}</option>
				</c:forEach></select></li>
			<li>教室编号：<input type="number" name="classroomId" min="0"></li>
			<li>学年：<select name="schoolYear"><option value=""></option>
				<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach></select></li>
			<li>学期：<select name="semester"><option value=""></option>
				<option value="1">1</option><option value="2">2</option></select></li>
			<li>星期：<select name="week"><option value=""></option>
				<option value="一">一</option><option value="二">二</option><option value="三">三</option><option value="四">四</option>
				<option value="五">五</option><option value="六">六</option><option value="日">日</option></select></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	<c:choose>
		<c:when test="${fn:length(courseSelectionList)==0 }"><h3>无选课记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>选课课号</td><td>课程编号</td><td>课程名称</td><td>授课教师工号</td>
					<td>授课教师姓名</td><td>选课班级编号</td><td>选课班级</td><td>开课学院</td><td>教室编号</td><td>教室名称</td><td>学年</td>
					<td>学期</td><td>星期</td><td>节次</td><td>单双周</td><td>起止周</td><td>上课人数</td><td colspan="3">添加记录</td><td>操作</td>
				</tr>
				<c:forEach items="${courseSelectionList}" var="courseSelection">
					<tr><td><input type="checkbox"></td><td>${courseSelection.id}</td><td>${courseSelection.course.id}</td>
					<td>${courseSelection.course.name}</td><td>${courseSelection.teacher.id}</td><td>${courseSelection.teacher.name}</td>
					<td>${courseSelection.clazz.id}</td><td>${courseSelection.clazz.name}</td>
					<td>${courseSelection.department}</td><td>${courseSelection.classroom.id}</td>
					<td>${courseSelection.classroom.name}</td><td>${courseSelection.schoolYear}</td>
					<td>${courseSelection.semester}</td><td>${courseSelection.week}</td><td>${courseSelection.jieci}</td>
					<td>${courseSelection.biweekly}</td><td>${courseSelection.startingAndEndingWeeks}</td><td>${courseSelection.numOfClass}</td>
					<td><a href="addAttendedLectures?courseSelectionId=${courseSelection.id}">添加听课</a></td>
					<td><a href="addHomeworkInspection?courseSelectionId=${courseSelection.id}">添加作业检查</a></td>
					<td><a href="addTeachingPlanInspection?courseSelectionId=${courseSelection.id}">添加教案检查</a></td>
					<td><img class="updateImage" src="images/update.gif">
					<img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryCourseSelection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新选课信息</h2>
	
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
				<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach></select></td><td class="errors"></td></tr>
		<tr><td class="essential">学期：</td><td><select name="semester"><option value=""></option>
			<option value="1">1</option><option value="2">2</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">星期：</td><td><select name="week"><option value=""></option>
			<option value="一">一</option><option value="二">二</option><option value="三">三</option><option value="四">四</option>
			<option value="五">五</option><option value="六">六</option><option value="日">日</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">节次：</td><td><input type="text" name="jieci"></td><td class="errors"></td></tr>
		<tr><td class="essential">单双周：</td><td><select name="biweekly"><option value=""></option><option value="单">单</option>
			<option value="双">双</option><option value="单双">单双</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">起止周：</td><td><input type="text" name="startingAndEndingWeeks"></td><td class="errors"></td></tr>
		<tr><td>上课人数：</td><td><input type="number" name="numOfClass" min="0"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	
	<p class="errorMessage"></p>
	
</div>

</body>
</html>