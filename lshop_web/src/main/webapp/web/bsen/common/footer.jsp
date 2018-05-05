<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>

<!-- service ad -->
<ad:ad adkey="AD_BANANA_SERVICE"></ad:ad>

<!-- help center -->
<help:help style="3"></help:help>

<div class="bottom"> 		
  <cus:store param="footerinfo"/>
</div>
<!--End Bottom -->
<!-- 第三方统计代码 -->
<cus:store param="statcode"/>
<cus:store param="statcode" shopFlag="bsen"/>
<div id="goTopBtn"><a href="#sc_top"><img border=0 src="${resDomain}/bsen/res/images/lanren_top.jpg" /></a></div>
