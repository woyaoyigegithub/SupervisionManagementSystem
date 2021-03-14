/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertDailyInspectionItems）
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
var contentTextarea;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var contentErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验content等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vContent=/^.+$/;
	
	var flag=true;
	
	if(!vContent.test(contentTextarea.val())){ 
		contentErrorTd.text("请输入巡查项内容"); 
		contentErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取表单数据，发起（url：addDailyInspectionItems）http请求到后台。
function submitData(){
	var content;
	content=contentTextarea.val();
	
	$.ajax({
        type: "POST",
        url: "insertDailyInspectionItems",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	content:content,
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryDailyInspectionItems";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
	
}


//清空表格数据
function clearData(){
	contentTextarea.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	contentTextarea=$("textarea[name='content']");
	
	contentErrorTd=$(".errors").eq(0);
	
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
		//清空上一次检验信息
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});