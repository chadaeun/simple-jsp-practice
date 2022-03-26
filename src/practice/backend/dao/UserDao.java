package practice.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import practice.backend.dto.User;
import practice.backend.util.DBUtil;

public class UserDao {
	private static UserDao instance;
	private DBUtil dbUtil;
	private UserDao() {
		dbUtil = DBUtil.getInstance();
	}
	public static UserDao getIntance() {
		if (instance == null) instance = new UserDao();
		return instance;
	}
	
	public void signUp(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "INSERT INTO users \n";
			sql += "(id, password) \n";
			sql += "VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	public User signIn(String id, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "SELECT id FROM users \n";
			sql += "WHERE id = ? and password = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
		return user;
	}
}
