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
var aIdInput,aNameInput,aSeatingCapacityInput,aTypeSelect,aBuildingNumInput;
//指的是类名updateArea区域中的input。
var uIdInput,uNameInput,uSeatingCapacityInput,uTypeSelect,uBuildingNumInput;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,nameErrorTd,seatingCapacityErrorTd,typeErrorTd,buildingNumErrorTd;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,name,seatingCapacity,type,buildingNum;
	
	id=aIdInput.val();
	name=aNameInput.val();
	seatingCapacity=aSeatingCapacityInput.val();
	type=aTypeSelect.val();
	buildingNum=aBuildingNumInput.val();
	
	seatingCapacity=seatingCapacity==""?-1:seatingCapacity;
	
	submitDataByForm("queryClassroom",{id:id,name:name,seatingCapacity:seatingCapacity,type:type,
		buildingNum:buildingNum});
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,name,seatingCapacity,type,buildingNum;
	
	var tdList=updateImage.parents("tr").children();
	
	id=tdList.eq(1).text();
	name=tdList.eq(2).text();
	seatingCapacity=tdList.eq(3).text();
	type=tdList.eq(4).text();
	buildingNum=tdList.eq(5).text();
	
	uIdInput.val(id);
	uNameInput.val(name);
	uSeatingCapacityInput.val(seatingCapacity);
	uTypeSelect.val(type);
	uBuildingNumInput.val(buildingNum);
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//vId,vName等为验证规范。
	var vId=/^\d{7}$/;
	var vName=/^.+$/;
	var vSeatingCapacity=/^\d+$/;
	var vType=/^(三笔字专用)|(体育场地)|(体育多媒体)|(国画室)|(外语专用)|(多媒体)|(实验室)|(微格)|(普通话)|(服装室)|(机房)|(机械专用)|(环化机房)|(生工智慧教室)|(电信专用)|(留学生专用)|(经管机房)|(美术机房)|(美术画室)|(计科专用)|(语音室)|(钢琴专用)|(陶艺室)$/;
	var vBuildingNum=/^.*$/;
	//正确标志
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		idErrorTd.text("教室编号必须是7位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(uNameInput.val())){ 
		nameErrorTd.text("教室名称格式不正确"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vSeatingCapacity.test(uSeatingCapacityInput.val())){ 
		seatingCapacityErrorTd.text("座位数格式不正确"); 
		seatingCapacityErrorTd.show();
		flag=false;
	}
	if(!vType.test(uTypeSelect.val())){ 
		typeErrorTd.text("请选择教室类别"); 
		typeErrorTd.show();
		flag=false;
	}
	if(!vBuildingNum.test(uBuildingNumInput.val())){ 
		buildingNumErrorTd.text("楼号格式不正确"); 
		buildingNumErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,name,seatingCapacity,type,buildingNum;
	id=uIdInput.val();
	name=uNameInput.val();
	seatingCapacity=uSeatingCapacityInput.val();
	type=uTypeSelect.val();
	buildingNum=uBuildingNumInput.val();
	
	
	$.ajax({
        type: "POST",
        url: "updateClassroom",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	name:name,
        	seatingCapacity:seatingCapacity,
        	type:type,
			buildingNum:buildingNum
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryClassroom";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uNameInput.val("");
	uSeatingCapacityInput.val("");
	uTypeSelect.val("");
	uBuildingNumInput.val("");
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
	aNameInput=advancedSearchUl.find("input[name='name']");
	aSeatingCapacityInput=advancedSearchUl.find("input[name='seatingCapacity']");
	aTypeSelect=advancedSearchUl.find("select[name='type']");
	aBuildingNumInput=advancedSearchUl.find("input[name='buildingNum']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uNameInput=updateDataTab.find("input[name='name']");
	uSeatingCapacityInput=updateDataTab.find("input[name='seatingCapacity']");
	uTypeSelect=updateDataTab.find("select[name='type']");
	uBuildingNumInput=updateDataTab.find("input[name='buildingNum']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	seatingCapacityErrorTd=$(".errors").eq(2);
	typeErrorTd=$(".errors").eq(3);
	buildingNumErrorTd=$(".errors").eq(4);
	
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
		submitDataByForm("deleteClassroom",{idList:idList});
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
				submitDataByForm("deleteClassroom",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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