// JavaScript Document
$(document).ready(function() {
    $(".chak_em").hover(
		function(){
			$(".chak").css("display","block")
			},function(){
			$(".chak").css("display","none")
				});
	$(".sale").click(function(){
		$(".gift").hide().next().show()
		});
	$(".yhxx_xg1").click(function(){
		$(".receiv").hide();
		$(".gift").show();
		});
	$(".ship_add").click(function(){
		$(".address").hide();
		$(".yhxx1,.yhxx2").show();
		})
	$(".yhxx_xg2").click(function(){
		$(".yhxx2").hide();
		$(".address").show();
		});
	$(".pay_mode").click(function(){
		$(".Pay").hide();
		$(".yhxx3").show();
		})
	$(".yhxx_xg3").click(function(){
		$(".yhxx3").hide();
		$(".Pay").show();
		});
$(".input_txt1").each(function(){
     var thisVal=$(this).val();
     //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
     if(thisVal!=""){
       $(".tsy1").hide();
      }else{
       $(".tsy1").show();
      }
     //聚焦型输入框验证 
     $(this).focus(function(){
      $(".tsy1").hide();
      }).blur(function(){
        var val=$(this).val();
        if(val!=""){
         $(".tsy1").hide();
        }else{
         $(".tsy1").show();
        } 
      });
    })
$(".input_txt2").each(function(){
     var thisVal=$(this).val();
     //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
     if(thisVal!=""){
        $(".tsy2").hide();
      }else{
        $(".tsy2").show();
      }
      $(this).focus(function(){
      	$(".tsy2").hide();
      }).blur(function(){
        var val=$(this).val();
        if(val!=""){
         $(".tsy2").hide();
        }else{
         $(".tsy2").show();
        }
       })
     })
$(".input_txt3").each(function(){
     var thisVal=$(this).val();
     //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
     if(thisVal!=""){
        $(".tsy3").hide();
      }else{
        $(".tsy3").show();
      }
      $(this).focus(function(){
      	$(".tsy3").hide();
      }).blur(function(){
        var val=$(this).val();
        if(val!=""){
         $(".tsy3").hide();
        }else{
         $(".tsy3").show();
        }
       })
     })  

});