<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
				<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/developer.js"></script>
<link rel="stylesheet" type="text/css" href="${resDomain}/www/res/js/uploadify/uploadify.css" />
<script type="text/javascript" src="${resDomain}/www/res/js/uploadify/jquery.uploadify.swfobject.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
	
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
	
	

   <div class="application1" style="height:730px">
   <div class="posit">
      <h2 class="bt3" id="nbt">
	    <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首頁</a> > <a href="${storeDomain}/web/app!appList.action">我的应用</a>  > 完善應用信息 </p>
	  </h2>
   </div> 
   <div class="clear"></div> 
  <div class="app_submit">

    <form action="${storeDomain}/web/app!editinfo.action" id="form"  method="post" enctype="multipart/form-data" onsubmit="return checkDate(this)">
    <input type="hidden" value="${code}" name="app.code"/>
     <table width="100%" border="0">
       <tr>
    <td width="35%" height="40" align="right"><p><span class="star"></span></p></td>
    <td  colspan="2" height="40"><p><span class="star" id="flash_msg"></span></p></td>
  </tr>
  <tr>
    <td width="35%" height="40" align="right"><p><span class="star">*</span>應用名稱：</p></td>
    <td width="28%" height="40"><p><strong>${app.name}</strong></p></td>
    <td width="37%" height="40"><p class="star error_msg">&nbsp;</p></td>
  </tr>
  <tr>
    <td height="40" align="right"><p><span class="star">*</span>應用版本號：</p></td>
    <td height="40"><input type="text" name="app.appVersion" id="appVersion"  class="inttext"/></td>
    <td height="40"><span class="star error_msg"></span></td>
  </tr>
  <tr>
    <td height="40" align="right"><p><span class="star">*</span>應用語言：</p></td>
    <td height="40"><input type="text" name="app.lang" id="lang"  class="inttext"/></td>
    <td height="40"><span class="star error_msg"></span></td>
  </tr>
  <tr>
    <td height="40" align="right"><p><span class="star">*</span>應用分類：</p></td>
    <td height="40"><input type="text" name="app.appType" id="appType"  class="inttext"/></td>
    <td height="40"><span class="star error_msg"></span></td>
  </tr>
  <tr>
    <td height="40" align="right"><p><span class="star">*</span>應用關鍵字：</p></td>
    <td height="40"><input name="app.keyWord" type="text"  class="inttext" id="keyWord" value=""/></td>
    <td height="40"><span class="star error_msg"></span></td>
  </tr>
  <tr>
    <td height="40" align="right"><p><span class="star">*</span>應用介紹：</p></td>
    <td height="100"><textarea name="app.appDesc" id="appDesc" cols="45" rows="5" class="inarea"></textarea></td>
    <td height="100"><span class="star error_msg" ></span></td>
  </tr>
   <tr>
     <td height="160" rowspan="2" align="right"><p><span class="star">*</span>應用截圖：</p></td>
     <td height="120" colspan="2">
       <div class="fileInputContainer">
              <div id="file_show_1" class="file_msg" style="position: absolute;"></div>
         	<input name="fileName" class="file_name" type="hidden" value="" />
		   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_1"  />
       </div> 
       <div class="fileInputContainer">
              <div id="file_show_2" class="file_msg" style="position: absolute;"></div>
         	<input name="fileName" class="file_name" type="hidden" value="" />
		   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_2"  />
       </div> 
       <div class="fileInputContainer">
              <div id="file_show_3" class="file_msg" style="position: absolute;"></div>
         	<input name="fileName" class="file_name" type="hidden" value="" />
		   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_3"  />
       </div> 
       <div class="fileInputContainer">
              <div id="file_show_4" class="file_msg" style="position: absolute;"></div>
         	<input name="fileName" class="file_name" type="hidden" value="" />
		   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_4"  />
       </div> 
       <div class="fileInputContainer">
              <div id="file_show_5" class="file_msg" style="position: absolute;"></div>
         	<input name="fileName" class="file_name" type="hidden" value="" />
		   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_5"  />
       </div> 
   
     </td>
     </tr>
   <tr>
     <td height="20" colspan="2"><p class="bt4">可上傳JPG或者PNG格式，圖片寬度不能小於320px，高度不能小於480px，單張圖片不能超過1MB。</p></td>
     </tr>
   <tr>
    <td height="30" align="right"><p><span class="star">*</span>是否收費：</p></td>
    <td height="30">
      <p>
        <label>
          <input type="radio" name="app.isCharge" value="1" id="RadioGroup1_0" />
          是</label>
     
        <label>
          <input type="radio" name="app.isCharge" value="0" id="RadioGroup1_1" />
          否
        </label>
        <br />
      </p>
    </td>
    <td height="30">&nbsp;</td>
  </tr>
   <tr>
    <td height="30" align="right"><p><span class="star">*</span>是否有廣告：</p></td>
    <td height="30"><label>
      <input type="radio" name="app.isAd" value="1" id="RadioGroup1_2" />
      是</label>
      
      <label>
        <input type="radio" name="app.isAd" value="0" id="RadioGroup1_3" />
        否 </label></td>
    <td height="30">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="right">&nbsp;</td>
    <td height="40" colspan="2"><input type="submit" name="button" id="button" value="提 交"  class="subminbt"/></td>
    </tr>
  <tr>
    <td height="20" align="right">&nbsp;</td>
    <td height="20"><p><span class="star" id="form_error_msg"></span></p>  </td>
    <td height="20">&nbsp;</td>
  </tr>
