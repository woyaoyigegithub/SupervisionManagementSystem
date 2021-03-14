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
var aIdInput,aClassIdInput,aWeeklyInput,aWeekSelect,aDateInput;
//指的是类名updateArea区域中的input。
var uIdInput,uWeeklyInput,uInspectionAreaArrangementSituationIdInput,uWeekSelect,uDateInput,
uSituationTextareaList,uSuggestTextareaList,uCourseSelectionIdInputList,
uClassIdInputList,uNormalTimeInputList,uActualTimeInputList;
//更新区域错误消息显示
var errorMessageP;
//追加一行和删除最后一行button
var appendRowBtn,deleteLastRowBtn;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,inspectionAreaArrangementSituationId,weekly,week,date;
	
	id=aIdInput.val();
	inspectionAreaArrangementSituationId=aInspectionAreaArrangementSituationIdInput.val();
	weekly=aWeeklyInput.val();
	week=aWeekSelect.val();
	date=aDateInput.val();
	
	id=id==""?0:id;
	weekly=weekly==""?0:weekly;
	date=date==""?null:date;
		
	submitDataByForm("queryDailyInspection",{id:id,inspectionAreaArrangementSituationId:inspectionAreaArrangementSituationId,
		weekly:weekly,week:week,date:date});
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,weekly,inspectionAreaArrangementSituationId,week,date;
	var dailyInspectionSituationList=[];
	var leaveClassAheadOfTimeSituationList=[];
	
	var firstTr=updateImage.parents("tr");
	var trList=firstTr.nextUntil(updateImageList.eq(updateImageList.index(updateImage)+1).parents("tr")).add(firstTr);
	var leaveClassAheadOfTimeSituationTabTr=trList.find("td:contains(教师提前下课情况记录表)").parent();
	//日常巡查项情况trList
	var dailyInspectionSituationTrList=firstTr.nextUntil(leaveClassAheadOfTimeSituationTabTr).add(firstTr);
	//提前下课情况trList
	var leaveClassAheadOfTimeSituationTrList=leaveClassAheadOfTimeSituationTabTr.next().nextUntil(trList[trList.length-1]).add(trList[trList.length-1]);
	
	
	//将所有行关键数据记录在dailyInspectionSituationTrList中
	for(var i=0;i<dailyInspectionSituationTrList.length;i++){
		var tdList=dailyInspectionSituationTrList.eq(i).children();
		//首行数据
		if(i==0){
			id=tdList.eq(1).text();
			weekly=tdList.eq(2).text();
			inspectionAreaArrangementSituationId=tdList.eq(3).text();
			week=tdList.eq(6).text();
			date=tdList.eq(8).text();
			
			dailyInspectionSituationList.push({dailyInspectionItems:{id:tdList.eq(9).text(),content:tdList.eq(10).text()},situation:tdList.eq(11).text(),
				suggest:tdList.eq(12).text()});
			continue;
		}
		dailyInspectionSituationList.push({dailyInspectionItems:{id:tdList.eq(0).text(),content:tdList.eq(1).text()},situation:tdList.eq(2).text(),
			suggest:tdList.eq(3).text()});
	}
	
	//将所有行关键数据记录在leaveClassAheadOfTimeSituationTrList中
	for(var i=0;i<leaveClassAheadOfTimeSituationTrList.length;i++){
		var tdList=leaveClassAheadOfTimeSituationTrList.eq(i).children();
		leaveClassAheadOfTimeSituationList.push({courseSelection:{id:tdList.eq(0).text()},classId:tdList.eq(5).text(),
			normalTime:tdList.eq(9).text(),actualTime:tdList.eq(10).text()});
	}
	
	
	//给更新区域赋值
	uIdInput.val(id);
	uWeeklyInput.val(weekly);
	uInspectionAreaArrangementSituationIdInput.val(inspectionAreaArrangementSituationId);
	uWeekSelect.val(week);
	uDateInput.val(date);
	
	var indexTr=updateDataArea.find("td:contains(序号)").parent("tr");
	var nextTr=indexTr;
	for(var i=0;i<dailyInspectionSituationList.length;i++){
		var dailyInspectionSituation=dailyInspectionSituationList[i];
		var dailyInspectionSituationHtml=
		"<tr>"+
			"<td>"+dailyInspectionSituation['dailyInspectionItems']['id']+"</td><td colspan='4'>"+dailyInspectionSituation['dailyInspectionItems']['content']+"</td><td colspan='3'><textarea name='situation' rows='3' cols='40'>"+dailyInspectionSituation['situation']+"</textarea></td>"+
			"<td colspan='2'><textarea name='suggest' rows='3' cols='35'>"+dailyInspectionSituation['suggest']+"</textarea></td>"+
		"</tr>";
		
		nextTr.after(dailyInspectionSituationHtml);
		nextTr=nextTr.next();
	}
	
	var CourseSelectionIdTr=updateDataArea.find("td:contains(选课编号)").parent();
	nextTr=CourseSelectionIdTr;
	for(var i=0;i<leaveClassAheadOfTimeSituationList.length;i++){
		var leaveClassAheadOfTimeSituation=leaveClassAheadOfTimeSituationList[i];
		var leaveClassAheadOfTimeSituationHtml="<tr><td colspan='4'><input type='text' name='courseSelectionId' value='"+leaveClassAheadOfTimeSituation['courseSelection']['id']+"'></td>"+
			"<td colspan='2'><input type='number' name='classId' value='"+leaveClassAheadOfTimeSituation['classId']+"'></td><td colspan='2'><input type='time' name='normalTime' value='"+leaveClassAheadOfTimeSituation['normalTime']+"'></td>"+
			"<td colspan='2'><input type='time' name='actualTime' value='"+leaveClassAheadOfTimeSituation['actualTime']+"'></td></tr>";
		
		nextTr.after(leaveClassAheadOfTimeSituationHtml);
		nextTr=nextTr.next();
	}
	
	//初始化情况，建议，选课编号等控件
	uSituationTextareaList=updateDataTab.find("textarea[name='situation']");
	uSuggestTextareaList=updateDataTab.find("textarea[name='suggest']");
	uCourseSelectionIdInputList=updateDataTab.find("input[name='courseSelectionId']");
	uClassIdInputList=updateDataTab.find("input[name='classId']");
	uNormalTimeInputList=updateDataTab.find("input[name='normalTime']");
	uActualTimeInputList=updateDataTab.find("input[name='actualTime']");
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	
	var vId=/^\d+$/;
	var vInspectionAreaArrangementSituationId=/^\d+$/;
	var vWeekly=/^\d{1,2}$/;
	var vWeek=/^[一二三四五六日]$/;
	var vDate=/^\d{4}-\d{2}-\d{2}$/;
	var vSituation=/^.+$/;
	var vSuggest=/^.+$/;
	var vCourseSelectionId=/^\(\d{4}-\d{4}-[12]\)-\d{10}-\d{7}-\d+$/;
	var vClassId=/^\d{10}$/;
	var vNormalTime=/^\d{2}:\d{2}:\d{2}$/;
	var vActualTime=/^\d{2}:\d{2}:\d{2}$/;

	var text="";
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		text+="请输入日常巡查编号\n"; 
		flag=false;
	}
	if(!vInspectionAreaArrangementSituationId.test(uInspectionAreaArrangementSituationIdInput.val())){ 
		text+="请输入督查区域人员安排编号\n"; 
		flag=false;
	}
	if(!vWeekly.test(uWeeklyInput.val())){ 
		text+="请输入周次\n"; 
		flag=false;
	}
	if(!vWeek.test(uWeekSelect.val())){ 
		text+="请选择星期\n"; 
		flag=false;
	}
	if(!vDate.test(uDateInput.val())){ 
		text+="请输入巡查日期\n"; 
		flag=false;
	}
	for(var i=0;i<uSituationTextareaList.length;i++){
		var uSituationTextarea=uSituationTextareaList.eq(i);
		var uSuggestTextarea=uSuggestTextareaList.eq(i);
		if(!vSituation.test(uSituationTextarea.val())){
			text+=i+1+".巡查情况记录为空\n";
			flag=false;
		}
		if(!vSuggest.test(uSuggestTextarea.val())){
			text+=i+1+".建议为空\n"; 
			flag=false;
		}
	}
	for(var i=0;i<uCourseSelectionIdInputList.length;i++){
		var uCourseSelectionIdInput=uCourseSelectionIdInputList.eq(i);
		var uClassIdInput=uClassIdInputList.eq(i);
		var uNormalTimeInput=uNormalTimeInputList.eq(i);
		var uActualTimeInput=uActualTimeInputList.eq(i);
		
		//临时记录
		var temText="";
		//本行填写数据数
		var flag0=0;
		
		if(uCourseSelectionIdInput.val().trim()==""){
			temText+=i+1+".选课编号为空\n";
		}else if(!vCourseSelectionId.test(uCourseSelectionIdInput.val())){
			flag0+=1;
			temText+=i+1+".选课编号不规范\n";
		}else{
			flag0+=1;
		}
		
		if(uClassIdInput.val().trim()==""){
			temText+=i+1+".班级编号为空\n";
		}else if(!vClassId.test(uClassIdInput.val())){
			flag0+=1;
			temText+=i+1+".班级编号为10位数\n";
		}else{
			flag0+=1;
		}
		
		if(uNormalTimeInput.val().trim()==""){
			temText+=i+1+".正常下课时间为空\n";
		}else if(!vNormalTime.test(uNormalTimeInput.val())){
			flag0+=1;
			temText+=i+1+".正常下课时间为空\n";
		}else{
			flag0+=1;
		}
		
		if(uActualTimeInput.val().trim()==""){
			temText+=i+1+".实际下课时间为空\n";
		}else if(!vActualTime.test(uActualTimeInput.val())){
			flag0+=1;
			temText+=i+1+".实际下课时间为空\n";
		}else{
			flag0+=1;
		}
		
		if(flag0>0 && flag0<4){ text+=temText;	flag=false; }
	}
	
	//判断是否有
	if(flag==false) { alert(text); return; }
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,inspectionAreaArrangementSituationId,weekly,week,date;
	var dailyInspectionSituationList=[];
	var leaveClassAheadOfTimeSituationList=[];
	
	id=uIdInput.val();
	inspectionAreaArrangementSituationId=uInspectionAreaArrangementSituationIdInput.val();
	weekly=uWeeklyInput.val();
	week=uWeekSelect.val();
	date=uDateInput.val();
	
	for(var i=0;i<uSituationTextareaList.length;i++){
		var uSituationTextarea=uSituationTextareaList.eq(i);
		var uSuggestTextarea=uSuggestTextareaList.eq(i);
		var dailyInspectionItemsId=uSituationTextarea.parents("tr").children("td:eq(0)").text();
		
		dailyInspectionSituationList.push({dailyInspectionItems:{id:dailyInspectionItemsId},situation:uSituationTextarea.val(),suggest:uSuggestTextarea.val()});
	}
	
	for(var i=0;i<uCourseSelectionIdInputList.length;i++){
		var uCourseSelectionIdInput=uCourseSelectionIdInputList.eq(i);
		var uClassIdInput=uClassIdInputList.eq(i);
		var uNormalTimeInput=uNormalTimeInputList.eq(i);
		var uActualTimeInput=uActualTimeInputList.eq(i);
		
		if(uCourseSelectionIdInput.val()!="" && uClassIdInput.val()!="" && uNormalTimeInput.val()!="" && uActualTimeInput.val()!=""){
			leaveClassAheadOfTimeSituationList.push({courseSelection:{id:uCourseSelectionIdInput.val()},clazz:{id:uClassIdInput.val()},
				normalTime:uNormalTimeInput.val(),actualTime:uActualTimeInput.val()});
		}
	}
	
	$.ajax({
        type: "POST",
        url: "updateDailyInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	weekly:weekly,
        	inspectionAreaArrangementSituation:{id:inspectionAreaArrangementSituationId},
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


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uInspectionAreaArrangementSituationIdInput.val("");
	uWeeklyInput.val("");
	uWeekSelect.val("");
	uDateInput.val("");
	uSituationTextareaList.val("");
	uSuggestTextareaList.val("");
	uCourseSelectionIdInputList.val("");
	uClassIdInputList.val("");
	uNormalTimeInputList.val("");
	uActualTimeInputList.val("");
	
	//清除常巡查情况记录和教师提前下课情况记录栏
	uSituationTextareaList.parents("tr").remove();
	uCourseSelectionIdInputList.parents("tr").remove();
}


//重新初始化选课id，上课班级id、正常下课时间、实际下课时间input
function regesterCourseSelectionIdInputListAndSoOn(){
	uCourseSelectionIdInputList=$("input[name='courseSelectionId']");
	uClassIdInputList=$("input[name='classId']");
	uNormalTimeInputList=$("input[name='normalTime']");
	uActualTimeInputList=$("input[name='actualTime']");

	//每个选课编号框失焦触发事件
	uCourseSelectionIdInputList.each(function(index){
		var uCourseSelectionIdInput=uCourseSelectionIdInputList.eq(index);
		var uClassIdInput=uClassIdInputList.eq(index);
		var uNormalTimeInput=uNormalTimeInputList.eq(index);
		uCourseSelectionIdInput.blur(function(){
			
			//清除上一次错误消息
			uCourseSelectionIdInput.next().remove();
			errorMessageP.text("");
			
			var vCourseSelection=/^\(\d{4}-\d{4}-[12]\)-\d{10}-\d{7}-\d+$/;
			if(!vCourseSelection.test(uCourseSelectionIdInput.val())){
				uCourseSelectionIdInput.after("<font color='red'>该选课编号不符合规范</font>");
				errorMessageP.text("该选课编号不符合规范");
				return;
			}
			
			$.ajax({
				type: "post",
				url: "queryCourseSelectionById",
				dataType: "json",
				data: {courseSelectionId:uCourseSelectionIdInput.val()},
				success: function(data){
					if(data==null){
						uCourseSelectionIdInput.after("<font color='red'>该选课不存在</font>");
						errorMessageP.text("该选课不存在");
						return;
					}
					if(data.clazz!=null){ uClassIdInput.val(data.clazz.id); }
				},
				error: function(xhr){
					alert(xhr.responseText);
				}
			})
		});
		
	});
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
	aInspectionAreaArrangementSituationIdInput=advancedSearchUl.find("input[name='inspectionAreaArrangementSituationId']");
	aWeeklyInput=advancedSearchUl.find("input[name='weekly']");
	aWeekSelect=advancedSearchUl.find("select[name='week']");
	aDateInput=advancedSearchUl.find("input[name='date']");
	
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true);
	uInspectionAreaArrangementSituationIdInput=updateDataTab.find("input[name='inspectionAreaArrangementSituationId']");
	uInspectionAreaArrangementSituationIdInput.attr("disabled",true);
	uWeeklyInput=updateDataTab.find("input[name='weekly']");
	uWeekSelect=updateDataTab.find("select[name='week']");
	uDateInput=updateDataTab.find("input[name='date']");	uDateInput.attr("disabled",true);
	
	
	
	errorMessageP=$(".errorMessage");
	
	appendRowBtn=$("#appendRowBtn");
	deleteLastRowBtn=$("#deleteLastRowBtn");
	
	batchDeleteBtn=$("#batchDeleteBtn");
	searchBtn=$("#searchBtn");
	selectAllChk=$("#selectAllChk");
	
	updateImageList=$(".updateImage");
	deleteImageList=$(".deleteImage");
	
	updateBtn=$("#updateBtn");
	cancelBtn=$("#cancelBtn");
	
	
	//追加一行button注册点击事件
	appendRowBtn.click(function(){
		//上一行数据行
		var html=appendRowBtn.parents("tr").prev();
		//复制上一行数据并清空所填数据
		appendRowBtn.parents("tr").before(html.prop("outerHTML"));
		appendRowBtn.parents("tr").prev().find("input").val("");
		
		//重新初始化选课编号，上课班级编号，正常下课时间，实际下课时间。
		regesterCourseSelectionIdInputListAndSoOn();
	});
	
	
	//删除最后一行点击事件
	deleteLastRowBtn.click(function(){
		if(uCourseSelectionIdInputList.length==1){
			alert("不能再删除了");
			return;
		}
		appendRowBtn.parents("tr").prev().remove();
		
		//重新初始化选课编号，上课班级编号，正常下课时间，实际下课时间。
		regesterCourseSelectionIdInputListAndSoOn();
	});
	
	
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
		submitDataByForm("deleteDailyInspection",{idList:idList});
	});
	
	
	searchBtn.click(submitAdvancedSearchData);
	

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
				submitDataByForm("deleteDailyInspection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
			}
		});
	});
	
	updateBtn.click(function(){
		//清空上一次检验信息
		$(".errors").hide();
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
	
});