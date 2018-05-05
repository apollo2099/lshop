
	//只能输入数字
	function onlyNumber()
	{
		var reg = new RegExp("^[0-9]*$");
		var num = $("#num").val();
	    if(!reg.test(num)){
	        return false;
	    }else{
	    	return true;
	    }
	} 
	
	//修改数量		
	function changeNum(obj){
		if(onlyNumber()){
			var nowNum=0;
			var num=$("#num").val();
			if(obj=="add"){
				if(num<9999){
					nowNum=Number(num)+1;
				}else{
					nowNum=Number(num);
					ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
				    ymPrompt.alert({title:'Tips',message:'Qty. shall be at most 9999.'}) ;
				    nowNum=9999;
				}
			}else if(obj=="del"&&num>1){
				nowNum=Number(num)-1;
			}else{
			    nowNum=Number(num);
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
				nowNum=1;
			}
			$("#num").val(nowNum);
		}else{
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    ymPrompt.alert({title:'Tips',message:'Please enter round figures only!'}) ;
			$("#num").val(1);
			$("#num").focus();
		}
	}

	//保留一位小数	
	function toFloat(value) { //保留一位小数点
	    value = Math.round(parseFloat(value) * 100) / 100;
	    return value;
	}

	//制保留2位小数，如：2，会在2后面补上00.即2.00  
	function toFloat2(f){
		var s = f.toString();  
	    var rs = s.indexOf('.');  
	    if (rs < 0) {  
	        rs = s.length;  
	        s += '.';  
	    }  
	    while (s.length <= rs + 1) {  
	        s += '0';  
	    }  
	    return s;  
	} 
	
	function showSelectInfo(a,obj){
	
		var num=$("input[name='commendProduct']:checked").length-1;
		$("#selectNum").html(num);
		var tmpPrice=toFloat($("#selectPrice").html());
		if(a.checked){
			tmpPrice=tmpPrice+toFloat(obj);
		}else{
			tmpPrice=tmpPrice-toFloat(obj);
		}
		$("#selectPrice").html(toFloat2(toFloat(tmpPrice)));
	}

	//加入购物车	
	function toPut(productCode,storeId){
		if(onlyNumber()){
			if($("#num").val()<1){
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
				$("#num").val(1);
				$("#num").focus();
			}else{
				var users=lshop.getCookieToJSON('user');
				if(users.uid!=null){
					$.ajax({
					   type: "POST",
					   url: "/web/mall!putShopCart.action",
					    data: "lvShopCart.productCode="+productCode+"&lvShopCart.shopNum="+$("#num").val(),
					   success: function(num){
					   		if(num==-1){
					   			top.window.location.reload(true); 
					   		}else if(num==-2){
					   			top.window.location.reload(true); 
					   		}else{
					   			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    			    ymPrompt.alert({title:'Tips',height:230,message:'The item has been successfully added into Shopping Cart! For detailed information, please check your Shopping Cart!'}) ;
					   	 		$("#shopCartNum").html(num);
					   		}
					   	 }
					});
				}else{
					$.ajax({
					   type: "POST",
					   url: "/web/index!putCartCookie.action",
					   data: "storeId="+storeId+"&productCode="+productCode+"&num="+$("#num").val()+"&shopCartNum="+$("#shopCartNum").html(),
					   async: false,
					   success: function(num){
							ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    			ymPrompt.alert({title:'Tips',height:230,message:'The item has been successfully added into Shopping Cart! For detailed information, please check your Shopping Cart!'}) ;
							$("#shopCartNum").html(num);
					   	 }
					});
				}
			}
		}else{
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    ymPrompt.alert({title:'Tips',message:'Please enter round figures only!'}) ;
			$("#num").val(1);
			$("#num").focus();
		}
	}

	//立即购买
	function toSub(storeId,productCode,storeDomain){
		if(onlyNumber()){
			if($("#num").val()<1){
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
				$("#num").val(1);
				$("#num").focus();
			}else{
				var users=lshop.getCookieToJSON('user');
				if(users.uid!=null){
					$("#buyForm").attr("action",storeDomain+"/web/mall!saveShopCart.action?shopFlag="+storeId);
					$("#buyForm").submit();
				}else{
					$.ajax({
					   type: "POST",
					   url: "/web/index!putCartCookie.action",
					   data: "storeId="+storeId+"&productCode="+productCode+"&num="+$("#num").val()+"&shopCartNum="+$("#shopCartNum").html(),
					   async: false,
					   success: function(){
							$("#buyForm").attr("action",storeDomain+"/web/mall!getShopCartList.action");
							$("#buyForm").submit();
					   	 }
					});
				}
			}
		}else{
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    ymPrompt.alert({title:'Tips',message:'Please enter round figures only!'}) ;
			$("#num").val(1);
			$("#num").focus();
		}
	}

	//组合购买
	function toSubMatch(storeId,productCode,storeDomain){
		var users=lshop.getCookieToJSON('user');
		if(users.uid!=null){
			$("#matchForm").attr("action",storeDomain+"/web/mall!saveMatchShop.action?shopFlag="+storeId);
			$("#matchForm").submit();
		}else{
			var obj=$("input[name='commendProduct']:checked");
			for(var i=0; i<obj.length; i++){
				var productCode=$(obj[i]).val();
				$.ajax({
				   type: "POST",
				   url: "/web/index!putCartCookie.action",
				   data: "storeId="+storeId+"&num=1&productCode="+productCode+"&shopCartNum="+$("#shopCartNum").html(),
				   async: false,
				   success: function(){
						$("#matchForm").attr("action",storeDomain+"/web/mall!getShopCartList.action");
						$("#matchForm").submit();
				   	 }
				});
			}
		}
	}

	//阶梯价
	function showLadder(productCode){
		ymPrompt.win({
			message:'/web/mall!getLadderPrice.action?productCode='+productCode,
			width:360,
			height:210,
			title:'Discounts ',
			handler:handler,
			iframe:true
		});
	}
	
	function handler(){
	}
		
	//变换图片
	function changeImage(newImage){
		$("#bigImage").attr("src",newImage);
	}

	//修改推荐样式
	function changeRecommendDiv(mProductsLen){
		var len=$("#recommendDiv").width();
		if(mProductsLen>3){
			$("#recommendDiv").width(len+180*(mProductsLen-3));
		}
	}	

	//图片样式
	$(function(){			
	   $(".jqzoom").jqueryzoom({
			xzoom:330,//放大图显示范围
			yzoom:390,//放大图显示范围
			offset:10,
			position:"right",
			preload:1,
			lens:1
		});
		$("#spec-list").jdMarquee({
			deriction:"left",
			width:322,//小图显示范围
			height:58,//小图高度
			step:2,
			speed:4,//小图滚动速度
			delay:10,
			control:true,
			_front:"#spec-right",//右边按钮容器
			_back:"#spec-left"//左边按钮容器
		});
		$("#spec-list img").bind("mouseover",function(){
			var src=$(this).attr("src");
			$("#spec-n1 img").eq(0).attr({
				src:src.replace("\/n5\/","\/n1\/"),
				jqimg:src.replace("\/n5\/","\/n0\/")
			});
			$(this).css({
				"border":"2px solid #ff6600",//小图鼠标经过时描边
				"padding":"1px"
			});
		}).bind("mouseout",function(){
			$(this).css({
				"border":"1px solid #ccc",//小图鼠标离开时描边
				"padding":"2px"
			});
		});				
	})
	
	//调整切换tab样式 
	function changeTabCss(pageMark,allCount){
		if(pageMark==1){
			MainItem(allCount,allCount);
		}else{
			MainItem(1,allCount);
		}
	}