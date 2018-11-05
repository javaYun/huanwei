
function updateCarInfo(){
	debugger;
	var carId = window.frames["addIframe"].document.getElementById("carId").value;//车辆品牌
	var brand = window.frames["addIframe"].document.getElementById("brand").value;//车辆品牌
	var motelnum = window.frames["addIframe"].document.getElementById("motelnum").value;//发动机
	var vin = window.frames["addIframe"].document.getElementById("VIN").value;//车架号
	var carnum = window.frames["addIframe"].document.getElementById("carNum").value;//车牌号
	var carlicensedate = window.frames["addIframe"].document.getElementById("carlicensedate").value;//领证日期
	var usetype = window.frames["addIframe"].document.getElementById("usetype").options[window.frames["addIframe"].document.getElementById("usetype").selectedIndex].value;//使用类型
	var carstatus = window.frames["addIframe"].document.getElementById("carStatus").options[window.frames["addIframe"].document.getElementById("carStatus").selectedIndex].value;//使用状态
	var str1 = window.frames["addIframe"].document.getElementById("ImgUrlHidden").value;//img地址
	var jsonData = {
			"id":carId,
			"brand" : brand,
			"motelnum" : motelnum,
			"vin" : vin,
			"carnum" : carnum,
			"carlicensedate" : new Date(carlicensedate.replace(/-/g,"/")),
			"usetype" : usetype,
			"carstatus" : carstatus,
			"str1" : str1
		};
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/CDS/cds/updateCarInfo",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.isSuccess == 1){
				alert("车辆信息修改完成");
				joinCarPage();
			}
			
			
		}
	});
}
function saveCarInfo(){//保存车辆信息到数据库
	debugger;
	var brand = window.frames["addIframe"].document.getElementById("brand").value;//车辆品牌
	var motelnum = window.frames["addIframe"].document.getElementById("motelnum").value;//发动机
	var vin = window.frames["addIframe"].document.getElementById("VIN").value;//车架号
	var carnum = window.frames["addIframe"].document.getElementById("carNum").value;//车牌号
	var carlicensedate = window.frames["addIframe"].document.getElementById("carlicensedate").value;//领证日期
	var usetype = window.frames["addIframe"].document.getElementById("usetype").options[window.frames["addIframe"].document.getElementById("usetype").selectedIndex].value;//使用类型
	var carstatus = window.frames["addIframe"].document.getElementById("carStatus").options[window.frames["addIframe"].document.getElementById("carStatus").selectedIndex].value;//使用状态
	var str1 = window.frames["addIframe"].document.getElementById("ImgUrlHidden").value;//img地址
	var jsonData = {
			"brand" : brand,
			"motelnum" : motelnum,
			"vin" : vin,
			"carnum" : carnum,
			"carlicensedate" : new Date(carlicensedate.replace(/-/g,"/")),
			"usetype" : usetype,
			"carstatus" : carstatus,
			"str1" : str1
		};
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/CDS/cds/insertCarInfo",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.isSuccess == 1){
				alert("车辆信息录入完成");
				joinCarPage();
			}
			
			
		}
	});
}
function updateCarInfoPage(){//进入车辆信息修改页面
	
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
		var shouyeUrl = "<iframe id='addIframe' src='/CDS/cds/addCarPage?File_NAME="+File_NAME+"' style='width:100%;margin-top:55px;margin-left:20px;height:470px;border:0;scrolling:no'></iframe>";
		$("#center").html(shouyeUrl);	
		$("#bottom").html(displayButton("确认","取消",null,null,"3"));
		$("#bottom").show();
	}
	
}

function joinCarPage(){//进入车辆信息展示页面
	
	debugger;
	
	hideContent();
	$("#top").html(displayButton("车辆登记","车辆编辑","删除车辆信息","返回首页","1"));
	var shouyeUrl = "<iframe id='addIframe' src='/CDS/cds/joinCarPage?' style='width:100%;height:470px;border:0;scrolling:no'</iframe>";
	$("#top").show();
	$("#center").html(shouyeUrl);
}
function deleteCarInfo(){ //jquery获取iframe里面复选框值 
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
			url: "/CDS/cds/deleteCarInfoByIds", 
			contentType: "application/json; charset=utf-8", 
			data: JSON.stringify(deleteCar), 
			dataType: "json",
			success:function(data){
				if(data >= 0){
					alert("删除数据成功");
					joinCarPage();//删除成功后返回车辆信息展示页面
				}else if(data == -999){
					alert("部分数据失败，含有已登记数据");
				}else{
					alert("数据删除成功");
				}
			}
		});
	}
	} 