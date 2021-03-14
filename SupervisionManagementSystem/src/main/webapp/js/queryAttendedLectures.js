/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertClass）
 * 2. 将网页中的使用到的标签对象的声明在js文件开头.
 * 3. 自定义函数均要写注释来表明其功能.
 * 4. 函数中的变量均声明在函数体开头处，变量使用驼峰命名法，变量取名要有意义.
 * 5. 当函数中代码量繁多时，可按照功能单独写成一个函数供其调用.
 */


//查询数据区域，更新数据区域
var queryDataArea,updateDataArea;
//高级搜索列表
var advancedSearchUl;
//dataTab1指的是queryDataArea中的dataTab,dataTab2则是updateArea中的dataTab
var queryDataTab,updateDataTab;	

//指的是类名advancedSearch区域中的input。
var aIdInput,aTypeSelect,aCourseSelectionIdInput,aSupervisorIdInput,aTeacherIdInput,aClassIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uCourseSelectionIdInput,uTypeSelect,uSupervisorIdInput,uTeachingSectionInput,uClassIdInput,
uNumOfClassInput,uActualNumInput,uLateNumInput,uLeavingEarlyNumInput,uDateInput,uWeeklyInput,
uWeekSelect,uJieciInput,uEvaluationScoreInputList,uRemarksTextareaList,uTotalScoreInput,
uEvaluationLevelInput,uExperimentalProcessTextarea,uAlrpTextarea,uDiscussingOrImprovingTextarea;
var errorMessageP;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;



//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,type,courseSelectionId,supervisorId,teacherId,classId;
	
	id=aIdInput.val();
	type=aTypeSelect.val();
	courseSelectionId=aCourseSelectionIdInput.val();
	supervisorId=aSupervisorIdInput.val();
	teacherId=aTeacherIdInput.val();
	classId=aClassIdInput.val();
	
	id=id==""?0:parseInt(id);
	
	submitDataByForm("queryAttendedLectures",{id:id,type:type,courseSelectionId:courseSelectionId,
		supervisorId:supervisorId,teacherId:teacherId,clazzId:classId});
}


