<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询班级信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryClass.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询班级信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>班级编号:<input type="number" name="id" min="0" maxlength="10"></li>
			<li>班级名称:<input type="text" name="name"></li>
			<li>专业：<input type="text" name="major"></li>
			<li>年级：<input type="text" name="grade"></li>
			<li>所在院系：<select name="department"><option value=""></option>
				<c:forEach items="${departmentList}" var="department"><option>${department}</option></c:forEach>
				</select></li>
			<li>本科/专科：<select name="undergOrJun"><option value=""></option><option value="本科">本科</option>
				<option value="专科">专科</option></select></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(clazzList)==0}"><h3>无班级记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>班级编号</td><td>班级名称</td><td>专业</td><td>年级</td>
					<td>所在院系</td><td>本科/专科</td><td>操作</td>
				</tr>
				<c:forEach items="${clazzList}" var="clazz">
					<tr><td><input type="checkbox"></td><td>${clazz.id}</td><td>${clazz.name}</td><td>${clazz.major}</td>
					<td>${clazz.grade}</td><td>${clazz.department}</td><td>${clazz.undergOrJun}</td>
					<td><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryClass?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新班级信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td class="essential">班级编号:</td><td><input type="number" name="id" min="0" maxlength="10"></td><td class="errors"></td></tr>
		<tr><td class="essential">班级名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td class="essential">专业：</td><td><input type="text" name="major"></td><td class="errors"></td></tr>
		<tr><td class="essential">年级：</td><td><input type="text" name="grade"></td><td class="errors"></td></tr>
		<tr><td class="essential">所在院系：</td><td><select name="department"><option value=""></option>
			<c:forEach items="${departmentList}" var="department"><option>${department}</option></c:forEach>
			</select><td class="errors"></td></tr>
		<tr>
			<td class="essential">本科/专科：</td>
			<td>
				<select name="undergOrJun">
					<option value=""></option><option value="本科">本科</option><option value="专科">专科</option>
				</select>
			</td><td class="errors"></td>
		</tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>


</body>
</html>