<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加课程信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addCourse.js"></script>

</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加课程信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">课程编号：</td><td><input type="number" name="id" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">课程名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td>课程性质：</td><td><select name="nature"><option value=""></option><option value="必修课">必修课</option><option value="公选课">公选课</option>
			<option value="任选课">任选课</option><option value="实践课">实践课</option><option value="限选课">限选课</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">课程类型：</td><td><select name="type"><option value=""></option><option value="理论">理论</option><option value="实验">实验</option>
			<option value="美术">美术</option><option value="体育">体育</option></select></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
</div>
	
</body>
</html>