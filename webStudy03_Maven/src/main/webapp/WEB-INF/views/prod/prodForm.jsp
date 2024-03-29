<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	.error{
		color: red;
	}
</style>	

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath }/js/generateLprodAndBuyer.js"></script>	
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>	
</head>
<body>
	<form method="post" enctype="multipart/form-data"> 
		<table class="table table-bordered">
			<tr>
				<th>상품코드</th>
				<td><input type="hidden" class="form-control"
					name="prod_id" value="${prod.prod_id}" /><span class="error">${errors.prod_id}</span></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" required class="form-control"
					name="prod_name" value="${prod.prod_name}" /><span class="error">${errors.prod_name}</span></td>
			</tr>
			<tr>
				<th>분류코드</th>
				<td>
					<select name="prod_lgu">
						<option value>분류선택</option>
					</select>
				<span class="error">${errors.prod_lgu}</span></td>
			</tr>
			<tr>
				<th>거래처코드</th>
				<td>
					<select name="prod_buyer">
						<option value>거래처선택</option>
					</select>
				<span class="error">${errors.prod_buyer}</span></td>
			</tr>
			<tr>
				<th>구매가</th>
				<td><input type="number" required class="form-control"
					name="prod_cost" value="${prod.prod_cost}" /><span class="error">${errors.prod_cost}</span></td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="number" required class="form-control"
					name="prod_price" value="${prod.prod_price}" /><span class="error">${errors.prod_price}</span></td>
			</tr>
			<tr>
				<th>세일가</th>
				<td><input type="number" required class="form-control"
					name="prod_sale" value="${prod.prod_sale}" /><span class="error">${errors.prod_sale}</span></td>
			</tr>
			<tr>
				<th>OUTLINE</th>
				<td><input type="text" required class="form-control"
					name="prod_outline" value="${prod.prod_outline}" /><span
					class="error">${errors.prod_outline}</span></td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td><input type="text" class="form-control" name="prod_detail"
					value="${prod.prod_detail}" /><span class="error">${errors.prod_detail}</span></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
				<input type="file" name="prod_image" 
					accept="image/*"
				/>
				
				<span class="error">${errors.prod_img}</span></td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td><input type="number" required class="form-control"
					name="prod_totalstock" value="${prod.prod_totalstock}" /><span
					class="error">${errors.prod_totalstock}</span></td>
			</tr>
			<tr>
				<th>입고일</th>
				<td><input type="date" class="form-control" name="prod_insdate"
					value="${prod.prod_insdate}" /><span class="error">${errors.prod_insdate}</span></td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td><input type="number" required class="form-control"
					name="prod_properstock" value="${prod.prod_properstock}" /><span
					class="error">${errors.prod_properstock}</span></td>
			</tr>
			<tr>
				<th>크기</th>
				<td><input type="text" class="form-control" name="prod_size"
					value="${prod.prod_size}" /><span class="error">${errors.prod_size}</span></td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="text" class="form-control" name="prod_color"
					value="${prod.prod_color}" /><span class="error">${errors.prod_color}</span></td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td><input type="text" class="form-control"
					name="prod_delivery" value="${prod.prod_delivery}" /><span
					class="error">${errors.prod_delivery}</span></td>
			</tr>
			<tr>
				<th>단위</th>
				<td><input type="text" class="form-control" name="prod_unit"
					value="${prod.prod_unit}" /><span class="error">${errors.prod_unit}</span></td>
			</tr>
			<tr>
				<th>입고량</th>
				<td><input type="number" class="form-control" name="prod_qtyin"
					value="${prod.prod_qtyin}" /><span class="error">${errors.prod_qtyin}</span></td>
			</tr>
			<tr>
				<th>판매량</th>
				<td><input type="number" class="form-control"
					name="prod_qtysale" value="${prod.prod_qtysale}" /><span
					class="error">${errors.prod_qtysale}</span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="prod_mileage" value="${prod.prod_mileage}" /><span
					class="error">${errors.prod_mileage}</span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn btn-primary" type="submit" value="저장" />
					<input class="btn btn-warning" type="reset" value="취소" />
				</td>
			</tr>
			
		</table>
	</form>
<script type="text/javascript">
	var prod_lguTag = $("[name='prod_lgu']");
	prod_lguTag.generateLprod("${pageContext.request.contextPath}","${prod.prod_lgu}");
	var prod_buyerTag = $("[name='prod_buyer']");
	prod_buyerTag.generateBuyer({
		cPath:"${pageContext.request.contextPath}",
		selectedBuyer:"${prod.prod_buyer}"
	});
	
	$(prod_lguTag).on("change", function(){
		let lguCode = $(this).val();
		if(!lguCode) return;
		let options = $(prod_buyerTag).find("option:gt(0)");
		$(options).hide();
		let lguOptions = $(prod_buyerTag).find("option."+lguCode);
		$(lguOptions).show();
		
	});
	
</script>
	
</body>
</html>







