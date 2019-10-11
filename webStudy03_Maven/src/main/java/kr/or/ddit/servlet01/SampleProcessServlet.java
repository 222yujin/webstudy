package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sampleProcess")//절대경로 "서버/웹서비스01"이 생략
public class SampleProcessServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String numParam = req.getParameter("numParam");
		if (numParam != null && numParam.matches("\\d+"))
			System.out.println(Integer.parseInt(numParam) * 300);

		String textParam = req.getParameter("textParam");
		String radioParam = req.getParameter("radioParam");
		String[] checkParam = req.getParameterValues("checkboxParam");
		String selectParam1 = req.getParameter("selectParam1");
		String[] selectParam2 = req.getParameterValues("selectParam2");

		Map<String, String[]> pMap = req.getParameterMap();
		Set<String> nameSet = pMap.keySet();
		Iterator<String> it = nameSet.iterator();

		while (it.hasNext()) {
			String pName = it.next();
			String[] values = pMap.get(pName);
			System.out.printf("%s : %s \n", pName, Arrays.toString(values));

		}

	}
}
