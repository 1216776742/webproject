window.onload = function(){
	var xmlhttp = null;
	document.querySelector('#uname').onblur=checkUname;
	document.querySelector('#uemail').onblur=checkUemail;
	document.querySelector('#img').onclick=checkcode;
}
	//生成验证码：
	function checkcode(){
		this.src='codeServlet.do?id='+Math.random();
//        $("#img").attr("src","codeServlet.do?id="+Math.random());   

	}
	//检查邮箱(POST请求)
	function checkUemail(){
		var email = document.querySelector('#uemail').value;
		//1创建一个XMLHttpRequest对象;
		xmlhttp = new XMLHttpRequest();
		//2创建一个http请求;
		xmlhttp.open("POST", "checkemail.do", true);
		//3发送请求;
//		var data="email="+email+"&name="+"abc";
		//设置http请求头;
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send(email);
		//4接收服务器据;
		xmlhttp.onreadystatechange=emailcallback;
	}
	//检查账号(GET请求);
	 function checkUname(){
		//将账号拿到，再传到服务器去验证，验证后拿到服务器返回的验证结果
		var uname = document.querySelector('#uname').value;
		//AJAX与服务器通信是通过XMLHttpRequest实现的;
		//1、得到一个异步通信的组件对象
		xmlhttp = new XMLHttpRequest(); //省去针对IE浏览器(<7.0)创建此对象的兼容判断
		//2、使用此对象与服务器通信;
		//A、创建一个http请求(建立与服务器某一资源的通信);
		xmlhttp.open("POST", "checkname.do", true);
		//B发送请求;
		xmlhttp.send(uname);
		//C接收服务器返回的数据;
		xmlhttp.onreadystatechange = namecallback;
	}
function emailcallback(){
	if(xmlhttp.readyState==4){ //数据传输完成;
		if(xmlhttp.status==200){ //成功在客户端接收到数据;
			var data = xmlhttp.responseText;//接收服务器返回的字符串；1,0
			var sp = document.querySelector('#spemail');
			if(data=="1"){ //1:表示存在;
				sp.innerHTML='邮箱已被注册';
				sp.style.color='red';
			}else{
				sp.innerHTML='恭喜，可以使用';
				sp.style.color='green';
			}
		}
	}
}
function namecallback(){ //处理状态改变的事件
	if(xmlhttp.readyState==4){ //数据传输完成;
		if(xmlhttp.status==200){ //成功在客户端接收到数据;
			//可以取服务器返回的数据
			var data = xmlhttp.responseText;//接收服务器返回的字符串；1,0
			var sp = document.querySelector('#sp');
			if(data=="1"){ //1:表示存在;
				sp.innerHTML='账号已被注册';
				sp.style.color='red';
			}else{
				sp.innerHTML='恭喜，可以使用';
				sp.style.color='green';
			}
			
		}
	}
}