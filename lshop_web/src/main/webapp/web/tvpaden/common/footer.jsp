<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="help" uri="/WEB-INF/tld/gv-help.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="store" uri="/WEB-INF/tld/gv-store.tld"%>
<!-- help center -->
<help:help></help:help>
<div class="bottom">
  <div class="content"> 		
  		<cus:store param="footerinfo"/>
  </div>
</div>
<!--End Bottom -->
<!-- 第三方统计代码 -->
<cus:store param="statcode"/>
<cus:store param="statcode" shopFlag="tvpaden"/>