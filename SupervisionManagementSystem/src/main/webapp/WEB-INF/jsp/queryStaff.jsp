<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询教职工信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryStaff.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询教职工信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>教职工号：<input type="number" name="id"></li>
			<li>教职工姓名：<input type="text" name="name"></li>
			<li>所属学院：<select name="department"><option value=""></option>
				<c:forEach items="${departmentList}" var="department"><option value="${department}">${department}</option></c:forEach></select></li>
			<li>权限：<select name="powerId"><option value=""></option>
				<c:forEach items="${powerList}" var="power"><option value="${power.id}">${power.name}</option></c:forEach></select></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(staffList)==0 }"><h3>无教职工记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>教职工号</td><td>教职工姓名</td><td>所属学院</td><td>密码</td><td>权限</td><td>操作</td>
				</tr>
				<c:forEach items="${staffList}" var="staff">
					<tr><td><input type="checkbox"></td><td>${staff.id}</td><td>${staff.name}</td>
					<td>${staff.department}</td><td>${staff.password}</td><td>${staff.power.name}</td>
					<td><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryStaff?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新教职工信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td class="essential">教职工号：</td><td><input type="number" name="id" min="0" maxlength="10"></td><td class="errors"></td></tr>
		<tr><td class="essential">教职工姓名：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td>所属学院：</td><td><select name="department"><option value=""></option>
			<c:forEach items="${departmentList}" var="department"><option value="${department}">${department}</option></c:forEach></select></td>
			<td class="errors"></td></tr>
		<tr><td class="essential">密码：</td><td><input type="text" name="password"></td><td class="errors"></td></tr>
		<tr><td class="essential">权限：</td><td><select name="powerId"><option value=""></option>
			<c:forEach items="${powerList}" var="power"><option value="${power.id}">${power.name}</option></c:forEach></select></td>
			<td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>

</body>
</html>