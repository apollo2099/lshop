<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>【${product.productName }】-- banana Mall</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bsen/common/top.jsp" %>
		<script type="text/javascript">
			$().ready(function() {
				//修改推荐样式
				changeRecommendDiv(${mProductsLen});
				//调整切换tab样式
				changeTabCss(${pageMark},${propertyNum+1});
				
			});	
		</script>
		<script src="${resDomain}/bsen/res/js/yu.js" type="text/javascript"></script>
		<script src="${resDomain}/bsen/res/js/tb.js" type="text/javascript"></script>	
		<script type="text/javascript" src="${resDomain}/bsen/res/js/js.js"></script>
		<script type="text/javascript" src="${resDomain}/bsen/res/js/cookie.js"></script>
		<link rel="stylesheet" type="text/css" href="${resDomain}/bsen/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<script type="text/javascript" src="${resDomain}/bsen/res/js/ymPrompt/ymPrompt.js" ></script>	
		<script src="${resDomain}/bsen/res/js/zoom.lib.js" type="text/javascript">//实现小图滚动效果js</script>
		<script src="${resDomain}/bsen/res/js/zoom.js" type="text/javascript">//实现放大镜效果js</script>
		<script src="${resDomain}/bsen/res/js/product.js" type="text/javascript"></script>
	</head>
	
	<body > 
		<!-- 获取店铺头部文件 -->
		<%@include file="/web/bsen/common/header.jsp" %>
		
		<!--ad-->
		<ad:ad adkey="AD_LOCATION_APP"></ad:ad>
		
		<!--End focus-->
		<div class="content_main">
			<!--left_Frame-->
			<div class="left_frame">
				<!--left_app-->
				<app:app language="en"></app:app>
				<!--left_ad-->
				<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
			</div>
		  <!--End left_Frame-->
		  <div class="right_frame">
		    <h1><img src="${resDomain}/bsen/res/images/icon02.gif" /><font class="bfont"><a href="${storeDomain}/index.html">Home</a>--></font>Product Details </h1>
		    <div class="product">
		      <!--End产品图-->
			  <div class="showPro">
		           	<div class="showProLeft">
						  <div class="jqzoom" id="spec-n1" onclick="window.open('#')">
							<img height=284 src="${product.pimage}" jqimg="${product.pimage}" width=350 />
						  </div>
		           	  <div id="spec-n5">
		              			 <div class="control" id="spec-left"><img src="${resDomain}/bsen/res/images/left.gif" /></div>
		                        <div id="spec-list">
		                         <ul>
		                           <li><img src="${product.pimage }"></li>
					          	<c:foreach items="${pImages}" var="mg" varStatus="status">
					          		<li><img src="${mg.productImage }" /></li>
					          	</c:foreach>
		                         </ul>
		                       </div>
							<div class="control" id="spec-right"><img src="${resDomain}/bsen/res/images/right.gif" /></div>
		                 </div>
		               </div>
		      <div class="product_intro">
		      <form id="buyForm" action="" method="post">
		      	<input type="hidden" name="lvShopCart.productCode" value="${product.code }"/>
		      	<input type="hidden" name="lvShopCart.storeId" value="${product.storeId }"/>
			  	<input type="hidden" name="cookieStr" id="cookieStr" value=""/>
		        <ul>
		          <li class="pm" title="${product.productName }">${product.productName }</li>
				  <!-- 商品价格 -->
				  <li id="p_orgin_price_area" p="${product.code}">
					<p>Price：<font id="p_orign_price" class="pm2">${lvStore.currency} ${oPrice}</font></p>
				  </li>
				  <li id="p_prod_ac" style="display:none;">
				  	<p class="ac">
				  		<font id="p_ac_til"></font>：<font id="p_ac_price" class="pm2"></font>
				  		<font class="bfont">(<span id="p_ac_tip"></span>:<span id="curCount" style="display:none;"></span><span id="curTime" style="display:none;"></span>）</font>
				  	</p>
				  </li>
				  <li style="display:none;">
				  	<p class="dt">Promotion：</p>
				  	<p id="p_order_ac"></p>
				  </li>
		          <li>
		            <p>Shipping Cost ： Free shipping</p>
            		<p>(excluding remote areas)</p>
		            <p style="float:none;display:block;clear:both;"><font class="blue2"><a href="http://en.bananatv.com/help28.html#M_123" target="_blank">Check Shipping Zones</a></font></p>
		          </li>
		          <div class="box">
		            <ul>
		              <li>
		                <p>Quantity:</p>
		                <a href="javascript:changeNum('del');"><img src="${resDomain}/bsen/res/images/jian.gif" border="0"/></a>
		                 <input type="text"  class="input0" id="num" name="lvShopCart.shopNum" value="1" onkeypress="onlyNumber(event)" maxlength=4/>
		                <a href="javascript:changeNum('add');"><img src="${resDomain}/bsen/res/images/jia.gif" border="0"/></a></li>
		              <li>
		                <p><input type="button" value="Buy Now" class="btn01" onclick="toSub('${product.storeId}','${product.code }','${storeDomain}');" style="CURSOR: pointer; "/></p>
		                <p><input type="button" value="Add to Cart" class="btn02" onclick="toPut('${product.code }','${product.storeId}');" style="CURSOR: pointer; "/></p>
		              </li>
		            </ul>
		          </div>
		             <div style="padding-top:10px;">Up to now we have sold:  (${page.totalCount})</div>
		             <div style="padding-top:10px;"><span>Tips：Customers shall bear any duties generated by<br /> overseas shopping.</span></div>
		            <div id="p_ladder" style="padding-top:10px;display:none;"><font class="blue2"><a href="javascript:showLadder('${product.code}');">View special offers for group buying </a></font></div>
		            <!-- JiaThis Button BEGIN -->
					<div class="jiathis_style" style="padding-top:10px;">
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_fb"></a>
						<a class="jiathis_button_twitter"></a>
						<a class="jiathis_button_google"></a>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_weixin"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<a class="jiathis_counter_style"></a>
					</div>
					<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
		         </ul>
		         </form>
		      </div>
			  </div>
			  <!--End产品簡介-->
			 <c:if test="${not empty mProducts}">
			<div class="recommend">
		        <p>Buy more and save more!</p>
				<form id="matchForm" action="" method="post">
		        <input type="hidden" name="cookieStr" id="cstr" value=""/>
		        <div class="recommend_list">
					<div style="width:580px" id="recommendDiv"><!--这里的宽度 每增加一个商品 宽度增加180px-->
						<ul>
							<li><a href="/products/${product.code }.html"><img src="${product.pimage }" /></a> </li>  
							<li><a href="/products/${product.code }.html">${product.productName } </a></li>  
							<li><input name="commendProduct" type="checkbox" value="${product.code }" checked="checked"  onclick="return false;"/>
							  Price：<font class="redfont p_price" f="${product.storeId}" p="${product.code}">${lvStore.currency} ${product.price  }</font> </li>   
						</ul>
						<c:foreach items="${mProducts}" var="p">
							<ul class="add"><img src="${resDomain}/bsen/res/images/add.gif" /></ul>
							<ul>
								<li><a href="/products/${p.code }.html"><img src="${p.pimage }" /> </a></li>  
								<li><a href="/products/${p.code }.html">${p.productName }</a> </li>  
								<li><input type="checkbox" name="commendProduct" value="${p.code }" onclick="showSelectInfo(this);"/>
								  Price：<font class="redfont p_price" f="${p.storeId}" p="${p.code}">${lvStore.currency} ${p.price  }</font> </li>   
							</ul>	
						</c:foreach>	
					</div>	
		        </div>
		        <div class="sum">
		          <ul>
		            <li><font class="redfont fontwz"><span id="selectNum">0</span></font> items added</li>
		            <li>Price：<font class="redfont fontwz">${lvStore.currency} <span id="selectPrice">${product.price  }</span></font></li>
		            <li><input type="button" onclick="toSubMatch('${product.storeId}','${product.code }','${storeDomain}');" class="btn03" value="购买组合" style="CURSOR: pointer; "/></li>
		          </ul>
		       </div>
		        </form>
		      </div>
		      </c:if>
		      
		      
		      <div class="produt_details">
		        <!-- TabbedPanels-->
		        <div class="indextj commargin1">
		          <div class="product_item">
		            <ul>
			              <c:foreach items="${propertys}" var="pro" varStatus="status">
			              		<li id="index${status.count}"><a href="javascript:MainItem(${status.count},${propertyNum+1});">${pro.title }</a></li>
			              </c:foreach>
		            	  <li id="index${propertyNum+1}" ><a href="javascript:MainItem(${propertyNum+1},${propertyNum+1});">Comments<font class="blue2">（${page.totalCount}）</font></a></li>
		            </ul>
		          </div>
		
		          	<c:foreach items="${propertys}" var="pr" varStatus="status">
			          	 	<div id="f_Pic${status.count }" <c:if test="${status.count==1}">class="tjcontent"</c:if><c:if test="${status.count!=1}">class="tjcontent${status.count }"</c:if>  style="display:none;" >${pr.content }</div>
		          	</c:foreach>
		   	  	 	<div <c:if test="${propertyNum!=0}">class="tjcontent3"</c:if><c:if test="${propertyNum==0}">class="tjcontent3"</c:if>  style="display:none;" id="f_Pic${propertyNum+1 }">
		   	  			<div class="index_comments">
			              <ul class="title" id="productComment">
			                <li class="title_s">Buyer</li>
			                <li class="title_s">Transaction Details</li>
			                <li>Comments</li>
			              </ul>
			              <c:if test="${not empty objList}">
				              <c:foreach items="${objList}" var="obj">
				              	<ul class="mytest">
					              	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/bsen${obj[0] }" /></li>
					              	<li class="short">${obj[1].pnum }<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
					              	<li>
					              		<c:foreach begin="1" end="${obj[1].score}">
					              			 <img src="${resDomain}/bsen/res/images/wjx.gif" width="30" height="29" />
					              		</c:foreach>
					              		<br />${obj[1].content }
					              	</li>
				              	</ul>
				              </c:foreach>
				              <c:if test="${page.totalCount>page.numPerPage}">
								<ul class="page">
									All<span style="color:#f00">${page.totalCount}</span>records&nbsp;
									<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">Home</a></c:if><c:if test="${page.pageNum<=1}">Home</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">Pre</a></c:if><c:if test="${page.pageNum<=1}">Pre</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">Next</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Next</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">Last Page</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Last Page</c:if></span>&nbsp;
									<span style="margin-left:10px">Page: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
									<span style="margin-left:10px"><span style="color:red">${fn:length(page.list)}</span>Per Page</span>&nbsp;
									<span style="margin-left:10px">Go to
										<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
											<c:foreach var="i" begin="1" end="${page.totalPage}">
												<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>${i}</option>
											</c:foreach>
										</select>
									</span>
								  <!--<u:page href="/web/product!toProduct.action?pcode=${product.code}&page.pageNum=@" language="cn">
								  </u:page>-->
								   <script type="text/javascript">
									<!--
									function gotoPage(num){
									   window.location.href="/web/product!toProduct.action?pcode=${product.code}&page.pageNum="+num+"&pageMark=1"+"#index${propertyNum+1}";
									
									}
									//-->
									</script>
								</ul>	
								</c:if>
			               </c:if>
			               <c:if test="${empty objList}">
			               <font color="red">No comment, welcome to make the first comment!</font>
			               </c:if>
			          	</div>
			     	 </div> 
		        </div>
		      </div>
		    </div>
			<c:if test="${not empty rProducts}">
			<div id="boxcenter02">
			   	<p>Recommended</p>
			  	<div class="news">
				  	<div class="leftbotton"  onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"><img src="${resDomain}/bsen/res/images/icon19.gif" /></div>
				  	<div class="newscon" id="ISL_Cont">
				    	<div class="max">
				      		<div id="List1">
						        <ul>
						            <c:foreach items="${rProducts }" var ="r">
						            	<li><a href="/products/${r.code }.html"><img src="${r.pimage }" /></a><br />
						            	<a href="/products/${r.code }.html">${r.productName }</a><br />
						            	<font class="redfont">Price:<font class="p_price" f="${r.storeId}" p="${r.code}" >${lvStore.currency} ${r.price  }</font></font></li>
						            </c:foreach>
				            	</ul>
				      		</div>
				      		<div id="List2"></div>
				      		<script type="text/javascript" src="${resDomain}/bsen/res/js/product_scroll.js"></script>
				    	</div>
				  	</div>
			  		<div class="rightbotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"><img src="${resDomain}/bsen/res/images/icon20.gif" /></div>
				</div>
			</div>
			</c:if>
		  </div>
		  <!--End right_Frame-->
		  <div class="cb"></div> 
		</div>
		<!--End content-->
		<!-- footer-->
		<%@include file="/web/bsen/common/footer.jsp" %>
	<script type="text/javascript">
	var PageConfig = {Product_Status_Normal: 1, Type_Activity_LimitTime: 1, Type_Activity_LimitCount: 2, Type_Activity_Ladder: 3};
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
		    	t=1;
				$("#curTime").html(tmpDay+"day(s) "+tmpHour+":"+tmpMinute+":"+tmpSecond); 
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
			var $a = $('#p_prod_ac');
			//reset
			$p.find('font').attr('class', 'pm2');
			$('#curCount').hide();
			$('#curTime').hide();
			$a.hide();
			$('#p_ladder').hide();
			$('#p_order_ac').parent().hide();
			//load data
			$.post('/web/product!getProdDetailPrice.action', {prodCode: pcode}, function(data){
				if(data.prodStatus != PageConfig.Product_Status_Normal) {
					$('.box').hide().after('<img src="http://res.bananatv.com/bsen/res/images/sold_out.jpg" style="padding:5px;"/>');
				}
				$('#p_orign_price').text(data.currency+' '+data.orignPrice);
				switch(data.activityType) {
				case PageConfig.Type_Activity_LimitTime :
					$('#p_ac_til').text(data.activityName);
					$('#p_ac_price').text(data.currency+' '+data.activityPrice);
					var restTime;
					if(data.beginTime > data.curTime) {
						restTime = data.beginTime - data.curTime;
						ProdLimitTime = parseInt(restTime/1000);
						if(ProdLimitTime < 0) break;
						$('#p_orign_price').attr('class', 'pm2');
						$('#p_ac_price').attr('class', 'pm1');
						$('#p_ac_tip').text('Beginning time');
						$a.show();
						$('#curTime').show();
					} else {
						restTime = data.endTime - data.curTime;
						ProdLimitTime = parseInt(restTime/1000);
						if(ProdLimitTime < 0) break;
						$('#p_orign_price').attr('class', 'pm1');
						$('#p_ac_price').attr('class', 'pm2');
						$('#p_ac_tip').text('Remaining time');
						$a.show();
						$('#curTime').show();
					}
					//开始倒计时
					countLimitTime();
					break;
				case PageConfig.Type_Activity_LimitCount :
					if(data.limitCount > 0) {
						$('#p_orign_price').attr('class', 'pm1');
						$('#p_ac_price').attr('class', 'pm2');
						$('#p_ac_til').text(data.activityName);
						$('#p_ac_price').text(data.currency+' '+data.activityPrice);
						$('#p_ac_tip').text('Surplus stock');
						updateProdStore();
						$a.show();
						$('#curCount').show();
					}
					break;
				case PageConfig.Type_Activity_Ladder :
					$('#p_ladder').show();
					break;
				}
				//下单赠券
				data.orderActivityTitle && $('#p_order_ac').text(data.orderActivityTitle).parent().show();
			});
		});
		$p.trigger('loadDetailActivity');
	});
	</script>
	<script type="text/javascript" src="${resDomain}/bsen/res/js/html.js"></script>
	</body>
</html> 
