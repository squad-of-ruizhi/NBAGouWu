/**
 * 
 */
var usid= sessionStorage.getItem("usid");
console.log(usid);

$(function(){
	console.log(usid);
         $.post("DingDan",{op:"selectId",usid:usid},function(data){
        	 if(data!=null){
        		 WDD();
        	 }else {
        		 
        		 $("#myaccount_content_").css("display","block");
        	 }	  
        	 
        	 /* var a=data.usid;
        	  console.log(a);*/
        	 
         },"json")	
	
	
     /*test(data.usid);*/
         
	
	
})


function WDD(){     //我的订单
	    	  $("#myaccount_content_").css("display","none");
	    	  $("#table-myaccount").css("display","block");
	    	  $("#right").css("display","block");
	    	  $("#right_DZ").css("display","none");
	    	  $("#right_GRXX").css("display","none");
	    	  
	    	  console.log(usid);
	    	  $.post("DingDan",{op:"selectId",usid:usid},function(data){
	    	         var str=" ";
	    	         $.each(data,function(index,item){
	    	        	 str+='<tr id="ccc">'
	    	        	   str+='&nbsp&nbsp&nbsp&nbsp<td><a href=" ">&nbsp&nbsp&nbsp&nbsp'+item.orderid+'&nbsp&nbsp&nbsp&nbsp</a></td>'
						  	str+='<td>'+item.odate+'</td>'
						  	str+='<td>'+item.oamount+'</td>'
						  	str+='<td>'+item.addr+'</td>'
						  	str+='<td>'+item.state+'</td>'
						  	str+='<td>'
						  	str+='<a href="#'+item.usid+'" class="font-bluea" onclick="deleteb('+item.orderid+')">删除<span class="font-blue"></span></a>'
						    str+='<a class="btn-red" ok_socode="NBACN200685" ok_paymenttype="4">'
			                str+='立即支付</a>'
						  	str+='</td>'
						  	str+='</tr>'
						  				
	    	         })
	    	           $("#dingD").html(str);
	    	         
	    	       
	       },"json")
	       
}

function deleteb(a){
	var orderid=a;
	console.log(orderid);
	  $.post("DingDan",{op:"deleteord",orderid:orderid},function(data){
		  
	  })
	 $("#ccc").remove();
}  




		
function WDZ(){   //地址簿
			  $("#myaccount_content_").css("display","none");
			  $("#right").css("display","none");
			  $("#right_DZ").css("display","block");
			  $("#right_GRXX").css("display","none");
			  
			  console.log(usid);
			  $.post("Addres",{op:"findAllDz",usid:usid},function(data){
				   var str="";
				     $.each(data,function(index,item){
				    	 str+='<div class="default-address" id="default-address">'
				    	str+='<p class="edit-name"> '  +item.aname;
				    	 if(item.adefault == 1){
				    		 str+='<span style="visibility:visible" class="2'+item.addrid+'">默认地址</span></p>';
				    	 }else{
				    		 str+='<span style="visibility:hidden" class="2'+item.addrid+'">默认地址</span></p>';
				    	 }
							str+='<p style="margin-left:35px;padding-top:7px;">+86 '+item.atel+'</p>';
							str+='<p style="margin-left:35px;padding-top:7px;">'+item.amail+'</p>';
							str+='<p class="edit-addressH">';
							str+=''+item.province+'&nbsp;'+item.city+' &nbsp;'+item.area+' &nbsp;&nbsp;'+item.detailed+'</p>';
							str+='<div class="edit-btn edit-btn-mob">';
							 str+='<button class="btnevents-editAddressBtn" onclick="updatec('+item.addrid+')">编辑地址</button>';
							 str+='<button class="btn-delet-btn " onclick="deletea('+item.addrid+')">删除</button>';
							str+='</div>';
							str+='<div class="Set-default"  style="display: inline;">';
							/*if(item.adefault == 1){*/
							str+=' <a href="#"  onclick="set(2'+item.addrid+')"><span   class="12'+item.addrid+'" >设为默认</span></a>';
					    	/* }else{
					    		 str+=' <a href="#"  onclick="set(2'+item.addrid+')"><span   class="12'+item.addrid+'" style="visibility:visible">设为默认</span></a>'
					    	 }*/
							
						str+='</div>';
						str+='</div> ';
				     })
				     $("#Dzc").html(str);    
			  },"json")
		 }

