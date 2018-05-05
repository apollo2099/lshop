<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/developer.js"></script>
<link rel="stylesheet" type="text/css" href="${resDomain}/bmcn/res/js/uploadify/uploadify.css" />
<script type="text/javascript" src="${resDomain}/bmcn/res/js/uploadify/jquery.uploadify.swfobject.js"></script>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/uploadify/jquery.uploadify.v2.1.4.js"></script>

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
		showMsg("appname","请输入32长度以内的应用名称");
		issubmit= false;
	}
	
    var filetest = /.+\.(apk)/;
    var file_val=$("#file_name").val();
    if(file_val==null||file_val==""){
           $("#errormsg").html("请选择apk格式文件");
           issubmit= false;
    }
    if(file_val!=""&&!filetest.test(file_val)){
    	$("#errormsg").html("只能上传apk格式文件");
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
	var cancelimg= '${resDomain}/bmcn/res/js/uploadify/cancel.png';
	var  user=lshop.getCookieToJSON('user');
	var email=user.email;
	$("#"+fileid).uploadify({
        'uploader'       : '${storeDomain}/web/bmcn/developer/uploadify.swf',
        'script'         : '/util/upload.do?p=1|'+email,
        'cancelImg'      : cancelimg,
        'queueID'        : showid,				  <%-- 与下面的id对应 --%>
        'fileDesc'       : 'File',   	 <%-- 可上传的文件格式说明，不能为空，否则fileExt会失效    --%>
        'fileExt'        : '*.apk;', 	<%-- 控制可上传文件的扩展名，启用本项时需同时声明fileDesc --%>
    	'sizeLimit'		 : sizeLimit,					 <%-- 上传文件大小 --%>
        'auto'           : true,				 		<%-- 是否自动上传 --%>
        'multi'          : true,				 		<%-- 是否可以上传多个文件 --%>
        'height'         : 30,
		'width'          : 80,
        'buttonImg'      : '${resDomain}/bmcn/res/images/uploadbt.jpg',
        'onComplete'	 : function(event, queueID, fileObj, response, data) {
							   $("#errormsg").html("");
				            	var result = eval("(" + response + ")");
				            	if (result.status != "200") {
				            		if(result.status=='301'){
									 $("#errormsg").html("文件名过长,文件名请控制在100以内。");
				            		}else{
				            			 $("#errormsg").html("上传失败(错误代码"+result.status+")");
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
				            	                    $("#"+fileid+"Uploader").css("width","80px");
				            	                    $("#"+fileid+"Uploader").css("height","30px");
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
      		           alert("sdf"+errorObj.info);
           						if (fileObj.size > sizeLimit) {
									$("#errormsg").html('上传文件不能大于200M');
								} else {
									$("#errormsg").html('上传异常:'+errorObj.type+" "+errorObj.info); 
								}
           						$("#"+showid).html("");
			         	   }
    });
}

</script>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
	
	
<div class="application1" style="height:360px">
   <div class="posit">
      <h2 class="bt3" id="nbt">
	    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p>
	    <p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > <a href="${storeDomain}/web/app!appList.action">我的应用</a> > 上传应用</p>
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
			    <td width="35%" height="40" align="right"><p><span class="star">*</span>应用名称：</p></td>
			    <td width="37%" height="40">
			      <input type="text" name="app.name" id="appname"  class="inttext" value="${app.name}"/></td>
			    <td width="28%" height="40"><span class="star errormsg"></span></td>
			  </tr>
			  <tr>
			    <td height="40" align="right"><p><span class="star">*</span>应用安装包：</p></td>
			    <td height="40" >
<!-- 			      <div class="upload">
			        <input name="fileName" id="file_name" class="file_name" type="hidden" value="0" />
		            <input name="file" class="fileinput" type="file" id="idUrl" size="30"  class="fileinput"/>
			     </div> -->
			     <div class="fileupload" >
			           <div id="file_show_1" class="file_msg" style="width:400px"></div>
			           <input name="app.url" id="file_name" class="file_name" type="hidden" value=""/>
					   <input name="file" class="fileInput" type="file"  size="30" id="payUploadify_1"  />
			       </div> 
			      <div class="upload_detial">
			        <p class="bt4">请上传apk格式文件，大小不超过200MB</p>
			      </div>
			    </td>
	                <td height="40"><span class="star errormsg" id="errormsg">${msg}</span></td>
			   </tr>
			  <tr>
			    <td height="40" align="right">&nbsp;</td>
			    <td height="40" colspan="2">
			      <a class="subminbt" id="subnext" onclick="submitForm()" href="#">下一步</a>
			      <a href="${storeDomain}/web/app!appList.action" class="subminbt" id="subreturn">取 消</a>
			      <input type="checkbox" name="checkbox" id="checkbox"   onchange="changeAgree()" />同意
			       <a href="#" id="shanfan"  onclick="show('xieyi')">《提交应该协议》</a>
			      </td>
			    </tr>
			  <tr>
			    <td height="40" align="right">&nbsp;</td>
			    <td height="40"><p class="star"  id="agreemsg" >请查看协议，同意后方可提交！</p></p></td>
			    <td height="40">&nbsp;</td>
			  </tr>
	 </table>
	</form>
  </div>
</div>
	
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
		<div id="mask" style="display:none;"></div>
<div class="xieyi" id="xieyi" style="top:150px;position: absolute;display: none;z-index:9999;" >
  <div class="context_xieyi_title"><strong>提交应用协议</strong></div>
  <div class="context_xieyi" style="height:80px">
      <p>申请提交者提供的应用程序及所包含的内容如侵犯第三方权利（包括但不限于著作权、专利权、名誉权等），由提交者自行承担由此产生全部责任，若由此引起第三方针对乙方的争议、纠纷、诉讼等应当由提交者出面负责解决，承担全部责任并赔偿由此对banana应用商城造成的全部损失</p>                                    
  
</div>
<div class="context_bottom">
  <div class="bt_bottom">
    <a href="#" class="Agree"  onclick="agreement(1)">同意</a>
    <a href="${storeDomain}/web/developer!developerIndex.action" >不同意</a>  </div>
</div>
</div>
	</body>
</html>