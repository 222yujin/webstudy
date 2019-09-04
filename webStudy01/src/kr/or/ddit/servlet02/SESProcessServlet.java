package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sesProcess")
public class SESProcessServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> memberMap = new LinkedHashMap<>();
		memberMap.put("a001", new String[] { "바다", "/ses/bada.jsp" });
		memberMap.put("a002", new String[] { "유진", "/ses/yujin.jsp" });
		memberMap.put("a003", new String[] { "슈", "/ses/sue.jsp" });

		String sesMember = request.getParameter("sesMember");
		if (StringUtils.isNotBlank(sesMember)) {
			if (!memberMap.containsKey(sesMember)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 멤버는 존재하지 않음.");
				return;
			}
			String[] record = memberMap.get(sesMember);
			String path = "/WEB-INF" + record[1];
			// String path="/05"+record[1];
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			// rd.include(request, response);
			// response.sendRedirect(request.getContextPath()+path);
			return;
			// wev-inf로 폴더를 옮기면,, 접근이 안되서 Redirect로 접근 불가
		} else {
			response.sendError(400,"어떤 멤버가 필요한가,.,?");
		}
	}

}