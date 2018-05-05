<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/web/www/common/top.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	parent.location.href='/web/www/mall/shopCart_payfail.jsp?out_trade_no=${oid}';
});
</script>

