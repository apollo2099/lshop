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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${product.productName }- TVpad MALL</title>
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/en/res/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/development.css" rel="stylesheet" type="text/css" />
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript">
			$().ready(function() {
				//修改推荐样式
				changeRecommendDiv(${mProductsLen});
				//调整切换tab样式
				changeTabCss(${pageMark},${propertyNum+1});
				
			});	
		</script>
		<script src="${resDomain}/tvpaden/res/js/yu.js" type="text/javascript"></script>
		<script src="${resDomain}/tvpaden/res/js/tb.js" type="text/javascript"></script>	
		<script type="text/javascript" src="${resDomain}/tvpaden/res/js/js.js"></script>
		<script type="text/javascript" src="${resDomain}/tvpaden/res/js/cookie.js"></script>
		<script src="${resDomain}/tvpaden/res/js/zoom.lib.js" type="text/javascript">//实现小图滚动效果js</script>
		<script src="${resDomain}/tvpaden/res/js/zoom.js" type="text/javascript">//实现放大镜效果js</script>	
		<script src="${resDomain}/tvpaden/res/js/product.js" type="text/javascript"></script>
		
		
			

		
	</head>
		
	<body> 
		<!-- 获取店铺头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		
		<!--ad-->
		<ad:ad adkey="AD_LOCATION_APP"></ad:ad>
		
		<!--End focus-->
		<div class="content_main">
			<!--left_Frame-->
			<div class="left_frame">
				<!--left_店铺信息-->
				<div class="cm_n_1">
		        	<h3>Store Info</h3>
					<ul class="xinxi_ul">
					  <li class="xinxi"><p class="x1">Name：</p><p class="x2"><span><a href="#">${lvStore.name }</a></span></p></li>
					  <%-- <li class="xinxi"><p class="x1">District：</p><p class="x2"><a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }">${lvStore.country }</a>&nbsp;-&nbsp;<a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }&cityCode=${lvStore.cityCode }">${lvStore.city }</a></p></li>--%>
					  <c:if test="${not empty lvStore.serviceTel }"><li class="xinxi"><p class="x1">Tel.：</p><p class="x2">${lvStore.serviceTel }</p></li></c:if>
					  <c:if test="${not empty lvStore.address }"><li class="xinxi"><p class="x1">Address：</p><p class="x2">${lvStore.address}</p></li></c:if>
					  <c:if test="${not empty lvStore.servicePromise }"><li class="xinxi"><p class="x1">Commitment：</p><p class="x2">${lvStore.servicePromise }</p></li></c:if>
					  <li class="xinx2"><img src="${resDomain}/tvpaden/res/images/chengnuo.gif" /></li>
					</ul>
		         </div>
				<!--left_app-->
				<app:app language="en"></app:app>
				<!--left_ad-->
				<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
			</div>
		  <!--End left_Frame-->
		  <div class="right_frame">
		    <h1><img src="${resDomain}/tvpaden/res/images/icon02.gif" /><font class="bfont"><a href="http://en.mtvpad.com/index.html">Home</a>--></font>Product Details </h1>
		    <div class="product">
		      <!--End产品图-->
			  <div class="showPro">
		           	<div class="showProLeft">
						  <div class="jqzoom" id="spec-n1" onclick="window.open('#')">
							<img height=284 src="${product.pimage}" jqimg="${product.pimage}" width=350 />
						  </div>
		           	  <div id="spec-n5">
		              			 <div class="control" id="spec-left"><img src="${resDomain}/tvpaden/res/images/left.gif" /></div>
		                        <div id="spec-list">
		                         <ul>
		                           <li><img src="${product.pimage }"></li>
					          	<c:foreach items="${pImages}" var="mg" varStatus="status">
					          		<li><img src="${mg.productImage }" /></li>
					          	</c:foreach>
		                         </ul>
		                       </div>
							<div class="control" id="spec-right"><img src="${resDomain}/tvpaden/res/images/right.gif" /></div>
		                 </div>
		               </div>
		      <div class="product_intro">
		      <form id="buyForm" action="${storeDomain}/web/mall!saveShopCart.action" onsubmit="return toSub('${product.storeId}','${product.code }','${storeDomain}');" method="post">
		      	<input type="hidden" name="shopFlag" value="${product.storeId}"/>
		      	<input type="hidden" name="lvShopCart.productCode" value="${product.code }"/>
			  	<input type="hidden" name="cookieStr" id="cookieStr" value=""/>
		        <ul>
		          <li class="pm">${product.productName }</li>
		          <li id="p_orgin_price_area" p="${product.code}">
		            <p class="dt">Price：</p>
		            <p id="p_orign_price" class="pm2">${lvStore.currency} ${product.price  }</p>
		            <p class="lk"><a href="http://en.mtvpad.com/help20.html#M_81" target="_blank">View Reference Currency</a></p>
		          </li>
		           <!--限时限量活动  -->
		          <li id="p_prod_ac" style="display:none;">
				  	<p class="ac"><span id="p_ac_til"></span><span>：</span></p>
				  	<p id="p_ac_price" class="pm2"></p>
				  	<p class="bfont">(<span id="p_ac_tip"></span>:<span id="curCount" style="display:none;"></span><span id="curTime" style="display:none;"></span>）</p>
				  </li>
				  
				  <!--订单购买就送活动  -->
				  <li style="display:none;">
				  	<p class="dt">Promotion ：</p>
				  	<p id="p_order_ac"></p>
				  </li>
				  
				 <!--赠品促销 begin  -->
			     <li id="giftTitle" style="display:none;">
                      <p class="dt">Promotion：</p>
                 </li>
                <li id="gift" style="display:none;">
             	<p class="dt">Gift ：</p>
                <ul class="zp_list"> 
         
             	</ul>
                </li>
				  <!--赠品促销 end  -->
				  
		          <li>
		            <p class="dt">Shipping Cost：</p>
		            <p>Free shipping</p>
		            <p class="redfont">（excluding remote areas）</p>
		            <p class="lk"><a href="http://en.mtvpad.com/help19.html#M_78" target="_blank">Check Shipping Zones</a></p>
		          </li>
		          <li class="tishi">Service：<a href="javascript:void()"><span class="bluewz">${lvStore.name}</span> </a> will ship the goods and offer after service to you.</li>
		          <div class="box">
		            <ul>
		              <li>
		                <p class="dt">Quantity：</p>
		                <a href="javascript:changeNum('del');"><img src="${resDomain}/tvpaden/res/images/jian.gif" border="0"/></a>
		                 <input type="text"  class="input0" id="num" name="lvShopCart.shopNum" value="1" onblur="onlyNumber(this)" maxlength=4/>
		                <a href="javascript:changeNum('add');"><img src="${resDomain}/tvpaden/res/images/jia.gif" border="0"/></a></li>
		              <li>
		                <p><input type="submit" class="btn01" style="CURSOR: pointer; "/></p>
		                <p><input type="button" class="btn02" onclick="toPut('${product.code }','${product.storeId}');" style="CURSOR: pointer; "/></p>
		              </li>
		            </ul>
		          </div>
		            <div><span>Tips：Customers shall bear any duties generated by<br /> overseas shopping.</span></div>
		            <div id="p_ladder" style="display:none;"><a href="javascript:showLadder('${product.code}');" class="lk">View special offers for group buying </a></div>
		         </ul>
		         </form>
		      </div>
			  </div>
			  <!--End产品簡介-->
			 <c:if test="${not empty mProducts}">
			<div class="recommend">
		        <p>Recommended</p>
				<form id="matchForm" action="${storeDomain}/web/mall!saveMatchShop.action" onsubmit="return toSubMatch('${product.storeId}','${product.code }','${storeDomain}');" method="post">
		        <input type="hidden" name="cookieStr" id="cstr" value=""/>
		        <input type="hidden" name="shopFlag" value="${product.storeId}"/>
		        <div class="recommend_list">
					<div style="width:580px" id="recommendDiv"><!--这里的宽度 每增加一个商品 宽度增加180px-->
						<ul>
							<li><a href="/products/${product.code }.html"><img src="${product.pimage }" /></a> </li>  
							<li><a href="/products/${product.code }.html">${product.productName } </a></li>  
							<li><input name="commendProduct" type="checkbox" value="${product.code }" checked="checked"  onclick="return false;"/>
							 Price：<font class="redfont p_price" f="${product.storeId}" p="${product.code}">USD ${product.price  }</font> </li>   
						</ul>
						<c:foreach items="${mProducts}" var="p">
							<ul class="add"><img src="${resDomain}/tvpaden/res/images/add.gif" /></ul>
							<ul>
								<li><a href="/products/${p.code }.html"><img src="${p.pimage }" /> </a></li>  
								<li><a href="/products/${p.code }.html">${p.productName }</a> </li>  
								<li><input type="checkbox" name="commendProduct" value="${p.code }" onclick="showSelectInfo(this);"/>
								  Price：<font class="redfont p_price" f="${p.storeId}" p="${p.code}">USD ${p.price  }</font> </li>   
							</ul>	
						</c:foreach>	
					</div>	
		        </div>
		        <div class="sum">
		          <ul>
		            <li>Added<font class="redfont fontwz">&nbsp;<span id="selectNum"> 0 </span>&nbsp;</font>items</li>
		            <li>Combo price：<font class="redfont fontwz">USD <span id="selectPrice">${product.price  }</span></font></li>
		            <li><input type="submit" class="btn09"  style="CURSOR: pointer; " value="Buy Now"/></li>
		          </ul>
		       </div>
		        </form>
		      </div>
		      </c:if>
		      
		      
		      <div id="produt_details" class="produt_details">
		        <!-- TabbedPanels-->
		        <div class="indextj commargin1">
		          <div class="product_item">
		            <ul>
			              <c:foreach items="${propertys}" var="pro" varStatus="status">
			              		<li id="index${status.count}"><a href="javascript:MainItem(${status.count},${propertyNum+1});">${pro.title }</a></li>
			              </c:foreach>
		            	  <li id="index${propertyNum+1}" ><a href="javascript:MainItem(${propertyNum+1},${propertyNum+1});">Comments<font class="redfont">（${page.totalCount}）</font></a></li>
		            </ul>
		          </div>
				   <!--大效果图弹出层  -->
						<div class="popUp" id="J-popUp">
							<div class="popUpBox" id="J-popUpBox">
								<img src="" class="popUpImg" id="popUpImg" />
							</div>
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
					              	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpaden${obj[0] }" /></li>
					              	<li class="short">${obj[1].pnum }<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
					              	<li>
					              		<c:foreach begin="1" end="${obj[1].score}">
					              			 <img src="${resDomain}/tvpaden/res/images/wjx.gif" width="30" height="29" />
					              		</c:foreach>
					              		<br />
					              	</li>
					              	
							        <li class="cImages">
							        ${obj[1].content }<br/>
								    <c:if test="${not empty  obj[1].commentImages}">
								    <img  src="${obj[1].commentImages }" width="200" height="150" />
								    </c:if>
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
								</ul>
								<script type="text/javascript">
								<!--
								function gotoPage(num){
									//加载评论显示
									$.post("/web/product!toProductComments.action?pcode=${product.code}&page.pageNum="+num+"&pageMark=1"+"#index${propertyNum+1}", function(data){
										$('.index_comments').empty().append(data);
										location.href = location.href.split('#')[0] + '#produt_details';
									}, 'html');
								}
								//-->
								</script>
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
				  	<div class="leftbotton"  onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"><img src="${resDomain}/tvpaden/res/images/icon19.gif" /></div>
				  	<div class="newscon" id="ISL_Cont">
				    	<div class="max">
				      		<div id="List1">
						        <ul>
						            <c:foreach items="${rProducts }" var ="r">
						            	<li><a href="/products/${r.code }.html"><img src="${r.pimage }" /></a><br /><a href="/products/${r.code }.html">${r.productName }</a><br /><font class="redfont">Price：USD ${r.price  }</font></li>
						            </c:foreach>
				            	</ul>
				      		</div>
				      		<div id="List2"></div>
				      		<script type="text/javascript" src="${resDomain}/tvpaden/res/js/product_scroll.js"></script>
				    	</div>
				  	</div>
			  		<div class="rightbotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"><img src="${resDomain}/tvpaden/res/images/icon20.gif" /></div>
				</div>
			</div>
			</c:if>
		  </div>
		  <!--End right_Frame-->
		</div>
		<!--End content-->
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
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
			$('#p_orign_price').attr('class', 'pm2');
			$('#curCount').hide();
			$('#curTime').hide();
			$a.hide();
			$('#p_ladder').hide();
			$('#p_order_ac').parent().hide();
			//load data
			$.post('/web/product!getProdDetailPrice.action', {prodCode: pcode}, function(data){
				if(data.prodStatus != PageConfig.Product_Status_Normal) {
					$('.box').hide().after('<img src="http://res.mtvpad.com/tvpaden/res/images/sold_out.jpg" style="padding:5px;"/>');
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
				case PageConfig.TYPE_ACTIVITY_APPOINT :
					var givenTypeNum=data.givenTypeNum;
					if(givenTypeNum==1){
						var givenTypeName=data.givenTypeName;
						var uncollectedTotal=data.uncollectedTotal;
						if(uncollectedTotal>0){
						   var givenHtml="<p  class='redfont'><img src='${resDomain}/en/res/images/zq.jpg' />"+givenTypeName+"</p>"
						   $('#giftTitle').append(givenHtml);
						   $('#giftTitle').show();
						}
					}else if(givenTypeNum==2){
						var givenTypeName=data.givenTypeName;
						var lotteryNum=data.lotteryNum;
						var givenHtml="<p  class='redfont'><img src='${resDomain}/en/res/images/cj.jpg' />"+givenTypeName+", Lucky draw"+lotteryNum+" chances</p>"
						$('#giftTitle').append(givenHtml);
						$('#giftTitle').show();
					}else if(givenTypeNum==3){
						var activityNameEn=data.activityNameEn;
						var givenHtml="<p  class='redfont'><img src='${resDomain}/en/res/images/zp_icon.png' />"+activityNameEn+"</p>"
						$('#giftTitle').append(givenHtml);
						var giftHtml="";
						var len=0;
						if(data.giftList.length>6){
							len=6;
						}else{
							len=data.giftList.length;
						}
						for(var i=0;i<len;i++){
							var giftNameEn=data.giftList[i].giftNameEn;
							var giftEveryNum=data.giftList[i].giftEveryNum;
							giftHtml+="<li class='zp'><img src='${resDomain}/www/res/images/zp_list.png'/>"+giftNameEn+"</li><p class='dg'><font class='redfont'> ×"+giftEveryNum+"</font></p>"
						}
						if(giftHtml!=null&&giftHtml.length>0){
							$('#gift .zp_list').append(giftHtml)
							$('#gift').show();
							$('#giftTitle').show();
						}
					}
					
					break;	
				}				
				
				//下单赠券
				data.orderActivityTitle && $('#p_order_ac').text(data.orderActivityTitle).parent().show();
			});
		});
		$p.trigger('loadDetailActivity');
	});
	</script>
	<script type="text/javascript" src="${resDomain}/tvpaden/res/js/html.js"></script>
	</body>
</html> 
