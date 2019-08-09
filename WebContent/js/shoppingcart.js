var amount;
var usida = sessionStorage.getItem("usid");

$(function() {
	$.post("cart",{op:"findAll",usid:usida},function(data,status){  
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
				var str1 = "<div class='cartbox' id="+'cbox'+item.shopid+"><div class='cb_1' align='center'><input id="+'ckb'+item.shopid+" onClick='choose("+item.spprice+','+item.samount+','+item.usid+','+item.spId+','+item.shopid+")' type='checkbox' class='ckb' /></div><div class='cb_2'>"+
					"<img src='"+item.sppic+"' width='100px' height='100px' /></div><div class='cb_3'>"+
					"<div class='cb3_1'>"+item.spname+"</div><div class='cb3_2'>商品："+item.spId+"</div>"+
					"<div class='cb3_3'>尺寸："+item.spsize+"</div><div class='cb3_4'>颜色："+item.spcolor+"</div>"+
					"</div><div class='cb_4' align='center'>￥"+item.spprice+"</div>"+
					"<div class='cb_5' align='center'><ul><li><input type='button' value='-' class='cb5_sub' id="+'cb_sub'+item.shopid+" onClick='sub("+'this.id,'+item.usid+','+item.spId+','+item.spprice+','+item.samount+','+item.shopid+")' />"+
					"</li><li><input type='text' value='"+item.samount+"' class='cb5_text' id="+'cb_text'+item.shopid+" /></li>"+
					"<li><input type='button' value='+' class='cb5_add' onClick='add("+'this.id,'+item.usid+','+item.spId+','+item.spprice+','+item.samount+','+item.shopid+")' id="+'cb_add'+item.shopid+" /></li><ul></div>"+
					"<div class='cb_6' align='center' id="+'cb6_price'+item.shopid+">￥"+(item.spprice*item.samount)+"</div>"+
					"<div class='cb_7' align='center'><a onClick='deleteDiv("+item.usid+','+item.spId+','+item.shopid+")'><i class='fa fa-times fa-2x' aria-hidden='true'>"+
					"</i></a></div><div class='cb_8'></div></div>";
				
				money(item.spprice, item.samount, item.usid, item.spId,item.shopid);
				choose("cbk"+item.usid+item.spId,item.spprice,item.samount,item.usid,item.spId,item.shopid);
				
				checkColor(item.usid, item.spId, item.samount, item.shopid);
				$("#bigbox").append(str1);
			});
			/*$("#bca_right").empty();
			$("#bca_right").append("￥0");*/
			getTotalPrice();
		}
	},"json");
})

function getTotalPrice(){
	$.post("cart",{op:"findAll",usid:usida},function(data,status){  
		var totalPrice = 0;
		$.each(data, function(index,item) {
			if($("#ckb"+item.shopid).prop("checked")) {
				totalPrice += parseInt(item.spprice)*parseInt(item.samount);
			}
			checkColor(item.usid, item.spId, item.samount, item.shopid);
		});
		$("#bca_right").empty();
		$("#bca_right").append("￥"+totalPrice);
	},"json");
}

function deleteDiv(usid,spId,shopid) {
	$("#popup").css("display","block");
	$("#pop_first").empty();
	$("#pop_first").append("您确定要删除该商品吗？");
	$.post("cart",{op:"save",usid:usid,spId:spId,shopid:shopid,delState:"single"});
	getTotalPrice();
}

function closeWindow() {
	$("#popup").css("display","none");
}
function confirm() {
	$.post("cart",{op:"search"}, function(data,status){
		// console.info(data);
		var usid = data.usid;
		var spId = data.spId;
		var shopid = data.shopid;
		var ds = data.delState;
		
		if("single" == ds) {
			$("#cbox"+shopid).remove();
			$.post("cart",{op:"delete",usid:usid,spId:spId,shopid:shopid}, function(data,status){
				alert("删除成功！");
			},"json");
		} else if("all" == ds) {
			$("#bigbox").empty();
			$.post("cart",{op:"deleteall"}, function() {
				alert("删除成功！");
			},"json");
		}
		$("#popup").css("display","none");
	},"json");
}

