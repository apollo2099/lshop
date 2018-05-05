<%@ page language="java" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("lvOrderComment.content") != null ? request.getParameter("lvOrderComment.content") : "";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />

		
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	<script type="text/javascript" src="${resDomain}/www/res/js/comment.js"></script>
     <script type="text/javascript" src="${resDomain}/www/res/js/developer.js"></script>
     <link rel="stylesheet" type="text/css" href="${resDomain}/www/res/js/uploadify/uploadify.css" />
     <script type="text/javascript" src="${resDomain}/www/res/js/uploadify/jquery.uploadify.swfobject.js"></script>
     <script type="text/javascript" src="${resDomain}/www/res/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
     <script type="text/javascript">
     $(function(){
    		  initUpload("file_show_1","payUploadify_1");
      });
     <%-- 初始化上传插件 --%>
     function initUpload( showid,fileid) {
     	$("#errormsg").html('');//情况错误信息显示
     	checkFlash("flash_msg");
     	var sizeLimit = 2 * 1024 * 1024;		<%-- 上传文件最大限制 --%>
     	var cancelimg= '${resDomain}/www/res/js/uploadify/cancel.png';
     	var  user=lshop.getCookieToJSON('user');
     	var email=user.email;
     	$("#"+fileid).uploadify({
             'uploader'       : '${storeDomain}/web/www/developer/uploadify.swf',
             'script'         : '/util/upload.do?p=3|'+email,
             'cancelImg'      : cancelimg,
             'queueID'        : showid,				  <%-- 与下面的id对应 --%>
             'fileDesc'       : 'File',   	 <%-- 可上传的文件格式说明，不能为空，否则fileExt会失效    --%>
             'fileExt'        : '*.jpg;*.png;', 	<%-- 控制可上传文件的扩展名，启用本项时需同时声明fileDesc --%>
         	 'sizeLimit'		 : sizeLimit,					 <%-- 上传文件大小 --%>
             'auto'           : true,				 		<%-- 是否自动上传 --%>
             'multi'          : true,				 		<%-- 是否可以上传多个文件 --%>
             'height'         : 30,
     		 'width'          : 80,
             'buttonImg'      : '${resDomain}/en/res/images/uploadbt.jpg',
             'onComplete'	 : function(event, queueID, fileObj, response, data) {
             	                    $("#errormsg").html("");
     				            	var result = eval("(" + response + ")");
     				            	if (result.status != "200") {
     				            		if(result.status=='301'){
     										 $("#errormsg").html("文件名過長,文件名請控制在100長度以內。");
     					            		}else{
     										 $("#errormsg").html("上傳失敗(錯誤代碼"+result.status+")");
     					            		}
     										return;
     				            	}
     				            	
     				            	$("#"+fileid+"Uploader").css("width","0px");
     				            	$("#"+fileid+"Uploader").css("height","0px");
     				            	
     				            	var spantext= document.createElement("span");//显示上次文件信息
     					               var fileDisplayName=fileObj.name;
     					            	spantext.title= fileDisplayName;
     					              if(fileDisplayName.length>35){
     					            	  fileDisplayName=fileDisplayName.substring(0,35);
     					            	  fileDisplayName+="...";
     					              }
     					            	spantext.innerHTML= fileDisplayName;
     				            	$("#"+showid).append(spantext);
     				            	
     				            	
     				            	var apppath=result.path;
     				            	var appname=apppath.substring(apppath.lastIndexOf("/")+1);
     				            	$("#"+fileid).prev().val(appname);
     				            	
     				            	
     				            	$("#file_name").val('${resDomain}'+apppath)
     				            	var cancelimgobj= document.createElement("img");//取消
     				            	cancelimgobj.src=cancelimg;
     				            	$("#"+showid).append(cancelimgobj);
     				            	$(cancelimgobj).addClass("fileupload_cancelbut");
     				            	$(cancelimgobj).click(function(){
     				            		$.ajax({
     				            			   type: "POST",
     				            			   url: "/web/app!cancelApp.action",
     				            			   data: "appname="+appname,
     				            			   async: false, 
     				            			   success: function(flag){
     				            			   	 	if(flag){
     								            		$("#"+showid).html("");
     								            		$("#"+fileid).prev().val("");
     									            	$("#"+fileid+"Uploader").css("width","80px");
     									            	$("#"+fileid+"Uploader").css("height","30px");
     				            			   	 	}else{
     				            			   	 		alert("删除失败");
     				            			   	 	}
     				            			   	 }
     				            			});
     				            	});
     				            	
     				            	
     	               		   },
              'onAllComplete' : function() {
                				 		
                				   },
           	  'onError' 		 : function(event, queueID, fileObj, errorObj) {   
                						if (fileObj.size > sizeLimit) {
     									$("#errormsg").html('上传文件不能大于2M');
     								} else {
     									alert('<s:text name=""><s:param value="' + fileObj.name + '"/><s:param value="'+ errorObj.type +'"/><s:param value="'+ errorObj.info +'"/></s:text>');
     								}
                						$("#file_show_1").html("");	<%-- 不显示插件自带的提示信息 --%>
                					   // $("#agreemsg").html(errorObj.info);
     			         	   }
         });
     }
     </script>
	</head>
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Leave Reviews</span></h1> 
				<div class="buy_order">
					<ul class="title">Item Details</ul>
					<ul>
						<li class="buy_order01">
							<p>&nbsp;</p>
							<p class="title">Item Name</p>
							<p>Price</p>
							<p>Quantity</p>
							<p>Subtotal</p>
						</li>
						<c:foreach items="${objList}" var="obj">
						<li>
							<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
							<p class="title">${obj[0].productName }</p>
							<p><font class="redfont fontwz">${moneyMark}${obj[1].oprice }</font></p>
							<p>${obj[1].pnum }</p>
							<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
						</li>
						</c:foreach>
					</ul>
					<ul class="sum">Total：${moneyMark}${allAmount} - Coupon：${moneyMark}${allCouponPrice } + Freight:${moneyMark}${lvOrder.postprice }</a></ul>
					<ul class="sum01">Amount Payable：<font class="redfont">${moneyMark}${lvOrder.totalPrice }</font></a></ul>
					<ul class="wz">
					  Please leave reviews on the item only.For question during transaction and delivery service, please contact our customer service! Thank you for your time!</a>
					</ul>
				</div>
				<div class="comment">
					<form  name="myform" action="/web/userOrder!saveComment.action" method="post">
						<input type="hidden" name="lvOrderComment.score" id="scoreid"  value="0"/>
						<input type="hidden" name="lvOrderComment.orderId"   value="${lvOrder.oid}"/>
						<input type="hidden" name="lvOrderComment.contryId" value="${lvOrderAdress.contryId}"/>
						<input type="hidden" name="lvOrderComment.grade" id="gradeId"  value="0"/>	
						<input type="hidden" name="shopFlag"   value="${lvOrder.storeId}"/>	
						<ul>Score</ul>
						<ul class="pf">
							<li>
								<img src="${resDomain}/en/res/images/wjx.gif" border="0" onclick="dograde(1,'${resDomain}/en')" onmousemove="moveStar(1,'${resDomain}/en')"  onmouseout="outStar('${resDomain}/en')"    id="image_1"/>
								<img src="${resDomain}/en/res/images/wjx.gif" onmousemove="moveStar(2,'${resDomain}/en')"  onmouseout="outStar('${resDomain}/en')"  border="0" onclick="dograde(2,'${resDomain}/en')" id="image_2"/>
								<img src="${resDomain}/en/res/images/wjx.gif" onmousemove="moveStar(3,'${resDomain}/en')"  onmouseout="outStar('${resDomain}/en')"  onclick="dograde(3,'${resDomain}/en')"  id="image_3" border="0"/>
								<img src="${resDomain}/en/res/images/wjx.gif" onclick="dograde(4,'${resDomain}/en')" onmousemove="moveStar(4,'${resDomain}/en')"  onmouseout="outStar('${resDomain}/en')" id="image_4"  width="30" height="29" border="0"/>
								<img src="${resDomain}/en/res/images/wjx_h.gif" onclick="dograde(5,'${resDomain}/en')" onmousemove="moveStar(5,'${resDomain}/en')"  onmouseout="outStar('${resDomain}/en')" id="image_5"  width="30" height="29" border="0" />
							</li>
							<li>Please click on the Star to score the item.</li>
							<li>
								<input name="grade" type="checkbox" disabled="disabled"  onclick="checkBoxValidate(0)" value="3" /> Good
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(1)"  value="2" /> Medium
								<input name="grade" type="checkbox"  disabled="disabled" onclick="checkBoxValidate(2)" value="1" /> Bad
							</li>
						</ul>
						<ul>Content</ul>
						<ul class="pf">
							<%-- 
							<li><textarea name="lvOrderComment.content" id="contentid"  style="width:700px;height:400px;visibility:hidden;"  ><%=htmlspecialchars(htmlData)%></textarea><p>You have entered <span id="contentNumId" class="word_count">0</span> characters!</p></li>
							--%>
							<li><textarea name="lvOrderComment.content" id="contentid" class="input1" onkeyup="changNumKeyValue()" onkeypress="changNumKeyValue()"    onfocus="if(this.value=='0/500')this.value=''">0/500</textarea><span id="contentNumId">0</span>/500</li>
							<li>
								<div class="fileupload">
						           <div id="file_show_1" class="file_msg" style="width:400px"></div>
						           <input name="lvOrderComment.commentImages" id="file_name" class="file_name" type="hidden" value=""/>
								   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_1"  />
					            </div>
					            <div class="upload_detial">
			                    <p class="bt4">Please upload JPG, PNG format, size does not exceed 2MB</p>
			                    </div>
							    <span class="star errormsg" id="errormsg">${msg}</span>
							</li>
							<li><input type="button" onclick="doAddComment();" value="Submit" class="user_center_bt" /></li>
						</ul>
					</form>	
				</div>
			</div>
		 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>		
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 


<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>