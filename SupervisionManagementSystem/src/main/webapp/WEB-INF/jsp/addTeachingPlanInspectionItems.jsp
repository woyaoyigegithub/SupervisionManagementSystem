<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加教案检查指标项信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/teachingPlanInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addTeachingPlanInspectionItems.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加教案检查指标项信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td colspan="2" class="essential">教案检查指标项内容：</td><td><textarea name="content" placeholder="课程性质和目的" rows="3" cols="30"></textarea></td><td class="errors"></td></tr>
		<tr><td rowspan="2" class="essential">选项</td><td>选项1：</td><td><input type="text" name="options" placeholder="明确具体"></td><td class="errors"></td></tr>
		<tr><td>选项2：</td><td><input type="text" name="options" placeholder="简略"></td><td class="errors"></td></tr>
		<tr><td colspan="3"><input type="button" id="appendRowOptionsBtn" value="追加一行选项">
		<input type="button" id="deleteLastRowOptionsBtn" value="删除最后一行选项"></td></tr>
		<tr><td colspan="3"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>