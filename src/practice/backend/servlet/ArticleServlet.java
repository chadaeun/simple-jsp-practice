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
		} else if ("write".equals(action)) {
			doWrite(request, response);  // 어떤 거는 forward 어떤 거는 redirect라서 분리함
		} else if ("view".equals(action)) {
			path = doView(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("delete".equals(action)) {
			path = doDeleteArticle(request, response);
			response.sendRedirect(root + path);
		} else if ("mvmodify".equals(action)) {
			path = doMvModify(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("modify".equals(action)) {
			doModify(request, response);  // 어떤 거는 forward 어떤 거는 redirect라서 분리함
		} else {
			response.sendRedirect(root + "/error/404.jsp");
		}
	}

	private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Restricted to Modify: Please sign in to modify article.");
			request.setAttribute("msgClass", "alert-warning");
			request.getRequestDispatcher("/article?action=list").forward(request, response);
			return;
		}
		
		Article article = new Article();
		article.setNo(Integer.parseInt(request.getParameter("no")));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setUserid(user.getId());
		
		try {
			articleDao.update(article, user.getId());
			response.sendRedirect(request.getContextPath() + "/article?action=view&no=" + article.getNo());
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Failed to Modify");
			request.setAttribute("msgClass", "alert-danger");
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
	}

	private String doMvModify(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Restricted to Modify: Please sign in to modify article.");
			request.setAttribute("msgClass", "alert-warning");
			return "/article?action=list";
		}
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			Article article = articleDao.view(no);
			
			if (!article.getUserid().equals(user.getId())) {
				request.setAttribute("msg", "Restricted to Modify: You are not allowed to modify other's article.");
				request.setAttribute("msgClass", "alert-warning");
				return "/article?action=list";
			}
			
			request.setAttribute("article", article);
			
			return "/article/modify.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/404.jsp";
		}
	}

	private String doDeleteArticle(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Restricted to Delete: Please sign in to delete article.");
			request.setAttribute("msgClass", "alert-warning");
			return "/article?action=list";
		}
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			articleDao.delete(no, user.getId());
			return "/article?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/404.jsp";
		}
	}

	private String doView(HttpServletRequest request, HttpServletResponse response) {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			Article article = articleDao.view(no);
			
			if (article == null) {
				return "/error/404.jsp";
			}
			
			request.setAttribute("article", article);
			return "/article/view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/404.jsp";
		}
	}

	private void doWrite(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Restricted to Write: Please sign in to write article.");
			request.setAttribute("msgClass", "alert-warning");
			request.getRequestDispatcher("/article?action=list").forward(request, response);
			return;
		}
		
		Article article = new Article();
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setUserid(user.getId());
		
		try {
			int no = articleDao.insert(article);
			response.sendRedirect(request.getContextPath() + "/article?action=view&no=" + no);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Failed to Submit");
			request.setAttribute("msgClass", "alert-danger");
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
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
