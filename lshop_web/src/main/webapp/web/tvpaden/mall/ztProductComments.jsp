<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad4 Reviews</title>
<link href="${resDomain}/tvpaden/res/css/reviewEn.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${resDomain}/en/res/js/jquery-1.4.4.min.js" ></script>
</head>

<body>
<div class="review_bananer">
	<img src="${resDomain}/tvpaden/res/images/review/huanxin_bananerEn.jpg" usemap="#Map" alt="TVpad以旧换新"/>
    <map name="Map" id="Map">
        <area shape="rect" coords="431,284,573,323" href="http://en.mtvpad.com/web/shopCart!toQuickOrder.action?shopFlag=${storeFlag }&productCode=${pcode }" target="_blank" />
    </map>
</div>
<div class="review_chanp">
	<img src="${resDomain}/tvpaden/res/images/review/huanxin_chanpEn.jpg"/>
</div>
<div class="review_review">
	<h1>Comments（${page.totalCount}）</h1>
    <div class="index_comments">
        <ul class="title">
          <li class="title_s">Buyer</li>
          <li class="title_s">Transaction Details</li>
          <li>Comments</li>
        </ul>
      <c:if test="${not empty objList}">
		<c:foreach items="${objList}" var="obj">  
        <ul>
          <li class="short">${obj[1].nickname }<br />
              <img src="${resDomain }/tvpadcn${obj[0] }" /></li>
          <li class="short">${obj[1].pnum } set<br />
            <fmt:formatDate value="${obj[1].createTime }"  type="both"/></li>
          <li><c:foreach begin="1" end="${obj[1].score}">
					 <img src="${resDomain}/tvpaden/res/images/wjx.gif" width="30" height="29" />
				</c:foreach><br />
		  </li>
		  <li>
		  ${obj[1].content }<br />
			 <c:if test="${not empty  obj[1].commentImages}">
			  <img src="${obj[1].commentImages }" width="200" height="150" />
			 </c:if>
		  </li>
        </ul>
        </c:foreach>
        
        
        

        <c:if test="${page.totalCount>page.numPerPage}">
		<ul class="page">
		All<span style="color:#f00">${page.totalCount}</span>records&nbsp;
		<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">Home</a></c:if><c:if test="${page.pageNum<=1}">Home</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">Pre</a></c:if><c:if test="${page.pageNum<=1}">Pre</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">Next</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Next</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">Last Page</a></c:if><c:if test="${page.totalPage<=page.pageNum}">Last Page</c:if></span>&nbsp;
		<span style="margin-left:10px">Page: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
		<span style="margin-left:10px"><span style="color:red">${fn:length(page.list)}</span>Per Page</span>&nbsp;
		<span style="margin-left:10px">Go to
		<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
		   <c:foreach var="i" begin="1" end="${page.totalPage}">
			 <option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>${i}</option>
		   </c:foreach>
		</select>
		</span>
		</ul>
		<script type="text/javascript">
		function gotoPage(num){
			//加载评论显示
			$.post("/web/product!toProductComments.action?pcode=${pcode}&page.pageNum="+num+"&pageMark=1"+"#index${propertyNum+1}", function(data){
			      $('.index_comments').empty().append(data);
			}, 'html');
			}
		</script>
		</c:if>
		
		
	  </c:if>
	  <c:if test="${empty objList}">
           <font color="red">暫無評價，歡迎您第一個留言！</font>
      </c:if>
      </div>
</div>
</body>
</html>
