<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 收货地址</span></h1> 
				<div class="usercenter_box">
					<ul class="usercenter_box_add">
						<li>
						 <c:if test="${page.totalCount>=5}">
						 	<img src="${resDomain}/bmcn/res/images/user_center_add.gif" border="0" style=" FILTER: gray;"/>
						 </c:if>
						 <c:if test="${page.totalCount<5}">
							<a href="/web/userCenterManage!toAddAddress.action"><img src="${resDomain}/bmcn/res/images/user_center_add.gif" border="0" /></a>							
						</c:if>
						<c:if test="${page.totalCount>5}">
							收货地址数量已达到上限，请删除不常用收货地址之后再添加！
						</c:if>
						<c:if test="${page.totalCount<=5}">
							共有<font class="redfont">${page.totalCount}</font>个常用地址，还可新增<font class="redfont">${5-page.totalCount}</font>个。</li>
						</c:if>
						
					</ul>
					
					<c:if test="${not empty page.list}">
					<c:foreach items="${page.list}" var="address" varStatus="status">
					<div class="address">
						<ul class="title">
							<li><font class="fontwz">地址<c:if test="${page.pageNum==1}">${status.count}</c:if><c:if test="${page.pageNum>1}">${(page.pageNum-1)*page.numPerPage+status.count}</c:if></font><c:if test="${address.isDefault==1}">（当前默认收货地址）</c:if></li>
							<li class="btn">
								<c:if test="${address.isDefault==1}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=0&addressCode=${address.code }"><img src="${resDomain}/bmcn/res/images/user_center_btn01.gif" border="0" /></a></c:if>
								<c:if test="${address.isDefault==0}"><a href="/web/userCenterManage!setDefaultAddress.action?isDefault=1&addressCode=${address.code }"><img src="${resDomain}/bmcn/res/images/user_center_btn001.gif" /></a></c:if>
								<a href="/web/userCenterManage!toEditAddress.action?addressCode=${address.code }"><img src="${resDomain}/bmcn/res/images/user_center_btn02.gif" border="0" /></a>
								<a href="/web/userCenterManage!deleteAddress.action?addressCode=${address.code }"><img src="${resDomain}/bmcn/res/images/user_center_btn03.gif" border="0" /></a>
							</li>								
						</ul>
						<ul class="xx">
							<li>收货人姓名：${address.relName }</li>
							<li>手机号码：${address.mobile }</li>
							<li>固定电话：${address.tel }</li>
							<li style="word-break:break-all">收货地址：
							<c:if test="${address.contryId==100023}">
								${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
							</c:if>
							<c:if test="${address.contryId!=100023}">
								${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
							</c:if>
							</li>
							<li>邮政区号：${address.postCode }</li>
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
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 