<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>收貨地址</a></font> </span></h1> 
				<div class="usercenter_box">
					<ul class="usercenter_box_add">
						<li>
						 <c:if test="${page.totalCount>=5}">
						 	<img src="${resDomain}/www/res/images/user_center_add.gif" border="0" style=" FILTER: gray;"/>
						 </c:if>
						 <c:if test="${page.totalCount<5}">
							<a href="/web/userCenterManage!toAddAddress.action" style="float:left;"><img src="${resDomain}/www/res/images/user_center_add.gif" border="0" /></a>							
						</c:if>
						<c:if test="${page.totalCount>5}">
							收貨地址數量已達到上限，請刪除不常用收貨地址之後再添加！
						</c:if>
						<c:if test="${page.totalCount<=5}">
							&nbsp;共有<font class="redfont">${page.totalCount}</font>個常用地址，還可新增<font class="redfont">${5-page.totalCount}</font>個。</li>
						</c:if>
						
					</ul>
					
					<c:if test="${not empty page.list}">
					<c:foreach items="${page.list}" var="address" varStatus="status">
					<div class="address">
						<ul class="title">
							<li><font class="fontwz">地址<c:if test="${page.pageNum==1}">${status.count}</c:if><c:if test="${page.pageNum>1}">${(page.pageNum-1)*page.numPerPage+status.count}</c:if></font><c:if test="${address.isDefault==1}">（當前默認收貨地址）</c:if></li>
							<li class="btn">
								<c:if test="${address.isDefault==1}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=0&addressCode=${address.code }"><img src="${resDomain}/www/res/images/user_center_btn01.gif" border="0" /></a></c:if>
								<c:if test="${address.isDefault==0}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=1&addressCode=${address.code }"><img src="${resDomain}/www/res/images/user_center_btn001.gif" /></a></c:if>
								<a href="/web/userCenterManage!toEditAddress.action?addressCode=${address.code }"><img src="${resDomain}/www/res/images/user_center_btn02.gif" border="0" /></a>
								<a href="/web/userCenterManage!deleteAddress.action?addressCode=${address.code }"><img src="${resDomain}/www/res/images/user_center_btn03.gif" border="0" /></a>
							</li>								
						</ul>
						<ul class="xx">
							<li>收貨人姓名：${address.relName }</li>
							<li>手機號碼：${address.mobile }</li>
							<li>固定電話：${address.tel }</li>
							<li style="word-break:break-all">收貨地址：${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName }&nbsp;${address.contryName }</li>
							<li>郵政區號：${address.postCode }</li>
						</ul>			
					</div>
					</c:foreach>
					</c:if>		
			
				<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/userCenterManage!getAddressList.action?page.pageNum=@"></u:newPage>
				  <script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/userCenterManage!getAddressList.action?page.pageNum="+pageNum;
					
					}
				 </script>
			 	</c:if>		
								
				</div>
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 