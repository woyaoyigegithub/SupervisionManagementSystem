<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询巡考信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/tourInspection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryTourInspection.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询巡考信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>巡考编号：<input type="number" name="id" min="0"></li>
			<li>巡考日期：<input type="date" name="date"></li>
			<li>起时：<input type="number" name="startingTime" min="0"></li>
			<li>终时：<input type="number" name="endingTime" min="0"></li>
			<li>巡考区域：<input type="text" name="tourInspectionArea"></li>
			<c:if test="${staff.power.name=='管理员'}"><li>督导工号：<input type="number" name="supervisorId" min="0"></li></c:if>
			<li><input type="button" id="searchBtn" value="搜索">
			<c:if test="${staff.power.name=='管理员'}"><input type="button" id="batchDeleteBtn" value="批量删除"></c:if></li>
		</ul>
	</div>
		
	<c:choose>
		<c:when test="${fn:length(tourInspectionList)==0 }"><h3>无巡考记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>巡考编号</td><td>巡考日期</td><td>起时</td><td>终时</td><td>巡考区域</td>
						<td>督导工号</td><td>督导姓名</td><td>巡考情况编号</td><td>巡考情况内容</td><td>具体情况记录</td>
						<c:if test="${staff.power.name=='管理员'}"><td>操作</td></c:if>
				</tr>
				<c:forEach items="${tourInspectionList}" var="tourInspection" varStatus="vs">
					
					<!-- 巡考情况列表 -->
					<c:set var="tourInspectionSituationList" value="${tourInspection.tourInspectionSituationList}" />
					<!-- 巡考情况列表长度 -->
					<c:set var="len" value="${fn:length(tourInspectionSituationList)}" />
					
					<c:forEach items="${tourInspectionSituationList}" var="tourInspectionSituation" varStatus="vs0">
					
						<c:if test="${vs0.index==0}">
							<tr>
								<td rowspan="${len}"><input type="checkbox"></td><td rowspan="${len} colspan="${len}">${tourInspection.id}</td><td rowspan="${len}">${tourInspection.date}</td>
								<td rowspan="${len}">${tourInspection.startingTime}</td><td rowspan="${len}">${tourInspection.endingTime}</td><td rowspan="${len}">${tourInspection.tourInspectionArea}</td>
								<td rowspan="${len}">${tourInspection.supervisor.id}</td><td rowspan="${len}">${tourInspection.supervisor.name}</td>
						</c:if>
						
						<c:if test="${vs0.index!=0}"><tr></c:if>
						<td>${tourInspectionSituation.tourInspectionItems.id}</td><td>${tourInspectionSituation.tourInspectionItems.content}</td><td>${tourInspectionSituation.situation}</td>
						<c:if test="${vs0.index!=0}"></tr></c:if>
						
						<c:if test="${vs0.index==0}">
							<c:if test="${staff.power.name=='管理员'}"><td rowspan="${len}"><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></c:if></tr>
						</c:if>
					
					</c:forEach>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryTourInspection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>
	
	
	
<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新巡考信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td>巡考编号</td><td>巡考日期</td><td class="essential">起时</td><td class="essential">终时</td><td class="essential">巡视区域</td><td>督导工号</td>
		</tr>
		<tr>
			<td><input type="number" name="id"></td>
			<td><input type="date" name="date"></td>
			<td><input type="number" name="startingTime" min="0"></td>
			<td><input type="number" name="endingTime" min="0"></td>
			<td><input type="text" name="tourInspectionArea"></input></td>
			<td><input type="number" name="supervisorId" min="0"></td>
		</tr>
		
		<tr>
			<td>巡考项编号</td><td colspan="3">巡考项内容</td><td colspan="2" class="essential">具体情况记录</td>
		</tr>
		<!-- 填充巡考情况记录处 start -->
		<c:forEach begin="0" end="9">
			
		</c:forEach>
		<!-- 填充巡考情况记录处 end -->
				
		<tr><td colspan="6"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>

</body>
</html>