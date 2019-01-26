package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.exception.ActiveUserException;
import cn.itcast.bookStore.service.UserService;

public class ActiveUserServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		try{
			service.activeUser(activeCode);
			response.sendRedirect(request.getContextPath() + "/client/activesuccess.jsp");
		} catch (ActiveUserException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
