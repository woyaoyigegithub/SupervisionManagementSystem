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
var aIdInput,aCourseIdInput,aTeacherIdInput,aClassIdInput,aDepartmentSelect,aClassroomIdInput,
aSchoolYearSelect,aSemesterSelect,aWeekSelect;
//指的是类名updateArea区域中的input。
var uIdInput,uCourseIdInput,uTeacherIdInput,uClassIdInput,uDepartmentInput,uClassIdInput,uSchoolYearSelect,
uSemesterSelect,uWeekSelect,uJieciInput,uBiweeklySelect,uStartingAndEndingWeeksInput,uNumOfClassInput;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,courseIdErrorTd,teacherIdErrorTd,classIdErrorTd,departmentErrorTd,
classroomIdErrorTd,schoolYearErrorTd,semesterErrorTd,weekErrorTd,jieciErrorTd,
biweeklyErrorTd,startingAndEndingWeeksErrorTd,numOfClassErrorTd;
//搜索、批量删除button和全选单选框
var searchBtn,batchDeleteBtn,selectAllChk;
//数据表格中的所有更新、删除图片列表
var updateImageList,deleteImageList;
//更新、取消button
var updateBtn,cancelBtn;



//获取并提交高级搜索区的数据
function submitAdvancedSearchData(){
	var id,courseId,teacherId,classId,department,classroomId,schoolYear,
	semester,week;
	
	id=aIdInput.val();
	courseId=aCourseIdInput.val();
	teacherId=aTeacherIdInput.val();
	classId=aClassIdInput.val();
	department=aDepartmentSelect.val();
	classroomId=aClassroomIdInput.val();
	schoolYear=aSchoolYearSelect.val();
	semester=aSemesterSelect.val();
	week=aWeekSelect.val();
	
	semester=semester==""?0:semester;
	
	submitDataByForm("queryCourseSelection",{id:id,courseId:courseId,teacherId:teacherId,
		classId:classId,department:department,classroomId:classroomId,schoolYear:schoolYear,
		semester:semester,week:week});
}



//从(数据表格)中提取数据到(更新数据区域)的input中。(参数：被点击的编辑图片)
function getUpdateAreaDataFromTable(updateImage) {
	var id,courseId,teacherId,classId,department,classroomId,schoolYear,
	semester,week,jieci,biweekly,startingAndEndingWeeks,numOfClass;
	
	//更新图片所在行的所有td列表
	var tdList=updateImage.parents("tr").children();
	
	//获取表格数据
	id=tdList.eq(1).text();
	courseId=tdList.eq(2).text();
	teacherId=tdList.eq(4).text();
	classId=tdList.eq(6).text();
	department=tdList.eq(8).text();
	classroomId=tdList.eq(9).text();
	schoolYear=tdList.eq(11).text();
	semester=tdList.eq(12).text();
	week=tdList.eq(13).text();
	jieci=tdList.eq(14).text();
	biweekly=tdList.eq(15).text();
	startingAndEndingWeeks=tdList.eq(16).text();
	numOfClass=tdList.eq(17).text();
	
	
	
	//将获取的数据放入更新区域中
	uIdInput.val(id);
	uCourseIdInput.val(courseId);
	uTeacherIdInput.val(teacherId);
	uClassIdInput.val(classId);
	uDepartmentSelect.val(department);
	uClassroomIdInput.val(classroomId);
	uSchoolYearSelect.val(schoolYear);
	uSemesterSelect.val(semester);
	uWeekSelect.val(week);
	uJieciInput.val(jieci);
	uBiweeklySelect.val(biweekly);
	uStartingAndEndingWeeksInput.val(startingAndEndingWeeks);
	uNumOfClassInput.val(numOfClass);
	
}


