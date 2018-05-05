
	//定义全局变量，存放从JS获取的店铺区域信息
	var data; //0，所有的洲列表 1，所有的店铺列表 2，洲组合列表
	$(function(){
		$.ajax({
		   type: "get",
		   url: "${resDomain}/www/res/js/storeArea.js",
		   dataType:"JSON",
		   async:false,
		   success: function(jsonData){
		   		data=eval(jsonData);
		   		$('.xuanze').mouseover(showContinent);
		   	}
		})
	});
	
	
	
	//展示洲
	function showContinent(){
		if(data.length>=1){
			var str = "";
			$.each(data[0],function(n,continent) { 
				str += "<div class='dp_menu1' id='cont"+continent.id+"' >";
				str += "<h2 class='zhou' onmouseover='showCountry("+continent.id+");'><a href='javascript:showCountry("+continent.id+");'>"+continent.areaName+"</a></h2>";
				str += "<ul id='coun"+continent.id+"' class='con' style='display:none;'>";
				$.each(data[2],function(n,obj) {
					if(obj[0].id==continent.id){
						$.each(obj[1],function(n,country) {
							str += "<li onmouseover='showCity("+country.id+",event);'><a href='javascript:showCity("+country.id+",event);'>"+country.areaName+"</a></li>";
						});
					}
				});
				str += "</ul>";
				str += "</div>";
			});
	
			$("#menu_country").html(str);
			$("#menu_country").show(function(){
				  $(".zhou").click(function(){
					  var obj=$(this).next("ul");
					  $(".dp_menu>div>ul:visible").hide();
					  obj.slideDown();
					  
				  });
			});
		}
	}
	
	
	//焦点在洲
	function showCountry(id){
		$("#menu_store").hide();
		$("#coun"+id).show();
		var prevDiv = $("#cont"+id).prevAll().find("ul");
		var nextDiv = $("#cont"+id).nextAll().find("ul");
		if(prevDiv!=null){
			prevDiv.hide();
		}
		if(nextDiv!=null){
			nextDiv.hide();
		}
	
	}
	
	//焦点在国家
	function showCity(id,event){
		var str = "";
		$.each(data[3],function(n,obj) {
			if(obj[0].id==id){
				$.each(obj[1],function(n,city) {
					str += "<dl>";
					str += "<dt>"+city.areaName+"</dt>";
					$.each(data[4],function(n,ob) {
						if(ob[0].id==city.id){
							str += "<dd>";
							$.each(ob[1],function(a,store) {
								var tmp = "javascript:saveCookieForStore('"+store.code+"','"+store.name+"','"+store.domainName+"');";
								var oldName = store.name;
								var newName = store.name;
								if(oldName.length>5){
									newName = oldName.substring(0, 5)+"…";
								}
								if(store.isHot==1){
									str += "<a href="+tmp+" title='"+oldName+"'><font color='red'>"+newName+"</font></a>";
								}else{
									str += "<a href="+tmp+" title='"+oldName+"'>"+newName+"</a>";
								}
								if(a<ob[1].length-1){
									str += " |";
								}
							});
							str += "</dd>";
						}
					});
					str += "</dl>";
				});
			}
		});
		
		$("#menu_store").html(str);
		$("#menu_store").show(function(){
			 var num=$(".dp_menu2>dl").length;
			  if(num<3){
				  $(".dp_menu2").css("width",(802-(3-num)*263));
			  }else{
				  $(".dp_menu2").css("width",802);
				  
			  }
		});
		var e = event || window.event;	
		var top = e.pageY || e.y+120;
		$("#menu_store").css("top",top-180);
	}
	
	//点击店铺时，保存店铺信息至cookie中
	function saveCookieForStore(code,name,domainName){
		$.ajax({
		   type: "POST",
		   url: "/web/store!saveCookieForStore.action",
		   data: "code="+code+"&name="+name+"&domainName="+domainName,
		   async: false,
		   success: function(){
		   		var url = "http://"+domainName+"/index.html";
				window.open(url);
		   	 }
		});
	}
	
	
	/**
	 * 隐藏店铺区域导航
	 */
	$(function(){
	  var m1=0,m2=0,m3=0;
	  if($('.sc_menu_A_r'))
	  $('.sc_menu_A_r').mouseover(function(){
		  m1=1;
	  }).mouseout(function(){
		  m1=0;
		  window.setTimeout(function(){onhideall();},3000);
	  });
	  if($('.dp_menu'))
	  $('.dp_menu').mouseover(function(){
		  m2=1;
	  }).mouseout(function(){
		  m2=0;
		  onhideall();
	  });
	  if($('.dp_menu2'))
	  $('.dp_menu2').mouseover(function(){
		  m3=1;
		  
	  }).mouseout(function(){
		  m3=0;
		  onhideall();
	  });
	  function onhideall(){
			if(m1==0&&m2==0&&m3==0){
				window.setTimeout(function(){
					if(m1==0&&m2==0&&m3==0){
						$('.dp_menu').hide();
						$('.dp_menu2').hide();
					}
				},1000);
			}
		}
	});
	
