<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询教案检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/teachingPlanInspection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryTeachingPlanInspection.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询教案检查信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>教案检查编号：<input type="number" name="id" min="0"></li>
			<li>选课编号编号：<input type="text" name="courseSelectionId" min="0"></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	<c:choose>
		<c:when test="${fn:length(teachingPlanInspectionList)==0}"><h3>无教案检查记录！</h3></c:when>
		
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>教案检查编号</td><td>选课编号编号</td><td>课程名称</td><td>开课学院</td><td>教师工号</td>
					<td>教师名称</td><td>教师所在部门</td><td>授课专业年级班级</td><td>教案检查指标项编号</td><td>教案检查指标项内容</td><td>检查情况选项</td><td>选项</td>
					<td>值得商榷和肯定的地方</td><td>操作</td>
				</tr>
				<c:forEach items="${teachingPlanInspectionList}" var="teachingPlanInspection" varStatus="vs">
					<c:set var="len" value="${fn:length(teachingPlanInspection.teachingPlanInspectionSituationList)}"/>
				
					<tr><td rowspan="${len}"><input type="checkbox"></td><td rowspan="${len}">${teachingPlanInspection.id}</td><td rowspan="${len}">${teachingPlanInspection.courseSelection.id}</td>
					<td rowspan="${len}">${teachingPlanInspection.courseSelection.course.name}</td><td rowspan="${len}">${teachingPlanInspection.courseSelection.clazz.department}</td>
					<td rowspan="${len}">${teachingPlanInspection.courseSelection.teacher.id}</td><td rowspan="${len}">${teachingPlanInspection.courseSelection.teacher.name}</td>
					<td rowspan="${len}">${teachingPlanInspection.courseSelection.teacher.department}</td><td rowspan="${len}">${teachingPlanInspection.courseSelection.clazz.name}</td>
					
					<c:forEach items="${teachingPlanInspection.teachingPlanInspectionSituationList}" var="teachingPlanInspectionSituation" varStatus="vs0">
						<c:if test="${vs0.index!=0}"><tr></c:if>
						<td>${teachingPlanInspectionSituation.teachingPlanInspectionItems.id}</td><td>${teachingPlanInspectionSituation.teachingPlanInspectionItems.content}</td>
						<td>${teachingPlanInspectionSituation.teachingPlanInspectionItems.options}</td><td>${teachingPlanInspectionSituation.situation}</td>
						<c:if test="${vs0.index!=0}"></tr></c:if>
						
						<c:if test="${vs0.index==0}">
							<td rowspan="${len}">${teachingPlanInspection.discussingAndAffirming}</td>
							<td rowspan="${len}"><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
						</c:if>
					</c:forEach>
					
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryTeachingPlanInspection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>


<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新教案检查信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td colspan="2">教案检查编号：<input type="number" name="id" min="0"></td>
			<td colspan="2" class="essential">选课编号：<input type="text" name="courseSelectionId" min="0"></td>
		</tr>
			
		<tr>
			<td>教案检查指标项编号</td><td>教案检查指标项内容</td><td>选项</td><td class="essential">检查情况</td>
		</tr>
		<!-- 填充教案检查指标项列表处start -->
		<!-- 填充教案检查指标项列表处end -->
		<tr><td colspan="2" class="essential">值得商榷和肯定的地方：</td><td colspan="2"><textarea name="discussingAndAffirming" rows="3" cols="50"></textarea></td></tr>
		<tr><td colspan="4"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>

</body>
</html>