//验证提交数据格式是否正确
function verifyUpdateAreaData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\(\d{4}-\d{4}-1|2\)-\d{10}-\d{7}-\d+$/;
	var vCourseId=/^\d{10}$/;
	var vTeacherId=/^\d{7}$/;
	var vClassId=/^\d*$/;
	var vDepartment=/^.+$/;
	var vClassroomId=/^\d{7}$/;
	var vSchoolYear=/^\d{4}-\d{4}$/;
	var vSemester=/^1|2$/;
	var vWeek=/^(一)|(二)|(三)|(四)|(五)|(六)|(日)$/;
	var vJieci=/^.+-.+$/;
	var vBiweekly=/^(单)|(双)|(单双)$/;
	var vStartingAndEndingWeeks=/^(第)?\d+-\d+(周)?$/;
	var vNumOfClass=/^\d*$/;
	
	var flag=true;
	
	if(!vId.test(uIdInput.val())){ 
		idErrorTd.text("选课编号必须是7位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vCourseId.test(uCourseIdInput.val())){ 
		courseIdErrorTd.text("课程编号不规范"); 
		courseIdErrorTd.show();
		flag=false;
	}
	if(!vTeacherId.test(uTeacherIdInput.val())){ 
		teacherIdErrorTd.text("授课教师工号不规范"); 
		teacherIdErrorTd.show();
		flag=false;
	}
	if(!vClassId.test(uClassIdInput.val())){ 
		classIdErrorTd.text("选课班级编号不规范"); 
		classIdErrorTd.show();
		flag=false;
	}
	if(!vDepartment.test(uDepartmentSelect.val())){ 
		departmentErrorTd.text("请选择开课学院"); 
		departmentErrorTd.show();
		flag=false;
	}
	if(!vClassroomId.test(uClassroomIdInput.val())){ 
		classroomIdErrorTd.text("教室编号不规范"); 
		classroomIdErrorTd.show();
		flag=false;
	}
	if(!vSchoolYear.test(uSchoolYearSelect.val())){ 
		schoolYearErrorTd.text("学年不规范"); 
		schoolYearErrorTd.show();
		flag=false;
	}
	if(!vSemester.test(uSemesterSelect.val())){ 
		semesterErrorTd.text("请选择学期"); 
		semesterErrorTd.show();
		flag=false;
	}
	if(!vWeek.test(uWeekSelect.val())){ 
		weekErrorTd.text("请选择星期"); 
		weekErrorTd.show();
		flag=false;
	}
	
	if(!vJieci.test(uJieciInput.val())){ 
		jieciErrorTd.text("节次不规范"); 
		jieciErrorTd.show();
		flag=false;
	}
	if(!vBiweekly.test(uBiweeklySelect.val())){ 
		biweeklyErrorTd.text("请选择单双周"); 
		biweeklyErrorTd.show();
		flag=false;
	}
	if(!vStartingAndEndingWeeks.test(uStartingAndEndingWeeksInput.val())){ 
		startingAndEndingWeeksErrorTd.text("请输入起止周"); 
		startingAndEndingWeeksErrorTd.show();
		flag=false;
	}
	if(!vNumOfClass.test(uNumOfClassInput.val())){ 
		numOfClassErrorTd.text("请输入上课人数"); 
		numOfClassErrorTd.show();
		flag=false;
	}
	
	return flag;
}