//获取所有的店铺信息
function getAllStores(){
	if(data.length>=1){
		var str;
		$.each(data[1],function(n,store) { 
			var tmp = "javascript:saveCookieForStore('"+store.code+"','"+store.name+"','"+store.domainName+"');";
			var tmp1 = "/web/store!toShopList.action?continentCode="+store.continentCode+"&countryCode="+store.countryCode;
			var tmp2 = "/web/store!toShopList.action?continentCode="+store.continentCode+"&countryCode="+store.countryCode+"&cityCode="+store.cityCode;
			str += "<ul onmouseover='showUL(this);' onmouseout='hideUL(this);'>";
			str += "<li>";
			str += "<p class='img_dianpu'><a href="+tmp+"><img src='"+store.logo+"' width='120px' height='40px'/></a>";
			if(store.isHot==1){
				str += "<img src='${resDomain}/www/res/images/hot.gif'  class='hot'/>";
			} 
			str += "</p>";
			str += "<p class='text'><span class='name'><a href="+tmp+">"+store.name+"</a></span><span class='country1'><a href="+tmp1+">"+store.country+"</a></span><span class='city1'> - <a href="+tmp2+">"+store.city+"</a></span></p>";
			str += "</li>";
			str += "</ul>";
		});
	}
	str += "<div class='cb'></div>	";
	$("#shop").html(str);
}


//点击洲
function getContinent(id){
	//添加选中的显示效果
	$("#continent").find("li").removeClass("dq");
	$("#continent"+id).addClass("dq");
	
	//先清空
	$("#country").html("");
	$("#city").html("");
	
	//获取对应的国家
	if(data.length>=1){
		var str = "<dt>國家</dt>";
		str += "<dd>";
		$.each(data[2],function(n,obj) { 
			if(obj[0].id==id){
				//超过两行显示更多
				if(obj[1].length>14){
					str += "<p class='shouqi' onclick='showMoreCountry("+id+");'>更多 <img src='${resDomain}/www/res/images/gengduo1.gif' /></p>";
				}else{
					str += "<p class='shouqi'></p>";
				}
				$.each(obj[1],function(n,country) {
					if(n<14){
						var tem = "/web/store!toShopList.action?continentCode="+obj[0].code+"&countryCode="+country.code;
						str += "<p><a id='country"+country.id+"' href="+tem+">"+country.areaName+"</a></p>";
					}
				});
			}
		});
		str += "</dd>";
		str += "<div class='cb'></div>";
	}
	$("#country").html(str);

}

//点击国家栏的“更多”
function showMoreCountry(id){
	var str = "<dt>國家</dt>";
	str += "<dd>";
	$.each(data[2],function(n,obj) { 
		if(obj[0].id==id){
			str += "<p class='shouqi' onclick='showLessCountry("+id+");'>收起 <img src='${resDomain}/www/res/images/shouqi.gif' /></p>";
			$.each(obj[1],function(n,country) {
					str += "<p><a id='country"+country.id+"' href='javascript:getCountry("+country.id+","+obj[0].code+");'>"+country.areaName+"</a></p>";
			});
		}
	});
	str += "</dd>";
	str += "<div class='cb'></div>";
	$("#country").html(str);
}

