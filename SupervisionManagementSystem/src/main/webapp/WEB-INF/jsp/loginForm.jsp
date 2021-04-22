<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background: url(images/bg.jpg) no-repeat contain;">
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css">  
<link rel="stylesheet" href="css/loginForm2.css"> 
<script type="text/javascript" src="js/vcode.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/loginForm.js"></script>


</head>
<body>

<div class="bg"></div>

<div class="loginArea">
	<h2 class="text-center">督导工作系统</h2>
	
	<div class="input-group">
		<span class="input-group-addon">职工号</span></span>
		<input class="form-control" type="text" name="id">
	</div>
	
	<div class="input-group">
		<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
		<input class="form-control" type="password" name="password">
	</div>
	
	<div class="input-group">
		<span class="input-group-addon">验证码</span>
		<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
			<input class="form-control" type="text" name="vcode" maxlength="4">
		</div>
		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			<canvas id="canvas" width="70px" height="30px"></canvas>
		</div>
	</div>
	
	<input class="btn btn-success btn-block" type="button" value="登录" id="logOn">
	<p class="errorMessage">${errorMessage}</p>
</div>




     

     

    


</body>
</html>