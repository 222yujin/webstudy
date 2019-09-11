<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/mystyle.css" />
<title>08/jdbcDesc.jsp</title>
<style>
table, tr, td, th {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	퍼사드 패턴 (Facade Pattern)
	<pre>
	<h4>JDBC (Java DataBase Connectivity)</h4>
		:Facade 패턴을 이용하여 데이터베이스와 자바 어플리케이션을 연결하는 방법 : Driver
		1. 벤더가 제공하는 driver classpath에 추가
		2. driver class 로딩(ClassLoader 사용)
		3. 로딩된 driver를 통해 Connection 연결(개방)
		4. Query 객체 생성
			1) Statement : Sql injection 취약(동적쿼리가 가능해서)
			2) PreparedStatement : 정적 쿼리 지향(쿼리 파라미터 활용)
			3) CallableStatement : 함수나 프로시저를 호출
		5. 쿼리 실행(SQL) : 
			1) ReseultSet executeQuery : select
			2) int executeUpdate : insert,update,delete (raw count 반환)
		6. 질의 결과 (raw data) 사용
		7. *** close
</pre>

	<%
		Map<String, Object> dataMap = (Map) request.getAttribute("dataMap");
		List<DataBasePropertyVO> list = (List) dataMap.get("list");
		String[] headers = (String[]) dataMap.get("headers");
	%>
	<table>
		<thead>
			<tr>
				<%
					for (String header : headers) {
				%>
				<th><%=header%></th>
				<%
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				for (DataBasePropertyVO vo : list) {
			%>
			<tr>
				<td><%=vo.getProperty_name()%></td>
				<td><%=vo.getProperty_value()%></td>
				<td><%=vo.getDescription()%></td>

			</tr>
			<%
				}
			%>
			<%
				if (list.isEmpty()) {
			%><tr>
				<td colspan="3">조회된 결과가 없음.</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>