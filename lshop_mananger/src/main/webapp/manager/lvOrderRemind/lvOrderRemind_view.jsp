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
						${lvOrderRemind.id  }
					</dd>				  
				</dl>
						<dl >
					<dt>用户名称：</dt>
					<dd>
				       ${lvAccount.nickname }
					</dd>				  
				</dl>
				<dl >
					<dt>订单编号：</dt>
					<dd>
				       ${lvOrderRemind.orderId}
					</dd>				  
				</dl>
				<dl >
					<dt>提醒次数：</dt>
					<dd>
				      ${lvOrderRemind.remindNum}
					</dd>				  
				</dl>
				<dl >
					<dt>提醒时间：</dt>
					<dd>
				       ${lvOrderRemind.remindTime}
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
