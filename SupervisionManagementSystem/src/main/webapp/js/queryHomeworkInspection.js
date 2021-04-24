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
var aIdInput,aCourseSelectionIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uCourseSelectionIdInput,uAssignmentsOrReportsFenNumInput,uAssignmentsOrReportsCiNumInput,
uCorrectionTimesInput,uSeriousCorrectionInput,uMoreSeriousCorrectionInput,uGeneralCorrectionInput,uPoorCorrectionInput,uRemarksTextarea;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,courseSelectionIdErrorTd,assignmentsOrReportsFenNumErrorTd,assignmentsOrReportsCiNumErrorTd,
correctionTimesErrorTd,seriousCorrectionErrorTd,moreSeriousCorrectionErrorTd,generalCorrectionErrorTd,poorCorrectionErrorTd,remarksErrorTd;
//错误消息标签
var errorMessageP;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,courseSelectionId;
	
	id=aIdInput.val();
	courseSelectionId=aCourseSelectionIdInput.val();
	
	id=id==""?0:id;
	
	submitDataByForm("queryHomeworkInspection",{id:id,courseSelectionId:courseSelectionId});
	
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,courseSelectionId,assignmentsOrReportsFenNum,assignmentsOrReportsCiNum,correctionTimes,
	seriousCorrection,moreSeriousCorrection,generalCorrection,poorCorrection,remarks;
	
	var tdList=updateImage.parents("tr").children();
	
	id=tdList.eq(1).text();
	courseSelectionId=tdList.eq(2).text();
	assignmentsOrReportsFenNum=tdList.eq(7).text();
	assignmentsOrReportsCiNum=tdList.eq(8).text();
	correctionTimes=tdList.eq(9).text();
	seriousCorrection=tdList.eq(10).text();
	moreSeriousCorrection=tdList.eq(11).text();
	generalCorrection=tdList.eq(12).text();
	poorCorrection=tdList.eq(13).text();
	remarks=tdList.eq(14).text();
	
	uIdInput.val(id);
	uCourseSelectionIdInput.val(courseSelectionId);
	uAssignmentsOrReportsFenNumInput.val(assignmentsOrReportsFenNum);
	uAssignmentsOrReportsCiNumInput.val(assignmentsOrReportsCiNum);
	uCorrectionTimesInput.val(correctionTimes);
	uSeriousCorrectionInput.val(seriousCorrection);
	uMoreSeriousCorrectionInput.val(moreSeriousCorrection);
	uGeneralCorrectionInput.val(generalCorrection);
	uPoorCorrectionInput.val(poorCorrection);
	uRemarksTextarea.val(remarks);
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//vId,vCourseSelectionId等为验证规范。
	var vId=/^\d+$/;
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
	
	if(!vId.test(uIdInput.val())){ 
		idErrorTd.text("选课编号不规范"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vCourseSelectionId.test(uCourseSelectionIdInput.val())){ 
		courseSelectionIdErrorTd.text("选课编号不规范"); 
		courseSelectionIdErrorTd.show();
		flag=false;
	}
	if(!vAssignmentsOrReportsFenNum.test(uAssignmentsOrReportsFenNumInput.val())){ 
		assignmentsOrReportsFenNumErrorTd.text("请填写作业或报告份数"); 
		assignmentsOrReportsFenNumErrorTd.show();
		flag=false;
	}
	if(!vAssignmentsOrReportsCiNum.test(uAssignmentsOrReportsCiNumInput.val())){ 
		assignmentsOrReportsCiNumErrorTd.text("请填写作业或报告次数"); 
		assignmentsOrReportsCiNumErrorTd.show();
		flag=false;
	}
	if(!vCorrectionTimes.test(uCorrectionTimesInput.val())){ 
		correctionTimesErrorTd.text("请填写批改次数"); 
		correctionTimesErrorTd.show();
		flag=false;
	}
	if(!vSeriousCorrection.test(uSeriousCorrectionInput.val())){ 
		seriousCorrectionErrorTd.text("请填写批改认真"); 
		seriousCorrectionErrorTd.show();
		flag=false;
	}
	if(!vMoreSeriousCorrection.test(uMoreSeriousCorrectionInput.val())){ 
		moreSeriousCorrectionErrorTd.text("请填写批改较认真"); 
		moreSeriousCorrectionErrorTd.show();
		flag=false;
	}
	if(!vGeneralCorrection.test(uGeneralCorrectionInput.val())){ 
		generalCorrectionErrorTd.text("请填写批改一般"); 
		generalCorrectionErrorTd.show();
		flag=false;
	}
	if(!vPoorCorrection.test(uPoorCorrectionInput.val())){ 
		poorCorrectionErrorTd.text("请填写批改差"); 
		poorCorrectionErrorTd.show();
		flag=false;
	}
	if(!vRemarks.test(uRemarksTextarea.val())){ 
		remarksErrorTd.text("请填写备注"); 
		remarksErrorTd.show();
		flag=false;
	}
	
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,courseSelectionId,assignmentsOrReportsFenNum,assignmentsOrReportsCiNum,correctionTimes,
	seriousCorrection,moreSeriousCorrection,generalCorrection,poorCorrection,remarks;
	
	id=uIdInput.val();
	courseSelectionId=uCourseSelectionIdInput.val();
	assignmentsOrReportsFenNum=uAssignmentsOrReportsFenNumInput.val();
	assignmentsOrReportsCiNum=uAssignmentsOrReportsCiNumInput.val();
	correctionTimes=uCorrectionTimesInput.val();
	seriousCorrection=uSeriousCorrectionInput.val();
	moreSeriousCorrection=uMoreSeriousCorrectionInput.val();
	generalCorrection=uGeneralCorrectionInput.val();
	poorCorrection=uPoorCorrectionInput.val();
	remarks=uRemarksTextarea.val();
	
	
	$.ajax({
        type: "POST",
        url: "updateHomeworkInspection",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data:JSON.stringify({
        	id:id,
        	courseSelection:{id:courseSelectionId},
        	assignmentsOrReportsFenNum:assignmentsOrReportsFenNum,
        	assignmentsOrReportsCiNum:assignmentsOrReportsCiNum,
			correctionTimes:correctionTimes,
			seriousCorrection:seriousCorrection,
			moreSeriousCorrection:moreSeriousCorrection,
			generalCorrection:generalCorrection,
			poorCorrection:poorCorrection,
			remarks:remarks
        }),
        success: function (data) {
            if(data!=true){ alert(data); errorMessageP.text(data); return; }
            location.href="queryHomeworkInspection";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
	
	
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uCourseSelectionIdInput.val("");
	uAssignmentsOrReportsFenNumInput.val("");
	uAssignmentsOrReportsCiNumInput.val("");
	uCorrectionTimesInput.val("");
	uSeriousCorrectionInput.val("");
	uMoreSeriousCorrectionInput.val("");
	uGeneralCorrectionInput.val("");
	uPoorCorrectionInput.val("");
	uRemarksTextarea.val("");
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
	aCourseSelectionIdInput=advancedSearchUl.find("input[name='courseSelectionId']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uCourseSelectionIdInput=updateDataTab.find("input[name='courseSelectionId']");
	uAssignmentsOrReportsFenNumInput=updateDataTab.find("input[name='assignmentsOrReportsFenNum']");
	uAssignmentsOrReportsCiNumInput=updateDataTab.find("input[name='assignmentsOrReportsCiNum']");
	uCorrectionTimesInput=updateDataTab.find("input[name='correctionTimes']");
	uSeriousCorrectionInput=updateDataTab.find("input[name='seriousCorrection']");
	uMoreSeriousCorrectionInput=updateDataTab.find("input[name='moreSeriousCorrection']");
	uGeneralCorrectionInput=updateDataTab.find("input[name='generalCorrection']");
	uPoorCorrectionInput=updateDataTab.find("input[name='poorCorrection']");
	uRemarksTextarea=updateDataTab.find("textarea[name='remarks']");
	
	idErrorTd=$(".errors").eq(0);
	courseSelectionIdErrorTd=$(".errors").eq(1);
	assignmentsOrReportsFenNumErrorTd=$(".errors").eq(2);
	assignmentsOrReportsCiNumErrorTd=$(".errors").eq(3);
	correctionTimesErrorTd=$(".errors").eq(4);
	seriousCorrectionErrorTd=$(".errors").eq(5);
	moreSeriousCorrectionErrorTd=$(".errors").eq(6);
	generalCorrectionErrorTd=$(".errors").eq(7);
	poorCorrectionErrorTd=$(".errors").eq(8);
	remarksErrorTd=$(".errors").eq(9);
	
	errorMessageP=updateDataArea.find(".errorMessage");

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
		submitDataByForm("deleteHomeworkInspection",{idList:idList});
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
				submitDataByForm("deleteHomeworkInspection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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