<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js">
	
</script>
<title>Insert title here</title>

</head>
<body>
	<%
		String data = (String) request.getAttribute("data");
	%>
	<table>
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>주소</th>
				<th>마일리지</th>
			</tr>

		</thead>
		<tbody id="listBody">

		</tbody>
	</table>
	<script>
	var listBody = $('#listBody');
		$.ajax({
			//data : "",
			dataType : "json",
			success : function(resp) {
				let trTags = [];
				$(resp).each(
						function(i, member) {
							let trTag=   $("<tr>").append(
						               $("<td>").text(member.mem_id),
						               $("<td>").text(member.mem_name),
						               $("<td>").text(member.mem_hp),
						               $("<td>").text(member.mem_mail),
						               $("<td>").text(member.mem_add1),
						               $("<td>").text(member.mem_mileage)

							).prop("id",member.mem_id);
							trTags.push(trTag);
						});
				listBody.html(trTags);
			},
			error : function(err) {
				console.log(err.status);
			}
		});
		
		listBody.on("click","tr",function(){
			console.log($(this).prop("id"));
		})
	</script>
</body>
</html>