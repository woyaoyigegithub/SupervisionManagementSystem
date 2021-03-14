/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertStaff）
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
var idInput,nameInput,departmentSelect,passwordInput,powerIdSelect;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,nameErrorTd,departmentErrorTd,passwordErrorTd,powerIdErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\d{7}$/;
	var vName=/^.+$/;
	var vDepartment=/^.*$/;
	var vPassword=/^\w{6,}$/;
	var vPowerId=/^\d+$/;

	var flag=true;
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("教职工编号必须是7位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(nameInput.val())){ 
		nameErrorTd.text("请输入教职工姓名"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vDepartment.test(departmentSelect.val())){ 
		departmentErrorTd.text("请选择所属学院"); 
		departmentErrorTd.show();
		flag=false;
	}
	if(!vPassword.test(passwordInput.val())){ 
		passwordErrorTd.text("密码至少6位数且必须由字母、数字或下划线组成的"); 
		passwordErrorTd.show();
		flag=false;
	}
	if(!vPowerId.test(powerIdSelect.val())){ 
		powerIdErrorTd.text("请选择权限"); 
		powerIdErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取表单数据，发起（url：addStaff）http请求到后台。
function submitData(){
	var id,name,department,powerId;
	id=idInput.val();
	name=nameInput.val();
	department=departmentSelect.val();
	password=passwordInput.val();
	powerId=powerIdSelect.val();
	
	$.ajax({
        type: "POST",
        url: "insertStaff",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	name:name,
        	department:department,
        	password:password,
			power:{id:powerId}
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryStaff";
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
	departmentSelect.val("");
	passwordInput.val("");
	powerIdSelect.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	nameInput=$("input[name='name']");
	departmentSelect=$("select[name='department']");
	passwordInput=$("input[name='password']");
	powerIdSelect=$("select[name='powerId']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	departmentErrorTd=$(".errors").eq(2);
	passwordErrorTd=$(".errors").eq(3);
	powerIdErrorTd=$(".errors").eq(4);
	
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