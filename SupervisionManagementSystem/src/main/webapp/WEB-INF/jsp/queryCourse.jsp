<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询课程信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryCourse.js"></script>

</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询课程信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>课程编号：<input type="number" name="id" min="0" maxlength="10"></li>
			<li>课程名称：<input type="text" name="name"></li>
			<li>课程性质：<select name=nature><option value=""></option><option value="必修课">必修课</option><option value="公选课">公选课</option>
			<option value="任选课">任选课</option><option value="实践课">实践课</option><option value="限选课">限选课</option></select></li>
			<li>课程类型：<select name="type"><option value=""></option><option value="理论">理论</option><option value="实验">实验</option>
			<option value="美术">美术</option><option value="体育">体育</option></select></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(courseList)==0 }"><h3>无课程记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>课程编号</td><td>课程名称</td><td>课程性质</td><td>课程类型</td><td>操作</td>
				</tr>
				<c:forEach items="${courseList}" var="course">
					<tr><td><input type="checkbox"></td><td>${course.id}</td><td>${course.name}</td>
					<td>${course.nature}</td><td>${course.type}</td>
					<td><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryCourse?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新课程信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td class="essential">课程编号：</td><td><input type="number" name="id" min="0" maxlength="10"></td><td class="errors"></td></tr>
		<tr><td class="essential">课程名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td>课程性质：</td><td><select name=nature><option value=""></option><option value="必修课">必修课</option><option value="公选课">公选课</option>
			<option value="任选课">任选课</option><option value="实践课">实践课</option><option value="限选课">限选课</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">课程类型：</td><td><select name="type"><option value=""></option><option value="理论">理论</option><option value="实验">实验</option>
			<option value="美术">美术</option><option value="体育">体育</option></select></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>

</body>
</html>