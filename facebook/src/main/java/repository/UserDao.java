package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConn;
import domain.user.User;
import web.dto.auth.JoinReqDto;
import web.dto.auth.LoginReqDto;

public class UserDao {
	
	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}	

	// 회원가입
	public int save(JoinReqDto dto) { 
	
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO user(username, password, nickname, age, createDate) ");
		sb.append("VALUES(?, ?, ?, ?, now()) ");
		String sql = sb.toString();	
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			pstmt.setInt(4, dto.getAge());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	//로그인 정보로 User데이터 찾기
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id, username, password, nickname, age, createDate FROM user WHERE username = ? AND password = ?";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = User.builder()
				.id(rs.getInt("id"))
				.username(rs.getString("username"))
				.nickname(rs.getString("nickname"))
				.age(rs.getInt("age"))
				.createDate(rs.getTimestamp("createDate"));
										
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConn.close(conn,pstmt,rs);
		}
		
		return null;
	}
	
	// username으로 User 데이터 한 건 조회
	public User findByUsername(String username) {
		String sql = "SELECT id, username, password, nickname, age, createDate FROM user WHERE username = ? ";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = User.builder()
				.id(rs.getInt("id"))
				.username(rs.getString("username"))
				.password(rs.getString("password"))
				.nickname(rs.getString("nickname"))
				.age(rs.getInt("age"))
				.createDate(rs.getTimestamp("createDate"));
										
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConn.close(conn,pstmt,rs);
		}
		
		return null;
	}
	
}
