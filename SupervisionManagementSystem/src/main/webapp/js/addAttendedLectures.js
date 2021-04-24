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
var courseSelectionIdInput,typeSelect,supervisorIdInput,teachingSectionInput,clazzIdInput,
numOfClassInput,actualNumInput,lateNumInput,leavingEarlyNumInput,dateInput,weeklyInput,
weekSelect,jieciInput,attendedLecturesItemsIdTdList,evaluationScoreInputList,
remarksTextareaList,totalScoreInput,evaluationLevelInput,experimentalProcessTextarea,
alrpTextarea,discussingOrImprovingTextarea;
//查询选课的button
var queryCourseSelectionBtn;
//错误消息显示标签
var errorMessageP;
//插入，清空button
var insertBtn,clearBtn;



//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
	var vType=/^(跟踪听课)|(讲课比赛)|(新进教师)|(随机听课)|(其他)$/;
	var vSupervisorId=/^\d{7}$/;
	var vTeachingSection=/^.+$/;
	var vClazzId=/^\d{10}$/;
	var vNumOfClass=/^\d*$/;
	var vActualNum=/^\d+$/;
	var vLateNum=/^\d+$/;
	var vLeavingEarlyNum=/^\d+$/;
	var vWeekly=/^\d+$/;
	var vWeek=/^[一二三四五六日]$/;
	var vJieci=/^\d{1,2}-\d{1,2}$/;
	var vEvaluationScore=/^\d{1,2}$/;
	var vRemarks=/^.*$/;
	var vTotalScore=/^\d{1,3}$/;
	var vEvaluationLevel=/^[A-D]$/;
	var vExperimentalProcess=/^.*$/;
	var valrp=/^.*$/;
	var vDiscussingOrImproving=/^.*$/;
	
	var text="";
	var flag=true;
	
	if(!vCourseSelectionId.test(courseSelectionIdInput.val())){ 
		text+="课程编号不规范\n"; 
		flag=false;
	}
	if(!vType.test(typeSelect.val())){ 
		text+="请选择听课类型\n"; 
		flag=false;
	}
	if(!vSupervisorId.test(supervisorIdInput.val())){ 
		text+="督导工号不规范\n"; 
		flag=false;
	}						//teachingSectionInput
	if(!vTeachingSection.test(teachingSectionInput.val())){ 
		text+="教学章节不能为空\n"; 
		flag=false;
	}
	if(!vClazzId.test(clazzIdInput.val())){ 
		text+="班级编号不规范\n"; 
		flag=false;
	}
	if(!vNumOfClass.test(numOfClassInput.val())){ 
		text+="应到人数不能为空\n"; 
		flag=false;
	}
	if(!vActualNum.test(actualNumInput.val())){ 
		text+="实到人数不能为空\n"; 
		flag=false;
	}
	if(!vLateNum.test(lateNumInput.val())){ 
		text+="迟到人数不能为空\n"; 
		flag=false;
	}
	if(!vLeavingEarlyNum.test(leavingEarlyNumInput.val())){ 
		text+="早退人数不能为空\n"; 
		flag=false;
	}
	if(!vWeekly.test(weeklyInput.val())){ 
		text+="周次不能为空\n"; 
		flag=false;
	}
	if(!vWeek.test(weekSelect.val())){ 
		text+="星期不能为空\n"; 
		flag=false;
	}
	if(!vJieci.test(jieciInput.val())){ 
		text+="节次不能为空\n"; 
		flag=false;
	}
	
	evaluationScoreInputList.each(function(index,ele){
		var evaluationScoreInput=evaluationScoreInputList.eq(index);
		if(!vEvaluationScore.test(evaluationScoreInput.val())){ 
			text+=index+".评价分数为空\n"; 
			flag=false;
		}
	});
	
	remarksTextareaList.each(function(index,ele){
		var remarksTextarea=remarksTextareaList.eq(index);
		if(!vRemarks.test(remarksTextarea.val())){ 
			text+=index+".备注不规范\n"; 
			flag=false;
		}
	});
	
	if(!vTotalScore.test(totalScoreInput.val())){ 
		text+="总分数区间应为[0-100]\n"; 
		flag=false;
	}
	if(!vEvaluationLevel.test(evaluationLevelInput.val())){ 
		text+="评价等级应为[A-D]\n"; 
		flag=false;
	}
	if(!vExperimentalProcess.test(experimentalProcessTextarea.val())){ 
		text+="实验过程记录不能为空\n"; 
		flag=false;
	}
	if(!valrp.test(alrpTextarea.val())){ 
		text+="值得肯定、学习、借鉴或推广不能为空\n"; 
		flag=false;
	}
	if(!vDiscussingOrImproving.test(discussingOrImprovingTextarea.val())){ 
		text+="值得商榷或改进不能为空\n"; 
		flag=false;
	}
	if(flag==false) { alert(text); }
	return flag;
}



