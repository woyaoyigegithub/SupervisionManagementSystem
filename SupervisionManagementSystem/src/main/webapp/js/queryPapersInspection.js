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
var aIdInput,aStudentIdInput,aInstructorIdInput,aConsultTeacherIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uStudentIdInput,uInstructorIdInput,uConsultTeacherIdInput,uRemarksTextareaList,
uSituationSelectList,uGeneralCommentsTextarea;
//毕业论文抽查项行
var papersInspectionItemsIdTr
//更新区域错误消息显示
var errorMessageP;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,studentId,instructorId,consultTeacherId,remarks,situation;
	
	id=aIdInput.val();
	studentId=aStudentIdInput.val();
	instructorId=aInstructorIdInput.val();
	consultTeacherId=aConsultTeacherIdInput.val();
	
	id=id==""?0:id;
	studentId=studentId==""?null:studentId;
	instructorId=instructorId==""?0:instructorId;
	consultTeacherId=consultTeacherId==""?0:consultTeacherId;
		
	submitDataByForm("queryPapersInspection",{id:id,studentId:studentId,instructorId:instructorId,consultTeacherId:consultTeacherId});
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,studentId,instructorId,consultTeacherId,remarks,generalComments;
	var papersInspectionSituationList=[];
	
	var firstTr=updateImage.parents("tr");
	var nextUpdateImageTr=updateImageList.eq(updateImageList.index(updateImage)+1).parents("tr");
	var trList=firstTr.nextUntil(nextUpdateImageTr).add(firstTr);
	
	//获取查询区域表格数据
	trList.each(function(index){
		var tdList=trList.eq(index).children();
		if(index==0){
			id=tdList.eq(1).text();
			studentId=tdList.eq(2).text();
			instructorId=tdList.eq(4).text();
			consultTeacherId=tdList.eq(6).text();
			remarks=tdList.eq(8).text();
			generalComments=tdList.eq(14).text();
			papersInspectionSituationList.push({papersInspectionItems:{id:tdList.eq(9).text(),content:tdList.eq(10).text(),
				options:tdList.eq(11).text()},situation:tdList.eq(12).text(),remarks:tdList.eq(13).text()});
		}else{
			papersInspectionSituationList.push({papersInspectionItems:{id:tdList.eq(0).text(),content:tdList.eq(1).text(),
				options:tdList.eq(2).text()},situation:tdList.eq(3).text(),remarks:tdList.eq(4).text()});
		}
		
	});
	
	//将获取到的数据放入更新区域中
	uIdInput.val(id);
	uStudentIdInput.val(studentId);
	uInstructorIdInput.val(instructorId);
	uConsultTeacherIdInput.val(consultTeacherId);
	uRemarksTextareaList.eq(0).val(remarks);
	uGeneralCommentsTextarea.val(generalComments);
	
	var nextTr=papersInspectionItemsIdTr;
	for(var i=0;i<papersInspectionSituationList.length;i++){
		var papersInspectionSituation=papersInspectionSituationList[i];
		var optionArray=papersInspectionSituation['papersInspectionItems']['options'].replace(/▢/g,"").split(" ");
		
		//所需的papersInspectionSituation的html
		var html="<tr><td>"+papersInspectionSituation['papersInspectionItems']['id']+"</td><td>"+
		papersInspectionSituation['papersInspectionItems']['content']+"</td><td>"+
		papersInspectionSituation['papersInspectionItems']['options']+"</td><td><select name='situation'><option value=''></option>";
		for(var j=0;j<optionArray.length;j++){ html+="<option"+(optionArray[j]==papersInspectionSituation['situation']?" selected>":">")+optionArray[j]+"</option>"; }
		html+="</select></td><td><textarea name='remarks'>"+papersInspectionSituation['remarks']+"</textarea></td></tr>";
		//添加在papersInspectionItemsIdTr后面
		nextTr.after(html);
		nextTr=nextTr.next();
	}
	
	
	//初始化suggestTextareaList
	uSituationSelectList=updateDataTab.find("select[name='situation']");
	uRemarksTextareaList=updateDataTab.find("textarea[name='remarks']");
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	
	var vId=/^\d+$/;
	var vStudentId=/^\d{12}$/;
	var vInstructorId=/^\d{7}$/;
	var vConsultTeacherId=/^\d{7}$/;
	var vRemarks=/^.*$/;
	var vSituation=/^.+$/;
	var vGeneralComments=/^.+$/;

	var text="";
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		text+="请输入毕业论文编号\n"; 
		flag=false;
	}
	if(!vStudentId.test(uStudentIdInput.val())){ 
		text+="请输入学生学号\n"; 
		flag=false;
	}
	if(!vInstructorId.test(uInstructorIdInput.val())){ 
		text+="请输入指导教师工号\n"; 
		flag=false;
	}
	if(!vConsultTeacherId.test(uConsultTeacherIdInput.val())){ 
		text+="请输入查阅教师工号\n"; 
		flag=false;
	}
	if(!vRemarks.test(uRemarksTextareaList.eq(0).val())){ 
		text+="请输入备注\n"; 
		flag=false;
	}
	uSituationSelectList.each(function(index){
		var uSituationSelect=uSituationSelectList.eq(index);
		if(!vSituation.test(uSituationSelect.val())){ 
			text+=index+1+".选项为空\n"; 
			flag=false;
		}
	});
	uRemarksTextareaList.not(":eq(0)").each(function(index){
		var uRemarksTextarea=uRemarksTextareaList.eq(index+1);
		if(!vRemarks.test(uRemarksTextarea.val())){ 
			text+=index+1+".备注为空\n"; 
			flag=false;
		}
	});
	if(!vGeneralComments.test(uGeneralCommentsTextarea.val())){ 
		text+="请输入查阅人对此毕业设计（论文）的综合意见\n"; 
		flag=false;
	}
	
	if(flag==false) { alert(text); return; }
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,studentId,instructorId,consultTeacherId,remarks;
	var papersInspectionSituationList=[];
	
	var trList=papersInspectionItemsIdTr.nextUntil(updateBtn.parents("tr"));
	
	id=uIdInput.val();
	studentId=uStudentIdInput.val();
	instructorId=uInstructorIdInput.val();
	consultTeacherId=uConsultTeacherIdInput.val();
	uSituationSelectList.each(function(index){
		var tr=trList.eq(index);
		var uSituationSelect=uSituationSelectList.eq(index);
		var uRemarksTextarea=uRemarksTextareaList.eq(index+1);
		
		papersInspectionSituationList.push({papersInspectionItems:{id:tr.children("td:eq(0)").text()},
			situation:uSituationSelect.val(),remarks:uRemarksTextarea.val()});
	})
	generalComments=uGeneralCommentsTextarea.val();
	
	$.ajax({
        type: "POST",
        url: "updatePapersInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
			student:{id:studentId},
        	instructor:{id:instructorId},
        	consultTeacher:{id:consultTeacherId},
        	remarks:uRemarksTextareaList.eq(0).val(),
        	papersInspectionSituationList:papersInspectionSituationList,
			generalComments:generalComments,
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


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uStudentIdInput.val("");
	uInstructorIdInput.val("");
	uConsultTeacherIdInput.val("");
	uSituationSelectList.val("");
	uRemarksTextareaList.val("");
	uGeneralCommentsTextarea.val("");
	
	//清除毕业论文复查记录
	papersInspectionItemsIdTr.nextUntil(updateBtn.parents("tr").prev()).remove();
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
	aStudentIdInput=advancedSearchUl.find("input[name='studentId']");
	aInstructorIdInput=advancedSearchUl.find("input[name='instructorId']");
	aConsultTeacherIdInput=advancedSearchUl.find("input[name='consultTeacherId']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uStudentIdInput=updateDataTab.find("input[name='studentId']");
	uInstructorIdInput=updateDataTab.find("input[name='instructorId']");
	uConsultTeacherIdInput=updateDataTab.find("input[name='consultTeacherId']");
	uRemarksTextareaList=updateDataTab.find("textarea[name='remarks']");
	uGeneralCommentsTextarea=updateDataTab.find("textarea[name='generalComments']");
	
	papersInspectionItemsIdTr=updateDataTab.find("td:contains(抽查项编号)").parent();
	
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
		submitDataByForm("deletePapersInspection",{idList:idList});
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
				submitDataByForm("deletePapersInspection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
			}
		});
	});
	
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
	
});