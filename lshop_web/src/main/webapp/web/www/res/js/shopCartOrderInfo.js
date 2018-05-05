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
	   	 	$("#amount").html(toFloat(toFloat(allAmount)-toFloat($("#allCoupon").html())+toFloat($("#allCarriage").html())));
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

//检验优惠券
function checkCouponCode(allNum,allAmount){
	var couponCode=$("#couponCode").val();
	if(couponCode==""){
		$("#couponMsg").html("<font color='red'>請輸入優惠碼！</font>");
	}else{
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!checkCouponCode.action",
		   data: "couponCode="+couponCode,
		   success: function(str){
		   	 	var o = eval('(' + str + ')');
		   	 	if(o.flag==1){
		   	 		$("#couponMsg").html("<font color='red'>優惠券不存在！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==2){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已刪除！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==3){
		   	 		$("#couponMsg").html("<font color='red'>優惠券未激活！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==4){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已過期！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==6){
		   	 		$("#couponMsg").html("<font color='red'>該優惠券已使用！</font>");
		   	 		$("#allCoupon").html(0);		   	 		
		   	 	}else{
		   	 		$("#couponMsg").html("優惠券驗證通過！金額为：<font class='redfont'>USD <span id='price'>"+o.money+"</span></font>，有效日期為：<font class='redfont'>"+o.validatydate+"</font>");
		   	 		$("#allCoupon").html(toFloat(o.money*allNum));
		   	 	}
		   	 	$("#amount").html(toFloat(toFloat(allAmount)-toFloat($("#allCoupon").html())+toFloat($("#allCarriage").html())));
		   	 }
		});
	}
}	


//展示优惠券信息
function showDiv(){
	if($("#couponDiv").hide()){
		$("#couponDiv").show();
	}
}

//隐藏优惠券信息
function hideDiv(){
	if($("#couponDiv").show()){
		$("#couponDiv").hide();
	}
}


//点击确认提交
function subMyForm(){
	$("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
//	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
	$("#myCoupon").val($("#couponCode").val());
	$("#myForm").submit();
}

//提交订单信息关键项目校验：订单收货地址,支付方式
function onValidate(){
    if($("input[name='addressCode']:checked").val()==null){
       alert("請選擇收貨地址，如果常用地址中無地址信息，請先添加常用地址信息！");
       return false;
    }
    if($("input[name='payCode']:checked").val()==null){
       alert("請選擇訂單支付方式！");
       return false;
    }
 //   if($("input[name='bestDeliveryTime']:checked").val()==null){
 //      alert("請選擇最佳收貨時間！");
 //      return false;
 //   }
    if($("#orderRemark").val().length>160){
    	alert("備註不得超過160位字符！");
    	return false;
    }
    $("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
//	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
	$("#myCoupon").val($("#couponCode").val());
}