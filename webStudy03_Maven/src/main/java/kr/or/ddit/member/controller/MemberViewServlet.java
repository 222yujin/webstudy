package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sun.net.httpserver.spi.HttpServerProvider;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;


@CommandHandler
public class MemberViewServlet{
	IMemberService service = new MemberServiceImpl();

	@URIMapping("/member/memberView.do")
	public String memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String who = req.getParameter("who");

		if (StringUtils.isBlank(who)) {
			resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "누구를 조회함?");
			return null;
		}

		MemberVO member = service.retriveMember(new MemberVO(who, null));

//		String accept = req.getHeader("Accept");

//		if (accept.toLowerCase().contains("json")) {
//			resp.setContentType("application/json;charset=UTF-8");
//
//			String json = new MarshallingUtils().marshalling(member);
//
//			try (PrintWriter out = resp.getWriter();) {
//				out.println(json);
//			}
//		} else {

		req.setAttribute("member", member);
		String viewName = "member/memberView";
		return viewName;

		// }

	}

}
