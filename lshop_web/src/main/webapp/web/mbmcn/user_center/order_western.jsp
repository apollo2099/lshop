<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_提交西联信息</title>
</head>
<body>
	<header>
		<div class="top">
			<div class="title1">
				<h1>提交西联信息</h1>
			</div>
		</div>
	</header>

	<article>
		<form id="myform" action="${MallPath}/web/userOrder!saveWesternInfo.action" method="post">
		<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid}"/>
		<input type="hidden" name="lvWesternInfo.storeId" value="${lvOrder.storeId }"/>
			<table width="94%" border="0" align="center" style="margin-top: 20px">
				<tr>
					<td height="40" colspan="2" class="fon18">汇款人（Payer）：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="remitter" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}" maxlength="32"/>
							<div class="tip">
								<em></em> <span>字符数不能大于32位</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">汇款金额（Amount）：（默认金额单位USD）</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="remittance" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice }" maxlength="12"/>
							<div class="tip">
								<em></em> <span>请输入正确的汇款金额</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">汇款国家（Country）：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="country" name="lvWesternInfo.contryName" maxlength="45"/>
							<div class="tip">
								<em></em> <span>请输入正确的汇款国家</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">汇款城市（City）：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" id="addr" class="inpu" name="lvWesternInfo.adress" maxlength="100"/>
							<div class="tip">
								<em></em> <span>请输入正确的汇款城市</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">汇款时间（Date）：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="transferTime" name="lvWesternInfo.transferTime" maxlength="10"/>
							<div class="tip">
								<em></em> <span>格式不对</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">MTCN：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="mtcn" name="lvWesternInfo.mtcn" maxlength="20"/>
							<div class="tip">
								<em></em> <span>请输入正确的MTCN</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="100" colspan="2" valign="bottom"><input
						type="submit" value="提  交" class="logins" /></td>
				</tr>
			</table>
		</form>

	</article>

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
	$('#remitter').bind('valiField', {name : '汇款人',required:true, max: 32}, valiField);
	$('#remittance').bind('valiField', {name : '汇款金额',required:true, maxlength: 11, reg:'^[0-9]+(\.[0-9]{1,2})$', txt: '格式不正确，只能为数字'}, valiField);
	$('#country').bind('valiField', {name : '汇款国家',required:true, max: 32}, valiField);
	$('#addr').bind('valiField', {name : '汇款城市',required:true, max: 48}, valiField);
	$('#transferTime').bind('valiField', {name : '汇款时间',required:true, max: 32, reg: '^\\d{4}\/\\d{2}\/\\d{2}$', txt: '格式不正确，正确格式请參照：2012/01/01'}, valiField);
	$('#mtcn').bind('valiField', {name : 'MTCN',required:true, max: 10, reg:'\\d+', txt:'格式不正确，只能为数字'}, valiField);
	</script>
</body>
</html>
