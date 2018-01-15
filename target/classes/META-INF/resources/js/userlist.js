var url=url();
var token=localStorage.getItem("sixinghuiToken");
layui.use(['element','layer','form','laydate','laypage'], function(){ //独立版的layer无需执行这一句
    var element = layui.element;
    var $ = layui.jquery;
    var layer = layui.layer; //独立版的layer无需执行这一句
    var form = layui.form;
    var laydate = layui.laydate;
    var laypage = layui.laypage;
    // 页面加载完成后回显数据调用
    showUser();
    // 设置边栏显示效果

    // 点击增加
    $("#addUser").on("click",function () {
        layer.open({
            type:1,
            area: ['400px', '510px'],
            content:$("#addUserForm")//弹出层内容
        });

        // 点击增加用户的保存按钮
        $("#add_save").on("click",function () {
            var data={};
            data.userName=$("#userName").val();
            data.passWord=md5($("#passWord").val());
            data.name=$("#names").val();
            data.gender=$('input:radio[name="gender"]:checked').val();
            data.birthDate=$("#formtime").val();
            data.createTime=$("#createTime").val();
            data.userType=$("#userType").val();
            $.ajax({
                type:"post",
                url:  url+"/user/saveOrUpdate",
                data:data,
                async:false,
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function (data) {
                        alert("新增用户成功")
                        showUser();
                },
                error:function () {
                    alert("新增用户失败")
                }
            });
        });
    });
    // 点击修改
    $("#userTable").on("click","table td .update",function () {
        layer.open({
            type:1,
            area: ['400px', '500px'],
            content:$("#addUserForm")//弹出层内容
        });
        var userId=this.parentNode.parentNode.firstChild.textContent;

        // 传递修改id得到该id的内容回显
        $.ajax({
            type:"get",
            url: url+"/user/getUserById",
            async:false,
            data:{userId:userId},
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                var obj=data.object;
                // 回显数据
               $("#userId").val(obj.userId);
               $("#higherUid").val(obj.higherUid);
               $("#createUid").val(obj.createUid);
               $("#passWord").val("");
               $("#userName").val(obj.userName);
               $("#names").val(obj.name);
                $("input:radio[name='gender'][value="+obj.gender+"]").attr('checked','true');
                form.render();
                laydate.render({
                    elem: '#formtime' //指定元素
                    ,value:obj.birthDate
                });
               $("#userType").val(obj.userType);
               // 点击保存时发送请求
               $("#add_save").on("click",function () {
                   var data={};
                   data.userId=$("#userId").val();
                   data.higherUid=$("#higherUid").val();
                   data.createUid=$("#createUid").val();
                   data.userName=$("#userName").val();
                   data.passWord=md5($("#passWord").val());
                   data.name=$("#names").val();
                   data.gender=$('input:radio[name="gender"]:checked').val();
                   data.birthDate=$("#formtime").val();
                   data.createTime=$("#createTime").val();
                   data.userType=$("#userType").val();
                   if( $("#passWord").val()!=''){
                       $.ajax({
                           type:"post",
                           url:  url+"/user/saveOrUpdate",
                           data:data,
                           async:false,
                           beforeSend: function(request) {
                               request.setRequestHeader("token", token);
                           }
                           ,success:function () {
                               alert("修改成功");
                               showUser();
                           }
                           ,error:function () {
                               alert("修改失败");
                           }
                       });
                   };
               });
            },
            error:function () {
            }
        });
    });
    // 点击删除
    $("#userTable").on("click","table td .delUser",function (){
        var userId=this.parentNode.parentNode.firstChild.textContent;
        console.log(userId);
        var msg="确定删除吗？"
        if(confirm(msg)==true){
            $.ajax({
                type:"get",
                url:url+"/user/deleteById",
                data:{"userId":userId},
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function () {
                    alert("删除成功")
                    showUser();
                },
                error:function () {
                    alert("删除失败");
                }
            });
        }else {
            return false;
     };

    });
    // 出生日期
    var enddate= new Date();
    laydate.render({
        elem: '#formtime' //指定元素
        ,max:'new Date()'
    });
    // 创建时间
    laydate.render({
        elem: '#createtime' //指定元素
    });
    // 页面加载完成后回显数据
    function showUser() {
        $.ajax({
            type:"get",
            url: url+"/user/allUsers",
            data:'',
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                if(data.code==000000){
                    var userObj=data.object;
                    var len=userObj.length;
                    // 分页
                    var data=userObj;
                    laypage.render({
                        elem: 'userPage'
                        ,limit:8
                        ,count: len //数据总数，从服务端得到
                        ,jump: function(obj){
                            //模拟渲染
                            document.getElementById('userBox').innerHTML = function(){
                                var arr = []
                                    ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                                layui.each(thisData, function(index, item){
                                         var html='';
                                         html+="<tr><td style='display: none'>"+item.userId+"</td>";
                                         html+="<td>"+item.userName+"</td>";
                                         html+="<td>"+item.name+"</td>";
                                         html+="<td>"+item.createTimeStr+"</td>";
                                         html+="<td><button class='layui-btn layui-btn-mini layui-btn-normal update'>修改</button><button class='layui-btn layui-btn-mini layui-btn-danger delUser'>删除</button></td></tr>";
                                         arr.push(html);
                                });
                                return arr.join('');
                            }();
                        }
                    });
                };
            },
            error:function () {
            }
        });
    };
    // 动态添加用户类型
    (function () {
        $.ajax({
            type:"get",
            async:false,
            url: url+"/comm/dictList",
            data:{type:'1'},
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                var len=data.object.length;
                for (var i=0;i<len;i++){
                    var itemCode=data.object[i].itemCode;
                    var itemName=data.object[i].itemName;
                    $("#userType").append("<option value='"+itemCode+"'>"+itemName+"</option>");
                }
                form.render("select");
                $("#add_save").on("click",function () {
                    var data={};
                    data.userName=$("#userName").val();
                    data.passWord=md5($("#passWord").val());
                    data.name=$("#names").val();
                    data.gender=$('input:radio[name="gender"]:checked').val();
                    data.birthDate=$("#formtime").val();
                    data.userType=$("#userType").val();
                    var dataLen=data.length;
                    if(dataLen==6){
                        $.ajax({
                            type:"post",
                            url: url+"/user/saveOrUpdate",
                            async:false,
                            data:data,
                            beforeSend: function(request) {
                                request.setRequestHeader("token", token);
                            },
                            success:function (data) {
                                alert("新增用户成功")
                                // 页面加载完成后回显数据调用
                                showUser();
                            },
                            error:function () {
                                alert("新增用户失败");
                            }
                        });
                    };
                });
            },
            error:function () {
                console.log("用户数据类型获取失败")
            }
        });
    })();
});

