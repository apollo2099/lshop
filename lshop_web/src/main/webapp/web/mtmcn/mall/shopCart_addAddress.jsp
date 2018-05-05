<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_新增收货人信息</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/cartInfo.js"></script>
</head>

<body>
<header>
   <div class="top">
      <div class="title1">
        <h1>新增收货人信息</h1>
      </div>
   </div>
</header>

<article>
   <div class="fanhui">
      <div class="fanhui_inner">
         <a href="/web/shopCart!toOrderInfo.action?shopFlag=${shopFlag }">返回</a>
      </div> 
      
    
   </div>
   
</article>

<article>  
    <div class="fanhui_inner">
        <div class="fanhu_tip">
           提示：非中国地区请输入英文收货信息
        </div>
    </div> 

</article>

<article>
  <form id="address_form" action="/web/shopCart!addAddressMobile.action" method="post">
  <input type="hidden" name="shopFlag" value="${shopFlag }"/>
     <table width="94%" border="0" align="center">
  <tr>
    <td height="40" colspan="2" class="fon18">收货人:</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu"  value="请输入收货人姓名" name="lvAccountAddress.relName" defalt="请输入收货人姓名"/>
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="fon18">手 机：</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu" value="手机和固定电话必须填写其中一项" name="lvAccountAddress.mobile" defalt="手机和固定电话必须填写其中一项"/>
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="fon18">固定电话：</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu"  value="手机和固定电话必须填写其中一项" name="lvAccountAddress.tel" defalt="手机和固定电话必须填写其中一项"/>
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td width="52%" height="40" class="fon18">国家：</td>
    <td width="48%" height="40" class="fon18">洲/省：</td>
  </tr>
  <tr>
    <td height="60" valign="top">
       <div style="width:96%; position:relative">
          <span class="spanchose" onClick="contryChange(this)">请选择国家</span>
          <div class="country">
          	<input type="hidden" id="contryId" name="lvAccountAddress.contryId" value=""/>
         	 <input type="hidden" id="contrynameId" name="lvAccountAddress.contryName" value=""/>
            <ul>
              <c:foreach items="${contryList}" var="c">
              <li value="${c.id}">${c.nameen}</li>
              </c:foreach>
            </ul>
          </div>
           <div  class="inputd" style="position:static">
   
       <div class="tip">
          <em></em>
           <span class="errInfo">请选择国家</span>
          <i></i>
          <b></b>
         </div>
        </div>
       </div>
     </td>
    <td height="60" valign="top" style="position:relative;"> 
		<input type="hidden" id="test" />
       <div class="shose_2" id="provinceNameDiv">
           <input type="text"  class="inpu" id="provinceName" value="请输入洲/省" name="lvAccountAddress.provinceName" defalt="请输入洲/省"/>
       </div>
       <div  class="inputd" style="position:static">
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
        </div>
       </div>
     </td>
  </tr>
  
  <tr>
    <td height="40" colspan="2" class="fon18">县/市：</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu" value="请输入市/县" name="lvAccountAddress.cityName" defalt="请输入市/县"/>
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="fon18">街道地址：</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu" value="请输入详细街道地址" name="lvAccountAddress.adress" defalt="请输入详细街道地址"/>
         <div class="tip">
          <em></em>
          <span class="errInfo">请输入详细街道地址</span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="fon18">邮编：</td>
  </tr>
  <tr>
    <td height="60" colspan="2">
      <div  class="inputd">
         <input type="text"  class="inpu" value="请输入邮编" name="lvAccountAddress.postCode" defalt="请输入邮编"/>
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
     </td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="md"></td>
  </tr>
    <tr>
    <td colspan="2">
      <input type="submit" value="保存"  class="logins"/>
     </td>
  </tr>
</table>

  </form>
  
</article>

<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>

<script>
function showhide(obj){
    $(obj).next(".country").slideToggle();
	$(".country").find("ul").find("li").click(function(){
		 $(this).parents(".country").prev().text($(this).text());
		 $(obj).next(".country").hide();
		 $("#contryId").val($(this).attr("value"));
		 $("#contrynameId").val($(this).text());
	});
}

</script>
</body>
</html>
