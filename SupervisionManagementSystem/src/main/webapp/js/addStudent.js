/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertClass）
 * 2. 将网页中的使用到的标签对象的声明在js文件开头.
 * 3. 自定义函数均要写注释来表明其功能.
 * 4. 函数中的变量均声明在函数体开头处，变量使用驼峰命名法，变量取名要有意义.
 * 5. 当函数中代码量繁多时，可按照功能单独写成一个函数供其调用.
 */


//插入数据区域
var insertDataArea;	
//数据展示表格
var dataTab;	

//插入数据区域中的各个<input>标签
var idInput,nameInput,sexSelect,classIdInput;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,nameErrorTd,sexErrorTd,classIdErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^[1-9]\d{11}$/;
	var vName=/^.+$/;
	var vSex=/^(男)|(女)$/;
	var vClassId=/^[1-9]\d{9}$/;
	var flag=true;
	
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("学号必须是12十位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(nameInput.val())){ 
		nameErrorTd.text("姓名格式不正确"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vSex.test(sexSelect.val())){ 
		sexErrorTd.text("请选择性别"); 
		sexErrorTd.show();
		flag=false;
	}
	if(!vClassId.test(classIdInput.val())){ 
		classIdErrorTd.text("所在班级格式不正确"); 
		classIdErrorTd.show();
		flag=false;
	}
	
	
	
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var id,name,sex,classId;
	id=idInput.val();
	name=nameInput.val();
	sex=sexSelect.val();
	classId=classIdInput.val();
	
	$.ajax({
        type: "POST",
        url: "insertStudent",
        dataType: "text",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	name:name,
        	sex:sex,
        	clazz:{id:classId}
        }),
        success: function (data) {
            if(data!="true"){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryStudent";
        },
        error: function (message) {
            console.log(message);
        }
    });
}


//清空表格数据
function clearData(){
	idInput.val("");
	nameInput.val("");
	sexSelect.val("");
	classIdInput.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	nameInput=$("input[name='name']");
	sexSelect=$("select[name='sex']");
	classIdInput=$("input[name='classId']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	sexErrorTd=$(".errors").eq(2);
	classIdErrorTd=$(".errors").eq(3);
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	insertBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ clearData(); return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});