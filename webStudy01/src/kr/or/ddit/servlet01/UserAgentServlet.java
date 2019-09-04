package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enums.BrowserType;
import kr.or.ddit.enums.OSType;

/**
 * 클라이언트의 시스템을 파악하고, 최종적으로 "당신의 OS는 XXX이고 당신의 브라우저는 XXX입니다" 라는 메세지를 ALERT창으로
 * 띄울것,,
 * 
 * linux/window chrome/firefox/msie
 *
 */
@WebServlet("/userAgent")
public class UserAgentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userAgent = req.getHeader("user-agent").toUpperCase();
		System.out.println(userAgent);
		OSType os = OSType.searchOS(userAgent);
		String osName = os.getName();
		BrowserType browser = BrowserType.searchBrowser(userAgent);
		String browserName = browser.getName();

//		Map<String, String> osMap = new HashMap<String, String>();
//		osMap.put("linux", "리눅스");
//		osMap.put("Windows", "윈도우");
//
//		Map<String, String> browserMap = new HashMap<String, String>();
//		browserMap.put("Chrome", "크롬");
//		browserMap.put("Firefox", "파이어폭스");
//		browserMap.put("MSIE", "익스플로러");	
//
//		for (String key : osMap.keySet()) {
//			if (userAgent.contains(key)) {
//				os = osMap.get(key);
//				break;
//			}
//		}
//
//		for (String key : browserMap.keySet()) {
//			if (userAgent.contains(key)) {
//				browser = browserMap.get(key);
//				break;
//			}
//		}

		StringBuffer html = new StringBuffer();
		resp.setContentType("text/html;charset=UTF-8");
		html.append("<html>");
		html.append("<head>");
		html.append("<script type='text/javascript'>");
		html.append("alert('당신의 OS는 " + osName + " 이고 당신의 브라우저는 " + browserName + "입니다')");
		html.append("</script>");
		html.append("</head>");
		html.append("<body>");
		html.append("<h4> User Agent 분석</h4>");
		html.append("당신의 OS는 " + osName + "이고 당신의 브라우저는 " + browserName + "입니다");
		html.append("</body>");
		html.append("</html>");

		try (PrintWriter out = resp.getWriter();) {

			out.println(html);
		}

	}
}
