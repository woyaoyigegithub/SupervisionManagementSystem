<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加教职工信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addStaff.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加教职工信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">教职工号:</td><td><input type="number" name="id" placeholder="0000077" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">教职工姓名：</td><td><input type="text" name="name" placeholder="胡胜高"></td><td class="errors"></td></tr>
		<tr><td>所属学院：</td><td><select name="department"><option value=""></option>
			<c:forEach items="${departmentList}" var="department"><option value="${department}">${department}</option></c:forEach></select></td>
			<td class="errors"></td></tr>
		<tr><td class="essential">密码：</td><td><input type="text" name="password" placeholder="123456"></td><td class="errors"></td></tr>
		<tr><td class="essential">权限：</td><td><select name="powerId"><option value=""></option>
			<c:forEach items="${powerList}" var="power"><option value="${power.id}">${power.name}</option></c:forEach></select></td>
			<td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>