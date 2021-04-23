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
var aIdInput,aSchoolYearSelect,aSemesterSelect,aStartingAndEndingWeeksInput,aPublicationTimeInput;
//指的是类名updateArea区域中的input。
var uIdInput,uSchoolYearSelect,uSemesterSelect,uStartingAndEndingWeeksInput,uPublicationTimeInput,
uInspectionAreaArrangementBuildingIdInputList,uAcademicBuildingTextareaList,uInspectorsNumInputList,
uInspectionAreaArrangementSituationIdInputList,uSupervisorIdInputList,uWeekSelectList,uEarlyMiddleLateSelectList;
//更新区域错误消息显示
var errorMessageP;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//校验id,courseId等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\d+$/;
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
	
	
	if(!vId.test(uIdInput.val())){ 
		text+="请选择学年\n"; 
		flag=false;
	}
	if(!vSchoolYear.test(uSchoolYearSelect.val())){ 
		text+="请选择学年\n"; 
		flag=false;
	}
	if(!vSemester.test(uSemesterSelect.val())){ 
		text+="请选择学期\n"; 
		flag=false;
	}
	if(!vStartingAndEndingWeeks.test(uStartingAndEndingWeeksInput.val())){ 
		text+="起止周不规范\n"; 
		flag=false;
	}
	if(!vPublicationTime.test(uPublicationTimeInput.val())){ 
		text+="请输入发布日期\n"; 
		flag=false;
	}
	uAcademicBuildingTextareaList.each(function(index){
		var uAcademicBuildingTextarea=uAcademicBuildingTextareaList.eq(index);
		if(!vAcademicBuilding.test(uAcademicBuildingTextarea.val())){
			text+=(index+1)+".教学楼为空\n";
			flag=false;
		}
	});
	uInspectorsNumInputList.each(function(index){
		var uInspectorsNumInput=uInspectorsNumInputList.eq(index);
		if(!vInspectorsNum.test(uInspectorsNumInput.val())){
			text+=(index+1)+".督查人数为空\n";
			flag=false;
		}
	});
	uSupervisorIdInputList.each(function(index){
		var uSupervisorIdInput=uSupervisorIdInputList.eq(index);
		if(!vSupervisorId.test(uSupervisorIdInput.val())){
			text+=(index+1)+".督导工号为空\n";
			flag=false;
		}
	});
	uWeekSelectList.each(function(index){
		var uWeekSelect=uWeekSelectList.eq(index);
		if(!vWeek.test(uWeekSelect.val())){
			text+=(index+1)+".星期为空\n";
			flag=false;
		}
	});
	uEarlyMiddleLateSelectList.each(function(index){
		var uEarlyMiddleLateSelect=uEarlyMiddleLateSelectList.eq(index);
		if(!vEarlyMiddleLate.test(uEarlyMiddleLateSelect.val())){
			text+=(index+1)+".早中晚为空\n";
			flag=false;
		}
	});
	
	if(flag==false) { alert(text); }
	return flag;
}


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,schoolYear,semester,startingAndEndingWeeks,publicationTime;
	
	id=aIdInput.val();
	schoolYear=aSchoolYearSelect.val();
	semester=aSemesterSelect.val();
	startingAndEndingWeeks=aStartingAndEndingWeeksInput.val();
	publicationTime=aPublicationTimeInput.val();
	
	id=id==""?0:id;
	semester=semester==""?-1:semester;
	
	var data={id:id,schoolYear:schoolYear,semester:semester,startingAndEndingWeeks:startingAndEndingWeeks};
	if(publicationTime!=""){
		data['publicationTime']=publicationTime;
	}
		
	submitDataByForm("queryInspectionAreaArrangement",data);
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,schoolYear,semester,startingAndEndingWeeks,publicationTime;
	var inspectionAreaArrangementBuildingList=[];
	
	//图片所在记录的所有数据行数
	var trList=updateImage.parents("tr").nextUntil(updateImageList.eq(updateImageList.index(updateImage)+1)).add(updateImage.parents("tr"));
	
	//将所有行关键数据记录在inspectionAreaArrangementBuildingList中
	for(var i=0;i<trList.length;i++){
		var tdList=trList.eq(i).children();
		//首行数据
		if(i==0){
			id=tdList.eq(1).text();
			schoolYear=tdList.eq(2).text();
			semester=tdList.eq(3).text();
			startingAndEndingWeeks=tdList.eq(4).text();
			publicationTime=tdList.eq(5).text();
			
			inspectionAreaArrangementBuildingList.push({id:tdList.eq(6).text(),academicBuilding:tdList.eq(7).text(),inspectorsNum:tdList.eq(8).text(),
				inspectionAreaArrangementSituationList:[]});
				
			inspectionAreaArrangementBuildingList[i]['inspectionAreaArrangementSituationList'].push({id:tdList.eq(9).text(),
				supervisor:{id:tdList.eq(10).text()},week:tdList.eq(12).text(),earlyMiddleLate:tdList.eq(13).text()});
			continue;
		}
		//每条inspectionAreaArrangementBuilding处理方式
		if(i%15==0 && i!=0){
			inspectionAreaArrangementBuildingList.push({id:tdList.eq(0).text(),academicBuilding:tdList.eq(1).text(),inspectorsNum:tdList.eq(2).text(),
				inspectionAreaArrangementSituationList:[]});
				
			inspectionAreaArrangementBuildingList[Math.floor(i/15)]['inspectionAreaArrangementSituationList'].push({id:tdList.eq(3).text(),
				supervisor:{id:tdList.eq(4).text()},week:tdList.eq(6).text(),earlyMiddleLate:tdList.eq(7).text()});
			continue;
		}
		//其余inspectionAreaArrangementSituation数据记录方式
		inspectionAreaArrangementBuildingList[Math.floor(i/15)]['inspectionAreaArrangementSituationList'].push({id:tdList.eq(0).text(),
				supervisor:{id:tdList.eq(1).text()},week:tdList.eq(3).text(),earlyMiddleLate:tdList.eq(4).text()});
	}
	
	
	uIdInput.val(id);
	uSchoolYearSelect.val(schoolYear);
	uSemesterSelect.val(semester);
	uStartingAndEndingWeeksInput.val(startingAndEndingWeeks);
	uPublicationTimeInput.val(publicationTime);
	for(var i=0;i<inspectionAreaArrangementBuildingList.length;i++){
		var inspectionAreaArrangementBuilding=inspectionAreaArrangementBuildingList[i];
		var situationListLen=inspectionAreaArrangementBuilding.inspectionAreaArrangementSituationList.length;
		
		uInspectionAreaArrangementBuildingIdInputList.eq(i).val(inspectionAreaArrangementBuilding['id']);
		uAcademicBuildingTextareaList.eq(i).val(inspectionAreaArrangementBuilding['academicBuilding']);
		uInspectorsNumInputList.eq(i).val(inspectionAreaArrangementBuilding['inspectorsNum']);
		for(var j=0;j<situationListLen;j++){
			var inspectionAreaArrangementSituation=inspectionAreaArrangementBuilding.inspectionAreaArrangementSituationList[j];
			uInspectionAreaArrangementSituationIdInputList.eq(i*situationListLen+j).val(inspectionAreaArrangementSituation['id']);
			uSupervisorIdInputList.eq(i*situationListLen+j).val(inspectionAreaArrangementSituation['supervisor']['id']);
			uWeekSelectList.eq(i*situationListLen+j).val(inspectionAreaArrangementSituation['week']);
			uEarlyMiddleLateSelectList.eq(i*situationListLen+j).val(inspectionAreaArrangementSituation['earlyMiddleLate']);
		}
		
		
	}
	
}




