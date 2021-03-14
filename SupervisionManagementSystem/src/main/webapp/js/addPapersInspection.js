/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,upstudentIdXXX,deleteXXX.（例：insertClass）
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
var studentIdInput,instructorIdInput,consultTeacherIdInput,remarksTextareaList,situationSelectList,generalCommentsTextarea;
//抽查项编号行
var papersInspectionItemsIdTr;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验studentId,instructorId等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vStudentId=/^\d{12}$/;
	var vInstructorId=/^\d{7}$/;
	var vConsultTeacherId=/^\d{7}$/;
	var vRemarks=/^.*$/;
	var vSituation=/^.+$/;
	var vGeneralComments=/^.+$/;
	
	//提示错误的消息
	var text="";
	var flag=true;
	
	if(!vStudentId.test(studentIdInput.val())){ 
		text+="学生学号应为10位数\n"; 
		flag=false;
	}
	if(!vInstructorId.test(instructorIdInput.val())){ 
		text+="指导教师工号为7位数\n"; 
		flag=false;
	}
	if(!vConsultTeacherId.test(consultTeacherIdInput.val())){ 
		text+="查阅教师工号应为7位数\n"; 
		flag=false;
	}
	remarksTextareaList.each(function(index){
		var remarksTextarea=remarksTextareaList.eq(index);
		if(!vRemarks.test(remarksTextarea.val())){
			if(index==0){ text+="备注为空\n" }
			else { text+=index+".备注为空\n" };
			flag=false;
		}
	});
	situationSelectList.each(function(index){
		var situationSelect=situationSelectList.eq(index);
		if(!vSituation.test(situationSelect.val())){
			text+=index+1+".选项为空\n";
			flag=false;
		}
	});
	if(!vGeneralComments.test(generalCommentsTextarea.val())){
		text+="请输入查阅人对此毕业设计（论文）的综合意见\n";
		flag=false;
	}
	
	if(flag==false){ alert(text); return; }
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var studentId,instructorId,consultTeacherId,generalComments;
	var papersInspectionSituationList=[];
	
	var trList=papersInspectionItemsIdTr.nextUntil(insertBtn.parents("tr"));
	
	studentId=studentIdInput.val();
	instructorId=instructorIdInput.val();
	consultTeacherId=consultTeacherIdInput.val();
	generalComments=generalCommentsTextarea.val();
	situationSelectList.each(function(index){
		var tr=trList.eq(index);
		var situationSelect=situationSelectList.eq(index);
		var remarksTextarea=remarksTextareaList.eq(index+1);
		
		papersInspectionSituationList.push({papersInspectionItems:{id:tr.children("td:eq(0)").text()},
			situation:situationSelect.val(),remarks:remarksTextarea.val()});
	});
	
	
	
	$.ajax({
        type: "POST",
        url: "insertPapersInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	student:{id:studentId},
        	instructor:{id:instructorId},
        	consultTeacher:{id:consultTeacherId},
        	papersInspectionSituationList:papersInspectionSituationList,
        	generalComments:generalComments,
        	remarks:remarksTextareaList.eq(0).val(),
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	alert(data);
            	return;
            }
            location.href = "queryPapersInspection";
        },
        error: function (xhr) {
           alert(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	studentIdInput.val("");
	instructorIdInput.val("");
	consultTeacherIdInput.val("");
	remarksTextareaList.val("");
	situationSelectList.val("");
	generalCommentsTextarea.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	studentIdInput=dataTab.find("input[name='studentId']");
	instructorIdInput=dataTab.find("input[name='instructorId']");
	consultTeacherIdInput=dataTab.find("input[name='consultTeacherId']");
	remarksTextareaList=dataTab.find("textarea[name='remarks']");
	situationSelectList=dataTab.find("select[name='situation']");
	generalCommentsTextarea=dataTab.find("textarea[name='generalComments']");
	
	//身份识别
	if(getIdentity()=="督导"){
		consultTeacherIdInput.val(getStaffId());
		consultTeacherIdInput.attr("disabled",true);
	}
	
	papersInspectionItemsIdTr=dataTab.find("td:contains(抽查项编号)").parent();
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	
	//test数据
//	studentIdInput.val("201701010211");
//	instructorIdInput.val("0000066");
//	consultTeacherIdInput.val(getIdentity()=="督导"?getStaffId():"0000258");
//	remarksTextareaList.val("无");
//	situationSelectList.each((index)=>{
//		var situationSelect=situationSelectList.eq(index);
//		var first_children=situationSelect.children(":eq(1)");
//		first_children.prop('selected',true);
//	});
//	generalCommentsTextarea.val("查阅人对此毕业设计（论文）的综合意见");
	
	
	insertBtn.click(function(){
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清空button清除表单所有数据
		clearData();
	});
	
});