package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.BuyerServiceImpl;
import kr.or.ddit.service.IBuyerService;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyerDelete")
public class BuyerDeleteServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BuyerVO buyer = new BuyerVO();
		
		buyer.setBuyer_id(req.getParameter("buyer_id"));
		
		
		
		IBuyerService service = new BuyerServiceImpl();
		int delete = service.deleteBuyer(buyer);
		
		int[] result = new int[1];
		result[0]=delete;
		

		String json = new MarshallingUtils().marshallingArrayToJson(result);


		try (PrintWriter out = resp.getWriter();) {
			out.println(json);
		}
		if(delete>0) {
			System.out.println("성공");
		}
		
	}
}
