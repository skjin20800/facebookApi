package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConn;
import domain.user.User;
import web.dto.auth.JoinReqDto;
import web.dto.auth.LoginReqDto;
import web.dto.boards.SaveReqDto;

public class BoardDao {

	public int save(SaveReqDto dto) { // 회원가입
		String sql = "INSERT INTO boards(userId, title, content, createDate) VALUES(?,?,?, now())";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return -1;
	}


}