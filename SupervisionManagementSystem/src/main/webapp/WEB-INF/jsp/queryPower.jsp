<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询权限信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryPower.js"></script>

</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询权限信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>权限编号：<input type="number" name="id" min="0" maxlength="10"></li>
			<li>权限名称：<input type="text" name="name"></li>
			<li>描述：<input type="text" name="description"></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(powerList)==0 }"><h3>无权限记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>权限编号</td><td>权限名称</td><td>描述</td><td>操作</td>
				</tr>
				<c:forEach items="${powerList}" var="power">
					<tr><td><input type="checkbox"></td><td>${power.id}</td><td>${power.name}</td>
					<td>${power.description}</td><td><img class="updateImage" src="images/update.gif">
					<img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryPower?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新权限信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td class="essential">权限编号：</td><td><input type="number" name="id" min="0" maxlength="10"></td><td class="errors"></td></tr>
		<tr><td class="essential">权限名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td>描述：</td><td><input type="text" name="description"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>

</body>
</html>