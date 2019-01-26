package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.User;

public class MyAccountServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/client/login.jsp");
			return;
		}
		if ("超级用户".equals(user.getRole())) {
			response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
		}else{
			response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
		}
	}
}
