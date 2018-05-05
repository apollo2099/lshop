<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
	

<div class="application1" style="height:auto;">
   <div class="posit">
      <h2 class="bt3" id="nbt">
	     <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > <a href="${storeDomain}/web/app!appList.action">我的应用</a> > 应用详情 </p>
	  </h2>
   </div> 
   <div class="clear"></div> 
  <div class="app_submit">
     <table width="100%" border="0">
  <tr>
    <td width="35%" height="30" align="right" valign="top"><p><span class="star">*</span>应用名称：</p></td>
    <td width="54%" height="30" valign="top"><p><strong>${app.name}</strong></p></td>
    <td width="11%" height="30"><p class="star">&nbsp;</p></td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>应用版本号：</p></td>
    <td height="30" valign="top"><p>${app.appVersion}</p></td>
    <td height="30">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>应用语言：</p></td>
    <td height="30" valign="top"><p>${app.lang}</p></td>
    <td height="30">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>应用分类：</p></td>
    <td height="30" valign="top"><p>${app.appType}</p></td>
    <td height="30">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>应用关键字：</p></td>
    <td height="30" valign="top"><p>${app.keyWord}</p></td>
    <td height="30">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>应用介绍：</p></td>
    <td height="30" valign="top"><p>${app.appDesc}</p></td>
    <td height="30">&nbsp;</td>
    </tr>
   <tr>
     <td height="160" align="right"><p><span class="star">*</span>应用截图：</p></td>
     <td colspan="2">
      <s:iterator value="app.demoImgs" id="appimg">
       <div class="fileInputContainer1">
        <a href="${resDomain}/${appimg}" target="_blank"> <img src="${resDomain}/${appimg}" width="100" height="120" /></a>
       </div> 
       </s:iterator>
     </td>
   </tr>
   <tr>
     <td height="30" align="right" valign="top"><p><span class="star">*</span>是否收费：</p></td>
     <td height="30" valign="top">
                 <s:if test="app.isCharge==0">否</s:if>
                 <s:if test="app.isCharge==1">是</s:if>
     </td>
     <td height="30" valign="top">&nbsp;</td>
   </tr>
   <tr>
    <td height="30" align="right" valign="top"><p><span class="star">*</span>是否有广告：</p></td>
    <td height="30" valign="top"> <s:if test="app.isAd==0">否</s:if><s:if test="app.isAd==1">是</s:if></td>
    <td height="30" valign="top">&nbsp;</td>
  </tr>
    <tr>
    <td height="30" align="right" valign="top"><p>提交时间：</p></td>
    <td height="30" colspan="2" valign="top"><p><s:date  name="app.createTime" format="yyyy-MM-dd HH:mm:ss"/></p></td>
    </tr>
  <tr>
    <td height="30" align="right" valign="top"><p>当前状态：</p></td>
    <td height="30" colspan="2" valign="top">
       <p> 
       <s:if test="app.status==0">待审核 </s:if>
       <s:if test="app.status==1">审核通过 </s:if>
       <s:if test="app.status==2">审核不通过  </s:if>
        </p>
    </td>
    </tr>
    <s:if test="app.status==2">
    <tr>
    <td height="30" align="right" valign="top"><p>审核不通过原因：</p></td>
    <td height="30" colspan="2" valign="top"><p>${app.reason}</p></td>
    </tr>
    <tr>
    <td height="30" align="right" valign="top"><p>审核时间：</p></td>
    <td height="30" colspan="2" valign="top"><p><s:date  name="app.reviewTime" format="yyyy-MM-dd HH:mm:ss"/></p></td>
    </tr>
    </s:if>

    <s:if test="app.status==1">
    <tr>
    <td height="30" align="right" valign="top"><p>审核时间：</p></td>
    <td height="30" colspan="2" valign="top"><p><s:date  name="app.reviewTime" format="yyyy-MM-dd HH:mm:ss"/></p></td>
    </tr>
    </s:if>
  <tr>
    <td height="20" align="right">&nbsp;</td>
    <td height="20"><p>
     <form action="${storeDomain}/web/app!appList.action" >
      <input type="submit" name="button" id="button" value="返 回"  class="subminbt"/>
      </form>
    </p>  </td>
    <td height="20">&nbsp;</td>
  </tr>
</table>

  </div>
</div>
	
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html>