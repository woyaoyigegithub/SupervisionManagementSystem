<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加听课信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/attendedLectures.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addAttendedLectures.js"></script>
</head>
<body>

<!-- 插入数据区域  -->
<div class="insertDataArea">
	<h2>添加听课信息</h2>
	
	<!-- 数据表格 -->
	<table class="dataTab">
		<tr>
			<td class="essential">选课编号：</td><td colspan="5"><input type="text" name="courseSelectionId" maxlength="35" 
				value="${courseSelectionId}" placeholder="(2020-2021-1)-0010000001-0000558-1">
				<input type="button" id="queryCourseSelectionBtn" value="查询"></td>
		</tr>
		<tr>
			<td class="essential">听课类型：</td><td colspan="2"><select name="type"><option value=""></option>
				<option value="跟踪听课">跟踪听课</option><option value="讲课比赛">讲课比赛</option>
				<option value="新进教师">新进教师</option><option value="随机听课">随机听课</option>
				<option value="其他 ">其他 </option></select></td>
			<td class="essential">督导工号：</td><td colspan="2"><input type="number" name="supervisorId" placeholder="0000325"></td>
		</tr>
		<tr><!-- 跟踪听课', '讲课比赛', '新进教师', '随机听课', '其他 -->
			<td class="essential">教学章节：</td><td colspan="2"><input type="text" name="teachingSection"></td>
			<td class="essential">上课班级：</td><td colspan="2"><input type="text" name="clazzId" value="${clazzId}"></td>
		</tr>
		<tr>
			<td class="essential">学生出勤：</td><td colspan="2">应到<input type="number" name="numOfClass" min="0" value="${numOfClass}">人,
				实到<input type="number" name="actualNum" placeholder="" min="0">人<br>
				迟到<input type="number" name="lateNum" min="0">人,
				早退<input type="number" name="leavingEarlyNum" min="0">人</td>
			<td class="essential">听课日期：</td>
			<td colspan="2">
				日期：<input type="date" name="date"><br>第<input type="number" name="weekly" min="1" max="30">周,
				星期<select name="week"><option value=""></option><option>一</option><option>二</option>
				<option>三</option><option>四</option><option>五</option><option>六</option><option>日</option></select>,
				第<input type="text" name="jieci" maxlength="4">节
			</td>
		</tr>
		
		<!-- 评价项区 -->
		<tr><td>评价项编号</td><td colspan="2">评价内容</td><td>分值</td><td class="essential">评价分数</td><td>备注</td></tr>
		
		<!-- 听课情况列表start -->
		<c:forEach items="${attendedLecturesItemsList}" var="attendedLecturesItems">
			<tr><td>${attendedLecturesItems.id}</td><td colspan="2">${attendedLecturesItems.content}</td><td>${attendedLecturesItems.score}</td>
			<td><input class="evaluationScore" type="number" max="10" maxlength="3"></td><td><textarea class="remark" rows="3" cols="30"></textarea></td></tr>
		</c:forEach>
		<!-- 听课情况列表end -->
		
		<tr>
			<td class="essential">总分：</td><td colspan="2"><input type="number" name="totalScore"></td>
			<td class="essential">评价等级：</td><td colspan="2"><input type="text" name="evaluationLevel"></td>	
		</tr>
		<tr><td colspan="2" class="essential">实验过程：</td><td colspan="4"><textarea name="experimentalProcess" placeholder="请输入实验过程" rows="5" cols="${courseSelectionId==null?40:60}"></textarea></td></tr>
		<tr><td colspan="2" class="essential">值得肯定、学习、借鉴或推广的是：</td><td colspan="4"><textarea name="alrp" placeholder="请输入肯定、学习、借鉴或推广的内容" rows="5" cols="${courseSelectionId==null?40:60}"></textarea></td></tr>
		<tr><td colspan="2" class="essential">值得商榷或改进的是：</td><td colspan="4"><textarea name="discussingOrImproving" placeholder="值得商榷或改进的地方" rows="5" cols="${courseSelectionId==null?40:60}"></textarea></td></tr>
		
		<tr><td colspan="6"><input type="button" id="insertBtn" value="提交"><input type="button" id="clearBtn" value="清空"></td></tr>
	</table>
	<p class="errorMessage"></p>
	
</div>

</body>
</html>