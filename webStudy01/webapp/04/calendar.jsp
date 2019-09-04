<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>04/calender.jsp</title>
<script>
         function eventHandler(year, month){
            var form = document.calForm;
            if(year && month >=0){
               form.year.value = year;
               form.month.value = month;
            }
            form.submit();
         }
      </script>
<style>
/*  	td:nth-child(1){
      		color:red;
      	}
      	td:nth-child(7){
      		color:blue;
      	} */
</style>
</head>
<body>
	<%
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");
		String lang = request.getParameter("lang");

		Calendar cal = getInstance();
		if (yearStr != null && yearStr.matches("\\d{4}") && monthStr != null && monthStr.matches("\\d{1,2}")) {
			cal.set(YEAR, Integer.parseInt(yearStr));
			cal.set(MONTH, Integer.parseInt(monthStr));
		}

		int year = cal.get(YEAR);
		int month = cal.get(MONTH);
		int currentDate = cal.get(DAY_OF_MONTH);//1,2,3.. 요일

		//이전달
		cal.add(MONTH, -1);
		int beforeYear = cal.get(YEAR);
		int beforeMonth = cal.get(MONTH);

		//다음달
		cal.add(MONTH, +2);
		int nextYear = cal.get(YEAR);
		int nextMonth = cal.get(MONTH);

		//현재날짜
		cal.add(MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int date = cal.get(Calendar.DAY_OF_WEEK);//월,화
		int offset = date - 1;
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//마지막 날 30,31

		Locale[] locales = Locale.getAvailableLocales();
		Locale currentLocale = request.getLocale();
		if (StringUtils.isNotBlank(lang)) {
			currentLocale = Locale.forLanguageTag(lang);
		}
		DateFormatSymbols dfs = new DateFormatSymbols(currentLocale);
		String[] months = dfs.getMonths();
		String[] weekDays = dfs.getWeekdays();
	%>

	<h4><%=String.format("%d년 %d월", year, month + 1)%></h4>
	<!--href="" 이렇게 공백으로 넣어주면 현재  브라우저의 위치 -->

	<a href="#" onclick="eventHandler(<%=beforeYear%>,<%=beforeMonth%>);">이전
		달</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<form name="calForm" method="post">
		<input type="number" name="year" value="<%=year%>"
			onblur="eventHandler();" />년 <select name="month"
			onchange="eventHandler();">
			<%
				for (int idx = 0; idx < months.length - 1; idx++) {
			%>
			<option <%=idx == month ? "selected" : ""%> value="<%=idx%>"><%=months[idx]%></option>
			<%
				}
			%>
		</select> <select name="lang" onchange="eventHandler();">
			<%
				for (Locale tmp : locales) {
					if (StringUtils.isNotBlank(tmp.getDisplayLanguage())) {
			%>
			<option <%=tmp.equals(currentLocale) ? "selected" : ""%>
				value="<%=tmp.toLanguageTag()%>"><%=tmp.getDisplayLanguage(tmp)%></option>
			<%
				}
				}
			%>
		</select>
	</form>
	<a href="javascript:eventHandler(<%=nextYear%>,<%=nextMonth%>);">다음
		달</a>
	<table>
		<thead>
			<tr>
				<%
					for (String tmp : weekDays) {
						if (StringUtils.isNotBlank(tmp)) {
				%>
				<th><%=tmp%></th>
				<%
					}
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				int count = 1;
				for (int row = 0; row < 6; row++) {
					out.println("<tr>");
					for (int col = 0; col < 7; col++) {
						int display = count++ - offset;
						String displayStr = display + "";
						if (display < 1 || display > lastDay) {
							displayStr = "&nbsp;";
						}
						out.println(String.format("<td>%s</td>", displayStr));
					}
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
	<script>
     $('td,th').css({border:'1px solid black'});
     $('td:first-child').css({color:'red'});
     $('td:last-child').css({color:'blue'});
	 $('table').css({'width':'100%','height':'500px',borderCollapse:'collapse'});
	 
	
		 
	  var today = new Date();
	  
      if(today.getFullYear() == <%=year%> && today.getMonth()==<%=month%>){
        var date = today.getDate();
        var tds=$("td");
        
        for(var idx=0;idx<tds.length;idx++){
           if($(tds[idx]).text()==date){
              $(tds[idx]).css({
                 'background':'yellow'
              })
           }    
        }
      }
      

	</script>
</body>
</html>