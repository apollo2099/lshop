<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
				<dl >
					<dt>联系人编号：</dt>
					<dd>
						${lvAccountAddress.relCode}
					</dd>				  
				</dl>
				<dl >
					<dt>联系人名称：</dt>
					<dd>
				     ${lvAccountAddress.relName}
					</dd>				  
				</dl>
				<dl >
					<dt>邮编：</dt>
					<dd>
					${lvAccountAddress.postCode }
					</dd>				  
				</dl>
				<dl >
					<dt>电话：</dt>
					<dd>
				     ${lvAccountAddress.tel}
					</dd>				  
				</dl>
				<dl >
					<dt>手机：</dt>
					<dd>
				     ${lvAccountAddress.mobile}
					</dd>				  
				</dl>
			    <dl >
					<dt>地址：</dt>
					<dd>
				     	${lvAccountAddress.contryName}&nbsp;${lvAccountAddress.provinceName}&nbsp;${lvAccountAddress.cityName}&nbsp;${lvAccountAddress.adress}
					</dd>				  
				</dl>
				<dl >
					<dt>创建时间：</dt>
					<dd>
					<s:date name="lvAccountAddress.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl >
					<dt>修改人：</dt>
					<dd>
					${lvAccountAddress.modifyUserName}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
					<s:date name="lvAccountAddress.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
			
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
