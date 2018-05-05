<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<style>
.viewInfo dl{
width: 400px;
margin-left: 0px;
}
.viewInfo dl dt{
width: 100px;
}
.viewInfo dl dd{
width: 260px;
}
</style>

<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" style="border-top-width: 0px;">
				<dl >
					<dt>账号：</dt>
					<dd>
						${developer.email }
					</dd>				  
				</dl>
				<dl >
					<dt>账号类型：</dt>
					<dd>
				     	<c:if test="${developer.dtype==0 }">个人开发者</c:if>
						<c:if test="${developer.dtype==1 }">企业开发者</c:if>
					</dd>				  
				</dl>
				
				<dl >
					<dt>状态：</dt>
					<dd>
						<c:if test="${developer.dstatus==0 }">待审核</c:if>
						<c:if test="${developer.dstatus==1 }">审核通过</c:if>
						<c:if test="${developer.dstatus==2 }">不通过</c:if>
					</dd>				  
				</dl>
				<dl >
					<dt>创建时间：</dt>
					<dd>
						<fmt:formatDate value="${developer.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				
				<dl >
					<dt>审核时间：</dt>
					<dd>
				    	<fmt:formatDate value="${developer.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl >
					<dt>是否同意协议：</dt>
					<dd>
				    	<c:if test="${developer.isAgree==0 }">不同意</c:if>
						<c:if test="${developer.isAgree==1 }">同意</c:if>
					</dd>				  
				</dl>
				
				<dl >	
					<c:if test="${developer.dstatus==2 }">
					<dt>审核不通过原因：</dt>
					<dd style="border-bottom: 0px;">
				    	<textarea name="developer.reason" maxlength="200"
								style="height: 45px; width: 260px;">${developer.reason}</textarea>
					</dd>
					</c:if>		  
				</dl>
				<dl >		  
				</dl>
<!-- 
				<dl style="height: 100px;">
					<dt>协议截图：</dt>
					<dd style="border-bottom: 0px;">
						<a href="${developer.agreeImgsUrl}"
							title="点击查看原图" target="_blank"> <img alt=""
							src="${developer.agreeImgsUrl}"
							width="200px" height="100px">
						</a>
					</dd>
				</dl>
				<dl style="height: 100px;">			  
				</dl>
				 -->
				<dl ></dl>
				<dl ></dl>
				
				<c:if test="${developer.dtype==0 }">
					<dl style="height: 50;">
						<dt>真实姓名：</dt>
						<dd style="border-bottom: 0px;"><textarea name="" maxlength=""
								style="height: 40px; width: 260px;">${developer.contactName}</textarea></dd>
					</dl>
					<dl style="height: 50;">
						<dt>联系电话：</dt>
						<dd>${developer.tel}</dd>
					</dl>
					<dl >
						<dt>身份证号码：</dt>
						<dd>
							${developer.idNum}
						</dd>
					</dl>
					<dl >
					</dl>
					<dl>
						<dt>身份证扫描件：</dt>
						<dd style="border-bottom: 0px;">
							<a href="/manager/devMngAction!idimg.action?developer.id=${developer.id}" title="点击查看原图" target="_blank">
							<img class="" width="200px" height="100px"
							src="/manager/devMngAction!idimg.action?developer.id=${developer.id}" 
							alt="">
							</a>
						</dd>
					</dl>
				</c:if>

				<c:if test="${developer.dtype==1 }">
					<dl style="height: 50;">
						<dt>公司名称：</dt>
						<dd style="border-bottom: 0px;"><textarea name="" maxlength=""
								style="height: 40px; width: 260px;">${developer.company}</textarea></dd>

					</dl>
					<dl style="height: 50;">
						<dt>联系人姓名：</dt>
						<dd style="border-bottom: 0px;">
							<textarea name="" maxlength=""
								style="height: 40px; width: 260px;">${developer.contactName}</textarea>
						</dd>
					</dl>
					<dl>
						<dt>联系电话：</dt>
						<dd>${developer.tel}</dd>
					</dl>
					<dl>
					</dl>
					<dl style="width: 800px;height: 50;">
						<dt>公司地址：</dt>
						<dd style="border-bottom: 0px;">
							<textarea name="" maxlength=""
								style="height: 45px; width: 620px;">${developer.address}</textarea>
						</dd>
					</dl>
					<dl>
						<dt>营业执照注册号：</dt>
						<dd>${developer.idNum}</dd>
					</dl>
					<dl>
					</dl>
					<dl>
						<dt>营业执照扫描件：</dt>
						<dd style="border-bottom: 0px;">
							<a href="/manager/devMngAction!idimg.action?developer.id=${developer.id}" title="点击查看原图" target="_blank">
							<img class="" width="200px" height="100px"
							src="/manager/devMngAction!idimg.action?developer.id=${developer.id}" 
							alt="">
							</a>
						</dd>
					</dl>
				</c:if>
			</div>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
/**
$(function(){
	$.ajax({
		type:"POST", 
		async:false,
		url:"/manager/devMngAction!idimg.action?developer.id=${developer.id }",
		cache: false, 
		dataType:"jsp",
		success:function(data){            
			$("#idImg").html(data);
                 }
	});
});*/
</script>