</table>
</form>
  </div>
</div>
   	<script type="text/javascript">
   	$(function(){
   		$(".fileInput").live('change',function(){ 
   			var val=$(this).val();
   			   $(this).parent().css("background-image","url(\""+val+"\")");
   			}); 
   		for(var i=1;i<6;i++){
   		 initUpload("file_show_"+i,"payUploadify_"+i);
   		}
   		
   	});
   	 
   	<%-- 初始化上传插件 --%>
   	function initUpload( showid,fileid) {
   		$("#form_error_msg").html('');//情况错误信息显示
   		checkFlash("flash_msg");
   		var sizeLimit = 1 * 1024 * 1024;		<%-- 上传文件最大限制 --%>
   		var cancelimg= '${resDomain}/www/res/js/uploadify/cancel.png';
   		var  user=lshop.getCookieToJSON('user');
   		var email=user.email;
   		$("#"+fileid).uploadify({
   	        'uploader'       : '${storeDomain}/web/www/developer/uploadify.swf',
   	        'script'         : '/util/upload.do?p=2|'+email,
   	        'cancelImg'      : cancelimg,
   	        'queueID'        : showid,				  <%-- 与下面的id对应 --%>
   	        'fileDesc'       : 'File',   	 <%-- 可上传的文件格式说明，不能为空，否则fileExt会失效    --%>
   	        'fileExt'        : '*.png;*.jpg;', 	<%-- 控制可上传文件的扩展名，启用本项时需同时声明fileDesc --%>
   	    	'sizeLimit'		 : sizeLimit,					 <%-- 上传文件大小 --%>
   	        'auto'           : true,				 		<%-- 是否自动上传 --%>
   	        'multi'          : true,				 		<%-- 是否可以上传多个文件 --%>
   	        'height'         : 120,
   			'width'          : 100,
   	        'buttonImg'      : '${resDomain}/www/res/images/add_app_img.jpg',
   	        'onComplete'	 : function(event, queueID, fileObj, response, data) {
   					            	var result = eval("(" + response + ")");
   										 $("#form_error_msg").html("");
   					            	if (result.status != "200") {
   					            		if(result.status=='301'){
   										 $("#form_error_msg").html("文件名過長,文件名請控制在100長度以內。");
   					            		}else{
   										 $("#form_error_msg").html(result.msg);
   					            		}
   										return;
   					            	}
   					            	var imgpath=result.path;
   					            	$("#"+showid).addClass("upload_show");
   					            	var imgobj= document.createElement("img");//预览
   					            	imgobj.src="${resDomain}/"+imgpath;
   					            	$("#"+showid).append(imgobj);
   					            	$(imgobj).addClass("show");
   					            	var imgname=imgpath.substring(imgpath.lastIndexOf("/")+1);
   					            	$("#"+fileid).prev().val(imgname);
   					            	
   					            	var cancelimgobj= document.createElement("img");//取消
   					            	cancelimgobj.src=cancelimg;
   					            	$("#"+showid).append(cancelimgobj);
   					            	$(cancelimgobj).addClass("cancelimg");
   					            	$(cancelimgobj).click(function(){
   					            		$.ajax({
   					            			   type: "POST",
   					            			   url: "/web/app!cancelImg.action",
   					            			   data: "img="+imgname,
   					            			   async: false, 
   					            			   success: function(flag){
   					            			   	 	if(flag){
   									            		$("#"+showid).html("");
   									            		$("#"+showid).removeClass("upload_show");
   									            		$("#"+fileid).prev().val("");
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
   										$("#form_error_msg").html('上傳圖片不能大於1M');
   									} else {
   										alert('<s:text name=""><s:param value="' + fileObj.name + '"/><s:param value="'+ errorObj.type +'"/><s:param value="'+ errorObj.info +'"/></s:text>');
   									}
   	           						$("#payFileQueue").html("");	<%-- 不显示插件自带的提示信息 --%>
   				         	   }
   	    });
   	}

   	function checkDate(form){
   		$(".error_msg").html("");
   		var appVersion=$("#appVersion").val();
   		var lang=$("#lang").val();
   		var appType=$("#appType").val();
   		var keyWord=$("#keyWord").val();
   		var appDesc=$("#appDesc").val();
   		var isCharge=$('input:radio[name="app.isCharge"]:checked').val();
   		var isad=$('input:radio[name="app.isAd"]:checked').val();
   		var issubmit=true;
   		if(!checkInput(appVersion,16)){
   			showMsg("appVersion","請輸入16長度以內的應用版本號。");
   			issubmit= false;
   		}
   		if(!checkInput(lang,16)){
   			showMsg("lang","請輸入16長度以內的應用語言");
   			issubmit= false;
   		}
   		if(!checkInput(appType,16)){
   			showMsg("appType","請輸入16長度以內的應用分類");
   			issubmit= false;
   		}
   		if(!checkInput(keyWord,32)){
   			showMsg("keyWord","請輸入32長度以內的應用關鍵字。");
   			issubmit= false;
   		}
   		if(!checkInput(appDesc,1000)){
   			showMsg("appDesc","請輸入1000長度以內的應用介紹。");
   			issubmit= false;
   		}
   		
   		var file_names=$(".file_name");
   		var isimg=false;
   		for(var i=0;i<file_names.length;i++){
   			var fileval=$(file_names[i]).val();
   			if(fileval!=null&&fileval!=''){
   				isimg=true;
   				break;
   			}
   		}
   		if(!isimg){
   			$("#form_error_msg").html('請選擇應用截圖');
   			return false;
   		}
   		
   		if(isCharge==null){
   			$("#form_error_msg").html('請選擇是否收費');
   			return false;
   		}
   		if(isad==null){
   			$("#form_error_msg").html('請選擇是否有廣告');
   			return false;
   		}
   		
   		
   	    var filetest = /.+\.(jpg|png)/;
   	    var file_vals=$(".fileInput");
   	    if(file_vals==null||file_vals.length==0){
   	        $("#errormsg").html("请选择jpg、png格式文件");
   	        issubmit= false;
   	    }else{
   		    for(var i=0;i<file_vals.length;i++){
   		    	var file_val=$(file_vals[i]).val();
   			    if(file_val!=null&&file_val!=""){
   			    if(!filetest.test(file_val)){
   			    	$("#errormsg").html("只能上傳jpg、png格式文件");
   			    	issubmit= false;
   			    }else {
   			        var filename=file_val.substring(file_val.lastIndexOf("\\")+1);
   			        $(file_vals[i]).prev().val(filename);
   			     //   $(file_vals[i]).parent().css("background-image",file_val);
   			    }
   			  }
   		    }
   	    }
   		
   		return issubmit;
   	}

   	function submit(){
   		$("#form").submit();
   	}
</script>
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>