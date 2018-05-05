<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
	
<div style="width:1000px; margin:0 auto">
  <div class="posit">
      <h2 class="bt3" style="background-color:#fff">
	    <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首頁 </a> > 我的應用 </p>
	  </h2>
   </div>  
</div>

  <s:if test="page.list!=null&&page.list.size>0">
<div class="application3">
  <div class="addappla">
    <a href="${storeDomain}/web/app!toadd.action">添加應用</a>
  </div>
  <div class="clear"></div>
   <div class="appli_list">
			<table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#e3e2e2">
              <tbody><tr>
               <td width="24%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>應用名稱</p></td>
               <td width="22%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>提交時間</p></td>
               <td width="21%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>審核時間</p></td>
               <td width="17%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>當前狀態</p></td>
               <td width="16%" valign="middle" height="30" bgcolor="#F3F7F7" align="center">操作</td>
              </tr>
               <s:iterator value="page.list" id="item">
	              <tr>
	               <td width="24%" valign="middle" height="30" bgcolor="#FFFFFF" align="center">${item.name}</td>
	               <td width="22%" valign="middle" height="30" bgcolor="#FFFFFF" align="center">
	                  <s:date name="createTime" format="yyyy-MM-dd hh:mm" />
	               </td>
	               <td width="21%" valign="middle" height="30" bgcolor="#FFFFFF" align="center"> 
	                   <s:if test="status==1||status==2"><s:date name="reviewTime" format="yyyy-MM-dd HH:mm" /></s:if>
	                   <s:else>-</s:else>
	               </td>
	               <td width="17%" valign="middle" height="30" bgcolor="#FFFFFF" align="center">
	               <p class="star">
	                  <s:if test="status==0">待審核</s:if>
	                  <s:if test="status==1">審核通過</s:if>
	                  <s:if test="status==2">審核不通過</s:if>
	                  <s:if test="status==3">待完善信息</s:if>
	                  </p>
	               </td>
	               <td width="16%" valign="middle" height="30" bgcolor="#FFFFFF" align="center">
	                  <p>
	                   <s:if test="status==3"> <a href="${storeDomain}/web/app!toEditInfo.action?code=${item.code}">完善應用信息</a></s:if>
	                   <s:else> <a href="${storeDomain}/web/app!info.action?code=${item.code}">查看</a></s:else>
	                  </p>                 
	                <font class="bluefont"><a href="#"></a></font></td>
	              </tr>
              </s:iterator>
            </tbody></table>
            
				  <u:newPage href="/web/app!appList.action?page.pageNum=@" ></u:newPage>
				  <script type="text/javascript">
						function toPage(){
							var pageValue=$.trim($("#pageValue").val());
						   if(pageValue!=""&&/^[0-9]*$/.test(pageValue)){
						   window.location.href="/web/app!appList.action?page.pageNum="+pageValue;
						   }
						}
				  </script>
            
          <div class="clear"></div>
		  </div>
</div>
</s:if>
<s:else>
<div class="application1" style="margin-top:10px">
   
   <div class="apl_title">  
      <div class="add_app_ruzu">
    您還沒有添加任何應用，<a href="${storeDomain}/web/app!toadd.action" style="color:#00F">點擊添加</a> </div>
      
   </div> 

</div>
</s:else>
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>