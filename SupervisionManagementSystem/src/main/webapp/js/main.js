var identityList=["管理员","督导"];	//未连接数据库时临时的身份列表
var staffId,identity;	//获取职工Id和身份
//var countURLList=["countAttendedLecturesNum","countDailyInspectionNum","countTourInspectionNum"];	//用于数据统计的URL
var targetURLList=[			//对应于itemsList的跳转URL列表
	"addClass","queryClass","addStudent","queryStudent","addClassroom","queryClassroom",
	"addCourse","queryCourse","addPower","queryPower","addStaff","queryStaff",
	"addCourseSelection","queryCourseSelection","addInspectionAreaArrangement","queryInspectionAreaArrangement",
	"addAttendedLecturesItems","queryAttendedLecturesItems","addAttendedLectures","queryAttendedLectures",
	"addDailyInspectionItems","queryDailyInspectionItems","addDailyInspection","queryDailyInspection",
	"addTourInspectionItems","queryTourInspectionItems","addTourInspection","queryTourInspection",
	"addHomeworkInspection","queryHomeworkInspection","addTeachingPlanInspectionItems","queryTeachingPlanInspectionItems",
	"addTeachingPlanInspection","queryTeachingPlanInspection","addPapersInspectionItems","queryPapersInspectionItems",
	"addPapersInspection","queryPapersInspection",
	"countAttendedLecturesNum","countDailyInspectionNum","countTourInspectionNum"
];
var navigBar;	//导航栏
var oneMenuItemsList,twoMenuItemsList,itemsList;	//一级菜单列表,二级菜单项列表，菜单项列表
var infoManFrame;		//显示跳转URL的iframe标签


//获取两个同级元素之间的兄弟项
function nextUntil(one,other){
	var itemsList;
	other.addClass("target888");
	itemsList=one.nextUntil(".target888");
	other.removeClass("target888");
	return itemsList;
}


//切换功能：通过点击一级菜单显示菜单项。（参数：_this 为jquery对象）
function toggleItemsByOneMenuItemsClick(_this){
	var _nextBrother;	//下一个一级菜单项兄弟
	var itemsList;	//这一个一级菜单项和下一个一级菜单项之间的所有菜单项列表
	
	_nextBrother=_this.nextAll(".oneMenuItems").eq(0);
	itemsList=_this.nextUntil(_nextBrother);
	
	//依据第一个菜单项的是否显示，来切换显示隐藏状态
	if(itemsList.eq(0).css("display")==""||itemsList.eq(0).css("display")=="none" ){
		itemsList.css("display","block");
		return;
	}
	itemsList.css("display","none");
}


//切换功能：通过点击一级菜单显示二级菜单项。（参数_this->jquery对象）
function toggleTwoMenuItemsByOneMenuItemsClick(_this){

	var _nextBrother;			//下一个一级菜单项的兄弟
	var itemsAndTwoMenuItemsList;	//两个一级菜单项之间的二级菜单项和菜单项。
	var twoMenuItemsList;		//两个一级菜单项之间的二级菜单项
	
	_nextBrother=_this.nextAll(".oneMenuItems").eq(0);
	//如果是最后一个一级菜单
	if(_nextBrother.length==0) {
		itemsAndTwoMenuItemsList=_this.nextAll();
	}else{
		itemsAndTwoMenuItemsList=_this.nextUntil(_nextBrother);
	}
	//从两个一级菜单项之间的菜单项中筛选出二级菜单项
	twoMenuItemsList=itemsAndTwoMenuItemsList.filter(".twoMenuItems");
	//隐藏两个一级菜单项之间的二级菜单项，显示其中的菜单项
	if(twoMenuItemsList.eq(0).css("display")==""||twoMenuItemsList.eq(0).css("display")=="none"){
		itemsAndTwoMenuItemsList.css("display","none");
		twoMenuItemsList.css("display","block");
		return;
	}
	//隐藏两个一级菜单项之间的二级菜单项和菜单项
	itemsAndTwoMenuItemsList.css("display","none");
}


