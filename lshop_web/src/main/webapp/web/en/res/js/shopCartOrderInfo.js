			
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
					$("#couponMsg").html("<font color='red'>Please enter the promo code!</font>");
				}else{
					$.ajax({
					   type: "POST",
					   url: "/web/shopCart!checkCouponCode.action",
					   data: "couponCode="+couponCode,
					   success: function(str){
					   	 	var o = eval('(' + str + ')');
					   	 	if(o.flag==1){
					   	 		$("#couponMsg").html("<font color='red'>The coupon does not exist!</font>");
					   	 		$("#allCoupon").html(0);
					   	 	}else if(o.flag==2){
					   	 		$("#couponMsg").html("<font color='red'>The coupon has been removed!</font>");
					   	 		$("#allCoupon").html(0);
					   	 	}else if(o.flag==3){
					   	 		$("#couponMsg").html("<font color='red'>The coupon is not yet activated!</font>");
					   	 		$("#allCoupon").html(0);
					   	 	}else if(o.flag==4){
					   	 		$("#couponMsg").html("<font color='red'>The coupon is expired!</font>");
					   	 		$("#allCoupon").html(0);
					   	 	}else if(o.flag==6){
					   	 		$("#couponMsg").html("<font color='red'>The coupons have been used!</font>");
					   	 		$("#allCoupon").html(0);		   	 		
					   	 	}else{
					   	 		$("#couponMsg").html("The coupon is verified! The coupon amount is <font class='redfont'>USD <span id='price'>"+o.money+"</span></font>, and the valid date is before <font class='redfont'>"+o.validatydate+"</font>");
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
			       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			  	   ymPrompt.alert({title:'Tips',height:230,message:'Please choose a shipping address. If your frequently used address is empty, please add new frequently used address information first!'}) ;
			       return false;
			    }
			    if($("input[name='payCode']:checked").val()==null){
			       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			  	   ymPrompt.alert({title:'Tips',message:'Please choose a mode of payment for your order!'}) ;
			       return false;
			    }
			 //   if($("input[name='bestDeliveryTime']:checked").val()==null){
			 //      alert("請選擇最佳收貨時間！");
			 //      return false;
			 //   }
			    if($("#orderRemark").val().length>160){
			       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			  	   ymPrompt.alert({title:'Tips',message:'Remark shall be no longer than 160 characters!'}) ;
			    	return false;
			    }
			    $("#myAddress").val($("input[name='addressCode']:checked").val());
				$("#payValue").val($("input[name='payCode']:checked").val());
			//	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
				$("#myCoupon").val($("#couponCode").val());
			}