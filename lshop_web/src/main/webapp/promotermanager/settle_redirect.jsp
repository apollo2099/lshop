<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/excenter/common/header.jsp" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@include file="/excenter/common/top.jsp" %>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<li><img src="images/banner02_01.gif" /><a href="diffusionPB.html"><img src="images/banner02_02.gif" /></a></li>		
	</ul>
</div>

<!--主要内容-->
<div class="main_conten2">
	<div class="diffusion_title"><ul><li>申请结算</li></ul></div>	
	<div class="logon_ts">
		<form>
		<p>您的申请已经提交，我们将尽快处理!</p>
		</form>
		<form action="rankpromt!getPromtCodeList.action">
		  <p>
		     <input name="" type="submit" value="返回"  class="button_01" />
		  </p>
		</form>
	</div>
</div>
<!--版权-->
<%@include file="/promotermanager/common/footer.jsp" %>

