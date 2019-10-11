package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class LoginController {

	@URIMapping("/login")
	public String loginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String saveId = new CookieUtil(req).getCookieValue("idCookie");
//		req.setAttribute("saveId", saveId);

		String viewName = "login/loginForm";
		return viewName;
	}

	@URIMapping(value = "/login", method = HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");

		if (StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)) {
			resp.sendError(400, "아이디나 비번 누락");
			return null;
		}

		HttpSession session = req.getSession(false);

		if (session == null || session.isNew()) {
			resp.sendError(400, "로그인 절차가 이상해,,!");
			return null;
		}

		IAuthenticateService service = new AuthenticateServiceImpl();
		String viewName = null;
		try {

			MemberVO savedMember = service.authenticate(new MemberVO(mem_id, mem_pass));
			String checkbox = req.getParameter("idSave");
			Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id);
			int maxAge = 0;
			if ("idSave".equals(checkbox)) {
				maxAge = 24 * 60 * 60 * 2;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);

			session.setAttribute("authMember", savedMember);
			// 이동방식
			viewName = "redirect:/";
		} catch (UserNotFoundException | NotAuthenticatedException e) {
			session.setAttribute("message", e.getMessage());
			viewName = "redirect:/login";
		}

		return viewName;
	}
}
