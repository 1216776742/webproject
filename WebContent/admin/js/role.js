$(function(){
$('#tt').datagrid({
    title:'DataGrid - DetailView',
    width:1000,
    height:500,
    remoteSort:false,
    singleSelect:true,
    nowrap:false,
    fitColumns:true,
    url:'/webproject/rolecontroller.do',
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
    view: detailview,
    detailFormatter: function(rowIndex, rowData){
        return '<table><tr>' +
//                '<td rowspan=2 style="border:0"><img src="images/' + rowData.itemid + '.png" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>Attribute: ' + rowData.utel + '</p>' +
                '<p>Status: ' + rowData.uaddress + '</p>' +
                '</td>' +
                '</tr></table>';
    },
    toolbar: [{
		iconCls: 'icon-edit',
		text:'编辑用户',
		handler: function(){}
	},]
});
});


