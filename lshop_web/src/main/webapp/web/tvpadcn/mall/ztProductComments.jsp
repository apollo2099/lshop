<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad4用戶評價</title>
<link href="${resDomain}/tvpadcn/res/css/review.css" type="text/css" rel="stylesheet"/>
<!-- 加载公共JS -->
<script type="text/javascript" src="${resDomain}/www/res/js/jquery-1.4.4.min.js" ></script>
</head>

<body>
<div class="review_bananer">
	<img src="${resDomain}/tvpadcn/res/images/review/huanxin_bananer.jpg" usemap="#Map" alt="TVpad以旧换新"/>
    <map name="Map" id="Map">
        <area shape="rect" coords="421,272,563,310" href="http://www.mtvpad.com/web/shopCart!toQuickOrder.action?shopFlag=${storeFlag }&productCode=${pcode }" target="_blank" />
    </map>
</div>
<div class="review_chanp">
	<img src="${resDomain}/tvpadcn/res/images/review/huanxin_chanp.jpg"/>
</div>
<div class="review_review">
	<h1>用戶評價（${page.totalCount}）</h1>
    <div class="index_comments">
        <ul class="title">
          <li class="title_s">買 家</li>
          <li class="title_s">交易詳情</li>
          <li>評 價</li>
        </ul>
<c:if test="${not empty objList}">
		<c:foreach items="${objList}" var="obj">
        <ul>
          <li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpadcn${obj[0] }" /></li>
          <li class="short">${obj[1].pnum }台<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
          <li>
            	<c:foreach begin="1" end="${obj[1].score}">
					 <img src="${resDomain}/tvpadcn/res/images/wjx.gif" width="30" height="29" />
				</c:foreach>
          </li>
          
          <li>${obj[1].content }<br />
			 <c:if test="${not empty  obj[1].commentImages}">
			  <img src="${obj[1].commentImages }" width="200" height="150" />
			 </c:if>
          </li>
        </ul>
        </c:foreach>
        <!-- 评论分页部分 -->
        <c:if test="${page.totalCount>page.numPerPage}">
		<ul class="page">
		共有<span style="color:#f00">${page.totalCount}</span>條記錄&nbsp;
		<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage(1);">首頁</a></c:if><c:if test="${page.pageNum<=1}">首頁</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.pageNum>1}"><a href="javascript:gotoPage('${page.prePage}');">上一頁</a></c:if><c:if test="${page.pageNum<=1}">上一頁</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.nextPage}');">下一頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">下一頁</c:if></span>&nbsp;
		<span style="margin-left:10px"><c:if test="${page.totalPage>page.pageNum}"><a href="javascript:gotoPage('${page.totalPage}');">尾頁</a></c:if><c:if test="${page.totalPage<=page.pageNum}">尾頁</c:if></span>&nbsp;
		<span style="margin-left:10px">頁次: <span style="color:#f00">${page.pageNum}</span> / ${page.totalPage}</span>&nbsp;
		<span style="margin-left:10px">本頁有<span style="color:red">${fn:length(page.list)}</span>條記錄</span>&nbsp;
		<span style="margin-left:10px">跳轉到
		<select onchange='gotoPage(this.options[this.selectedIndex].value)'>
		<c:foreach var="i" begin="1" end="${page.totalPage}">
		<option  value="${i}" <c:if test='${i==page.pageNum}'>selected="selected"</c:if>>第${i}頁</option>
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
