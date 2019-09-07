package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
   // 디자인을 수정하면 굳이 다시 컴파일 하지 않아도 된다.
   // HttpServletRequest 캡슐화해서 가지고 있는 아이


   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      try (InputStream is = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
         String tmp = null;
         while ((tmp = reader.readLine()) != null) {
            System.out.printf("===============>%s\n", tmp);
         }

      }
      String minParam = req.getParameter("minDan"); // 파라미터의 기본적인 형태는 String
      String maxParam = req.getParameter("maxDan");

      // 확인하기위한 검증코드 클라이언트의 입력을 제한하기위해서
      int minDan = 2;
      int maxDan = 9;

      if (minParam != null && minParam.matches("[0-9]{1,2}") && maxParam != null && maxParam.matches("[0-9]{1,2}")) {
         minDan = Integer.parseInt(minParam);
         maxDan = Integer.parseInt(maxParam);
      }

      resp.setContentType("text/html;charset=UTF-8");
      StringBuffer template = readTemplate("gugudan.tmpl");

      StringBuffer gugudan = new StringBuffer();

      String ptrn = "<td>%d*%d=%d</td>";
      for (int dan = minDan; dan <= maxDan; dan++) {
         gugudan.append("<tr>");
         for (int mul = 1; mul <= 9; mul++) {
            gugudan.append(String.format(ptrn, dan, mul, dan * mul));
         }
         gugudan.append("</tr>");
      }

      int idx = template.indexOf("@gugudan");
      StringBuffer html = template.replace(idx, idx + "@gugudan".length(), gugudan.toString());
      idx = html.indexOf("@cnt");
      html = html.replace(idx, idx + "@cnt".length(), (9 - 1 + 1) + "");

      try (
            // closable객체의 선언
            PrintWriter out = resp.getWriter();) {
    	  	out.println(html);
    	  	out.println(getServletContext().hashCode());
      }
   }

   private StringBuffer readTemplate(String name) throws IOException {
      StringBuffer template = new StringBuffer();
      try (InputStream is = GugudanServlet.class.getResourceAsStream(name); // 상대경로표기방식

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));// 2차스트림의 별도의 reader가 있을때
      // 1차스트림과 2차스트림의 중간의 역할을 하는게 new InputStreamReader(is)
      ) {
         String tmp = null;
         while ((tmp = reader.readLine()) != null) {
            template.append(tmp + "\n");
         }
      }

      return template;
   }
}