package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.CommandType;

@WebServlet("/imagesFolderProcess")
public class ImageViewerModel2Servlet extends HttpServlet {
	ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String imagesURI = "/images";
		String imagesPath = application.getRealPath(imagesURI);
		File imagesFolder = new File(imagesPath);

		String targetURI = "/07/";
		String targetPath = application.getRealPath(targetURI);
		File targetFolder = new File(targetPath);

		application.setAttribute("imagesFolder", imagesFolder);
		application.setAttribute("targetFolder", targetFolder);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imagesFolder = (File) application.getAttribute("imagesFolder");
		File targetFolder = (File) application.getAttribute("targetFolder");
		
		String[] files = imagesFolder.list((dir, name) -> {
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/");
		});
		
		String[] targetfiles = targetFolder.list();
		
		

		req.setAttribute("imagefiles", files);
		req.setAttribute("targetfiles", targetfiles);

		String view = "WEB-INF/views/imagesFolderView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename = req.getParameter("filename");
		String command = req.getParameter("command");
		int status = 200;

		if (StringUtils.isBlank(filename) || StringUtils.isBlank(command)) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			File imagesFolder = (File) application.getAttribute("imagesFolder");

			File imageFile = new File(imagesFolder, filename);

			if (!imageFile.exists()) {
				status = HttpServletResponse.SC_NOT_FOUND;
			}

			try {
				CommandType commandtype = CommandType.valueOf(command.toUpperCase());
				if (status == 200) {
					File targetFolder = (File) application.getAttribute("targetFolder");
					commandtype.commandProcess(imageFile, targetFolder);
				}

			} catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}

			if (status == 200) {
				resp.sendRedirect(req.getContextPath() + "/imagesFolderProcess");
			} else {
				resp.sendError(status);
			}
		}

	}
}
