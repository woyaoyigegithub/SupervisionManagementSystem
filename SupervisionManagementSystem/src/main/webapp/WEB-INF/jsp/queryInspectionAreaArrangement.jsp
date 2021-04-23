<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询督查区域人员安排信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/inspectionAreaArrangement.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryInspectionAreaArrangement.js"></script>
</head>
<body>
<jsp:useBean id="today" class="java.util.Date"/>
<fmt:formatDate value="${today}" pattern="E" var="today"/>
<c:set value="${fn:replace(today,'星期','周')}" var="today"/>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询督查区域人员安排信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>督查区域安排编号：<input type="number" name="id" min="0" maxlength="10"></li>
			<li>学年：<select name="schoolYear"><option value=""></option>
				<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach></select></li>
			<li>学期：<select name="semester"><option value=""></option><option value="1">1</option><option value="2">2</option></select></li>
			<li>起止周：<input type="text" name="startingAndEndingWeeks"></li>
			<li>公布日期：<input type="date" name="publicationTime"></li>
			<li><input type="button" id="searchBtn" value="搜索">
				<c:if test="${staff.power.name=='管理员'}"><input type="button" id="batchDeleteBtn" value="批量删除"></c:if></li>
		</ul>
	</div>
	
	<c:choose>
		<c:when test="${fn:length(inspectionAreaArrangementList)==0 }"><h3>无督查区域安排记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>督查区域安排编号</td><td>学年</td><td>学期</td><td>起止周</td><td>公布日期</td>
					<td>督查区域教学楼安排编号</td><td>教学楼</td><td>督导人数</td><td>督查区域人员安排编号</td><td>督导工号</td><td>督导姓名</td><td>星期</td><td>早中晚</td><td>添加记录</td>
					<c:if test="${staff.power.name=='管理员'}"><td>操作</td></c:if>
				</tr>
				
				<c:forEach items="${inspectionAreaArrangementList}" var="inspectionAreaArrangement" varStatus="vs">
					<c:set var="buildingListLen" value="${fn:length(inspectionAreaArrangement.inspectionAreaArrangementBuildingList)}"/>
					<c:set var="situationListLen" value="${fn:length(inspectionAreaArrangement.inspectionAreaArrangementBuildingList.get(0).inspectionAreaArrangementSituationList)}"/>
					
					<tr><td rowspan="${buildingListLen*situationListLen}"><input type="checkbox"></td>
					<td rowspan="${buildingListLen*situationListLen}">${inspectionAreaArrangement.id}</td>
					<td rowspan="${buildingListLen*situationListLen}">${inspectionAreaArrangement.schoolYear}</td>
					<td rowspan="${buildingListLen*situationListLen}">${inspectionAreaArrangement.semester}</td>
					<td rowspan="${buildingListLen*situationListLen}">${inspectionAreaArrangement.startingAndEndingWeeks}</td>
					<td rowspan="${buildingListLen*situationListLen}">${inspectionAreaArrangement.publicationTime}</td>
					
					<c:forEach items="${inspectionAreaArrangement.inspectionAreaArrangementBuildingList}" var="inspectionAreaArrangementBuilding" varStatus="vs0">
						<c:if test="${vs0.index!=0}"><tr></c:if>									
						<td rowspan="${situationListLen}">${inspectionAreaArrangementBuilding.id}</td>
						<td rowspan="${situationListLen}">${inspectionAreaArrangementBuilding.academicBuilding}</td>
						<td rowspan="${situationListLen}">${inspectionAreaArrangementBuilding.inspectorsNum}</td>
						<c:forEach items="${inspectionAreaArrangementBuilding.inspectionAreaArrangementSituationList}" var="inspectionAreaArrangementSituation" varStatus="vs1">
							<c:if test="${vs1.index!=0}"><tr></c:if>
							<td>${inspectionAreaArrangementSituation.id}</td><td>${inspectionAreaArrangementSituation.supervisor.id}</td>
							<td>${inspectionAreaArrangementSituation.supervisor.name}</td><td>${inspectionAreaArrangementSituation.week}</td>
							<td>${inspectionAreaArrangementSituation.earlyMiddleLate}</td>
							
							<td><c:if test="${(staff.power.name=='管理员' && inspectionAreaArrangementSituation.supervisor.id!=null && vs.last) || 
								(staff.power.name=='督导' && staff.id==inspectionAreaArrangementSituation.supervisor.id && today==inspectionAreaArrangementSituation.week && vs.last) }">
								<a href="addDailyInspection?inspectionAreaArrangementSituationId=${inspectionAreaArrangementSituation.id}">添加日常巡查</a></c:if></td>
							<c:if test="${vs0.index==0 && vs1.index==0}">
								<c:if test="${staff.power.name=='管理员'}">
								<td rowspan="${buildingListLen*situationListLen}"><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></c:if>
							</c:if></tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryInspectionAreaArrangement?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新督查区域安排信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td class="essential">督查区域编号：</td><td><input type="number" name="id" min="0"></td>
			<td class="essential">学年：</td><td><select name="schoolYear"><option value=""></option>
				<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach></select></td>
			<td class="essential">学期：</td><td><select name="semester"><option value=""></option>
			<option value="1">1</option><option value="2">2</option></select></td>
			<td class="essential">起止周：</td><td><input type="text" name="startingAndEndingWeeks"></td>
			<td>公布日期：</td><td><input type="date" name="publicationTime"></td>
		</tr>
		<tr>
			<td class="essential">督查区域教学楼编号</td><td colspan="2" class="essential">教学楼</td>
			<td class="essential">督导人数</td><td colspan="2">督查区域人员安排编号</td><td colspan="2" class="essential">督导工号</td><td>星期</td><td>早中晚</td>
		</tr>
		
		
		<c:forEach begin="0" end="3" varStatus="vs">
			<!-- 督查区域教学楼首行数据 -->
			<c:forEach begin="0" end="14" varStatus="vs0">
			
				<c:if test="${vs0.index==0}">
					<tr>
						<td rowspan="16"><input type="number" name="inspectionAreaArrangementBuildingId" min="0"></td>
						<td rowspan="16" colspan="2"><textarea name="academicBuilding" cols="35" rows="10"></textarea></td>
						<td rowspan="16"><input type="number" name="inspectorsNum" min="0"></td>
					</tr>
				</c:if>
				<c:if test="${vs0.index!=0}"><tr></c:if>
				<td colspan="2"><input type="text" name="inspectionAreaArrangementSituationId" min="0"></td>
				<td colspan="2"><input type="text" name="supervisorId"></td>
				<td><select name="week"><option value=""></option>
					<option value="周一">周一</option><option value="周二">周二</option><option value="周三">周三</option><option value="周四">周四</option>
					<option value="周五">周五</option><option value="周六">周六</option><option value="周日">周日</option></select></td>
				<td><select name="earlyMiddleLate"><option value=""></option>
					<option value="上午">上午</option><option value="下午">下午</option><option value="晚上">晚上</option></select></td>
				<c:if test="${vs0.index!=0}"></tr></c:if>
			
			</c:forEach>
		</c:forEach>
		<tr><td colspan="10"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>

</body>
</html>