package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//탈퇴할 사람 아이디 가져오기
		//탈퇴를 하려면 로그인이되어야하고
		//최초의 세션일 수 없음
		//비번이 안넘어왔으면 탈퇴도 못함
		//문제가 생기면 탈퇴 처리 불가능
		//돌아오는건 서비스 그 이넘 타입
		req.setCharacterEncoding("UTF-8");
		IMemberService service = new MemberServiceImpl();
		HttpSession session = req.getSession();
		
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		
		String password = req.getParameter("password");
		if(session.isNew()||authMember==null||StringUtils.isBlank(password)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		MemberVO member = new MemberVO(authMember.getMem_id(),password);
		
		ServiceResult result = service.removeMember(member);
		
		
		String viewName = null;
		String message = null;
		
			switch (result) {
			case INVALIDPASSWORD:
				viewName="/mypage";
				message="비번오류";
				session.setAttribute("message", message);
				break;
			case FAILED:
				viewName="/mypage";
				message="서버 오류";
				session.setAttribute("message", message);
				break;

			default:
				viewName="/";
				session.invalidate();
				break;
			}
		
		resp.sendRedirect(req.getContextPath() + viewName);
	}

}