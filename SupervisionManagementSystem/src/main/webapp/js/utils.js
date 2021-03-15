/**
 * 创建一个隐藏表单，将表单数据填写其中，随后发送http请求。
 * @method submitDataByForm
 * @param {string} URL 请求发送地址
 * @param {object} params 发送参数对象（例{id:1,name:"zhangsan"}）
 * @return {null}  返回null
 */ 
 
function submitDataByForm(URL,params){
	if(URL=="" || params=={})
		return false;
	
	var form = document.createElement("form");
	form.style.display="none";
	form.action = URL;
	form.method="post";
	document.body.appendChild(form);
	
	for(var i in params){
		var input=document.createElement("input");
		input.name=i;
		input.value=params[i];
		form.appendChild(input);
	}
	
	form.submit();
}


function getDate(){
	var dateStr;
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var date=date.getDate();
	
	month=month<10?"0"+month:""+month;
	date=date<10?"0"+date:""+date;
	
	return year+"-"+month+"-"+date;
}

var getStaffId;
var getIdentity;

$(document).ready(function(){
	//获取职工id和身份
	var sayHelloText1=document.querySelector(".say_hello_text");
	var sayHelloText2=window.parent.document.querySelector(".say_hello_text");
	var text;
	if(sayHelloText1){
		text=sayHelloText1.innerText;
	}else if(sayHelloText2){
		text=sayHelloText2.innerText;
	}
	getStaffId=()=>{
		return text.slice(text.indexOf("：")+1,text.indexOf("("));	//获取职工id
	}

	getIdentity=()=>{
		return text.slice(text.indexOf("【")+1,text.indexOf("："));	//获取当前身份
	}
});