function updatec(c){
	var addrid=c;
	if($("#addDZa").css("display")=="none"){
		$("#addDZa").css("display","block");
	}else{
		$("#addDZa").css("display","none");
	}
	$(".userwho").val("edit");
    $.post("Addres",{op:"selectrid",addrid:addrid},function(data){
    	   console.log(data);
    	   var a=data.aname;
    	   console.log(a);
    	   if($(".userwho").val() == "edit"){
    		   $(".thisid").val(data.addrid);
    		   $(".userthis").val('确认修改');
        	   $(".addadress").val('编辑地址');
        	    $("#uname").val(''+data.aname+'');
        	    $("#province").val(''+data.province+'');
        		 $("#city").val(''+data.city+'');
        		 $("#area").val(''+data.area+'');
        		  $("#DZ").val(''+data.detailed+'');
        	     $("#tel").val(''+data.atel+'');
        	     /* var atel1="/^(13|14|15|17|18)[0-9]{9}$/";*/
        	     $("#YXiang").val(''+data.amail+'');
    	   }
    	  
    },"json")    
}


function set(id){
/*	alert(id + "  " + $(".default").val());*/
	if($(".default").val() == ""){
		$("." + id).css("visibility","visible");
		$(".1" + id).css("visibility","hidden");
		$(".default").val(id)
	}else{
		$("." + $(".default").val()).css("visibility","hidden");
		$("." + id).css("visibility","visible");
		$(".1" +id).css("visibility","hidden");
		$(".1" + $(".default").val()).css("visibility","visible");
		$(".default").val(id)
	}
	var def=1;
	var addrid=id;
	$.post("Addres",{op:"ZTai",def:def,usid:usid,addrid:addrid},function(data){
		
		
		
	})
}	


$(".my-address-btn").click(function(data){
	if($("#addDZa").css("display")=="none"){
		$("#addDZa").css("display","block");
	}else{
		$("#addDZa").css("display","none");
	}
	$(".userwho").val("");
	if($(".userwho").val() == ""){
		$(".userthis").val('确认修改');
 	   $(".addadress").val('编辑地址');
 	    $("#uname").val('');
 		  $("#DZ").val('');
 	     $("#tel").val('');
 	     /* var atel1="/^(13|14|15|17|18)[0-9]{9}$/";*/
 	     $("#YXiang").val('');
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
      
      console.log(usid);
      var adefault=0;
      
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
    	  if($(".userwho").val() == "edit"){
    		//編輯地址親求
    		  var addrid=$(".thisid").val();
    		  $.post("Addres",{op:"updateDZ",addrid:addrid,province:province,city:city,area:area,detailed:detailed,aname:aname,atel:atel,amail:amail},function(data){
    			  data=parseInt($.trim(data));
         		   if(data==1) 	{
         			   $("#addDZa").css("display","none");
         			    WDZ();
         		   }
    			  
    		  })
    		  
    	  }else{
    		  $.post("Addres",{op:"addDz",province:province,city:city,area:area,detailed:detailed,aname:aname,atel:atel,amail:amail,usid:usid,adefault:adefault},function(data){
          		   data=parseInt($.trim(data));
          		   if(data==1) 	{
          			   $("#addDZa").css("display","none");
          			    WDZ();
          		   }
          	   },"json")
    	  }
     }
}


function deletea(id){
	    var addrid=id;
	    $.post("Addres",{op:"deletea",addrid:addrid},function(data){
	    	   
	    })
	    
	    $("#default-address").remove();    
}

function personedit(){
	 $("#right_GRXX").css("display","block");
	 $("#myaccount_content_").css("display","none");
	  $("#right").css("display","none");
	  $("#right_DZ").css("display","none");
	  
}
