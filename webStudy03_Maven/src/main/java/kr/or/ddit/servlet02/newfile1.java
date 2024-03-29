package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.enums.FileCommand;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/serverExplorer")
public class newfile1 extends HttpServlet {
	
    private ServletContext application;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.application = this.getServletContext();
    }

    private File[] traversingFolder(File folder) throws FileNotFoundException, URISyntaxException, MalformedURLException {
    	//폴더 존재여부
        if (!folder.exists()) {
            throw new FileNotFoundException(folder.getName() + "에 해당하는 폴더가 없음.");
        } 
        
        //폴더 확인여부
        else if (!folder.isDirectory()) {
            throw new IllegalArgumentException(folder.getName() + "폴더가 아님.");
        }
        
        //파일 배열 반환
        else {
            URI root = this.getServletContext().getResource("").toURI();
            URI current = folder.toURI();
            File[] files = folder.listFiles();
            if (!root.equals(current)) {
                File[] newFiles = new File[files.length + 1];
                System.arraycopy(files, 0, newFiles, 1, files.length);
                newFiles[0] = folder.getParentFile();
                files = newFiles;
            }

            return files;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String src    = request.getParameter("src");
        String target = request.getParameter("target");
        if (src == null) {
            src = "";
        }

        if (target == null) {
            target = "";
        }

        /*
         * application.getRealPath
         * 	: 지정한 경로를 웹 어플리케이션 시스템상의 경로로 변경하여 리턴
         * 	: String path = application.getRealPath("/");      
         * 	  C:\project\.......
         * 	  웹 어플리케이션 루트에 대한 로컬상의 실제 경로를 얻음
         */
        String leftPath  = this.application.getRealPath(src.trim());
        String rightPath = this.application.getRealPath(target.trim());
        int status     = 200;
        String message = null;

        try {
            File[] leftFiles  = this.traversingFolder(new File(leftPath));	//왼쪽 폴더(파일) 목록
            File[] rightFiles = this.traversingFolder(new File(rightPath)); //오른쪽 폴더(파일) 목록
            request.setAttribute("leftFiles", leftFiles);
            request.setAttribute("rightFiles", rightFiles);
        } catch (FileNotFoundException var11) {
            status = 404;
            message = var11.getMessage();
        } catch (IllegalArgumentException var12) {
            status = 400;
            message = var12.getMessage();
        } catch (URISyntaxException var13) {
            var13.printStackTrace();
        }

        if (status == 200) {
            request.getRequestDispatcher("/WEB-INF/views/File.jsp").forward(request, response);
        } else {
            response.sendError(status, message);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String src = request.getParameter("src");
        String target = request.getParameter("target");
        if (src == null) {
            src = "";
        }

        if (target == null) {
            target = "";
        }

        String srcFileParam = request.getParameter("srcFile");
        String commandParam = request.getParameter("command");
        int status = 200;
        if (StringUtils.isBlank(srcFileParam) || StringUtils.isBlank(commandParam)) {
            status = 400;
        }

        File srcFile = new File(this.getServletContext().getRealPath(srcFileParam));
        if (!srcFile.exists() || srcFile.isDirectory()) {
            status = 400;
        }

        FileCommand command = null;

        try {
            command = FileCommand.valueOf(commandParam);
        } catch (Exception var12) {
            status = 400;
        }

        File targetFolder = new File(this.getServletContext().getRealPath(target));
        if (command != null && !FileCommand.DELETE.equals(command) && targetFolder.isFile()) {
            status = 400;
        }

        if (status == 200) {
            command.process(srcFile, targetFolder);
            String queryString = String.format("src=%s&target=%s&srcFile=%s", src, target, srcFileParam);
            response.sendRedirect(request.getContextPath() + "/serverExplorer?" + queryString);
        } else {
            response.sendError(status);
        }

    }
}