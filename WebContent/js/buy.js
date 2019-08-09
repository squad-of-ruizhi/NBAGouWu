/*var spIda=location.hash;
spIda=spIda.replace("#"," ");
spIda=spIda.split("&");
console.log(spIda);
spId=spIda[0];
var usid=spIda[1];
console.log(spId);
console.log(usid);*/
var spid=location.hash;
spId=spid.replace("#"," ")
$("#pdp-ids").html(spId);
var usid = sessionStorage.getItem("usid");
console.log(usid);
var tolpricea=0;
var spnumber=0;

$(function(){
	  $.post("cart",{op:"selSp",usid:usid},function(data){
		  var str="";
		  $.each(data,function(index,item){
		            str+='<li class="dtl-box">';
					str+='<a class="thumbnail">';
					str+='<img src="'+item.sppic+'" style="margin-left:1px;margin-top:10px;width:140px;height:140px;"></img>';
					str+='</a>';
				    str+='<div class="dtl-box-tx">';
					str+='<p class="dtl-pdn" style="color:red;margin-left:150px;margin-top:-135px;">&nbsp'+item.spname+'</p>';
					str+='<p class="dtl-box-row">';
				    str+='<span class="dtl-qty"  >&nbsp&nbsp&nbsp数量:'+item.samount+'</span>';
					str+='<span class="dtl-size">&nbsp&nbsp&nbsp尺寸:'+item.spsize+'</span>';
					str+='<span class="dtl-color">&nbsp&nbsp&nbsp颜色:'+item.spcolor+'</span>';
					str+='</p>';
					str+='</div>';
					str+='<p class="dtl-box-pic">';
					str+='<span>&nbsp&nbsp&nbsp¥'+item.spprice+'.00</span>'	;
					str+='<p class="oneSp" style="margin-left:150px;color:#00559a;" >单件商品总价:&nbsp&nbsp&nbsp¥'+item.tolprice+'.00</p>';
					str+='</p>'
				    str+='</li>';
					
					tolpricea+=parseInt(item.tolprice);
					spnumber+=parseInt(item.samount);
		             
		             var str1="";
		            str1+='<span class="dtl-name">订单产品总额</span>'; 
		            str1+='<span class="dtl-pic">￥'+tolpricea+'.00</span>';
		            $("#abcd").html(str1);
		                
		           var str2=" ";
		             var yf=$("#dtl-pic").text();
		             var yfprice=yf.replace("￥","");
		             var yfpricea=yfprice.substring(0,2); //取到10
		             console.log(yfpricea);
		             var sppriceb=tolpricea;
		             var k=sppriceb+parseInt(yfpricea);  //取到最终价格
		           
		              
		            str2+='<span class="dtl-name" style="font-size:22px;padding-top:10px;">订单总计</span>';
	                 str2+='<span class="dtl-pic" style="font-size:22px;padding-top:10px;">￥'+k+'.00</span> ';
	                 $("#priceaa").html(str2);
	                 
	                 
	                 var str3=" ";
	                 str3+='<span class="dtl-name">商品数量总计</span>'
	                 str3+='<span class="dtl-pica">'+spnumber+'</span>';
	                 $("#number").html(str3);
	                 
		  })
		  $("#list-unstyled").html(str);
	  },"json")


	    console.log(usid);
	    $.post("Addres",{op:"findAllDz",usid:usid},function(data){
	    	 console.log(data);
	    	 if(data.length==0){
	    	    	 $("#fga").css("display","block");
	    	     }
	    	     else{
	    	    	 var str=" ";
	    	    	  $.each(data,function(index,item){
		    	    	   $("#fga").css("display","none");
		    	    	   var adefault=item.adefault;
		    	    	   if(adefault==1){
		    	    		   $("#events-DefaultAddress").css("display","block");
		    	    		    str+='<p style="text-align: center;font-size:18px;"> 地址信息</p>';
		    	    			str+='<p class="add-name">'+item.aname+'</p>';
		    	    			str+='<p class="events-phoneVal">+86 '+item.atel+'</p>';
		    	    			str+='<p class="events-mailVal">'+item.amail+'</p>';
		    	    			str+='<p class="events-addressVall">'+item.province+' '+item.city+' '+item.area+' '+item.detailed+' </p>';
		    	    	   }   
		    	    	  /* else {
		    	    		   return false;
		    	    	   }*/
		    	    	   
	    	    	  })  
	    	    	$("#ppp").html(str);
	    	     }
	    },"json")
	  
	  
	    
	    
	    
 $("#buynext").click(function(data){
	 if($(".events-DefaultAddress").css("display")=="none"){
		 alert("请填写相关地址");
	 }else {
		 /*location.href='DingDan.html';*/
		  var usida=usid;         //用户ID
		  var address=$(".events-addressVall").text(); //地址
		  var state=$(".dtl-pic").text();
		  var statea=state.substring(20,28);   //总价
		  var  oamounta=$(".dtl-pica").text();   //数量
		  var x = 100;       
		  var y = 0;           
		  var orderid = parseInt(Math.random() * (x - y + 1) + y);       //订单ID
		  var date = new Date();//获取生成订单时间
		  var odate = date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " +date.getHours()+ ":" + date.getMinutes()+":"+date.getSeconds();
		
		  $.post("Addres",{op:"AddDD",orderid:orderid,odate:odate,oamounta:oamounta,statea:statea,address:address,usida:usida},function(data){
			  
			  
		  })
		  
		  $.post("cart",{op:"updateo",usid:usid},function(){
			
			  
		  })  
		  
		  
		 location.href='DingDan.html';
	 }
})
	      
	  
})




