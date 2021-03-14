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
var aIdInput,aDateInput,aStartingTimeInput,aEndingTimeSelect,aTourInspectionAreaInput,aSupervisorIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uDateInput,uStartingTimeInput,uEndingTimeSelect,uTourInspectionAreaInput,
uSupervisorIdInput,uSituationTextareaList;
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
	var id,date,startingTime,endingTime,tourInspectionArea,supervisorId;
	
	id=aIdInput.val();
	date=aDateInput.val();
	startingTime=aStartingTimeInput.val();
	endingTime=aEndingTimeInput.val();
	tourInspectionArea=aTourInspectionAreaInput.val();
	supervisorId=aSupervisorIdInput.val();
	
	id=id==""?0:id;
	date=date==""?null:date;
	startingTime=startingTime==""?0:startingTime;
	endingTime=endingTime==""?0:endingTime;
		
	submitDataByForm("queryTourInspection",{id:id,date:date,startingTime:startingTime,endingTime:endingTime,
		tourInspectionArea:tourInspectionArea,supervisorId:supervisorId});
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,date,startingTime,endingTime,tourInspectionArea,supervisorId;
	var tourInspectionSituationList=[];
	
	var firstTr=updateImage.parents("tr");
	var nextUpdateImageTr=updateImageList.eq(updateImageList.index(updateImage)+1).parents("tr");
	var trList=firstTr.nextUntil(nextUpdateImageTr).add(firstTr);
	var tourInspectionItemsIdTr=updateDataArea.find("td:contains(巡考项编号)").parents("tr");
	
	//获取查询区域表格数据
	trList.each(function(index){
		var tdList=trList.eq(index).children();
		if(index==0){
			id=tdList.eq(1).text();
			date=tdList.eq(2).text();
			startingTime=tdList.eq(3).text();
			endingTime=tdList.eq(4).text();
			tourInspectionArea=tdList.eq(5).text();
			supervisorId=tdList.eq(6).text();
			tourInspectionSituationList.push({tourInspectionItems:{id:tdList.eq(8).text(),content:tdList.eq(9).text()},situation:tdList.eq(10).text()});
		}else{
			tourInspectionSituationList.push({tourInspectionItems:{id:tdList.eq(0).text(),content:tdList.eq(1).text()},situation:tdList.eq(2).text()});
		}
		
	});
	
	//将获取到的数据放入更新区域中
	uIdInput.val(id);
	uDateInput.val(date);
	uStartingTimeInput.val(startingTime);
	uEndingTimeInput.val(endingTime);
	uTourInspectionAreaInput.val(tourInspectionArea);
	uSupervisorIdInput.val(supervisorId);
	
	var nextTr=tourInspectionItemsIdTr;
	for(var index=0;index<tourInspectionSituationList.length;index++){
		var tourInspectionSituation=tourInspectionSituationList[index];
		var html="<tr><td>"+tourInspectionSituation['tourInspectionItems']['id']+"</td><td colspan='3'>"+
			tourInspectionSituation['tourInspectionItems']['content']+"</td><td colspan='2'><textarea name='situation' rows='3' cols='50'>"+
			tourInspectionSituation['situation']+"</textarea></td></tr>";
		
		nextTr.after(html);
		nextTr=nextTr.next();
	}
	
	//初始化suggestTextareaList
	uSituationTextareaList=updateDataArea.find("textarea[name='situation']");
	
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	
	var vId=/^\d+$/;
	var vDate=/^\d{4}-\d{2}-\d{2}$/;
	var vStartingTime=/^\d{1,2}$/;
	var vEndingTime=/^\d{1,2}$/;
	var vTourInspectionArea=/^.+$/;
	var vSupervisorId=/^.+$/;
	var vSituation=/^.+$/;

	var text="";
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		text+="请输入巡考编号\n"; 
		flag=false;
	}
	if(!vDate.test(uDateInput.val())){ 
		text+="请输入巡考日期\n"; 
		flag=false;
	}
	if(!vStartingTime.test(uStartingTimeInput.val())){ 
		text+="请输入起时\n"; 
		flag=false;
	}
	if(!vEndingTime.test(uEndingTimeInput.val())){ 
		text+="请输入终时\n"; 
		flag=false;
	}
	if(!vTourInspectionArea.test(uTourInspectionAreaInput.val())){ 
		text+="请输入巡视区域\n"; 
		flag=false;
	}
	if(!vSupervisorId.test(uSupervisorIdInput.val())){ 
		text+="请输入督导工号\n"; 
		flag=false;
	}
	uSituationTextareaList.each(function(index){
		var uSituationTextarea=uSituationTextareaList.eq(index);
		if(!vSituation.test(uSituationTextarea.val())){ 
			text+=index+1+".具体情况记录为空\n"; 
			flag=false;
	}
	});
	
	
	if(flag==false) { alert(text); return; }
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,date,startingTime,endingTime,tourInspectionArea,supervisorId;
	var tourInspectionSituationList=[];
	
	var tourInspectionItemsTr=updateDataTab.find("td:contains(巡考项编号)").parents("tr");
	var updateBtnTr=updateBtn.parents("tr");
	var trList=tourInspectionItemsTr.nextUntil(updateBtnTr);
	
	id=uIdInput.val();
	date=uDateInput.val();
	startingTime=uStartingTimeInput.val();
	endingTime=uEndingTimeInput.val();
	tourInspectionArea=uTourInspectionAreaInput.val();
	supervisorId=uSupervisorIdInput.val();

	uSituationTextareaList.each(function(index){
		var uSituationTextarea=uSituationTextareaList.eq(index);
		var tdList=uSituationTextarea.parents("tr").children();
		tourInspectionSituationList.push({tourInspectionItems:{id:tdList.eq(0).text()},situation:uSituationTextarea.val()});
	});
	
	$.ajax({
        type: "POST",
        url: "updateTourInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
			date:date,
        	startingTime:startingTime,
        	endingTime:endingTime,
        	tourInspectionArea:tourInspectionArea,
			supervisor:{id:supervisorId},
			tourInspectionSituationList:tourInspectionSituationList
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryTourInspection";
        },
        error: function (xhr) {
             alert(xhr.responseText);
        }
    });
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uDateInput.val("");
	uStartingTimeInput.val("");
	uEndingTimeInput.val("");
	uTourInspectionAreaInput.val("");
	
	//清除常巡考情况tr栏
	uSituationTextareaList.parents("tr").remove();
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
	aDateInput=advancedSearchUl.find("input[name='date']");
	aStartingTimeInput=advancedSearchUl.find("input[name='startingTime']");
	aEndingTimeInput=advancedSearchUl.find("input[name='endingTime']");
	aTourInspectionAreaInput=advancedSearchUl.find("input[name='tourInspectionArea']");
	aSupervisorIdInput=advancedSearchUl.find("input[name='supervisorId']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uDateInput=updateDataTab.find("input[name='date']");	uDateInput.attr("disabled",true);
	uStartingTimeInput=updateDataTab.find("input[name='startingTime']");
	uEndingTimeInput=updateDataTab.find("input[name='endingTime']");
	uTourInspectionAreaInput=updateDataTab.find("input[name='tourInspectionArea']");
	uSupervisorIdInput=updateDataTab.find("input[name='supervisorId']");	uSupervisorIdInput.attr("disabled",true);
	
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
		submitDataByForm("deleteTourInspection",{idList:idList});
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
				submitDataByForm("deleteTourInspection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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