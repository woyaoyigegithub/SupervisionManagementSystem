<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加巡考信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/tourInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addTourInspection.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加巡考信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr>
			<td class="essential">巡考日期</td><td class="essential">起时</td><td class="essential">终时</td><td class="essential">巡视区域</td>
			<td class="essential">督导工号</td>
		</tr>
		<tr>
			<td><input type="date" name="date"></td>
			<td><input type="number" name="startingTime" min="0"></td>
			<td><input type="number" name="endingTime" min="0"></td>
			<td><input type="text" name="tourInspectionArea"></input></td>
			<td><input type="number" name="supervisorId" min="0"></td>
		</tr>
		
		<tr>
			<td>巡考项编号</td><td colspan="2">巡考项内容</td><td colspan="2" class="essential">具体情况记录</td>
		</tr>
		<c:forEach items="${tourInspectionItemsList}" var="tourInspectionItems">
			<tr>
				<td>${tourInspectionItems.id}</td><td colspan="2">${tourInspectionItems.content}</td><td colspan="2"><textarea name="situation" rows="3" cols="70"></textarea></td>
			</tr>
		</c:forEach>
		
		<tr><td colspan="5"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>