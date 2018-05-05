<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<gv:esi uri="/web/en/common/footer.jsp">
<!-- help center -->
<help:help></help:help>
<div class="bottom">
  <div class="content"> 		
  		<cus:store param="footerinfo"/>
  </div>
</div>
<!--End Bottom -->
<!-- 返回顶部 -->
<div id="topcontrol" style="position: fixed; bottom: 50px; right: 10px; opacity: 1; cursor: pointer;" title="top">
  <a href="#sc_top"><img src="http://res.mtvpad.com/en/res/images/top1.png" border="0"></a>
</div>
<!-- 第三方统计代码 -->
<cus:store param="statcode"/>
</gv:esi>