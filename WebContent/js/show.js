/**
 * 
 */

/*var usid=location.hash;*/
/*var spIda=location.hash;
spIda=spIda.replace("#"," ");
spIda=spIda.split("&");
console.log(spIda);
spId=spIda[0];
usid=spIda[1]
 $("#pdp-ids").html(spId);*/
 /*console.log(spId);
 console.log(usid);*/
var spid=location.hash;
spId=spid.replace("#"," ")
$("#pdp-ids").html(spId);
var usid = sessionStorage.getItem("usid");
var tel = sessionStorage.getItem("tel");
$("#aaa").html(tel);


$(function(){
	$.post("Sping",{op:"findSingle",spId:spId},function(data){
	      var str="";
	      str+='<img class="events-pdp-cloudzoom" src="'+data.sppic+'"  width="448" height="448" style="user-select: none;padding-top:35px;"></img>';
	      $("#pdp-cloudZoom-imgBox").html(str);
	      
	      
	      var str1="";
	          str1+='<h1 id="itemName_pc">'+data.spname+'';       /*NBA斯伯丁 TF-1000 LEGACY·传奇 篮球 室内篮球*/
	         str1+= '</h1>';
	         str1+='<p class="pdp-price" id="pdpPrice_pc">¥'+data.spprice+'.00</p></br><hr/>';
	          $("#itemName_pca").html(str1);
	      
	          var str2="";
	          str2+='<p>颜色:<span>&nbsp'+data.spcolor+'&nbsp</span></p>';
              str2+='<p>尺寸:<span>&nbsp'+data.spsize+'&nbsp</span></p>';
             $("#are").html(str2);
             
            var str3="";
             str3+='<ul class="list-inline" id="list-inline">';
     		 str3+='<li><a href="#" title="篮球"><input type="hidden" class="types" value="'+data.spcate+'" >商品详情&nbsp&nbsp&nbsp/</a>'+data.spcate+'</li>';
     	     str3+='</ul>';
     	   $("#breadcrumb").html(str3);
     	   
     	   var spId=data.spId;
     	   var spIda=data.spId;
     	    testa(spIda);
     	    test($(".types").val(),spId);
},"json")

function test(type,type1){
		 
		var  spcateL=type;
		var spIdL=type1;
		 $.post("Sping",{op:"favorite",spcateL:spcateL,spIdL:spIdL},function(data){
			 var str="";
			   $.each(data,function(index,item){
				str+='<li style="margin-top:18px; height:340px;width:247px;float:left;">';
				str+='<a class="thumbnail" href="show.html#'+item.spId+'" product_id="'+item.spId+'">';	
				str+='<img src="'+item.sppic+'" style="margin-top:27px;width:220px;width:224px;"></img>';		
				str+='</a>';
			    str+='<h2>'+item.spname+'</h2>';
			    str+='<p>'
			    str+='<span>¥'+item.spprice+'.00</span>'		
                str+='</p>';
			    str+='</li>';
			     })
			   $("#favoriteL").html(str);
		 },"json")
		 
	}
     
   	/*	var spcate=$("#list-inline li").text();
	
		var str=spcate.substring(8,10);
	   console.log(spcate+"***");*/
        
})


function testa(a){
	var spId=a;
	 var tolprice=$("#pdpPrice_pc").text();
	 tolprice=tolprice.replace("¥","");
	 console.log(tolprice);
	 var samount=1;
       $("#btn-isBuyNow").click(function(data){
    	   $.post("cart",{op:"addoneSp",spId:spId,usid:usid,tolprice:tolprice,samount:samount},function(data){
    		   
    		   
    		   
    	   })
    	  location.href='buy.html';
       }) 
}


$("#addToShoppingCart").click(function(data){
	var samount=1;
	  $.post("cart",{op:"addcar",usid:usid,spId:spId,samount:samount},function(data){
		  
	  })
	  
	  alert("添加成功");
})
 

function carta(){
	location.href='shoppingCart.html';
	
}