<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
					<dt>所属商城：</dt>
					<dd>
						<cus:store param="storeName" shopFlag="${lvNetwork.storeId}"></cus:store>
					</dd>				  
				</dl>
				<dl>
					<dt>所属国家：</dt>
					<dd>
						${lvNetwork.country}
					</dd>				  
				</dl>
				<dl>
					<dt>所属城市：</dt>
					<dd>
						${lvNetwork.city}
					</dd>				  
				</dl>
				<dl>
					<dt>网点名称：</dt>
					<dd>
						${lvNetwork.channelName}
					</dd>				  
				</dl>
				<dl  style="height: 30px;">
					<dt>详细地址：</dt>
					<dd>
						${lvNetwork.address}
					</dd>				  
				</dl>
				<dl>
					<dt>负责人：</dt>
					<dd>
						${lvNetwork.responsiblePerson}
					</dd>				  
				</dl>
				<dl>
					<dt>语言：</dt>
					<dd>
					<c:if test="${lvNetwork.webLanguage=='cn'}">中文简体</c:if>
					<c:if test="${lvNetwork.webLanguage=='tw'}">中文繁体</c:if>
					<c:if test="${lvNetwork.webLanguage=='en'}">英文</c:if>
					<c:if test="${lvNetwork.webLanguage=='kn'}">韩文</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						${lvNetwork.orderValue}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<fmt:formatDate value="${lvNetwork.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						<fmt:formatDate value="${lvNetwork.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
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
