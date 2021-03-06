<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>统计巡考次数</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/countXXXNum.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/addCountTourInspectionNum.js"></script>
</head>
<body>

<!-- 统计数据区域  -->
<div class="countDataArea">
	<h2>统计巡考次数</h2>
	
	<c:if test="${fn:length(tourInspectionCountsList)==0}">
			<h3>还没有数据哟！</h3>
		</c:if>
	
	
	<c:if test="${fn:length(tourInspectionCountsList)!=0}">
		<table>
			<caption>我的巡考记录统计</caption>
			<tr><td>学年</td><td>学期</td><td>听课次数</td></tr>
			<c:forEach items="${tourInspectionCountsList}" var="tourInspectionCounts">
				<tr><td>${tourInspectionCounts.schoolYear}</td><td>${tourInspectionCounts.semester}</td><td>${tourInspectionCounts.count}</td></tr>
			</c:forEach>
		</table>
	</c:if>
	
</div>

</body>
</html>