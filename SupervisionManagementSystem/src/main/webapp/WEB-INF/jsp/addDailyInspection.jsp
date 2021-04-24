<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加日常巡查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/dailyInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addDailyInspection.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加日常巡查信息</h2>
	
	<!-- 无督查区域安排 -->
	<c:if test="${staff.power.name=='督导' && fn:length(inspectionAreaArrangementSituationIdList)==0}">
		<h3>今天没有日常巡查任务！</h3>
	</c:if>
	
	<!-- 有至少一条督查区域安排 -->
	<c:if test="${!(staff.power.name=='督导' && fn:length(inspectionAreaArrangementSituationIdList)==0)}">
		<!-- 数据表格 -->
		<table class="dataTab">
			<tr>
				<td class="essential">督查区域人员安排编号：</td><td><select name="inspectionAreaArrangementSituationId"><option value=""></option>
					<c:forEach items="${inspectionAreaArrangementSituationIdList}" var="inspectionAreaArrangementSituationId">
					<c:choose><c:when test="${inspectionAreaArrangementSituationId==selectedInspectionAreaArrangementSituationId}">
						<option selected>${inspectionAreaArrangementSituationId}</option></c:when>
						<c:otherwise><option>${inspectionAreaArrangementSituationId}</option></c:otherwise></c:choose></c:forEach></select></td>
				<td class="essential">周次：</td><td><input type="number" name="weekly" min="0"></td>
				<td class="essential">星期：</td><td><select name="week"><option value=""></option>
				<option value="一">一</option><option value="二">二</option><option value="三">三</option>
				<option value="四">四</option><option value="五">五</option><option value="六">六</option><option value="日">日</option>
				</select></td>
				<td class="essential">巡查日期：</td><td><input type="date" name="date"></td><td class="errors"></td>
			</tr>
			
			<tr>
				<td>序号</td><td colspan="2">巡查项目</td><td colspan="3" class="essential">日常巡查情况记录</td><td colspan="2" class="essential">建议</td>
			</tr>
			<c:forEach items="${dailyInspectionItemsList}" var="dailyInspectionItems">
				<tr>
					<td>${dailyInspectionItems.id}</td><td colspan="2">${dailyInspectionItems.content}</td><td colspan="3"><textarea name="situation" placeholder="请输入日常巡查情况记录" rows="3" cols="40"></textarea></td>
					<td colspan="2"><textarea name="suggest" placeholder="给点建议" rows="3" cols="35"></textarea></td>
				</tr>
			</c:forEach>
			
			<tr><td colspan="8">教师提前下课情况记录表</td></tr>
			
			<tr><td colspan="2">选课编号</td><td colspan="2">上课班级</td><td colspan="2">正常下课时间</td><td colspan="2">实际下课时间</td></tr>
			<tr><td colspan="2"><input type="text" name="courseSelectionId"></td>
				<td colspan="2"><input type="number" name="classId"></td><td colspan="2"><input type="time" name="normalTime"></td>
				<td colspan="2"><input type="time" name="actualTime"></td></tr>
			<tr><td colspan="8"><input type="button" id="appendRowBtn" value="追加一行"><input type="button" id="deleteLastRowBtn" value="删除最后一行"></td></tr>
			<tr><td colspan="8"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
		</table>
		
		<!-- 如果有错误消息输出错误提示 -->
		<p class="errorMessage"></p>
	</c:if>
	
</div>
	
</body>
</html>