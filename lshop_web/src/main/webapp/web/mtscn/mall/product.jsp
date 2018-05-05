<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_商品详情</title>
<!-- 加载公共JS -->
<%@include file="/web/mtscn/common/top.jsp" %>
<script type="text/javascript"  src="${resDomain}/mtscn/res/js/jquery.hammer.js"></script>
<script type="text/javascript" src="${resDomain}/mtscn/res/js/product.js"></script>
<style type="text/css">
#curspan{height: 42px;}
</style>
</head>

<body> 
<header>
   <div class="shopping">
         <div class="shoplebg1">
            <div class="shopicon1"><a href="${storeDomain}"></a></div>
         </div>
      </div>
   <div class="top">
      <div class="title" id="title1">
        <h1>商品详情</h1>
        <%-- <span>English</span>--%>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>

<article class="c-detail">
<form id="buyForm" action="" method="post">
    <input type="hidden" name="lvShopCart.productCode" value="${product.code }" id="pcode"/>
    <input type="hidden" name="lvShopCart.storeId" value="${product.storeId }"/>
    <input type="hidden" name="lvShopCart.shopNum" value="1"/>
    
  <input type="hidden" name="cookieStr" id="cookieStr" value=""/>
  <div class="bigimg">
    <div class="imgconts"><span class="c-pic-ind">1</span>/${fn:length(pImages) + 1}</div>
    <div class="imgflag c-slider" id="c_detail_img">
       <ul>
       	  <li><img src="${product.pimage }" width="236"/></li>
        	<c:foreach items="${pImages}" var="mg" varStatus="status">
        		<li><img src="${mg.productImage }" width="236"/></li>
        	</c:foreach>
       </ul>
   </div>

     <!-- 商品信息和商品活动信息区域 -->
	 <div class="imgreture1">
      <h2>${product.productName }</h2>
      <div id="p_orgin_price_area" p="${product.code}">
      <p >
        <span>价格:</span>&nbsp;&nbsp;<i class="usd" id="p_orign_price">USD 999.0</i>
      </p>
      </div>
      <p id="giftTitle" style="display:none">
       <span>促销信息:</span>&nbsp;&nbsp;<i class="usd" style="font-size:12px"></i>
      </p>
      <div class="zp_prodict" id="gift" style="display:none">
       <div class="zp_tito">赠品:&nbsp;&nbsp;</div>
       <div class="zp_list"> 
      </div>
      <div class="clear"></div>
      </div>
      
      <div class="discount" id="p_ladder" style="display:none;">
			<span class="discount_span">购买多件可享受优惠折扣</span>
			<div class="discount_table"></div>
	  </div>
    </div>  
     
     
    <div class="baoyou"><span class="imgprcont">运费：</span>免费包邮（偏远地区例外）</div>
    <div class="spanblock1">
        <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
                         <tr>
                           <td height="30" class="imgprcont" style="width:42px">数量：</td>
                           <td height="30">
                            <div class="spanblock">
                              <span class="cut"></span><span class="zhi" id="num">1</span><span class="add"></span>
                              <div class="clear"></div>
                           </div>
                           </td>
                         </tr>      
      </table>
    </div>
    <div class="jiru">
      <a href="javascript:toSub('${product.storeId}','${product.code }','${storeDomain}');">立即购买</a>
      <a href="javascript:toPut('${product.code }','${product.storeId}');">加入购物车</a> 
    </div>
    <div class="infomation">
       温馨说明：海外购物产生的关税由用户承担
    </div>
  </div>
</form>
</article>

