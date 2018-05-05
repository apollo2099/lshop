<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>business_cooperation</title>
<%@include file="/web/bmen/common/top.jsp" %>
<link href="${resDomain}/bmen/res/css/business_c.css" rel="stylesheet" type="text/css" />
<style  type="text/css">
.fr_img{position:absolute;lift:0px;}
.fr_imgSmall{}
.fr_imgBig{position:absolute;top:0px;right:30px;display:none;}
</style>
	</head>
<body>
<% request.setAttribute("navFlag","bmen_merchants"); %>
<%@include file="/web/bmcn/common/header.jsp" %>
<script type="text/javascript">
window.onload=function(){
    var imgObj=document.getElementById("fr_imgObj"),top=window.screen.availHeight/4,bodyObj=document.body,htmlObj=document.documentElement,imgTop=imgObj.style,scrolltop;
	imgTop.top=top+"px";
	var imgScroll=function(){
		scrollTop=bodyObj.scrollTop||htmlObj.scrollTop;
		imgTop.top=top+scrollTop+"px";
	}
	window.onscroll=imgScroll;
}



</script>
<div class="fr_img" id="fr_imgObj">
			     <div class="fr_imgSmall" id="fr_imgSmall">
			     <a href="/web/bmen/business/ApplicationForm.jsp" target="_blank">
                <img src="${resDomain}/bmen/res/images/business/application.jpg" />
                </a>
            </div>
</div>
<!--top-->
<div class="business_c_header">
	<div class="business_c_header_t"><img src="${resDomain}/bmen/res/images/business/business_cooperation_01.jpg" width="1002" height="498" border="0" usemap="#Map" />
      <map name="Map" id="Map">
        <area shape="rect" coords="774,359,949,419" href="/web/bmen/business/ApplicationForm.jsp" target="_blank" />
      </map>
	</div>
</div>
<!--END top-->

<!--position-->
<div class="business_c_p">
	<div class="business_c_p_t"><img src="${resDomain}/bmen/res/images/business/pos.gif" width="22" height="17" /> Home
>Investment >banana TV</div>
</div>
<!--END position-->


<div class="business_c_banner"><img src="${resDomain}/bmen/res/images/business/business_cooperation_02.jpg" width="1002" height="79" /></div>
<div class="business_c_banner"><img src="${resDomain}/bmen/res/images/business/business_cooperation_03.jpg"  /></div>

<!--cooperation-->
<div class="business_c_d">
	<ul>
    	<li class="wd1">
    	  <p class="title"><img src="${resDomain}/bmen/res/images/business/ic.gif" width="23" height="29" />New Sunshine Industry</p>
    	 Technology is inadvertently making great progress in changing our lives in pretty much every aspect. Just witness the inevitable rise of Smart Home; we are confidently to predict that smart living room is certain tobe the future mainstream, which of course will bring chances for the development of Internet TV. According to industry estimates based on current market demands that the number of STB will reach up to 286 million in 2014 with an increase of 6% year-on-year 
    	 including 30 million consumed by domestic market which is about 3 times as great as that of 2013.
<br /><br />
      Launched in 2013, banana TV has brought its intelligence, richness and convenience to large number of users. With excellent performance, high-quality and customer-oriented contents, banana TV has made itself stand out from the rest and earned global recognition.<br /><br />
      With the rise of banana TV in this new industry, we believe it is certain to have a bright prospect in the near future.</li>
      <li class="wd2"><img src="${resDomain}/bmen/res/images/business/business_cooperation_04.jpg" /></li>
    </ul>
</div>

<div class="business_c_d">
	<ul>
    	<li class="wd2"><img src="${resDomain}/bmen/res/images/business/business_cooperation_05.jpg" /></li>
    	<li class="wd3">
    	  <p class="title"><img src="${resDomain}/bmen/res/images/business/ic.gif" width="23" height="29" />R &amp; D strength</p>
    	 The company headquarters in Shenzhen and has set up R &amp; D research centers in Shenzhen, Shanghai and Beijing. With more than 5 years of expertise in R &amp; D and market practices, the company has accumulated hundreds of international and 
    	 domestic patented technologies, which makes banana TV the leading STB in the industry. We believe that an excellent 
    	 product must be an outcome of accumulated technology and innovation, and that’s what that brings success to banana TV.</li>
        
    </ul>
</div>

<div class="business_c_d">
	<ul>
    	
    	<li class="wd3">
    	  <p class="title"><img src="${resDomain}/bmen/res/images/business/ic.gif" width="23" height="29" />Product strength</p>
    	  The market-oriented as well as customer-oriented banana TV aims at being the real customized and personalized smart STB in the industry. At present, we have launched the Kid Edition, 
    	  Overseas Chinese Edition, Domestic Edition and are preparing for more specially featured products to satisfy our customers’ needs. We believe that it’s the special characteristics of banana TV series products that construct its unique brand value.</li>
        <li class="wd2"><img src="${resDomain}/bmen/res/images/business/business_cooperation_06.jpg" /></li>
    </ul>
