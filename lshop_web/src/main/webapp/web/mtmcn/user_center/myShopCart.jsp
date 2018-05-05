<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<c:set var="MallPath" value="" />
<c:set var="ShopPath" value="" />
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta content="telephone=no" name="format-detection" />
<link href="${MallPath}${resDomain}/mtmcn/res/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/FomrValidate.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/header.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/hammer.js"></script>
<script type="text/javascript" src="${MallPath}${resDomain}/mtmcn/res/js/cart.js"></script>
<title>TVpad商城_购物车</title>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>
	<!-- 判断购物车是否为空 -->
<c:if test="${not empty objList}">
<article>
   <div class="mycart">
   <c:foreach items="${objList}" var="ob" varStatus="outStatus">
       <div class="settleaccounts">
           <div class="settleaccounts_inner">
              <div class="settleaccounts_inner_left">
                 <h2>${ob[0].name }</h2> 
                 <input type="checkbox" value="" name="box${outStatus.count }" onclick="checkBox('${outStatus.count }',this);">
                 <span>商品总金额：</span><i>USD <font id="allAmount${outStatus.count }">${ob[2] }</font></i>
              </div>
              <div class="settleaccounts_inner_right">
                <a href="javascript:showInfo('${ob[0].storeFlag }','${outStatus.count }');">去结算</a>
              </div>
              <div class="clear"></div>
           </div>
       </div>
       
       <div class="cartlist">
       <c:foreach items="${ob[1]}" var="obj" varStatus="status"> 
          <ul>
              <li>
                 <div class="imgchek">
                   <table width="100%" border="0">
                        <tr>
                            <td width="21%"><input name="inBox${outStatus.count }" value="${obj[0].code}" type="checkbox" onclick="checkInBox('${outStatus.count }',this);"></td>
                            <td width="79%" align="center" valign="middle">
                              <a href="http://${ob[0].domainName }/web/product!toProduct.action?pcode=${obj[1].code}">
                                <img width="90%" src="${obj[1].pimage }">
                              </a>
                            </td>
                        </tr>
                   </table>

                 </div>
                 <div class="totalconts">
                    <a href="http://${ob[0].domainName }/web/product!toProduct.action?pcode=${obj[1].code}">
                     <div class="imgname"><p>${obj[1].productName }</p></div>
                   </a>
                   
                    <div>
                       <table width="100%" border="0">
                         <tr>
                           <td width="48%" valign="middle" class="imgprcont"><span class="imgjiag">USD <font id="_${outStatus.count }oPrice${status.count }">${obj[0].shopPrice }</font></span> X</td>
                           <td width="52%" class="spanblock">
                            <span class="cut" onclick="changeNum('del','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"></span>
                            <span class="zhi" id="_${outStatus.count }num${status.count }">${obj[0].shopNum }</span>
                            <span class="add" onclick="changeNum('add','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"></span>
                            <span id="_${outStatus.count }price${status.count }" style="display:none">${obj[2] }</span>
                           </td>
                         </tr>
                         
                      </table>

                    </div>
                    <div class="imgjiag"></div>
                 </div>  
                 <div class="clear"></div> 
              </li>         
           </ul>
          </c:foreach>
        </div>
        </c:foreach>
       <div class="delete">
          <a href="javascript:deleteAllCheckData('/web/shopCart!delManyCart.action');" id="dele">删除选中项</a>
       </div>
       
        
   </div>
</article>
</c:if>

<c:if test="${empty objList}">
<article>
  <div class="shnullbox">
    <div class="shopimg">
       
    </div>
    <div class="tisopn">
      您的购物车还是空的，快去选购吧！
    </div>
  </div>
  
  <div class="goshop">
    <a href="${MallPath}/">去逛逛</a>
  </div> 
   
</article>
</c:if>

	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
		document.getElementById('c_title').innerHTML = '购物车';
	</script>
</body>
</html>
