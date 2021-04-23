<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加毕业论文检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/papersInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addPapersInspection.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加毕业论文检查信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr>
			<td class="essential">学生学号</td><td class="essential">指导教师工号</td><td class="essential">查阅教师工号</td>
			<td colspan="2">备注</td>
		</tr>
		<tr>
			<td><input type="number" name="studentId" placeholder="201706084101" min="0"></td>
			<td><input type="number" name="instructorId" placeholder="1010100" min="0"></td>
			<td><input type="number" name="consultTeacherId" placeholder="0000325" min="0"></td>
			<td colspan="2"><textarea name="remarks" placeholder="写点备注" rows="3" cols="40"></textarea></td>
		</tr>
		
		<tr>
			<td>抽查项编号</td><td>抽查项内容</td><td>抽查情况记录</td><td class="essential">选项</td><td>备注</td>
		</tr>
		
		<c:forEach items="${papersInspectionItemList}" var="papersInspectionItems">
			<c:set var="optionArray" value="${fn:split(fn:replace(papersInspectionItems.options,'▢',''),' ') }"/>
			
			<tr><td>${papersInspectionItems.id}</td><td>${papersInspectionItems.content}</td>
			<td>${papersInspectionItems.options}</td><td><select name="situation"><option value=""></option>
			<c:forEach items="${optionArray}" var="option"><option>${option}</option></c:forEach>
			</select></td><td><textarea name="remarks" placeholder="写点备注" rows="3" cols="30"></textarea></td></tr>
		</c:forEach>
		
		<tr><td colspan="2" class="essential">查阅人对此毕业设计（论文）的综合意见：</td><td colspan="3"><textarea name="generalComments" placeholder="请输入综合意见" rows="3" cols="70"></textarea></td></tr>
		<tr><td colspan="5"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
	
</div>

</body>
</html>