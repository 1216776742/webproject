$(function() {
	$('#cc').combobox({    
		url:'/webproject/cate.do',    
		valueField:'cid',    
		textField:'cname',
		onSelect:function(data){
			var cid = $('#cc').combobox('getValue');
			loadGoods(cid);
		},
		onLoadSuccess:function(){
			var datas = $(this).combobox('getData');
			if (datas.length>0) {
				$(this).combobox('setValue',datas[0].cid);
				var cid = $('#cc').combobox('getValue');
				loadGoods(cid);
			}
		}
		
	}); 
})
function loadGoods(cid){
	$('#tb').datagrid({
		url:'/webproject/goods.do',
		queryParams:{cid:cid},
		title:'商品数据',
		iconCls:'icon-ok',
		collapsible:true,
		pagination:true,
		pageSize:10,
		rownumbers:true,
//		singleSelect:true,
	    idField:'gid',
		pageList:[10,20,30,40],
		columns:[[
	              {field:'chk',checkbox:true},
	              {field:'gid',title:'商品编号'},    
		  	      {field:'gtitle',title:'商品名称'},    
		  	      {field:'gauthor',title:'供应商'},
		  	      {field:'gsaleprice',title:'进价'},
		  	      {field:'ginprice',title:'售价'},
		  	      {field:'gimg',title:'图片名称',formatter: function(value,row,index){
		  	          return '<img src="images/bookcover/' + value + '.jpg" style="height:50px;">';
		          }},
		  	      {field:'gclicks',title:'访问量'},
		  	      {field:'cid',title:'类别编号'},
		  	      {field:'pid',title:'出版社编号'},
		  	  ]],
		  	toolbar: [{
				iconCls: 'icon-edit',
				text:'编辑商品',
				handler: function(){
					var goodsinf = $('#tb').datagrid('getSelected');
					if(goodsinf==null){
						$.messager.alert('修改用户','请选择要修改的用户','info');
					}else{
						$('#gid').textbox('setValue',googsinf.gid);
						$("#tab").dialog({
							closed:false,
							title:'修改用户',
							iconCls:'icon-edit',
							buttons: [{text:'保存修改',iconCls:'icon-edit',
								handler:function(){
								$.ajax({
									type:'post',
//									url:'/webproject/usercontroller.do?type=edit',
									data:$('#f1').serialize(),
									success:function(data){
										if(data=="1"){
											//重新刷新
											$('#tab').datagrid('reload');
											$('#f1').form('clear');
											$.messager.alert('编辑用户','编辑用户成功','info');
										}else if(data=="0"){
											$.messager.alert('编辑用户','编辑用户失败','info');
										}
								}
							});
						}
								}]
						});
					}
				}
//						$("#dd").dialog({
//							closed:false,
//							title:'修改用户',
//							iconCls:'icon-edit',
//							buttons: [{text:'保存修改',iconCls:'icon-edit',handler:function(){
//							$.ajax({
//								type:'post',
//								url:'/webproject/usercontroller.do?type=edit',
//								data:$('#f1').serialize(),
//								success:function(data){
//									if(data=="1"){
//										//重新刷新
//										$("#dd").dialog("close");
//										$('#tab').datagrid('reload');
//										$('#f1').form('clear');
//										$('#cc').textbox('setValue','男');
//										$('#uname').textbox({editable:true});
//										$.messager.alert('编辑用户','编辑用户成功','info');
//									}else if(data=="0"){
//										$.messager.alert('编辑用户','编辑用户失败','info');
//									}
//							}
//						});
//					}
//							}]
//					
//					});
//						}
//					}
			},'-',{
				iconCls: 'icon-add',
				text:'添加商品',
				handler: function(){
					$("#dd").dialog({
						closed:false,
						title:'添加商品',
						iconCls:'icon-edit',
						buttons: [{text:'添加',iconCls:'icon-save',
//							handler:function(){
//							$('#uname').textbox({editable:true});
//							var uname = $('#uname').textbox('getValue');
//							$.messager.confirm('添加确认', "您确认要添加"+uname+"吗？", function(r){
//								if (r){
//									$.ajax({
//										type:'post',
//										url:'/webproject/usercontroller.do?type=add',
//										data:$('#f1').serialize(),
//										success:function(data){
//											if(data=="1"){
//												//重新刷新
//												$('#tab').datagrid('reload');
//												$("#dd").dialog("close");
//												$('#f1').form('clear');
//												$('#cc').textbox('setValue','男');
//												$('#uname').textbox({editable:true});
//												$.messager.alert('添加用户','添加成功！','info');
//											}else if(data=="0"){
//												$.messager.alert('添加用户','添加失败,请正确填写','info');
//											}
//											
//										}
//									});
//								}
//								});
//						}
//						},{text:'取消',iconCls:'icon-remove',
//							handler:function(){
//							$.messager.confirm('取消确认', "您确认要取消添加吗？", function(r){
//								if (r){
//									$("#dd").dialog("close");
//									$('#f1').form('clear');
//									$('#cc').textbox('setValue','男');
//									$('#uname').textbox({editable:true});
//							}
//							});
//						}
						}]
				
				});
				}
			},'-',{
				iconCls: 'icon-remove',
				text:'删除商品',
//				handler: function(){
//					var row = $('#tab').datagrid('getSelected');
//					var rows = $('#tab').datagrid('getSelections');
//					
//					if(row==null){
//						$.messager.alert('删除用户','清选择要删除的用户','info');
//					}
//					else{
//						$.messager.confirm('删除确认', "您确认要删除"+row.uloginid+"吗？", function(r){
//							if (r){
//							    // 退出操作;
//								$.ajax({
//									type:'post',
//									url:'/webproject/usercontroller.do',
//									data:{type:'remove',userid:row.uloginid},
//									success:function(data){
//										if(data=="1"){
//											//重新刷新
//											$('#tab').datagrid('reload');
//											$.messager.alert('删除用户','刪除成功！','info');
//										}else if(data=="0"){
//											$('#tab').datagrid('reload');
//											$.messager.alert('删除用户','删除失败','info');
//										}
//									}
//								});
//							}
//						});
//					}
//				}
			}]

	})
}