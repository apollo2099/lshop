<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_用户中心</title>
<style type="text/css">
.conmaseg table td{word-break: break-all;}
</style>
</head>
<body>
	<%@include file="/web/mbmcn/user_center/c_top.jsp"%>
	<article>
		<div class="fanhui" style="height: 72px">
			<div class="fanhui_inner">

				<a href="javascript:void(0);"
					id="addnew">新增</a>
				<div class="clear"></div>
			</div>
		</div>
		<c:if test="${empty page.list}">
		<div class="tipaddress">
			<p>您还没有添加收货地址，请点击“新增”按钮进行添加！</p>
		</div>
		</c:if>
	</article>

	<article>
		<c:if test="${not empty page.list}">
		<c:foreach items="${page.list}" var="address" varStatus="status">
		<div class="massinfomaw">
			<div class="conmaseg">
				<table width="94%" border="0" align="center">
					<tr>
						<td width="30%" height="12" align="right"></td>
						<td height="12"></td>
					</tr>
					<tr>
						<td width="30%" height="25" align="left" valign="top">收&nbsp;货&nbsp;人：</td>
						<td width="75%" height="25" valign="top">${address.relName }</td>
					</tr>
					<c:if test="${not empty address.mobile}">
					<tr>
						<td width="30%" height="25" align="left" valign="top">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
						<td height="25" valign="top">${address.mobile }</td>
					</tr>
					</c:if>
					<c:if test="${not empty address.tel}">
					<tr>
						<td width="30%" height="25" align="left" valign="top">固定电话：</td>
						<td height="25" valign="top">${address.tel }</td>
					</tr>
					</c:if>
					<tr>
						<td width="30%" height="25" align="left" valign="top">收货地址：</td>
						<td height="25" valign="top">
							<c:if test="${address.contryId==100023}">
								${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
							</c:if>
							<c:if test="${address.contryId!=100023}">
								${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
							</c:if>
						</td>
					</tr>
					<tr>
						<td width="30%" height="25" align="left" valign="top">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
						<td height="25" valign="top">${address.postCode }</td>
					</tr>
					<tr>
						<td width="30%" height="12" align="right"></td>
						<td height="12"></td>
					</tr>
				</table>
			</div>
			<div class="maseedit">
				<a href="${MallPath}/web/userCenterManage!toEditAddress.action?addressCode=${address.code}"></a>
				<div class="tipedit">
					<i></i> <em></em>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		</c:foreach>
		</c:if>	
	</article>

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '用户中心';
		$('#addnew').click(function(e){
			if($('.massinfomaw').length>=5){
				//限止收货地址个数
				alert('常用地址大于5项,请先删除再增加!');
				return false;
			} else {
				window.location.href = "/web/userCenterManage!toAddAddress.action";
			}
		});
	</script>
</body>
</html>
