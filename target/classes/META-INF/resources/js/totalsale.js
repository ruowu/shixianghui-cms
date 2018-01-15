var url=url();
var token=localStorage.getItem("sixinghuiToken");
layui.use(['element','laydate','form','table','laypage'],function(){

    var $= layui.jquery;
    var element = layui.element;
    var laydate = layui.laydate;
    var form = layui.form;
    var table = layui.table;
    var laypage = layui.laypage;
    // =====================左侧菜单显示当前========================
    document.getElementById("user").classList.remove("layui-nav-itemed");
    document.getElementById("sale").setAttribute("class","layui-nav-item  layui-nav-itemed");
    // =====================查询开始时间和结束时间========================
    laydate.render({
        elem: '#start-time'//开始时间
    });
    laydate.render({
        elem: '#end-time'//结束时间
    });

    // =====================显示所有销售信息==================
    function showAllSellMsg(object) {
        // =====================分页========================
        var count;
        var limit;
        var curr="1";
        object.currentPage=curr;
        $.ajax({
            type:"get",
            url:  url+"/saleRecord/getList",
            data:object,
            async:false,
            beforeSend: function(request) {
                request.setRequestHeader("token", token);
            },
            success:function (data) {
                count=data.object.total;
                limit=data.object.pageSize;
            },
            error:function () {
            }
        });
        laypage.render({
            elem: 'salePage'
            ,jump: function(obj){
                curr= obj.curr;
                $.ajax({
                    type:"get",
                    url:  url+"/saleRecord/getList",
                    data:object,
                    async:false,
                    beforeSend: function(request) {
                        request.setRequestHeader("token", token);
                    },
                    success:function (data) {
                        count=data.object.total;
                        limit=data.object.pageSize;
                        if(data.code =="000000"){
                            var sellerArr=data.object.list;
                            var len=sellerArr.length;
                            var html='';
                            for (var i=0;i<len;i++){
                                //销售类型
                               function type(types) {
                                   if(types==0){
                                       return "批发";
                                   }else if(types==1){
                                       return "零售";
                                   };
                               };
                               var types=type(sellerArr[i].saleType);
                               html+="<tr><td>"+sellerArr[i].goodsName +"</td>";
                               html+="<td>"+types+"</td>";
                               html+="<td>"+sellerArr[i].originalPrice+"</td>";
                               html+="<td>"+sellerArr[i].salePrice+"</td>";
                               html+="<td>"+sellerArr[i].number+"</td>";
                               html+="<td>"+sellerArr[i].totalPrice+"</td>";
                               html+="<td>"+sellerArr[i].saleTimeStr+"</td>";
                               html+="<td>"+sellerArr[i].salerName+"</td>";
                               html+="<td>"+sellerArr[i].buyerName+"</td>";
                               html+="<td>"+sellerArr[i].discount+"</td>";
                               html+="<td>"+sellerArr[i].discountReason+"</td></tr>";
                            };
                            $("#seller-msg").html(html);
                        };
                    },
                    error:function () {
                    }
                });
            }
            ,count:count//数据总数，从服务端得到
            ,limit:limit
        });
    };
    showAllSellMsg({});
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
    // =====================商品名称========================
        if($("#commodity-type").val("")){
            $.ajax({
                type:"get",
                url:  url+"/goods/goodsListByType",
                data:{"type":""},
                async:false,
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function (data) {
                    for (var i=0;i<data.object.length;i++ ){
                        $("#commodity-name") .append("<option value="+data.object[i].goodsId+">"+data.object[i].goodsName+"</option>");
                        form.render();
                    }

                },
                error:function () {
                }
            });
        }
        form.on('select(commodity-type)', function(data){
            var typeVlue=$("#commodity-type").val();
            $.ajax({
                type:"get",
                url:  url+"/goods/goodsListByType",
                data:{"type":typeVlue},
                async:false,
                beforeSend: function(request) {
                    request.setRequestHeader("token", token);
                },
                success:function (data) {
                    console.log("商品类型",data)
                    $("#commodity-name").html("<option value=\"\">选择商品名称</option>");
                    for (var i=0;i<data.object.length;i++ ){
                        $("#commodity-name") .append("<option value="+data.object[i].goodsId+">"+data.object[i].goodsName+"</option>");
                    }
                    form.render();
                },
                error:function () {
                }
            });
        });
    // =================销售人员模糊查询（点击确定时可能提交的字段）=========
    $("#seller_name").keyup(function () {
        var searchStr=$("#seller_name").val();
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
                        $("#salesman_name_list").removeClass("layui-hide");
                        var html="";
                        var len=data.object.length;
                        for(var i=0;i<len;i++){
                            html+="<li class='sellerLi' value='"+data.object[i].userId+"'>"+data.object[i].name+"</li>";
                        };
                        $("#salesman_name_list").html(html);
                        if(len==0){
                            $("#userId").val("");
                        }
                        $("#salesman_name_list").on("click",".sellerLi",function (e) {
                            e.stopPropagation();
                            e.preventDefault();
                            var value1=$(this).html();
                            var value2=$(this).val();
                            $("#seller_name").val(value1);
                            $("#userId").val(value2);
                            // $("#seller_name").val(value1);
                            $("#salesman_name_list").addClass("layui-hide");
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
    $("#seller_name").blur(function () {
        var salerId = $("#userId").val();
        if(salerId==null || salerId==""){
            $("#seller_name").val("");
        }
    });
    // =====================查询事件（点击确定按钮）========================
    $(".do-search-btn").on("click",function () {
        // 发送ajax请求
        var data={};
        data.startDate=$("#start-time").val();
        data.endDate=$("#end-time").val();
        data.goodsType=$("#commodity-type").val();
        data.goodsId=$("#commodity-name").val();
        data.salerIds=$("#userId").val();
        console.log(data.salerIds);
        // 开始时间
        showAllSellMsg(data);
        $("#start-time").val("");
        $("#end-time").val("");
        $("commodity-type").val("");
        $("commodity-name").val("");
    });

});