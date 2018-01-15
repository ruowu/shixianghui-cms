var url = url();
var token = localStorage.getItem("sixinghuiToken");
var files;
layui.use([ 'element', 'layer', 'form', 'laydate', 'laypage', 'upload' ],function() { // 独立版的layer无需执行这一句
		var element = layui.element;
		var $ = layui.jquery;
		var layer = layui.layer; // 独立版的layer无需执行这一句
		var form = layui.form;
		var laydate = layui.laydate;
		var laypage = layui.laypage;
		var upload = layui.upload;

		// 图片地址
		var imgUrls = "";
		// 设置边栏显示效果

		// =====================（默认加载填充）========================
		// 商品类型
		$.ajax({
			type : "get",
			url : url + "/comm/dictList",
			data : {
				"type" : 2
			},
			async : false,
			beforeSend : function(request) {
				request.setRequestHeader("token", token);
			},
			success : function(data) {
				var foodTypeArr = data.object;
				var len = foodTypeArr.length;
				$("#goodsType").empty();
				for ( var i = 0; i < len; i++) {
					$("#goodsType").append(
							"<option value="
									+ foodTypeArr[i].itemCode + ">"
									+ foodTypeArr[i].itemName
									+ "</option>");
					form.render();
				};
			},
			error : function() {
			}
		});
		// 商品单位
		$.ajax({
			type : "get",
			url : url + "/comm/dictList",
			data : {
				"type" : 3
			},
			async : false,
			beforeSend : function(request) {
				request.setRequestHeader("token", token);
			},
			success : function(data) {
				var foodTypeArr = data.object;
				var len = foodTypeArr.length;
				$("#units").empty();
				for ( var i = 0; i < len; i++) {
					$("#units").append(
							"<option value="
									+ foodTypeArr[i].itemCode + ">"
									+ foodTypeArr[i].itemName
									+ "</option>");
					form.render();
				}
				;
			},
			error : function() {
			}
		});
		// 点击增加商品的保存按钮
		$("#add_save").on("click",function() {
			var data = {};
			data.goodsType = $("#goodsType").val();
			data.goodsName = $("#goodsName").val();
			data.tradePrice = $("#tradePrice").val();
			data.marketPrice = $("#marketPrice").val();
			data.unit = $("#units").val();
			data.imgUrl = $("#imgUrls").val();
			$.ajax({
					type : "post",
					url : url+ "/goods/saveOrUpdate",
					data : data,
					async : false,
					beforeSend : function(request) {
						request.setRequestHeader("token",token);
					},
					success : function(data) {
						window.location.href = "/url/goodsList";
						alert("新增商品成功")
					},
					error : function() {
						alert("新增商品失败")
					}
				});
		});
		// 多文件列表示例
		var demoListView = $('#demoList'), uploadListIns = upload.render({
			elem : '#selectPic',
			url : url + '/comm/uploadByFile',
			accept : 'file',
			multiple : true,
			auto : false,
			bindAction : '#upload',
			choose : function(obj) {
				files = obj.pushFile(); // 将每次选择的文件追加到文件队列
				// 读取本地文件
				obj.preview(function(index, file,result) {
							var tr = $([
									'<tr id="upload-'+ index + '">',
									'<td>'+ '<img src="'+ result+ '" alt="'+ file.name+ '" class="layui-upload-img">'+ '</td>',
									'<td>等待上传</td>',
									'<td>',
									'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>',
									'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>',
									'</td>', '</tr>' ]
									.join(''));

				// 单个重传
				tr.find('.demo-reload').on('click',function() {
							obj.upload(index,file);
						});

					// 删除
					tr.find('.demo-delete').on('click',function() {
										delete files[index]; // 删除对应的文件
										tr.remove();
									});

						demoListView.append(tr);
					});
				},
				done : function(res, index, upload) {
					if (res.code == 000000) { // 上传成功
						console.log(res)
						console.log(res.object)
						imgUrls = imgUrls+res.object+","
						$("#imgUrls").val(imgUrls);
						var tr = demoListView.find('tr#upload-'+ index), 
						tds = tr.children();
						tds.eq(1).html('<span style="color: #5FB878;">上传成功</span>');
						tds.eq(2).html(''); // 清空操作
						delete files[index]; // 删除文件队列已经上传成功的文件
						return;
					}
					this.error(index, upload);
				},
				error : function(index, upload) {
					var tr = demoListView.find('tr#upload-'+ index), tds = tr.children();
					tds.eq(1).html('<span style="color: #FF5722;">上传失败</span>');
					tds.eq(2).find('.demo-reload').removeClass('layui-hide'); // 显示重传
				}
			});

		// 点击返回列表
	    $("#return_list").on("click",function () {
	    	window.location.href="/url/goodsList";
	    });
		// 创建时间
		laydate.render({
			elem : '#createtime' // 指定元素
		});
	});
