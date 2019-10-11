package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MyPageController {

	IMemberService service = new MemberServiceImpl();

	@URIMapping("/mypage")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String viewName = null;

		MemberVO savedMember = service.retriveMember(authMember);
		req.setAttribute("savedMember", savedMember);
		viewName = "member/mypage";

		return viewName;

	}

}
