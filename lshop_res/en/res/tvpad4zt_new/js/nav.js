// JavaScript Document
/*$(document).ready(function(){
	var $menu_li=$("#menu li");
	var $menu_text_li=$("#menu_text li");
	$menu_li.hover(function(){
		var index=$menu_li.index(this);
		$menu_text_li.eq(index).show().siblings().hide()
		},function(){
		var index=$menu_li.index(this);
			$menu_text_li.eq(index).hide();
			})
	}) */
$(document).ready(function(){
	var $menu_li=$("#menu li");
	var $menu_text_li=$("#menu_text li");
	$menu_li.mouseover(function(){
		var index=$menu_li.index(this);
		$menu_text_li.eq(index).css("display","block")
		}).mouseout(function(){
		var index=$menu_li.index(this);
		$menu_text_li.eq(index).css("display","none")
		}) 
	})