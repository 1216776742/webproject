$(function(){
	$('#btn').on('click',function(){
		$('#f1').ajaxSubmit({
			dataType:"text",
			url:'/webproject/test.do',
			success:function(data){
				if(data=="1"){
					alert("ok");
					//清空input
					$('#f1').form('clear');
					$('#dd').html('');
				}
			}
	});
//		$.ajax({
//		type:'post',
//		url:'/form_test/test.do',
//		data:$('#f1').serialize(),
//		success:function(data){
//			if(data=="1"){
//				//清空input
//				$('#f1').form('clear');
//				$('#dd').html('');
//			}
//		},
//		error:function(data){
//			alert("上传错误！"); 
//		}
//	});
	});

});

