//$(function(){
//	$('#uname').on('input',function(){
//		$('form table tr:first td:last').html('<font color=gforestgreen>通过</font>');
//		if($(this).val()=='')
//			$('table tr:first td:last').html('<font color=red>账号不能为空</font>');
//	})
//	$('#upassword').on('input',function(){
//		$('form table tr:eq(1) td:last').html('<font color=gforestgreen>通过</font>');
//		alert($(this).val().length)
//		if($(this).val().length<6)
//			$('form table tr:eq(1) td:last').html('<font color=red>账号不能为空</font>');
//	})
//	$('#uPasswordConfirm').on('input',function(){
//		$('form table tr:eq(2) td:last').html('<font color=gforestgreen>通过</font>');
//		var upassword=$('form table tr:eq(1) td:last');
//		alert($(this).val())
//		if($(this).val()!=upassword.val()&&$(this).val().length>=6)
//			$('form table tr:eq(2) td:last').html('<font color=red>账号不能为空</font>');
//	})
//	$('#ucode').on('input',function(){
//		$('form table tr:eq(3) td:last').html('<font color=gforestgreen>通过</font>');
//		if($(this).val()!=upassword.val()&&$(this).val().length>=6)
//			$('form table tr:eq(2) td:last').html('<font color=red>账号不能为空</font>');
//	})
//})