//所有的评价分数填写完毕后，显示总分数和评价等级
function showTotalScoreAndEvaluationLevelWhenWritedAllScore(){
	var flag=true;
	var totalScore=0;
	var evaluationLevel;
	
	//判断评价分数是否全部填写完毕
	uEvaluationScoreInputList.each(function(index){
		var uEvaluationScoreInput=uEvaluationScoreInputList.eq(index);
		if(""==uEvaluationScoreInput.val()){ flag=false; }
		else{ totalScore+=parseInt(uEvaluationScoreInput.val()); }
	});
	
	if(flag==false) { return; }
	
	//全部填写完毕，则显示总分数，和评价等级
	uTotalScoreInput.val(totalScore);
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
	uEvaluationLevelInput.val(evaluationLevel);
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,courseSelectionId,type,supervisorId,teachingSection,classId,
	numOfClass,actualNum,lateNum,leavingEarlyNum,date,weekly,week,jieci,
	totalScore,evaluationLevel,experimentalProcess,alrp,discussingOrImproving;
	
	var attendedLecturesSituationList;
	
	//更新图片所在行的所有td列表
	var tdList=updateImage.parents("tr").children();
	//下一张更新图片
	var nextBrotherUpdateImage=updateImageList.eq(updateImageList.index(updateImage)+1);
	//当前更新图片和下一张更新图片之间的所在行
	var nextAllTr=updateImage.parents("tr").nextUntil(nextBrotherUpdateImage.parents("tr"));
	
	//获取表格数据
	id=tdList.eq(1).text();
	type=tdList.eq(2).text();
	courseSelectionId=tdList.eq(3).text();
	supervisorId=tdList.eq(4).text();
	teachingSection=tdList.eq(8).text();
	classId=tdList.eq(9).text();
	numOfClass=tdList.eq(11).text();
	actualNum=tdList.eq(12).text();
	lateNum=tdList.eq(13).text();
	leavingEarlyNum=tdList.eq(14).text();
	date=tdList.eq(15).text();
	weekly=tdList.eq(16).text();
	week=tdList.eq(17).text();
	jieci=tdList.eq(18).text();
	
	attendedLecturesSituationList=[];
	attendedLecturesSituationList.push({attendedLecturesItems:{id:tdList.eq(19).text(),
		content:tdList.eq(20).text(),score:tdList.eq(21).text()},evaluationScore:tdList.eq(22).text(),
		remarks:tdList.eq(23).text()});
	nextAllTr.each(function(index){
		var tr=nextAllTr.eq(index);
		var tdList=tr.children();
		attendedLecturesSituationList.push({attendedLecturesItems:{id:tdList.eq(0).text(),
			content:tdList.eq(1).text(),score:tdList.eq(2).text()},evaluationScore:tdList.eq(3).text(),
			remarks:tdList.eq(4).text()});
	});
	
	totalScore=tdList.eq(24).text();
	evaluationLevel=tdList.eq(25).text();
	experimentalProcess=tdList.eq(26).text();
	alrp=tdList.eq(27).text();
	discussingOrImproving=tdList.eq(28).text();
	
	
	//将获取的数据放入更新区域中
	uIdInput.val(id);
	uCourseSelectionIdInput.val(courseSelectionId);
	uTypeSelect.val(type);
	uSupervisorIdInput.val(supervisorId);
	uTeachingSectionInput.val(teachingSection);
	uClassIdInput.val(classId);
	uNumOfClassInput.val(numOfClass);
	uActualNumInput.val(actualNum);
	uLateNumInput.val(lateNum);
	uLeavingEarlyNumInput.val(leavingEarlyNum);
	uDateInput.val(date);
	uWeeklyInput.val(weekly);
	uWeekSelect.val(week);
	uJieciInput.val(jieci);
	
	//移除最后五行数据
	var dom=updateDataTab.find("tr").filter(":nth-last-child(6)").nextAll();
	dom.remove();
	for(var i=0;i<attendedLecturesSituationList.length;i++){
		var attendedLecturesSituation=attendedLecturesSituationList[i];
		var attendedLecturesItems=attendedLecturesSituation.attendedLecturesItems;
		updateDataTab.append('<tr><td>'+attendedLecturesItems.id+'</td><td colspan="2">'+attendedLecturesItems.content+
				'</td><td>'+attendedLecturesItems.score+'</td><td><input class="evaluationScore" type="number" max="10" maxlength="3" '+ 
				'value='+attendedLecturesSituation.evaluationScore+'></td><td><textarea class="remarks" rows="3" cols="30">'+
				attendedLecturesSituation.remarks+'</textarea></td></tr>');
		
		
	}
	updateDataTab.append(dom);
	
	uTotalScoreInput.val(totalScore);
	uEvaluationLevelInput.val(evaluationLevel);
	uExperimentalProcessTextarea.val(experimentalProcess);
	uAlrpTextarea.val(alrp);
	uDiscussingOrImprovingTextarea.val(discussingOrImproving);
	
	//注册新添加的评价分数、备注<input>和更新、取消<button>
	uEvaluationScoreInputList=updateDataTab.find("input[class='evaluationScore']");
	uRemarksTextareaList=updateDataTab.find("textarea[class='remarks']");
	
	
	uEvaluationScoreInputList.blur(showTotalScoreAndEvaluationLevelWhenWritedAllScore);
	uRemarksTextareaList.blur(showTotalScoreAndEvaluationLevelWhenWritedAllScore);
	
	updateBtn.click(function(){
		//验证更新数据区域中的表单数据是否正确
		if(!verifyUpdateAreaData()){  return; }
		//确认提交数据
		if(confirm("确认提交？")==false){  return; }
		submitUpdateAreaData();
	});
	
	cancelBtn.click(function(){
		//清除更新区域数据，显示查询区域并隐藏更新区域
		clearUpdateAreaData();
		queryDataArea.show();
		updateDataArea.hide();
	});
	
	
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\d+$/
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
	var vJieci=/^(第)?\d{1,2}-\d{1,2}(节)?$/;
	var vEvaluationScore=/^\d{1,2}$/;
	var vRemarks=/^.*$/;
	var vTotalScore=/^\d{1,3}$/;
	var vEvaluationLevel=/^[A-D]$/;
	var vExperimentalProcess=/^.*$/;
	var valrp=/^.*$/;
	var vDiscussingOrImproving=/^.*$/;
	
	var text="";
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		text+="听课编号不规范\n"; 
		flag=false;
	}
	if(!vCourseSelectionId.test(uCourseSelectionIdInput.val())){ 
		text+="课程编号不规范\n"; 
		flag=false;
	}
	if(!vType.test(uTypeSelect.val())){ 
		text+="请选择听课类型\n"; 
		flag=false;
	}
	if(!vSupervisorId.test(uSupervisorIdInput.val())){ 
		text+="督导工号不规范\n"; 
		flag=false;
	}						
	if(!vTeachingSection.test(uTeachingSectionInput.val())){ 
		text+="教学章节不能为空\n"; 
		flag=false;
	}
	if(!vClazzId.test(uClassIdInput.val())){ 
		text+="班级编号不规范\n"; 
		flag=false;
	}
	if(!vNumOfClass.test(uNumOfClassInput.val())){ 
		text+="应到人数不能为空\n"; 
		flag=false;
	}
	if(!vActualNum.test(uActualNumInput.val())){ 
		text+="实到人数不能为空\n"; 
		flag=false;
	}
	if(!vLateNum.test(uLateNumInput.val())){ 
		text+="迟到人数不能为空\n"; 
		flag=false;
	}
	if(!vLeavingEarlyNum.test(uLeavingEarlyNumInput.val())){ 
		text+="早退人数不能为空\n"; 
		flag=false;
	}
	if(!vWeekly.test(uWeeklyInput.val())){ 
		text+="周次不能为空\n"; 
		flag=false;
	}
	if(!vWeek.test(uWeekSelect.val())){ 
		text+="星期不能为空\n"; 
		flag=false;
	}
	if(!vJieci.test(uJieciInput.val())){ 
		text+="节次不规范\n"; 
		flag=false;
	}
	
	uEvaluationScoreInputList.each(function(index,ele){
		var uEvaluationScoreInput=uEvaluationScoreInputList.eq(index);
		if(!vEvaluationScore.test(uEvaluationScoreInput.val())){ 
			text+=index+".评价分数为空\n"; 
			flag=false;
		}
	});
	
	uRemarksTextareaList.each(function(index,ele){
		var uRemarksTextarea=uRemarksTextareaList.eq(index);
		if(!vRemarks.test(uRemarksTextarea.val())){ 
			text+=index+".备注不规范\n"; 
			flag=false;
		}
	});
	
	if(!vTotalScore.test(uTotalScoreInput.val())){ 
		text+="总分数区间应为[0-100]\n"; 
		flag=false;
	}
	if(!vEvaluationLevel.test(uEvaluationLevelInput.val())){ 
		text+="评价等级应为[A-D]\n"; 
		flag=false;
	}
	if(!vExperimentalProcess.test(uExperimentalProcessTextarea.val())){ 
		text+="实验过程记录不能为空\n"; 
		flag=false;
	}
	if(!valrp.test(uAlrpTextarea.val())){ 
		text+="值得肯定、学习、借鉴或推广不能为空\n"; 
		flag=false;
	}
	if(!vDiscussingOrImproving.test(uDiscussingOrImprovingTextarea.val())){ 
		text+="值得商榷或改进不能为空\n"; 
		flag=false;
	}
	if(flag==false) { alert(text); }
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,courseSelectionId,type,supervisorId,teachingSection,classId,
	numOfClass,actualNum,lateNum,leavingEarlyNum,date,weekly,week,jieci,
	totalScore,evaluationLevel,experimentalProcess,alrp,discussingOrImproving;
	
	id=uIdInput.val();
	type=uTypeSelect.val();
	courseSelectionId=uCourseSelectionIdInput.val();
	supervisorId=uSupervisorIdInput.val();
	teachingSection=uTeachingSectionInput.val();
	classId=uClassIdInput.val();
	actualNum=uActualNumInput.val();
	lateNum=uLateNumInput.val();
	leavingEarlyNum=uLeavingEarlyNumInput.val();
	date=uDateInput.val();
	weekly=uWeeklyInput.val();
	week=uWeekSelect.val();
	jieci=uJieciInput.val();
	
	
	var startTr=updateDataTab.find("td:contains('评价项编号')");
	var endTr=updateDataTab.find("td:contains('总分：')");
	var trList=startTr.parents("tr").nextUntil(endTr.parents("tr"));
	var attendedLecturesSituationList=[];
	
	trList.each(function(index){
		var tr=trList.eq(index);
		var uEvaluationScoreInput=uEvaluationScoreInputList.eq(index);
		var uRemarksTextarea=uRemarksTextareaList.eq(index);
		var tdList=tr.children();
		attendedLecturesSituationList.push({attendedLecturesItems:{id:tdList.eq(0).text()},
			evaluationScore:uEvaluationScoreInput.val(),remarks:uRemarksTextarea.val()});
	});
	
	totalScore=uTotalScoreInput.val();
	evaluationLevel=uEvaluationLevelInput.val();
	experimentalProcess=uExperimentalProcessTextarea.val();
	alrp=uAlrpTextarea.val();
	discussingOrImproving=uDiscussingOrImprovingTextarea.val();
	
	
	$.ajax({
		url:"updateAttendedLectures",
		type:"post",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify({id:id,type:type,courseSelection:{id:courseSelectionId},supervisor:{id:supervisorId},
			teachingSection:teachingSection,clazz:{id:classId},actualNum:actualNum,lateNum:lateNum,
			leavingEarlyNum:leavingEarlyNum,date:date,weekly:weekly,week:week,jieci:jieci,
			attendedLecturesSituationList:attendedLecturesSituationList,totalScore:totalScore,
			evaluationLevel:evaluationLevel,experimentalProcess:experimentalProcess,alrp:alrp,
			discussingOrImproving:discussingOrImproving}),
		success:function(data){
			 if(data!=true){
            	errorMessageP.text(data);
				errorMessageP.css("color","red");
            	return;
            }
			location.reload();
		},
		error:function(xhr){
			alert(xhr.responseText);
		}
	});
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uCourseSelectionIdInput.val("");
	uTypeSelect.val("");
	uSupervisorIdInput.val("");
	uTeachingSectionInput.val("");
	uClassIdInput.val("");
	uNumOfClassInput.val("");
	uActualNumInput.val("");
	uLateNumInput.val("");
	uLeavingEarlyNumInput.val("");
	uDateInput.val("");
	uWeeklyInput.val("");
	uWeekSelect.val("");
	uJieciInput.val("");
	uEvaluationScoreInputList.val("");
	uRemarksTextareaList.val("");
	uTotalScoreInput.val("");
	uEvaluationLevelInput.val("");
	uExperimentalProcessTextarea.val("");
	uAlrpTextarea.val("");
	uDiscussingOrImprovingTextarea.val("");
	
	var dom=updateDataTab.find("tr").filter(":nth-last-child(6)").nextAll();
	//移除多余的数据项
	updateDataTab.find("tr").filter(":nth-child(5)").nextAll().remove();
	//还原最后几项数据
	updateDataTab.append(dom);
	
}


