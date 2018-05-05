<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad2定制版智能系統網路智能機頂盒</title>
		<link href="${resDomain}/www/res/css/free.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS文件-->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/free.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部信息 -->
		<% request.setAttribute("navFlag","www_free"); %>
		<%@include file="/web/www/common/header.jsp" %>
		
		<div class="project">
			<div class="banner">
		  		<p class="p1"></p>
		  		<p class="p2"><span><a href="#shiyong"><img src="${resDomain}/www/res/images/free/anniu1.jpg" border="0" /></a></span></p>
			</div>
			
			<div class="main">
		  		<div class="main1">
		    		<ul>
						<li>
							<img src="${resDomain}/www/res/images/free/gaoqing.jpg" width="50" height="50" />
							<p><span>高清穩定</span><br />專為海外華人提供香港、澳門、台灣、大陸超過100個高清穩定的<br />中文電視直播頻道；</p>
					  	</li>
			  			<li>
			  				<img src="${resDomain}/www/res/images/free/zhengban.jpg" width="50" height="50" />
			  				<p><span>正版保證</span><br />中國最大廣電集團運營傳媒企業——華數入駐TVpad，正版認證，<br />片源穩定；</p>
			  			</li>
			  			<li>
			  				<img src="${resDomain}/www/res/images/free/new.jpg" width="50" height="50" />
			  				<p><span>每日更新</span><br />超過100,000部正版授權的影劇、綜藝、動漫和紀錄片每日更新；</p>
			  			</li>
			  			<li>
			  				<img src="${resDomain}/www/res/images/free/yule.jpg" width="50" height="50" />
			  				<p><span>娛樂體驗</span><br />卡拉OK，體感遊戲、VOIP電話燈全新娛樂功能體驗，操作簡單；</p>
			  			</li>
					</ul>
		  		</div>
		  
		  		<div class="main2">
		    		<p class="image1"><span>“每一個中國傳統佳節都牽動著億萬遊子的心。正所謂禮尚往來，一份<br />體面又有新意的好禮最能表達思念和慰問！”<br /><a href="#shiyong">立即免費試用>></a></span></p><p class="image2"></p>
		  		</div>
		  
		  		<div class="main3">
		   			<p><img src="${resDomain}/www/res/images/free/main3_image1.jpg" /></p>
		   			<p><img src="${resDomain}/www/res/images/free/main3_image2.jpg" /></p>
		  		</div>
		  
		  		<div class="main4" id="shiyong">
		    		<div class="main4_top"></div>
					<div class="main4_middle">
			  			<p class="bt"><img src="${resDomain}/www/res/images/free/main4_bg_bt.jpg" /></p>
			  			<form action="/web/free!toEmail.action" method="post" id="myform">
							<ul>
								<c:if test="${not empty cMark}">
									<li>
										<p class="text"></p>
										<p class="input"><font color="red">
											<c:if test="${cMark==1}">驗證碼不正確！</c:if>
											<c:if test="${cMark==2}">對不起，申請發送失敗！</c:if>
											<c:if test="${cMark==3}">恭喜您，申請發送成功！</c:if>
										</font></p>
									</li>
								</c:if>
								
							    <li><p class="text"><span class="red">*</span> 申請人:</p><p class="input"><input name="name" class="input2"  type="text" /></p></li>
								<li><p class="text"><span class="red">*</span> 電話:</p><p class="input"><input name="tel" class="input2"  type="text" /></p></li>
								<li><p class="text"><span class="red">*</span> 郵箱:</p><p class="input"><input name="email" class="input2"  type="text" /></p></li>
								<li><p class="text"><span class="red">*</span> 郵編:</p><p class="input"><input name="postCode" class="input2"  type="text" /></p></li>
								<li><p class="text"><span class="red">*</span> 收貨地址:</p><p class="input"><input  name="adress" id="adress" type="text" class="input3" value="街道詳細地址" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/>  
									- <input  name="cityName" id="cityName" type="text" class="input2" value="縣/市" onfocus="if(this.value=='縣/市')this.value=''" onblur="if(this.value=='')this.value='縣/市'"/>
									- <input type="hidden" id="test" />
									<input type="text" name="provinceName" id="provinceName" class="input2" value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
									-<select name="contryId" id="contryId" onchange="contryChange()" class="input2">
										<option value="">--請選擇國家--</option>
										<c:foreach items="${contryList}" var="c">
											<option value="${c.id}">${c.nameen}</option>
										</c:foreach>
									</select></p>
									<input type="hidden" name="contryName" id="contrynameId"  value=""/>
								<li>
									<p class="text"><span class="red">*</span> 驗證碼:</p>
									<p class="input"><input name="code" id="yzm" class="input2" type="text"/></p>
									<p class="cx"><img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
									<p class="huanyizhang"><span class="kbqc">看不清楚？</span><a href="#" class="ljh" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"> 換一張</a>&nbsp;&nbsp;<span id="yzmInfo"></span>		</p>
								</li>
								<li id="infoUl" style="display:none"><p class="text"></p><p class="input"><span id="addressInfo"></span></p></li>
								<li class="btn"><input type="image" src="${resDomain}/www/res/images/free/tijiao.jpg" border="0" /></li>
						  	</ul>
			  			</form>	
			 			<div class="cb"></div>
					</div>
					<div class="main4_bottom"></div>
		  		</div>
			</div>
		</div>
		<!--End 试用-->	
		
		<!-- 底部-->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>