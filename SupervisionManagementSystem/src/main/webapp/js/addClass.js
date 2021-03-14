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
var idInput,nameInput,majorInput,gradeInput,departmentSelect,undergOrJunSelect;
//插入数据区域中的各个<input>标签对应的错误提示小方格
var idErrorTd,nameErrorTd,majorErrorTd,gradeErrorTd,departmentErrorTd,undergOrJunErrorTd;
//插入，清空buttton
var insertBtn,clearBtn;


//验证提交数据格式是否正确
function verifyData(){
	//校验id,name等表单数据是否符合规范。	（注意：/^...$/ 为正则表达式写法）
	var vId=/^[1-9]\d{9}$/;
	var vName=/^.+$/;
	var vMajor=/^[1-9]\d{3}.+$/;
	var vGrade=/^[1-9]\d{3}$/;
	var vDepartment=/^.+$/;
	var vUndergOrJun=/^(本科)|(专科)$/;
	var flag=true;
	
	
	if(!vId.test(idInput.val())){ 
		idErrorTd.text("班级编号必须是十位数"); 
		idErrorTd.show();
		flag=false;
	}
	if(!vName.test(nameInput.val())){ 
		nameErrorTd.text("班级名称格式不正确"); 
		nameErrorTd.show();
		flag=false;
	}
	if(!vMajor.test(majorInput.val())){ 
		majorErrorTd.text("专业格式不正确"); 
		majorErrorTd.show();
		flag=false;
	}
	if(!vGrade.test(gradeInput.val())){ 
		gradeErrorTd.text("年级格式不正确"); 
		gradeErrorTd.show();
		flag=false;
	}
	if(!vDepartment.test(departmentSelect.val())){ 
		departmentErrorTd.text("所在院系不能为空"); 
		departmentErrorTd.show();
		flag=false;
	}
	if(!vUndergOrJun.test(undergOrJunSelect.val())){ 
		undergOrJunErrorTd.text("请选择本科或是专科"); 
		undergOrJunErrorTd.show();
		flag=false;
	}
	return flag;
}


//获取表单数据，发起（url：addClass）http请求到后台。
function submitData(){
	var id,name,major,grade,department,undergOrJun;
	id=idInput.val();
	name=nameInput.val();
	major=majorInput.val();
	grade=gradeInput.val();
	department=departmentSelect.val();
	undergOrJun=undergOrJunSelect.val();
	
	submitDataByForm("insertClass",{id:id,name:name,major:major,grade:grade,department:department,
		undergOrJun:undergOrJun});
}


//清空表格数据
function clearData(){
	idInput.val("");
	nameInput.val("");
	majorInput.val("");
	gradeInput.val("");
	departmentSelect.val("");
	undergOrJunSelect.val("");
}


//文档加载时调用的函数
$(document).ready(function(){
	//初始化变量
	insertDataArea=$(".insertDataArea");
	dataTab=$(".dataTab");
	
	idInput=$("input[name='id']");
	nameInput=$("input[name='name']");
	majorInput=$("input[name='major']");
	gradeInput=$("input[name='grade']");
	departmentSelect=$("select[name='department']");
	undergOrJunSelect=$("select[name='undergOrJun']");
	
	idErrorTd=$(".errors").eq(0);
	nameErrorTd=$(".errors").eq(1);
	majorErrorTd=$(".errors").eq(2);
	gradeErrorTd=$(".errors").eq(3);
	departmentErrorTd=$(".errors").eq(4);
	undergOrJunErrorTd=$(".errors").eq(5);
	
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
		//清空上一次检验信息
		$(".errors").hide();
		//清空button清除表单所有数据
		clearData();
	});
	
});