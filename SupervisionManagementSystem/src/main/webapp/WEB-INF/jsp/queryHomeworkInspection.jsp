<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询作业检查信息</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/pager.css">
<link rel="stylesheet" type="text/css" href="css/homeworkInspection.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/queryHomeworkInspection.js"></script>
</head>
<body>

<!-- 查询区域 -->
<div class="queryDataArea">
	<h2>查询作业检查信息</h2>
	
	<!-- 高级搜索区域 -->
	<div>
		<h3>高级搜索</h3>
		<ul class="advancedSearch">
			<li>作业检查编号<input type="number" name="id" min="0"></li>
			<li>选课编号<input type="text" name="courseSelectionId" min="0"></li>
			<li><input type="button" id="searchBtn" value="搜索"><input type="button" id="batchDeleteBtn" value="批量删除"></li>
		</ul>
	</div>
	
	
	<c:choose>
		<c:when test="${fn:length(homeworkInspectionList)==0 }"><h3>无作业检查记录</h3></c:when>
	
		<c:otherwise>
			<!-- 数据表格区域 -->
			<table class="dataTab">
				<tr><td rowspan="2"><input id="selectAllChk" type="checkbox"></td><td rowspan="2">作业检查编号</td><td rowspan="2">选课编号</td>
					<td rowspan="2">课程名称</td><td rowspan="2">任课教师工号</td><td rowspan="2">任课教师姓名</td><td rowspan="2">学生数</td><td rowspan="2">作业或报告份数</td>
					<td rowspan="2">作业或报告次数</td><td colspan="5">批改批改情况</td><td rowspan="2">备注</td><td rowspan="2">操作</td></tr>
				<tr><td>批改次数</td><td>批改认真</td><td>批改较认真</td><td>批改一般</td><td>批改一般</td></tr>
				
				<c:forEach items="${homeworkInspectionList}" var="homeworkInspection">
					<tr><td><input type="checkbox"></td><td>${homeworkInspection.id}</td><td>${homeworkInspection.courseSelection.id}</td>
					<td>${homeworkInspection.courseSelection.course.name}</td><td>${homeworkInspection.courseSelection.teacher.id}</td>
					<td>${homeworkInspection.courseSelection.teacher.name}</td><td>${homeworkInspection.courseSelection.numOfClass}</td>
					<td>${homeworkInspection.assignmentsOrReportsFenNum}</td><td>${homeworkInspection.assignmentsOrReportsCiNum}</td><td>${homeworkInspection.correctionTimes}</td>
					<td>${homeworkInspection.seriousCorrection}</td><td>${homeworkInspection.moreSeriousCorrection}</td><td>${homeworkInspection.generalCorrection}</td>
					<td>${homeworkInspection.poorCorrection}</td><td>${homeworkInspection.remarks}</td>
					<td><img class="updateImage" src="images/update.gif"><img class="deleteImage" src="images/delete.gif"></td></tr>
				</c:forEach>
			</table>
			
			<!-- 分页区域 -->
			<pager:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" recordCount="${pageModel.recordCount}"
			 submitUrl="queryHomeworkInspection?pageIndex={0}"/>
		</c:otherwise>
	</c:choose>
</div>



<!-- 更新区域 -->
<div class="updateDataArea">
	<h2>更新作业检查信息</h2>
	
	<!-- 数据表格区域 -->
	<table class="dataTab">
		<tr><td>作业检查编号:</td><td><input type="number" name="id" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">选课编号：</td><td><input type="text" name="courseSelectionId" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">作业或报告份数：</td><td><input type="number" name="assignmentsOrReportsFenNum" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">作业或报告次数：</td><td><input type="number" name="assignmentsOrReportsCiNum" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改次数：</td><td><input type="number" name="correctionTimes" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改认真：</td><td><input type="number" name="seriousCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改较认真：</td><td><input type="number" name="moreSeriousCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改一般：</td><td><input type="number" name="generalCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">批改差：</td><td><input type="number" name="poorCorrection" min="0"></td><td class="errors"></td></tr>
		<tr><td class="essential">备注：</td><td><textarea name="remarks" rows="3" cols="30"></textarea></td><td class="errors"></td></tr>
		<tr><td colspan="2"><input type="button" id="updateBtn" value="修改"><input type="button" id="cancelBtn" value="取消"></td></tr>
	</table>
</div>

</body>
</html>