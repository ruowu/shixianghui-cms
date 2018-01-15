var url=url();
var token=localStorage.getItem("sixinghuiToken");
layui.use(['element','layer','form','laydate','laypage','upload','carousel'], function(){ //独立版的layer无需执行这一句
    var element = layui.element;
    var $ = layui.jquery;
    var layer = layui.layer; //独立版的layer无需执行这一句
    var form = layui.form;
    var laydate = layui.laydate;
    var laypage = layui.laypage;
	var upload = layui.upload;
	var carousel = layui.carousel
    // 页面加载完成后回显数据调用
    showGoods();
    // 设置边栏显示效果
    
 // =====================商品类型（默认加载填充）========================
    $.ajax({
        type:"get",
        url:  url+"/comm/dictList",
        data:{"type":2},
        async:false,
        beforeSend: function(request) {
            request.setRequestHeader("token", token);
        },
        success:function (data) {
            var foodTypeArr=data.object;
            var len=foodTypeArr.length;
            for(var i=0;i<len;i++){
               $("#commodity-type") .append("<option value="+foodTypeArr[i].itemCode+">"+foodTypeArr[i].itemName+"</option>");
               form.render();
            };
        },
        error:function () {
        }
    });

    //点击搜索
    $(".goods_search_btn").on("click",function(){
    	var goodsType = $("#commodity-type").val();
    	$.ajax({
            type:"get",
            url: url+"/goods/goodsListByType",
            data:"type="+goodsType,
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
            	console.log(data);
                if(data.code==000000){
                    var goodsObj=data.object;
                    var len=goodsObj.length;
                    // 分页
                    var data=goodsObj;
                    laypage.render({
                        elem: 'goodsPage'
                        ,limit:8
                        ,count: len //数据总数，从服务端得到
                        ,jump: function(obj){
                            //模拟渲染
                            document.getElementById('goodsBox').innerHTML = function(){
                                var arr = []
                                    ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                                layui.each(thisData, function(index, item){
                                         var html='';
                                         html+="<tr><td style='display: none'>"+item.goodsId+"</td>";
                                         html+="<tr><td style='display: none'>"+item.imgUrl+"</td>";
                                         html+="<td>"+item.goodsName+"</td>";
                                         html+="<td>"+item.goodsTypeName+"</td>";
                                         html+="<td>"+item.marketPrice+"</td>";
                                         html+="<td>"+item.tradePrice+"</td>";
                                         html+="<td>"+(item.inventory==null?"":item.inventory)+"</td>";
                                         html+="<td>"+item.unitName+"</td>";
                                         html+="<td>"+(item.imgUrl==null?"没有上传图片":"<button class='layui-btn layui-btn-mini layui-btn-warm showImgs'>图片展示</button>")+"</td>";
                                         html+="<td><button class='layui-btn layui-btn-mini layui-btn-normal update'>修改</button><button class='layui-btn layui-btn-mini layui-btn-danger delGoods'>删除</button></td></tr>";
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
    });
    // 点击增加
    $("#addGoods").on("click",function () {
    	window.location.href="/url/goodsEdit";
    });
	//图片上传
    upload.render({
        elem: '#test2'
        ,url: '/upload/'
        ,multiple: true
        ,before: function(obj){
          //预读本地文件示例，不支持ie8
          obj.preview(function(index, file, result){
            $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
          });
        }
        ,done: function(res){
          //上传完毕
        }
      });
    // 点击修改
    $("#goodsTable").on("click","table td .update",function () {
        layer.open({
            type:1,
            area: ['400px', '500px'],
            content:$("#addGoodsForm")//弹出层内容
        });
        var goodsId=this.parentNode.parentNode.firstChild.textContent;

        // 传递修改id得到该id的内容回显
        $.ajax({
            type:"get",
            url: url+"/goods/getGoodsById",
            async:false,
            data:{goodsId:goodsId},
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                var obj=data.object;
                // 回显数据
               $("#goodsId").val(obj.goodsId);
               $("#higherUid").val(obj.higherUid);
               $("#createUid").val(obj.createUid);
               $("#passWord").val("");
               $("#goodsName").val(obj.goodsName);
               $("#names").val(obj.name);
                $("input:radio[name='gender'][value="+obj.gender+"]").attr('checked','true');
                form.render();
                laydate.render({
                    elem: '#formtime' //指定元素
                    ,value:obj.birthDate
                });
               $("#goodsType").val(obj.goodsType);
               // 点击保存时发送请求
               $("#add_save").on("click",function () {
                   var data={};
                   data.goodsId=$("#goodsId").val();
                   data.higherUid=$("#higherUid").val();
                   data.createUid=$("#createUid").val();
                   data.goodsName=$("#goodsName").val();
                   data.passWord=md5($("#passWord").val());
                   data.name=$("#names").val();
                   data.gender=$('input:radio[name="gender"]:checked').val();
                   data.birthDate=$("#formtime").val();
                   data.createTime=$("#createTime").val();
                   data.goodsType=$("#goodsType").val();
                   if( $("#passWord").val()!=''){
                       $.ajax({
                           type:"post",
                           url:  url+"/goods/saveOrUpdate",
                           data:data,
                           async:false,
                           beforeSend: function(request) {
                               request.setRequestHeader("token", token);
                           }
                           ,success:function () {
                               alert("修改成功");
                               showGoods();
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
    //点击图片展示
    $("#goodsTable").on("click","table td .showImgs",function (){
    	/**
    	 * 填充弹出层内容
    	 */
    	var imgUrlPath = url+"/comm/getImgByUrl?fileName=";
    	var imgLenth;
    	var thisImgUrl=this.parentNode.parentNode.childNodes[0].textContent;
    	var imgSplit = thisImgUrl.split(",");
    	var imgLenth = imgSplit.length-1;
    	var html="";
    	for (var i = 0; i < imgLenth; i++) {
    		html += "<div><img class='imgStyle' src='"+imgUrlPath+imgSplit[i]+"'></div>"
		}
    	$("#showImgBody").empty();
    	$("#showImgBody").append(html);
    	form.render();
    	carousel.render({
    		elem: '#showImg'
    			,width: '778px'
    				,height: '440px'
    					,interval: 5000
    	});
    	layer.open({
    		type:1,
    		title: "图片",
    		area: ['750px', '510px'],
    		content:$("#showImg")//弹出层内容
    	});
    	 
    });
    // 点击删除
    $("#goodsTable").on("click","table td .delGoods",function (){
        var goodsId=this.parentNode.parentNode.firstChild.textContent;
        console.log(goodsId);
        var msg="确定删除吗？"
        if(confirm(msg)==true){
            $.ajax({
                type:"get",
                url:url+"/goods/deleteById",
                data:{"goodsId":goodsId},
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function () {
                    alert("删除成功")
                    showGoods();
                },
                error:function () {
                    alert("删除失败");
                }
            });
        }else {
            return false;
     };

    });
    // 创建时间
    laydate.render({
        elem: '#createtime' //指定元素
    });
    // 页面加载完成后回显数据
    function showGoods() {
        $.ajax({
            type:"get",
            url: url+"/goods/goodsListByType",
            data:'',
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
            	console.log(data);
                if(data.code==000000){
                    var goodsObj=data.object;
                    var len=goodsObj.length;
                    // 分页
                    var data=goodsObj;
                    laypage.render({
                        elem: 'goodsPage'
                        ,limit:8
                        ,count: len //数据总数，从服务端得到
                        ,jump: function(obj){
                            //模拟渲染
                            document.getElementById('goodsBox').innerHTML = function(){
                                var arr = [],thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                                layui.each(thisData, function(index, item){
                                         var html='';
                                         html+="<tr><td style='display: none'>"+item.goodsId+"</td>";
                                         html+="<tr><td style='display: none'>"+item.imgUrl+"</td>";
                                         html+="<td>"+item.goodsName+"</td>";
                                         html+="<td>"+item.goodsTypeName+"</td>";
                                         html+="<td>"+item.marketPrice+"</td>";
                                         html+="<td>"+item.tradePrice+"</td>";
                                         html+="<td>"+(item.inventory==null?"":item.inventory)+"</td>";
                                         html+="<td>"+item.unitName+"</td>";
                                         html+="<td>"+(item.imgUrl==null?"没有上传图片":"<button class='layui-btn layui-btn-mini layui-btn-warm showImgs'>图片展示</button>")+"</td>";
                                         html+="<td><button class='layui-btn layui-btn-mini layui-btn-normal update'>修改</button><button class='layui-btn layui-btn-mini layui-btn-danger delGoods'>删除</button></td></tr>";
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
    // 动态添加商品类型
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
                    $("#goodsType").append("<option value='"+itemCode+"'>"+itemName+"</option>");
                }
                form.render("select");
                $("#add_save").on("click",function () {
                    var data={};
                    data.goodsName=$("#goodsName").val();
                    data.passWord=md5($("#passWord").val());
                    data.name=$("#names").val();
                    data.gender=$('input:radio[name="gender"]:checked').val();
                    data.birthDate=$("#formtime").val();
                    data.goodsType=$("#goodsType").val();
                    var dataLen=data.length;
                    if(dataLen==6){
                        $.ajax({
                            type:"post",
                            url: url+"/goods/saveOrUpdate",
                            async:false,
                            data:data,
                            beforeSend: function(request) {
                                request.setRequestHeader("token", token);
                            },
                            success:function (data) {
                                alert("新增用户成功")
                                // 页面加载完成后回显数据调用
                                showGoods();
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