//点击国家栏的“收起”
function showLessCountry(id){
	var str = "<dt>國家</dt>";
	str += "<dd>";
	$.each(data[2],function(n,obj) { 
		if(obj[0].id==id){
			str += "<p class='shouqi' onclick='showMoreCountry("+id+");'>更多 <img src='${resDomain}/www/res/images/gengduo1.gif' /></p>";
			$.each(obj[1],function(n,country) {
				if(n<14){
					str += "<p><a id='country"+country.id+"' href='javascript:getCountry("+country.id+","+obj[0].code+");');'>"+country.areaName+"</a></p>";
				}
			});
		}
	});
	str += "</dd>";
	str += "<div class='cb'></div>";
	$("#country").html(str);
}



//点击国家
function getCountry(id,continentCode){
	//添加选中的显示效果
	$("#country").find("a").removeClass("dq");
	$("#country"+id).addClass("dq");
	
	//先清空
	$("#city").html("");
	
	//获取对应的城市
	if(data.length>=1){
		var str = "<dt>城市</dt>";
		str += "<dd>";
		
		$.each(data[3],function(n,obj) {
			if(obj[0].id==id){
				//超过两行显示更多
				if(obj[1].length>14){
					str += "<p class='shouqi' onclick='showMoreCity("+id+");'>更多 <img src='${resDomain}/www/res/images/gengduo1.gif' /></p>";
				}else{
					str += "<p class='shouqi'></p>";
				}
				$.each(obj[1],function(n,city) {
					if(n<14){
						var tem = "/web/store!toShopList.action?continentCode="+continentCode+"&countryCode="+obj[0].code+"&cityCode="+city.code;
						str += "<p><a id='city"+city.id+"' href="+tem+">"+city.areaName+"</a></p>";
					}
				});
			}
		});
		str += "</dd>";
		str += "<div class='cb'></div>";
	}
	$("#city").html(str);
}

//点击城市栏的“更多”
function showMoreCity(id){
	var str = "<dt>城市</dt>";
	str += "<dd>";
	$.each(data[3],function(n,obj) {
		if(obj[0].id==id){
			str += "<p class='shouqi' onclick='showLessCity("+id+");'>收起 <img src='${resDomain}/www/res/images/shouqi.gif' /></p>";
			$.each(obj[1],function(n,city) {
				str += "<p><a id='city"+city.id+"' href='javascript:getCity("+city.id+");'>"+city.areaName+"</a></p>";
			});
		}
	});
	str += "</dd>";
	str += "<div class='cb'></div>";
	$("#city").html(str);
}

//点击城市栏的“收起”
function showLessCity(id){
	var str = "<dt>城市</dt>";
	str += "<dd>";
	$.each(data[3],function(n,obj) {
		if(obj[0].id==id){
			str += "<p class='shouqi' onclick='showMoreCity("+id+");'>更多 <img src='${resDomain}/www/res/images/gengduo1.gif' /></p>";
			$.each(obj[1],function(n,city) {
				if(n<14){
					str += "<p><a id='city"+city.id+"' href='javascript:getCity("+city.id+");'>"+city.areaName+"</a></p>";
				}
			});
		}
	});
	str += "</dd>";
	str += "<div class='cb'></div>";
	$("#city").html(str);
}

//点击城市
function getCity(id){
	//添加选中的显示效果
	$("#city").find("a").removeClass("dq");
	$("#city"+id).addClass("dq");
}

//点击洲
function clickContinent(continentCode){
	var continentId;
	$.each(data[0],function(n,continent) {
		if(continent.code==continentCode){
			continentId=continent.id;
		}
	});
	getContinent(continentId);
}

//点击国家
function clickCountry(continentCode,countryCode){
	var continentId;
	var countryId;
	$.each(data[2],function(n,obj) {
		if(obj[0].code==continentCode){
			continentId=obj[0].id;
			$.each(obj[1],function(n,country) {
				if(country.code==countryCode){
					countryId=country.id;
				}
			});
		}
	});
	getContinent(continentId);
	getCountry(countryId,continentCode);
}

//点击城市
function clickCity(continentCode,countryCode,cityCode){
	clickCountry(continentCode,countryCode);
	$.each(data[4],function(n,obj) {
		if(obj[0].code==cityCode){
			getCity(obj[0].id);
		}
	});
}
