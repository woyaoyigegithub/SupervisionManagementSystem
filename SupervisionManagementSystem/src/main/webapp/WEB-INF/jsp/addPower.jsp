<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加权限信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addPower.js"></script>

</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加权限信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">权限名称：</td><td><input type="text" name="name" placeholder="教师"></td><td class="errors"></td></tr>
		<tr><td>描述：</td><td><input type="text" name="description" placeholder="简单描述"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage">${errorMessage}</p>
	
</div>

</body>
</html>