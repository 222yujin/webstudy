package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class calculator extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String num1 = req.getParameter("leftOp");
		String num2 = req.getParameter("rightOp");
		int cnt = 0;
		int cnt1 = 0;
		String select = req.getParameter("operator");
		OperatorType op = OperatorType.searchParams(select);
		String operator = op.getName();

		StringBuffer html = new StringBuffer();
		
		if (num1 != null && num1.matches("\\d+") && num2 != null && num2.matches("\\d+")) {
			cnt = Integer.parseInt(num1);
			cnt1 = Integer.parseInt(num2);
		}

		resp.setContentType("text/html;charset=UTF-8");
		html.append("<html>");
		html.append("<head>");
		html.append("<body>");
		html.append("<h4> calculator</h4>");
		html.append("클라이언트 이름 : " + name + "<br>");
		
		
		if (operator.equals("+")) {
			html.append(num1 + operator + num2 + "=" + (cnt + cnt1));
		} else if (operator.equals("-")) {
			html.append(num1 + operator + num2 + "=" + (cnt - cnt1));
		} else if (operator.equals("*")) {
			html.append(num1 + operator + num2 + "=" + (cnt * cnt1));
		} else if (operator.equals("/")) {
			html.append(num1 + operator + num2 + "=" + (cnt / cnt1));
		}
		
		
		html.append("</body>");
		html.append("</head>");
		html.append("</html>");

		try (

				PrintWriter out = resp.getWriter();) {

			out.println(html);
		}
	}
}
