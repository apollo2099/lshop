<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/excenter/common/header.jsp" %>
<script type="text/javascript">
$().ready(function() {
	$("#myform").validate({
		rules: {
			pwd: {
				required: true,
				minlength: 6
				
			},
	        newPwd:{
		     required: true,
		     minlength: 6,
		     maxlength: 16
            }
            ,truePwd:{
		     required: true,
		     minlength: 6,
		     equalTo: "#newPwd"
            }
			
		},
		messages: {
			pwd: {
				required: "请输入原密码！",
				minlength: "密码不能少于6位字符！"
			},
			newPwd: {
				required: "请输入新密码！",
				minlength: "密码不能少于6位！",
				maxlength: "密码不能大于16位字符！"
			},
			truePwd: {
				required: "再输入一次上面的密码！",
				minlength: "密码不能少于6位！"
				,equalTo: "新密码确认与新密码不一样！"
			}
		}
	});
});
</script>
   <style type="text/css">
       label.error {    
           color: red;    
       }   
</style>
<!--顶部信息-->
<%@include file="/excenter/common/top.jsp"%>
<div class="clear_p"></div>
<!--banner部份-->
<jsp:include page="/excenter/common/menu.jsp">
<jsp:param value="profile" name="p"/>
</jsp:include>
<!--主要内容-->
<div class="main_conten3">
	<!--我的推广码-->
	<div class="check">
		 <c:if test="${userType==0}">
	    <ul>
		   <c:if test="${presentRate=='1'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP1</span>(已结算台数1-10台)：每台返 15美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP2 </span>(已结算台数11-50台)  每台返20美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='2'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP2</span>(已结算台数11-50台)：每台返 20美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP3 </span>(已结算台数51-200台)  每台返25美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='3'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP3</span>(已结算台数51-200台)：每台返 25美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP4 </span>(已结算台数201-1000台)  每台返30美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='4'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP4</span>(已结算台数201-1000台)：每台返 30美金 </li>
			<li>下一级<span class="f_orange f_B"> VIP5 </span>(已结算台数1001-台)  每台返35美金 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad，还差<span class="f_blue f_B"> ${rateNum} </span>台即可升级 </li>	
		  </c:if>	
		  <c:if test="${presentRate=='5'}">
			<li class="f_B">您现在的级别: </li>
			<li><span class="f_orange f_B">VIP5</span>(已结算台数1001-台)：每台返 35美金 </li>
			<li><span class="f_orange f_B">  您已达到最高级别 </li>
			<li>您已经成功结算了<span class="f_orange f_B"> ${presentNum} </span>台TVpad </li>	
		  </c:if>	
		</ul>
		<ul>
			<li class="f_B">结算规则说明： </li>
			<li>假如结算时您是VIP1，还有 2 台即可升级，目前有 8 台佣金待结算。 </li>
			<li>那么您这次申请结算的实际金额为：</li>
			<li>2 * 15 + 6 * 20 = 150  </li>
		</ul>	
		</c:if>
		<c:if test="${userType==1}">
		  <ul>
		   <li>特殊合作伙伴：每台返<span class="f_orange">${specialAmount}</span>美金</li>
		   <li>推广码有效日期：${createTime}至${validitydate}</li>
		  </ul>
		</c:if>
	</div>
	<!--个人资料-->
	<div class="profile">
		<form action="/excenter/excenter!doEditpwd.action" id="myform" method="post">
		<ol>修改密码</ol>
		<ul>
		    <li>
		    <p class="pr_left"></p>
		    <p class="pr_right" style="color:red;">${msg}</p>
		    <p></p>
		    </li>
			<li>
				<p class="pr_left">原密码：</p>
				<p class="pr_right"><input type="password" name="pwd" size="30" maxlength="60" /></p>
			</li>
			<li>
				<p class="pr_left">新密码：</p>
				<p class="pr_right"><input type="password" name="newPwd" id="newPwd" size="30" maxlength="30" /></p>
			</li>
			<li>
				<p class="pr_left">新密码确认：</p>
				<p class="pr_right"><input type="password" name="truePwd" id="truePwd" size="30" maxlength="30" /></p>
			</li>	
			<li>			
				<p class="pr_left">&nbsp;</p>
				<p class="pr_right"><input name="提交" type="submit" class="button_01" value="修改密码" style="cursor:pointer;"/></p>
			</li>
		</ul>
		</form>
		<% session.setAttribute("msg",""); %>
	</div>
	<div class="clear_p"></div>
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>

