<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询教案检查项信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/teachingPlanInspection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryTeachingPlanInspectionItems.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询教案检查项信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>教案检查指标项编号:<input type="number" name="id" min="0"></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(teachingPlanInspectionItemsList)==0 }"><h3>无教案检查项记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>教案检查指标项编号</td><td>教案检查指标项内容</td><td>选项</td><td>操作</td>
				</tr>
				<c:forEach items="${teachingPlanInspectionItemsList}" var="teachingPlanInspectionItems">
					<tr><td><input type="checkbox"></td><td>${teachingPlanInspectionItems.id}</td><td>${teachingPlanInspectionItems.content}</td>
					<td>${teachingPlanInspectionItems.options}</td><td><img class="updateImage" src="images/update.gif">
					<img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryTeachingPlanInspectionItems?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新教案检查项信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td colspan="2">教案检查指标项编号：</td><td><input type="number" name="id" min="0"/></td><td class="errors"></td></tr>
		<tr><td colspan="2" class="essential">教案检查指标项内容：</td><td><textarea name="content" rows="3" cols="30"></textarea></td><td class="errors"></td></tr>
		<tr><td rowspan="2" class="essential">选项</td><td>选项1：</td><td><input type="text" name="options"></td><td class="errors"></td></tr>
		<tr><td>选项2：</td><td><input type="text" name="options"></td><td class="errors"></td></tr>
		<tr><td colspan="3"><input type="button" id="appendRowOptionsBtn" value="追加一行选项">
		<input type="button" id="deleteLastRowOptionsBtn" value="删除最后一行选项"></td></tr>
		<tr><td colspan="3"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>

</body>
</html>