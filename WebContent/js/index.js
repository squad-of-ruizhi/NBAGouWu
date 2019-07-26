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