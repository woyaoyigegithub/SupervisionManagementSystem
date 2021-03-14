<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询教室信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryClassroom.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询教室信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>教室编号:<input type="number" name="id" min="0" maxlength="7"></li>
			<li>教室名称:<input type="text" name="name"></li>
			<li>座位数：<input type="number" name="seatingCapacity"></li>
			<li>教室类别：<select name="type"><option value=""></option><option value="三笔字专用">三笔字专用</option><option value="体育场地">体育场地</option>
			<option value="体育多媒体">体育多媒体</option><option value="国画室">国画室</option><option value="外语专用">外语专用</option><option value="多媒体">多媒体</option>
			<option value="实验室">实验室</option><option value="微格">微格</option><option value="普通话">普通话</option><option value="服装室">服装室</option>
			<option value="机房">机房</option><option value="机械专用">机械专用</option><option value="环化机房">环化机房</option><option value="生工智慧教室">生工智慧教室</option>
			<option value="电信专用">电信专用</option><option value="留学生专用">留学生专用</option><option value="经管机房">经管机房</option><option value="美术机房">美术机房</option>
			<option value="美术画室">美术画室</option><option value="计科专用">计科专用</option><option value="语音室">语音室</option><option value="钢琴专用">钢琴专用</option>
			<option value="陶艺室">陶艺室</option></select></li>
			<li>楼号：<input type="text" name="buildingNum"></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(classroomList)==0 }"><h3>无教室记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>教室编号</td><td>教室名称</td><td>座位数</td><td>教室类别</td>
					<td>楼号</td><td>操作</td>
				</tr>
				<c:forEach items="${classroomList}" var="classroom">
					<tr><td><input type="checkbox"></td><td>${classroom.id}</td><td>${classroom.name}</td><td>${classroom.seatingCapacity}</td>
					<td>${classroom.type}</td><td>${classroom.buildingNum}</td>
					<td><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryClassroom?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新班级信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td class="essential">教室编号：</td><td><input type="number" name="id" min="0" maxlength="7"></td><td class="errors"></td></tr>
		<tr><td class="essential">教室名称：</td><td><input type="text" name="name"></td><td class="errors"></td></tr>
		<tr><td class="essential">座位数：</td><td><input type="text" name=seatingCapacity></td><td class="errors"></td></tr>
		<tr><td class="essential">教室类别：</td><td><select name="type"><option value=""></option><option value="三笔字专用">三笔字专用</option><option value="体育场地">体育场地</option>
			<option value="体育多媒体">体育多媒体</option><option value="国画室">国画室</option><option value="外语专用">外语专用</option><option value="多媒体">多媒体</option>
			<option value="实验室">实验室</option><option value="微格">微格</option><option value="普通话">普通话</option><option value="服装室">服装室</option>
			<option value="机房">机房</option><option value="机械专用">机械专用</option><option value="环化机房">环化机房</option><option value="生工智慧教室">生工智慧教室</option>
			<option value="电信专用">电信专用</option><option value="留学生专用">留学生专用</option><option value="经管机房">经管机房</option><option value="美术机房">美术机房</option>
			<option value="美术画室">美术画室</option><option value="计科专用">计科专用</option><option value="语音室">语音室</option><option value="钢琴专用">钢琴专用</option>
			<option value="陶艺室">陶艺室</option></select></td><td class="errors"></td></tr>
		<tr><td>楼号：</td><td><input type="text" name="buildingNum"><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>
	
</body>
</html>