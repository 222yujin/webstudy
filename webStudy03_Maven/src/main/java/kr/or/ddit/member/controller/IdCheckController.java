package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class IdCheckController {

	IMemberService service = new MemberServiceImpl();

	@URIMapping(value="/member/idCheck.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");

		if (StringUtils.isBlank(mem_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Pattern regex=Pattern.compile("([a-z]+[a-zA-Z0-9]{3,11})");
		Matcher matcher = regex.matcher(mem_id);
		while(matcher.find()) {
			System.out.println(matcher.group(1));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.retriveMember(new MemberVO(mem_id, null));
			result.put("valid", new Boolean(false));
		} catch (UserNotFoundException e) {
			result.put("valid", new Boolean(true));
			result.put("useId", mem_id);
		}

		resp.setContentType("application/json;charset=UTF-8");
		String json = new MarshallingUtils().marshalling(result);

		try (PrintWriter out = resp.getWriter()) {
			out.println(json);
		}
		
		return null;
	}
}
