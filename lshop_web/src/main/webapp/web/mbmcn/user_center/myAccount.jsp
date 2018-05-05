<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>banana商城_用户中心</title>
</head>
<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>
<article>
   <section>
      <div class="tucubg">
           
          <div class="tucubg_wel"><c:out value="${lvUserSubscribe.userName }" default="${lvUserSubscribe.email }" /><br/>欢迎访问banana商城！</div>
          <div class="tucubg_bt"><a href="${MallPath}/web/userCenterManage!logout.action"><img src="${MallPath}${resDomain}/mbmcn/res/images/rebt.gif" /></a></div>
      </div>
   </section>
  
</article>

<article>
  <div class="articbox">
   <h2>订单中心</h2>
   <div class="listyle">
      <ul>
         <li><a href="${MallPath}/web/userOrder!getOrderlist.action" id="order">我的订单</a></li>
         <li><a href="${MallPath}/web/mall!getShopCartList.action" id="sho">我的购物车</a></li>
         <li><a href="${MallPath}/web/userCenterManage!getCommentList.action" id="comend">我的评论</a></li>
      </ul>
   </div>
 </div>
</article>

<article>
  <div class="articbox">
   <h2>账户中心</h2>
   <div class="listyle">
      <ul>
         <li><a href="${MallPath}/web/userCenterManage!getInfoDetail.action" id="acont">账户信息</a></li>
         <li><a href="${MallPath}/web/userCenterManage!toUpdatePassword.action" id="aq">账户安全</a></li>
         <li><a href="${MallPath}/web/recharge!list.action" id="Account_balance">帐户余额</a></li>
         <li><a href="${MallPath}/web/couponManage!getCouponList.action?useStatus=1" id="couponm">我的优惠券</a></li>
         <li><a href="${MallPath}/web/recharge!tradeList.action?recordType=1" id="Trans_records">交易记录</a></li>
         <li><a href="${MallPath}/web/userCenterManage!getAddressList.action" id="adress">收货地址</a></li>
      </ul>
   </div>
 </div>
</article>

<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '用户中心';
</script>
</body>
</html>
