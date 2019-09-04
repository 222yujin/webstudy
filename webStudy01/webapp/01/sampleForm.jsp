<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/sampleForm.jsp</title>
</head>
<body>
ServletContext : <%=request.getServletContext().hashCode()%>
<form action="/webStudy01/sampleProcess" method="get">
		<pre>
			<input type="number" name="numParam"/>
			<input type="text" name="textParam"/>
			<input type="radio" name="radioParam" value="radioON"/>ON
			<input type="radio" name="radioParam" value="radioOFF"/>OFF
			<input type="checkbox" name="checkboxParam" value="checkA"/>
			<input type="checkbox" name="checkboxParam" value="checkB"/>
			<input type="checkbox" name="checkboxParam" value="checkC"/>
			<select name="selectParam1">
				<option>combo1</option>
				<option>combo2</option>
				<option>combo3</option>
			</select>
			
			<select name="selectParam2" multiple="multiple">
				<option>listbox1</option>
				<option>listbox2</option>
				<option>listbox3</option>
			</select>
			<button type="submit">전송</button>
			
			
		</pre>

</form>

</body>
</html>