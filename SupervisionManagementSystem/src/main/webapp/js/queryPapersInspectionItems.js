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
var aIdInput;
//指的是类名updateArea区域中的input。
var uIdInput,uContentTextarea,uOptionsInputList;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,contentErrorTd,optionsErrorTdList;
//抽查情况选项那一列
var optionsTd;
//追加一行选项button 和 删除最后一行选项button
var appendRowOptionsBtn,deleteLastRowOptionsBtn;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;

//当前选项个数
var count=2;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id;
	
	id=aIdInput.val();
	
	id=id==""?0:id;

	submitDataByForm("queryPapersInspectionItems",{id:id});
	
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,content,options;
	var regExp=/▢[\u4e00-\u9fa5a-zA-Z\d_]+/g;
	
	var tdList=updateImage.parents("tr").children();
	
	id=tdList.eq(1).text();
	content=tdList.eq(2).text();
	options=tdList.eq(3).text();
	
	uIdInput.val(id);
	uContentTextarea.val(content);
	var optionsArray=options.replace(/▢/g,"").split(" ");
	if(optionsArray==null){ return; }
	for(var i=0;i<optionsArray.length;i++){
		var uOptionsInput=uOptionsInputList.eq(i);
		var optionsStr=optionsArray[i];
		
		if(i==0 || i==1){
			uOptionsInput.val(optionsStr);
			continue;
		}
		
		var html="<tr><td>选项"+(++count)+"：</td><td><input type='text' name='options' value='"+optionsStr+"' ></td><td class='errors'></td></tr>";
		optionsTd.attr("rowspan",count);
		appendRowOptionsBtn.parents("tr").before(html);
		//初始化uOptionsInputList和optionsErrorTdList
		uOptionsInputList=$("input[name='options']");
		optionsErrorTdList=$(".errors").not((index)=>{if(index<2){return true;}});
	}
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//vId,vContent等为验证规范。
	var vId=/^\d+$/;
	var vContent=/^.+$/;
	var vOptions=/^[\u4e00-\u9fa5a-zA-Z\d_]+$/;
	//正确标志
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		idErrorTd.text("日常巡查项编号必须是数字组合"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vContent.test(uContentTextarea.val())){ 
		contentErrorTd.text("请输入巡查项内容"); 
		contentErrorTd.show();
		flag=false;
	}
	uOptionsInputList.each(function(index){
		var uOptionsInput=uOptionsInputList.eq(index);
		var optionsErrorTd=optionsErrorTdList.eq(index);
		//非空判断
		if(""==uOptionsInput.val().trim()){
			optionsErrorTd.text("查抽情况选项不允许为空"); 
			optionsErrorTd.show();
			flag=false;
		}
		//字词判断
		else if(!vOptions.test(uOptionsInput.val())){
			optionsErrorTd.text("查抽情况选项只允许汉字、英文字母、数字和下划线组成"); 
			optionsErrorTd.show();
			flag=false;
		}
	});
	
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,content,options="";
	id=uIdInput.val();
	content=uContentTextarea.val();
	uOptionsInputList.each(function(index){
		var uOptionsInput=uOptionsInputList.eq(index);
		if(index==0){ options="▢"+uOptionsInput.val(); }
		else{ options+=" ▢"+uOptionsInput.val(); }
	});
	
	$.ajax({
        type: "POST",
        url: "updatePapersInspectionItems",
        dataType: "json",
        data:{
        	id:id,
        	content:content,
        	options:options
        },
        success: function (data) {
            if(data!=true){ return; }
            location.href="queryPapersInspectionItems";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
	
	
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uContentTextarea.val("");
	uOptionsInputList.val("");
	
	while(count>2){
		uOptionsInputList.eq(--count).parents("tr").remove();
	}
	optionsTd.attr("rowspan",count);
	//初始化optionsInputList和optionsErrorTdList和count
	uOptionsInputList=$("input[name='options']");
	optionsErrorTdList=$(".errors").not((index)=>{if(index<2){return true;}});
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
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uContentTextarea=updateDataTab.find("textarea[name='content']");
	uOptionsInputList=updateDataTab.find("input[name='options']");
	
	idErrorTd=$(".errors").eq(0);
	contentErrorTd=$(".errors").eq(1);
	optionsErrorTdList=$(".errors").not((index)=>{if(index<2){return true;}});
	
	optionsTd=updateDataTab.find("td:contains(抽查情况选项)");

	appendRowOptionsBtn=updateDataTab.find("#appendRowOptionsBtn");
	deleteLastRowOptionsBtn=updateDataTab.find("#deleteLastRowOptionsBtn");
	
	batchDeleteBtn=$("#batchDeleteBtn");
	searchBtn=$("#searchBtn");
	selectAllChk=$("#selectAllChk");
	
	updateImageList=$(".updateImage");
	deleteImageList=$(".deleteImage");
	
	updateBtn=updateDataTab.find("#updateBtn");
	cancelBtn=updateDataTab.find("#cancelBtn");

	appendRowOptionsBtn.click(function(){
		var html="<tr><td>选项"+(++count)+"：</td><td><input type='text' name='options' ></td><td class='errors'></td></tr>";
		
		optionsTd.attr("rowspan",count);
		appendRowOptionsBtn.parents("tr").before(html);
		//初始化optionsInputList和optionsErrorTdList
		uOptionsInputList=$("input[name='options']");
		optionsErrorTdList=$(".errors").not((index)=>{if(index<2){return true;}});
	});
	
	
	deleteLastRowOptionsBtn.click(function(){
		if(count==1){
			alert("不能再删除了！");
			return;
		}
		optionsTd.attr("rowspan",--count);
		appendRowOptionsBtn.parents("tr").prev().remove();
		
		//初始化optionsInputList和optionsErrorTdList和count
		uOptionsInputList=$("input[name='options']");
		optionsErrorTdList=$(".errors").not((index)=>{if(index<2){return true;}});
		
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
		submitDataByForm("deletePapersInspectionItems",{idList:idList});
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
				submitDataByForm("deletePapersInspectionItems",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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
		//清空上一次检验信息
		$(".errors").hide();
		//清除更新区域数据，显示查询区域并隐藏更新区域
		clearUpdateAreaData();
		queryDataArea.show();
		updateDataArea.hide();
	});
	
});