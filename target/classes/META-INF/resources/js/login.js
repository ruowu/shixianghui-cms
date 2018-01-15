
$(document).ready(function(){
    // 登陆验证
    doLogin ();
});
var url=url();

// 登陆验证
function doLogin () {
    // 点击提交时
    $(".loginBtn").on("click",function(){
        var param={};
        param.userName=$("#userName").val();
        param.passWord= $("#passWord").val();
        // 表单验证
        checkLogin(param.userName,param.passWord);
        param.passWord= md5($("#passWord").val());
        if(param.userName!=""&&param.passWord!=""){
            $.ajax({
                type:"get",
                url: url+"/user/login",
                data: param,
                success: function (data) {
                    if(data.code=="000000"){
                        // 登陆成功存储token
                        var token=data.object2;
                        localStorage.setItem("sixinghuiToken",token);
                        var user_info=JSON.stringify(data.object);
                        localStorage.setItem("user_info",user_info);
                        param={};
                        $.ajax({
                        	type:"get",
                            url: url+"/user/getAuthority",
                            data: param,
                            beforeSend: function(request) {
                                request.setRequestHeader("token", token);
                            },
                        	success: function (data) {
                        		if(data.code=="000000"){
                        			alert("登陆成功");
                        			var menu_list=JSON.stringify(data.object);
                        			localStorage.setItem("menu_list",menu_list);
                        			window.location.href="/url/empty";
                        		}else{
                        			alert("登陆失败");
                                    // 登陆失败清楚token
                                    localStorage.removeItem("sixinghuiToken")
                                    $("#userName").val("");
                                    $("#passWord").val("");
                        		}
                        	}
                        });
                    }else {
                        alert("登陆失败");
                        // 登陆失败清楚token
                        localStorage.removeItem("sixinghuiToken")
                       $("#userName").val("");
                       $("#passWord").val("");
                    }
                }
            })
        };
    });
};
// 表单验证
function checkLogin(userName,passWord) {
    if(!userName){
        alert("请填写用户名");
        return false;
    };
    if(!passWord){
        alert("请填写密码");
        return false;
    };
};
