//获得年月日      得到日期oTime  
        function getMyDate(str){  //yy--mm--dd hh--mm--s
            var oDate = new Date(str),  
           oYear = oDate.getFullYear(),  
            oMonth = oDate.getMonth()+1,  
           oDay = oDate.getDate(),  
           oHour = oDate.getHours(),  
            oMin = oDate.getMinutes(),  
            oSen = oDate.getSeconds(),  
           oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
            return oTime;  
        };  
        function getMyDateFormat(str){  //yy--mm--dd
            var oDate = new Date(str),  
           oYear = oDate.getFullYear(),  
            oMonth = oDate.getMonth()+1,  
           oDay = oDate.getDate(),  
           oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间  
            return oTime;  
        }; 
        //补0操作  
        function getzf(num){  
            if(parseInt(num) < 10){  
                num = '0'+num;  
            }  
            return num;  
        }  