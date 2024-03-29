package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler
public class MemberListController {//plain old java object
   IMemberService service = new MemberServiceImpl();

   @URIMapping(value="/member/memberList.do",method=HttpMethod.GET)
   public String memberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pageParam = req.getParameter("page");
      
      String searchType = req.getParameter("searchType");
      String searchWord = req.getParameter("searchWord");
      
      Map<String, Object> searchMap = new HashMap<String, Object>();
      searchMap.put("searchType", searchType);
      searchMap.put("searchWord", searchWord);
      
      int currentPage = 1;
      if(StringUtils.isNumeric(pageParam)) {
         currentPage = Integer.parseInt(pageParam);
      }
      
      // 2가지 케이스중에 어느 케이스로 왔는지를 잡아야한다
      String accept = req.getHeader("Accept");
      
      PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<MemberVO>(5, 3);
      
      pagingVO.setSearchMap(searchMap);
      
      int totalRecord = service.retrieveMemberCount(pagingVO);
      pagingVO.setTotalRecord(totalRecord);
      pagingVO.setCurrentPage(currentPage);
      
      // service -> retr
      List<MemberVO> list = service.retriveMemberList(pagingVO);
      pagingVO.setDataList(list);
      if (accept.toLowerCase().contains("json")) {
         resp.setContentType("application/json;charset=UTF-8");

         String json = new MarshallingUtils().marshalling(pagingVO);

         try (PrintWriter out = resp.getWriter();) {
            out.println(json);
         }
         return null;
      } else {
         String viewName = "member/memberList";
         //req.getRequestDispatcher(viewName).forward(req, resp);
         return viewName;
      }

   }
}