package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet02.service.ImageListService;
import kr.or.ddit.utils.CookieUtil;



@WebServlet("/imageForm.do")
public class ImageFormServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Model 2 구조
//		1.요청 받기
//		2.요청 분석(request - Line,Header,body)
		String imageName = new CookieUtil(req).getCookieValue("imageCookie");
		req.setAttribute("imageName", imageName);
//		3.service 객체와의 의존관계형성 -> 로직 선택
		ImageListService service = new ImageListService();
		String[] images=service.getImageList();
		req.setAttribute("images", images);
//		4.Scope를 통해 information 공유 
//		5.view 선택
//		6.view 이동.
		
		String viewName="/WEB-INF/views/imageForm.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.include(req, resp);
	}
}
