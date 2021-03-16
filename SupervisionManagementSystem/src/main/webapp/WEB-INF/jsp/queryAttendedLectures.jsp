<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询听课信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/attendedLectures.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryAttendedLectures.js"></script>
</head>
<body>



<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询听课信息</h2>
	
	<!-- 高级搜索区域 -->
		<div>
			<h3>高级搜索</h3>
			<ul class="advancedSearch">
				<li>听课编号:<input type="number" name="id" min="0"></li>
				<li>听课类型:<select name="type"><option value=""></option>
					<option value="跟踪听课">跟踪听课</option><option value="讲课比赛">讲课比赛</option>
					<option value="新进教师">新进教师</option><option value="随机听课">随机听课</option>
					<option value="其他 ">其他 </option></select></li>
				<li>选课编号:<input type="text" name="courseSelectionId" min="0"></li>
				<c:if test="${staff.power.name=='管理员'}"><li>督导编号:<input type="number" name="supervisorId" min="0"></li></c:if>
				<li>教师编号:<input type="number" name="teacherId" min="0"></li>
				<li>上课班级编号:<input type="number" name="classId" min="0"></li>
				<li><input type="button" id="searchBtn" value="搜索">
				<c:if test="${staff.power.name=='管理员'}"><input type="button" id="batchDeleteBtn" value="批量删除"></c:if></li>
			</ul>
		</div>
	
	
	<c:choose>
		<c:when test="${fn:length(attendedLecturesList)==0}"><h3>无听课信息！</h3></c:when>
		
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>听课编号</td><td>听课类型</td><td>选课编号</td><td>督导工号</td>
					<td>督导姓名</td><td>教师编号</td><td>教师姓名</td><td>教学章节</td><td>上课班级编号</td><td>上课班级</td><td>应到人数</td><td>实到人数</td>
					<td>迟到人数</td><td>早退人数</td><td>听课日期</td><td>周次</td><td>星期</td><td>节次</td><td>评价项编号</td><td>评价项内容</td><td>分数</td>
					<td>评价分数</td><td>备注</td><td>总分</td><td>评价等级</td><td>实验过程</td><td>值得肯定、学习、借鉴或推广的是</td><td>值得商榷或改进的是</td>
					<c:if test="${staff.power.name=='管理员'}"><td>操作</td></c:if>
				</tr>
				<c:forEach items="${attendedLecturesList}" var="attendedLectures" varStatus="vs">
					<!-- 听课情况列表长度 -->
					<c:set var="len" value="${fn:length(attendedLectures.attendedLecturesSituationList)}" />
					
					<c:forEach items="${attendedLectures.attendedLecturesSituationList}" var="attendedLecturesSituation" varStatus="vs0">
						<c:if test="${vs0.index==0}">
							<tr>
								<td rowspan="${len}"><input type="checkbox"></td><td rowspan="${len}">${attendedLectures.id}</td>
								<td rowspan="${len}">${attendedLectures.type}</td><td rowspan="${len}">${attendedLectures.courseSelection.id}</td>
								<td rowspan="${len}">${attendedLectures.supervisor.id}</td><td rowspan="${len}">${attendedLectures.supervisor.name}</td>
								<td rowspan="${len}">${attendedLectures.courseSelection.teacher.id}</td><td rowspan="${len}">${attendedLectures.courseSelection.teacher.name}</td>
								<td rowspan="${len}">${attendedLectures.teachingSection}</td><td rowspan="${len}">${attendedLectures.clazz.id}</td>
								<td rowspan="${len}">${attendedLectures.clazz.name}</td><td rowspan="${len}">${attendedLectures.courseSelection.numOfClass}</td>
								<td rowspan="${len}">${attendedLectures.actualNum}</td><td rowspan="${len}">${attendedLectures.lateNum}</td>
								<td rowspan="${len}">${attendedLectures.leavingEarlyNum}</td><td rowspan="${len}">${attendedLectures.date}</td>
								<td rowspan="${len}">${attendedLectures.weekly}</td><td rowspan="${len}">${attendedLectures.week}</td>
								<td rowspan="${len}">${attendedLectures.jieci}</td>
						</c:if>
						
						<c:if test="${vs0.index!=0}"><tr></c:if>
							<td>${attendedLecturesSituation.attendedLecturesItems.id}</td>
							<td>${attendedLecturesSituation.attendedLecturesItems.content}</td>
							<td>${attendedLecturesSituation.attendedLecturesItems.score}</td>
							<td>${attendedLecturesSituation.evaluationScore}</td>
							<td>${attendedLecturesSituation.remarks}</td>
						<c:if test="${vs0.index!=0}"></tr></c:if>
						
						<c:if test="${vs0.index==0}">
							<td rowspan="${len}">${attendedLectures.totalScore}</td>
							<td rowspan="${len}">${attendedLectures.evaluationLevel}</td>
							<td rowspan="${len}">${attendedLectures.experimentalProcess}</td>
							<td rowspan="${len}">${attendedLectures.alrp}</td>
							<td rowspan="${len}">${attendedLectures.discussingOrImproving}</td>
							<c:if test="${staff.power.name=='管理员'}"><td rowspan="${len}"><img class="updateImage" src="images/update.gif">
							<img class="deleteImage" src="images/delete.gif"></td></c:if></tr>
							
						</c:if>
					</c:forEach>
						
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryAttendedLectures?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>
	
	
<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新听课信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td class="essential">听课编号：</td><td colspan="2"><input type="number" name="id"></td>
			<td class="essential">选课编号：</td><td colspan="2"><input type="text" name="courseSelectionId" maxlength="35"></td>
		</tr>
		<tr>
			<td class="essential">听课类型：</td><td colspan="2"><select name="type"><option value=""></option>
				<option value="跟踪听课">跟踪听课</option><option value="讲课比赛">讲课比赛</option>
				<option value="新进教师">新进教师</option><option value="随机听课">随机听课</option>
				<option value="其他 ">其他 </option></select></td>
			<td class="essential">督导工号：</td><td colspan="2"><input type="number" name="supervisorId"></td>
		</tr>
		<tr><!-- 跟踪听课', '讲课比赛', '新进教师', '随机听课', '其他 -->
			<td class="essential">教学章节：</td><td colspan="2"><input type="text" name="teachingSection"></td>
			<td class="essential">上课班级：</td><td colspan="2"><input type="text" name="classId"></td>
		</tr>
		<tr>
			<td class="essential">学生出勤：</td><td colspan="2">应到<input type="number" name="numOfClass" min="0">人,
				实到<input type="number" name="actualNum" min="0">人<br>
				迟到<input type="number" name="lateNum" min="0">人,
				早退<input type="number" name="leavingEarlyNum" min="0">人</td>
			<td class="essential">听课日期：</td>
			<td colspan="2">
				日期：<input type="date" name="date" disabled><br>第<input type="number" name="weekly" min="1" max="30">周,
				星期<select name="week"><option value=""></option><option value="一">一</option><option value="二">二</option><option value="三">三</option>
				<option value="四">四</option><option value="五">五</option><option value="六">六</option><option value="日">日</option></select>,
				第<input type="text" name="jieci" maxlength="4" value="3-4">节
			</td>
		</tr>
		
		<!-- 评价项区 -->
		<tr><td>评价项编号</td><td colspan="2">评价内容</td><td>分值</td><td class="essential">评价分数</td><td>备注</td></tr>
		<tr>
			<td class="essential">总分：</td><td colspan="2"><input type="number" name="totalScore" disabled></td>
			<td class="essential">评价等级：</td><td colspan="2"><input type="text" name="evaluationLevel" disabled></td>	
		</tr>
		<tr><td colspan="2" class="essential">实验过程：</td><td colspan="4"><textarea name="experimentalProcess" rows="5" cols="70"></textarea></td></tr>
		<tr><td colspan="2" class="essential">值得肯定、学习、借鉴或推广的是：</td><td colspan="4"><textarea name="alrp" rows="5" cols="70"></textarea></td></tr>
		<tr><td colspan="2" class="essential">值得商榷或改进的是：</td><td colspan="4"><textarea name="discussingOrImproving" rows="5" cols="70"></textarea></td></tr>
		
		<tr><td colspan="6"><input type="button" id="updateBtn" value="更新"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	<p class="errorMessage"></p>
</div>




</body>
</html>