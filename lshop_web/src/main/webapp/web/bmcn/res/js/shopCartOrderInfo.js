//保留一位小数
function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;
    return value;
}

//动态显示运费
function showCarriage(contryId,shopFlag,allAmount){

	$("input[name='addressCode']").parent().parent().removeClass("choose");
	$("input[name='addressCode']:checked").parent().parent().addClass("choose");
	
	$.ajax({
	   type: "POST",
	   url: "/web/shopCart!getCarriage.action",
	   data: "deliveryId="+contryId+"&shopFlag="+shopFlag,
	   success: function(data){
	   	 	$("#carriage").html(data);
	   	 	$("#allCarriage").html(data);
	   	 	$("#amount").html(toFloat2(toFloat(toFloat(allAmount)+toFloat($("#allCarriage").html()))));
	   	 	if(data!=null&&data>0){
	   	 		$("#aa").show();
	   	 		$("#bb").show();
	   	 	}else{
	   	 		$("#aa").hide();
	   	 		$("#bb").hide();
	   	 	}
	   	 }
	});
}



//点击确认提交
function subMyForm(){
	$("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
	$("#myForm").submit();
}

//提交订单信息关键项目校验：订单收货地址,支付方式
function onValidate(){
    if($("input[name='addressCode']:checked").val()==null){
       alert("您需要先保存收货人信息,再提交订单");
       return false;
    }
    if($("input[name='payCode']:checked").val()==null){
       alert("您需要先保存支付方式,再提交订单");
       return false;
    }
    if($("#orderRemark").val().length>160){
    	alert("备注不得超过160位字符！");
    	return false;
    }
    $("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
}