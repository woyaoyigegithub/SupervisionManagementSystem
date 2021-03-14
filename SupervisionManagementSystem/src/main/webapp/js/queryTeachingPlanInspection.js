/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertClass）
 * 2. 将网页中的使用到的标签对象的声明在js文件开头.
 * 3. 自定义函数均要写注释来表明其功能.
 * 4. 函数中的变量均声明在函数体开头处，变量使用驼峰命名法，变量取名要有意义.
 * 5. 当函数中代码量繁多时，可按照功能单独写成一个函数供其调用.
 */


var optionRegExp=/（[ ]*）[\u4e00-\u9fa5a-zA-Z\d/]+/g;
//查询数据区域，更新数据区域
var queryDataArea,updateDataArea;
//高级搜索列表
var advancedSearchUl;
//dataTab1指的是queryDataArea中的dataTab,dataTab2则是updateArea中的dataTab
var queryDataTab,updateDataTab;	

//指的是类名advancedSearch区域中的input。
var aIdInput,aCourseSelectionIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uCourseSelectionIdInput,uSituationSelectList,uDiscussingAndAffirmingTextarea;
//错误消息
var errorMessageP;
//定位到教案检查指标项编号那一行
var teachingPlanInspectionItemsIdTr;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,courseSelectionId,situation,discussingAndAffirming;
	
	id=aIdInput.val();
	courseSelectionId=aCourseSelectionIdInput.val();

	id=id==""?0:id;
	
	submitDataByForm("queryTeachingPlanInspection",{id:id,courseSelectionId:courseSelectionId});
	
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,courseSelectionId,discussingAndAffirming;
	var teachingPlanInspectionSituationList=[];
	
	var trList=updateImage.parents("tr").nextUntil(updateImageList.eq(updateImageList.index(updateImage)+1).parents("tr")).add(updateImage.parents("tr"));
	
	//循环trList，将数据放入teachingPlanInspectionSituationList
	trList.each(function(index){
		var tdList=trList.eq(index).children();
		if(index==0){
			id=tdList.eq(1).text();
			courseSelectionId=tdList.eq(2).text();
			discussingAndAffirming=tdList.eq(13).text();
			
			teachingPlanInspectionSituationList.push({teachingPlanInspectionItems:{id:tdList.eq(9).text(),content:tdList.eq(10).text(),options:tdList.eq(11).text()},
				situation:tdList.eq(12).text()});
		}else{
			teachingPlanInspectionSituationList.push({teachingPlanInspectionItems:{id:tdList.eq(0).text(),content:tdList.eq(1).text(),options:tdList.eq(2).text()},
				situation:tdList.eq(3).text()});
		}
	});
	
	
	uIdInput.val(id);
	uCourseSelectionIdInput.val(courseSelectionId);
	uDiscussingAndAffirmingTextarea.val(discussingAndAffirming);
	
	var nextTr=teachingPlanInspectionItemsIdTr;
	//将获取到的teachingPlanInspectionSituationList转化为html插入到教案检查指标项行的后面
	for(var i=0;i<teachingPlanInspectionSituationList.length;i++){
		var teachingPlanInspectionSituation=teachingPlanInspectionSituationList[i];
		//需添加的html
 		var html="<tr><td>"+teachingPlanInspectionSituation.teachingPlanInspectionItems.id+"</td><td>"+teachingPlanInspectionSituation.teachingPlanInspectionItems.content+"</td><td>"+
 		teachingPlanInspectionSituation.teachingPlanInspectionItems.options+"</td><td><select name='situation'><option value=''></option>";
 		//获取选项列表
 		var optionsArray=teachingPlanInspectionSituation.teachingPlanInspectionItems.options.replace(/▢/g,"").split(" ");
 		for(var j=0;j<optionsArray.length;j++){
 			var options=optionsArray[j];
 			html+="<option"+(teachingPlanInspectionSituation.situation==options?" selected":"")+">"+options+"</option>";
 		}
 		html+="</select></td></tr>";
		nextTr.after(html);
		nextTr=nextTr.next();
	}
	
	//初始化uSituationSelectList
	uSituationSelectList=updateDataTab.find("select[name='situation']");
	uSituationSelectList=updateDataTab.find("select[name='situation']");
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//vId,vCourseSelectionId等为验证规范。
	var vId=/^\d+$/;
	var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
	var vSituation=/^.+$/;
	var vDiscussingAndAffirming=/^.+$/;
	
	//错误消息
	var text="";
	//正确标志
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		text+="教案检查编号为空\n"; 
		flag=false;
	}
	if(!vCourseSelectionId.test(uCourseSelectionIdInput.val())){ 
		text+="选课编号不规范\n"; 
		flag=false;
	}
	uSituationSelectList.each(function(index){
		var uSituationSelect=uSituationSelectList.eq(index);
		if(!vSituation.test(uSituationSelect.val())){ 
			text+=index+1+".选项为空\n"; 
			flag=false;
		}
	});
	if(!vDiscussingAndAffirming.test(uDiscussingAndAffirmingTextarea.val())){ 
		text+="值得商榷和肯定的地方为空\n"; 
		flag=false;
	}
	
	if(flag==false){ alert(text); return; }
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,courseSelectionId,discussingAndAffirming;
	var teachingPlanInspectionSituationList=[];
	var trList=teachingPlanInspectionItemsIdTr.nextUntil(uDiscussingAndAffirmingTextarea.parents("tr"));
	
	id=uIdInput.val();
	courseSelectionId=uCourseSelectionIdInput.val();
	discussingAndAffirming=uDiscussingAndAffirmingTextarea.val();
	uSituationSelectList.each(function(index){
		var uSituationSelect=uSituationSelectList.eq(index);
		var tdList=trList.eq(index).children();
		teachingPlanInspectionSituationList.push({teachingPlanInspectionItems:{id:tdList.eq(0).text()},situation:uSituationSelect.val()});
	});
	
	$.ajax({
        type: "POST",
        url: "updateTeachingPlanInspection",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data:JSON.stringify({
        	id:id,
        	courseSelection:{id:courseSelectionId},
        	teachingPlanInspectionSituationList:teachingPlanInspectionSituationList,
        	discussingAndAffirming:discussingAndAffirming
        }),
        success: function (data) {
            if(data!=true){ alert(data); errorMessageP.text(data); return; }
            location.reload();
        },
        error: function (xhr) {
            alert(xhr);
        }
    });
	
	
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uCourseSelectionIdInput.val("");
	uSituationSelectList.val("");
	uDiscussingAndAffirmingTextarea.val("");
	
	uSituationSelectList.parents("tr").remove();
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
	uCourseSelectionIdInput=updateDataTab.find("input[name='courseSelectionId']");	uCourseSelectionIdInput.attr("disabled",true);
	uDiscussingAndAffirmingTextarea=updateDataTab.find("textarea[name='discussingAndAffirming']");
	
	teachingPlanInspectionItemsIdTr=updateDataTab.find("td:contains(教案检查指标项编号)").parent();
	
	errorMessageP=updateDataTab.find(".errorMessage");
	
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
		submitDataByForm("deleteTeachingPlanInspection",{idList:idList});
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
				submitDataByForm("deleteTeachingPlanInspection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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