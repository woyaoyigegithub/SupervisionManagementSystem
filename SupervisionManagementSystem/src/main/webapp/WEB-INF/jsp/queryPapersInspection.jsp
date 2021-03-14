<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询毕业论文检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/papersInspection.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryPapersInspection.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询毕业论文检查信息</h2>

	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>毕业论文编号：<input type="number" name="id" min="0"></li>
			<li>学生学号：<input type="number" name="studentId" min="0"></li>
			<li>指导教师工号：<input type="number" name="instructorId" min="0"></li>
			<c:if test="${'督导'!=staff.power.name}"><li>查询教师工号：<input type="number" name="consultTeacherId" min="0"></li></c:if>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
		
		
	<c:choose>
		<c:when test="${fn:length(papersInspectionList)==0 }"><h3>无毕业论文记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr>
					<td><input id="selectAllChk" type="checkbox"></td><td>毕业论文编号</td><td>学生学号</td><td>学生姓名</td><td>指导教师工号</td>
					<td>指导教师姓名</td><td>查询教师工号</td><td>查询教师姓名</td><td>备注</td>
					<td>抽查项编号</td><td>抽查项内容</td><td>抽查情况记录</td><td>选项</td><td>备注</td><td>查阅人对此毕业设计（论文）的综合意见</td><td>操作</td>
				</tr>
				<c:forEach items="${papersInspectionList}" var="papersInspection">
					<c:set var="len" value="${fn:length(papersInspection.papersInspectionSituationList)}"/>
					
					<c:forEach items="${papersInspection.papersInspectionSituationList}" var="papersInspectionSituation" varStatus="vs">
					
						<c:if test="${vs.index==0}">
							<tr><td rowspan="${len}"><input type="checkbox"></td><td rowspan="${len}">${papersInspection.id}</td>
							<td rowspan="${len}">${papersInspection.student.id}</td><td rowspan="${len}">${papersInspection.student.name}</td>
							<td rowspan="${len}">${papersInspection.instructor.id}</td><td rowspan="${len}">${papersInspection.instructor.name}</td>
							<td rowspan="${len}">${papersInspection.consultTeacher.id}</td><td rowspan="${len}">${papersInspection.consultTeacher.name}</td>
							<td rowspan="${len}">${papersInspection.remarks}</td>
						</c:if>
						
						<c:if test="${vs.index!=0}"><tr></c:if>
						<td>${papersInspectionSituation.papersInspectionItems.id}</td><td>${papersInspectionSituation.papersInspectionItems.content}</td>
						<td>${papersInspectionSituation.papersInspectionItems.options}</td><td>${papersInspectionSituation.situation}</td>
						<td>${papersInspectionSituation.remarks}</td>
						<c:if test="${vs.index!=0}"></tr></c:if>
						
						<c:if test="${vs.index==0}">
							<td rowspan="${len}">${papersInspection.generalComments}</td>
							<td rowspan="${len}"><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
						</c:if>
						
					</c:forEach>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryPapersInspection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新毕业论文信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr>
			<td>毕业论文编号</td><td class="essential">学生学号</td><td class="essential">指导教师工号</td>
			<td class="essential">查阅教师工号</td><td>备注</td>
		</tr>
		<tr>
			<td><input type="number" name="id" min="0"></td>
			<td><input type="number" name="studentId" min="0"></td>
			<td><input type="number" name="instructorId" min="0"></td>
			<td><input type="number" name="consultTeacherId" min="0"></td>
			<td><textarea name="remarks" rows="3" cols="30"></textarea></td>
		</tr>
		<tr>
			<td>抽查项编号</td><td>抽查项内容</td><td>抽查情况记录</td><td class="essential">选项</td><td>备注</td>
		</tr>
		
		<!-- 毕业论文复查记录处start -->
		<!-- 毕业论文复查记录处end -->
		
		<tr><td colspan="2" class="essential">查阅人对此毕业设计（论文）的综合意见：</td><td colspan="3"><textarea name="generalComments" rows="3" cols="70"></textarea></td></tr>
		
		<tr><td colspan="5"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
	
	<p class="errorMessage"></p>
	
</div>

</body>
</html>