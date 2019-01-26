package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String flag = request.getParameter("flag");
		if (flag == null || flag.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
