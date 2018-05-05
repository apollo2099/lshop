<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>	
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Billing Address</span></h1> 
				<div class="usercenter_box">
					<ul class="usercenter_box_add">
						<li>
						 <c:if test="${page.totalCount>=5}">
						 	<p class="cx"><img src="${resDomain}/en/res/images/user_center_add.gif" border="0" style=" FILTER: gray;"/></p>
						 </c:if>
						 <c:if test="${page.totalCount<5}">
							<p class="cx"><a href="/web/userCenterManage!toAddAddress.action"><img src="${resDomain}/en/res/images/user_center_add.gif" border="0" /></a></p> 							
						</c:if>
						<c:if test="${page.totalCount>5}">
							<p class="cx">&nbsp;The number of frequently used address you can add has reached the limit, please remove one and try again!</p>
						</c:if>
						<c:if test="${page.totalCount<=5}">
							<p class="cx">&nbsp;<span class="redfont">${page.totalCount}</span> frequently used addresses, you can add <span class="redfont">${5-page.totalCount}</span> more.</p>
						</c:if>
						
					</ul>
					
					<c:if test="${not empty page.list}">
					<c:foreach items="${page.list}" var="address" varStatus="status">
					<div class="address">
						<ul class="title">
							<li><font class="fontwz">Address <c:if test="${page.pageNum==1}">${status.count}</c:if><c:if test="${page.pageNum>1}">${(page.pageNum-1)*page.numPerPage+status.count}</c:if>：</font><c:if test="${address.isDefault==1}">(Default billing address)</c:if></li>
							<li class="btn">
								<c:if test="${address.isDefault==1}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=0&addressCode=${address.code }"><img src="${resDomain}/en/res/images/user_center_btn01.gif" border="0" /></a></c:if>
								<c:if test="${address.isDefault==0}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=1&addressCode=${address.code }"><img src="${resDomain}/en/res/images/user_center_btn001.gif" /></a></c:if>
								<a href="/web/userCenterManage!toEditAddress.action?addressCode=${address.code }"><img src="${resDomain}/en/res/images/user_center_btn02.gif" border="0" /></a>
								<a href="/web/userCenterManage!deleteAddress.action?addressCode=${address.code }"><img src="${resDomain}/en/res/images/user_center_btn03.gif" border="0" /></a>
							</li>								
						</ul>
						<ul class="xx">
							  <li>
				                <p class="shr1">Full Name：</p>
				                <p class="shr" style="word-break:break-all">${address.relName }</p>
				              </li>
							  <li>
				                <p class="shr1">Mobile No.：</p>
				                <p class="shr" style="word-break:break-all">${address.mobile }</p>
				              </li>
							 <li>
				                <p class="shr1">Phone No.：</p>
				                <p class="shr" style="word-break:break-all">${address.tel }</p>
				              </li>
							 <li>
				                <p class="shr1">Billing Addr.：</p>
				                <p class="shr" style="word-break:break-all">${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName }&nbsp;${address.contryName }</p>
				              </li>
							  <li>
				                <p class="shr1">Zip：</p>
				                <p class="shr" style="word-break:break-all">${address.postCode }</p>
				              </li>
						</ul>			
					</div>
					</c:foreach>
					</c:if>		
			
				<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/userCenterManage!getAddressList.action?page.pageNum=@" language="en"></u:newPage>
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
		<%@include file="/web/en/common/footer.jsp" %>

	</body>
</html> 