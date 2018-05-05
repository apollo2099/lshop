<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<ul class="title" id="productComment">
	<li class="title_s">Buyer</li>
	<li class="title_s">Transaction Details</li>
	<li>Comments</li>
</ul>
<c:if test="${not empty objList}">
<c:foreach items="${objList}" var="obj">
<ul class="mytest">
	<li class="short">${obj[1].nickname }<br /><img src="${resDomain }/tvpaden${obj[0] }" /></li>
	<li class="short">${obj[1].pnum }<br /><fmt:formatDate value="${obj[1].createTime }"  type="both" /></li>
	<li>
		<c:foreach begin="1" end="${obj[1].score}">
			 <img src="${resDomain}/tvpaden/res/images/wjx.gif" width="30" height="29" />
		</c:foreach>
		<br />
	</li>
	<li class="cImages">
	${obj[1].content }<br/>
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
</c:if>
</c:if>