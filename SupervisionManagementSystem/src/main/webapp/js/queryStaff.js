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
var aIdInput,aNameInput,aDepartmentSelect,aPasswordInput,aPowerIdSelect;
//指的是类名updateArea区域中的input。
var uIdInput,uNameInput,uDepartmentSelect,uPasswordInput,uPowerIdSelect;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,nameErrorTd,departmentErrorTd,passwordErrorTd,powerIdErrorTd;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;


//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,name,department,powerId;
	
	id=aIdInput.val();
	name=aNameInput.val();
	department=aDepartmentSelect.val();
	powerId=aPowerIdSelect.val();
	
	id=id==""?0:id;

	submitDataByForm("queryStaff",{id:id,name:name,department:department,powerId:powerId});
	
}


//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,name,department,password,powerId;
	
	var tdList=updateImage.parents("tr").children();
	
	id=tdList.eq(1).text();
	name=tdList.eq(2).text();
	department=tdList.eq(3).text();
	password=tdList.eq(4).text();
	powerId=uPowerIdSelect.children(":contains('"+tdList.eq(5).text()+"')").val();
	
	uIdInput.val(id);
	uNameInput.val(name);
	uDepartmentSelect.val(department);
	uPasswordInput.val(password);
	uPowerIdSelect.val(powerId);
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//vId,vName等为验证规范。
	var vId=/^\d{7}$/;
	var vName=/^.+$/;
	var vDepartment=/^.*$/;
	var vPassword=/^\w{6,}$/;
	var vPowerId=/^\d+$/;
	//正确标志
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		idErrorTd.text("教职工号必须是数字组合"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(uNameInput.val())){ 
		nameErrorTd.text("请输入教职工姓名"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vDepartment.test(uDepartmentSelect.val())){ 
		departmentErrorTd.text("请选择所属学院"); 
		departmentErrorTd.show();
		flag=false;
	}
	if(!vPassword.test(uPasswordInput.val())){ 
		passwordErrorTd.text("密码至少6位数且必须由字母、数字或下划线组成的"); 
		passwordErrorTd.show();
		flag=false;
	}
	if(!vPowerId.test(uPowerIdSelect.val())){ 
		powerIdErrorTd.text("请选择权限"); 
		powerIdErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,name,department,powerId;
	id=uIdInput.val();
	name=uNameInput.val();
	department=uDepartmentSelect.val();
	password=uPasswordInput.val();
	powerId=uPowerIdSelect.val();
	
	$.ajax({
        type: "POST",
        url: "updateStaff",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data:JSON.stringify({
        	id:id,
        	name:name,
        	department:department,
        	password:password,
			power:{id:powerId}
        }),
        success: function (data) {
            if(data!=true){ return; }
            location.href="queryStaff";
        },
        error: function (message) {
            console.log(message);
        }
    });
	
	
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uNameInput.val("");
	uDepartmentSelect.val("");
	uPasswordInput.val("");
	uPowerIdSelect.val("");
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
	aDepartmentSelect=advancedSearchUl.find("select[name='department']");
	aPasswordInput=advancedSearchUl.find("input[name='password']");
	aPowerIdSelect=advancedSearchUl.find("select[name='powerId']");
	
	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uNameInput=updateDataTab.find("input[name='name']");
	uDepartmentSelect=updateDataTab.find("select[name='department']");
	uPasswordInput=updateDataTab.find("input[name='password']");
	uPowerIdSelect=updateDataTab.find("select[name='powerId']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	departmentErrorTd=$(".errors").eq(2);
	passwordErrorTd=$(".errors").eq(3);
	powerIdErrorTd=$(".errors").eq(4);

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
		submitDataByForm("deleteStaff",{idList:idList});
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
				submitDataByForm("deleteStaff",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
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