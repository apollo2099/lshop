<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="page.list" id="c">
<div class="myconmmentsubdiv">
	<table width="94%" border="0" align="center" <s:if test="#request.action=='replylist'">class="myrecon"</s:if> >
		<tr>
			<td width="25%" height="20" align="left" valign="top">订&nbsp;&nbsp;单&nbsp;&nbsp;号：</td>
			<td width="75%" height="20" valign="top"
				style="text-decoration: underline; color: #0099ff"><s:property value="orderId"/></td>
		</tr>
		<tr>
			<td width="25%" height="20" align="left" valign="top">评论时间：</td>
			<td width="75%" height="20" valign="top"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
		</tr>
		<tr>
			<td width="25%" align="left" valign="top">评论内容：</td>
			<td width="75%" valign="top"><s:property value="content"/></td>
		</tr>
	</table>
	<s:if test="#request.action=='replylist'">
	<div class="logis_tip">
	<s:iterator value="#request.replyList" id="r">
	<s:if test="#r.replyId==#c.id">
	<p>
	 管理员回复：<br />
	<s:property value="#r.content"/>
	</p>
	</s:if>
	</s:iterator>
	</div>
	</s:if>
</div>
</s:iterator>