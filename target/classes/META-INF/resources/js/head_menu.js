/**
 * 头部信息
 */
var txt1='<div class="nav">\
	<ul class="layui-nav nva-title" lay-filter="nva-title" >\
	<li class="layui-nav-item">\
	<a href="#" class="nav-top-title">狮享汇管理系统</a>\
	</li>\
	<li class="layui-nav-item user-msg">\
	<a href="#" class="user-name" id="welcome_user_name">欢迎</a>\
	</li>\
	<li class="layui-nav-item logout-login">\
	<a href="#" id="loginOut" class="checkout">退出登陆</a>\
	</li>\
	</ul>\
	</div>';
document.write(txt1);

/**
 * 左侧菜单
 */
var menuList = JSON.parse(localStorage.getItem("menu_list"));
console.log(menuList);
var txt2 = '<div class="left-nav"><ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="test">';
for ( var i = 0; i < menuList.length; i++) {
	txt2 += '<li id="'+menuList[i].authority+'" class="layui-nav-item layui-nav-itemed"><a href="javascript:;">'+menuList[i].name+'</a>';
	var menuDtls = menuList[i].menuDtls;
	console.log(menuDtls);
	for ( var j = 0; j < menuDtls.length; j++) {
		txt2 += '<dl class="layui-nav-child"><dd><a href="javascript:;" id="'+menuDtls[j].authority+'">'+menuDtls[j].name+'</a></dd></dl>'
	}
	txt2 += '</li>'
}
txt2 += '</ul></div>';
console.log(txt2);
document.write(txt2);

/**
 * 欢迎字段
 */
var userInfo = JSON.parse(localStorage.getItem("user_info"));
document.getElementById("welcome_user_name").innerHTML="欢迎您，"+userInfo.name;

/**
 * 退出登录
 */
// 点击退出时跳到登陆页面，然后清除cookie
document.getElementById("loginOut").onclick=function(){
	localStorage.clear();
	window.location.href=url;
}
// 点击跳到销售记录页面
document.getElementById("sales_record").onclick=function(){
    window.location.href=url+'/url/sale';
}
// 点击跳到用户管理页面
document.getElementById("user_management").onclick=function(){
    window.location.href=url+'/url/userlist';
}
// 点击跳到新增业绩页面
document.getElementById("add_achievement").onclick=function(){
    window.location.href=url+'/url/addachievement';
}
// 点击跳到商品管理页面
document.getElementById("goods_manage").onclick=function(){
	window.location.href=url+'/url/goodsList';
}
//点击跳到销售总记录页面
document.getElementById("total_sales_record").onclick=function(){
	window.location.href=url+'/url/totalsaleList';
}