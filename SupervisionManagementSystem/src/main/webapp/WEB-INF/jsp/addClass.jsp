<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加班级信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addClass.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加班级信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">班级编号:</td><td><input type="number" name="id" placeholder="2017060841" min="0" maxlength="10"></td><td class="errors"></td></tr>
		<tr><td class="essential">班级名称：</td><td><input type="text" name="name" placeholder="17级软件工程1班"></td><td class="errors"></td></tr>
		<tr><td class="essential">专业：</td><td><input type="text" name="major" placeholder="2017级软件工程"></td><td class="errors"></td></tr>
		<tr><td class="essential">年级：</td><td><input type="text" name="grade" placeholder="2017"></td><td class="errors"></td></tr>
		<tr><td class="essential">所在院系：</td><td><select name="department"><option value=""></option>
			<c:forEach items="${departmentList}" var="department"><option>${department}</option></c:forEach>
			</select><td class="errors"></td></tr>
		<tr>
			<td class="essential">本科/专科：</td>
			<td>
				<select name="undergOrJun">
					<option value=""></option><option value="本科">本科</option><option value="专科">专科</option>
				</select>
			</td>
			<td class="errors"></td>
		</tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<c:if test="${!empty errorMessage}"><p class="errorMessage">错误消息：${errorMessage}</p></c:if>
	
</div>

</body>
</html>