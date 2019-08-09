/**
 * 
 */
/*var usid=location.hash;
 usid=usid.replace("#","");*/
var usid = sessionStorage.getItem("usid");
 

$(function(){
	console.info(sessionStorage.getItem("usid"));
	 $.post("Sping",{op:"findAll"},function(data){
		 var str=" ";
		   $.each(data,function(index,item){
		    str+='<div class="commo">';
		       str+='<a href="show.html#'+item.spId+'"><img src="'+item.sppic+'" width="150" height="150"  alt=""/></a>';
		       str+='<h2>'+item.spname+'<br /> '+item.spcate+'</h2>';
		       str+='<p><span>¥'+item.spprice+'.00</span></p>';
		       str+='<a href="show.html#'+item.spcate+'"></a>';
		       str+='</div>';
			})
			$("#commodity").html(str);
	 },"json")
	 
	 setInterval(function() {
		 console.log(usid);
			var atotalPrice = 0;
			var aamount = 0;
			$.post("cart",{op:"findAll",usid:usid},function(data,status){  /*************需要修改***************/
				$("#hml_coms").empty();
				if(data.length == 0) {
					$("#header_menu").css("display","none");
					$("#body_content").empty();
					$("#bodycon").css("display","block");
					$("#body_content").css("display","none");
				} else {
					$("#shoppingcar").mouseover(function() {
						$("#header_menu").css("display","block");
					});
					$("#shoppingcar").mouseout(function() {
						$("#header_menu").css("display","none");
					});
					$("#header_menu").mouseover(function() {
						$("#header_menu").css("display","block");
					});
					$("#bodycon").css("display","none");
					$("#body_content").css("display","block");
					$.each(data, function(index,item) {
						var str = "<div class='hml_com'><div class='hmlc'>" +
								  "<img src='"+item.sppic+"' width='60px' height='60px' alt=''/>" +
								  "<div class='hmlc_com' align='left'>"+
								  "<p class='hmlcc_1'>"+item.spname+"</p>" +
								  "<p class='hmlcc_2'>数量:<b class='com_num'>"+item.samount+"</b> &nbsp; &nbsp; "+
								  "&nbsp; &nbsp; 颜色:<b class='com_color'>"+item.spcolor+"</b><br/>尺寸:" +
								  "<b class='com_size'>"+item.spsize+"</b></p><p class='hmlcc_3'>￥"+item.spprice+"</p></div>" +
								  "</div></div>";
						$("#hml_coms").append(str);
						atotalPrice += parseInt(item.spprice)*(parseInt(item.samount));
						aamount += parseInt(item.samount);
					});

					$("#hml_2").empty();
					$("#hml_2").append(aamount+"件商品");
					$("#shoppingcar a").empty();
					$("#shoppingcar a").append("<i class='fa fa-shopping-cart'></i>&nbsp;"+aamount);
					$("#totalprice").empty();
					$("#totalprice").append("￥"+atotalPrice);
				}
			},"json");
		}, 1000);
	 
})
	 
	 
	
function gotoShopcart() {
			location.href='shoppingCart.html';
		}



/*$("#padChecka").on("click",function(){

	console.log($(this).find("a")[0].text);
	
})*/

$("#padCheck li a").on("click",function(){
     var spcate=$(this).text();
	
    $.post("Sping",{op:"findcate",spcate:spcate},function(data){
    	var str=" ";
		   $.each(data,function(index,item){
		    str+='<div class="commo">';
		       str+='<a href="show.html#'+item.spId+'"><img src="'+item.sppic+'" width="150" height="150"  alt=""/></a>';
		       str+='<h2>'+item.spname+'<br /></h2>';
		       str+='<p><span>¥'+item.spprice+'.00</span></p>';
		       str+='</div>';
		    	   
			})
			$("#commodity").html(str);
	 },"json")
	
})

