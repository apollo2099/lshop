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
				 $("#isAgree").val("1");
  			  $("#agreemsg").hide();
			 }else{
				 $("#isAgree").val("0");
  			  $("#agreemsg").show();
			 }
		}
	
		/**
		 * 检查输入框是否合法
		 * val 检查的值
		 * len 允许的长度
		 */
		function checkInput(val,len){
			var isright=false;
			val=$.trim(val);
			if(val==null||val==""||val.length>len){
				isright=false;
			}else{
				isright=true;
			}
			return isright;
		}
		
		/**
		 * 显示提示信息
		 * @param id
		 * @param msg
		 */
		function showMsg(id,msg){
			 $("#"+id).parent().next().find("span").html(msg);
		}
		
		/**
		 * 检查是否安装flash ，没安装则提示
		 * msgid 提示语显示的id
		 * 显示的提示语
		 */
		function checkFlash(msgid){
			var msg="溫馨提示:上傳圖片需要flash支持,您當前瀏覽器沒安裝flash控件.<a href='http://www.adobe.com/go/getflashplayer/' target='_blank'>官網安裝</a>";
			var isIE = !-[1,];
			var isflash=false;
			if(isIE){
			    try{
			        var swf1 = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
			        isflash=true;
			    }
			    catch(e){
			        //alert('没有安装Flash');
			    }
			}
			else {
				msg=msg+"(火狐瀏覽器安裝后還需激活flash)";
			    try{
			        var swf2 = navigator.plugins['Shockwave Flash'];
			        if(swf2!= undefined){
			        	  isflash=true;
			        }
			    }
			    catch(e){
			       // alert('没有安装Flash');
			    }
			}

			if(!isflash){
				$("#"+msgid).html(msg);
			}
		}