</div>

<div class="business_c_d">
	<ul>
    	<li class="wd2"><img src="${resDomain}/bmen/res/images/business/business_cooperation_07.jpg" /></li>
    	<li class="wd3">
    	  <p class="title"><img src="${resDomain}/bmen/res/images/business/ic.gif" width="23" height="29" />Huge profit</p>
    	 banana TV is well-reputed in the industry and among its 
    	 customers for its differentiated product forms and unique 
    	 marketing position. Its unlimited market potential makes it 
    	 possible for our partners to amass a fortune in a short time 
    	 and share the success of banana TV.<br /><br />
    	 In the future, our partners are encouraged to collect 
    	 customers’ needs and customize special products for their own customers according their preferences, and in this way, partners can help to increase the market share of banana TV and have a chance to be an independent STB operator. And meanwhile, banana TV is willing to provide exclusive media platform for our outstanding partners.</li>
        
    </ul>
</div>
<!--END cooperation-->

<!--support-->
<div class="business_c_banner"><img src="${resDomain}/bmen/res/images/business/business_cooperation_08.jpg"  /></div>
<div class="business_c_e">
  <p class="business_c_e_t">We provide our partner with powerful cooperative support to increase 
  their competitive strength and achieve win-win.</p>
<img src="/res/images/business/business_cooperation_09.jpg" width="1002" height="190" />
<ul>
          <li>
            <p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" /> Regional guarantee</p> Own exclusionary right within 
specific sales territory</li>
          <li class="wd1">
            <p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" /> Marketing support</p>
            Enjoy market promotion fund <br />
            and after-sales services</li>
          <li class="wd2">
            <p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" /> Ad support</p>
            Enjoy support on placing ad on 
            international and domestic media</li>
      </ul>
<img src="${resDomain}/bmen/res/images/business/business_cooperation_10.jpg" width="1002" height="190" />
<ul>
          <li>
            <p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" /> Profit guarantee</p>
            Enjoy competitive price policy to <br />
            guarantee high profit margins</li>
          <li class="wd1"><p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" />  Service support</p>
            Enjoy support on management capability 
            improvement, marketing promotion, 
          technical services, etc.</li>
          <li class="wd2">
            <p class="tt"><img src="${resDomain}/bmen/res/images/business/ic01.gif" width="10" height="11" /> Logistics support</p>
          Enjoy worldwide logistics services</li>
      </ul>

</div>
<!--END support-->

<!--requires-->
<div class="business_c_banner"><img src="${resDomain}/bmen/res/images/business/business_cooperation_11.jpg"  /></div>
<div class="business_c_f">
	<ul>
    	<li>Approve of King 
Banana’s marketing 
concept and have 
a good reputation.</li>
        <li>Have a strong<br />
          financial strength<br />
          andmarket <br />
        developing capability.</li>
        <li>Own complete and <br />
          stable distribution <br />
          channels and <br />
        marketing capability.</li>
        <li>Experienced in <br />
          promoting IT <br />
        electronics.</li>
        <li>Comply with the <br />
          service specifications <br />
          and sales policy made 
          by King Banana.<br />
        </li>
    </ul>
</div>
<!--END requires-->

<!--process-->
<div class="business_c_banner1"><img src="${resDomain}/bmen/res/images/business/business_cooperation_13.jpg"  /></div>
<div class="business_c_g">
<img src="${resDomain}/bmen/res/images/business/business_cooperation_14.jpg" width="1002" height="110" border="0" usemap="#Map2" />
<map name="Map2" id="Map2">
  <area shape="rect" coords="313,7,475,116" href="../../Application Form.html" target="_blank" />
</map>
	<ul>
        <li>Early contact </li>
        <li>Submit application</li>
        <li class="wd1">Verify qualification</li>
        <li class="wd2">Trial sale</li>
  </ul>
  <img src="${resDomain}/bmen/res/images/business/business_cooperation_15.jpg" width="1002" height="110" />
<ul>
        <li>Sign contract</li>
        <li>Start business</li>
        <li class="wd1">Marketing support</li>
  </ul>
</div>
<!--END process-->

<!--advertising-->
<div class="business_c_gg">
	<div class="business_c_gg_c"><img src="${resDomain}/bmen/res/images/business/business_cooperation_16.jpg"/></div>
</div>
<!--END advertising-->

<!--foot-->
<%@include file="/web/bmen/common/footer.jsp" %>

</body>
</html>
