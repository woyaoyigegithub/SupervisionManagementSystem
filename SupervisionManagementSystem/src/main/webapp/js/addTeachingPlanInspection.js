/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,upcourseSelectionIdXXX,deleteXXX.（例：insertClass）
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
var courseSelectionIdInput,discussingAndAffirmingTextarea,situationSelectList;
//定位到教案检查指标项编号那一行
 var teachingPlanInspectionItemsIdTr;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验courseSelectionId,discussingAndAffirming等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
	var vDiscussingAndAffirming=/^.+$/;
	var vSituation=/^.+$/;
	
	//提示错误的消息
	var text="";
	var flag=true;
	
	if(!vCourseSelectionId.test(courseSelectionIdInput.val())){ 
		text+="请输入选课编号\n"; 
		flag=false;
	}
	if(!vDiscussingAndAffirming.test(discussingAndAffirmingTextarea.val())){ 
		text+="请输入值得商榷和肯定的地方\n"; 
		flag=false;
	}
	situationSelectList.each(function(index){
		var situationSelect=situationSelectList.eq(index);
		if(!vSituation.test(situationSelect.val())){
			text+=index+1+".选项为空\n";
			flag=false;
		}
	});
	
	if(flag==false){ alert(text); return; }
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var courseSelectionId,discussingAndAffirming;
	var teachingPlanInspectionSituationList=[];
	var trList=teachingPlanInspectionItemsIdTr.nextUntil(discussingAndAffirmingTextarea.parents("tr"));
	
	courseSelectionId=courseSelectionIdInput.val();
	discussingAndAffirming=discussingAndAffirmingTextarea.val();
	situationSelectList.each(function(index){
		var situationSelect=situationSelectList.eq(index);
		var tdList=trList.eq(index).children();
		teachingPlanInspectionSituationList.push({teachingPlanInspectionItems:{id:tdList.eq(0).text()},situation:situationSelect.val()});
	});
	
	
	$.ajax({
        type: "POST",
        url: "insertTeachingPlanInspection",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
        	courseSelection:{id:courseSelectionId},
        	discussingAndAffirming:discussingAndAffirming,
        	teachingPlanInspectionSituationList:teachingPlanInspectionSituationList
        }),
        success: function (data) {
            if(data!=true){
            	errorMessageP.text(data);
            	return;
            }
            location.href = "queryTeachingPlanInspection";
        },
        error: function (xhr) {
           alert(xhr.responseText);
        }
    });
}


//清空表格数据
function clearData(){
	discussingAndAffirmingTextarea.val("");
	situationSelectList.val("");
}


//通过获取项目列表项的成功与否，进行初始化设置
function initComponentStatus(flag){
	//初始化失败
	if(!flag){
		//选课编号可用，其余控件不可用状态
		courseSelectionIdInput.attr("disabled",false);
		discussingAndAffirmingTextarea.attr("disabled",true);
		insertBtn.attr("disabled",true);
		clearBtn.attr("disabled",true);
		return;
	}
	//初始成功
	//optionsTextareaList初始化，并解除控件禁用状态
	courseSelectionIdInput.attr("disabled",true);
 	situationSelectList=dataTab.find("select[name='situation']");
 	discussingAndAffirmingTextarea.attr("disabled",false);
	insertBtn.attr("disabled",false);
	clearBtn.attr("disabled",false);
}



//通过教案检查项列表往教案检查记录中添加检查项网页
function addTeachingPlanInspectionSituationListHtml(teachingPlanInspectionItemsList){
	var nextTr=teachingPlanInspectionItemsIdTr;
 	//循环
 	for(var i=0;i<teachingPlanInspectionItemsList.length;i++){
 		var teachingPlanInspectionItems=teachingPlanInspectionItemsList[i];
 		//需添加的html
 		var html="<tr><td>"+teachingPlanInspectionItems.id+"</td><td>"+teachingPlanInspectionItems.content+"</td><td>"+teachingPlanInspectionItems.options+"</td>"+
 			"<td><select name='situation'><option value=''></option>";
 		//获取选项列表
 		var optionsArray=teachingPlanInspectionItems.options.replace(/▢/g,"").split(" ");
 		for(var j=0;j<optionsArray.length;j++){
 			var options=optionsArray[j];
 			html+="<option>"+options+"</option>";
 		}
		html+="</select></td></tr>";
		nextTr.after(html);
		nextTr=nextTr.next();
 	}
 	
 	//进行成功的初始化
 	initComponentStatus(true);
 	
    //test数据
//    situationSelectList.each(function(index){
//    var situationSelect=situationSelectList.eq(index);
//    situationSelect.children(":eq(1)").prop("selected",true);
//    });
}


