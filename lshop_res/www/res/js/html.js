//动态网页静态化需要引入文件
$(function() {
	//加载商品价格
	var shopObj = {'bscn': [], 'bsen': [], 'mbscn': [], 'tvpadcn': [], 'tvpaden': [], 'mtscn': []};//刷新商品价格对象
	var $p = $('.p_price');
	$p.each(function(i, elem){
		var f = $(elem).attr('f'), p = $(elem).attr('p');
		shopObj[f].push(p);
	});
	for(var k in shopObj) {
		var prods = shopObj[k];
		if(prods.length > 0){
			//去除重复元素
			var tem = [];
			$.each(prods, function(i, el){
				if($.inArray(el, tem) === -1) tem.push(el);
			});
			//刷新商品价格
			var pcode = tem.join(',');
			$.post('/web/product!loadProdPrice.action', {shopFlag: k, prodCode: pcode}, function(data){
				var productList=data.productList;
				for(var k=0;k<productList.length;k++){
					var product=productList[k];
					var isGift=product.isGift;
					var activityPriceShow=product.activityPriceShow;
					var priceShow=product.priceShow;
					var type=product.type;
					var currency=product.currency;
					$p.each(function(i, elem){
						if($(elem).attr('p') === product.productNo){
							if(type==0){
								var productHtml="<span class='price'><span class='price2'><font class='usd'>"+currency+"</font> "+priceShow+"</span>";
								if(isGift){
									productHtml+="<img src='http://res.mtvpad.com/www/res/images/zp_icon.png' width='40' height='22' />";
								}
								productHtml+="</span>";
								$(elem).find(".price").remove();
								$(elem).append(productHtml);
							}else{
								var productHtml="<span class='price'>原价：<span class='price3'> "+currency+" "+priceShow+"</span></span>" +
										        "<span class='price'>活动价：<span class='price2'><font class='usd'>"+currency+"</font>"+activityPriceShow+"</span></span>";
								$(elem).find(".price").remove();
								$(elem).append(productHtml);
							}
						
						}
					});
				}
			});
		}
	}
});