package com.naver.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.api.NSearch;

/**
 * Servlet implementation class NaverServlet
 */
@WebServlet("/nsearch")
public class NaverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// index.html 에서 검색어를 받아서 아래 매소드에 넣어서 결과를 받은 내용을
		// index.html 에 검색 결과를 출력( jQuery(.getJson 활용) )
		String search = request.getParameter("search");
		String json = NSearch.getSearch(search);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(json);
		// Gson 안써도 됨. 왜? 네이버에서 json 결과로 주니까!!
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
