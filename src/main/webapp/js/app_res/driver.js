function joinDriverPage(){
	//hideContent();
	$("#top").html(displayButton("驾驶员登记","驾驶员信息修改","删除驾驶员数据","返回首页","4"));
	var shouyeUrl = "<iframe id='addIframe' src='/CDS/cds/joinDriverPage?' style='width:100%;height:464px;border:0;margin-left:20px;scrolling:no'></iframe>";
	$("#top").show();
	$("#center").html(shouyeUrl);
}
function deleteDriverInfo(){ //jquery获取iframe里面复选框值 
	var File_NAME = getSelectCheckBoxInfo();
	if(File_NAME.length==0){
		alert("你还没有选择任何内容！"); 
	}else{
		var deleteCar = {
				"id":File_NAME
				};
		$.ajax({ 
			type: "POST", 
			enctype:"multipart/form-data",
			url: "/CDS/cds/deleteDirverByIds", 
			contentType: "application/json; charset=utf-8", 
			data: JSON.stringify(deleteCar), 
			dataType: "json",
			success:function(data){
				if(data >= 0){
					alert("删除数据成功");
					joinDriverPage();//删除成功后返回车辆信息展示页面
				}
			}
		});
	}
	} 


function updateDriverInfo(){
	var id = window.frames["addIframe"].document.getElementById("dirverid").value;
	var drivername = window.frames["addIframe"].document.getElementById("drivername").value;//驾驶员姓名
	var sex = window.frames["addIframe"].document.getElementById("sex").options[window.frames["addIframe"].document.getElementById("sex").selectedIndex].value;//性别
	var nativeplace = window.frames["addIframe"].document.getElementById("nativeplace").value;//籍贯
	var educationlevel = window.frames["addIframe"].document.getElementById("educationlevel").options[window.frames["addIframe"].document.getElementById("educationlevel").selectedIndex].value;//教育水平
	var idcard = window.frames["addIframe"].document.getElementById("idcard").value;//身份证号
	var birthday = window.frames["addIframe"].document.getElementById("birthday").value;//出生日期
	var joncom =window.frames["addIframe"].document.getElementById("joncom").value;//入职日期
	var address = window.frames["addIframe"].document.getElementById("address").value;//家庭地址
	var mobilephone = window.frames["addIframe"].document.getElementById("mobilephone").value;//手机号
	var drivercard = window.frames["addIframe"].document.getElementById("drivercard").value;//驾驶员证号
	var cardunit = window.frames["addIframe"].document.getElementById("cardunit").value;//发证日期
	var drivertype =  window.frames["addIframe"].document.getElementById("drivertype").value;//准驾车型
	var startdate = window.frames["addIframe"].document.getElementById("startdate").value;//img地址
	var enddate = window.frames["addIframe"].document.getElementById("enddate").value;//有效期
	var beizhu = window.frames["addIframe"].document.getElementById("beizhu").value;//结束期
	var str1 = window.frames["addIframe"].document.getElementById("ImgUrlHidden").value;//img地址
	var jsonData = {
			"id":id,
			"drivername" : drivername,
			"sex" : sex,
			"nativeplace" : nativeplace,
			"educationlevel" : educationlevel,
			"idcard" : idcard,
			"birthday" :new Date(birthday.replace(/-/g,"/")),
			"joncom" : new Date(joncom.replace(/-/g,"/")),
			"address" : address,
			"mobilephone" : mobilephone,
			"drivercard" : drivercard,
			"cardunit" : cardunit,
			"drivertype" : drivertype,
			"startdate" : new Date(startdate.replace(/-/g,"/")),
			"enddate" : new Date(enddate.replace(/-/g,"/")),
			"beizhu" : beizhu,
			"str1" : str1
		};
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/CDS/cds/updateDriverInfo",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.isSuccess == 1){
				alert("车辆信息修改完成");
				joinDriverPage();
			}
			
			
		}
	});
}
function saveDriverInfo(){//保存车辆信息到数据库
	var drivername = window.frames["addIframe"].document.getElementById("drivername").value;//驾驶员姓名
	var sex = window.frames["addIframe"].document.getElementById("sex").options[window.frames["addIframe"].document.getElementById("sex").selectedIndex].value;//性别
	var nativeplace = window.frames["addIframe"].document.getElementById("nativeplace").value;//籍贯
	var educationlevel = window.frames["addIframe"].document.getElementById("educationlevel").options[window.frames["addIframe"].document.getElementById("educationlevel").selectedIndex].value;//教育水平
	var idcard = window.frames["addIframe"].document.getElementById("idcard").value;//身份证号
	var birthday = window.frames["addIframe"].document.getElementById("birthday").value;//出生日期
	var joncom =window.frames["addIframe"].document.getElementById("joncom").value;//入职日期
	var address = window.frames["addIframe"].document.getElementById("address").value;//家庭地址
	var mobilephone = window.frames["addIframe"].document.getElementById("mobilephone").value;//手机号
	var drivercard = window.frames["addIframe"].document.getElementById("drivercard").value;//驾驶员证号
	var cardunit = window.frames["addIframe"].document.getElementById("cardunit").value;//发证日期
	var drivertype =  window.frames["addIframe"].document.getElementById("drivertype").value;//准驾车型
	var startdate = window.frames["addIframe"].document.getElementById("startdate").value;//img地址
	var enddate = window.frames["addIframe"].document.getElementById("enddate").value;//有效期
	var beizhu = window.frames["addIframe"].document.getElementById("beizhu").value;//结束期
	var str1 = window.frames["addIframe"].document.getElementById("ImgUrlHidden").value;//img地址
	var jsonData = {
			"drivername" : drivername,
			"sex" : sex,
			"nativeplace" : nativeplace,
			"educationlevel" : educationlevel,
			"idcard" : idcard,
			"birthday" :new Date(birthday.replace(/-/g,"/")),
			"joncom" : new Date(joncom.replace(/-/g,"/")),
			"address" : address,
			"mobilephone" : mobilephone,
			"drivercard" : drivercard,
			"cardunit" : cardunit,
			"drivertype" : drivertype,
			"startdate" : new Date(startdate.replace(/-/g,"/")),
			"enddate" : new Date(enddate.replace(/-/g,"/")),
			"beizhu" : beizhu,
			"str1" : str1
		};
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/CDS/cds/insertDriverInfo",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.isSuccess == 1){
				alert("车辆信息录入完成");
				joinDriverPage();
			}
			
			
		}
	});
}
function updateDriverInfoPage(){
	var File_NAME = getSelectCheckBoxInfo();
		
		var isSelected = false;
		var str = File_NAME.split(",");
		if(str.length == 1){
			isSelected = true;
		}
		if(File_NAME.length  == 0){
			isSelected = false;
		}
		if(!isSelected){
			alert("有且只能选择一条数据");
		}else{
			hideContent();
			var shouyeUrl = "<iframe id='addIframe' src='/CDS/cds/addDriverPage?File_NAME="+File_NAME+"' style='width:100%;height:464px;border:0;margin-top:55px;margin-left:20px;scrolling:no'></iframe>";
			$("#center").html(shouyeUrl);	
			$("#bottom").html(displayButton("确认","取消",null,null,"6"));
			$("#bottom").show();
		}
	}
