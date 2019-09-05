package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/imagesfolder")
public class imagesFolderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/imagesFolderView.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename = req.getParameter("filename");
		String command = req.getParameter("command");

		// 가져오는가
		String uri = "D:\\A_TeachingMaterial\\7.JspSpring\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webStudy01\\images\\"
				+ filename;
		// 저장할거
		String targetUri = "D:\\A_TeachingMaterial\\7.JspSpring\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webStudy01\\07\\"
				+ filename;

		if (command.equals("copy")) {
			try (FileInputStream input = new FileInputStream(uri);
					FileOutputStream output = new FileOutputStream(targetUri);) {
				IOUtils.copy(input, output);
			}
		} else if (command.equals("move")) {
			// 현재 파일 위치
			File file = new File(uri);
			// 이동할 파일위치
			File fileToMove = new File(targetUri);

			file.renameTo(fileToMove);

		} else if (command.equals("delete")) {
			File imageFile = new File(uri);
			imageFile.delete();

		}

		String path = "/imagesfolder";
		String location = req.getContextPath() + path;
		resp.sendRedirect(location);
	}
}