<article class="c-detail">
  <div class="commentbt1">
       <ul>
       	  <c:foreach items="${propertys}" var="pro" varStatus="status">
       	  		<li><span <c:if test="${status.count==1}">id="curspan"</c:if>>${pro.title }</span></li>
       	  </c:foreach>
         <li><span>评价（<font style="color:#ff4e00">${page.totalCount}</font>）</span></li>
   
       </ul>
     </div>
  <div class="mycomment"> 
  	<c:if test="${propertyNum!=0}">
  		<c:foreach items="${propertys}" var="pr" varStatus="status">
  		<div class="productinfo_${status.count-1}" <c:if test="${status.count!=1}">style="display:none"</c:if>>
  			${pr.content }
  		</div>
  		</c:foreach>  
  	</c:if>      
     <div class="productinfo_${propertyNum} c-pager" <c:if test="${propertyNum!=0}">style="display:none"</c:if> totalpage="${page.totalPage }">
     <c:if test="${not empty objList}">
     	<s:iterator value="page.list" status="stat" id="obj">
     		<div class="detailcoment-box">
		        <div class="comentname">
		          <strong><c:choose><c:when test="${fn:contains(obj[1].nickname, '@')}">${fn:substringBefore(obj[1].nickname, '@')}</c:when><c:otherwise>${obj[1].nickname}</c:otherwise></c:choose><img src="${resDomain }/mtscn${obj[0] }" /></strong>
		          <span>
		          <s:date name="#obj[1].createTime" format="yyyy-MM-dd HH:mm:ss"/></span>
		          <div class="clear"></div>
		        </div>
		        <div class="star">
		        	<c:foreach begin="1" end="${obj[1].score}">
              			 <span></span>
              		</c:foreach>
		          <div class="clear"></div>
		        </div>
		        <div class="comenttext">
		           ${obj[1].content }
		        </div>
		        <div class="shopcoouts">购买数量：<em>${obj[1].pnum }</em></div>  
		        <div id="henxian"></div>
		      </div>
		      </s:iterator>
     </c:if>
     <c:if test="${page.totalPage>1}"> 
     <div class="more1" nextpage="2" build="buildProductComments">
           <span  >加载更多</span>
     </div>
     </c:if>
     </div>
  </div>
</article>

<article class="c-detail">
   <div class="liulang">
     <h2>买过此商品的用户还喜欢</h2>
     <div class="productlist">
        <ul>
        	<c:foreach items="${rProducts }" var ="r" varStatus="vs">
        	<c:if test="${vs.index < 2}">
	        	 <li>
	            <div class="libox" id="libox">
	             <div class="proimg"><a href="/products/${r.code }.html"><img src="${r.pimage }" width="63%"/></a></div>
	             <div class="imtitile">
	               <h2>${r.productName }</h2>
	             </div>
	             <div class="imgjia">
	              <span></span>
	              <span class="p_price" f="${r.storeId}" p="${r.code}">USD ${r.price  }</span>
	             </div>
	            </div>
	          </li>
	        </c:if>
        	</c:foreach>
          <div class="clear"></div>
        </ul>
     </div>
   </div>
</article>

<!-- 商品大图 -->
<article class="c-detail" style="display:none;">
	<div class="bigimg">
		<div class="imgconts"><span class="c-pic-ind">1</span>/${fn:length(pImages) + 1}</div>
		<div class="imgflag c-slider">
		<ul>
			<li><img src="${product.pimage }" width="90%"/></li>
        	<c:foreach items="${pImages}" var="mg" varStatus="status">
        	<li><img src="${mg.productImage }" width="90%"/></li>
        	</c:foreach>
        </ul>
		</div>
		<div class="imgreture">
			<a href="javascript:void(0)" id="c_img_detail"></a>
		</div>
	</div>
</article>

<!-- 分享 -->
<%@include file="/web/mtscn/common/share.jsp" %>
<!-- footer -->
<%@include file="/web/mtscn/common/footer.jsp" %>
<div class="maketip">
  <div class="mark"></div>
  <div class="mark_tip">
     <div class="mark_tip_titile"><h2>添加成功</h2></div>
     <div class="tipdetail">商品已成功加入购物车</div>
     <div class="bt_marktip">
       <a href="#" style="background-color:#ff9c00">去购物车</a>
       <a href="javascript:void(0);" style="background-color:#55c822; color:#fff">再逛逛</a>
     </div>
  </div>
