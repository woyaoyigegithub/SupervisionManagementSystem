<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/loginForm.css" >
<script type="text/javascript" src="js/vcode.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/loginForm.js"></script>
</head>
<body>
<h2>督导工作系统登录页面</h2>


<div class="loginArea">
	<div class="bgImg"></div>
	<table>
		<tr><td>教职工号：</td><td><input type="text" name="id"></td></tr>
		<tr><td>密码：</td><td><input type="password" name="password"></td></tr>
		<tr>
			<td>身份:</td>
			<td>
				<select name="identity"><option value="">==请选择身份==</option>
					<option selected>督导</option><option>管理员</option></select>
			</td>
		</tr>
		<tr>
			<td>验证码：</td>
			<td>
				<input type="text" name="vcode" maxlength="4">
				<canvas id="canvas" width="50px" height="20px"></canvas>
			</td>
		</tr>
		<tr><td colspan="2" align="center"><input type="button" value="登录" id="logOn"></td></tr>
	</table>
</div>
<p class="errorMessage">${errorMessage}</p>


     

     

    


</body>
</html>