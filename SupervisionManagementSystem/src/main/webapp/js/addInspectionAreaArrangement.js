/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,uppublicationTimeXXX,deleteXXX.（例：insertInspectionAreaArrangement）
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
var schoolYearSelect,semesterSelect,startingAndEndingWeeksInput,publicationTimeInput,academicBuildingTextareaList,inspectorsNumInputList,
supervisorIdInputList,weekSelectList,earlyMiddleLateSelectList;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,courseId等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vSchoolYear=/^\d{4}-\d{4}$/;
	var vSemester=/^1|2$/;
	var vStartingAndEndingWeeks=/^(第)?\d+-\d+(周)?$/;
	var vPublicationTime=/^\d+-\d{2}-\d{2}$/;
	var vAcademicBuilding=/^.+$/;
	var vInspectorsNum=/^\d+$/;
	var vSupervisorId=/^(\d{7})?$/;
	var vWeek=/^(周一)|(周二)|(周三)|(周四)|(周五)|(周六)|(周日)$/;
	var vEarlyMiddleLate=/^(上午)|(下午)|(晚上)$/
	
	var text="";
	var flag=true;
	
	if(!vSchoolYear.test(schoolYearSelect.val())){ 
		text+="请选择学年\n"; 
		flag=false;
	}
	if(!vSemester.test(semesterSelect.val())){ 
		text+="请选择学期\n"; 
		flag=false;
	}
	if(!vStartingAndEndingWeeks.test(startingAndEndingWeeksInput.val())){ 
		text+="起止周不规范\n"; 
		flag=false;
	}
	if(!vPublicationTime.test(publicationTimeInput.val())){ 
		text+="请输入发布日期\n"; 
		flag=false;
	}
	academicBuildingTextareaList.each(function(index){
		var academicBuildingTextarea=academicBuildingTextareaList.eq(index);
		if(!vAcademicBuilding.test(academicBuildingTextarea.val())){
			text+=(index+1)+".教学楼为空\n";
			flag=false;
		}
	});
	inspectorsNumInputList.each(function(index){
		var inspectorsNumInput=inspectorsNumInputList.eq(index);
		if(!vInspectorsNum.test(inspectorsNumInput.val())){
			text+=(index+1)+".督察人数为空\n";
			flag=false;
		}
	});
	supervisorIdInputList.each(function(index){
		var supervisorIdInput=supervisorIdInputList.eq(index);
		if(!vSupervisorId.test(supervisorIdInput.val())){
			text+=(index+1)+".督导工号为空\n";
			flag=false;
		}
	});
	weekSelectList.each(function(index){
		var weekSelect=weekSelectList.eq(index);
		if(!vWeek.test(weekSelect.val())){
			text+=(index+1)+".星期为空\n";
			flag=false;
		}
	});
	earlyMiddleLateSelectList.each(function(index){
		var earlyMiddleLateSelect=earlyMiddleLateSelectList.eq(index);
		if(!vEarlyMiddleLate.test(earlyMiddleLateSelect.val())){
			text+=(index+1)+".早中晚为空\n";
			flag=false;
		}
	});
	
	if(flag==false) { alert(text); }
	return flag;
}


//获取表单数据，发起（url：addInspectionAreaArrangement）http请求到后台。
function submitData(){
	var schoolYear,semester,startingAndEndingWeeks,publicationTime;
	var inspectionAreaArrangementBuildingList=[];
	
	schoolYear=schoolYearSelect.val();
	semester=semesterSelect.val();
	startingAndEndingWeeks=startingAndEndingWeeksInput.val();
	publicationTime=publicationTimeInput.val();
	for(var index=0;index<academicBuildingTextareaList.length;index++){
		var inspectionAreaArrangementBuilding={};
		
		var academicBuildingTextarea=academicBuildingTextareaList.eq(index);
		var inspectorsNumInput=inspectorsNumInputList.eq(index);
		
		inspectionAreaArrangementBuilding['academicBuilding']=academicBuildingTextarea.val();
		inspectionAreaArrangementBuilding['inspectorsNum']=inspectorsNumInput.val();
		inspectionAreaArrangementBuilding['inspectionAreaArrangementSituationList']=[];
		
		for(var i=index*15;i<(index+1)*15;i++){
			var supervisorIdInput=supervisorIdInputList.eq(i);
			var weekSelect=weekSelectList.eq(i);
			var earlyMiddleLateSelect=earlyMiddleLateSelectList.eq(i);
		
			inspectionAreaArrangementBuilding['inspectionAreaArrangementSituationList'].push({supervisor:{id:supervisorIdInput.val()==""?null:supervisorIdInput.val()},week:weekSelect.val(),
				earlyMiddleLate:earlyMiddleLateSelect.val()});
		}
		inspectionAreaArrangementBuildingList.push(inspectionAreaArrangementBuilding);

	}
	
	$.ajax({
        type: "post",
		url: "insertInspectionAreaArrangement",
		dataType: "json",
		contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
        	schoolYear:schoolYear,
        	semester:semester,
        	startingAndEndingWeeks:startingAndEndingWeeks,
			publicationTime:publicationTime,
			inspectionAreaArrangementBuildingList:inspectionAreaArrangementBuildingList
        }),
		success: function(data){
			if(data!=true){
				errorMessageP.text(data);
				return;
			}
			location.href= "queryInspectionAreaArrangement";
		},
		error: function(xhr){
			alert(xhr.responseText);
		}
    });

}


//清空表格数据
function clearData(){
	schoolYearSelect.val("");
	semesterSelect.val("");
	startingAndEndingWeeksInput.val("");
	publicationTimeInput.val("");
	academicBuildingTextareaList.val("");
	inspectorsNumInputList.val("");
	supervisorIdInputList.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	schoolYearSelect=$("select[name='schoolYear']");
	semesterSelect=$("select[name='semester']");
	startingAndEndingWeeksInput=$("input[name='startingAndEndingWeeks']");
	publicationTimeInput=$("input[name='publicationTime']");
	academicBuildingTextareaList=$("textarea[name='academicBuilding']");
	inspectorsNumInputList=$("input[name='inspectorsNum']");
	supervisorIdInputList=$("input[name='supervisorId']");
	weekSelectList=$("select[name='week']");
	earlyMiddleLateSelectList=$("select[name='earlyMiddleLate']");
	
	publicationTimeInput.val(getDate()); publicationTimeInput.attr("disabled",true);
	var weekList=["周一","周二","周三","周四","周五"];
	var earlyMiddleLateList=['上午','下午','晚上'];
	weekSelectList.each(function(index){
		var weekSelect=weekSelectList.eq(index);
		var earlyMiddleLateSelect=earlyMiddleLateSelectList.eq(index);
		weekSelect.val(weekList[Math.floor(index/3)%5]);
		earlyMiddleLateSelect.val(earlyMiddleLateList[index%3]);
		
		weekSelect.attr("disabled",true);
		earlyMiddleLateSelect.attr("disabled",true);
	});
	
	//test数据
//	schoolYearSelect.val("2020-2021");
//	semesterSelect.val("1");
//	startingAndEndingWeeksInput.val("1-18");
//	academicBuildingTextareaList.val("教学楼");
//	inspectorsNumInputList.val("1");
//	supervisorIdInputList.val("0000077");
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	
	insertBtn.click(function(){
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ clearData(); return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清空button清除表单所有数据
		clearData();
	});
	
});