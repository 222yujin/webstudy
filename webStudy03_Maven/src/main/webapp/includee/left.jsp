<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="leftForm" action="<%=request.getContextPath()%>/module/layout.do">
<input type="hidden" name="command" />
</form>
<ul>
	<li><a href="#" id="standard"/>스탠다드jsp</a></li>
	<li><a href="#" id="gugudan"/>구구단</a></li>
	<li><a href="#" id="calendar"/>달력</a></li>
	<li><a href="#" id="time"/>시간확인</a></li>
	<li><a href="#" id="image"/>이미지뷰어</a></li>
	<li><a href="#" id="explorer"/>Server Explorer</a></li>
</ul>
<script>
	var leftForm = $("#leftForm");
	$(function() {
		$("#leftMenu a").on('click', function() {
			var command = $(this).prop("id");
			leftForm.find("[name='command']").val(command);
			leftForm.submit();
		});
	});
</script>

