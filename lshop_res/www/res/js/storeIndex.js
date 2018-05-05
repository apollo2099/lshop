
/*
	//定义全局变量，存放从JS获取的店铺区域信息
	var shopData; //0，所有的洲列表 1，所有的店铺列表 2，洲组合列表
	$(function(){
		$.ajax({
		   type: "get",
		   url: "/web/store!getProductsForIp.action",
		   dataType:"JSON",
		   async:false,
		   success: function(data){
		   		shopData = eval(data);
				getShopProducts();
				getStores();
		   }
		})
	});

	function getShopProducts(){
   		if(shopData[0].length>=1){
  			var str = "";
  			$.each(shopData[0],function(n,obj) {
				str += "<h2 class='bt2'>";
				str += "<p class='cx'>"+obj[0].subjectName+"</p>";
				if(obj[1].length>4){
					str += "<span class='more'><a href='/web/store!showMoreProducts.action?subjectType="+obj[0].code+"&exhibitType="+obj[0].exhibitType+"'>更多>></a></span>"
				}
				str += "</h2>";
				if(obj[0].exhibitType==1){
					$.each(obj[1],function(m,ob){
						if(m<4){
							str += "<p class='zq_image'><a href='"+ob[0].url+"'><img src='"+ob[0].pimageAd+"' border='0' width='706px' height='220px'/></a></p>";
						}
					});
				}else{
					str += "<ul class='pro_zs1'>";
					$.each(obj[1],function(m,ob){
						if(m<4){
							str += "<li>";
							str += "<a href='"+ob[0].url+"'>";
							str += "<img src='"+ob[0].pimage+"' width='158px' height='122px'/>";
							str += "<p><span class='mingcheng'>"+ob[0].productName+"</span><span class='price'>價格：<span class='price1'>USD "+ob[0].productPrice+"</span></span><span class='country'>"+ob[1].country+"</span><span class='city'> - "+ob[1].city+"</span></p>";
							str += "</a>";
							str += "</li>";
						}
					});
					str += "<div class='cb'></div>";
					str += "</ul>";
				}
  			});
  			$("#pDiv").html(str);
  		}
	}
	
	function getStores(){
		if(shopData[1].length>=1){
			var str = "";
			str += "<h2 class='bt2'><p class='cx'>推薦店鋪<span class='yw'></span></p></h2>";
			str += "<ul class='pro_zs2'>";
			$.each(shopData[1],function(n,store) {
				if(n<8){
					var tmp = "javascript:saveCookieForStore('"+store.code+"','"+store.name+"','"+store.domainName+"');";
					var tmp1 = "/web/store!toShopList.action?continentCode="+store.continentCode+"&countryCode="+store.countryCode;
					var tmp2 = "/web/store!toShopList.action?continentCode="+store.continentCode+"&countryCode="+store.countryCode+"&cityCode="+store.cityCode;
					str += "<li>";
					str += "<p class='shangjia_logo'><a href="+tmp+"><img src='"+store.logo+"' border='0' width='120px' height='40px'/></a></p>";
					str += "<p><span class='name'><a href="+tmp+">"+store.name+"</a></span><span class='country1'><a href="+tmp1+">"+store.country+"</a></span><span class='city1'> - <a href="+tmp2+">"+store.city+"</a></span></p>";
					str += "</li>";
				}
			});
			str += "<div class='cb'></div>";
			str += "</ul>";
			str += "</div>";
			$("#sDiv").html(str);
		}
	}
*/


/**************************商城首页广告图*******************************************/
	//保证导航栏背景与图片轮播背景一起显示
	
	//滚动Banner图片的显示
	$(function(){
		$("#mainbody").removeClass();
		$("#mainbody").addClass("index_bg01");
		
		if($("#slides").size()>0){
			$('#slides').slides({
				preload: false,
				preloadImage: 'res/images/loading.gif',
				effect: 'fade',
				slideSpeed: 400,
				fadeSpeed: 100,
				play: 5000,
				pause: 100,
				hoverPause: true
			});
		}

   	});

/**************************店铺导航*******************************************/	
	//获取所有的洲
	function getAllContinents(){
		if(data.length>=1){
			var str = "<ul>";
			$.each(data[0],function(n,continent) {
				str += "<li id='continent"+continent.id+"'><a href='javascript:getContinent("+continent.id+");'>"+continent.areaName+"</a></li>";
			});
			str += "</ul>";
			$("#continent").append(str);
		}	
	}
	
	function showUL(obj){
		$(obj).addClass("sekuai");
	}
	function hideUL(obj){
		$(obj).removeClass("sekuai");
	}
