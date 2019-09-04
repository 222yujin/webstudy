<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, tr, td {
	border: 1px solid black;
	border-collapse: collapse;
	width: 500px;
	height: 50px;
	text-align: center;
	font-weight: bold;
}

#title td {
	background-color: pink;
}
</style>
<meta charset="UTF-8">
<title>유진이 달력</title>
</head>
<body>

	<%
		//현재 년도 값
		Calendar nowCal = Calendar.getInstance();
		int nowYear = nowCal.get(Calendar.YEAR);
		int nowMonth = nowCal.get(Calendar.MONTH);

		//파라미터 값
		String paramYear = request.getParameter("year");
		String paramMonth = request.getParameter("month");

		/*    int year  = paramYear  != null ? Integer.parseInt(paramYear)  : nowYear;
		   int month = paramMonth != null ? Integer.parseInt(paramMonth) : nowMonth; */

		int year = 0;
		int month = 0;
		Calendar resultCal = Calendar.getInstance();

		if (paramYear != null && paramMonth != null) {
			year = Integer.parseInt(paramYear);
			month = Integer.parseInt(paramMonth);

			resultCal.set(year, month - 1, 1);
		} else {
			year = nowYear;
			month = nowMonth;

			resultCal.set(year, month, 1);
		}

		if (month > 12) {
			month = 1;
			year++;
		}
	%>

	<%!public String getTitle(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		return sdf.format(cal.getTime());
	}%>

	<form method="get">
		<input name="year" type="number" /> <input name="month" type="number"
			min="1" max="12" /> <input name="submit" type="submit" value="검색" />
	</form>
	<br>
	<br>



	<table>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/03/calender.jsp?year=<%=year%>&month=<%=month - 1%>">
					&lt;&lt; </a></td>
			<td colspan="5"><%=getTitle(resultCal)%></td>
			<td><a
				href="<%=request.getContextPath()%>/03/calender.jsp?year=<%=year%>&month=<%=month + 1%>">
					&gt;&gt; </a></td>
		</tr>
		<tr id="title">
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<%
			int count = 0;
			int outputDay = 1;

			int day = resultCal.get(resultCal.DAY_OF_MONTH);
			int week = resultCal.get(resultCal.DAY_OF_WEEK);
			int last = resultCal.getActualMaximum(resultCal.DATE);

			for (int w = 1; w < 6; w++) {
		%>
		<tr>
			<%
				for (int d = 1; d < 8; d++) {
						if (!(count >= week - 1)) {
			%>
			<td>♡</td>
			<%
				count++;
						} else {
							if (last >= outputDay) {
			%>
			<td><%=outputDay++%></td>
			<%
				} else {
			%>
			<td>♡</td>
			<%
				}
						}
					}
			%>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>