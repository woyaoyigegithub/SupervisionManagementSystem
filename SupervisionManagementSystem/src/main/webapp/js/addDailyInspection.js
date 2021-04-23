/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertDailyInspection）
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
var inspectionAreaArrangementSituationIdSelect,weeklyInput,weekSelect,dateInput,
situationTextareaList,suggestTextareaList,courseSelectionIdInputList,classIdInputList,
normalTimeInputList,actualTimeInputList;
//错误消息
var errorMessageP;
//追加一行、删除最后一行button
var appendRowBtn,deleteLastRowBtn;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验inspectionAreaArrangementSituationId,weekly等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vInspectionAreaArrangementSituationId=/^\d+$/;
	var vWeekly=/^\d{1,2}$/;
	var vWeek=/^[一二三四五六日]$/;
	var vDate=/^\d{4}-\d{2}-\d{2}$/;
	var vSituation=/^.+$/;
	var vSuggest=/^.+$/;
	var vCourseSelectionId=/^\(\d{4}-\d{4}-[12]\)-\d{10}-\d{7}-\d+$/;
	var vClassId=/^\d{10}$/;
	var vNormalTime=/^\d{2}:\d{2}$/;
	var vActualTime=/^\d{2}:\d{2}$/;

	var text="";
	var flag=true;
	
	if(!vInspectionAreaArrangementSituationId.test(inspectionAreaArrangementSituationIdSelect.val())){ 
		text+="请输入督查区域人员安排编号\n"; 
		flag=false;
	}
	if(!vWeekly.test(weeklyInput.val())){ 
		text+="请输入周次\n"; 
		flag=false;
	}
	if(!vWeek.test(weekSelect.val())){ 
		text+="请选择星期\n"; 
		flag=false;
	}
	if(!vDate.test(dateInput.val())){ 
		text+="请输入巡查日期\n"; 
		flag=false;
	}
	for(var i=0;i<situationTextareaList.length;i++){
		var situationTextarea=situationTextareaList.eq(i);
		var suggestTextarea=suggestTextareaList.eq(i);
		if(!vSituation.test(situationTextarea.val())){
			text+=i+1+".巡查情况记录为空\n"; 
			flag=false;
		}
		if(!vSuggest.test(suggestTextarea.val())){
			text+=i+1+".建议为空\n"; 
			flag=false;
		}
	}
	
	for(var i=0;i<courseSelectionIdInputList.length;i++){
		var courseSelectionIdInput=courseSelectionIdInputList.eq(i);
		var classIdInput=classIdInputList.eq(i);
		var normalTimeInput=normalTimeInputList.eq(i);
		var actualTimeInput=actualTimeInputList.eq(i);
		
		//临时错误记录
		var temText="";
		//本行填写数据数
		var flag0=0;
		
		if(courseSelectionIdInput.val().trim()==""){
			temText+=i+1+".选课编号为空\n";
		}else if(!vCourseSelectionId.test(courseSelectionIdInput.val())){
			flag0+=1;
			temText+=i+1+".选课编号不规范\n";
		}else{
			flag0+=1;
		}
		
		if(classIdInput.val().trim()==""){
			temText+=i+1+".班级编号为空\n";
		}else if(!vClassId.test(classIdInput.val())){
			flag0+=1;
			temText+=i+1+".班级编号为10位数\n";
		}else{
			flag0+=1;
		}
		
		if(normalTimeInput.val().trim()==""){
			temText+=i+1+".正常下课时间为空\n";
		}else if(!vNormalTime.test(normalTimeInput.val())){
			flag0+=1;
			temText+=i+1+".正常下课时间为空\n";
		}else{
			flag0+=1;
		}
		
		if(actualTimeInput.val().trim()==""){
			temText+=i+1+".实际下课时间为空\n";
		}else if(!vActualTime.test(actualTimeInput.val())){
			flag0+=1;
			temText+=i+1+".实际下课时间为空\n";
		}else{
			flag0+=1;
		}
		
		if(flag0>0 && flag0<4){ text+=temText;	flag=false; }
	} 
	
	if(flag==false) { alert(text); return; }
	return flag;
}


