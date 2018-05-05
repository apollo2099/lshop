<%@ page language="java" pageEncoding="utf-8"%>

<%

	if(request.getSession().getAttribute("USER") != null ) {
		response.sendRedirect("mainfream/index_index.jsp");
	}
    else {
		//response.sendRedirect("login.jsp");
		%>
		<script type="text/javascript">
		  parent.location.href = 'mainfream/login.jsp';
		</script>
		<%
    }
%>