//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var courseSelectionId,type,supervisorId,teachingSection,clazzId,
	numOfClass,actualNum,lateNum,leavingEarlyNum,date,weekly,
	week,jieci,totalScore,evaluationLevel,experimentalProcess,
	alrp,discussingOrImproving;
	
	var attendedLecturesSituationList=[];
	
	courseSelectionId=courseSelectionIdInput.val();
	type=typeSelect.val();
	supervisorId=supervisorIdInput.val();
	teachingSection=teachingSectionInput.val();
	clazzId=clazzIdInput.val();
	numOfClass=numOfClassInput.val();
	actualNum=actualNumInput.val();
	lateNum=lateNumInput.val();
	leavingEarlyNum=leavingEarlyNumInput.val();
	date=dateInput.val();
	weekly=weeklyInput.val();
	week=weekSelect.val();
	jieci=jieciInput.val();
	
	
	evaluationScoreInputList.each(function(index,ele){
		var evaluationScoreInput=evaluationScoreInputList.eq(index);
		var remarksTextarea=remarksTextareaList.eq(index);
		
		var id=evaluationScoreInput.parents("tr").children("td:first-child").text();
		var evaluationScore=evaluationScoreInput.val();
		var remarks=remarksTextarea.val();
		attendedLecturesSituationList.push({attendedLecturesItems:{id:id},evaluationScore:evaluationScore,remarks:remarks});
	});
	
	totalScore=totalScoreInput.val();
	evaluationLevel=evaluationLevelInput.val();
	experimentalProcess=experimentalProcessTextarea.val();
	alrp=alrpTextarea.val();
	discussingOrImproving=discussingOrImprovingTextarea.val();
	
	var data={courseSelection:{id:courseSelectionId},type:type,supervisor:{id:supervisorId},
			teachingSection:teachingSection,clazz:{id:clazzId},actualNum:actualNum,lateNum:lateNum,
			leavingEarlyNum:leavingEarlyNum,date:date,weekly:weekly,week:week,jieci:jieci,
			attendedLecturesSituationList:attendedLecturesSituationList,totalScore:totalScore,
			evaluationLevel:evaluationLevel,experimentalProcess:experimentalProcess,
			alrp:alrp,discussingOrImproving:discussingOrImproving};
	
	$.ajax({
        type: "POST",
        url: "insertAttendedLectures",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify(data),
        dataType: "json",
        success: function (data) {
           if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryAttendedLectures";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
	
}


//清空表格数据
function clearData(){
	typeSelect.val("");
	supervisorIdInput.val("");
	teachingSectionInput.val("");
	clazzIdInput.val("");
	numOfClassInput.val("");
	actualNumInput.val("");
	lateNumInput.val("");
	leavingEarlyNumInput.val("");
	weeklyInput.val("");
	jieciInput.val("");
	
	evaluationScoreInputList.val("");
	remarksTextareaList.val("");
	
	totalScoreInput.val("");
	evaluationLevelInput.val("");
	experimentalProcessTextarea.val("");
	alrpTextarea.val("");
	discussingOrImprovingTextarea.val("");
	
}



//选课编号初始化成功与失败的输入框状态设置
function initComponentsState(flag){
	//初始化失败时，除选课编号输入框外，其余输入框全部禁用
	if(!flag){
		courseSelectionIdInput.attr("disabled",false);
		queryCourseSelectionBtn.show();
		typeSelect.attr("disabled",true);
		if(getIdentity()=="管理员"){ supervisorIdInput.attr("disabled",false); }else{ supervisorIdInput.attr("disabled",true); }
		teachingSectionInput.attr("disabled",true);
		clazzIdInput.attr("disabled",true);
		numOfClassInput.attr("disabled",true);
		actualNumInput.attr("disabled",true);
		lateNumInput.attr("disabled",true);
		leavingEarlyNumInput.attr("disabled",true);
		dateInput.attr("disabled",true);
		weeklyInput.attr("disabled",true);
		weekSelect.attr("disabled",true);
		jieciInput.attr("disabled",true);
		totalScoreInput.attr("disabled",true);
		evaluationLevelInput.attr("disabled",true);
		experimentalProcessTextarea.attr("disabled",true);
		alrpTextarea.attr("disabled",true);
		discussingOrImprovingTextarea.attr("disabled",true);
		insertBtn.attr("disabled",true);
		clearBtn.attr("disabled",true);
		return;
	}
	//初始化成功时，除选课编号，督导工号，应到人数，日期，星期，总分和评价等级外，其余输入框状态均为正常输入状态
	courseSelectionIdInput.attr("disabled",true);
	queryCourseSelectionBtn.hide();
	typeSelect.attr("disabled",false);
	if(getIdentity()=="管理员"){ supervisorIdInput.attr("disabled",false); }else{ supervisorIdInput.attr("disabled",true); }
	teachingSectionInput.attr("disabled",false);
	if(clazzIdInput.val()!=""){ clazzIdInput.attr("disabled",true); }else{ clazzIdInput.attr("disabled",false); }
	numOfClassInput.attr("disabled",true);
	actualNumInput.attr("disabled",false);
	lateNumInput.attr("disabled",false);
	leavingEarlyNumInput.attr("disabled",false);
	dateInput.attr("disabled",true);
	weeklyInput.attr("disabled",false);
	weekSelect.attr("disabled",true);
	jieciInput.attr("disabled",false);
	totalScoreInput.attr("disabled",true);
	evaluationLevelInput.attr("disabled",true);
	experimentalProcessTextarea.attr("disabled",false);
	alrpTextarea.attr("disabled",false);
	discussingOrImprovingTextarea.attr("disabled",false);
	insertBtn.attr("disabled",false);
	clearBtn.attr("disabled",false);
}


//所有的评价分数填写完毕后，显示总分数和评价等级
function showTotalScoreAndEvaluationLevelWhenWritedAllScore(){
	var totalScore=0;
	var evaluationLevel;
	
	//判断评价分数是否全部填写完毕
	evaluationScoreInputList.each(function(index){
		var evaluationScoreInput=evaluationScoreInputList.eq(index);
		var evaluationScore=evaluationScoreInput.val();
		evaluationScore=evaluationScore==""?0:parseInt(evaluationScore);
		totalScore+=evaluationScore;
	});
	
	//全部填写完毕，则显示总分数，和评价等级
	totalScoreInput.val(totalScore);
	switch (true) {
	case totalScore>=90:
		evaluationLevel='A';
		break;
	case totalScore>=80:
		evaluationLevel='B';
		break;
	case totalScore>=60:
		evaluationLevel='C';
		break;
	default:
		evaluationLevel='D';
		break;
	}
	evaluationLevelInput.val(evaluationLevel);
}


//初始化评价分数和备注控件列表、注册添加、删除控件点击事件
function finishLastInit(){
	//获取评价分数和备注控件列表
	evaluationScoreInputList=$(".evaluationScore");
	remarksTextareaList=$(".remarks");
	
	insertBtn.click(function(){
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ return; }
		submitData();
	});
	
	clearBtn.click(clearData);
	
	//注册事件
	evaluationScoreInputList.each(function(index){
		var evaluationScoreInput=evaluationScoreInputList.eq(index);
		evaluationScoreInput.change(function(){
			//如果该输入框存在评价分数超过最大值的情况，则给与提示
			max=parseInt(evaluationScoreInput.attr("max"));
			val=parseInt(evaluationScoreInput.val());
			if(val>max){
				alert("超出规定数！");
				evaluationScoreInput.val("");
				return;
			}
			//显示总分数和评价等级
			showTotalScoreAndEvaluationLevelWhenWritedAllScore();
		});
	});
	
	//增加输入字数
	experimentalProcessTextarea.attr("cols",60);
	alrpTextarea.attr("cols",60);
	discussingOrImprovingTextarea.attr("cols",60);
}


//通过听课项列表在网页上显示出来
function showAttendedLecturesItemsList(attendedLecturesItemsList,status){
	if(status=="error"){
		alert(status);
		return;
	}
	//将获取到的听课项列表放入表格中
	var dom=dataTab.find("tr").filter(":nth-last-child(1),:nth-last-child(2),"+
			":nth-last-child(3),:nth-last-child(4),:nth-last-child(5)");
	dom.remove();
	for(var i=0;i<attendedLecturesItemsList.length;i++){
		var attendedLecturesItems=attendedLecturesItemsList[i];
		dataTab.append('<tr><td>'+attendedLecturesItems.id+'</td><td colspan="2">'+attendedLecturesItems.content+
		'</td><td>'+attendedLecturesItems.score+'</td><td><input class="evaluationScore" type="number" max="10" maxlength="3">'+
		'</td><td><textarea class="remarks" rows="3" cols="30"></textarea></td></tr>');
	}
	dataTab.append(dom);
	
	//初始化评价分数和备注控件列表、注册添加、删除控件点击事件
	finishLastInit();
}


//通过循环查询课程编号是否为存在，来设置课程编号输入框
function tryGetCourseSelectionIdByInquire(){
	var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;	//输入所需的选课编号
	
	var courseSelectionId=prompt("请输入选课编号");
	//选课编号格式不正确
	if(!vCourseSelectionId.test(courseSelectionId)){
		var answer=confirm("选课编号不规范！是否重试？");
		if(answer==true){ tryGetCourseSelectionIdByInquire(); }
		else { initComponentsState(false); }
		return;
	}
	//选课编号格式正确时
	$.ajax({
			url:"queryCourseSelectionById",
			type: "post",
			dataType: "json",
			data:{courseSelectionId:courseSelectionId},
			success: function(courseSelection){
				//不存在该选课
				if(courseSelection==undefined){	
					var answer=confirm("选课编号不存在！是否重试？");
					if(answer==true){ tryGetCourseSelectionIdByInquire(); }
					else { initComponentsState(false); }
					return;
				}
				courseSelectionIdInput.val(courseSelectionId);
				//存在该选课，将获取到的数据放入对应标签中，并设置适当的状态
				if(courseSelection.clazz!=null){ 
					clazzIdInput.val(courseSelection.clazz.id);
					clazzIdInput.attr("disabled",true);
				}
				numOfClassInput.val(courseSelection.numOfClass);
				initComponentsState(true);
				finishLastInit();
				//发起获取听课项列表请求，并刷新网页
				$.post("queryAttendedLecturesItemsList",{dataType:"json",type:courseSelection.course.type},showAttendedLecturesItemsList);
			},
			error: function(xhr){ alert(xhr.responseText); }
	});
}


//查询课程，如果获得课程对象则显示在表格数据中，若果没有则显示错误信息
function showTableData(){
	var courseSelectionId=courseSelectionIdInput.val();
	if(""!=courseSelectionId.trim()){
		$.post("queryCourseSelectionById",
				{courseSelectionId:courseSelectionId},
				function(data,status){
					//获取选课对象
					courseSelection=eval(data);
					if(status=="success" && courseSelection!=undefined){	//存在该选课
						//将获取到的数据放入对应标签中，并设置适当的状态
						courseSelectionIdInput.attr("disabled",true);
						if(courseSelection.clazz!=null){ 
							clazzIdInput.val(courseSelection.clazz.id);
							clazzIdInput.attr("disabled",true);
						}
						numOfClassInput.val(courseSelection.numOfClass);
						
						//发起获取听课项列表请求，并刷新网页
						$.post("queryAttendedLecturesItemsList",{type:data.course.type},showAttendedLecturesItemsList);
						return;
					}else if(status=="success" && courseSelection==undefined){	//不存在该选课
						//显示错误消息
						errorMessageP.text("选课编号错误");
						return;
					}
					alert(status);
				}
		);
	}
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	courseSelectionIdInput=$("input[name='courseSelectionId']");
	typeSelect=$("select[name='type']");
	supervisorIdInput=$("input[name='supervisorId']");		
	teachingSectionInput=$("input[name='teachingSection']");
	clazzIdInput=$("input[name='clazzId']");
	numOfClassInput=$("input[name='numOfClass']");	numOfClassInput.attr("disabled",true);
	actualNumInput=$("input[name='actualNum']");
	lateNumInput=$("input[name='lateNum']");
	leavingEarlyNumInput=$("input[name='leavingEarlyNum']");
	dateInput=$("input[name='date']");		
	weeklyInput=$("input[name='weekly']");
	weekSelect=$("select[name='week']");	
	jieciInput=$("input[name='jieci']");
	totalScoreInput=$("input[name='totalScore']");	totalScoreInput.attr("disabled",true);
	evaluationLevelInput=$("input[name='evaluationLevel']");	evaluationLevelInput.attr("disabled",true);
	experimentalProcessTextarea=$("textarea[name='experimentalProcess']");
	alrpTextarea=$("textarea[name='alrp']");
	discussingOrImprovingTextarea=$("textarea[name='discussingOrImproving']");
	
	queryCourseSelectionBtn=$("#queryCourseSelectionBtn");
	
	//初始化日期和星期
	supervisorIdInput.val(getIdentity()=="督导"?getStaffId():"");	supervisorIdInput.attr("disabled",true);
	dateInput.val(getDate());	dateInput.attr("disabled",true);
	weekSelect.val(['日','一','二','三','四','五','六'][new Date().getDay()]);	weekSelect.attr("disabled",true);
	
	//test数据
//	typeSelect.val("跟踪听课");
//	teachingSectionInput.val("第一章");
//	clazzIdInput.val("2020010179");
//	actualNumInput.val("0");
//	lateNumInput.val("0");
//	leavingEarlyNumInput.val("0");
//	weeklyInput.val("5");
//	jieciInput.val("3-4");
//	experimentalProcessTextarea.val("实验过程");
//	alrpTextarea.val("值得肯定、学习、借鉴或推广");
//	discussingOrImprovingTextarea.val("值得商榷或改进");
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");	
	clearBtn=$("#clearBtn");
	
	//如果没有选课id的值（没有从查询选课页面的超链接点击过来，而是直接点击的导航栏），则提示输入选课编号
	if(courseSelectionIdInput.val()==""){ tryGetCourseSelectionIdByInquire(); }
	else {
		initComponentsState(true);
		//初始化评价分数和备注控件列表、注册添加、删除控件点击事件
		finishLastInit();
	}
	
	
	queryCourseSelectionBtn.click(function(){
		errorMessageP.hide();
		//若果课程编号格式正确，显示完整表格并发起ajax请求获取听课项列表
		var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
		if(!vCourseSelectionId.test(courseSelectionIdInput.val())){
			alert("选课编号格式不正确！");
			errorMessageP.text("选课编号格式不正确！");
			errorMessageP.show();
			return;
		}
		showTableData();
		//恢复输入框输入状态,并完成最后初始化
		initComponentsState(true);
		finishLastInit();
	});
	
});