//通过询问方式不断验证选课编号，如选课编号存在，则添加检查项到网页中
function tryGetCourseSelectionIdByInquiry(){
	var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
	var courseSelectionId=prompt("请输入选课编号，以便获取相应的教案检查指标项列表");
	if(!vCourseSelectionId.test(courseSelectionId)){
		var answer=confirm("选课编号不合规范，是否重新输入？");
		if(answer==true){ tryGetCourseSelectionIdByInquiry(); }
		else { initComponentStatus(false); /*初始化失败设置*/ }
		return;
	}
	//发送ajax到后端验证
	$.ajax({
		url:"queryCourseSelectionById",
		type: "post",
		dataType: "json",
		data:{courseSelectionId:courseSelectionId},
		success: function(courseSelection){
			//不存在该选课
			if(courseSelection==undefined){	
				var answer=confirm("选课编号不存在，是否重新输入？");
				if(answer==true){ tryGetCourseSelectionIdByInquiry(); }
				else { initComponentStatus(false); /*初始化失败设置*/ }
				return;
			}
			//存在该选课
			courseSelectionIdInput.val(courseSelectionId);
			courseSelectionIdInput.attr("disabled",true);
			
			//发起获取听课项列表请求，并刷新网页
			$.ajax("queryTeachingPlanInspectionItemsList",{type:"post",data:{type:courseSelection.course.type},
				success:addTeachingPlanInspectionSituationListHtml,error:(xhr)=>alert(xhr.responseText)});
		},
		error: function(xhr){ alert(xhr.responseText); }
	});
}



//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	courseSelectionIdInput=dataTab.find("input[name='courseSelectionId']");
	discussingAndAffirmingTextarea=dataTab.find("textarea[name='discussingAndAffirming']");
	
	//test数据
//	discussingAndAffirmingTextarea.val("教案制作精美");
	
	//定位到教案检查指标项编号那一行
	teachingPlanInspectionItemsIdTr=insertDataArea.find("td:contains(教案检查指标项编号)").parent();
	
	errorMessageP=$(".errorMessage");
	
	insertBtn=$("#insertBtn");
	clearBtn=$("#clearBtn");
	
	
	//网页打开时输入选课编号到后端检验，正确则输入到输入框中
	if(courseSelectionIdInput.val()==""){ tryGetCourseSelectionIdByInquiry(); }
	else{ initComponentStatus(true); }

	
	courseSelectionIdInput.blur(function(){
		var vCourseSelectionId=/^\(\d{4}-\d{4}-\d\)-\d{10}-\d{7}-\d{1,2}$/;
		var courseSelectionId=courseSelectionIdInput.val();
		//判断选课编号是否为空
		if(courseSelectionId.trim()==""){
			alert("请输入选课编号");
			return;
		}
		//判断选课编号是否符合规范
		if(!vCourseSelectionId.test(courseSelectionId)){
			alert("选课编号不规范");
			return;
		}
		//后端检验输入框中的选课编号，若存在，则获取教案检查项列表添加到教案检查信息的列表中，不存在，则显示错误信息
		$.ajax({
			url:"queryCourseSelectionById",
			type: "post",
			async: false,
			dataType: "json",
			data:{courseSelectionId:courseSelectionIdInput.val()},
			success: function(courseSelection){
				//不存在该选课
				if(courseSelection==undefined){	
					errorMessageP.text("选课编号不存在！");
					alert("选课编号不存在！");
					return;
				}
				//存在该选课
				courseSelectionIdInput.val(courseSelectionId);
				
				//发起获取听课项列表请求，并刷新网页
				$.ajax("queryTeachingPlanInspectionItemsList",{type:"post",async:false,data:{type:courseSelection.course.type},
					success:addTeachingPlanInspectionSituationListHtml,error:(xhr)=>alert(xhr.responseText)});
			},
			error: function(xhr){ alert(xhr.responseText); }
		});
		
	});
	
	insertBtn.click(function(){
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