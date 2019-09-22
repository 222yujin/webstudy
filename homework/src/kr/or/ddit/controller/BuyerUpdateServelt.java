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

@WebServlet("/buyerUpdate")
public class BuyerUpdateServelt extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BuyerVO buyer = new BuyerVO();
		
		buyer.setBuyer_add1(req.getParameter("buyer_add1"));
		buyer.setBuyer_add2(req.getParameter("buyer_add2"));
		buyer.setBuyer_bank(req.getParameter("buyer_bank"));
		buyer.setBuyer_bankname(req.getParameter("buyer_bankname"));
		buyer.setBuyer_bankno(req.getParameter("buyer_bankno"));
		buyer.setBuyer_charger(req.getParameter("buyer_charger"));
		buyer.setBuyer_comtel(req.getParameter("buyer_comtel"));
		buyer.setBuyer_fax(req.getParameter("buyer_fax"));
		buyer.setBuyer_id(req.getParameter("buyer_id"));
		buyer.setBuyer_lgu(req.getParameter("buyer_lgu"));
		buyer.setBuyer_mail(req.getParameter("buyer_mail"));
		buyer.setBuyer_name(req.getParameter("buyer_name"));
		buyer.setBuyer_telext(req.getParameter("buyer_telext"));
		buyer.setBuyer_zip(req.getParameter("buyer_zip"));

		IBuyerService service = new BuyerServiceImpl();
		int update = service.updateBuyer(buyer);

		int[] result = new int[1];
		result[0]=update;
		

		String json = new MarshallingUtils().marshallingArrayToJson(result);

		try (PrintWriter out = resp.getWriter();) {
			out.println(json);
		}
		if(update>0) {
			System.out.println("성공");
		}
		
	}
}