//文档加载完毕调用函数
$(document).ready(function(){
	//初始化变量
	queryDataArea=$(".queryDataArea");
	updateDataArea=$(".updateDataArea");
	advancedSearchUl=$(".advancedSearch");
	queryDataTab=$(".dataTab").eq(0);
	updateDataTab=$(".dataTab").eq(1);
	
	aIdInput=advancedSearchUl.find("input[name='id']");
	aTypeSelect=advancedSearchUl.find("select[name='type']");
	aCourseSelectionIdInput=advancedSearchUl.find("input[name='courseSelectionId']");
	aSupervisorIdInput=advancedSearchUl.find("input[name='supervisorId']");
	aTeacherIdInput=advancedSearchUl.find("input[name='teacherId']");
	aClassIdInput=advancedSearchUl.find("input[name='classId']");


	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uCourseSelectionIdInput=updateDataTab.find("input[name='courseSelectionId']");
	uTypeSelect=updateDataTab.find("select[name='type']");
	uSupervisorIdInput=updateDataTab.find("input[name='supervisorId']");
	uTeachingSectionInput=updateDataTab.find("input[name='teachingSection']");
	uClassIdInput=updateDataTab.find("input[name='classId']");
	uNumOfClassInput=updateDataTab.find("input[name='numOfClass']");	uNumOfClassInput.attr("disabled",true);
	uActualNumInput=updateDataTab.find("input[name='actualNum']");
	uLateNumInput=updateDataTab.find("input[name='lateNum']");
	uLeavingEarlyNumInput=updateDataTab.find("input[name='leavingEarlyNum']");
	uDateInput=updateDataTab.find("input[name='date']");
	uWeeklyInput=updateDataTab.find("input[name='weekly']");
	uWeekSelect=updateDataTab.find("select[name='week']");	uWeekSelect.attr("disabled",true);
	uJieciInput=updateDataTab.find("input[name='jieci']");
	uTotalScoreInput=updateDataTab.find("input[name='totalScore']");
	uEvaluationLevelInput=updateDataTab.find("input[name='evaluationLevel']");
	uExperimentalProcessTextarea=updateDataTab.find("textarea[name='experimentalProcess']");
	uAlrpTextarea=updateDataTab.find("textarea[name='alrp']");
	uDiscussingOrImprovingTextarea=updateDataTab.find("textarea[name='discussingOrImproving']");
	
	errorMessageP=$(".errorMessage");
	
	batchDeleteBtn=$("#batchDeleteBtn");
	searchBtn=$("#searchBtn");
	selectAllChk=$("#selectAllChk");
	
	updateImageList=$(".updateImage");
	deleteImageList=$(".deleteImage");
	
	updateBtn=$("#updateBtn");
	cancelBtn=$("#cancelBtn");
	
	
	
	batchDeleteBtn.click(function(){
		//被选删除列表：checkbox对象列表
		var deleteItemsList;
		//删除id的列表
		var idList=[];
		
		deleteItemsList=$("input[type='checkBox']:not(#selectAllChk)").filter(":checked");
		//没有勾选一个删除checkbox，给出提示
		if(deleteItemsList.length==0){ alert("还未选择删除项！"); return; }
		
		//将所选复选框对应栏的id添加到idList中，发起批量删除请求
		deleteItemsList.each(function(index,ele){
			var deleteItems=deleteItemsList.eq(index);
			var id=deleteItems.parents("tr").children(":nth-child(2)").text();
			idList.push(id);
		});
		if(confirm("确认删除这些数据？")==false) { return; }
		submitDataByForm("deleteAttendedLectures",{idList:idList});
	});
	
	
	searchBtn.click(function(){
		//提交更新区域数据
		submitAdvancedSearchData();
	});
	

	selectAllChk.change(function(){
		//选择所有复选框（不包含类名为selectAllChk的单选框）
		var chkList=$("input[type='checkbox']:not(#selectAllChk)");
		//如果全选框被选中，所有复选框均为选中状态，否则，取消所有复选框选中状态。
		if(selectAllChk.prop("checked")){ chkList.prop("checked",true); }
		else { chkList.prop("checked",false); }
	});

	
	updateImageList.each(function(index,ele){
		var updateImage=updateImageList.eq(index);
		updateImage.click(function(){
			//从表格中提取数据
			getUpdateAreaDataFromTable(updateImage);
			//显示查询区域，隐藏更新区域数据
			queryDataArea.hide();
			updateDataArea.show();
		});
	});
	
	
	deleteImageList.each(function(index,ele){
		var deleteImage=deleteImageList.eq(index);
		deleteImage.click(function(){
			if(confirm("确认删除该数据？")) { 
				submitDataByForm("deleteAttendedLectures",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
			}
		});
	});
});