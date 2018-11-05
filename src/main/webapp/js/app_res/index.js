function getSelectCheckBoxInfo(){//获取iframe 里面checkbox的选中数据
	 var File_NAME="";
	var arr = document.getElementById("addIframe").contentWindow.document.getElementsByName("productCheck"); 
	for(i=0;i<arr.length;i++){
		 if(arr[i].checked){
			 if(File_NAME != ""){
           	  File_NAME = File_NAME+","+arr[i].value;
             }else{
           	   File_NAME=arr[i].value;
             }
		 }
		}
	    return File_NAME;
}
function hideContent(){
	$("#top").hide();
	$("#bottom").hide();
}
function returnpage(){//首页
	window.location.href="/CDS/loginSuccess.jsp";
}
function displayButton(str1,str2,str3,str4,str5){
	var b1 = "";
	if(str1 != null){
		b1 = "&nbsp;&nbsp;&nbsp;<input type='button' value='"+str1+"' onclick='add("+str5+")' class='btn btn-primary'/>&nbsp;&nbsp;&nbsp;"
	}
	var b2 = "";
	if(str2 != null){
		b2 ="<input type='button' value='"+str2+"' onclick='update("+str5+")' class='btn btn-default'/>&nbsp;&nbsp;&nbsp;"
	}
	var b3 = "";
	if(str3 != null){
		b3 = "<input type='button' value='"+str3+"' onclick='deleteInfo("+str5+")' class='btn btn-default'/>&nbsp;&nbsp;&nbsp;"
	}
	var b4 = "";
	if(str4 != null){
		b4 = "<input type='button' value='"+str4+"' onclick='shouye()' class='btn btn-default'/>&nbsp;&nbsp;&nbsp;"
	}
	return b1+b2+b3+b4;
}
/*function login(){
	alert("dddddddd");
	var jsonLogin = {
			"username":$("#username").val(),
			"password":$("#password").val()
	};
	$.ajax({ 
		type: "POST", 
		enctype:"multipart/form-data",
		url: loginajax,//'<c:url value=/user/login/>',//'<c:url value='/user/login'/>',//"/CDS/user/login", 
		contentType: "application/json; charset=utf-8", 
		data: JSON.stringify(jsonLogin), 
		dataType: "json",
		success:function(data){
			debugger;
			var isLogin = data.isSucces;
			if("username" == isLogin){
				alert("请输入用户名！");
			}else if("password" == isLogin){
				alert("请输入密码！");
			}else if("usernamepassword" == isLogin){
				alert("请输入用户名和密码");
			}else if(0 == isLogin){
				alert("用户名或密码不正确");
			}else{
				window.location.href= winlongin1;
				//window.location.href='<c:url value='/user/login1'/>';"/CDS/user/login1";
			}
	}});*/
	/**
		var theForm =document.getElementById("loginForm");
		theForm.action = "/BMS/user/loginSysSuccess";
		theForm .target = "_blank";
		theForm.method="post";
		theForm.submit();
		window.close();
	*/
/*}
function deptRegister(){
	$("#longin_div").hide();
	$("#register_div").css("display","block");
}
function register_sub(){
	$("#longin_div").show();
	$("#register_div").css("display","none");
	var jsonRegister = {
		"deptName":$("#deptName").val(),
		"describe":$("#describe").val(),
		"variety":$("#variety").val(),
		"license":$("#license").val()
	};
	alert(JSON.stringify(jsonRegister)); 
	$.ajax({ 
		type: "POST", 
		enctype:"multipart/form-data",
		url: '',//'<c:url value=/user/login/>',
		contentType: "application/json; charset=utf-8", 
		data: JSON.stringify(jsonRegister), 
		dataType: "json",
		success:function(data){
			alert(data);
	}});
}*/