/*
 * 数量加一
 */
function add(id,usid,spId,spprice,samount,shopid) {
	$.post("cart",{op:"add",usid:usid,spId:spId,shopid:shopid}, function(data,status){
		var state = "add";
		findAmo(usid,spId,state,spprice,samount,shopid);
	},"json");
	getTotalPrice();
}

function choose(spprice,samount,usid,spId,shopid){
	$.post("cart",{op:"findAll",usid:usid},function(data,status){
		$.each(data, function(index,item) {
			if($("#ckb"+item.shopid).prop("checked") == false) {
				$("#checkall1").prop("checked",false);
				$("#checkall2").prop("checked",false);
				return false;
			}else {
				$("#checkall1").prop("checked",true);
				$("#checkall2").prop("checked",true);
			}
		});
	},"json");
	getTotalPrice();
}

/*
 * 数量减一
 */
function sub(id,usid,spId,spprice,samount,shopid) {
	$.post("cart",{op:"sub",usid:usid,spId:spId,shopid:shopid}, function(data,status){
		var state = "sub";
		findAmo(usid,spId,state,spprice,samount,shopid);
	},"json");
	getTotalPrice();
}

/*
 * 单件商品的总价 ：数量*单价
 * 以及 所有商品的总价格
 */
function money(spprice, samount, usid, spId,shopid) {
	var moneyid = "cb6_price"+shopid;
	$("#"+moneyid).empty();
	$("#"+moneyid).append("￥"+(parseInt(spprice)*parseInt(samount)));
}

/*
 * 控制按键的颜色 
 */
function checkColor(usid,spId,samount,shopid) {
	if(samount > 1 && samount < 10) {
		$("#cb_sub"+shopid).css("background-color","#00539C");
		$("#cb_add"+shopid).css("background-color","#00539C");
	} else if(samount == 1) {
		$("#cb_sub"+shopid).css("background-color","#B4B4B4");
		$("#cb_add"+shopid).css("background-color","#00539C");
	} else if(samount == 10) {
		$("#cb_sub"+shopid).css("background-color","#00539C");
		$("#cb_add"+shopid).css("background-color","#B4B4B4");
	}
}

/*
 * 查询单个商品数量
 */
function findAmo(usid,spId,state,spprice,samount,shopid){
	var i = "cb_text"+shopid;
	var amo = $("#"+i).val();
	if("add" == state) {
		$("#"+i).val(parseInt(amo)+1);
		money(spprice, parseInt(amo)+1, usid, spId,shopid);
	} else if("sub" == state) {
		$("#"+i).val(parseInt(amo)-1);
		money(spprice, parseInt(amo)-1, usid, spId,shopid);
	}
	checkColor(usid,spId,$("#"+i).val(),shopid);
}

function delAll() {
	$("#popup").css("display","block");
	$("#pop_first").empty();
	$("#pop_first").append("您确定要删除所有商品吗？");
	$.post("cart",{op:"save",usid:null,spId:null,delState:"all"});
}

function choAll(val) {
	$(".ckb").each(function() {
		$(this).prop("checked",val);
	});
	$("#checkall1").prop("checked",val);
	$("#checkall2").prop("checked",val);
	getTotalPrice();
}

function change(id){
	$(this).attr("src","images/open.png");
	if($("#"+id+" a img").attr("src") == "images/open.png"){
		$("#"+id+" a img").attr("src", "images/close.png");
	} else{
		$("#"+id+" a img").attr("src", "images/open.png");
	}
}

function goshopping(){
	location.href = "index.html";
}

function settleAcc(){ // 结算
	$.post("cart",{op:"findAll",usid:usida},function(data,status){  
		var tolprice = 0;
		var flag = false;
		$.each(data, function(index,item) {
			if($("#ckb"+item.shopid).prop("checked")) {
				flag = true
				tolprice = parseInt(item.spprice)*parseInt(item.samount);
				$.post("cart",{op:"orderInfo",usid:item.usid, spId:item.spId, tolprice:tolprice, samount:item.samount});
			}
		});
		if(!flag) {
			alert("请选择您要结算的物品!");
		} else {
			location.href = 'buy.html';
		}
	},"json");
}



