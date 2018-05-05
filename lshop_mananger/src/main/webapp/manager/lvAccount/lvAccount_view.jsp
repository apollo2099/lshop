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
					<dt>登录账户：</dt>
					<dd>
						${unifiedUser.account}
					</dd>				  
				</dl>
				<dl >
					<dt>昵称：</dt>
					<dd>
				     ${unifiedUser.nickname}
					</dd>				  
				</dl>
				<dl >
					<dt>性别：</dt>
					<dd>
						<c:choose>
							<c:when test="${unifiedUser.sex eq '0'}">男</c:when>
							<c:when test="${unifiedUser.sex eq '1'}">女</c:when>
						</c:choose>
					</dd>				  
				</dl>
				<dl >
					<dt>QQ：</dt>
					<dd>
				     ${unifiedUser.qq}
					</dd>				  
				</dl>
				<dl >
					<dt>MSN：</dt>
					<dd>
				     ${unifiedUser.msn}
					</dd>				  
				</dl>
			    <dl >
					<dt>真实姓名：</dt>
					<dd>
				     ${unifiedUser.name}
					</dd>				  
				</dl>
				<dl >
					<dt>电话：</dt>
					<dd>
				     ${unifiedUser.tel}
					</dd>				  
				</dl>
				<dl >
					<dt>手机：</dt>
					<dd>
				     ${unifiedUser.phone}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>地址：</dt>
					<dd>
				     ${unifiedUser.country}&nbsp;${unifiedUser.province}&nbsp;${unifiedUser.city}&nbsp;${unifiedUser.address}
					</dd>				  
				</dl>
				<dl > 
					<dt>用户状态：</dt>
					<dd>
						<c:choose>
							<c:when test="${unifiedUser.status eq '0'}">停用</c:when>
							<c:when test="${unifiedUser.status eq '1'}">启用</c:when>
						</c:choose>
					</dd>				  
				</dl>
				<dl > 
					<dt>最后登录时间：</dt>
					<dd>
						<fmt:formatDate value="${unifiedUser.loginTimeDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
				</dl>
				<dl >
					<dt>创建时间：</dt>
					<dd>
					<fmt:formatDate value="${unifiedUser.createTimeDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
				</dl>
				<dl >
					<dt>修改人：</dt>
					<dd>
					${lvAccount.modifyUserName}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
					<fmt:formatDate value="${unifiedUser.updateTimeDate}" pattern="yyyy-MM-dd HH:mm:ss" />
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
