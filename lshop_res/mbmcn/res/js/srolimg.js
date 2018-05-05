// JavaScript Document

 $(function(){
	// 倒计时
	lxfEndtime();

 
	 
	 //滑动
	   var liw= $(".imgflag").find("ul").find("li").width();
	   var lis=$(".imgflag").find("ul").find("li");
	   var licon=lis.size()-1;
	   var tt=document.getElementById("tt");
	   function pistion(){
		   var posid=$(".imgflag").find("ul").css("margin-left");
		   posid=posid.replace("px"," ");
		   posid=parseInt(posid);
		   return posid;
		   }
	
		   

    
     var ind=1;
   
	  var hammertime = Hammer(tt, {
            prevent_default: false,
        }).on('dragleft', function(ev){
			 ind++;
			 if(ind>lis.size()){
				 ind=lis.size();
				 }
				  if(ind<1){
				ind=1;
				}
				
			 var piss=pistion();
			 if(piss<=(-licon*liw)){
				   piss=-(licon*liw);
				   $(".imgflag").find("ul").animate({"margin-left":piss},300);
					$(".imgconts").find("span").html(5);
				   }else{
			  $(".imgflag").find("ul").animate({"margin-left":piss-liw},300);
                 $(".imgconts").find("span").html(ind);
				 
				   }
			}).on('dragright',function(ev){
				
				 if(ind>=lis.size()){
				 ind=lis.size();
				 }
				 if(ind<1){
				 ind=1;
				}
				
				 var piss=pistion();
				 if(piss>=0){
				   piss=0;
				   $(".imgflag").find("ul").animate({"margin-left":piss},300);
				   $(".imgconts").find("span").html(1);
				}else{
				//var inds=ind-1;
				$(".imgflag").find("ul").animate({"margin-left":piss+liw},300);
				$(".imgconts").find("span").html(ind-1);
				ind--;
				}
				})
		   
		   
    })
	//倒计时
  function lxfEndtime(){
          $(".lxftime").each(function(){
                var lxfday=$(this).attr("lxfday");//用来判断是否显示天数的变量
                var endtime = new Date($(this).attr("endtime")).getTime();//取结束日期(毫秒值)
                var nowtime = new Date().getTime();        //今天的日期(毫秒值)
                var youtime = endtime-nowtime;//还有多久(毫秒值)
                var seconds = youtime/1000;
                var minutes = Math.floor(seconds/60);
                var hours = Math.floor(minutes/60);
                var days = Math.floor(hours/24);
                var CDay= days ;
                var CHour= hours % 24;
                var CMinute= minutes % 60;
                var CSecond= Math.floor(seconds%60);//"%"是取余运算，可以理解为60进一后取余数，然后只要余数。
                if(endtime<=nowtime){
                        $(this).html("已过期")//如果结束日期小于当前日期就提示过期啦
                        }else{
                                if($(this).attr("lxfday")=="no"){
                                        $(this).html("<i>剩余：</i><span>"+hours+"</span>时<span>"+CMinute+"</span>分<span>");          //输出没有天数的数据
                                        }else{
                        $(this).html("<i>剩余：</i><span>"+days+"</span><em>天</em><span>"+CHour+"</span><em>时</em><span>"+CMinute+"</span><em>分</em><span>");          //输出有天数的数据
                                }
                        }
          });
   setTimeout("lxfEndtime()",1000);
  };