<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:if test="${not empty activity}"><span>${activity.activityTitle}</span></c:if>