function selectedusetype(){
	   var t = document.getElementById("sex");   
	    var selectValue="${driver.sex }";//获取select的值  
	     if(selectValue != null && selectValue!=""){
	    	 for(i=0;i<t.length;i++){//给select赋值  
	   	      if(selectValue==t.options[i].value){  
	   	        t.options[i].selected=true  
	   	      }  
	   	     }   
	     }
}
function selectedcarStatus(){
	   var t = document.getElementById("educationlevel");   
	    var selectValue="${driver.educationlevel}";//获取select的值  
	     if(selectValue != null && selectValue!=""){
	    	 for(i=0;i<t.length;i++){//给select赋值  
	   	      if(selectValue==t.options[i].value){  
	   	        t.options[i].selected=true  
	   	      }  
	   	     }   
	     }
}
function dateformat(){
	   var birthday = "${driver.birthday }";
	   if(birthday != null && birthday!=""){
		   $("#birthday").val( getMyDateFormat(birthday));
	   }
	   var joncom = "${driver.joncom }";
	   if(joncom != null && joncom!=""){
		   $("#joncom").val( getMyDateFormat(joncom));
	   }
	   var startdate = "${driver.startdate }";
	   if(startdate != null && startdate!=""){
		   $("#startdate").val( getMyDateFormat(startdate));
	   }
	   var enddate = "${driver.enddate }";
	   if(enddate != null && enddate!=""){
		   $("#enddate").val( getMyDateFormat(enddate));
	   }

	  
	  
}
var totalNum = 0;
var pageSize = 5;
var currentPage = 1;
function turnPage(num){
		if(num == 0){//上一页
			if(currentPage >1){
				currentPage = currentPage-1;
			}
		}else if(num == 1){//下一页
			if(currentPage*pageSize <totalNum){
				currentPage = currentPage+1;
				
			}
		}
		getDirverList();
		$("#spanNum").html(currentPage);
		
	}
