<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加抽查项信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/papersInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addPapersInspectionItems.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加抽查项信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td colspan="2" class="essential">巡查项内容：</td><td><textarea name="content" rows="3" cols="50"></textarea></td><td class="errors"></td></tr>
		<tr><td rowspan="2" class="essential">抽查情况选项</td><td>选项1：</td><td><input type="text" name="options" ></td><td class="errors"></td></tr>
		<tr><td>选项2：</td><td><input type="text" name="options" ></td><td class="errors"></td></tr>
		<tr><td colspan="3"><input type="button" id="appendRowOptionsBtn" value="追加一行选项">
		<input type="button" id="deleteLastRowOptionsBtn" value="删除最后一行选项"></td></tr>
		<tr><td colspan="3"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>