//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,schoolYear,semester,startingAndEndingWeeks,publicationTime;
	var inspectionAreaArrangementBuildingList=[];
	
	id=uIdInput.val();
	schoolYear=uSchoolYearSelect.val();
	semester=uSemesterSelect.val();
	startingAndEndingWeeks=uStartingAndEndingWeeksInput.val();
	publicationTime=uPublicationTimeInput.val();
	
	for(var i=0;i<uInspectionAreaArrangementBuildingIdInputList.length;i++){
		var uInspectionAreaArrangementBuildingIdInput=uInspectionAreaArrangementBuildingIdInputList.eq(i);
		var uAcademicBuildingTextarea=uAcademicBuildingTextareaList.eq(i);
		var uInspectorsNumInput=uInspectorsNumInputList.eq(i);
		inspectionAreaArrangementBuildingList.push({id:uInspectionAreaArrangementBuildingIdInput.val(),
			academicBuilding:uAcademicBuildingTextarea.val(),inspectorsNum:uInspectorsNumInput.val(),
			inspectionAreaArrangementSituationList:[]});
			
		for(var j=i*15;j<i*15+uInspectionAreaArrangementSituationIdInputList.length/uInspectionAreaArrangementBuildingIdInputList.length;j++){
			var uInspectionAreaArrangementSituationIdInput=uInspectionAreaArrangementSituationIdInputList.eq(j);
			var uSupervisorIdInput=uSupervisorIdInputList.eq(j);
			var uWeekSelect=uWeekSelectList.eq(j);
			var uEarlyMiddleLateSelect=uEarlyMiddleLateSelectList.eq(j);
			
			inspectionAreaArrangementBuildingList[Math.floor(j/15)]['inspectionAreaArrangementSituationList'][j%15]={
				id:uInspectionAreaArrangementSituationIdInput.val(),supervisor:{id:uSupervisorIdInput.val()==""?null:uSupervisorIdInput.val()},
				week:uWeekSelect.val(),earlyMiddleLate:uEarlyMiddleLateSelect.val()
			}
		
		}
	}
	
	
	$.ajax({
        type: "POST",
        url: "updateInspectionAreaArrangement",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	schoolYear:schoolYear,
        	semester:semester,
        	startingAndEndingWeeks:startingAndEndingWeeks,
			publicationTime:publicationTime,
			inspectionAreaArrangementBuildingList:inspectionAreaArrangementBuildingList
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryInspectionAreaArrangement";
        },
        error: function (message) {
            console.log(message);
        }
    });
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uSchoolYearSelect.val("");
	uSemesterSelect.val("");
	uStartingAndEndingWeeksInput.val("");
	uPublicationTimeInput.val("");
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
	aSchoolYearSelect=advancedSearchUl.find("select[name='schoolYear']");
	aSemesterSelect=advancedSearchUl.find("select[name='semester']");
	aStartingAndEndingWeeksInput=advancedSearchUl.find("input[name='startingAndEndingWeeks']");
	aPublicationTimeInput=advancedSearchUl.find("input[name='publicationTime']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uSchoolYearSelect=updateDataTab.find("select[name='schoolYear']");
	uSemesterSelect=updateDataTab.find("select[name='semester']");
	uStartingAndEndingWeeksInput=updateDataTab.find("input[name='startingAndEndingWeeks']");
	uPublicationTimeInput=updateDataTab.find("input[name='publicationTime']");	uPublicationTimeInput.attr("disabled",true);
	uInspectionAreaArrangementBuildingIdInputList=updateDataTab.find("input[name='inspectionAreaArrangementBuildingId']");	uInspectionAreaArrangementBuildingIdInputList.attr("disabled",true);
	uAcademicBuildingTextareaList=updateDataTab.find("textarea[name='academicBuilding']");
	uInspectorsNumInputList=updateDataTab.find("input[name='inspectorsNum']");
	uInspectionAreaArrangementSituationIdInputList=updateDataTab.find("input[name='inspectionAreaArrangementSituationId']");	uInspectionAreaArrangementSituationIdInputList.attr("disabled",true);
	uSupervisorIdInputList=updateDataTab.find("input[name='supervisorId']");
	uWeekSelectList=updateDataTab.find("select[name='week']");	uWeekSelectList.attr("disabled",true);
	uEarlyMiddleLateSelectList=updateDataTab.find("select[name='earlyMiddleLate']");	uEarlyMiddleLateSelectList.attr("disabled",true);
	
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
		submitDataByForm("deleteInspectionAreaArrangement",{idList:idList});
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
				submitDataByForm("deleteInspectionAreaArrangement",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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