//切换功能：通过点击二级菜单显示菜单项。（参数_this->jquery对象）
function toggleItemsByTwoMenuItemsClick(_this){
	var nextOneMenuItems,nextTwoMenuItems;	//下一个一级菜单项和下一个二级菜单项
	var itemsList;	//所选菜单项
	
	nextOneMenuItems=_this.nextAll(".oneMenuItems").eq(0);
	nextTwoMenuItems=_this.nextAll(".twoMenuItems").eq(0);
	
	if(nextOneMenuItems.length==1 && nextTwoMenuItems.length==0){	//非最后一项且不存在二级菜单项
		itemsList=nextUntil(_this,nextOneMenuItems);
	}else if(nextOneMenuItems.length==1 && nextTwoMenuItems.index()<nextOneMenuItems.index()){	//非最后一项一级菜单中的有下一个二级菜单
		itemsList=nextUntil(_this,nextTwoMenuItems);
	}else if(nextOneMenuItems.length==1 && nextTwoMenuItems.index()>nextOneMenuItems.index()){	//非最后一项一级菜单中的无下一个二级菜单
		itemsList=nextUntil(_this,nextOneMenuItems);
	}else if(nextOneMenuItems.length==0 && nextTwoMenuItems.length==1){	//最后一项一级菜单中的有下一个二级菜单
		itemsList=nextUntil(_this,nextTwoMenuItems);
	}else{	//最后一项一级菜单中的无下一个二级菜单
		itemsList=_this.nextAll();
	}
	//显示或隐藏所选菜单项
	if(itemsList.eq(0).css("display")==""||itemsList.eq(0).css("display")=="none"){
		itemsList.css("display","block");
		return;
	}
	itemsList.css("display","none");
}

//根据身份删除导航栏结点和跳转URL(需要先初始化targetURLList)
function deleteNavigNodeAndTargetURLByIdentity(){
	var indexList=[];	//搜集的需要删除的URL下标数组
	var itemsList=$(".middleArea>.navigBar>.items");
	
	if(identity=="督导"){
		//获取需删除的adminURL数组下标
		var adminItems=$(".admin.items");
		adminItems.each(function(index){
			var items=adminItems.eq(index);
			indexList.push(itemsList.index(items));
		});
		//删除admin的结点
		$(".admin").remove();
	}else if(identity=="管理员"){
		//获取需删除的supervisorURL数组下标
		var supervisorItems=$(".supervisor.items");
		supervisorItems.each(function(index){
			var items=supervisorItems.eq(index);
			indexList.push(itemsList.index(items));
		});
		//删除supervisor的结点
		$(".supervisor").remove();
	}
	//根据需要删除的URL下标数组删除多余URL
	for(var i=0;i<indexList.length;i++){delete targetURLList[indexList[i]]};
	var indexList=[];
	targetURLList.forEach((items)=>{indexList.push(items)});
	targetURLList=indexList;
}


$(document).ready(function(){
	
	var sayHelloText=$(".say_hello_text").text();
	staffId=getStaffId();	//获取职工id
	identity=getIdentity();	//获取当前身份
	
	//根据身份删除导航栏结点和跳转URL
	deleteNavigNodeAndTargetURLByIdentity();
	//初始化导航栏所有跳转页面
	navigBar=$(".middleArea>.navigBar");
	itemsList=navigBar.children(".items");
	oneMenuItemsList=navigBar.children(".oneMenuItems");
	twoMenuItemsList=navigBar.children(".twoMenuItems");
	infoManFrame=$(".infoManFrame");
	
	//注册所有的一级菜单的点击事件
	oneMenuItemsList.each(function(index){
		//根据下一个结点是二级菜单，还是菜单分别注册不同的事件
		var nextNode=oneMenuItemsList.eq(index).next();
		if(nextNode.hasClass("items")){
			this.onclick=function(){
				toggleItemsByOneMenuItemsClick(oneMenuItemsList.eq(index));
			};
		}else if(nextNode.hasClass("twoMenuItems")){
			this.onclick=function(){
				toggleTwoMenuItemsByOneMenuItemsClick(oneMenuItemsList.eq(index));
			};
		}
	});
	
	//注册所有的二级菜单的点击事件
	twoMenuItemsList.each(function(index){
		//加载图片
		this.onclick=function(){
			toggleItemsByTwoMenuItemsClick(twoMenuItemsList.eq(index));
		}
	});
	
	//给所有菜单项注册跳转点击事件
	itemsList.each(function(index){
		this.onclick=function(){
			var targetURL=targetURLList[index];
//			if(countURLList.indexOf(targetURL)>=0){
//				if(identity=="管理员"){
//					targetURL+="?adminId=";
//				}else if(identity=="督导"){
//					targetURL+="?supervisorId=";
//				}
//				targetURL+=staffId;
//			}
			infoManFrame.attr("src",targetURL);
			return;
		}
	})
	
});