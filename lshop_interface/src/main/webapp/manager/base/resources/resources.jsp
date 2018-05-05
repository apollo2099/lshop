<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<div class="pageContent" style="padding:5px">
	<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
	    <gv:tree name="menuCode" code="menuCode" data="${MENU_LIST}" parentflag="menuParent" rootnode="0" type="1" value="menuCode" isopen="true" href="baseResources!list.action" labelFlag="menuName" treeclass="menuclass" target="ajax" rel="jbsxBox" ></gv:tree>
	</div>
	
	<div id="jbsxBox" class="unitBox" style="margin-left:246px;" layoutH="10">
		<!--#include virtual="list1.html" -->
	</div>
</div>

