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
					<dt>ID：</dt>
					<dd>
						${lvPayLogs.id  }
					</dd>				  
				</dl>
				<dl >
					<dt>订单编号：</dt>
					<dd>
				       ${lvPayLogs.oid}
					</dd>				  
				</dl>
				<dl >
					<dt>支付方式：</dt>
					<dd>
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(lvPayLogs.paymethod)"/>
					</dd>				  
				</dl>
				<dl >
					<dt>支付日期：</dt>
					<dd>
				       ${lvPayLogs.paydate}
					</dd>				  
				</dl>
				<dl >
					<dt>支付总金额：</dt>
					<dd>
				       ${lvPayLogs.amount }
					</dd>				  
				</dl>
				<dl >
					<dt>币种：</dt>
					<dd>
				       ${lvPayLogs.currency }
					</dd>				  
				</dl>
				<dl >
					<dt>状态：</dt>
					<dd>
				 <c:if test="${lvPayLogs.status==0}">未支付</c:if>
				 <c:if test="${lvPayLogs.status==1}">已支付未发货</c:if>
				 <c:if test="${lvPayLogs.status==2}">已发货未确认</c:if>
				 <c:if test="${lvPayLogs.status==3}">完成</c:if>
					</dd>				  
				</dl>
				<dl >
					<dt>第三方支付返回的订单号：</dt>
					<dd>
				       ${lvPayLogs.bankorderid }
					</dd>				  
				</dl>
		
				<dl >
					<dt>备注：</dt>
					<dd>
				       ${lvPayLogs.remark }
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
