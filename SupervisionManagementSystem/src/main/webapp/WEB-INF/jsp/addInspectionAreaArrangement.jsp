<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加巡查区域人员安排信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/inspectionAreaArrangement.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addInspectionAreaArrangement.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加督查区域安排信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr>
			<td class="essential">学年：</td><td><select name="schoolYear"><option value=""></option>
				<c:forEach items="${schoolYearList}" var="schoolYear"><option>${schoolYear}</option></c:forEach>
				</select></td>
			<td class="essential">学期：</td><td><select name="semester"><option value=""></option>
			<option value="1">1</option><option value="2">2</option></select></td>
			<td class="essential">起止周：</td><td><input type="text" name="startingAndEndingWeeks" placeholder="2-16"></td>
			<td>公布日期：</td><td><input type="date" name="publicationTime"></td>
		</tr>
		<tr>
			<td colspan="2" class="essential">教学楼</td><td colspan="2" class="essential">督导人数</td><td colspan="2" class="essential">督导工号</td><td>星期</td><td>早中晚</td>
		</tr>
		
		
		<c:forEach begin="0" end="3" varStatus="vs">
			<c:forEach begin="0" end="14" varStatus="vs0">
			
				<c:if test="${vs0.index==0}">
					<tr>
						<td rowspan="16" colspan="2"><textarea name="academicBuilding" placeholder="明辨楼1、2、钢琴教室、一附、二附" cols="35" rows="10"></textarea></td>
						<td rowspan="16" colspan="2"><input type="number" name="inspectorsNum" placeholder="1" min="0"></td>
					</tr>
				</c:if>
				<c:if test="${vs0.index!=0}"><tr></c:if>
				<td colspan="2"><input type="text" name="supervisorId" placeholder="0000077"></td>
				<td><select name="week"><option value=""></option>
					<option value="周一">周一</option><option value="周二">周二</option><option value="周三">周三</option><option value="周四">周四</option>
					<option value="周五">周五</option><option value="周六">周六</option><option value="周日">周日</option></select></td>
				<td><select name="earlyMiddleLate"><option value=""></option>
					<option value="上午">上午</option><option value="下午">下午</option><option value="晚上">晚上</option></select></td>
				<c:if test="${vs0.index!=0}"></tr></c:if>
				
			</c:forEach>
		</c:forEach>
		
		<tr><td colspan="8"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>