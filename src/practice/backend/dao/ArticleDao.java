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
			sql += "FROM article";
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
			sql += "WHERE userid = ?";
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
}
