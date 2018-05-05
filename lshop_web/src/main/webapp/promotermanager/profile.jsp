<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/excenter/common/header.jsp" %>
<script>
function doEditAction(){
   var accountNumber=$.trim($('#accountNumber').val());
   if(accountNumber==''){
    alert('请认真填写帐户，这将作为您最终收款的帐户 ');
    $('#accountNumber').focus();
    return false;
   }
	return true;
}

</script>
<!--顶部信息-->
<%@include file="top.jsp" %>
<div class="clear_p"></div>
<!--banner部份-->
<jsp:include page="menu.jsp">
<jsp:param value="profile" name="p" />
</jsp:include>
<!--主要内容-->
<div class="main_conten3">
	<!--个人资料-->
	<div class="profile">
	  
		<form action="rankpromt!edit" id="myForm" onsubmit="return doEditAction()"  method="post">
		<ol><span>个人资料</span></ol>
		<ul>
			<li>
				<p class="pr_left">申请者姓名：</p>
				<p class="pr_right">${userp.realName}</p>
			</li>
			<li>
				<p class="pr_left">Email：</p>
				<p class="pr_right">${userp.email}</p>
			</li>
			<li>
				<p class="pr_left">电话：</p>
				<p class="pr_right">${userp.tel}</p>
			</li>
			<li>
				<p class="pr_left">所在地区：</p>
				<p class="pr_right">${userp.adress}</p>
			</li>
			<li>
				<p class="pr_left">收款帐户：</p>
				<p class="pr_right"><select name="userp.accountTypes">
				  <option value="1" <s:if test="userp.accountTypes==1">selected="selected"</s:if>>PayPal</option>
				  <option value="2" <s:if test="userp.accountTypes==2">selected="selected"</s:if>>支付宝</option>
				</select></p>
			</li>
			<li>
				<p class="pr_left">帐户：</p>
				<p class="pr_right"><input type="text" name="userp.accountNumber" id="accountNumber" size="30" value="${userp.accountNumber}" maxlength="60" /> <span style="font-size:11px;">请认真填写帐户，这将作为您最终收款的帐户 </span></p>
			</li>
			<li>
			<c:choose>
			    <c:when test="${userp.description==''||userp.description==null}">
			       <p class="pr_left">我的推广优势：</p>
				   <p class="pr_right">
				     <textarea rows="3" cols="20" name="userp.description"></textarea>
				   </p>
			    </c:when>
			    <c:otherwise>
			       <p class="pr_left">我的推广优势：</p>
			       <p class="pr_right" style="width:300px;word-wrap: break-word;">
			       		${userp.description}
			       </p>
			    </c:otherwise>
			 </c:choose>
			<!-- 
				<p class="pr_left">我的推广优势：</p>
				<p class="pr_right">${userp.description}</textarea></p>
			-->
			</li>
			<li>
				<p class="pr_left">&nbsp;</p>
				<p class="pr_right"><input name="提交" type="submit" onclick="javascript:return confirm('请核对收款账号?');" class="button_01" value="修改资料" style="cursor:pointer;"/> <input name="提交" style="cursor:pointer;" onclick="location.href='rankpromt!editpwd.action'" class="button_01" value="修改密码"/></p>
			</li>
		</ul>
		</form>
		   <script type="text/javascript">
	     if("true"=="${sessionScope.msg}"){
              alert('修改成功！');
		  }else if("false"=="${sessionScope.msg}"){
              alert('修改失败！');
		  }
	     </script>
	    <%session.setAttribute("msg","");%>
	</div>
	<div class="clear_p"></div>
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
