<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_物流信息</title>
</head>
<body>
	<%@include file="/web/mbmcn/user_center/c_top.jsp"%>
	<article>
		<div class="logis">
			<p>
				快递公司：${lvOrder.expressCompany }<br /> 快递单号：${lvOrder.expressNum }
			</p>
		</div>
		<div class="logis_list">
			<ul>
			<s:iterator value="dto.events">
				<li><time><s:date name="date" format="E MM'月'dd,yyyy"/>&nbsp;&nbsp;<s:date name="time" format="HH:mm"/></time> <span>${serviceEvent }&nbsp;&nbsp;${serviceArea }</span></li>
			</s:iterator>
			</ul>
		</div>

		<div class="logis_tip">
			<p>
				以上信息由物流公司提供，若无跟踪信息或有疑问，<br /> 请查询官方网站或其他公示电话。
			</p>
		</div>
	</article>

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '物流信息';
	</script>
</body>
</html>
