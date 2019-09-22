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

@WebServlet("/buyerDetail")
public class BuyerDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("id");
		BuyerVO buyer = new BuyerVO();
		buyer.setBuyer_id(id);
		
		IBuyerService service = new BuyerServiceImpl();
		BuyerVO vo =  service.selectBuyer(buyer);

		String accept = req.getHeader("Accept");

		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");

			String json = new MarshallingUtils().marshalling(vo);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
		} else {

			String viewName = "/WEB-INF/Buyer.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);

		}
	}
}