$("#add").click(function(data){
	if($("#addDZ").css("display")=="none"){
		$("#addDZ").css("display","block");
	}else{
		$("#addDZ").css("display","none");
	
	}
})


function addAddress(){
	  var province=$.trim($("#province").val());
	  var city=$.trim($("#city").val());
	  var area=$.trim($("#area").val());
	  var detailed=$.trim($("#DZ").val());
	  var aname=$.trim($("#uname").val());
      var atel=$.trim($("#tel").val());
     /* var atel1="/^(13|14|15|17|18)[0-9]{9}$/";*/
      var amail=$.trim($("#YXiang").val());
      var adefault=1;
      
      test(atel);
      test1(amail);
      //手机号码判断
      function test(atel){
 		 if( atel== "") {
 			 alert("手机号码为空");
 		  }
 		 else {
 			  if( ! /^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(atel) ) {
 				  alert("手机格式不正确");
 			  }
 			  else{
 				  return true;
 			  }
 			 }
 	}
   
      function test1(amail){
  		 if( amail== "") {
  			 alert("邮箱为空");
  		  }
  		 else {
  			  if( ! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(amail) ) {
  				  alert("邮箱格式不正确");
  			  }
  			  else{
  				  return true;
  			  }
  			 }
  	}
    
      
      //空判断
     if(aname=="" || detailed=="" || province==" "|| city==" "|| area=="" || test1(amail)!=true || test(atel)!=true){
    	 alert("错误提示-输入文本信息为空或者错误")
     }else {
    	     
    	   $.post("Addres",{op:"addDz",province:province,city:city,area:area,detailed:detailed,aname:aname,atel:atel,amail:amail,usid:usid,adefault:adefault},function(data){
    		   data=parseInt($.trim(data));
    		   if(data==1) 	{
    			   $("#addDZ").css("display","none");
    			   
    			   var str="";
    			   str+='<p style="text-align: center;font-size:18px;"> 地址信息</p>';
    				str+='<p class="add-name">'+aname+'</p>';
    				str+='<p class="events-phoneVal">+86 '+atel+'</p>';
    				str+='<p class="events-mailVal">'+amail+'</p>';
    				str+='<p class="events-addressVall">'+province+' '+city+' '+area+' '+detailed+' </p>';
    			     $("#ppp").html(str);
    			     
    			     
    			     
    		    $("#events-DefaultAddress").css("display","block");
    		   }
    	   },"json")
     }
}

function bddZ(){
	location.href='DingDan.html';
}


