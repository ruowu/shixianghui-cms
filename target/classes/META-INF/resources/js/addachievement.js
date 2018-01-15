var url=url();
var token=localStorage.getItem("sixinghuiToken");


layui.use(['element','layer','form','laydate'], function(){ //独立版的layer无需执行这一句
    var element = layui.element;
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    // =====================左侧菜单显示当前========================
    document.getElementById("user").classList.remove("layui-nav-itemed");
    document.getElementById("sale").setAttribute("class","layui-nav-item  layui-nav-itemed");
    //============ 销售时间=============
    laydate.render({
        elem: '#saleTime' //指定元素
    });
    //============ 商品回显=============
    $.ajax({
        type:"get",
        url:  url+"/goods/goodsListByName",
        data:{"name":""},
        beforeSend: function(request) {
            request.setRequestHeader("token", token);
        },
        success:function (data) {
            var goodsNameArr=data.object;
            var len=goodsNameArr.length;
            for(var i=0;i<len;i++){
                $("#addGoods").append("<option mprice="+goodsNameArr[i].marketPrice+" tprice="+goodsNameArr[i].tradePrice+" value="+goodsNameArr[i].goodsId+">"+goodsNameArr[i].goodsName+"</option>")
            };
            form.render();
        },
        error:function () {
            alert("数据错误")
        }
    });

    /**
     * 商品选择事件
     */
    form.on('select(addGoods)', function(data){
        $("#selectTypeId").val("");
        form.render();
    });

    /**
     * 商品类型选择事件
     */
    form.on('select(selectType)', function(data){
        var goodsId = $("#addGoods option:selected").attr("value");
        var marketPrice = $("#addGoods option:selected").attr("mprice");
        var tradePrice = $("#addGoods option:selected").attr("tprice");
        if(data.value==0){
            $("#oldPrice").val(marketPrice);
        }else{
            $("#oldPrice").val(tradePrice);
        }
        var oldPri=$("#oldPrice").val()
        var newPri=$("#newPri").val()
        if(newPri!=""){
            $("#discount").val(newPri/oldPri);
        }
    });
    // ===========回显计算后折扣=============
    $("#newPri").change(function () {
        var oldPri=$("#oldPrice").val()
        var newPri=$("#newPri").val()
        var goodsNum=$("#goodsNum").val()
        if(oldPri!=""){
            $("#discount").val(newPri/oldPri);
        }
        if(goodsNum!=""){
            $("#totalPri").val(newPri*goodsNum);
        }
    })
   // ===========回显计算后总价=============
    $("#goodsNum").change(function () {
        var newPri=$("#newPri").val()
        var goodsNum=$("#goodsNum").val()
        if(newPri!=""){
            $("#totalPri").val(newPri*goodsNum);
        }
    })
    // ===========销售员=============
    $.ajax({
        type:"get",
        url:  url+"/user/getMySalers",
        data:{"userName":""},
        beforeSend: function(request) {
            request.setRequestHeader("token", token);
        },
        success:function (data) {

            var sellerNameArr=data.object;
            var len=sellerNameArr.length;
            for(var i=0;i<len;i++){
                if(userInfo.userId==sellerNameArr[i].userId){
                    $("#sellerName").append("<option value="+sellerNameArr[i].userId+" selected>"+sellerNameArr[i].name+"</option>")
                }else{
                    $("#sellerName").append("<option value="+sellerNameArr[i].userId+">"+sellerNameArr[i].name+"</option>")
                }
            };
            form.render();
        },
        error:function () {
            alert("数据错误")
        }
    });

    // =================购买人员模糊查询（点击确定时可能提交的字段）=========
    $("#buyer_name").keyup(function () {
        var searchStr=$("#buyer_name").val();
        if(searchStr!=""){
            $.ajax({
                type:"get",
                url:  url+"/user/getMySalers",
                data:{"userName":searchStr},
                async:false,
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function (data) {
                    if(data.code=="000000"){
                        $("#buyers-name-list").removeClass("layui-hide");
                        var html="";
                        var len=data.object.length;
                        for(var i=0;i<len;i++){
                            html+="<li class='buyerLi' value='"+data.object[i].userId+"'>"+data.object[i].name+"</li>";
                        };
                        $("#buyers-name-list").html(html);
                        $("#buyers-name-list").on("click",".buyerLi",function (e) {
                            e.stopPropagation();
                            e.preventDefault();
                            var value1=$(this).html();
                            var value2=$(this).val();
                            $("#buyer_name").val(value1);
                            $("#buyer_name").attr("buyerId",value2);
                            $("#buyers-name-list").addClass("layui-hide");
                        });
                    };
                },
                error:function () {
                }
            });
        }else {
            $("#salesman_name_list").addClass("layui-hide");
        }
    });
    // =================点击提交按钮=========
    $("#doAdd").on("click",function () {
        var data={};
        data.goodsId=$("#addGoods").val();
        data.saleType=$("#selectTypeId").val();
        data.originalPrice  =$("#oldPrice").val();
        data.discount=$("#discount").val();
        data.discountReason=$("#discountReason").val();
        data.salePrice=$("#newPri").val();
        data.number=$("#goodsNum").val();
        data.totalPrice=$("#totalPri").val();
        data.saleTimeStr=$("#saleTime").val();
        data.salerId=$("#sellerName option:selected").val();
        data.salerName=$("#sellerName option:selected").text();
        data.buyerId=$("#buyer_name").attr("buyerId");
        data.buyerName=$("#buyer_name").val();
        $.ajax({
            type:"post",
            url:  url+"/saleRecord/saveOrUpdate",
            data:data,
            async:false,
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                console.log(data)
                alert("新增成功")

            },
            error:function () {
                alert("新增失败")
            }
        });
    })
});

