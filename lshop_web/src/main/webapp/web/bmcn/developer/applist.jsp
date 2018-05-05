<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
	
<div style="width:1000px; margin:0 auto">
  <div class="posit">
      <h2 class="bt3" style="background-color:#fff">
	    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > 我的应用 </p>
	  </h2>
   </div>  
</div>
  <s:if test="page.list!=null&&page.list.size>0">
<div class="application3">
  <div class="addappla">
    <a href="${storeDomain}/web/app!toadd.action">添加应用</a>
  </div>
  <div class="clear"></div>
   <div class="appli_list">
		    
			<table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#e3e2e2">
              <tbody><tr>
               <td width="24%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>应用名称</p></td>
               <td width="22%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>提交时间</p></td>
               <td width="21%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>审核时间</p></td>
               <td width="17%" valign="middle" height="30" bgcolor="#F3F7F7" align="center"><p>当前状态</p></td>
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
	                  <s:if test="status==0">待审核</s:if>
	                  <s:if test="status==1">审核通过</s:if>
	                  <s:if test="status==2">审核不通过</s:if>
	                  <s:if test="status==3">待完善信息</s:if>
	                  </p>
	               </td>
	               <td width="16%" valign="middle" height="30" bgcolor="#FFFFFF" align="center">
	                  <p>
	                   <s:if test="status==3"> <a href="${storeDomain}/web/app!toEditInfo.action?code=${item.code}">完善应用信息</a></s:if>
	                   <s:else> <a href="${storeDomain}/web/app!info.action?code=${item.code}">查看</a></s:else>
	                  </p>                 
	                <font class="bluefont"><a href="#"></a></font></td>
	              </tr>
              </s:iterator>
            </tbody></table>
            
				  <u:newPage href="/web/app!appList.action?page.pageNum=@"></u:newPage>
				  <script type="text/javascript">
						function toPage(){
							var pageValue=$.trim($("#pageValue").val());
						   if(pageValue!=""&&/^[0-9]*$/.test(pageValue)){
						   window.location.href="/web/app!appList.action?page.pageNum="+pageValue;
						   }
						}
				  </script>
            
            
<%-- 			<div class="fenye">
            <form method="get" action="">
              <ul class="fy">
                <li class="sf"><a href="#">&lt;</a></li>
                <li class="fy_dq"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">6</a></li>
                <li>…</li>
                <li><a href="#">35</a></li>
                <li class="sf"><a href="#">&gt;</a></li>
                &nbsp;
              </ul>
            </form>
          </div> --%>
          <div class="clear"></div>
		  </div>
</div>
	</s:if>
<s:else>
<div class="application1" style="margin-top:10px">
   
   <div class="apl_title">  
      <div class="add_app_ruzu">
    您还没有添加任何应用，<a href="${storeDomain}/web/app!toadd.action" style="color:#00F">点击添加</a> </div>
      
   </div> 

</div>
</s:else>
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html>