function getDirverList(){
	
	var jsonRegister = {
			"currentPage" : currentPage,
			"pageSize" : pageSize
		};
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/CDS/cds/getDriverInfos",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonRegister),
		dataType : "json",
		success : function(data) {
			var tpl = "<tr><td colspan='10' style='text-align:center'>数据为空</tr>";
			var listData = data.driverInfos;//List<ProductPrice>
			totalNum = data.totalNum;//totalNum
			$("#totalNumSize").html( totalNum);
			$("#totalNum").html( Math.ceil(totalNum/pageSize) );
			if (listData == null) {
				$("#displayTbody").html(tpl);
				return false;
			}
			if (listData.length > 0) {
				tpl = "";
				//$("#spanNum".html(Math.ceil(listData.length/5)));
			}
			for(var i=0;i<listData.length;i++){
				tpl += "<tr>t<td><input type='checkBox' name='productCheck' value='"+listData[i].id+"'></td>";
				tpl += "<td>" + listData[i].drivername + "</td>";
				tpl += "<td>" + listData[i].sex + "</td>";
				tpl += "<td>" + listData[i].unitname + "</td>";
				tpl += "<td>" + listData[i].educationlevel + "</td>";
				tpl += "<td>" + listData[i].idcard + "</td>";
				tpl += "<td>" + getMyDateFormat(listData[i].birthday) + "</td>";
				tpl += "<td>" + getMyDateFormat(listData[i].joncom) + "</td>";
				tpl += "<td>" + listData[i].address + "</td>";
				tpl += "<td>" + listData[i].mobilephone + "</td>";
				tpl += "<td>" + listData[i].cardunit + "</td>";
				
				tpl += "</tr>";
			}
			$("#displayTbody").html(tpl);
		}
	});
}

function deleteDriver(){ //jquery获取复选框值 
	var chk_value =""; 
	$('input[name="productCheck"]:checked').each(function(){ 
		if(chk_value == ""){
			chk_value = $(this).val();
		}else{
			chk_value = chk_value+","+$(this).val();
		}
		
	}); 
	//alert(chk_value);
	if(chk_value.length==0){
		alert("你还没有选择任何内容！"); 
	}else{
		var deleterUser = {
				"id":chk_value
				};
		$.ajax({ 
			type: "POST", 
			enctype:"multipart/form-data",
			url: "/SczhPlatform/sczhCarController/deleteCarInfoById", 
			contentType: "application/json; charset=utf-8", 
			data: JSON.stringify(deleterUser), 
			dataType: "json",
			success:function(data){
				if(data >= 0){
					alert("删除数据成功");
					getDirverList();
				}else if(data == -999){
					alert("部分数据失败，含有已登记数据");
				}else{
					alert("数据删除成功");
				}
			}
		});
	}
	} 
/**
	function updateCarInfo(){
		var chk_value =[]; 
		var carId = "";
		$('input[name="productCheck"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
		}); 
		if(chk_value.length>1){
			alert("有且只能选择一条数据进行修改");
		}else if(chk_value.length == 0){
			alert("您还没有选择任何内容！");
		}else{
			$('input[name="productCheck"]:checked').each(function(){ 
				carId = $(this).val();
			});
			var shouyeUrl = "<iframe src='/SczhPlatform/sczhCarController/updateCarInfopage?&id="+carId+"' style='width:100%;height:480px;border:0;scrolling:no'></iframe>";
			$("#displayDiv").html(shouyeUrl);
		}
	}
	*/
	
	function changeImg(){
		 $('#uploadc').on('change', function() { 
	 	   $.ajaxFileUpload({
		         //跟具updateP得到不同的上传文本的ID
		                 url:"/CDS/uploadImg?updateP="+'uploadc',             //需要链接到服务器地址
		                 secureuri:false,
		                 fileElementId:''+'uploadc'+'',                         //文件选择框的id属性（必须）
		                 dataType: 'text',  
		                 success: function (data, status){   
		                	$("#imgUrlHidden").val("");
		                	$("#uploadc").val($("#imgUrl")[0].src+"resources/upload/"+data);
		                	$("#upload").attr("src",$("#imgUrl")[0].src+"resources/upload/"+data);
		                	$("#ImgUrlHidden").val("resources/upload/"+data);
		                	
		                //	S("#imgUrlHidden").val(data);
		                  },
		                  error: function (data, status, e){  
		                      alert('上传失败');
		                  }
		                }
		             );
	 	   
	 	   
	    });  
	}
	$(function(){  
	   //点击打开文件选择器  
	   $("#upload").on('click', function() {  
	       $('#uploadc').click();  
	   });  
	   }); 
	   