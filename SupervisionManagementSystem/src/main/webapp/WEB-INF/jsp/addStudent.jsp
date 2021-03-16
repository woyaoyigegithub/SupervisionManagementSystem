<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加学生信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addStudent.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加学生信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">学号：</td><td><input type="number" name="id" placeholder="201706084101" min="0" maxlength="12"></td><td class="errors"></td></tr>
		<tr><td class="essential">姓名：</td><td><input type="text" name="name" placeholder="张三"></td><td class="errors"></td></tr>
		<tr><td class="essential">性别：</td><td><select name="sex"><option value=""></option><option value="男">男</option><option value="女">女</option></select></td><td class="errors"></td></tr>
		<tr><td class="essential">所在班级：</td><td><input type="text" name="classId" placeholder="2017060841"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>


</body>
</html>