//获取并提交更新数据区域数据到后台
function submitUpdateAreaData(){
	var id,courseId,teacherId,classId,department,classroomId,
	schoolYear,semester,week,jieci,biweekly,startingAndEndingWeeks,numOfClass;
	
	id=uIdInput.val();
	courseId=uCourseIdInput.val();
	teacherId=uTeacherIdInput.val();
	classId=uClassIdInput.val();
	department=uDepartmentSelect.val();
	classroomId=uClassroomIdInput.val();
	schoolYear=uSchoolYearSelect.val();
	semester=uSemesterSelect.val();
	week=uWeekSelect.val();
	jieci=uJieciInput.val();
	biweekly=uBiweeklySelect.val();
	startingAndEndingWeeks=uStartingAndEndingWeeksInput.val();
	numOfClass=uNumOfClassInput.val();
	
	
	$.ajax({
		url:"updateCourseSelection",
		type:"POST",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify({
			id:id,
			course:{id:courseId},
			teacher:{id:teacherId},
			clazz:classId==""?null:{id:classId},
			department:department,
			classroom:{id:classroomId},
			schoolYear:schoolYear,
			semester:semester,
			week:week,
			jieci:jieci,
			biweekly:biweekly,
			startingAndEndingWeeks:startingAndEndingWeeks,
			numOfClass:numOfClass,
		}),
		success: function (data) {
            if(data!=true){ 
            	alert(data);
				errorMessage.text(data);
				errorMessage.show();
				return; 
			}
            location.href="queryCourseSelection";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
	});
}


//清空（更新数据区域）中的{表格数据}的input值
function clearUpdateAreaData(){
	uIdInput.val("");
	uCourseIdInput.val("");
	uTeacherIdInput.val("");
	uClassIdInput.val("");
	uDepartmentSelect.val("");
	uClassroomIdInput.val("");
	uSchoolYearSelect.val("");
	uSemesterSelect.val("");
	uWeekSelect.val("");
	uJieciInput.val("");
	uBiweeklySelect.val("");
	uStartingAndEndingWeeksInput.val("");
	uNumOfClassInput.val("");
	
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
	aCourseIdInput=advancedSearchUl.find("input[name='courseId']");
	aTeacherIdInput=advancedSearchUl.find("input[name='teacherId']");
	aClassIdInput=advancedSearchUl.find("input[name='classId']");
	aDepartmentSelect=advancedSearchUl.find("select[name='department']");
	aClassroomIdInput=advancedSearchUl.find("input[name='classroomId']");
	aSchoolYearSelect=advancedSearchUl.find("select[name='schoolYear']");
	aSemesterSelect=advancedSearchUl.find("select[name='semester']");
	aWeekSelect=advancedSearchUl.find("select[name='week']");

	uIdInput=updateDataTab.find("input[name='id']");	uIdInput.attr("disabled",true); //更新时id框不可用
	uCourseIdInput=updateDataTab.find("input[name='courseId']");
	uTeacherIdInput=updateDataTab.find("input[name='teacherId']");
	uClassIdInput=updateDataTab.find("input[name='classId']");
	uDepartmentSelect=updateDataTab.find("select[name='department']");
	uClassroomIdInput=updateDataTab.find("input[name='classroomId']");
	uSchoolYearSelect=updateDataTab.find("select[name='schoolYear']");
	uSemesterSelect=updateDataTab.find("select[name='semester']");
	uWeekSelect=updateDataTab.find("select[name='week']");
	uJieciInput=updateDataTab.find("input[name='jieci']");
	uBiweeklySelect=updateDataTab.find("select[name='biweekly']");
	uStartingAndEndingWeeksInput=updateDataTab.find("input[name='startingAndEndingWeeks']");
	uNumOfClassInput=updateDataTab.find("input[name='numOfClass']");
	
	idErrorTd=$(".errors").eq(0);
	courseIdErrorTd=$(".errors").eq(1);
	teacherIdErrorTd=$(".errors").eq(2);
	classIdErrorTd=$(".errors").eq(3);
	departmentErrorTd=$(".errors").eq(4);
	classroomIdErrorTd=$(".errors").eq(5);
	schoolYearErrorTd=$(".errors").eq(6);
	semesterErrorTd=$(".errors").eq(7);
	weekErrorTd=$(".errors").eq(8);
	jieciErrorTd=$(".errors").eq(9);
	biweeklyErrorTd=$(".errors").eq(10);
	startingAndEndingWeeksErrorTd=$(".errors").eq(11);
	numOfClassErrorTd=$(".errors").eq(12);
	
	errorMessage=$(".errorMessage");
	
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
		submitDataByForm("deleteCourseSelection",{idList:idList});
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
				submitDataByForm("deleteCourseSelection",{idList:[deleteImage.parents("tr").children(":nth-child(2)").text()]});
			}
		});
	});
	
	
	updateBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//验证更新数据区域中的表单数据是否正确
		if(!verifyUpdateAreaData()){  return; }
		//确认提交数据
		if(confirm("确认提交？")==false){  return; }
		submitUpdateAreaData();
	});
	
	cancelBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//清除更新区域数据，显示查询区域并隐藏更新区域
		clearUpdateAreaData();
		queryDataArea.show();
		updateDataArea.hide();
	});
	
});