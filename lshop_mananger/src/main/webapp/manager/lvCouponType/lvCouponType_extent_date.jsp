<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>

function show()
{
	alert(document.getElementById('oidEndTime').value.replace(/-/g, "/"));
//debugger;
var d1 = new Date(document.getElementById('oidEndTime').value.replace(/-/g, "/"));
var d2 = new Date(document.getElementById('newEndTime').value.replace(/-/g, "/"));      
alert("d1"+d1);
alert("d2"+d2);
var d3 = new Date($("#oidEndTime").value());
var d4 = new Date($("#newEndTime").value());
alert("d3"+d3+"d4"+d4);
if(d1>d2)
    {
        alert("竣工日期必须在开工日期之后!");
        return false;
    }    
if(d1=="NaN")
    alert("开工日期不能为空！");
else if(d2=="NaN")
    alert("竣工日期不能为空！");
else
    {
    s1 = new Date(d1);
    s2 = new Date(d2);
    
    var time= s2.getTime() - s1.getTime(); 
    var days = parseInt(time / (1000 * 60 * 60 * 24));
    alert(days);
    //var mytext=document.getElementById("Text1");
    //mytext.value = days;
    }
}

$(".okBut").click(function(){
	alert("++++");
	alert(document.getElementById('oidEndTime').value.replace(/-/g, "/"));
});
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponTypeAction!updateExtendDate.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCouponType.id" type="hidden" size="30" value="${lvCouponType.id}"/>
			<input name="lvCouponType.oldEndTime" type="hidden" value="${lvCouponType['endTime']}" id="oidEndTime"/>
			
				<!-- 生成表单 -->
				  <p>
		           <label>当前有效期</label>
				   <s:date name="lvCouponType.startTime" format="yyyy-MM-dd"/>至<s:date name="lvCouponType.endTime" format="yyyy-MM-dd"/>
				  </p>
		          <p>
				   <label>新结束时间</label>
						<input type="text"  class="required date" name="lvCouponType.endTime" format="yyyy-MM-dd" readonly="readonly" size="32" id="newEndTime"/>
				  </p>
				  <!--
				  <p>
				  <font color="red">
				  <label>延长有效期</label>
				  0天
				  </font>
				  </p>
				   -->
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>