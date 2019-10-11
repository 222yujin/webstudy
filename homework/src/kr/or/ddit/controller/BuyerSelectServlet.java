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

@WebServlet("/buyerSelect")
public class BuyerSelectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IBuyerService service = new BuyerServiceImpl();
		List<BuyerVO> list = service.selectBuyerList();
		
		
		String accept = req.getHeader("Accept");

		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");

			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
		} else {

			String viewName = "/WEB-INF/Buyer.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);

		}
	}
}
