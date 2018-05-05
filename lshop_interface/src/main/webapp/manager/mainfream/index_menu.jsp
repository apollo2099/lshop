<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div id="sidebar_s">
	<div class="collapse">
		<div class="toggleCollapse"><div></div></div>
	</div>
</div>
<div id="sidebar">
		<gv:menu name="menuCode" code="menuCode" parentflag="menuParent" value="menuCode" rootnode="0" rel="menuCode" labelFlag="menuName" isopen="true" href="menuUrl"></gv:menu> 	
</div>

