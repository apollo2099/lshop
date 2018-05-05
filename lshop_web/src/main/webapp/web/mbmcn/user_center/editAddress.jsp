<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/area_cn.js"></script>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_编辑收货地址</title>
</head>
<body>
	<%@include file="/web/mbmcn/user_center/c_top.jsp"%>
	<article>
		<div class="fanhui" style="height:72px;">
			<div class="fanhui_inner">
				<a href="${MallPath}/web/userCenterManage!getAddressList.action">返回</a>
				<a href="javascript:void(0)" id="addnew1">删除</a>
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
		<form action="/web/userCenterManage!editAddress.action" method="post" id="addressForm">
		<input type="hidden" name="lvAccountAddress.code" value="${lvAccountAddress.code }"/>
		<input type="hidden" name="lvAccountAddress.id" value="${lvAccountAddress.id }"/>
		<input type="hidden" name="lvAccountAddress.relCode" value="${lvAccountAddress.relCode }"/>
		<input type="hidden" name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }"/>
		<input type="hidden" name="lvAccountAddress.isDefault" value="${lvAccountAddress.isDefault }"/>
		<input type="hidden" name="lvAccountAddress.createTime" value="${lvAccountAddress.createTime }"/>
			<table width="94%" border="0" align="center">
				<tr>
					<td height="40" colspan="2" class="fon18">收货人:</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd">
							<input type="text" class="inpu" id="relName" value="${lvAccountAddress.relName }"
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
							<input type="text" class="inpu" id="mobile" value="${lvAccountAddress.mobile }"
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
							<input type="text" class="inpu" id="tel" value="${lvAccountAddress.tel }"
								name="lvAccountAddress.tel" placeholder="请输入电话号码" />
							<div class="tip">
								<em></em> <span>请输入正确的电话号码</span> <i></i> <b></b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="52%" height="40" class="fon18">国家：</td>
					<td width="48%" height="40" class="fon18">州/省：</td>
				</tr>
				<tr>
					<td height="60" valign="top">
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value="${lvAccountAddress.contryName }"/>
						<div class="boxsele">
							<select id="c_contry" class="spanchose1" name="lvAccountAddress.contryId">
								<option value="">请选择国家</option>
								<option value="100023" <c:if test="${lvAccountAddress.contryId==100023}">selected</c:if>>中国</option>
								<c:foreach items="${contryList}" var="c">
									<c:if test="${c.id!=100023}">
										<option value="${c.id}"  <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
									</c:if>
								</c:foreach>
							</select>
							<div id="contry_tip" class="tip">
								<em></em>
								<span>请选择国家</span>
								<i></i>
								<b></b>
							</div>
						</div>
					</td>
					<td height="60" valign="top">
						<input type="hidden" id="provinceNameId" name="lvAccountAddress.provinceId" value="${lvAccountAddress.provinceId}" />
						<div class="boxsele" style="width:100%">
						<select id="c_province" class="spanchose1" <c:if test="${empty lvAccountAddress.provinceId}">style="display:none;"</c:if> >
						</select>
						<input type="text" class="inpu" value="${lvAccountAddress.provinceName }"
						<c:if test="${not empty lvAccountAddress.provinceId}">style="display:none;"</c:if> 
							name="lvAccountAddress.provinceName" id="provinceName" placeholder="请输入州/省" />
						<div id="province_tip" class="tip">
							<em></em> <span>请输入正确州/省</span> <i></i> <b></b>
						</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">县/市：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<input type="hidden" id="cityNameId" name="lvAccountAddress.cityId" value="${lvAccountAddress.cityId}" />
						<div class="boxsele" style="width:100%">
						<select id="c_city" class="spanchose1" <c:if test="${empty lvAccountAddress.cityId}">style="display:none;"</c:if>></select>
						<input type="text" class="inpu" value="${lvAccountAddress.cityName }"
						<c:if test="${not empty lvAccountAddress.cityId}">style="display:none;"</c:if>
							name="lvAccountAddress.cityName" id="cityName" placeholder="请输入市/县" />
						<div id="city_tip" class="tip">
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
							<input type="text" class="inpu" value="${lvAccountAddress.adress }"
								name="lvAccountAddress.adress" id="adress" placeholder="请输入详细街道地址" />
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
							<input type="text" class="inpu" id="postcodeId" value="${lvAccountAddress.postCode }"
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

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<!-- 对话框 -->
	<div id="c_del_area" class="mark_tip">
	   <div class="mark_tip_titile"><h2>确认删除？</h2></div>
	   <div class="tipdetail"></div>
	   <div class="bt_marktip">
	     <a href="javascript:void(0);" id="c_del_btn">确定</a>
	     <a href="javascript:void(0);" class="c_cancle_btn">取消</a>
	   </div>
	</div>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '编辑地址';
		//隐藏快捷键
		$('.fastchannel').hide();
		$(function(){
			//初始化省份
			<c:if test="${not empty lvAccountAddress.provinceId}">
			var countryId = '${lvAccountAddress.contryId}';
			var provinceId = '${lvAccountAddress.provinceId}', provinceName = '${lvAccountAddress.provinceName}';
			var cityId = '${lvAccountAddress.cityId}', cityName = '${lvAccountAddress.cityName}';
			$('#c_contry').val(countryId).trigger('change');
			//省
			var $pv = $('#c_province');
			if($pv.is(':visible')){
				$pv.val(provinceId).trigger('change');
				$('#provinceName').val(provinceName);
			} else {
				$('#provinceName').val(provinceName);
			}
			//市
			var $ct = $('#c_city');
			if($ct.is(':visible')){
				$ct.val(cityId).trigger('change');
				$('#cityName').val(cityName);
			} else {
				$('#cityName').val(cityName);
			}
			</c:if>
		});
		//选择国家
		$('#c_contry').change(function(e){
			var $c = $(this);
			$c.trigger('valiField');
			var text = $('#c_contry option:selected').text();
			$('#contrynameId').val(text);
			//清空选择的省
			$('#provinceName').val('');
			$('#provinceNameId').val('');
			$('#c_province').removeClass('error');
			$('#province_tip').hide();
			//清空选择的市
			$('#cityName').val('');
			$('#cityNameId').val('');
			$('#c_city').removeClass('error');
			$('#city_tip').hide();
			//加载省列表
			loadProvince($c.val());
		});
		//选择省份
		$('#c_province').change(function(e){
			var val = $(this).val();
			$('#provinceNameId').val(val);
			var text = $('#c_province option:selected').text();
			$('#provinceName').val(text);
			$('#c_province').trigger('valiField');
			//清空选择的市
			$('#cityName').val('');
			$('#cityNameId').val('');
			$('#c_city').removeClass('error');
			$('#city_tip').hide();
			//加载省列表
			loadCity(val);
		});
		//选择市
		$('#c_city').change(function(e){
			var val = $(this).val();
			$('#cityNameId').val(val);
			var text = $('#c_city option:selected').text();
			$('#cityName').val(text);
			$('#c_city').trigger('valiField');
		});
		//对话框
		$('.c_cancle_btn').click(function(e){
			uncenter($('.mark_tip'));
		});
		//删除收货地址
		$('#addnew1').on('tap', function(e){
			center($('#c_del_area'));
		}).on('click', function(e){
			center($('#c_del_area'));
		});
		$('#c_del_btn').click(function(e){
			window.location.href = '${MallPath}/web/userCenterManage!deleteAddress.action?addressCode=${lvAccountAddress.code }';
		});
		//表单
		function checkPhone(){
			if($('#tel').val() == "" && $('#mobile').val() == ""){
				errorTip('#tel', '电话和手机必须填写其中一个');
				errorTip('#mobile', '电话和手机必须填写其中一个');
			}
		}
		$('#relName').bind('valiField', {name : '收货人',required:true,min:2,max:32}, valiField);
		$('#tel').bind('valiField', {name : '电话号码', max : 16, reg : '^[0-9\\-]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#mobile').bind('valiField', {name : '手机号码', max : 16, reg : '^[0-9]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#postcodeId').bind('valiField', {name : '邮编', required:true, max : 16, reg : '(?!\.*[\\u4E00-\\u9FA5])^\.*$',
			txt: '不能含有特殊字符'}, valiField);
		$('#provinceName').bind('valiField', {name : '州/省',required:true, max: 32}, valiField);
		$('#cityName').bind('valiField', {name : '市/县',required:true, max: 32}, valiField);
		$('#adress').bind('valiField', {name : '街道地址',required:true, max: 128}, valiField);
		$('#c_contry').bind('valiField', function(e){
			//国家不能为空
			if($('#c_contry').val()){
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
		$('#c_city').bind('valiField', function(e){
			//省份不能为空
			if($('#cityName').val()){
				$('#c_city').removeClass('error');
				$('#city_tip').hide();
			} else {
				$('#c_city').addClass('error');
				$('#city_tip').show();
			}
		});
		//提交前执行的函数
		function beforeFormSubmit(){
			$('#c_contry').trigger('valiField');
			$('#c_province:visible').trigger('valiField');
			$('#c_city:visible').trigger('valiField');
		}
	</script>
</body>
</html>
