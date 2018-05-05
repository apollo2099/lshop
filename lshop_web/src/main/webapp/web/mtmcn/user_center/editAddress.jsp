<%@ page language="java" pageEncoding="utf-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<c:set var="MallPath" value="" />
<c:set var="ShopPath" value="" />
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta content="telephone=no" name="format-detection" />
<link href="${resDomain}/mtmcn/res/css/style.css" rel="stylesheet" type="text/css">
<%long timeFlag = new Date().getTime(); %>
<link href="${resDomain}/mtmcn/res/css/development.css?time=<%=timeFlag %>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/dev.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/address.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/FomrValidate.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/header.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/hammer.js"></script>
<%-- 静态页面用id写样式bug --%>
<style type="text/css">
.inpu{color:#52555B;}
</style>
<title>TVpad商城_编辑收货地址</title>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>
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
		<form action="/web/userCenterManage!editAddress.action" method="post" id="addressForm" name="addressForm" onsubmit="return beforeAddressSubmit();">
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
					<td width="48%" height="40" class="fon18">洲/省：</td>
				</tr>
				<tr>
					<td height="60" valign="top">
						<div class="boxsele" style="position: inherit;background-color:transparent;">
							<div id="countrySelect" class="searchSelect"  style="float:left;">
								<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
							</div>								
							<input type="hidden" name="lvAccountAddress.contryName"
								id="contryName" value="" />
							<input type="hidden" name="lvAccountAddress.contryId"
								id="contryId" value="" />	
							<div style="position:relative;">
								<div id="contry_tip" class="tip">
									<em></em>
									<span>请选择国家</span>
									<i></i>
									<b></b>
								</div>							
							</div>							
						</div>
					</td>
					<td height="60" valign="top">
						<div class="boxsele" style="width:100%;position: inherit;background-color:transparent;">
							<div id="provinceSelect" class="searchSelect"  style="float:left;">
								<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
							</div>
							<input type="hidden" name="lvAccountAddress.provinceId" id="provinceId"/>
							<input type="hidden" name="lvAccountAddress.provinceName" id="provinceName"/>	
							<div style="position:relative;">
								<div id="province_tip" class="tip">
									<em></em> <span>请输入正确洲/省</span> <i></i> <b></b>
								</div>				
							</div>					
						</div>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="2" class="fon18">县/市：</td>
				</tr>
				<tr>
					<td height="60" colspan="2">
						<div class="inputd"style="position: inherit;">
							<div id="citySelect" class="searchSelect"  style="float:left;">
								<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
							</div>	
							<input type="hidden" name="lvAccountAddress.cityId" id="cityId"/>
							<input type="hidden" name="lvAccountAddress.cityName" id="cityName"/>
							<div style="position:relative;">
								<div  id="city_tip" class="tip">
									<em></em> <span>请输入正确市/县</span> <i></i> <b></b>
								</div>						
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
							<div  id="adress_tip" class="tip">
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
					<td colspan="2"><input type="button" value="保存" class="logins" onclick="doSubmitAddAddress();"/>
					</td>
				</tr>
			</table>

		</form>

	</article>

	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
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
		$('#relName').bind('valiField', {name : '收货人',required:true,min:2,max:32,
			reg:'(?!\.*[\\u4E00-\\u9FA5])^\.*$', txt:'格式不正确'}, valiField);
		$('#tel').bind('valiField', {name : '电话号码', max : 16, reg : '^[0-9\\-]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#mobile').bind('valiField', {name : '手机号码', max : 16, reg : '^[0-9]{3,16}$',
			txt: '格式不正确', callback: 'checkPhone'}, valiField);
		$('#postcodeId').bind('valiField', {name : '邮编', required:true, max : 16, reg : '(?!\.*[\\u4E00-\\u9FA5])^\.*$',
			txt: '不能含有特殊字符'}, valiField);
		$('#provinceName').bind('valiField', {name : '洲/省',required:true, max: 32}, valiField);
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
		//提交前执行的函数
		function beforeFormSubmit(){
			$('#c_contry').trigger('valiField');
			$('#c_province:visible').trigger('valiField');
		}
		
		var resDomainArea = '${resDomain}/www/res/area_en/';
		$(document).ready(function(){
			initArea();
			var contryId = ${lvAccountAddress.contryId};
			var contryName = '${lvAccountAddress.contryName}';
			$.getScript(resDomainArea+'country.js',function(){
				if (contryId > 0) {
					$('#contryId').val(contryId);
					countrySelect.setData(countryData);
					countrySelect.setSelectedId(contryId);
				} else {
					$('#contryId').val('');
				}
				$('#contryName').val(contryName);
				var provinceId = '${lvAccountAddress.provinceId}';
				var pattern = /\d/ig;
				if (!pattern.test(provinceId)) {
					provinceId = -1;
				}		
				var provinceName = '${lvAccountAddress.provinceName}';
				var cityId = '${lvAccountAddress.cityId}';
				if (!pattern.test(cityId)) {
					cityId = -1;
				}
				var cityName = '${lvAccountAddress.cityName}';
				if (provinceId > 0) {
					$('#provinceId').val(provinceId);
					$.getScript(resDomainArea+contryId+'.js',function(){
						provinceSelect.setData(provinceData);
						provinceSelect.setSelectedId(provinceId);
						if (cityId > 0) {
							$('#cityId').val(cityId);
							$.getScript(resDomainArea+contryId+'/'+provinceId+'.js',function(){
								citySelect.setData(cityData);
								citySelect.setSelectedId(cityId);
							});					
						} else {
							$('#cityId').val('');
							citySelect.setData([]);
							citySelect.setText(cityName);
						}
						$('#cityName').val(cityName);				
					});			
				} else {
					$('#provinceId').val('');
					provinceSelect.setData([]);
					provinceSelect.setText(provinceName);
					$('#cityId').val('');
					$('#cityName').val(cityName);
					citySelect.setData([]);
					citySelect.setText(cityName);					
				}
				$('#provinceName').val(provinceName);		
			});
		});		
	</script>
</body>
</html>
