package practice.backend.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.exceptions.SQLError;

import practice.backend.dao.UserDao;
import practice.backend.dto.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getIntance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String root = request.getContextPath();
		String path;
		if ("signup".equals(action)) {
			path = doSignUp(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("signin".equals(action)) {
			path = doSignIn(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("signout".equals(action)) {
			path = doSignOut(request, response);
			response.sendRedirect(root + "/article?action=list");
		} else {
			response.sendRedirect(root + "/error/404.jsp");
		}
	}

	private String doSignOut(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Sign Out");
		
		request.getSession().invalidate();
		return "/article?action=list";
	}

	private String doSignIn(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Sign In");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		try {
			User user = userDao.signIn(id, password);
			if (user != null) {
				request.getSession().setAttribute("user", user);				
			} else {
				request.setAttribute("msg", "Failed to Sign In: Please check your ID and password.");
				request.setAttribute("msgClass", "alert-danger");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "/error/500.jsp";
		}
		
		return "/article?action=list";
	}

	private String doSignUp(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Sign Up");
		if (!request.getParameter("password").equals(request.getParameter("password-confirm"))) {
			request.setAttribute("msg", "Failed to Sign Up: Password and confirm password are not matching.");
			request.setAttribute("msgClass", "alert-danger");
			return "/article?action=list";
		}
		
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		
		try {
			userDao.signUp(user);
			request.setAttribute("msg", "Sign Up Succeeded!");
			request.setAttribute("msgClass", "alert-success");
			return "/article?action=list";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Failed to Sign Up: ID is duplicated.");
			request.setAttribute("msgClass", "alert-danger");
			return "/article?action=list";
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
