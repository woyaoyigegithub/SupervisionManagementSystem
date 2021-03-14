/**
 * 1. 所有发送表单请求均规定为insertXXX,queryXXX,updateXXX,deleteXXX.（例：insertClass）
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
var idInput,nameInput,seatingCapacityInput,typeSelect,buildingNumInput;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,nameErrorTd,seatingCapacityErrorTd,typeErrorTd,buildingNumErrorTd;
//错误消息
var errorMessageP;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^\d{7}$/;
	var vName=/^.+$/;
	var vSeatingCapacity=/^\d+$/;
	var vType=/^(三笔字专用)|(体育场地)|(体育多媒体)|(国画室)|(外语专用)|(多媒体)|(实验室)|(微格)|(普通话)|(服装室)|(机房)|(机械专用)|(环化机房)|(生工智慧教室)|(电信专用)|(留学生专用)|(经管机房)|(美术机房)|(美术画室)|(计科专用)|(语音室)|(钢琴专用)|(陶艺室)$/;
	var vBuildingNum=/^.*$/;
	var flag=true;
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("教室编号必须是7位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(nameInput.val())){ 
		nameErrorTd.text("教室名称格式不正确"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vSeatingCapacity.test(seatingCapacityInput.val())){ 
		seatingCapacityErrorTd.text("座位数应大于等于0"); 
		seatingCapacityErrorTd.show();
		flag=false;
	}
	if(!vType.test(typeSelect.val())){ 
		typeErrorTd.text("教室类别格式不正确"); 
		typeErrorTd.show();
		flag=false;
	}
	if(!vBuildingNum.test(buildingNumInput.val())){ 
		buildingNumErrorTd.text("楼号格式不正确"); 
		buildingNumErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var id,name,seatingCapacity,type,buildingNum;
	id=idInput.val();
	name=nameInput.val();
	seatingCapacity=seatingCapacityInput.val();
	type=typeSelect.val();
	buildingNum=buildingNumInput.val();
	
	$.ajax({
        type: "POST",
        url: "insertClassroom",
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
        error: function (message) {
            console.log(message);
        }
    });
}


//清空表格数据
function clearData(){
	idInput.val("");
	nameInput.val("");
	seatingCapacityInput.val("");
	typeSelect.val("");
	buildingNumInput.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	nameInput=$("input[name='name']");
	seatingCapacityInput=$("input[name='seatingCapacity']");
	typeSelect=$("select[name='type']");
	buildingNumInput=$("input[name='buildingNum']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	seatingCapacityErrorTd=$(".errors").eq(2);
	typeErrorTd=$(".errors").eq(3);
	buildingNumErrorTd=$(".errors").eq(4);
	
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