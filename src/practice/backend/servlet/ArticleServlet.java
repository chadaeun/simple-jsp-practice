package practice.backend.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.backend.dao.ArticleDao;
import practice.backend.dto.Article;
import practice.backend.dto.User;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleDao articleDao;
	
	@Override
	public void init() throws ServletException {
		articleDao = ArticleDao.getInstance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String root = request.getContextPath();
		String path;
		
		if ("list".equals(action)) {
			path = doList(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("myarticles".equals(action)) {
			path = doMyArticles(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("mvwrite".equals(action)) {
			response.sendRedirect(root + "/article/write.jsp");
		} else {
			response.sendRedirect(root + "/error/404.jsp");
		}
	}

	private String doMyArticles(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Restricted to Access My Articles: Please sign in to view your articles.");
			request.setAttribute("msgClass", "alert-warning");
			return "/article?action=list";
		}
		
		try {
			List<Article> list = articleDao.myArticles(user.getId());
			request.setAttribute("articles", list);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Failed to Load Articles");
			request.setAttribute("msgClass", "article-danger");
		}
		
		return "/myarticles.jsp";
	}

	private String doList(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Article> list = articleDao.list();
			request.setAttribute("articles", list);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Failed to Load Articles");
			request.setAttribute("msgClass", "article-danger");
		}
		
		return "/index.jsp";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
