<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加作业检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/homeworkInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addHomeworkInspection.js"></script>
</head>

<body>
<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加作业检查信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">选课编号：</td><td><input type="text" name="courseSelectionId" min="0" value="${courseSelectionId}"></td><td class="errors"></td></tr>
		<tr><td class="essential">作业或报告份数：</td><td><input type="number" name="assignmentsOrReportsFenNum" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">作业或报告次数：</td><td><input type="number" name="assignmentsOrReportsCiNum" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改次数：</td><td><input type="number" name="correctionTimes" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改认真：</td><td><input type="number" name="seriousCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改较认真：</td><td><input type="number" name="moreSeriousCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改一般：</td><td><input type="number" name="generalCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改差：</td><td><input type="number" name="poorCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">备注：</td><td><textarea name="remarks" rows="3" cols="30"></textarea></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>