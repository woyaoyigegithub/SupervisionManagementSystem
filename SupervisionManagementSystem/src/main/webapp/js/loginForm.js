var canvas;	//画布标签
var ctx,ctxW,ctxH;	//画布上下文，画布宽，画布高
var veritifyCode;	//验证码

//登录名框，密码框和验证码框
var idInput,passwordInput,indentitySelect,vcodeInput;	

//绘制验证码
function drawCanvas() {
	// 清空canvas
	ctx.clearRect(0, 0, 200, 60);
	// 绘制背景
	drawBg(ctx, ctxW, ctxH, 200, 255);
	// 绘制干扰圆点
	drawCircle(ctx, ctxW, ctxH, 0, 5, 200, 255);
	// 绘制干扰线段
	drawLine(ctx, ctxW, ctxH, 0, 0, 255);
	// 绘制验证码
	veritifyCode = drawText(ctx, ctxW, ctxH, 4, 10, 15, -20, 20, 0, 100);
}

//生成验证码
function createVCode(){
	canvas=document.getElementById("canvas");
	ctx = canvas.getContext("2d");
	ctxW = canvas.clientWidth;
	ctxH = canvas.clientHeight;
	drawCanvas();
	canvas.onclick=drawCanvas;
}

//清空登录名框，密码框和验证码框文本
function clear(){
	idInput.value="";
	passwordInput.value="";
	vcodeInput.value="";
}



//登录
function login(){
	var id,password,vcode;
	var vId=/^\d{7}$/;
	var vPassword=/^\w{6,}$/;
	
	var flag=true;
	var text="";
	
	id=idInput.value;
	password=passwordInput.value;
	identity=identitySelect.value;
	vcode=vcodeInput.value;
	
	if(!vId.test(idInput.value)){ 
		text+="教职工号错误\n"; 
		flag=false;
	}
	if(!vPassword.test(passwordInput.value)){ 
		text+="密码错误\n"; 
		flag=false;
	}
	if(vcode!=veritifyCode) {
		text+="验证码错误\n"; 
		flag=false;
	}
	if(!flag){ 
		alert(text); 
		createVCode(); 
		return; 
	}
	submitDataByForm("login",{id:id,password:password,identity:identity});
}


$(document).ready(function(){
	//初始化登录名框，密码框,身份框和验证码框
	idInput=document.querySelector("input[name='id']");
	passwordInput=document.querySelector("input[name='password']");
	identitySelect=document.querySelector("select[name='identity']");
	vcodeInput=document.querySelector("input[name='vcode']");
	
	//找到canvas控件绑定产生随机验证码
	createVCode();
	
	//注册登录按钮事件
	$("#logOn").click(login);

});

$(document).keydown((event)=>{if(event.keyCode==13){login();}});