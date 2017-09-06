$(function(){
	$('#tab').datagrid({    
	    url:'/webproject/usercontroller.do',
	    title:'用户信息',
	    iconCls:'icon-man',
	    collapsible:true,
	    rownumbers:true,
	    idField:'userid',
	    loadMsg:'正在加载，请稍候........',
	    queryParams:{type:'list'},
	    columns:[[
	              {field:'chk',checkbox:true},
	              {field:'userid',title:'用户编号'},    
		  	      {field:'uemail',title:'用户邮箱'},    
		  	      {field:'uloginid',title:'用户名'},
		  	      {field:'upassword',title:'密码'},
		  	      {field:'usex',title:'性别'},
		  	      {field:'uaddress',title:'地址'},
		  	      {field:'utel',title:'电话'},
		  	      {field:'ustateid',title:'状态编号'},
		  	      {field:'uroleid',title:'用户等级'},
		  	  ]],
		toolbar: [{
			iconCls: 'icon-edit',
			text:'编辑用户',
			handler: function(){
				var userinf = $('#tab').datagrid('getSelected');
				if(userinf==null){
					$.messager.alert('修改用户','请选择要修改的用户','info');
				}else{
					$('#uname').textbox('setValue',userinf.uloginid);
					$('#uname').textbox({editable:false});
					$('#upsw').textbox('setValue',userinf.upassword);
					$('#email').textbox('setValue',userinf.uemail);
					$('#tel').textbox('setValue',userinf.utel);
					$('#address').textbox('setValue',userinf.uaddress);
					$('#cc').textbox('setValue',userinf.usex);
					$("#dd").dialog({
						closed:false,
						title:'修改用户',
						iconCls:'icon-edit',
						buttons: [{text:'保存修改',iconCls:'icon-edit',handler:function(){
						$.ajax({
							type:'post',
							url:'/webproject/usercontroller.do?type=edit',
							data:$('#f1').serialize(),
							success:function(data){
								if(data=="1"){
									//重新刷新
									$("#dd").dialog("close");
									$('#tab').datagrid('reload');
									$('#f1').form('clear');
									$('#cc').textbox('setValue','男');
									$('#uname').textbox({editable:true});
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
		},'-',{
			iconCls: 'icon-add',
			text:'添加用户',
			handler: function(){
				$("#dd").dialog({
					closed:false,
					title:'添加用户',
					iconCls:'icon-edit',
					buttons: [{text:'添加',iconCls:'icon-save',handler:function(){
						$('#uname').textbox({editable:true});
						var uname = $('#uname').textbox('getValue');
						$.messager.confirm('添加确认', "您确认要添加"+uname+"吗？", function(r){
							if (r){
								$.ajax({
									type:'post',
									url:'/webproject/usercontroller.do?type=add',
									data:$('#f1').serialize(),
									success:function(data){
										if(data=="1"){
											//重新刷新
											$('#tab').datagrid('reload');
											$("#dd").dialog("close");
											$('#f1').form('clear');
											$('#cc').textbox('setValue','男');
											$('#uname').textbox({editable:true});
											$.messager.alert('添加用户','添加成功！','info');
										}else if(data=="0"){
											$.messager.alert('添加用户','添加失败,请正确填写','info');
										}
										
									}
								});
							}
							});
					}
					},{text:'取消',iconCls:'icon-remove',handler:function(){
						$.messager.confirm('取消确认', "您确认要取消添加吗？", function(r){
							if (r){
								$("#dd").dialog("close");
								$('#f1').form('clear');
								$('#cc').textbox('setValue','男');
								$('#uname').textbox({editable:true});
						}
						});
					}
					}]
			
			});
			}
		},'-',{
			iconCls: 'icon-remove',
			text:'删除用户',
			handler: function(){
				var row = $('#tab').datagrid('getSelected');
				var rows = $('#tab').datagrid('getSelections');
				
				if(row==null){
					$.messager.alert('删除用户','清选择要删除的用户','info');
				}
				else{
					$.messager.confirm('删除确认', "您确认要删除"+row.uloginid+"吗？", function(r){
						if (r){
						    // 退出操作;
							$.ajax({
								type:'post',
								url:'/webproject/usercontroller.do',
								data:{type:'remove',userid:row.uloginid},
								success:function(data){
									if(data=="1"){
										//重新刷新
										$('#tab').datagrid('reload');
										$.messager.alert('删除用户','刪除成功！','info');
									}else if(data=="0"){
										$('#tab').datagrid('reload');
										$.messager.alert('删除用户','删除失败','info');
									}
								}
							});
						}
					});
				}
			}
		}]
	}); 
})
