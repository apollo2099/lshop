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
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
	
<script type="text/javascript">

$(function(){
	if(!$("#checkbox").attr("checked")){
	  show("xieyi");
	}
	  
	  $("#idUrl").change(function(){
		  var file_val=$(this).val();
		  if(file_val==null||file_val==""){
	        $("#upload_ps").html("上传小于4MB的图片");
		  }else if(file_val.length>104){
			  $("#upload_ps").html("上传的文件名称过长,请控制在100长度以内.");  
		  }else{
		    file_val=file_val.substring(file_val.lastIndexOf("\\")+1);
		    if(file_val.length>30){
	         $("#upload_ps").attr("title",file_val);
		    	file_val=file_val.substring(0,30);
		    	file_val+="...";
		    }
	         $("#upload_ps").html(file_val);
		  }
	  });
});



function checkDate(form){
	$(".errormsg").html("");
	var name=$("#contactName").val();
	var idNum=$("#idNum").val();
	var tel=$("#tel").val();
	var isCheck=$("#checkbox").attr("checked");
	var issubmit=true;
	if(!checkInput(name,32)){
		showMsg("contactName","请输入32长度以内的真实姓名");
		issubmit= false;
	}
	if(!checkInput(idNum,18)){
		showMsg("idNum","请输入18长度以内的身份证号码");
		issubmit= false;
	}
	
    var filetest = /.+\.(jpg|JPG|png|PNG)/;
    var file_val=$("#idUrl").val();
    if(file_val==null||file_val==""){
           $("#errormsg").html("请选择jpg|png格式图片");
           issubmit= false;
    }
    if(file_val!=""&&!filetest.test(file_val)){
    	$("#errormsg").html("只能上传jpg|png格式的图");
    	issubmit= false;
    }else {
        file_val=file_val.substring(file_val.lastIndexOf("\\")+1);
      $("#file_name").val(file_val);
    }
	if(!checkInput(tel,32)){
		showMsg("tel","请输入32长度以内的联系电话");
		issubmit= false;
	}
	
	 if(!isCheck){
		 issubmit= false;
	 }
	return issubmit;
}
</script>
	
	
<div class="application1" style="height:360px">
	   <div class="posit">
	      <h2 class="bt3" id="nbt">
		    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > <a href="${storeDomain}/web/developer!developerIndex.action">注册开发者</a>  > 个人开发者 </p>
		  </h2>
	   </div> 
	   <div class="clear"></div> 
	  <div class="app_submit">
	   <form action="${storeDomain}/web/developer!apply.action" method="post" enctype="multipart/form-data" onsubmit="return checkDate(this)">
	      <input type="hidden" name="developer.dtype" id="dtype" value="0" />
	      <input type="hidden" name="developer.isAgree" id="isAgree"  value="${developer.isAgree}" />
	     <table width="100%" border="0">
	  <tr>
	    <td width="35%" height="40" align="right"><p><span class="star">*</span>真实姓名：</p></td>
	    <td width="28%" height="40">
	      <input type="text" name="developer.contactName" id="contactName" value="${developer.contactName}" class="inttext"/><p class="star"></p></td>
	    <td width="37%" height="40"><span class="star errormsg"></span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>身份证号码：</p></td>
	    <td height="40"><input type="text" name="developer.idNum" id="idNum"  value="${developer.idNum}"  class="inttext"/></td>
	    <td height="40"><span class="star errormsg"><c:choose><c:when test="${status eq '3'}">身份证号码已被使用</c:when></c:choose> </span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>身份证扫描件：</p></td>
	    <td height="40">
	     <div class="upload">
	     <input name="fileName" id="file_name" class="file_name" type="hidden" value="0" />
		 <input name="file" class="file_input" type="file" id="idUrl" size="30"  class="fileinput"/>
	     </div>
	      <div class="upload_detial"><span class="bt4" id="upload_ps">上传小于4MB的图片</span></div>
	     </td>
	    <td height="40"><span class="star errormsg" id="errormsg">${msg}</span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>联系电话：</p></td>
	    <td height="40"><input type="text"  name="developer.tel" id="tel"  value="${developer.tel}"  class="inttext"/></td>
	    <td height="40"><span class="star errormsg"></span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right">&nbsp;</td>
	    <td height="40" colspan="2"><input type="submit" name="button" id="button" value="提 交"  class="subminbt"/>
	      <input type="checkbox" name="checkbox" id="checkbox" onchange="changeAgree()"	  <c:if test="${developer.isAgree==1}">checked='checked'</c:if> />  
	                  同意 <a href="#" id="shanfan" onclick="show('xieyi')">《第三方应用入驻协议》</a></td>
	    </tr>
	  <tr>
	    <td height="40" align="right">&nbsp;</td>
	    <td height="40"><p class="star" id="agreemsg" ><c:if test="${developer.isAgree!=1}">请查看协议，同意后方可提交！</c:if></p></td>
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
  <div class="context_xieyi_title"><strong>banana应用商城入驻协议</strong></div>
  <div class="context_xieyi">
    <p>本协议系由深圳市金香蕉信息技术有限公司与所有入驻的主体（包括但不限于个人、团队等）（以下简称&#8220;提交者&#8221;）就应用程序上架、推广及相关服务所订立的有效合约，所有提交者必须同意并遵守本协议。提交者阅读并点击&#8220;同意&#8221;后被视为提交者完全接受本协议。</p>
   <p><strong>一、定义</strong><br />
     &#8220;应用商城&#8221;是指由深圳市金香蕉信息技术有限公司所有并运营的banana应用商城。该平台可能包括但不限于一个或多个子业务。<br />
     &#8220;提交者&#8221;指有效申请通过banana应用商城资质审核获得上传权限，在banana应用商城上发布应用程序作品的入驻方，包括法人、其他组织或自然人。</p>
   <p><strong>二、所有权</strong><br />
     深圳市金香蕉信息技术有限公司保留对以下各项内容、信息完全的、不可分割的所有权及知识产权: <br />
     1、banana应用商城及其所有元素，<strong>包括但不限于所有内容、数据、技术、软件、代码、用户界面以及与其相关的任何衍生作品；</strong><br />
     2、用户信息；<br />
     3、提交者向banana应用商城提供的与该平台服务相关的任何信息及反馈。 <br />
     深圳市金香蕉信息技术有限公司授予提交者有限的、可随时废止的许可，非经许可和超范围的使用均被视为违约，<strong>banana应用商城有权随时停止上述许可并有权取消提交者资格。 </strong><br />
   </p>
   <p><strong>三、承诺与保证</strong><br />
     1.1、<strong>提交者保证，其向banana应用商城提供的应用程序及所包含的内容如侵犯第三方权利&nbsp;（包括但不限于著作权、专利权、名誉权等），由提交者自行承担由此产生全部责任，</strong>若由此引起第三方针对乙方的争议、纠纷、诉讼等应当由提交者出面负责解决，承担全部责任并赔偿由此对banana应用商城造成的全部损失: <br />
     1.2、<strong>提交者向banana应用商城所提供的个人资料（包括但不限于姓名、性别、民族、年龄、身份证号码、职业、联系方式）是真实的、完整的、合法有效的；</strong><br />
     1.3、提交者提交的应用程序及内容不得显示或者链接以下内容： <br />
     a.非法内容<br />
     b.侵犯个人隐私权或侵害形象权<br />
     c.干扰任何其他团体的服务器，网络或服务的内容<br />
     d.鼓动仇视或煽动暴力的内容<br />
     e.侵犯知识产权，包括专利权、著作权、注册商标、商业机密或是其他任何团体的所有权。<br />
     f.色情、淫秽等内容 <br />
     1.4、&nbsp;未事先经过原始用户的同意向任何非原始用户显示或以其他方式提供任何用户信息；<br /> 
     1.5、 请求、收集、索取或以其他方式从任何用户那里获取对banana应用商城登录帐号、密码或其他身份验证凭据的访问权；<br />
     1.6、 提交者违反上述任何一款的保证，深圳市金香蕉信息技术有限公司均有权就其情节，对其做出警告、屏蔽、直至取消资格的处罚；如因提交者违反上述保证而给banana应用商城、banana应用商城用户或深圳市金香蕉信息技术有限公司的任何合作伙伴造成损失，提交者负责赔偿。</p>
   <p>2、深圳市金香蕉信息技术有限公司承诺并保证: </p>
   <p >2.1&nbsp;遵守公平、公正、公开的原则，对提交者的开发作品给予公正的评论。 </p>
   <p >2.2&nbsp;banana应用商城确认对提交者提供的banana应用商城服务，现阶段免费供banana应用商城的提交者使用，但以后是否收取费用，收费的时间、费用额度，以及深圳市金香蕉信息技术有限公司与提交者之间的收入分配均由深圳市金香蕉信息技术有限公司另行决定。 </p>
   <p>
  <strong>四、知识产权保护</strong><br />
 如果提交者创建的banana应用商城资源允许用户下载、查看、收听或以其他方式访问或分发由其他用户提供的第三方内容，应当得到深圳市金香蕉信息技术有限公司的书面许可，并必须发布和实施符合相关知识产权法律法规中相关的版权政策，其中必须包括但不限于:</p>
   <p>1、提交者在收到侵权通知之时，应立即删除或禁止访问声明的侵权内容，并同时联系banana商城运营人员。 </p>
   <p >2、提交者知悉并同意深圳市金香蕉信息技术有限公司将根据相关法律法规对第三方发出的合格的侵权通知进行处理，并按照要求删除或禁止访问声明的侵权内容，采用并实施适当的政策。  </p>
   <p>
  <strong>五、宣传</strong><br />
  提交者可以宣传自己提供的banana应用商城资源，但未事先经过深圳市金香蕉信息技术有限公司的书面同意，不得发布使用任何含有banana应用商城名称、标记的新闻稿或其他宣传声明或推广材料。深圳市金香蕉信息技术有限公司有权在任何时间、空间发布提及和/或描述banana应用商城、banana应用商城资源和/或提交者的宣传和推广材料。</p>
   <p>
<strong>六、其他</strong> <br />
1、本协议最终解释权及修改权归深圳市金香蕉信息技术有限公司所有。 </p>
   <p >2、本协议已经公布即生效，深圳市金香蕉信息技术有限公司有权随时对协议内容进行修改，修改后的结果公布于官方网站上，提交者继续使用商城服务，则视为其对修改后的协议不持任何异议并同意遵守；如提交者对协议存在异议，可以向深圳市金香蕉信息技术有限公司书面提出退出开发许可，但提交者在深圳市金香蕉信息技术有限公司确认其退出许可前的行为，仍受本协议限制。 </p>
   <p>&nbsp;</p>
</div>
<div class="context_bottom">
  <div class="bt_bottom">
    <a href="#" class="Agree" onclick="agreement(1)">同意</a>
    <a href="${storeDomain}/web/developer!developerIndex.action" >不同意</a>
  </div>
</div>
</div>
	</body>
</html>