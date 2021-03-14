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
var dateInput,startingTimeInput,endingTimeInput,tourInspectionAreaInput,supervisorIdInput,situationTextAreaList;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验date,startingTime等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vDate=/^\d{4}-\d{2}-\d{2}$/;
	var vStartingTime=/^\d{1,2}$/;
	var vEndingTime=/^\d{1,2}$/;
	var vTourInspectionArea=/^.+$/;
	var vSupervisorId=/^\d{7}$/;
	var vSituation=/^.+$/;
	
	//提示错误的消息
	var text="";
	var flag=true;
	
	if(!vDate.test(dateInput.val())){ 
		text+="请输入日期\n"; 
		flag=false;
	}
	if(!vStartingTime.test(startingTimeInput.val())){ 
		text+="请输入起时\n"; 
		flag=false;
	}
	if(!vEndingTime.test(endingTimeInput.val())){ 
		text+="请输入终时\n"; 
		flag=false;
	}
	if(!vTourInspectionArea.test(tourInspectionAreaInput.val())){ 
		text+="请输入巡查区域\n"; 
		flag=false;
	}
	if(!vSupervisorId.test(supervisorIdInput.val())){ 
		text+="请输入督导工号\n"; 
		flag=false;
	}
	situationTextAreaList.each(function(index){
		var situationTextArea=situationTextAreaList.eq(index);
		if(!vSituation.test(situationTextArea.val())){
			text+=index+1+".具体情况记录为空\n";
		}
	});
	
	if(flag==false){ alert(text); return; }
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var date,startingTime,endingTime,tourInspectionArea,supervisorId;
	var tourInspectionSituationList=[];
	
	var trList=insertDataArea.find("td:contains(巡考项编号)").parent().nextUntil(insertBtn.parents("tr"));
	
	date=dateInput.val();
	startingTime=startingTimeInput.val();
	endingTime=endingTimeInput.val();
	tourInspectionArea=tourInspectionAreaInput.val();
	supervisorId=supervisorIdInput.val();
	situationTextAreaList.each(function(index){
		var situationTextArea=situationTextAreaList.eq(index);
		var tdList=trList.eq(index).children();
		tourInspectionSituationList.push({tourInspectionItems:{id:tdList.eq(0).text()},situation:situationTextArea.val()});
	});
	
	
	$.ajax({
        type: "POST",
        url: "insertTourInspection",
        dataType: "text",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	date:date,
        	startingTime:startingTime,
        	endingTime:endingTime,
        	tourInspectionArea:tourInspectionArea,
			supervisor:{id:supervisorId},
			tourInspectionSituationList:tourInspectionSituationList
        }),
        success: function (data) {
            if(data!="true"){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryTourInspection";
        },
        error: function (xhr) {
           alert(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	startingTimeInput.val("");
	endingTimeInput.val("");
	tourInspectionAreaInput.val("");
	situationTextAreaList.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	dateInput=$("input[name='date']");
	startingTimeInput=$("input[name='startingTime']");
	endingTimeInput=$("input[name='endingTime']");
	tourInspectionAreaInput=$("input[name='tourInspectionArea']");
	supervisorIdInput=$("input[name='supervisorId']");
	situationTextAreaList=$("textarea[name='situation']");
	
	//根据登录者身份，进行简单初始化
	if(getIdentity()=="督导"){ 
		supervisorIdInput.val(getStaffId());
		supervisorIdInput.attr("disabled",true);
		dateInput.val(getDate());
		dateInput.attr("disabled",true);
	}
	
	
	//test数据
//	startingTimeInput.val("14");
//	endingTimeInput.val("16");
//	tourInspectionAreaInput.val("慎思5栋、6栋");
//	situationTextAreaList.val("情况");
	
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