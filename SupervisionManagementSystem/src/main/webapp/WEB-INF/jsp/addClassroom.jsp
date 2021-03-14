<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加教室信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addClassroom.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加教室信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr><td class="essential">教室编号：</td><td><input type="number" name="id" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">教室名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td class="essential">座位数：</td><td><input type="number" name="seatingCapacity" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">教室类别：</td><td><select name="type"><option value=""></option><option value="三笔字专用">三笔字专用</option><option value="体育场地">体育场地</option>
			<option value="体育多媒体">体育多媒体</option><option value="国画室">国画室</option><option value="外语专用">外语专用</option><option value="多媒体">多媒体</option>
			<option value="实验室">实验室</option><option value="微格">微格</option><option value="普通话">普通话</option><option value="服装室">服装室</option>
			<option value="机房">机房</option><option value="机械专用">机械专用</option><option value="环化机房">环化机房</option><option value="生工智慧教室">生工智慧教室</option>
			<option value="电信专用">电信专用</option><option value="留学生专用">留学生专用</option><option value="经管机房">经管机房</option><option value="美术机房">美术机房</option>
			<option value="美术画室">美术画室</option><option value="计科专用">计科专用</option><option value="语音室">语音室</option><option value="钢琴专用">钢琴专用</option>
			<option value="陶艺室">陶艺室</option></select></td><td class="errors"></td></tr>
		<tr><td>楼号：</td><td><input type="text" name="buildingNum"></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	
	<!-- 如果有错误消息输出错误提示 -->
	<p class="errorMessage"></p>
</div>

</body>
</html>