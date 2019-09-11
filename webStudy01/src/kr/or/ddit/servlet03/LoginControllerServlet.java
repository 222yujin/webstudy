package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login")
public class LoginControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/login/loginForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");

		if (StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)) {
			resp.sendError(400, "아이디나 비번 누락");
			return;
		}

		HttpSession session = req.getSession(false);

		if (session == null || session.isNew()) {
			resp.sendError(400, "로그인 절차가 이상해,,!");
			return;
		}
		
		IAuthenticateService service = new AuthenticateServiceImpl();
		
		try {
			
			MemberVO savedMember =service.authenticate(new MemberVO(mem_id,mem_pass));
			session.setAttribute("authMember", savedMember);
			
			
			// 이동방식
			resp.sendRedirect(req.getContextPath() + "/");
		}catch (UserNotFoundException  |NotAuthenticatedException e) {
			session.setAttribute("message", e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		

	}
}
