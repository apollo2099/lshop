<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div class="pop_up_box" style="background-color: white;">
	<span class="box_l">Unbind<span class="box_r">
		<a href='javascript:void(0);' onclick='ymPrompt.doHandler("ok",undefined)'>
		<img src="${resDomain}/bmen/res/images/close.gif" border="0" /></a></span></span>
	<input type="hidden" name="code" id="code" value="${code }"/>
	<div class="remove_bingding">					    
		   <ul>
				<li class="password">
				  <p class="text"><font class="redfont">*</font> Password：</p>
				  <p><input type="password" id="password" name="password" /></p> 
				</li>
				<li class="prompt"><p class="er" id="pwdTips"></p></li>
		   </ul>
            <ul class="btn">
			<li class="wd1">&nbsp;</li>
			<li class="wd2">
				<input type="button" onclick="javascript:submitUnbinding();" value="Unbind" class="user_center_bt" />
				<input type="button" onclick='ymPrompt.doHandler("ok",undefined)' value="Cancel" class="user_center_bt03"  />
			</li>
		</ul>		
	</div>
</div>
