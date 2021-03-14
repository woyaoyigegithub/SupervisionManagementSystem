/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertCourseSelection）
 * 2. 将网页中的使用到的标签对象的声明在js文件开头.
 * 3. 自定义函数均要写注释来表明其功能.
 * 4. 函数中的变量均声明在函数体开头处，变量使用驼峰命名法，变量取名要有意义.
 * 5. 当函数中代码量繁多时，可按照功能单独写成一个函数供其调用.
 */


//插入数据区域
var insertDataArea;	
//数据展示表格
var dataTab;	

//插入数据区域中的各个<input>标签
var idInput,courseIdInput,teacherIdInput,classIdInput,departmentSelect,
classroomIdInput,schoolYearSelect,semesterSelect,weekSelect,jieciInput,
biweeklySelect,startingAndEndingWeeksInput,numOfClassInput;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,courseIdErrorTd,teacherIdErrorTd,classIdErrorTd,departmentErrorTd,
classroomIdErrorTd,schoolYearErrorTd,semesterErrorTd,weekErrorTd,jieciErrorTd,
biweeklyErrorTd,startingAndEndingWeeksErrorTd,numOfClassErrorTd;
//指的是类名为updateArea区域中的error td标签
var idErrorTd,courseIdErrorTd,teacherIdErrorTd,classIdErrorTd,departmentErrorTd,
classroomIdErrorTd,schoolYearErrorTd,semesterErrorTd,weekErrorTd,jieciErrorTd,
biweeklyErrorTd,startingAndEndingWeeksErrorTd,numOfClassErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,courseId等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
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
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("选课编号必须是7位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vCourseId.test(courseIdInput.val())){ 
		courseIdErrorTd.text("课程编号不规范"); 
		courseIdErrorTd.show();
		flag=false;
	}
	if(!vTeacherId.test(teacherIdInput.val())){ 
		teacherIdErrorTd.text("授课教师工号不规范"); 
		teacherIdErrorTd.show();
		flag=false;
	}
	if(!vClassId.test(classIdInput.val())){ 
		classIdErrorTd.text("选课班级编号不规范"); 
		classIdErrorTd.show();
		flag=false;
	}
	if(!vDepartment.test(departmentSelect.val())){ 
		departmentErrorTd.text("请选择开课学院"); 
		departmentErrorTd.show();
		flag=false;
	}
	if(!vClassroomId.test(classroomIdInput.val())){ 
		classroomIdErrorTd.text("教室编号不规范"); 
		classroomIdErrorTd.show();
		flag=false;
	}
	if(!vSchoolYear.test(schoolYearSelect.val())){ 
		schoolYearErrorTd.text("学年不规范"); 
		schoolYearErrorTd.show();
		flag=false;
	}
	if(!vSemester.test(semesterSelect.val())){ 
		semesterErrorTd.text("请选择学期"); 
		semesterErrorTd.show();
		flag=false;
	}
	if(!vWeek.test(weekSelect.val())){ 
		weekErrorTd.text("请选择星期"); 
		weekErrorTd.show();
		flag=false;
	}
	
	if(!vJieci.test(jieciInput.val())){ 
		jieciErrorTd.text("节次不规范"); 
		jieciErrorTd.show();
		flag=false;
	}
	if(!vBiweekly.test(biweeklySelect.val())){ 
		biweeklyErrorTd.text("请选择单双周"); 
		biweeklyErrorTd.show();
		flag=false;
	}
	if(!vStartingAndEndingWeeks.test(startingAndEndingWeeksInput.val())){ 
		startingAndEndingWeeksErrorTd.text("请输入起止周"); 
		startingAndEndingWeeksErrorTd.show();
		flag=false;
	}
	if(!vNumOfClass.test(numOfClassInput.val())){ 
		numOfClassErrorTd.text("请输入上课人数"); 
		numOfClassErrorTd.show();
		flag=false;
	}
	
	return flag;
}


//获取表单数据，发起（url：addCourseSelection）http请求到后台。
function submitData(){
	var id,courseId,teacherId,classId,department,classroomId,schoolYear,semester,
	week,jieci,biweekly,startingAndEndingWeeks,numOfClass;
	id=idInput.val();
	courseId=courseIdInput.val();
	teacherId=teacherIdInput.val();
	classId=classIdInput.val();
	department=departmentSelect.val();
	classroomId=classroomIdInput.val();
	schoolYear=schoolYearSelect.val();
	semester=semesterSelect.val();
	week=weekSelect.val();
	jieci=jieciInput.val();
	biweekly=biweeklySelect.val();
	startingAndEndingWeeks=startingAndEndingWeeksInput.val();
	numOfClass=numOfClassInput.val();
	
	$.ajax({
        type: "POST",
        url: "insertCourseSelection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	id:id,
        	course:{id:courseId},
        	teacher:{id:teacherId},
			class:{id:classId},
			department:department,
			classroom:{id:classroomId},
			schoolYear:schoolYear,
			semester:semester,
			week:week,
			jieci:jieci,
			biweekly:biweekly,
			startingAndEndingWeeks:startingAndEndingWeeks,
			numOfClass:numOfClass
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryCourseSelection";
        },
        error: function (message) {
            console.log(message);
        }
    });
}


//清空表格数据
function clearData(){
	idInput.val("");
	courseIdInput.val("");
	teacherIdInput.val("");
	classIdInput.val("");
	departmentSelect.val("");
	classroomIdInput.val("");
	schoolYearSelect.val("");
	semesterSelect.val("");
	weekSelect.val("");
	jieciInput.val("");
	biweeklySelect.val("");
	startingAndEndingWeeksInput.val("");
	numOfClassInput.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	courseIdInput=$("input[name='courseId']");
	teacherIdInput=$("input[name='teacherId']");
	classIdInput=$("input[name='classId']");
	departmentSelect=$("select[name='department']");
	classroomIdInput=$("input[name='classroomId']");
	schoolYearSelect=$("select[name='schoolYear']");
	semesterSelect=$("select[name='semester']");
	weekSelect=$("select[name='week']");
	jieciInput=$("input[name='jieci']");
	biweeklySelect=$("select[name='biweekly']");
	startingAndEndingWeeksInput=$("input[name='startingAndEndingWeeks']");
	numOfClassInput=$("input[name='numOfClass']");
	
	//test数据
//	idInput.val("(2020-2021-1)-0010000001-0000558-3");
//	courseIdInput.val("0010000001");
//	teacherIdInput.val("0000558");
//	classIdInput.val("");
//	departmentSelect.val("环境与化学工程学院");
//	classroomIdInput.val("0250001");
//	schoolYearSelect.val("2020-2021");
//	semesterSelect.val("1");
//	weekSelect.val("三");
//	jieciInput.val("1-2");
//	biweeklySelect.val("单双");
//	startingAndEndingWeeksInput.val("第3-10周");
//	numOfClassInput.val("");
	
	
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
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	
	insertBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//校验数据是否符合规范，不符合直接退出函数
		if(!verifyData()){ return; }
		//是否确认提交，确认则清空数据并提交数据
		if(confirm("确认提交？")==false){ return; }
		submitData();
	});
	
	
	clearBtn.click(function(){
		//清除上一次错误提示
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});