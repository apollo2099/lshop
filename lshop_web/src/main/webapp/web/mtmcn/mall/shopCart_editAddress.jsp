<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_编辑收货人信息</title>
</head>
<body>
	<header>
		<div class="top">
			<div class="title1">
				<h1>编辑收货人信息</h1>
			</div>
		</div>
	</header>

	<article>
		<div class="fanhui">
			<div class="fanhui_inner">
				<a href="${MallPath}/web/userCenterManage!getAddressList.action?shopFlag=${shopFlag}">返回</a>
				<div class="clear"></div>
			</div>
		</div>
	</article>

	<article>
		<div class="fanhui_inner">
			<div class="fanhu_tip">提示：非中国地区请输入英文收货信息</div>
		</div>

	</article>

	<article>
		<input type="hidden" id="defAddrChange" value="flase" />
		<form action="/web/shopCart!editAddressMobile.action" method="post"
			id="addressForm">
			<input type="hidden" name="shopFlag" value="${shopFlag }" />
			<input type="hidden" name="lvAccountAddress.code"
				value="${lvAccountAddress.code }" /> <input type="hidden"
				name="lvAccountAddress.id" value="${lvAccountAddress.id }" /> <input
				type="hidden" name="lvAccountAddress.relCode"
				value="${lvAccountAddress.relCode }" /> <input type="hidden"
				name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }" />
			<input type="hidden" name="lvAccountAddress.isDefault"
				value="${lvAccountAddress.isDefault }" /> <input type="hidden"
				name="lvAccountAddress.createTime"
				value="${lvAccountAddress.createTime }" />
			<table width="94%" border="0" align="center">
				<tr>
					<td height="40" colspan="2" class="fon18">收货人:</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="relName"
								value="${lvAccountAddress.relName }"
								name="lvAccountAddress.relName" placeholder="请输入收货人姓名" />
							<div class="tip">
								<em></em> <span>请输入收货人姓名</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">手 机：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="mobile"
								value="${lvAccountAddress.mobile }"
								name="lvAccountAddress.mobile" placeholder="请输入手机号码" />
							<div class="tip">
								<em></em> <span>请输入正确的手机号码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">固定电话：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="tel"
								value="${lvAccountAddress.tel }" name="lvAccountAddress.tel"
								placeholder="请输入电话号码" />
							<div class="tip">
								<em></em> <span>请输入正确的电话号码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="52%" height="40" class="fon18">国家：</td>
					<td width="48%" height="40" class="fon18">洲/省：</td>
				</tr>
				<tr>
					<td height="60" valign="top"><input type="hidden"
						name="lvAccountAddress.contryId" id="contryId"
						value="${lvAccountAddress.contryId }" /> <input type="hidden"
						name="lvAccountAddress.contryName" id="contrynameId"
						value="${lvAccountAddress.contryName }" />
						<div style="width: 96%; position: relative;">
							<span id="c_contry" class="spanchose" onClick="showhide(this)">${lvAccountAddress.contryName
								}</span>
							<div id="c_contry_area" class="country">
								<ul>
									<c:foreach items="${contryList}" var="c">
										<li val="${c.id}">${c.nameen}</li>
									</c:foreach>
								</ul>
							</div>
							<div class="inputd" style="position: static">
								<div id="contry_tip" class="tip">
									<em></em> <span>请选择国家</span> <i></i> <b></b>
								</div>
							</div>
						</div></td>
					<td height="60" valign="top"><input type="hidden"
						id="provinceNameId" name="lvAccountAddress.provinceId"
						value="${lvAccountAddress.provinceId}" />
						<div style="position: relative;">
							<span id="c_province" class="spanchose" onClick="showhide(this)"
								<c:if test="${empty lvAccountAddress.provinceId}">style="display:none;"</c:if>>${lvAccountAddress.provinceName
								}</span>
							<div id="c_province_area" class="country">
								<ul>
									<li>usa</li>
								</ul>
							</div>
							<div class="inputd" style="position: static;">
								<input type="text" class="inpu"
									value="${lvAccountAddress.provinceName }"
									<c:if test="${not empty lvAccountAddress.provinceId}">style="display:none;"</c:if>
									name="lvAccountAddress.provinceName" id="provinceName"
									placeholder="请输入洲/省" />
								<div id="province_tip" class="tip">
									<em></em> <span>请输入正确洲/省</span> <i></i> <b></b>
								</div>
							</div>
						</div></td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">县/市：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu"
								value="${lvAccountAddress.cityName }"
								name="lvAccountAddress.cityName" id="cityName"
								placeholder="请输入市/县" />
							<div class="tip">
								<em></em> <span>请输入正确市/县</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">街道地址：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu"
								value="${lvAccountAddress.adress }"
								name="lvAccountAddress.adress" id="adress"
								placeholder="请输入详细街道地址" />
							<div class="tip">
								<em></em> <span>请输入正确街道地址</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">邮编：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="inpu"
								value="${lvAccountAddress.postCode }"
								name="lvAccountAddress.postCode" placeholder="请输入邮编" />
							<div class="tip">
								<em></em> <span>请输入正确的密码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="md"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="保存" class="logins" />
					</td>
				</tr>
			</table>

		</form>

	</article>

	<!-- 分享 -->
	<%@include file="/web/mtmcn/common/share.jsp"%>
	<!-- footer -->
	<%@include file="/web/mtmcn/common/footer.jsp"%>

	<script type="text/javascript">
		//初始化省份
		<c:if test="${not empty lvAccountAddress.provinceId}">
		//加载省列表
		loadProvince($('#contryId').val());
		</c:if>
		//选择国家
		$("#c_contry_area ul").on('click', 'li', function() {
			var text = $(this).text();
			$("#c_contry").text(text);
			$("#c_contry_area").hide();
			$('#contryId').val($(this).attr('val'));
			$('#contrynameId').val(text);
			$('#c_contry').trigger('valiField');
			//加载省列表
			loadProvince($('#contryId').val());
			//清空选择的省
			$('#c_province').text('请选择洲/省');
			$('#provinceName').val('');
			$('#provinceNameId').val('');
		});
		//选择省份
		$("#c_province_area ul").on('click', 'li', function() {
			var text = $(this).text();
			$("#c_province").text(text);
			$("#c_province_area").hide();
			$('#provinceNameId').val($(this).attr('val'));
			$('#provinceName').val(text);
			$('#c_province').trigger('valiField');
		});
		function showhide(obj) {
			$(obj).next(".country").slideToggle();
		}
		//表单
		function checkPhone() {
			if ($('#tel').val() == "" && $('#mobile').val() == "") {
				errorTip('#tel', '电话和手机必须填写其中一个');
				errorTip('#mobile', '电话和手机必须填写其中一个');
			}
		}
		$('#relName').bind('valiField', {
			name : '收货人',
			required : true,
			min : 2,
			max : 32,
			reg : '(?!\.*[\\u4E00-\\u9FA5])^\.*$',
			txt : '格式不正确'
		}, valiField);
		$('#tel').bind('valiField', {
			name : '电话号码',
			max : 16,
			reg : '^[0-9\\-]{3,16}$',
			txt : '格式不正确',
			callback : 'checkPhone'
		}, valiField);
		$('#mobile').bind('valiField', {
			name : '手机号码',
			max : 16,
			reg : '^(((13[0-9]{1})|(15[0-9]{1}))+\\d{1,8})$',
			txt : '格式不正确',
			callback : 'checkPhone'
		}, valiField);
		$('#postcodeId').bind('valiField', {
			name : '邮编',
			required : true,
			max : 16,
			reg : '\\d+$',
			txt : '不能含有特殊字符'
		}, valiField);
		$('#provinceName').bind('valiField', {
			name : '洲/省',
			required : true
		}, valiField);
		$('#cityName').bind('valiField', {
			name : '市/县',
			required : true
		}, valiField);
		$('#adress').bind('valiField', {
			name : '街道地址',
			required : true
		}, valiField);
		$('#c_contry').bind('valiField', function(e) {
			//国家不能为空
			if ($('#contryId').val()) {
				$('#c_contry').removeClass('error');
				$('#contry_tip').hide();
			} else {
				$('#c_contry').addClass('error');
				$('#contry_tip').show();
			}
		});
		$('#c_province').bind('valiField', function(e){
			//省份不能为空
			if($('#provinceName').val()){
				$('#c_province').removeClass('error');
				$('#province_tip').hide();
			} else {
				$('#c_province').addClass('error');
				$('#province_tip').show();
			}
		});
		//提交前执行的函数
		function beforeFormSubmit() {
			$('#c_contry').trigger('valiField');
			$('#c_province:visible').trigger('valiField');
		}
	</script>
</body>
</html>
