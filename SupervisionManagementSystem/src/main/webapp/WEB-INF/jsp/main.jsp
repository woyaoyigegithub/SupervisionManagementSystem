<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页面</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>

<!-- 显示 -->
<div class="topArea">
	<div class="say_hello_text">
		欢迎【${staff.power.name}：${staff.id}(${staff.name})】登录&nbsp;&nbsp;<a href="loginout">注销</a>
	</div>
</div>

<div class="middleArea">

	<!-- 导航栏 -->
	<ul class="navigBar">
		<li class="oneMenuItems admin"><img src="images/circle.jpg">班级信息管理</li>
		<li class="items admin">添加班级信息</li>
		<li class="items admin">查询班级信息</li>
		
		<li class="oneMenuItems admin"><img src="images/circle.jpg">学生信息管理</li>
		<li class="items admin">添加学生信息</li>
		<li class="items admin">查询学生信息</li>
		
		<li class="oneMenuItems admin"><img src="images/circle.jpg">教室信息管理</li>
		<li class="items admin">添加教室信息</li>
		<li class="items admin">查询教室信息</li>
		
		<li class="oneMenuItems admin"><img src="images/circle.jpg">课程信息管理</li>
		<li class="items admin">添加课程信息</li>
		<li class="items admin">查询课程信息</li>
		
		<li class="oneMenuItems admin"><img src="images/circle.jpg">教职工信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">权限管理</li>
		<li class="items admin">添加权限</li>
		<li class="items admin">查询权限</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">教职工管理</li>
		<li class="items admin">添加教职工</li>
		<li class="items admin">查询教职工</li>
		
		
		<li class="oneMenuItems"><img src="images/circle.jpg">选课信息管理</li>
		<li class="items admin">添加选课</li>
		<li class="items">查询选课</li>
		
		<!-- 督查区域人员安排-->
		<li class="oneMenuItems"><img src="images/circle.jpg">督查区域安排信息管理</li>
		<li class="items admin">添加督查区域安排</li>
		<li class="items">查询督查区域安排</li>

		<!-- 听课-->
		<li class="oneMenuItems"><img src="images/circle.jpg">听课信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">评价项管理</li>
		<li class="items admin">添加评价项</li>
		<li class="items admin">查询评价项</li>
		
		<li class="twoMenuItems"><img src="images/add.jpg">听课管理</li>
		<li class="items">添加听课</li>
		<li class="items">查询听课</li>
		
		<!-- 日常巡查-->
		<li class="oneMenuItems"><img src="images/circle.jpg">日常巡查信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">日常巡查项管理</li>
		<li class="items admin">添加日常巡查项</li>
		<li class="items admin">查询日常巡查项</li>
		
		<li class="twoMenuItems"><img src="images/add.jpg">日常巡查管理</li>
		<li class="items">添加日常巡查</li>
		<li class="items">查询日常巡查</li>
		
		<!-- 巡考-->
		<li class="oneMenuItems"><img src="images/circle.jpg">巡考信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">巡考项管理</li>
		<li class="items admin">添加巡考项</li>
		<li class="items admin">查询巡考项</li>
		
		<li class="twoMenuItems"><img src="images/add.jpg">巡考管理</li>
		<li class="items">添加巡考</li>
		<li class="items">查询巡考</li>
		
		
		<!-- 作业检查-->
		<li class="oneMenuItems"><img src="images/circle.jpg">作业检查信息管理</li>
<!-- 		<li class="twoMenuItems"><img src="images/add.jpg">作业检查管理</li> -->
		<li class="items">添加作业检查</li>
		<li class="items">查询作业检查</li>
		
		<!-- 教案检查-->
		<li class="oneMenuItems"><img src="images/circle.jpg">教案检查信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">教案检查项管理</li>
		<li class="items admin">添加教案检查项</li>
		<li class="items admin">查询教案检查项</li>
		
		<li class="twoMenuItems"><img src="images/add.jpg">教案检查管理</li>
		<li class="items">添加教案检查</li>
		<li class="items">查询教案检查</li>
		
		
		<!-- 毕业论文-->
		<li class="oneMenuItems"><img src="images/circle.jpg">毕业论文信息管理</li>
		<li class="twoMenuItems admin"><img src="images/add.jpg">抽查项管理</li>
		<li class="items admin">添加抽查项</li>
		<li class="items admin">查询抽查项</li>
		
		<li class="twoMenuItems"><img src="images/add.jpg">毕业论文检查管理</li>
		<li class="items">添加毕业论文检查</li>
		<li class="items">查询毕业论文检查</li>
		
		<li class="oneMenuItems supervisor"><img src="images/circle.jpg">数据统计信息管理</li>
		<li class="items supervisor">统计听课次数</li>
		<li class="items supervisor">统计日常巡查次数</li>
		<li class="items supervisor">统计巡考次数</li>
	</ul>
	
	<iframe class="infoManFrame" name="infoManFrame"></iframe>
</div>

</body>
</html>