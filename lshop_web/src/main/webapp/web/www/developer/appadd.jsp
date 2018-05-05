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

<style type="text/css">

</style>
		<script type="text/javascript">

$(function(){
	if(!$("#checkbox").attr("checked")){
	  show("xieyi");
	}
	  initUpload("file_show_1","payUploadify_1");
});


function checkDate(form){
	$(".errormsg").html("");
	var name=$("#appname").val();
	var isCheck=$("#checkbox").attr("checked");
	var issubmit=true;
	if(!checkInput(name,32)){
		showMsg("appname","請輸入32長度以內的應用名稱");
		issubmit= false;
	}
	
    var filetest = /.+\.(pkg|apk)/;
    var file_val=$("#file_name").val();
    if(file_val==null||file_val==""){
           $("#errormsg").html("請選擇pkg、apk格式文件");
           issubmit= false;
    }
    if(file_val!=""&&!filetest.test(file_val)){
    	$("#errormsg").html("只能上傳pkg、apk格式文件");
    	issubmit= false;
    }else {
        file_val=file_val.substring(file_val.lastIndexOf("\\")+1);
      $("#file_name").val(file_val);
    }

	
	 if(!isCheck){
		 issubmit= false;
	 }
	return issubmit;
}

function submitForm(){
	$("#form").submit();
}


<%-- 初始化上传插件 --%>
function initUpload( showid,fileid) {
	$("#errormsg").html('');//情况错误信息显示
	checkFlash("flash_msg");
	var sizeLimit = 200 * 1024 * 1024;		<%-- 上传文件最大限制 --%>
	var cancelimg= '${resDomain}/www/res/js/uploadify/cancel.png';
	var  user=lshop.getCookieToJSON('user');
	var email=user.email;
	$("#"+fileid).uploadify({
        'uploader'       : '${storeDomain}/web/www/developer/uploadify.swf',
        'script'         : '/util/upload.do?p=1|'+email,
        'cancelImg'      : cancelimg,
        'queueID'        : showid,				  <%-- 与下面的id对应 --%>
        'fileDesc'       : 'File',   	 <%-- 可上传的文件格式说明，不能为空，否则fileExt会失效    --%>
        'fileExt'        : '*.pkg;*.apk;', 	<%-- 控制可上传文件的扩展名，启用本项时需同时声明fileDesc --%>
    	'sizeLimit'		 : sizeLimit,					 <%-- 上传文件大小 --%>
        'auto'           : true,				 		<%-- 是否自动上传 --%>
        'multi'          : true,				 		<%-- 是否可以上传多个文件 --%>
        'height'         : 30,
		'width'          : 80,
        'buttonImg'      : '${resDomain}/www/res/images/uploadbt.jpg',
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
									$("#errormsg").html('上传文件不能大于200M');
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
		<%@include file="/web/www/common/header.jsp" %>
	
	
<div class="application1" style="height:360px">
   <div class="posit">
      <h2 class="bt3" id="nbt">
	    <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p>
	    <p><a href="${storeDomain}/web/index!toIndex.action">首頁 </a> > <a href="${storeDomain}/web/app!appList.action">我的應用</a> > 上傳應用</p>
	  </h2>
   </div> 
   <div class="clear"></div> 
  <div class="app_submit">
	  <form  action="${storeDomain}/web/app!add.action" id="form"  method="post" enctype="multipart/form-data" onsubmit="return checkDate(this)">
	   	  <input type="hidden" name="app.isAgree" id="isAgree" value="0" />
	     <table width="100%" border="0">
	       <tr>
		    <td width="35%" height="40" align="right"><p><span class="star"></span></p></td>
		    <td  colspan="2" height="40"><p><span class="star" id="flash_msg"></span></p></td>
		  </tr>
			  <tr>
			    <td width="35%" height="40" align="right"><p><span class="star">*</span>應用名稱：</p></td>
			    <td width="37%" height="40">
			      <input type="text" name="app.name" id="appname"  class="inttext" value="${app.name}"/></td>
			    <td width="28%" height="40"><span class="star errormsg"></span></td>
			  </tr>
			  <tr>
			    <td height="40" align="right"><p><span class="star">*</span>應用安裝包：</p></td>
			    <td height="40" >
			<!--       <div class="upload">
			        <input name="fileName" id="file_name" class="file_name" type="hidden" value="0" />
		            <input name="file" class="fileinput" type="file" id="idUrl" size="30"  class="fileinput"/>
			     </div> -->
			      <div class="fileupload">
			           <div id="file_show_1" class="file_msg" style="width:400px"></div>
			           <input name="app.url" id="file_name" class="file_name" type="hidden" value=""/>
					   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_1"  />
			       </div> 
			      <div class="upload_detial">
			        <p class="bt4">請上傳pkg、apk格式文件，大小不超過200MB</p>
			      </div>
			    </td>
	                <td height="40"><span class="star errormsg" id="errormsg">${msg}</span></td>
			   </tr>
			  <tr>
			    <td height="40" align="right">&nbsp;</td>
			    <td height="40" colspan="2">
			      <a href="#" class="subminbt" id="subnext" onclick="submitForm()">下一步</a>
			      <a href="${storeDomain}/web/app!appList.action" class="subminbt" id="subreturn">取 消</a>
			      <input type="checkbox" name="checkbox" id="checkbox"   onchange="changeAgree()" />同意
			       <a href="#" id="shanfan"  onclick="show('xieyi')">《提交應用協議》</a>
			      </td>
			    </tr>
			  <tr>
			    <td height="40" align="right">&nbsp;</td>
			    <td height="40"><p class="star"  id="agreemsg" >請查看協議，同意後方可提交！</p></p></td>
			    <td height="40">&nbsp;</td>
			  </tr>
	 </table>
	</form>
  </div>
</div>
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
		<div id="mask" style="display:none;"></div>
<div class="xieyi" id="xieyi" style="top:150px;position: absolute;display: none;z-index:9999;" >
  <div class="context_xieyi_title"><strong>提交應用協議</strong></div>
  <div class="context_xieyi" style="height:80px">
   <p><strong>應用入駐免責申明：申請入駐者需對提交的應用程式及其內容負責</strong>。申請者對一切侵權行為負法律責任，並賠償TVpad應用商店因此遭受的損失。如遇任何協力廠商對甲方所發佈的應用程式提出智慧財產權質疑或投訴，TVpad應用商店有權做出對涉及智慧財產權質疑或投訴的內容進行下架處理。</p>                                   
  
</div>
<div class="context_bottom">
  <div class="bt_bottom">
    <a href="#" class="Agree"  onclick="agreement(1)">同意</a>
    <a href="${storeDomain}/web/developer!developerIndex.action" >不同意</a>
  </div>
</div>
</div>
	</body>
</html>