</div>
<script type="text/javascript" src="${resDomain}/mtscn/res/js/slider.js"></script>
<script type="text/javascript">
//查看更多分页加载器
$('.c-pager .more1').click(function(e){
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

function buildProductComments(pageNo){
	//取得产品评论
	var htm = '';
	var url = '/web/product!toProductComments.action?&pcode=${product.code }&page.pageNum='+pageNo;
	$.ajax({
	url: url,
	async: false,
	dataType: 'html',
	success: function(data){
			htm = data;
		}
	});
	
	return htm;
}
</script>
<script type="text/javascript">
$('.c-slider').each(function(index, ele){
	var $u = $(ele).find('ul');
	var n = $u.find('li').length;
	$u.css('width', n+'00%');
	$u.find('li').css('width',(100/n)+'%');
});
//查看商品大图
$('#c_detail_img').live('click','img', function(e){
	$('.c-detail').toggle();
});
$('#c_img_detail').click(function(e){
	$('.c-detail').toggle();
});

//容器移动事件
function showPic($e, ind){
	var wid = $e.find('li').width();
	var mglt = '-'+ind*wid+'px';
	$e.animate({"margin-left":mglt},500, function(){
		$e.css('margin-left', mglt);
	});
	$e.parent().siblings('.imgconts').find('.c-pic-ind').text(ind+1);
}
$('.c-slider').bind('prev',function(e){
	var $e = $(this).find('ul');
	var wid = $e.find('li').width(), ml = $e.css('margin-left');
	var mlt = -parseInt(ml.substring(0, ml.length-2));
	var cur = Math.round(mlt / wid);
	if(cur == 0){
		showPic($e, 0);
	} else {
		showPic($e, cur-1);
	}
	return false;
}).bind('next', function(e){
	var $e = $(this).find('ul');
	var len = $e.find('li').length, wid = $e.find('li').width(), ml = $e.css('margin-left');
	var mlt = -parseInt(ml.substring(0, ml.length-2));
	var cur = Math.round(mlt / wid);
	if(cur == len-1){
		showPic($e, cur);
	} else {
		showPic($e, cur+1);
	}
	return false;
});
</script>
<script type="text/javascript">
var PageConfig = {Product_Status_Normal: 1, Type_Activity_LimitTime: 1, Type_Activity_LimitCount: 2, Type_Activity_Ladder: 3,TYPE_ACTIVITY_APPOINT:4};
var ProdLimitTime = 0;
$(function() {
	//限时活动倒计时
	function countLimitTime() {
		var tmpDay,tmpHour,tmpMinute,tmpSecond; 
	    tmpDay=parseInt(ProdLimitTime/(24*60*60));
	    tmpHour=parseInt((ProdLimitTime%(24*60*60))/(60*60)); 
	    tmpMinute=parseInt((ProdLimitTime%(24*60*60)%(60*60))/60); 
	    tmpSecond=parseInt((ProdLimitTime%(24*60*60)%(60*60))%60);  
	    if(ProdLimitTime>0&&tmpDay>=0&&tmpHour>=0&&tmpMinute>=0&&tmpSecond>=0){
			$("#curTime").html(tmpDay+"天"+tmpHour+"小时"+tmpMinute+"分"+tmpSecond+"秒"); 
			ProdLimitTime=ProdLimitTime-1;
			setTimeout(countLimitTime,1000);
	    }else{
	    	$p.trigger('loadDetailActivity');
		}
	}
	//限量活动更新库存
	function updateProdStore() {
		var pcode = $p.attr('p');
		$.ajax({
			   type: "get",
			   url: "/web/mall!getLimitCount.action",
			   data: "productCode="+pcode,
			   success: function(data){
				   if("0" === $.trim(data)) {
						window.location.reload();						   
				   } else {
						$("#curCount").html(data);
						setTimeout(updateProdStore, 10000);
				   }
				}
			});
	}
	//加载商品详情实时价格
	var $p = $('#p_orgin_price_area');
	$p.bind('loadDetailActivity', function(e){
		var pcode = $p.attr('p');
		//load data
		$.post('/web/product!getProdDetailPrice.action', {prodCode: pcode}, function(data){
			if(data.prodStatus != PageConfig.Product_Status_Normal) {
				alert('商品已经下架');
			}
			$('.c_ac').remove();
			$('#p_orign_price').text(data.currency+' '+data.orignPrice);
			switch(data.activityType) {
			case PageConfig.Type_Activity_LimitTime :
				$('#p_ac_til').text(data.activityName);
				$('#p_ac_price').text(data.currency+' '+data.activityPrice);
				var restTime;
				var html = '';
				if(data.beginTime > data.curTime) {
					html += '<p class="c_ac"><span>原价:</span>&nbsp;&nbsp;<i class="usd">'+data.currency+' '+data.orignPrice+'</i></p>';
					html += '<p class="c_ac"><span style="text-decoration: line-through">促销:&nbsp;&nbsp;<i class="usd" style="color:#9d9d9d">'+data.currency+' '+data.activityPrice+'</i></span></p>';
					html += '<div class="lxftime1 c_ac">';
				    html += '<span>'+data.activityName+'</span>';
				    html += '<span class="lxftime">';
				    html += '<i>离开始剩余：</i><span id="curTime"></span>';
				    html += '</span>';
				    html += '</div>';
					restTime = data.beginTime - data.curTime;
				} else {
					html += '<p class="c_ac"><span>促销:</span>&nbsp;&nbsp;<i class="usd">'+data.currency+' '+data.activityPrice+'</i></p>';
					html += '<p class="c_ac"><span style="text-decoration: line-through">原价:&nbsp;&nbsp;<i class="usd" style="color:#9d9d9d">'+data.currency+' '+data.orignPrice+'</i></span></p>';
					html += '<div class="lxftime1 c_ac">';
				    html += '<span>'+data.activityName+'</span>';
				    html += '<span class="lxftime">';
				    html += '<i>离结束剩余：</i><span id="curTime"></span>';
				    html += '</span>';
				    html += '</div>';
					restTime = data.endTime - data.curTime;
				}
				$p.hide().after(html);
				//开始倒计时
				ProdLimitTime = parseInt(restTime/1000);
				countLimitTime();
				$('#curTime').show();
				break;
			case PageConfig.Type_Activity_LimitCount :
				if(data.limitCount > 0) {
					var html = '<p class="c_ac"><span>促销:</span>&nbsp;&nbsp;<i class="usd">'+data.currency+' '+data.activityPrice+'</i></p>';
				    html += '<p class="c_ac"><span style="text-decoration: line-through">原价:&nbsp;&nbsp;<i class="usd" style="color:#9d9d9d">'+data.currency+' '+data.orignPrice+'</i></span></p>';
				    html += '<div class="lxftime1 c_ac">';
				    html += '<span>'+data.activityName+'</span>';
				    html += '<span class="lxftime">';
				    html += '<span class="lxftime2"><font>剩余数量：</font>'+data.limitCount+'</span>';
				    html += '</span>';
				    html += '</div>';
				    $p.hide().after(html);
				}
				break;
			case PageConfig.Type_Activity_Ladder :
				$('#p_ladder').show();
				//加载阶梯价信息
				$.post('/web/mall!getLadderPrice.action', {productCode: pcode}, function(data){
					$('#p_ladder').find('.discount_table').append(data);
				});
				break;
			case PageConfig.TYPE_ACTIVITY_APPOINT :
				var givenTypeNum=data.givenTypeNum;
				if(givenTypeNum==1){
					var givenTypeName=data.givenTypeName;
					var uncollectedTotal=data.uncollectedTotal;
					if(uncollectedTotal>0){
					  var givenHtml="<img src='${resDomain}/mtscn/res/images/zq.jpg' style='margin-left:0; margin-right:10px'>"+givenTypeName+"</i>"
					  $('#giftTitle .usd').append(givenHtml);
					  $('#giftTitle').show();
					}
				}else if(givenTypeNum==2){
					var givenTypeName=data.givenTypeName;
					var lotteryNum=data.lotteryNum;
					var givenHtml="<img src='${resDomain}/mtscn/res/images/cjjh.jpg' style='margin-left:0; margin-right:10px'>"+givenTypeName+"抽奖次数"+lotteryNum+"次</i>"
					$('#giftTitle .usd').append(givenHtml);
					$('#giftTitle').show();
				}else if(givenTypeNum==3){
					var activityName=data.activityName;
					var givenHtml="<img src='${resDomain}/mtscn/res/images/zp1.jpg' style='margin-left:0; margin-right:10px'>"+activityName+"</i>"
					$('#giftTitle .usd').append(givenHtml);
					var s=data.giftList;
					var giftHtml="";
					var len=0;
					if(data.giftList.length>6){
						len=6;
					}else{
						len=data.giftList.length;
					}
					for(var i=0;i<len;i++){
						var giftName=data.giftList[i].giftName;
						var giftEveryNum=data.giftList[i].giftEveryNum;
				        giftHtml+="<span><i class='ihind'>"+giftName+"</i><i class='usd xcont'>x"+giftEveryNum+"</i></span>"
					}
					if(giftHtml!=null&&giftHtml.length>0){
						$('#gift .zp_list').append(giftHtml)
						$('#gift').show();
						$('#giftTitle').show();
					}
				
				}
			
				break;	
				
			}
		});
	});
	$p.trigger('loadDetailActivity');
});
</script>
<script type="text/javascript" src="${resDomain}/mtscn/res/js/html.js"></script>
</body>
</html>

