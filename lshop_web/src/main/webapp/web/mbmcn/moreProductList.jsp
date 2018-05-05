<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_更多商品</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
</head>

<body>
<header>
   <div class="shopping">
         <div class="shoplebg1">
            <div class="shopicon1"><a href="/"></a></div>
         </div>
      </div>
   <div class="top">
      <div class="title" id="title1">
        <h1>更多商品</h1>
        <span>Enlish</span>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>

<article>
     <div class="productlist" style="margin-top:20px">
        <ul class="c-pager" totalpage="${page.totalPage}">
        	<c:foreach items="${objList}" var="obj" varStatus="status">
	        <li>
	            <div class="libox">
	             <a href="${obj[0].url}">
	             <div class="proimg"><img src="${obj[0].pimage }" width="63%"/></div>
	             <div class="imtitile">
	               <h2>${obj[0].productName }</h2>
	             </div>
	             <div class="imgjia">
	              <span></span>
	              <span>${obj[1].currency} ${obj[0].productPrice}</span>
	             </div>
	             </a>
	            </div>
	         </li>
        	</c:foreach>
			<c:if test="${page.totalPage>1}">
			<div nextpage="2" build="buildProduct" class="more" style="float:left;"><span>查看更多</span></div>
			</c:if>
          <div class="clear"></div>
        </ul>
     </div>
 
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>
<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>

<script>
 $(document).ready(function(){
	   var liw= $(".adimg").find("ul").find("li").width();
	   var lis=$(".adimg").find("ul").find("li");
	   var licon=lis.size()-1;
	 
	   function pistion(){
		   var posid=$(".adimg").find("ul").css("margin-left");
		   posid=posid.replace("px"," ");
		   posid=parseInt(posid);
		   return posid;
		   }
		var startpo; 
		var endpo;
		var maxlong;  
		var piss;
		var flag;
		   $.each(lis,function(){
		  $(this).hammer({ 
	   prevent_default:false,
	   swipe:false
	   }).bind("dragstart",function(ev){  
			  piss=pistion();	   
		   }).bind("drag",function(ev){ 
		      flag=ev.direction;
			  if(flag=='left'){
			  ev.distance=0-ev.distance;
			   $(".adimg").find("ul").css({"margin-left":piss+ ev.distance});
			    maxlong=Math.abs(ev.distance);	 
			  }
			  if(flag=='right'){
			  $(".adimg").find("ul").css({"margin-left":piss+ ev.distance});
			  }
			  
			   }).bind("dragend",function(ev){
				  maxlong=Math.abs(maxlong);	 
				 if(maxlong>=(liw/2)){
					   if(flag=='left'){
						  maxlong=-liw;
						   }
						  if(flag=='right'){
							   maxlong=liw;
							   }
					    if((piss+maxlong)<(-licon*liw)){
							$(".adimg").find("ul").animate({"margin-left":piss});
							 var ind=Math.abs(piss/liw);
							  $(".indexinner").find("span").eq(ind).attr("id","cur");
							  $(".indexinner").find("span").eq(ind).siblings().removeAttr("id");
							
							}else if((piss+maxlong)>=0){
								$(".adimg").find("ul").animate({"margin-left":0});
								$(".indexinner").find("span").eq(0).attr("id","cur");
							  $(".indexinner").find("span").eq(0).siblings().removeAttr("id");
								}else{		   	
				         $(".adimg").find("ul").animate({"margin-left":piss+ maxlong});	
						      var ind=Math.abs((piss+ maxlong)/liw);
							  $(".indexinner").find("span").eq(ind).attr("id","cur");
							  $(".indexinner").find("span").eq(ind).siblings().removeAttr("id");
						 	  			    
							}
					   }else{
						   $(".adimg").find("ul").animate({"margin-left":piss});
						    var ind=Math.abs(piss/liw);
							  $(".indexinner").find("span").eq(ind).attr("id","cur");
							  $(".indexinner").find("span").eq(ind).siblings().removeAttr("id");
						   }
				   }); 
	  });
	
	//查看更多分页加载器
	$('.c-pager .more').click(function(e){
		var $n = $(this), $t = $n.parents('.c-pager');
		var next = parseInt($n.attr('nextpage')), total = parseInt($t.attr('totalpage'));
		var build = $n.attr('build');
		var html = window[build](next);
		$(html).insertBefore($n);
		if(next +1 >total){
			$n.hide();
		} else {
			$n.attr('nextpage', next +1 );
		}
	});
    });
//查看更多商品
function buildProduct(pageNo){
	//取得订单
	var htm = '';
	var url = '/web/store!pageShowMoreProducts.action?numPage=10&subjectType=${subjectType}&exhibitType=${exhibitType}';
	url += '&page.pageNum='+pageNo;
	$.ajax({
	url: url,
	async: false,
	dataType: 'html',
	success: function(da){
			htm = da;
		}
	});
	
	return htm;
}
</script>

</body>
</html>