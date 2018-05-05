<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>經銷商申請—TVpad官網</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>	

		<div class="content_main">
		<!--End left_Frame-->
		<div class="right_frame" id="user_box">
			 	
				<div class="form_box" id="user_box1">
		          <ul style="margin:0 auto">
						<li class="fometite">
						  <h2>TVpad意向经销商申请表</h2>
						</li>
					</ul>
			<form action="/web/tvpadTopic!saveAgencyApplication.action" method="post">
				  <table width="100%" border="0" cellspacing="1"  class="fonmtable">
					  <tr>
					    <td height="50"><strong><span class="star">*</span>申请人/公司:</strong></td>
					    <td height="50" colspan="3"><input type="text" name="LvDealerApplication.applyCmp" id="textfield"  class="applicant"/></td>
					    </tr>
					  <tr>
					    <td width="14%" height="50"><span class="star"><strong>*</strong></span><strong>联系人:</strong></td>
					    <td width="21%" height="50"><input type="text" name="LvDealerApplication.applyName" id="textfield2" class="mailingaddress"/></td>
					    <td width="11%" height="50"><span class="star"><strong>*</strong></span><strong>联系电话:</strong></td>
					    <td width="54%" height="50"><input type="text" name="LvDealerApplication.applyTel" id="textfield3" class="mailingaddress"/></td>
					    </tr>
					  <tr>
					    <td height="50"><span class="star"><strong>*</strong></span><strong>E-mail:</strong></td>
					    <td height="50" colspan="3">
					        <input type="text" name="LvDealerApplication.applyEmail" id="textfield5" class="mailingaddress"/></td>
					    </tr>
					  <tr>
					    <td height="50"><span class="star"><strong>*</strong></span><strong>通讯地址:</strong></td>
					    <td height="50" colspan="3">
					        <input type="text" name="LvDealerApplication.applyAddr" id="textfield5" class="applicant" value="国家 省/州 城市 区 "/></td>
					    </tr>
					  <tr>
					    <td height="50"><p><span class="star"><strong>*</strong></span><strong>申请代理的区域:</strong></p></td>
					    <td height="50" colspan="3">
					    <input type="text" name="LvDealerApplication.applyArea" id="textfield9" class="applicant" value="国家 省/州  市"/></td>
					    </tr>
					  <tr>
					    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>申请理由（区域特征、行业优势、资源）</strong></p></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><textarea name="LvDealerApplication.applyReason" id="textarea" cols="45" rows="5"></textarea></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><p><span class="star"><strong>*</strong></span><strong>申请人/公司介绍</strong></p></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><textarea name="LvDealerApplication.applyIntro" id="textarea2" cols="45" rows="5"></textarea></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><strong>TVpad 营销计划</strong></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><textarea name="LvDealerApplication.applyPlan" id="textarea3" cols="45" rows="5"></textarea></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><strong>对TVpad公司的建议</strong></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><textarea name="LvDealerApplication.appySuggest" id="textarea4" cols="45" rows="5"></textarea></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4"><span class="star"><strong>*</strong></span><strong>您是通过哪种方式得到TVpad招商信息的？</strong></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4">
					    
					      <p class="p_label">
					        <label><input type="radio" name="LvDealerApplication.type" value="1. 网络" id="RadioGroup1_0" />1. 网络</label>
					       
					        <label><input type="radio" name="LvDealerApplication.type" value="2. 报纸杂志" id="RadioGroup1_1" />2. 报纸杂志</label>
					        
					        <label><input type="radio" name="LvDealerApplication.type" value="3. 朋友推荐" id="RadioGroup1_2" />3. 朋友推荐</label>
					       
					        <label><input type="radio" name="LvDealerApplication.type" value="4. 其他" id="RadioGroup1_3" />4. 其他</label>
					        <input type="text" name="textfield13" id="textfield13" />
					      </p>
					    
					    </td>
					  </tr>
			  
					  <tr>
					    <td height="30" colspan="4" align="center"><p class="star">提示错误信息提示错误信息提示错误信息</p></td>
					  </tr>
					  <tr>
					    <td height="50" colspan="4" align="center"><input type="submit"  value="提交" class="user_center_bt" style="height:34px; margin:0 auto; color:#fff"></td>
					  </tr>
					</table>
				</form>
				</div>
		  	</div>
		  	<!--End right_Frame-->
		  	<div class="cb"></div>	 
		</div>
		<!--End content-->	
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>