/**
 * 
 */

$(function(){
	 $.post("Sping",{op:"findAll"},function(data){
		 var str=" ";
		   $.each(data,function(index,item){
		    str+='<div class="commo">';
		       str+='<a href="buy.html"><img src="'+item.sppic+'" width="150" height="150"  alt=""/></a>';
		       str+='<h2>'+item.spname+'<br /> '+item.spcate+'</h2>';
		       str+='<p><span>¥'+item.spprice+'</span></p>';
		       str+='</div>';
		    	   
			})
			$("#commodity").html(str);
	 },"json")
    	
	 
})


/*$("#padChecka").on("click",function(){

	console.log($(this).find("a")[0].text);
	
})*/

$("#padCheck li a").on("click",function(){
     var spcate=$(this).text();
	
    $.post("Sping",{op:"findcate",spcate:spcate},function(data){
    	var str=" ";
		   $.each(data,function(index,item){
		    str+='<div class="commo">';
		       str+='<a href="buy.html"><img src="'+item.sppic+'" width="150" height="150"  alt=""/></a>';
		       str+='<h2>'+item.spname+'<br /></h2>';
		       str+='<p><span>¥'+item.spprice+'</span></p>';
		       str+='</div>';
		    	   
			})
			$("#commodity").html(str);
	 },"json")
	
})

