<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<input type="hidden" name="lvActivity.activityType" value="${lvActivity.activityType }" id="activityType">
                  <dl>
					<dt>产品名称：</dt>
					<dd>
				       <s:iterator value="#request.productList" id="product">
						  <c:if test="${lvActivity.productCode==product.code }">${product.productName }</c:if>
					   </s:iterator>
				
					</dd>				  
				</dl>
				<dl >
				<dt>活动类型：</dt>
					<dd>
                     <c:if test="${lvActivity.activityType==1 }">限时打折</c:if>
                     <c:if test="${lvActivity.activityType==2 }">限量打折</c:if>
					</dd>				  
				</dl>
				
	
				<dl>
				<dt>活动标题：</dt>
					<dd>
                      ${lvActivity.activityTitle }
					</dd>				  
				</dl>

				<dl>
				<dt>活动价格：</dt>
					<dd>
                       ${lvActivity.activityPrice }
					</dd>				  
				</dl>

				<dl>
				<dt>排序值：</dt>
					<dd>
                      ${lvActivity.sortId }
					</dd>				  
				</dl>
				
				<div style="display:inline" id="limitNum">
				<dl class="nowrap">
				<dt>库存数量：</dt>
					<dd>
                       ${lvActivity.counts }
					</dd>				  
				</dl>
				</div>
				
				<div style="display:none" id="limitTime">
				<dl>
				
				<dt>活动开始时间：</dt>
					<dd>
                       <s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                       <s:date name="lvActivity.endTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				</div>
				
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvActivity.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改人：</dt>
					<dd>
						${lvActivity.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改时间：</dt>
					<dd>
						<s:date name="lvActivity.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
<script type="text/javascript">
<!--
$(document).ready(function(){
	var type=$("#activityType").val();
	if(type==1){
	  $("#limitTime").show();
	  $("#limitNum").hide(); 
	}else if(type==2){
	  $("#limitNum").show();
	  $("#limitTime").hide();
	}


})
//-->
</script>
