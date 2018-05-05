<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>提交西聯匯款信息_TVpad商城</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/western.js"></script>
		<script type="text/javascript">
			//提交表单
			function onSub(){
				document.myform.submit();
			}
			</script>
		<style type="text/css">
		.xlhk_main ul li .wd3{width: 160px;float: left;text-align: right;margin-right: 10px;}
		</style>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		<div  class="xlhk_main">
			<ul>
			<form action="/web/shopCart!saveWesternInfo.action" method="post" id="myform">
				<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid }"/>
				<input type="hidden" name="lvWesternInfo.storeId" value="${lvOrder.storeId }"/>
				<!--<li><span class="wd3"><b class="bt">您已在西聯點匯款，請務必準確填寫以下信息：</b></span></li>  -->
				<li><span class="wd3"><font class="redfont">*</font>匯款人（PAYER）：</span><input type="text" class="input3" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/></li>
				<li><span class="wd3"><font class="redfont">*</font>匯款金額（AMOUNT）：</span><input type="text" class="input3" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice}"/><font class="redfont">默認金額單位為：USD </font></li>	
				<li><span class="wd3"><font class="redfont">*</font>匯款國家（Country）：</span><input type="text" class="input3" name="lvWesternInfo.contryName"/></li>	
				<li><span class="wd3"><font class="redfont">*</font>匯款城市（CITY）：</span><input type="text" class="input3" name="lvWesternInfo.adress"/></li>						
			  	<li><span class="wd3"><font class="redfont">*</font>匯款時間（DATE）：</span><input type="text" class="input3" name="lvWesternInfo.transferTime"/><font class="redfont"></font></li>
				<li><span class="wd3"><font class="redfont">*</font>MTCN：</span><input type="text" class="input3" name="lvWesternInfo.mtcn"/></li>
				<c:if test="${not empty msg}">
					<li><span class="qrtj_1"><font class="redfont">您已提交西聯匯款信息，不能重複提交！</font></span></li>
				</c:if>
				<li><span class="wd3" style="height:15px;"></span><span class="qrtj_1"><input name="" type="submit"  value="提 交"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
				<li><span class="wd3" style="height:15px;"></span>
				<p>如果您還未在西聯匯款，請點擊 <a href="/web/www/mall/western.jsp" class="xxsm" target="_blank">詳細說明>></a> 
		          <br />
		          匯款以後可前往“用戶中心—我的訂單”提交西聯匯款信息。</p>
				  <div class="cb"></div>
				</li>
			</form>
			
			</ul>
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 
