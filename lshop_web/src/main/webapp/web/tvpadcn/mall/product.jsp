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
		<title>${product.productName }_TVpad</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/development.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/FomrValidate.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery.validate.min.1.8.1.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery_validate_extend.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery.form.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/cookie.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/header.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/bi.js"></script>
		<script type="text/javascript">
				
			$().ready(function() {
				//修改推荐样式
				changeRecommendDiv(${mProductsLen});
				//调整切换tab样式
				changeTabCss(${pageMark},${propertyNum+1});
				
			});	
		</script>
		<script src="${resDomain}/tvpadcn/res/js/yu.js" type="text/javascript"></script>
		<script src="${resDomain}/tvpadcn/res/js/tb.js" type="text/javascript"></script>	
		<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/js.js"></script>
		<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/cookie.js"></script>
		<link rel="stylesheet" type="text/css" href="${resDomain}/tvpadcn/res/js/ymPrompt/skin/vista/ymPrompt.css" />
		<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/ymPrompt/ymPrompt.js" ></script>	
		<script src="${resDomain}/tvpadcn/res/js/zoom.lib.js" type="text/javascript">//实现小图滚动效果js</script>
		<script src="${resDomain}/tvpadcn/res/js/zoom.js" type="text/javascript">//实现放大镜效果js</script>
		<script src="${resDomain}/tvpadcn/res/js/product.js" type="text/javascript"></script>

		
	</head>
	
	<body > 
		<!-- 获取店铺头部文件 -->
		<%@include file="/web/tvpadcn/common/header.jsp" %>
		
		<!--ad-->
		<ad:ad adkey="AD_LOCATION_APP"></ad:ad>
		
		<!--End focus-->
		<div class="content_main">
			<!--left_Frame-->
			<div class="left_frame">
				<!--left_店铺信息-->
				<div class="cm_n_1">
		        	<h3>店鋪信息</h3>
					<ul class="xinxi_ul">
					  <li class="xinxi"><p class="x1">店 鋪 名：</p><p class="x2"><span><a href="#">${lvStore.name }</a></span></p></li>
					 <%--  <li class="xinxi"><p class="x1">所屬地區：</p><p class="x2"><a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }">${lvStore.country }</a>&nbsp;-&nbsp;<a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }&cityCode=${lvStore.cityCode }">${lvStore.city }</a></p></li>--%>
					  <c:if test="${not empty lvStore.serviceTel }"><li class="xinxi"><p class="x1">服務電話：</p><p class="x2">${lvStore.serviceTel }</p></li></c:if>
					  <c:if test="${not empty lvStore.address }"><li class="xinxi"><p class="x1">店鋪地址：</p><p class="x2">${lvStore.address}</p></li></c:if>
					  <c:if test="${not empty lvStore.servicePromise }"><li class="xinxi"><p class="x1">服務承諾：</p><p class="x2">${lvStore.servicePromise }</p></li></c:if>
					  <li class="xinx2"><img src="${resDomain}/tvpadcn/res/images/chengnuo.gif" /></li>
					</ul>
		         </div>
				<!--left_app-->
				<app:app></app:app>
				<!--left_ad-->
				<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
			</div>
		  <!--End left_Frame-->
		  <div class="right_frame">
		    <div class="posit"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" /><a href="http://www.mtvpad.com/index.html">首頁</a>--> <a href="http://www.mtvpad.com/online_mall.html">網上商城</a>--> <a>產品詳情</a></font></div>
		    <div class="product">
		      <!--End产品图-->
			  <div class="showPro">
		           	<div class="showProLeft">
						  <div class="jqzoom" id="spec-n1" onclick="window.open('#')">
							<img height=284 src="${product.pimage}" jqimg="${product.pimage}" width=350 />
						  </div>
		           	  <div id="spec-n5">
		              			 <div class="control" id="spec-left"><img src="${resDomain}/tvpadcn/res/images/left.gif" /></div>
		                        <div id="spec-list">
		                         <ul>
		                           <li><img src="${product.pimage }"></li>
					          	<c:foreach items="${pImages}" var="mg" varStatus="status">
					          		<li><img src="${mg.productImage }" /></li>
					          	</c:foreach>
		                         </ul>
		                       </div>
							<div class="control" id="spec-right"><img src="${resDomain}/tvpadcn/res/images/right.gif" /></div>
		                 </div>
		               </div>
		      <div class="product_intro">
		      <form id="buyForm" action="" method="post">
		      	<input type="hidden" name="lvShopCart.productCode" value="${product.code }"/>
		      	<input type="hidden" name="lvShopCart.storeId" value="${product.storeId }"/>
			  	<input type="hidden" name="cookieStr" id="cookieStr" value=""/>
		        <ul>
		          <li class="pm">${product.productName }</li>
				  <li id="p_orgin_price_area" p="${product.code}">
		            <p class="dt">價 格：</p>
		            <p id="p_orign_price" class="pm2">${lvStore.currency} ${product.price  }</p>
		            <p class="lk"><a href="http://www.mtvpad.com/help1.html#M_66" target="_blank">查看多幣種報價</a></p>
		          </li>
		          <!--限时限量活动  -->
		          <li id="p_prod_ac" style="display:none;">
				  	<p class="ac"><span id="p_ac_til"></span><span>：</span></p>
				  	<p id="p_ac_price" class="pm2"></p>
				  	<p>(<span id="p_ac_tip"></span>:<span id="curCount" style="display:none;"></span><span id="curTime" style="display:none;"></span>）</p>
				  </li>
				  <!--订单购买就送活动  -->
				  <li style="display:none;">
				  	<p class="dt">下單贈券：</p>
				  	<p id="p_order_ac"></p>
				  </li>
				  
				 <!--赠品促销 begin  -->
			     <li id="giftTitle" style="display:none;">
                      <p class="dt">促 销：</p>
                 </li>
	              <li id="gift" style="display:none;">
	             	<p class="dt">赠 品：</p>
	                <ul class="zp_list">              
	             	</ul>
	             </li>
				  <!--赠品促销 end  -->
		          <li>
		            <p class="dt">運 費：</p>
		            <p>免費包郵</p>
		            <p class="redfont">（偏遠地區例外）</p>
		            <p class="lk"><a href="http://www.mtvpad.com/help2.html#M_12" target="_blank">查看配送範圍</a></p>
		          </li>
		          <li class="tishi">服      務：由 <a href="#"><span class="bluewz">${lvStore.name}</span> </a>發貨并提供售後服務。</li>
		          <div class="box">
		            <ul>
		              <li>
		                <p class="dt">購買數量：</p>
		                <a href="javascript:changeNum('del');"><img src="${resDomain}/tvpadcn/res/images/jian.gif" border="0"/></a>
		                 <input type="text"  class="input0" id="num" name="lvShopCart.shopNum" value="1" onkeypress="onlyNumber(event)" maxlength=4/>
		                <a href="javascript:changeNum('add');"><img src="${resDomain}/tvpadcn/res/images/jia.gif" border="0"/></a></li>
		              <li>
		                <p><input type="button" value=" 立即購買" class="btn01" onclick="toSub('${product.storeId}','${product.code }','${storeDomain}');" style="CURSOR: pointer; "/></p>
		                <p><input type="button" value="加入購物車" class="btn02" onclick="toPut('${product.code }','${product.storeId}');" style="CURSOR: pointer; "/></p>
		              </li>
		            </ul>
		          </div>
		            <div><span>溫馨說明：海外購物產生的關稅由用戶承擔</span></div>
		            <div id="p_ladder" style="display:none;"><a href="javascript:showLadder('${product.code}');" class="lk">查看批量購買優惠</a></div>
		         </ul>
		         </form>
		      </div>
			  </div>
			  <!--End产品簡介-->
			 <c:if test="${not empty mProducts}">
			<div class="recommend">
		        <p>推薦組合</p>
				<form id="matchForm" action="" method="post">
		        <input type="hidden" name="cookieStr" id="cstr" value=""/>
		        <div class="recommend_list">
					<div style="width:580px" id="recommendDiv"><!--这里的宽度 每增加一个商品 宽度增加180px-->
						<ul>
							<li><a href="/products/${product.code }.html"><img src="${product.pimage }" /></a> </li>  
							<li><a href="/products/${product.code }.html">${product.productName } </a></li>  
							<li><input name="commendProduct" type="checkbox" value="${product.code }" checked="checked"  onclick="return false;"/>
							  價 格：<font class="redfont p_price" f="${product.storeId}" p="${product.code}">USD ${product.price  }</font> </li>   
						</ul>
						<c:foreach items="${mProducts}" var="p">
							<ul class="add"><img src="${resDomain}/tvpadcn/res/images/add.gif" /></ul>
							<ul>
								<li><a href="/products/${p.code }.html"><img src="${p.pimage }" /> </a></li>  
								<li><a href="/products/${p.code }.html">${p.productName }</a> </li>  
								<li><input type="checkbox" name="commendProduct" value="${p.code }" onclick="showSelectInfo(this);"/>
								  價 格：<font class="redfont p_price" f="${p.storeId}" p="${p.code}" >USD ${p.price  }</font> </li>   
							</ul>	
						</c:foreach>	
					</div>	
		        </div>
		        <div class="sum">
		          <ul>
		            <li>已選擇<font class="redfont fontwz"><span id="selectNum">0</span></font>個配件</li>
		            <li>搭配價：<font class="redfont fontwz">USD <span id="selectPrice">${product.price  }</span></font></li>
		            <li><input type="button" onclick="toSubMatch('${product.storeId}','${product.code }','${storeDomain}');" class="btn09" value="購買組合" style="CURSOR: pointer; "/></li>
		          </ul>
		       </div>
		        </form>
		      </div>
		      </c:if>
		      
		      
		      <div class="produt_details" id="produt_details">
		        <!-- TabbedPanels-->
		        <div class="indextj commargin1">
		          <div class="product_item">
		            <ul>
			              <c:foreach items="${propertys}" var="pro" varStatus="status">
			              		<li id="index${status.count}"><a href="javascript:MainItem(${status.count},${propertyNum+1});">${pro.title }</a></li>
			              </c:foreach>
		            	  <li id="index${propertyNum+1}" ><a href="javascript:MainItem(${propertyNum+1},${propertyNum+1});">用戶評價<font class="redfont">（${page.totalCount}）</font></a></li>
		            </ul>
		          </div>
		
		        <!-- 图片大图查看弹出窗口层 -->
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
			                <li class="title_s">買家</li>
			                <li class="title_s">交易詳情</li>
			                <li>評價</li>
			              </ul>
			              <c:if test="${not empty objList}">
				              <c:foreach items="${objList}" var="obj">
				              	<ul class="mytest">
					              	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpadcn${obj[0] }" /></li>
					              	<li class="short">${obj[1].pnum }臺<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
					              	<li>
					              		<c:foreach begin="1" end="${obj[1].score}">
					              			 <img src="${resDomain}/tvpadcn/res/images/wjx.gif" width="30" height="29" />
					              		</c:foreach>
					              		<br />
					              	</li>
					              	<li class="cImages">
					              	${obj[1].content }<br />
					              	<c:if test="${not empty  obj[1].commentImages}">
					              	<img src="${obj[1].commentImages }" width="200" height="150" />
					              	</c:if>
					              	</li>
				              	</ul>
				              </c:foreach>
				              <c:if test="${page.totalCount>page.numPerPage}">
								<ul class="page">
									共有<span style="color:#f00">${page.totalCount}</span>條記錄&nbsp;
									<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">首頁</a></c:if><c:if test="${page.pageNum<=1}">首頁</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">上一頁</a></c:if><c:if test="${page.pageNum<=1}">上一頁</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">下一頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">下一頁</c:if></span>&nbsp;
									<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">尾頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">尾頁</c:if></span>&nbsp;
									<span style="margin-left:10px">頁次: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
									<span style="margin-left:10px">本頁有<span style="color:red">${fn:length(page.list)}</span>條記錄</span>&nbsp;
									<span style="margin-left:10px">跳轉到
										<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
											<c:foreach var="i" begin="1" end="${page.totalPage}">
												<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>第${i}頁</option>
											</c:foreach>
										</select>
									</span>
								  <!--<u:page href="/web/product!toProduct.action?pcode=${product.code}&page.pageNum=@" language="cn">
								  </u:page>-->
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
			               <font color="red">暫無評價，歡迎您第一個留言！</font>
			               </c:if>
			          	</div>
			     	 </div> 
		        </div>
		      </div>
		    </div>
			<c:if test="${not empty rProducts}">
			<div id="boxcenter02">
			   	<p>購買套裝更優惠</p>
			  	<div class="news">
				  	<div class="leftbotton"  onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"><img src="${resDomain}/tvpadcn/res/images/icon19.gif" /></div>
				  	<div class="newscon" id="ISL_Cont">
				    	<div class="max">
				      		<div id="List1">
						        <ul>
						            <c:foreach items="${rProducts }" var ="r">
						            	<li><a href="/products/${r.code }.html"><img src="${r.pimage }" /></a><br /><a href="/products/${r.code }.html">${r.productName }</a><br />
						            	<font class="redfont">價格<font class="p_price" f="${r.storeId}" p="${r.code}" >${lvStore.currency} ${r.price  }</font></font></li>
						            </c:foreach>
				            	</ul>
				      		</div>
				      		<div id="List2"></div>
				      		<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/product_scroll.js"></script>
				    	</div>
				  	</div>
			  		<div class="rightbotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"><img src="${resDomain}/tvpadcn/res/images/icon20.gif" /></div>
				</div>
			</div>
			</c:if>
		  </div>
		  <!--End right_Frame-->
		</div>
		<!--End content-->
		<!-- footer-->
		<%@include file="/web/tvpadcn/common/footer.jsp" %>
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
				$("#curTime").html(tmpDay+"天"+tmpHour+"小時"+tmpMinute+"分"+tmpSecond+"秒"); 
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
					$('.box').hide().after('<img src="http://res.mtvpad.com/tvpadcn/res/images/sold_out.jpg" style="padding:5px;"/>');
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
						$('#p_ac_tip').text('離開始');
						$a.show();
						$('#curTime').show();
					} else {
						restTime = data.endTime - data.curTime;
						ProdLimitTime = parseInt(restTime/1000);
						if(ProdLimitTime < 0) break;
						$('#p_orign_price').attr('class', 'pm1');
						$('#p_ac_price').attr('class', 'pm2');
						$('#p_ac_tip').text('離結束');
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
						$('#p_ac_tip').text('剩餘庫存');
						updateProdStore();
						$a.show();
						$('#curCount').show();
					}
					break;
				case PageConfig.TYPE_ACTIVITY_APPOINT :
					var givenTypeNum=data.givenTypeNum;
					if(givenTypeNum==1){
						var givenTypeName=data.givenTypeName;
						var uncollectedTotal=data.uncollectedTotal;
						if(uncollectedTotal>0){
							var givenHtml="<p  class='redfont'><img src='${resDomain}/www/res/images/zq_icon.png' />"+givenTypeName+"</p>"
							$('#giftTitle').append(givenHtml);
							$('#giftTitle').show();
						}
					}else if(givenTypeNum==2){
						var givenTypeName=data.givenTypeName;
						var lotteryNum=data.lotteryNum;
						var givenHtml="<p  class='redfont'><img src='${resDomain}/www/res/images/zcj_icon.png' />"+givenTypeName+","+lotteryNum+" chances</p>"
						$('#giftTitle').append(givenHtml);
						$('#giftTitle').show();
					}else if(givenTypeNum==3){
						var s=data.giftList;
						if(data.giftList!=null&&data.giftList.length>0){
							var activityName=data.activityName;
							var givenHtml="<p  class='redfont'><img src='${resDomain}/www/res/images/zp_icon.png' />"+activityName+"</p>"
							$('#giftTitle').append(givenHtml);
							
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
								giftHtml+="<li class='zp'><img src='${resDomain}/www/res/images/zp_list.png'/>"+giftName+"</li><p class='dg'><font class='redfont'> ×"+giftEveryNum+"</font></p>"
							}
							if(giftHtml!=null){
								$('#gift .zp_list').append(giftHtml)
								$('#gift').show();
								$('#giftTitle').show();
							}
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
	<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/html.js"></script>
	</body>
</html> 
