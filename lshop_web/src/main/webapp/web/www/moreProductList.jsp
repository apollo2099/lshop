<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<title>TVpad官方正品、配件及APP應用—TVpad官方商城</title>
		         <meta name="description" content="TVpad商城提供品牌機頂盒、遙控器、充電器、組合套餐以及TV應用等系列商品。體驗中文電視精彩從TVpad官方商城開始。" />
		         <meta name="keywords" content="TVpad遙控器，套餐， APP應用，TVpad配件，HDMI线，最新优惠，优惠券，促销活动" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
	
		<div class="sc_main_ch">
		  	<div class="w_sc_main_left">
		    	<div class="w_sc_product">
				  	<h2 class="bt3">
				    	<p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p><p><a href="/index.html">首頁</a> > ${subject.subjectName }<span>（${page.totalCount}）</span> </p>
			      	</h2>
				  	<c:if test="${exhibitType==2}">
					  	<ul class="pro_zs">
					  	<ad:product products="${page.list}" currency="USD" maxDisplay="8"></ad:product>
					  	<div class="cb"></div>
					  	</ul>
				  	</c:if>
				  	<c:if test="${exhibitType==1}">
					  	<c:foreach items="${objList}" var="obj">
					  		<p class="zq_image"><a href="${obj[0].url}"><img src="${obj[0].pimageAd}" border="0" width="706px" height="220px"/></a></p>
					  	</c:foreach>
				  	</c:if>
		    	</div>
		    	
				<c:if test="${page.totalPage>1}">
				  	<u:newPage href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum=@"></u:newPage>
				  	<script type="text/javascript">
						function toPage(){
							var pageNum = $("#pageValue").val();
						   	window.location.href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum="+pageNum;
						}
				 	</script>
			 	</c:if>
			</div>
			<!--End left-->
		  
		  	<!--End right-->
		  
			<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html>