<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad海外高清中文直播電視—全球销售网点</title>
		<meta name="description" content="TVpad在全球設定的渠道銷售服務網點遍布美國、加拿大、澳大利亞、英國、法國、日本、馬來西亞、菲律賓、泰國等40多個國家，覆蓋100多個經濟發達城市。" />
		<meta name="keywords" content="全球销售网点、销售渠道、经销商" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/web/www/common/top.jsp" %>
		<!-- 加载公共JS -->
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/FomrValidate.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery.validate.min.1.8.1.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery_validate_extend.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/jquery.form.js" ></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/cookie.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/slides.jquery.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/header.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/bi.js"></script>
		<script type="text/javascript">
			$(function(){
				if(${empty searchContent}){
					$("#searchContent").val("請輸入搜索商品");
				 }
			});
		</script>
		<script type="text/javascript" src="https://rds.alipay.com/merchant/merchant.js"></script>
		<script type="text/javascript">
		$(function(){
			lshop.setCookie("jsReturnCookie",encodeURIComponent(window["alipay-merchant-result"]));
		});
		</script>
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","www_tvpadzs"); %>
		<%@include file="/web/www/common/header.jsp" %>	

		<div class="content_main">
			<div class="posit">
      			<h2 class="bt3" style="background-color:#fff">
					<img src="${resDomain}/www/res/images/icon02.gif" /><a href="/index.html"> 首頁</a> > 服務網點 </p>
	  			</h2>
   			</div>
			  <div class="Overall market">
			    <div class="mp" >
			      <!--<div class="Market_f">
						  <object  classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="960" height="512">
							<param name="movie" value="swf/map.swf" />
							<param name="quality" value="high" />				
							<param name="wmode" value="opaque" />-->
			      <!--[if !IE]>--> 
							<!--<object width="960" height="512" type="application/x-shockwave-flash" data="swf/map.swf">-->
							<!--<![endif]-->
			      <!--<param name="quality" value="high" />
							<param name="wmode" value="opaque" />
							<param name="expressinstall" value="swf/map.swf" />				
						 	 </object>
						  </object>
						</div>-->
			      <div class="Market_Map"> </div>
			    </div>
			    <div class="Market_join"> <a href="http://business.mtvpad.com/agent/agentAction!toLogin.action"><img src="${resDomain}/www/res/images/Button_Market.gif"/></a> <img src="${resDomain}/www/res/images/Market_01.gif"/> </div>
        		<table class="table01" cellpadding="0" cellspacing="0">
		          	<thead>
			            <tr>
			              <th style="text-align:center" scope="col" width="130"> 國家 </th>
			              <th style="text-align:center" scope="col" width="96"> 城市 </th>
			              <th style="text-align:center" scope="col" width="221"> 網點名稱 </th>
			              <th style="text-align:center" scope="col" width="291"> 詳細地址 </th>
			              <th style="text-align:center" scope="col" width="127"> 負責人 </th>
			            </tr>
		          	</thead>
         			<tbody>
         				<c:foreach items="${networkList }" var="network">
	         				<tr class="t Australia">
				              <td style="PADDING-LEFT: 8px"><img alt="" src="${resDomain }/${network.storeId}${network.icon}" />${network.country } </td>
				              <td> ${network.city } </td>
				              <td> ${network.channelName } </td>
				              <td class="font02"> ${network.address }</td>
				              <td style="TEXT-ALIGN: center"> ${network.responsiblePerson } </td>
				            </tr>
         				</c:foreach>
          			</tbody>
        		</table>
        		<div class="Market_border"></div>
    		</div>
		</div>
		<!--End Content_Main-->

		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>
