<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
  .css{
     background-color : green;
  }
</style>
</head>
<body>
1. 구구단을 2~9단까지 table 출력
 단, 첫단과 세번째단, 마지막단의 컬러를 green
 
 <br><br>
 <table border="1">
 <c:forEach var="i" begin="2" end="9" step="1" varStatus="vs">
    <tr>
   <c:forEach var="j" begin="1" end="9" step="1">
         <c:choose>
            <c:when test="${vs.first or vs.last or vs.index eq 4  }">
               <c:set var="green" value="css"></c:set>
            </c:when>
            <c:otherwise>
               <c:set var="green" value="normal"></c:set>
            </c:otherwise>
         </c:choose>
         <td class="${green }">${i } * ${j } = ${i*j }</td>
   </c:forEach>
    </tr>
 </c:forEach>
 </table>
 <br><br>
 
2. 사용자로부터 특정 사이트의 url 을 입력받음.
 체크박스를 통해 소스로 가져올지 여부 결정.
    ==> 해당 사이트 크로울링.
<br><br>
<div id="naver">

</div>
<div id="daum">


</div>
<script type="text/javascript">
   var naverTag = $("#naver");
   var daumTag = $("#daum");
   function crawling(paramObj){
      $.ajax({
         url : "crawler.jsp",
   //       data : { page:"naver" },
         data : { page:paramObj.page },
         dataType : "html",
         success : function(resp) {
//              var keywords = $(resp).find('.PM_CL_realtimeKeyword_base');
//             var keywords = $(resp).find('.hotissue_builtin');
            var keywords = $(resp).find(paramObj.element);
   
//              naverTag.html(keywords);
//             daumTag.html(keywords);
            paramObj.tag.html(keywords);
         },
         error : function(errorResp) {
            console.log(errorResp.status);
         }
      })
   }
   
   setInterval(() => {
      crawling({
         page : "naver",
         element : ".PM_CL_realtimeKeyword_base",
         tag : naverTag
      });
   
      crawling({
         page : "daum",
         element : ".hotissue_builtin",
         tag : daumTag
      });
   }, 10000);

</script>

<%-- <c:set var="toSource" value="${param.toSource }" /> --%>
<!-- <form> -->
<%--    url 입력 : <input type="url" name="url" value="${param.url }"/><br> --%>
<!--    소스 가져올랫?<input  type="checkbox" name="toSource" value="true" -->
<%--       ${toSource eq 'true' ?"checked":""} /> --%>
<!--    <input type="submit" value="가져오기"/> -->
<!-- </form> -->

<%-- <c:if test="${not empty param.url }"> --%>
<!--   <div id="result"> -->
<%--    <c:import url="${param.url }" var="importPage" /> --%>
<%--    <c:out value="${importPage }" escapeXml="${toSource eq 'true'}"></c:out> --%>
<!--   </div> -->
<%-- </c:if> --%>

</body>
</html>