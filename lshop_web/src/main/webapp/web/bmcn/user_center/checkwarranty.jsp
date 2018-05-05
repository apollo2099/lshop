<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div class="pop_up_box" style="background-color: white;">
	<span class="box_l">查看保修期<span class="box_r">
		<a href='javascript:void(0);' onclick='ymPrompt.doHandler("ok",undefined)'>
		<img src="${resDomain}/bmcn/res/images/close.gif" border="0" /></a></span></span>
	<div class="tips">
		<ul>
			<li>MAC：${name }</li>
			<li>保修期(Warranty)：未激活保修期(Not activated)</li>
			<li>&nbsp;</li>
		</ul>	
	</div>
</div>
