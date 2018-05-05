<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<script>
	var pgo = 0;
	function JumpUrl() {
		if (pgo == 0) {
			location = '${json.forwardUrl}';
			pgo = 1;
		}
	}
	setTimeout('JumpUrl()', 1000);
</script>

<center>
	<div>
		<br />
		<br />
		<img src='images/loadinglit.gif' />&nbsp;&nbsp;&nbsp;&nbsp;${json.message }
		<br />
		<br />
		<a href='${json.forwardUrl}'>如果你的浏览器没反应，请点击这里...</a>
		<br />
		<br />
	</div>
</center>