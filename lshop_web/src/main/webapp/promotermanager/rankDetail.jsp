<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/promotermanager/common/header.jsp" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@include file="top.jsp" %>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<ol>人个管理中心</ol>
		<div class="concenter_nv">
			<p><a href="rankpromt!getPromtCodeList.action" class="choose">我的推广码</a></p>
			<p><a href="rankpromt!basic.action">个人资料</a></p>
			<p><a href="rankpromt!getSettledList.action">结算查询</a></p>
			<!--<p><a href="#" class="choose">详细信息</a></p>  -->
		</div>
	</ul>
</div>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="theorder">
		<ol class="f_B">
		<c:if test="${remark==null}">
			<li>推广码<span class="f_orange"> ${couponCode}</span> 详情：</li>
			<li>拥有者姓名：<font color="blue">${realName}</font> &nbsp;&nbsp; 邮箱：<font color="blue">${email}</font> &nbsp;&nbsp; 所在地：<font color="blue">${contryname}</font></li>
		</c:if>
		</ol>
		<ul>
			<li class="my_od">
				<p class="od01">买家姓名</p>
				<p class="od02">推广码</p>
				<p class="od03">成功使用时间</p>
				<p class="od04">购买台数（单笔订单购买数量）</p>
				<p class="od05">买家所在地区</p>
				<p class="od06">是否退货</p>
			</li>
			<c:foreach items="${objList}" var="obj">
			<li>
				<p class="od01">${obj[0]}</p>
				<p class="od02">${obj[1]}</p>
				<p class="od03">${obj[2]}</p>
				<p class="od04">${obj[3]}</p>
				<p class="od05">${obj[4]}</p>
				<p class="od06">
				     <c:if test="${obj[5]==3}">已退货</c:if>
				     <c:if test="${obj[5]!=3}">否</c:if>
				</p>
			</li>
			</c:foreach>
		</ul>
	  <ul class="page" style="float:right;">
	  
	  <c:if test="${remark==null}">
	  	<u:page href="/promotermanager/rankpromt!getInfo.action?code=${code}&page.pageNum=@"></u:page>
	  	<script type="text/javascript">
			function gotoPage(num){
			   window.location.href="/promotermanager/rankpromt!getInfo.action?code=${code}&page.pageNum="+num;
		}
		</script>
	  </c:if>
	  
	  	  <c:if test="${remark!=null}">
	  	<u:page href="/promotermanager/rankpromt!getSettleInfo.action?remark=${remark}&page.pageNum=@"></u:page>
	  	<script type="text/javascript">
			function gotoPage(num){
			   window.location.href="/promotermanager/rankpromt!getSettleInfo.action?remark=${remark}&page.pageNum="+num;
		}
		</script>
	  </c:if>
	  
	  </ul>	
	</div>	

</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>

