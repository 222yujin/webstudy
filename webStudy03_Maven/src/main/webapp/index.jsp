<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"> </script>
<title>Insert title here</title>
</head>
<body>
	<form name="logoutForm"
		action="${pageContext.request.contextPath }/logout" method="post">

	</form>
	<c:choose>
		<c:when test="${not empty authMember }">
			<a href="${cPath }/mypage"> ${authMember.mem_name }님</a>,${authMember.mem_role }<a href="#" onclick="document.logoutForm.submit();">로그아웃.</a>
			<div>
				<h4>접속자 리스트</h4>
				<ul id="userListArea">
					<c:forEach items="${userList }" var="user">
						<li>${user.mem_name }</li>
					</c:forEach>
				</ul>
			</div>
	</c:when>

		<c:otherwise>
			<a href="${cPath }/login">로그인</a>
			<a href="${cPath }/member/memberInsert.do">가입하기</a>
		</c:otherwise>
	</c:choose>
	
<script type="text/javascript">
var userListArea = $('#userListArea');

setInterval(() => {
    $.ajax({
       url : "${cPath}/getUserList.do",
       dataType : "json",
       success : function(resp) {
          var liTags = [];
          $(resp).each(function() {
             let liTag = $("<li>").text(
                         $(this).prop("mem_name")                     
                      );
             liTags.push(liTag);
          });
          userListArea.html(liTags);
       },
       error : function(errorResp) {
          console.log(errorResp.status);
       }
    })
 }, 2000);
</script>

</body>
</html>