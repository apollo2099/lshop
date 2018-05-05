/**
 * 协议弹窗
 * @param id
 */	
function show(id){
		    var W=$(document).width();
		    var H=$(document).height();
		    var mask=document.getElementById("mask");
		    mask.style.cssText="position:absolute;z-index:5;width:"+W+"px;height:"+H+"px;background:#000;filter:alpha(opacity=30);opacity:0.3;top:0;left:0;";
		    $("#mask").show();
			  var tx_b=document.getElementById(id);
			    tx_b.style.left=(window.screen.width/2-400)+"px";
			    tx_b.style.top=(150+document.documentElement.scrollTop||document.body.scrollTop)+'px';
			    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
			    	//你是使用IE
			    }else if (navigator.userAgent.indexOf('Firefox') >= 0){
			    	//你是使用Firefox
			    }else if (navigator.userAgent.indexOf('Opera') >= 0){
			    	//你是使用Opera
			    }
			    $(tx_b).fadeIn("fast",function(){});
			   //	document.getElementById("#"+id).style.height=document.body.clientHeight+"px";
			 //   document.getElementById("#"+id).style.width=document.body.clientWidth+"px";
			$("#"+id).show();
		}
		
		
		/**
		 * 点击是否同意按钮
		 * @param isagree
		 */
		function agreement(isagree){
			$("#xieyi").hide();
			$("#mask").hide();
			if(isagree=='1'){
  			  $("#checkbox").attr("checked","checked");
			}else{
	          $("#checkbox").removeAttr("checked");
			}
			changeAgree();
		}

		/**
		 * 点击勾选同意控件
		 */
		function changeAgree(){
			var isCheck=$("#checkbox").attr("checked");
			 if('checked' == isCheck || isCheck){
  			  $("#agreemsg").hide();
			 }else{
  			  $("#agreemsg").show();
			 }
		}
	
		/**
		 * 显示提示信息
		 * @param id
		 * @param msg
		 */
		function showMsg(id,msg){
			 $("#"+id).parent().next().find("span").html(msg);
		}