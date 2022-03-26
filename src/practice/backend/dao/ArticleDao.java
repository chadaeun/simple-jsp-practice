package practice.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import practice.backend.dto.Article;
import practice.backend.util.DBUtil;

public class ArticleDao {
	private static ArticleDao instance;
	private DBUtil dbUtil;
	private ArticleDao() {
		dbUtil = DBUtil.getInstance();
	}
	public static ArticleDao getInstance() {
		if (instance == null) instance = new ArticleDao();
		return instance;
	}
	
	public List<Article> list() throws SQLException {
		List<Article> list = new ArrayList<Article>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "SELECT no, userid, subject, content \n";
			sql += "FROM article \n";
			sql += "ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Article article = new Article();
				article.setNo(rs.getInt("no"));
				article.setUserid(rs.getString("userid"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	public List<Article> myArticles(String id) throws SQLException {
		List<Article> list = new ArrayList<Article>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "SELECT no, userid, subject, content \n";
			sql += "FROM article \n";
			sql += "WHERE userid = ? \n";
			sql += "ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Article article = new Article();
				article.setNo(rs.getInt("no"));
				article.setUserid(rs.getString("userid"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public int insert(Article article) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "INSERT INTO article \n";
			sql += "(userid, subject, content) \n";
			sql += "VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getUserid());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.executeUpdate();
			
			pstmt.close();
			sql = "SELECT MAX(no) FROM article";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public Article view(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "SELECT no, userid, subject, content \n";
			sql += "FROM article \n";
			sql += "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.next();
			article = new Article();
			article.setNo(rs.getInt("no"));
			article.setUserid(rs.getString("userid"));
			article.setSubject(rs.getString("subject"));
			article.setContent(rs.getString("content"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return article;
	}
	public void delete(int no, String userid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "DELETE FROM article \n";
			sql += "WHERE no = ? and userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	public void update(Article article, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "UPDATE article \n";
			sql += "SET subject = ?, content = ? \n";
			sql += "WHERE no = ? and userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getNo());
			pstmt.setString(4, article.getUserid());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
}
