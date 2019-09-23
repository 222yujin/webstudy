package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	IMemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 2가지 케이스중에 어느 케이스로 왔는지를 잡아야한다
		String accept = req.getHeader("Accept");

		// service -> retr
		List<MemberVO> list = service.retriveMemberList();
		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");

			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
		} else {

			String viewName = "/WEB-INF/views/member/memberList.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);

		}

	}
}