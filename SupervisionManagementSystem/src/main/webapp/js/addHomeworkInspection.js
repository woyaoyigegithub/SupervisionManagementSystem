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
var courseSelectionIdInput,assignmentsOrReportsFenNumInput,assignmentsOrReportsCiNumInput,correctionTimesInput,seriousCorrectionInput,
moreSeriousCorrectionInput,generalCorrectionInput,poorCorrectionInput,remarksTextarea;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var courseSelectionIdErrorTd,assignmentsOrReportsFenNumErrorTd,assignmentsOrReportsCiNumErrorTd,correctionTimesErrorTd,seriousCorrectionErrorTd,
moreSeriousCorrectionErrorTd,generalCorrectionErrorTd,poorCorrectionErrorTd,remarksErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验courseSelectionId,assignmentsOrReportsFenNum等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vCourseSelectionId=/^\(\d{4}-\d{4}-1|2\)-\d{10}-\d{7}-\d+$/;
	var vAssignmentsOrReportsFenNum=/^\d+$/;
	var vAssignmentsOrReportsCiNum=/^\d+$/;
	var vCorrectionTimes=/^\d+$/;
	var vSeriousCorrection=/^\d+$/;
	var vMoreSeriousCorrection=/^\d+$/;
	var vGeneralCorrection=/^\d+$/;
	var vPoorCorrection=/^\d+$/;
	var vRemarks=/^.+$/;
	
	var flag=true;
	
	if(!vCourseSelectionId.test(courseSelectionIdInput.val())){ 
		courseSelectionIdErrorTd.text("选课编号不规范"); 
		courseSelectionIdErrorTd.show();
		flag=false;
	}
	if(!vAssignmentsOrReportsFenNum.test(assignmentsOrReportsFenNumInput.val())){ 
		assignmentsOrReportsFenNumErrorTd.text("请填写作业或报告份数"); 
		assignmentsOrReportsFenNumErrorTd.show();
		flag=false;
	}
	if(!vAssignmentsOrReportsCiNum.test(assignmentsOrReportsCiNumInput.val())){ 
		assignmentsOrReportsCiNumErrorTd.text("请填写作业或报告次数"); 
		assignmentsOrReportsCiNumErrorTd.show();
		flag=false;
	}
	if(!vCorrectionTimes.test(correctionTimesInput.val())){ 
		correctionTimesErrorTd.text("请填写批改次数"); 
		correctionTimesErrorTd.show();
		flag=false;
	}
	if(!vSeriousCorrection.test(seriousCorrectionInput.val())){ 
		seriousCorrectionErrorTd.text("请填写批改认真"); 
		seriousCorrectionErrorTd.show();
		flag=false;
	}
	if(!vMoreSeriousCorrection.test(moreSeriousCorrectionInput.val())){ 
		moreSeriousCorrectionErrorTd.text("请填写批改较认真"); 
		moreSeriousCorrectionErrorTd.show();
		flag=false;
	}
	if(!vGeneralCorrection.test(generalCorrectionInput.val())){ 
		generalCorrectionErrorTd.text("请填写批改一般"); 
		generalCorrectionErrorTd.show();
		flag=false;
	}
	if(!vPoorCorrection.test(poorCorrectionInput.val())){ 
		poorCorrectionErrorTd.text("请填写批改差"); 
		poorCorrectionErrorTd.show();
		flag=false;
	}
	if(!vRemarks.test(remarksTextarea.val())){ 
		remarksErrorTd.text("请填写备注"); 
		remarksErrorTd.show();
		flag=false;
	}
	
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var courseSelectionId,assignmentsOrReportsFenNum,assignmentsOrReportsCiNum,correctionTimes,seriousCorrection,
	moreSeriousCorrection,generalCorrection,poorCorrection,remarks;
	
	courseSelectionId=courseSelectionIdInput.val();
	assignmentsOrReportsFenNum=assignmentsOrReportsFenNumInput.val();
	assignmentsOrReportsCiNum=assignmentsOrReportsCiNumInput.val();
	correctionTimes=correctionTimesInput.val();
	seriousCorrection=seriousCorrectionInput.val();
	moreSeriousCorrection=moreSeriousCorrectionInput.val();
	generalCorrection=generalCorrectionInput.val();
	poorCorrection=poorCorrectionInput.val();
	remarks=remarksTextarea.val();
	
	$.ajax({
        type: "POST",
        url: "insertHomeworkInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	courseSelection:{id:courseSelectionId},
        	assignmentsOrReportsFenNum:assignmentsOrReportsFenNum,
        	assignmentsOrReportsCiNum:assignmentsOrReportsCiNum,
        	correctionTimes:correctionTimes,
			seriousCorrection:seriousCorrection,
			moreSeriousCorrection:moreSeriousCorrection,
			generalCorrection:generalCorrection,
			poorCorrection:poorCorrection,
			remarks:remarks,
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryHomeworkInspection";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	assignmentsOrReportsFenNumInput.val("");
	assignmentsOrReportsCiNumInput.val("");
	correctionTimesInput.val("");
	seriousCorrectionInput.val("");
	moreSeriousCorrectionInput.val("");
	generalCorrectionInput.val("");
	poorCorrectionInput.val("");
	remarksTextarea.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	courseSelectionIdInput=$("input[name='courseSelectionId']");
	assignmentsOrReportsFenNumInput=$("input[name='assignmentsOrReportsFenNum']");
	assignmentsOrReportsCiNumInput=$("input[name='assignmentsOrReportsCiNum']");
	correctionTimesInput=$("input[name='correctionTimes']");
	seriousCorrectionInput=$("input[name='seriousCorrection']");
	moreSeriousCorrectionInput=$("input[name='moreSeriousCorrection']");
	generalCorrectionInput=$("input[name='generalCorrection']");
	poorCorrectionInput=$("input[name='poorCorrection']");
	remarksTextarea=$("textarea[name='remarks']");
	
	//如果是从选课表点击过来的则，选课Id控件就禁用
	if(courseSelectionIdInput.val()!=""){ courseSelectionIdInput.attr("disabled",true); }
	
	//test数据
//	courseSelectionIdInput.val("(2020-2021-1)-0201140011-0001009-6");
//	assignmentsOrReportsFenNumInput.val(100);
//	assignmentsOrReportsCiNumInput.val(4);
//	correctionTimesInput.val(50);
//	seriousCorrectionInput.val(1);
//	moreSeriousCorrectionInput.val(1);
//	generalCorrectionInput.val(1);
//	poorCorrectionInput.val(1);
//	remarksTextarea.val("有点怪！");
	
	courseSelectionIdErrorTd=$(".errors").eq(0);
	assignmentsOrReportsFenNumErrorTd=$(".errors").eq(1);
	assignmentsOrReportsCiNumErrorTd=$(".errors").eq(2);
	correctionTimesErrorTd=$(".errors").eq(3);
	seriousCorrectionErrorTd=$(".errors").eq(4);
	moreSeriousCorrectionErrorTd=$(".errors").eq(5);
	generalCorrectionErrorTd=$(".errors").eq(6);
	poorCorrectionErrorTd=$(".errors").eq(7);
	remarksErrorTd=$(".errors").eq(8);
	
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