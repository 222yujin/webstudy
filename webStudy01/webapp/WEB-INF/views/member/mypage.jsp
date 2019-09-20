<%@page import="java.util.HashMap"%>
<%@page
	import="com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage.jsp</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style>
.error {
	color: red;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:useBean id="errors" class="java.util.HashMap" scope="session"></jsp:useBean>
	<%
		session.removeAttribute("errors");
		MemberVO savedMember = (MemberVO) request.getAttribute("savedMember");
		/* Map<String, String> errors = (Map) session.getAttribute("errors");
		if (errors == null)
			errors = new HashMap<>(); */
		String message = (String) session.getAttribute("message");
		if (StringUtils.isNotBlank(message)) {
			session.removeAttribute("message");
	%>
	<script type="text/javascript">
				alert("<%=message%>");
	</script>
	<%
		}
	%>
	<form action="<%=request.getContextPath()%>/member/memberUpdate.do"
		method="post">
		<table class="table table-bordered">
			<tr>
				<th>회원아이디</th>
				<td><input type="text" required class="form-control"
					name="mem_id" value="<%=savedMember.getMem_id()%>" /> <span
					class="error">${errors["mem_id"]}</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" required class="form-control"
					name="mem_pass" value="<%=savedMember.getMem_pass()%>" /> <span
					class="error" id="mem_pass"><%=errors.get("mem_pass")%></span></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" required class="form-control"
					name="mem_name" value="<%=savedMember.getMem_name()%>" /></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" class="form-control" name="mem_regno1"
					value="<%=savedMember.getMem_regno1()%>" /></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" class="form-control" name="mem_regno2"
					value="<%=savedMember.getMem_regno2()%>" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" class="form-control" name="mem_bir"
					value="<%=savedMember.getMem_bir()%>" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" required class="form-control"
					name="mem_zip" value="<%=savedMember.getMem_zip()%>" /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" required class="form-control"
					name="mem_add1" value="<%=savedMember.getMem_add1()%>" /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" required class="form-control"
					name="mem_add2" value="<%=savedMember.getMem_add2()%>" /></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" class="form-control" name="mem_hometel"
					value="<%=savedMember.getMem_hometel()%>" /></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" class="form-control" name="mem_comtel"
					value="<%=savedMember.getMem_comtel()%>" /></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" class="form-control" name="mem_hp"
					value="<%=savedMember.getMem_hp()%>" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" required class="form-control"
					name="mem_mail" value="<%=savedMember.getMem_mail()%>" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" class="form-control" name="mem_job"
					value="<%=savedMember.getMem_job()%>" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" class="form-control" name="mem_like"
					value="<%=savedMember.getMem_like()%>" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" class="form-control" name="mem_memorial"
					value="<%=savedMember.getMem_memorial()%>" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" class="form-control"
					name="mem_memorialday"
					value="<%=savedMember.getMem_memorialday()%>" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="mem_mileage" value="<%=savedMember.getMem_mileage()%>" /></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" class="form-control" name="mem_delete"
					value="<%=savedMember.getMem_delete()%>" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" class="btn btn-info"
					value="저장" /> <input type="reset" class="btn btn-info" value="취소" />
					<input type="button" class="btn btn-info" value="탈퇴"
					id="deleteBtn" /></td>
		</table>
	</form>
	<!-- <%=savedMember%> -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">비밀번호 입력</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
       	<form method="post" action="<%=request.getContextPath()%>/member/memberDelete.do">
      <div class="modal-body">
       		<input type="password" name="password" />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
       	</form>
    </div>
  </div>
</div>
<script>


	$('#exampleModal').on('hidden.bs.modal', function() {
		$(this).find("form")[0].reset();
	});
	
	$('#deleteBtn').on('click', function() {
		$('#exampleModal').modal('show');

	});
</script>
</body>
</html>