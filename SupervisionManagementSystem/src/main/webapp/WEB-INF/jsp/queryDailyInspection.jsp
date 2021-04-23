<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询日常巡查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/dailyInspection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryDailyInspection.js"></script>
</head>
<body>

<c:set var="dailyInspection" value="${fn:length(dailyInspectionList)==0?null:dailyInspectionList.get(0)}"/>
<c:set var="dailyInspectionSituationListLen" value="${fn:length(dailyInspection.dailyInspectionSituationList)}"/>
<c:set var="len0" value="${2+dailyInspectionSituationListLen}"/>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询日常巡查信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>日常巡查编号：<input type="number" name="id" min="0"></li>
			<li>督查区域人员安排编号：<input type="number" name="inspectionAreaArrangementSituationId" min="0"></li>
			<li>周次：<input type="number" name="weekly" min="0"></li>
			<li>星期：<select name="week"><option value=""></option>
				<option value="一">一</option><option value="二">二</option><option value="三">三</option>
				<option value="四">四</option><option value="五">五</option><option value="六">六</option>
				<option value="日">日</option></select></li>
			<li>巡查日期：<input type="date" name="date"></li>
			<li><input type="button" id="searchBtn" value="搜索">
			<c:if test="${staff.power.name=='管理员'}"><input type="button" id="batchDeleteBtn" value="批量删除"></c:if></li>
		</ul>
	</div>
		
	<c:choose>
		<c:when test="${fn:length(dailyInspectionList)==0 }"><h3>无日常巡查记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>日常巡查编号</td><td>周次</td><td>督查区域人员安排编号</td><td>督导工号</td>
						<td>督导姓名</td><td>星期</td><td>早中晚</td><td>巡查日期</td><td>序号</td><td colspan="4">巡查项目</td><td colspan="4">日常巡查情况记录</td><td colspan="3">建议</td>
						<c:if test="${staff.power.name=='管理员'}"><td>操作</td></c:if>
				</tr>
				<c:forEach items="${dailyInspectionList}" var="dailyInspection" varStatus="vs">
				
					<!-- 提前下课情况数据行数 -->
					<c:set var="leave_len" value="${fn:length(dailyInspection.leaveClassAheadOfTimeSituationList)}"/>
					<!-- 表格总行数 -->
					<c:set var="len" value="${len0+leave_len}"/>
					
					<c:forEach items="${dailyInspection.dailyInspectionSituationList}" var="dailyInspectionSituation" varStatus="vs0">
						<c:if test="${vs0.index==0}">
							<tr>
								<td rowspan="${len}"><input type="checkbox"></td><td rowspan="${len}">${dailyInspection.id}</td><td rowspan="${len}">${dailyInspection.weekly}</td><td rowspan="${len}">${dailyInspection.inspectionAreaArrangementSituation.id}</td>
								<td rowspan="${len}">${dailyInspection.inspectionAreaArrangementSituation.supervisor.id}</td><td rowspan="${len}">${dailyInspection.inspectionAreaArrangementSituation.supervisor.name}</td>
								<td rowspan="${len}">${dailyInspection.week}</td><td rowspan="${len}">${dailyInspection.inspectionAreaArrangementSituation.earlyMiddleLate}</td><td rowspan="${len}">${dailyInspection.date}</td>
								<td>${dailyInspectionSituation.dailyInspectionItems.id}</td><td colspan="4">${dailyInspectionSituation.dailyInspectionItems.content}</td>
								<td colspan="4">${dailyInspectionSituation.situation}</td><td colspan="3">${dailyInspectionSituation.suggest}</td>
								<c:if test="${staff.power.name=='管理员'}">
									<td rowspan="${len}"><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></c:if>
							</tr>
						</c:if>
						
						<c:if test="${vs0.index!=0}">
							<tr>
								<td>${dailyInspectionSituation.dailyInspectionItems.id}</td><td colspan="4">${dailyInspectionSituation.dailyInspectionItems.content}</td>
								<td colspan="4">${dailyInspectionSituation.situation}</td><td colspan="3">${dailyInspectionSituation.suggest}</td>
							</tr>
						</c:if>
						
						<c:if test="${vs0.index==dailyInspectionSituationListLen-1}">
							<tr><td colspan="12">教师提前下课情况记录表</td></tr>
							<tr><td>选课编号</td><td>课程编号</td><td>课程名称</td><td>任课教师编号</td><td>任课教师姓名</td><td>上课班级编号</td><td>上课班级名称</td>
								<td>上课教室编号</td><td>上课教室名称</td><td>正常下课时间</td><td>实际下课时间</td></tr>
							<c:forEach items="${dailyInspection.leaveClassAheadOfTimeSituationList}" var="leaveClassAheadOfTimeSituation" varStatus="vs">
								<tr>
									<td>${leaveClassAheadOfTimeSituation.courseSelection.id}</td><td>${leaveClassAheadOfTimeSituation.courseSelection.course.id}</td>
									<td>${leaveClassAheadOfTimeSituation.courseSelection.course.name}</td><td>${leaveClassAheadOfTimeSituation.courseSelection.teacher.id}</td>
									<td>${leaveClassAheadOfTimeSituation.courseSelection.teacher.name}</td><td>${leaveClassAheadOfTimeSituation.clazz.id}</td>
									<td>${leaveClassAheadOfTimeSituation.clazz.name}</td><td>${leaveClassAheadOfTimeSituation.courseSelection.classroom.id}</td>
									<td>${leaveClassAheadOfTimeSituation.courseSelection.classroom.name}</td><td>${leaveClassAheadOfTimeSituation.normalTime}</td>
									<td>${leaveClassAheadOfTimeSituation.actualTime}</td>
								</tr>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryDailyInspection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>
	
	
	
<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新日常巡查信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td class="essential">日常巡查编号：</td><td><input type="number" name="id"></td>
			<td class="essential">督查区域人员安排编号：</td><td><input name="inspectionAreaArrangementSituationId"/></td>
			<td class="essential">周次：</td><td><input type="number" name="weekly" min="0"></td>
			<td class="essential">星期：</td><td><select name="week"><option value=""></option>
				<option value="一">一</option><option value="一">一</option><option value="二">二</option><option value="三">三</option>
				<option value="四">四</option><option value="五">五</option><option value="六">六</option><option value="日">日</option>
				</select></td>
			<td>巡查日期：</td><td><input type="date" name="date"></td><td class="errors"></td>
		</tr>
		
		<tr>
			<td>序号</td><td colspan="4">巡查项目</td><td colspan="3" class="essential">日常巡查情况记录</td><td colspan="2" class="essential">建议</td>
		</tr>
		<!-- 填充日常巡查情况记录处 -->
		
		<tr><td colspan="10">教师提前下课情况记录表</td></tr>
		<tr><td colspan="4">选课编号</td><td colspan="2">上课班级</td><td colspan="2">正常下课时间</td><td colspan="2">实际下课时间</td></tr>
		<!-- 填充教师提前下课情况记录处 -->
		<tr><td colspan="10"><input type="button" id="appendRowBtn" value="追加一行"><input type="button" id="deleteLastRowBtn" value="删除最后一行"></td></tr>
		<tr><td colspan="10"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>

</body>
</html>