/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertTeachingPlanInspectionItems）
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
var contentTextarea,optionsInputList;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var contentErrorTd,optionsErrorTdList;
//错误消息
var errorMessageP;
//选项那一列
var optionsTd;
//追加一行选项button 和 删除最后一行选项button
var appendRowOptionsBtn,deleteLastRowOptionsBtn;
//插入，清空buttton
var insertBtn,clearBtn;

//当前选项个数
var count=2;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,content等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vContent=/^.+$/;
	var vOptions=/^[\u4e00-\u9fa5a-zA-Z\d_]+$/;
	
	var flag=true;
	
	if(!vContent.test(contentTextarea.val())){ 
		contentErrorTd.text("请输入教案检查指标项内容"); 
		contentErrorTd.show();
		flag=false;
	}
	optionsInputList.each(function(index){
		var optionsInput=optionsInputList.eq(index);
		var optionsErrorTd=optionsErrorTdList.eq(index);
		if(!vOptions.test(optionsInput.val())){ 
			optionsErrorTd.text("不空且只允许汉字、英文字母、数字和下划线组成"); 
			optionsErrorTd.show();
			flag=false;
		}
	});
	
	return flag;
}


//获取表单数据，发起（url：addTeachingPlanInspectionItems）http请求到后台。
function submitData(){
	var content,options;
	
	content=contentTextarea.val();
	for(var i=0;i<optionsInputList.length;i++){
		var optionsInput=optionsInputList.eq(i);
		if(i==0){
			options="▢"+optionsInput.val();
			continue;
		}
		options+=" ▢"+optionsInput.val();
	}
	
	$.ajax({
        type: "POST",
        url: "insertTeachingPlanInspectionItems",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	content:content,
        	options:options
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryTeachingPlanInspectionItems";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
	
}


//清空表格数据
function clearData(){
	contentTextarea.val("");
	optionsInputList.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	contentTextarea=$("textarea[name='content']");
	optionsInputList=$("input[name='options']");
	
	contentErrorTd=$(".errors").eq(0);
	optionsErrorTdList=$(".errors").not(contentErrorTd);
	
	errorMessageP=$(".errorMessage");
	optionsTd=$("td:contains(选项)").eq(0);
	appendRowOptionsBtn=$("#appendRowOptionsBtn");
	deleteLastRowOptionsBtn=$("#deleteLastRowOptionsBtn");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	appendRowOptionsBtn.click(function(){
		var html="<tr><td>选项"+(++count)+"：</td><td><input type='text' name='options'></td><td class='errors'></td></tr>";
		
		optionsTd.attr("rowspan",count);
		appendRowOptionsBtn.parents("tr").before(html);
		//初始化optionsInputList和optionsErrorTdList
		optionsInputList=$("input[name='options']");
		optionsErrorTdList=$(".errors").filter(":not(:eq(0))");
	});
	
	
	deleteLastRowOptionsBtn.click(function(){
		if(count==1){
			alert("不能再删除了！");
			return;
		}
		optionsTd.attr("rowspan",--count);
		appendRowOptionsBtn.parents("tr").prev().remove();
		
		//初始化optionsInputList和optionsErrorTdList和count
		optionsInputList=$("input[name='options']");
		optionsErrorTdList=$(".errors").filter(":not(:eq(0))");
	});
	
	
	insertBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){  return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清空上一次检验信息
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});