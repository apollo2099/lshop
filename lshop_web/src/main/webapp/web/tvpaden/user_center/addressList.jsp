<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> Billing address</span></h1> 
		<div class="usercenter_box">
			<ul class="usercenter_box_add">
				<li>
				 <c:if test="${page.totalCount>=5}">
				 	<img src="${resDomain}/tvpaden/res/images/user_center_add.gif" border="0" style=" FILTER: gray;"/>
				 </c:if>
				 <c:if test="${page.totalCount<5}">
					<a href="/web/userCenterManage!toAddAddress.action"><img src="${resDomain}/tvpaden/res/images/user_center_add.gif" border="0" /></a>							
				</c:if>
				All<font class="redfont"> ${page.totalCount} </font>frequently used billing address now, you can add other <font class="redfont"> ${5-page.totalCount} </font>billing address.</li>
			</ul>
			
			<c:if test="${not empty page.list}">
			<c:foreach items="${page.list}" var="address" varStatus="status">
			<div class="address">
				<ul class="title">
					<li><font class="fontwz">Address<c:if test="${page.pageNum==1}">${status.count}</c:if><c:if test="${page.pageNum>1}">${(page.pageNum-1)*page.numPerPage+status.count}</c:if></font><c:if test="${address.isDefault==1}">(Default billing address)</c:if></li>
					<li class="btn">
						<c:if test="${address.isDefault==1}"><p class="address_bt1"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=0&addressCode=${address.code }"><img src="${resDomain}/tvpaden/res/images/user_center_btn01.gif" border="0" /></a></p></c:if>
						<c:if test="${address.isDefault==0}"><p class="address_bt1"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=1&addressCode=${address.code }">Set default</a></p></c:if>
						<p class="address_bt1"><a href="/web/userCenterManage!toEditAddress.action?addressCode=${address.code }"><img src="${resDomain}/tvpaden/res/images/user_center_btn02.gif" border="0" /></a></p>
						<p class="address_bt1"><a href="/web/userCenterManage!deleteAddress.action?addressCode=${address.code }"><img src="${resDomain}/tvpaden/res/images/user_center_btn03.gif" border="0" /></a></p>
					</li>								
				</ul>
				<ul class="xx">
					<li>Name：${address.relName }</li>
					<li>Mobile No.：${address.mobile }</li>
					<li>Tel.：${address.tel }</li>
					<li style="word-break:break-all">Shipping Address：${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName }&nbsp;${address.contryName }</li>
					<li>Zip Code：${address.postCode }</li>
				</ul>			
			</div>
			</c:foreach>
			</c:if>		
					
			<s:if test="page.totalCount>page.numPerPage">
			<ul class="page">
			  <u:page href="/web/userCenterManage!getAddressList.action?page.pageNum=@" language="en">
			  </u:page>
			   <script type="text/javascript">
				<!--
				function gotoPage(num){
				   window.location.href="/web/userCenterManage!getAddressList.action?page.pageNum="+num;
				
				}
				//-->
				</script>
			 
			</ul>	
		</s:if>				
						
		</div>
  </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 