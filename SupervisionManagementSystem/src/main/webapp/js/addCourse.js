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
var idInput,nameInput,natureSelect,typeSelect;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,nameErrorTd,natureErrorTd,typeErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\d{10}$/;
	var vName=/^.+$/;
	var vNature=/^.*$/;
	var vType=/^(理论)|(实验)|(美术)|(体育)$/;
	
	var flag=true;
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("课程编号必须是10位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(nameInput.val())){ 
		nameErrorTd.text("请输入课程名称"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vNature.test(natureSelect.val())){ 
		natureErrorTd.text("请选择课程性质"); 
		natureErrorTd.show();
		flag=false;
	}
	if(!vType.test(typeSelect.val())){ 
		typeErrorTd.text("请选择课程类型"); 
		typeErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var id,name,nature,type;
	id=idInput.val();
	name=nameInput.val();
	nature=natureSelect.val();
	type=typeSelect.val();
	
	$.ajax({
        type: "POST",
        url: "insertCourse",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	name:name,
        	nature:nature,
        	type:type,
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryCourse";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	idInput.val("");
	nameInput.val("");
	natureSelect.val("");
	typeSelect.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	nameInput=$("input[name='name']");
	natureSelect=$("select[name='nature']");
	typeSelect=$("select[name='type']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	natureErrorTd=$(".errors").eq(2);
	typeErrorTd=$(".errors").eq(3);
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	insertBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});