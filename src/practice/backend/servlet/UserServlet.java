package practice.backend.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.backend.dto.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String root = request.getContextPath();
		String path;
		if ("signup".equals(action)) {
			path = doSignUp(request, response);
			response.sendRedirect(root + path);
		} else {
			response.sendRedirect(root + "/error/404.jsp");
		}
	}

	private String doSignUp(HttpServletRequest request, HttpServletResponse response) {
		if (!request.getParameter("password").equals(request.getParameter("password-confirm"))) {
			request.setAttribute("msg", "Failed to Sign Up: Password and confirm password are not matching.");
			return "/index.jsp";
		}
		
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		
//		TODO here
		
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
