<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>華揚商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/storeIndex.js"></script>
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
		<div class="sc_main">
		    	<div class="" style="height: 200px;">
		    		<p style="text-align: center;margin-top: 150px;"><img src='${resDomain}/www/res/images/loading.gif' style="vertical-align: middle;"/>
		    		<span style="margin-left: 5px;">加載中，請稍後。。。</span>
		    		</p>
		    	</div>

			<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
		
<script type="text/javascript">
var pgo = 0;
function JumpUrl() {
	if (pgo == 0) {
		var url = '${jumpurl }';
		if(url == ''){
			url = '/web/index!toIndex.action';
		}
		location = url;
		pgo = 1;
	}
}
var time = '${dalaytime}';
if(time == null || time == ""){
	time = 1200;
	}

setTimeout('JumpUrl()', time);
</script> 
	</body>
</html>
