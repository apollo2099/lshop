// JavaScript Document
$(window).ready(function(){                   
        autoScrolling();
    	});
function autoScrolling(){						
	var $ww = $(window).width();
	if($ww < 360){
		$(".hd_cpu,.hd_h,.content_li").hide()
	} else {
		$(".hd_cpu,.hd_h,.content_li").show()
	}
}

autoScrolling();
	