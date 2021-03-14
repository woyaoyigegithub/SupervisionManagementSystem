<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加教案检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/teachingPlanInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addTeachingPlanInspection.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加教案检查信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr>
			<td colspan="4" class="essential">选课编号：<input type="text" name="courseSelectionId" min="0" value="${courseSelectionId}"></td>
			
		</tr>
		<tr>
			<td>教案检查指标项编号</td><td>教案检查指标项内容</td><td>选项</td><td class="essential">检查情况</td>
		</tr>
		
		<!-- 填充教案检查指标项列表处start -->
		<c:forEach items="${teachingPlanInspectionItemsList}" var="teachingPlanInspectionItems">
			<tr><td>${teachingPlanInspectionItems.id}</td><td>${teachingPlanInspectionItems.content}</td><td>${teachingPlanInspectionItems.options}</td>
			<td><select name="situation"><option value=""></option>
				<c:forEach items="${fn:split(fn:replace(teachingPlanInspectionItems.options,'▢',''),' ')}" var="options"><option>${options}</option></c:forEach></select></td></tr>
		</c:forEach>
		<!-- 填充教案检查指标项列表处end -->
		
		<tr><td colspan="2" class="essential">值得商榷和肯定的地方：</td><td colspan="2"><textarea name="discussingAndAffirming" rows="3" cols="50"></textarea></td></tr>
		<tr><td colspan="4"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>