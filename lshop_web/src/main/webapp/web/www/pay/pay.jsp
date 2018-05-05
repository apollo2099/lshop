<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script>
    var wpwlOptions = {
        style: "card"
    }
    </script>
    <script src="https://test.oppwa.com/v1/paymentWidgets.js?checkoutId=${checkoutId}"></script>
    <style>
        body { background-color:white; width:720px; margin:auto;padding:10px;font-size:14px;}
        h2 { margin-top:25px;margin-bottom:10px;padding:5px;width:100%;background-color:#eee;
        border:1px solid #ccc;border-radius:6px;font-size: 16px;font-weight:normal; }
    </style>
    
    <!-- 加载公共JS -->
<jsp:include page="/web/www/common/top.jsp"/>
  </head>
  
  <body>
  <jsp:include page="/web/www/common/header.jsp"/>
    <hr/>

<form action="http://www.itvpad.com/web/alipayInitial?checkoutId=${checkoutId }&oid=${lvOrder.oid}" class="paymentWidgets"><input type="text" id="cpFormBrands" value="VISA MASTER AMEX"></form>


    <%--
<div id="card_220921339056" class="wpwl-container wpwl-container-card">
    <form class="wpwl-form wpwl-form-card wpwl-clearfix" action="https://test.oppwa.com/v1/checkouts/{checkoutId}/payment" method="post" target="cnpIframe" lang="en">
        <div class="wpwl-group wpwl-group-brand wpwl-clearfix">
            <div class="wpwl-label wpwl-label-brand">Brand</div>
            <div class="wpwl-wrapper wpwl-wrapper-brand">
                <select class="wpwl-control wpwl-control-brand" name="paymentBrand">
                    <option value="MASTER">MasterCard</option>
                    <option value="VISA">Visa</option>
                </select>
            </div>
            <div class="wpwl-brand wpwl-brand-card wpwl-brand-MASTER"></div>
        </div>
        <div class="wpwl-group wpwl-group-cardNumber wpwl-clearfix">
            <div class="wpwl-label wpwl-label-cardNumber">Card Number</div>
            <div class="wpwl-wrapper wpwl-wrapper-cardNumber">
                <input autocomplete="off" type="tel" name="card.number" class="wpwl-control wpwl-control-cardNumber" placeholder="Card Number">
            </div>a
        </div>
        <div class="wpwl-group wpwl-group-expiry wpwl-clearfix">
            <div class="wpwl-label wpwl-label-expiry">Expiry Date</div>
            <div class="wpwl-wrapper wpwl-wrapper-expiry">
                <input autocomplete="off" type="tel" name="card.expiry"b class="wpwl-control wpwl-control-expiry" placeholder="MM / YY">
            </div>
        </div>
        <div class="wpwl-group wpwl-group-cardHolder wpwl-clearfix">
            <div class="wpwl-label wpwl-label-cardHolder">Card holder</div>
            <div class="wpwl-wrapper wpwl-wrapper-cardHolder">
                <input autocomplete="off" type="text" name="card.holder" class="wpwl-control wpwl-control-cardHolder" placeholder="Card holder">
            </div>
        </div>
        <div class="wpwl-group wpwl-group-cvv wpwl-clearfix">
            <div class="wpwl-label wpwl-label-cvv">CVV</div>
            <div class="wpwl-wrapper wpwl-wrapper-cvv">
                <input autocomplete="off" type="tel" name="card.cvv" class="wpwl-control wpwl-control-cvv" placeholder="CVV">
            </div>
        </div>
        <div class="wpwl-group wpwl-group-submit wpwl-clearfix">
            <div class="wpwl-wrapper wpwl-wrapper-submit">
                <button type="submit" name="pay" class="wpwl-button wpwl-button-pay">Pay now</button>
            </div>
        </div>
        <input type="hidden" name="shopperResultUrl" value="http://www.itvpad.com/index.html">
        <input type="hidden" name="card.expiryMonth" value="">
        <input type="hidden" name="card.expiryYear" value="">
    </form>
</div>
 --%>

<jsp:include page="/web/www/common/footer.jsp"/>

  </body>
</html>