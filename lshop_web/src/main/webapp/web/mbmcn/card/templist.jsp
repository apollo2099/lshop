<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="template" id="template" style="display: none;">
	<div class="temClose" id="temClose">
		<img src="${resRoot}/${model.cssName}/images/close.png" />
	</div>
	<ul>
		<li><img src="${resRoot}/${model.cssName}/images/template.jpg" />
			<p>明月几时有，把酒问青天。</p></li>
			
		
		<s:iterator value="#request.tempList" status="stat" id="item">
			<li onclick="changeStyle('${item.cssName}');"><img src="${resRoot}/${item.cssName}/images/template.jpg" />
			<p>${item.title}</p></li>
		</s:iterator>
	</ul>
</div>