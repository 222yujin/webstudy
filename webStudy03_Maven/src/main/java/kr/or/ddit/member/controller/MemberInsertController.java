package kr.or.ddit.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class MemberInsertController  {
	IMemberService service = new MemberServiceImpl();

	@URIMapping("/member/memberInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터를 받아감
		// ui로 연결
		
		return "member/MemberForm";
	}

@URIMapping(value="/member/memberInsert.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터를 서버로 보내기 위함
		// 보내지는 데이터 받음
		// membervo를 만들어 데이터 채워넣기
		// 실패하는경우가 있으니 계속 가지고 다녀야함 스코프에다!
		// 검증해야함
		// 필수데이터,타입,길이 등등 검증해야한다

		// 검증을 통과하면 등록을 해준다
		// 의존관계 형성,로직 선택,,로직 호출 돌아오는 결과 createmember return타입이 service result이니깐 결과에 따라
		// 가입에 성공을 하면 로그인할수있게 ->< loginform이나 index로 가야함 리다이렉션!!

		// 통과하지 못했으면 멤버폼으로 가야함,기존의 입력데이타랑 뭐가 잘못됐는지 가지고 가야한다
		// 아이디 중복-> 다른 아이디로 바꾸게 해줌 ->멤버폼,, 매새지 가지고,,기존입력데이타
		// failed -> 서버상의 문제 -> 멤버폼,,메세지 가져가야함,기존입력데이타

		// 멤버업데이트 서블릿이랑 비슷하다고?

		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();

		req.setAttribute("member", member);

		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		if(req instanceof MultipartRequestWrapper) {
			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrapper("mem_img");
			
			if(partWrapper!=null) {
				member.setMem_img(partWrapper.getBytes());
			}
		}
		
		
		Map<String, String> errors = new HashMap<String, String>();

		req.setAttribute("errors", errors);

		boolean valid = validate(member, errors);

		String viewName = "member/MemberForm";
		String message = null;
		

		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				message = "아이디 중복";
				viewName = "member/MemberForm";
				break;
			case FAILED:
				message = "서버 오류";
				viewName = "member/MemberForm";
				break;

			default:
				message = "가입 성공";
			
				viewName = "redirect:/";
				break;
			}
			
		} else {
			viewName = "/member/MemberForm";
		}

		req.setAttribute("message", message);

		
		return viewName;

	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		return valid;
	}

}
