package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConn;
import domain.boards.Boards;
import web.dto.boards.SaveReqDto;

public class BoardDao {
	
	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}	
	
	// 게시글 전체 찾기
	public List<Boards> findAll() { 
		String sql = "SELECT id, userId, title, content, createDate FROM boards ";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Boards> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				Boards board = Boards.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.userId(rs.getInt("userId"))
						.createDate(rs.getTimestamp("createDate"));
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return null;
	}
	
	
	// 아이디로 게시글 하나 찾기
	public Boards findById(int boardId) { 
		String sql = "SELECT id, userId, title, content, createDate FROM boards WHERE id = ? ";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				Boards board = Boards.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.userId(rs.getInt("userId"))
						.createDate(rs.getTimestamp("createDate"));
				
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return null;
	}

	//게시글 쓰기
	public int save(SaveReqDto dto) { 
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
