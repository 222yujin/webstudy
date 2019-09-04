<%@page import="java.io.IOException"%>
<%@page import="javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/gugudan.jsp</title>
</head>
<style>
.blue{
	background-color : blue;
}
</style>
<body>
	<h4>구구단</h4>
	<!-- 2~9단까지의 구구단을 table 태그로 출력
		3단의 배경색은 파란색 -->
	<%!
	private String gugudan(int i, int j) {
		return String.format("%d*%d=%d", i, j, i * j);
	}

	private StringBuffer tonggugudan(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException {
		StringBuffer table = new StringBuffer();
		String id = "";
		String checkmin=req.getParameter("minDan");
		String checkmax=req.getParameter("maxDan");
		int min=0;
		int max=0;
		
		if (checkmin == null || !checkmin.matches("\\d+") || checkmax == null || !checkmax.matches("\\d+")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		min = new Integer(checkmin);
		max = new Integer(checkmax);

		table.append("<table>");
		for (int i = min; i <= max; i++) {
			if (i == 3) {
				id = "blue";
			} else {
				id = "normal";
			}
			table.append("<tr class="+id+">");
			for (int j = 1; j < 10; j++) {
				table.append("<td>" + gugudan(i, j) + "</td>");
			}
			table.append("</tr>");
		}
		table.append("</table");
		return table;
	}%>
	
	<table>
		<%
			String checkmin=request.getParameter("minDan");
			String checkmax=request.getParameter("maxDan");
			int min=0;
			int max=0;
			
			if (checkmin == null || !checkmin.matches("\\d+") || checkmax == null || !checkmax.matches("\\d+")) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			min = new Integer(checkmin);
			max = new Integer(checkmax);

			String id = "";
			for (int i = min; i <= max; i++) {
				if (i == 3) {
					id = "blue";
				} else {
					id = "normal";
				}
		%>
		<tr class="<%=id %>">
			<% 
		for(int j=1; j<10; j++){
			
			%>
			<td><%=gugudan(i,j)%></td>
			<% 
		}
		%>

		</tr>
		<% 
	}
%>
	</table>
	<%= tonggugudan(request,response) %>
</body>
</html>