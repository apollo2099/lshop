<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ page import="com.lshop.common.util.HttpUtil"%><%@ page import="java.util.Map"%>
<%
String host = request.getParameter("host");
String bizline = request.getParameter("bizline");
String terminal = request.getParameter("terminal");
String lid = request.getParameter("lid");
//String url = "http://10.0.1.207:8289/api/qrloginstatus?lid="+lid+"&bizline=hd&terminal=1";
String url = "http://"+host+"/api/qrloginstatus?lid="+lid+"&bizline="+bizline+"&terminal="+terminal;
String xmlRes = HttpUtil.get(url);
try {
	Map<String,String> map = HttpUtil.xml2Map(xmlRes);
	String statu = map.get("return.result.status");
	String sid = map.get("return.sid");
	String json = "{'statu':" + statu + ",'sid':'" + sid + "'}";
	out.print(json);
} catch (Exception e) {
	e.printStackTrace();
}
%>
