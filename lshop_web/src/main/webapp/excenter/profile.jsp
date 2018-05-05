<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/excenter/common/header.jsp" %>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.1.8.1.js"></script>
<script type="text/javascript" src="js/FomrValidate.js" ></script>
<script>

function checkEmailOne(){
	var email=$('#email');
	var eInfo=$('#emailInfo');
		 if(email.val()==""){
			 eInfo.html("请填写您常用的邮箱");
		 }else{
			 $.ajax({   
				 url: '/excenter/excenter!isExistsUser.action',
				 data:"lvUser.email="+$.trim(email.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					  eInfo.html("<font color='red'>该邮箱已存在！</font>");
				   }else if(num==0){
					    eInfo.html("请填写您常用的邮箱");
					   }
				 }   
				 });
		}
}
var msg="";
var  isuccessFalg=false;
$().ready(function() {
	$("#regedit").validate({
		submitHandler:function(form){
		  var email=$('#email');
	      var eInfo=$('#emailInfo');
	      var a7=$('#a7').val();
	      if(a7.length>200){
	       alert("推广优势填写内容不能大于200个字符");
	       $('#a7').focus();
	       return;
	      }
			 $.ajax({   
				 url: '/excenter/excenter!isExistsUser.action',
				 data:"lvUser.email="+$.trim(email.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					   eInfo.html("<font color='red'>该邮箱已存在！</font>");
					   return;
				   }else if(num==0){
					   isuccessFalg=true;  
					}
				 }   
			 });
    },
	 rules: {
		   "userp.realName": {
				required: true,
			},
			"userp.email": {
				required: true,
				email:true
			}
			,
			"userp.tel": {
				required: true
			}
			,
			"userp.adress": {
				required: true
			}
			,
			"userp.accountTypes": {
				required: true
			}
			,
			"userp.accountNumber": {
				required: true
			}
			,
			"userp.description": {
				required: true
			}
			
		},
		messages: {
			"userp.realName": {
				required: "请输入真实姓名",
				email:"请输入一个有效的email"
			},
		},
/* 设置信息提示DOM */  
errorPlacement: function(error, element) {       
            msg=element.next();
            msg.css('color','red'); 
} 
,
success: function(element){
    msg.css('color','');
}
});
});

function doRegeditAction(){
if(true==isuccessFalg){
document.myform.submit();
}
}

}
</script>
  <style type="text/css">
       .errorColor {    
           color: #;    
       }  
     
</style>
<!--顶部信息-->
<%@include file="/excenter/common/top.jsp" %>
<div class="clear_p"></div>
<!--banner部份-->
<jsp:include page="/excenter/common/menu.jsp">
<jsp:param value="profile" name="p" />
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
	  
		<form action="/excenter/excenter!edit" id="regedit" name="myform"   method="post">
			<ol>
				个人资料
			</ol>
			<ul>
				<li>
					<p class="pr_left">
						申请者姓名：
					</p>
					<p class="pr_right">
					<c:choose>
						<c:when test="${empty userp.realName}">
							<input type="text" name="userp.realName" value="${userp.realName}" id="a1"/> <span style="font-size:11px;">请填写真实姓名,姓名保存后将无法再次修改</span>
						</c:when>
						<c:otherwise>
							${userp.realName}
						</c:otherwise>
					</c:choose>
					</p>
				</li>
				<li>
					<p class="pr_left">
						Email：
					</p>
					<p class="pr_right">
						<input type="text" name="userp.email"  onblur="checkEmailOne()" value="${userp.email}" maxlength="40" id="email"/><span id="emailInfo" class="error" style="font-size:11px;">请填写您常用的邮箱</span>
					</p>
				</li>
				<li>
					<p class="pr_left">
						电话：
					</p>
					<p class="pr_right">
						<input type="text" name="userp.tel" value="${userp.tel}" maxlength="20" id="a3"/><span style="font-size:11px;">请填写您的手机号码或固定电话 </span>
					</p>
				</li>
				<li>
					<p class="pr_left">
						所在地区：
					</p>
					<p class="pr_right">
						<input type="text" name="userp.adress" value="${userp.adress}" id="a4"/><span style="font-size:11px;">请填写详细地址如：*国*省*城市*区*路*门号 </span>
					</p>
				</li>
				<li>
					<p class="pr_left">
						收款帐户：
					</p>
					<p class="pr_right">
						<select name="userp.accountTypes" id="a5">
							<s:if test="userp.accountTypes==1">
								<option value="1" selected="selected">PayPal</option>
								<option value="2">支付宝</option>
							</s:if>
							<s:if test="userp.accountTypes==2">
								<option value="2" selected="selected">支付宝</option>
							</s:if>
						</select>
					</p>
				</li>
				<li>
					<p class="pr_left">
						帐户：
					</p>
					<p class="pr_right">
						<input type="text" name="userp.accountNumber" id="accountNumber"
							size="30" value="${userp.accountNumber}" maxlength="60" id="a6"/>
						<span style="font-size:11px;">请认真填写帐户，这将作为您最终收款的帐户 </span>
					</p>
				</li>
				<li>
					<p class="pr_left">
						我的推广优势：
					</p>
					<p class="pr_right">
						<textarea rows="3" cols="20" name="userp.description" id="a7">${userp.description}</textarea><span style="font-size:11px;">请填写您的推广优势 </span>
					</p>
				</li>
				<li>
					<p class="pr_left">
						&nbsp;
					</p>
					<p class="pr_right">
						<input name="提交" type="submit"
							class="button_01" value="修改资料" style="cursor: pointer;" onclick="doRegeditAction();"/>
						<input name="提交" style="cursor: pointer;"
							onclick="location.href='/excenter/excenter!editpwd.action'"
							class="button_01" value="修改密码" />
					</p>
				</li>
			</ul>
		</form>
		   <script type="text/javascript">
	     if("true"=="${sessionScope.msg}"){
              alert('修改成功！');
		  }else if("false"=="${sessionScope.msg}"){
              alert('修改失败！'+"${sessionScope.message}");
		  }
	     </script>
	    <%session.setAttribute("msg","");%>
	</div>
	<div class="clear_p"></div>
    <!--说明文字-->
    <div class="note">
  	 提示：请准确填写个人资料，以便能与您及时取得联系，首次登录请修改登录密码。
   </div> 
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
<div style="text-align:center;"><br/>
   <script language="javascript" type="text/javascript" src="http://js.users.51.la/7189009.js"></script>
   <noscript><a href="http://www.51.la/?7189009" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/7189009.asp" style="border:none" /></a></noscript>	
</div>