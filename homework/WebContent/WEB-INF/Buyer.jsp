<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" />
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.color {
	background-color: crimson;
}

table {
	float: left;
}

input[type=text] {
	width: 98%;
}
</style>
</head>
<body>
	<%
		List<BuyerVO> list = (List) request.getAttribute("list");
	%>
	<div id="div">
		<div style="display: inline-block; width: 150px;">
			<div style="padding: 5px; font-weight: bolder;">목록</div>
			<table>
				<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="listBody">
				</tbody>
			</table>
		</div>

		<div
			style="display: inline-block; vertical-align: top; width: 1200px;">
			<div style="padding: 5px; font-weight: bolder;">상세내용</div>
			<form id="form">
				<table id="detail">
					<thead id="detailHead">
						<tr>
							<th style="width: 150px;">아이디</th>
							<td style="width: 300px;"><input type="text" id="buyer_id"
								name="buyer_id" /></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" id="buyer_name" name="buyer_name" />
							</td>
						</tr>
						<tr>
							<th>구분</th>
							<td><input type="text" id="buyer_lgu" name="buyer_lgu" /></td>
						</tr>
						<tr>
							<th>은행이름</th>
							<td><input type="text" id="buyer_bank" name="buyer_bank" />
							</td>
						</tr>
						<tr>
							<th>은행번호</th>
							<td><input type="text" id="buyer_bankno" name="buyer_bankno" />
							</td>
						</tr>
						<tr>
							<th>입금자명</th>
							<td><input type="text" id="buyer_bankname"
								name="buyer_bankname" /></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" id="buyer_zip" name="buyer_zip" /></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" id="buyer_add1" name="buyer_add1" />
							</td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><input type="text" id="buyer_add2" name="buyer_add2" />
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" id="buyer_comtel" name="buyer_comtel" />
							</td>
						</tr>
						<tr>
							<th>팩스번호</th>
							<td><input type="text" id="buyer_fax" name="buyer_fax" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="text" id="buyer_mail" name="buyer_mail" />
							</td>
						</tr>
						<tr>
							<th>대표</th>
							<td><input type="text" id="buyer_charger"
								name="buyer_charger" /></td>
						</tr>
						<tr>
							<th>?</th>
							<td><input type="text" id="buyer_telext" name="buyer_telext" />
							</td>
						</tr>
					</thead>
				</table>
			</form>
		</div>
	</div>



	<div id="button">
		<input type="button" id="insert" value="등록"> 
		<input type="button" id="update" value="수정">
		<input type="button" id="delete" value="삭제">
		<input type="button" id="save"   value="저장" hidden />
	</div>

	<script>
		$.ajax({
			//data : "",
			dataType : "json",
			success : function(resp) {
				let trTags = [];
				$(resp).each(
						function(i, buyer) {
							let trTag = $("<tr>").append(
									$("<td>").text(buyer.buyer_id),
									$("<td>").text(buyer.buyer_name)

							).prop("id", buyer.buyer_id);
							trTags.push(trTag);
						});
				$('#listBody').html(trTags);
			},
			error : function(err) {
				console.log(err.status);
			}
		});

		$('#listBody').on("click", "tr", function() {
			let id= $(this).prop("id");

			$.ajax({
				url:'<%=request.getContextPath()%>/buyerDetail',
				method : 'get',
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(resp) {
					
					$("#buyer_id").val(resp.buyer_id);
					$("#buyer_name").val(resp.buyer_name);
					$("#buyer_lgu").val(resp.buyer_lgu);
					$("#buyer_bank").val(resp.buyer_bank);
					$("#buyer_bankno").val(resp.buyer_bankno);
					$("#buyer_bankname").val(resp.buyer_bankname);
					$("#buyer_zip").val(resp.buyer_zip);
					$("#buyer_add1").val(resp.buyer_add1);
					$("#buyer_add2").val(resp.buyer_add2);
					$("#buyer_comtel").val(resp.buyer_comtel);
					$("#buyer_fax").val(resp.buyer_fax);
					$("#buyer_mail").val(resp.buyer_mail);
					$("#buyer_charger").val(resp.buyer_charger);
					$("#buyer_telext").val(resp.buyer_telext);
				},
				error : function(err) {
					console.log(err.status);
				}
			});

		})

		
		$('#insert').on("click", function() {
			$('#detail td input').val("");
			$('#save').attr("hidden",false);
			
		})
		
		$('#save').on("click",function(){
			let form = $('#form').serialize();
			$.ajax({
				url:'<%=request.getContextPath()%>/buyerInsert',
				method : 'post',
				data : form,
				dataType : "json",
				success : function(resp) {
					alert("저장성공");
					window.location.reload();
				},
				error : function(err) {
					alert(err.status);
				}
			});

		})
		
		$('#delete').on("click",function(){
			let form = $('#form').serialize();
			$.ajax({
				url:'<%=request.getContextPath()%>/buyerDelete',
				method : 'post',
				data : form,
				dataType : "json",
				success : function(resp) {
					alert("삭제성공");
					window.location.reload();
				},
				error : function(err) {
					alert(err.status);
				}
			});
		})
		
		$('#update').on("click",function(){
				let form = $('#form').serialize();
				$.ajax({
					url:'<%=request.getContextPath()%>/buyerUpdate',
					method : 'post',
					data : form,
					dataType : "json",
					success : function(resp) {
						alert("수정성공");
						window.location.reload();
					},
					error : function(err) {
						alert(err.status);
					}
				});
			})
	</script>
</body>
</html>