//获取表单数据，发起（url：addDailyInspection）http请求到后台。
function submitData(){
	var inspectionAreaArrangementSituationId,weekly,week,date;
	var dailyInspectionSituationList=[];
	var leaveClassAheadOfTimeSituationList=[];
	
	inspectionAreaArrangementSituationId=inspectionAreaArrangementSituationIdSelect.val();
	weekly=weeklyInput.val();
	week=weekSelect.val();
	date=dateInput.val();
	
	for(var i=0;i<situationTextareaList.length;i++){
		
		var situationTextarea=situationTextareaList.eq(i);
		var suggestTextarea=suggestTextareaList.eq(i);
		var dailyInspectionItemsId=situationTextarea.parents("tr").children("td:eq(0)").text();
		
		dailyInspectionSituationList.push({dailyInspectionItems:{id:dailyInspectionItemsId},situation:situationTextarea.val(),suggest:suggestTextarea.val()});
	}
	
	for(var i=0;i<courseSelectionIdInputList.length;i++){
		var courseSelectionIdInput=courseSelectionIdInputList.eq(i);
		var classIdInput=classIdInputList.eq(i);
		var normalTimeInput=normalTimeInputList.eq(i);
		var actualTimeInput=actualTimeInputList.eq(i);
		
		if(courseSelectionIdInput.val()!="" && classIdInput.val()!="" && normalTimeInput.val()!="" && actualTimeInput.val()!=""){
			leaveClassAheadOfTimeSituationList.push({courseSelection:{id:courseSelectionIdInput.val()},clazz:{id:classIdInput.val()},
				normalTime:normalTimeInput.val(),actualTime:actualTimeInput.val()});
		}
	}
	
	
	$.ajax({
        type: "POST",
        url: "insertDailyInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	inspectionAreaArrangementSituation:{id:inspectionAreaArrangementSituationId},
        	weekly:weekly,
        	week:week,
			date:date,
			dailyInspectionSituationList:dailyInspectionSituationList,
			leaveClassAheadOfTimeSituationList:leaveClassAheadOfTimeSituationList
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryDailyInspection";
        },
        error: function (xhr) {
            console.log(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	inspectionAreaArrangementSituationIdSelect.val("");
	weeklyInput.val("");
	situationTextareaList.val("");
	suggestTextareaList.val("");
	courseSelectionIdInputList.val("");
	classIdInputList.val("");
	normalTimeInputList.val("");
	actualTimeInputList.val("");
}


//重新初始化选课id，上课班级id、正常下课时间、实际下课时间input
function regesterCourseSelectionIdInputListAndSoOn(){
	courseSelectionIdInputList=$("input[name='courseSelectionId']");
	classIdInputList=$("input[name='classId']");
	normalTimeInputList=$("input[name='normalTime']");
	actualTimeInputList=$("input[name='actualTime']");

	//每个选课编号框失焦触发事件
	courseSelectionIdInputList.each(function(index){
		var courseSelectionIdInput=courseSelectionIdInputList.eq(index);
		var classIdInput=classIdInputList.eq(index);
		var normalTimeInput=normalTimeInputList.eq(index);
		courseSelectionIdInput.blur(function(){
			
			//清除上一次错误消息
			courseSelectionIdInput.next().remove();
			errorMessageP.text("");
			classIdInput.attr("disabled",false);
			
			
			var vCourseSelection=/^\(\d{4}-\d{4}-[12]\)-\d{10}-\d{7}-\d+$/;
			if(!vCourseSelection.test(courseSelectionIdInput.val())){
				courseSelectionIdInput.after("<font color='red'>该选课编号不符合规范</font>");
				errorMessageP.text("该选课编号不符合规范");
				return;
			}
			
			$.ajax({
				type: "post",
				url: "queryCourseSelectionById",
				dataType: "json",
				data: {courseSelectionId:courseSelectionIdInput.val()},
				success: function(data){
					if(data==null){
						courseSelectionIdInput.after("<font color='red'>该选课不存在</font>");
						errorMessageP.text("该选课不存在");
						return;
					}
					if(data.clazz!=null){ classIdInput.val(data.clazz.id); classIdInput.attr("disabled",true); }
				},
				error: function(xhr){
					alert(xhr.responseText);
				}
			})
		});
		
	});
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	inspectionAreaArrangementSituationIdSelect=$("select[name='inspectionAreaArrangementSituationId']");
	weeklyInput=$("input[name='weekly']");
	weekSelect=$("select[name='week']");	
	dateInput=$("input[name='date']");
	situationTextareaList=$("textarea[name='situation']");
	suggestTextareaList=$("textarea[name='suggest']");
	courseSelectionIdInputList=$("input[name='courseSelectionId']");
	classIdInputList=$("input[name='classId']");
	normalTimeInputList=$("input[name='normalTime']");
	actualTimeInputList=$("input[name='actualTime']");
	
	//根据身份设置控件状态
	if(getIdentity()=="督导"){
		weekSelect.val(['日','一','二','三','四','五','六'][new Date().getDay()]);
		dateInput.val(getDate());
		weekSelect.attr("disabled",true);
		dateInput.attr("disabled",true);
	}
	
	//如果是从督查区域安排表超链接点击过来，则禁用督查区域人员安排编号控件
	if(inspectionAreaArrangementSituationIdSelect.val()!=""){ inspectionAreaArrangementSituationIdSelect.attr("disabled",true); }
	
	//test数据
//	weeklyInput.val(5);
//	situationTextareaList.val("情况");
//	suggestTextareaList.val("建议");
//	courseSelectionIdInputList.val("(2020-2021-1)-0101141221-0000056-1");
//	classIdInputList.val("2020010149");
//	normalTimeInputList.val("10:00");
//	actualTimeInputList.val("09:50");
	
	
	errorMessageP=$(".errorMessage");
	
	appendRowBtn=$("#appendRowBtn");
	deleteLastRowBtn=$("#deleteLastRowBtn");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	regesterCourseSelectionIdInputListAndSoOn();
	

	//追加一行button注册点击事件
	appendRowBtn.click(function(){
		//所需的html
		var html=appendRowBtn.parents("tr").prev();
		appendRowBtn.parents("tr").before(html.prop("outerHTML"));
		
		//重新初始化选课编号，上课班级编号，正常下课时间，实际下课时间。
		regesterCourseSelectionIdInputListAndSoOn();
	});
	
	
	//删除最后一行点击事件
	deleteLastRowBtn.click(function(){
		if(courseSelectionIdInputList.length==1){
			alert("不能再删除了");
			return;
		}
		appendRowBtn.parents("tr").prev().remove();
		
		//重新初始化选课编号，上课班级编号，正常下课时间，实际下课时间。
		regesterCourseSelectionIdInputListAndSoOn();
	});
	
	
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